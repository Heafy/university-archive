package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Clase para hilos de ejecución que manejan conexiones del servidor.
 */
public class HiloServidor<T extends Registro> extends Thread {

    /* El servidor de base de datos. */
    private ServidorBaseDeDatos<T> sbdd;
    /* La base de datos. */
    private BaseDeDatos<T> bdd;
    /* El enchufe. */
    private Socket enchufe;
    /* El nombre del cliente. */
    private String cliente;
    /* La entrada del cliente. */
    private BufferedReader in;
    /* La salida hacia el cliente. */
    private BufferedWriter out;
    /* El identificador del hilo. */
    private int id;

    /**
     * Construye un nuevo hilo de ejecución.
     * @param sbdd el servidor de bases de datos.
     * @param bdd la base de datos.
     * @param enchufe el enchufe de la conexión.
     * @param id el identificador.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public HiloServidor(ServidorBaseDeDatos<T> sbdd,
                        BaseDeDatos<T> bdd,
                        Socket enchufe,
                        int id)
        throws IOException {
        this.sbdd = sbdd;
        this.bdd = bdd;
        this.id = id;
        this.enchufe = enchufe;
        in =
            new BufferedReader(
                new InputStreamReader(enchufe.getInputStream()));
        out =
            new BufferedWriter(
                new OutputStreamWriter(enchufe.getOutputStream()));
    }

    /**
     * Maneja la conexión con el cliente.
     */
    public void run() {
        cliente = enchufe.getInetAddress().getCanonicalHostName();
        log("Conexión recibida de " + clienteId());
        String linea = null;
        try {
            while ((linea = in.readLine()) != null) {
                Protocolo comando = Protocolo.obtenComando(linea);
                switch (comando) {
                case ENVIAR_BASE_DE_DATOS:
                    enviarBaseDeDatos();
                    break;
                case REGISTRO_AGREGADO:
                    registroAgregado();
                    break;
                case REGISTRO_ELIMINADO:
                    registroEliminado();
                    break;
                case REGISTRO_MODIFICADO:
                    registroModificado();
                    break;
                case COMANDO_INVALIDO:
                default:
                    log("Comando inválido de " + clienteId() +
                        ": " + comando);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            log("Hubo un error al comunicarnos con el cliente " +
                clienteId() + ".");
            log("Nos desconectaremos del cliente " + clienteId() + ".");
            cierraTodo();
        }
        log("Conexión de " + clienteId() + " terminada.");
        sbdd.clienteDesconectado(this);
    }

    /* Regresa una cadena con el identificador del cliente. */
    private String clienteId() {
        return id + " (" + cliente + ")";
    }

    /* Cierra la conexión con el cliente. */
    private void cierraTodo() {
        try {
            enchufe.close();
        } catch (Exception ioe) {
            /* No importa qué ocurra, terminamos el hilo de ejecución. */
        }
    }

    /* Manda un mensaje a la consola del servidor. */
    private void log(String mensaje) {
        System.err.println(mensaje);
    }

    /* Envía la base de datos al cliente. */
    private void enviarBaseDeDatos()
        throws IOException {
        bdd.guarda(out);
        out.newLine();
        out.flush();
        log("Base de datos enviada a " + clienteId());
    }

    /* Notifica a los demás hilos de un registro agregado o eliminado. */
    private void notificaHilos(Protocolo accion, T registro)
        throws IOException {
        notificaHilos(accion, registro, null);
    }

    /* Notifica a los demás hilos de un cambio en la base de datos. */
    private void notificaHilos(Protocolo accion, T registro, T modificado)
        throws IOException {
        for (HiloServidor<T> hilo : sbdd.getHilos()) {
            if (hilo == this)
                continue;
            String comando = Protocolo.generaComando(accion);
            hilo.out.write(comando);
            hilo.out.newLine();
            registro.guarda(hilo.out);
            if (modificado != null)
                modificado.guarda(hilo.out);
            hilo.out.flush();
        }
    }

    /* Agrega un registro recibido. */
    private void registroAgregado()
        throws IOException {
        log("Recibiendo registro de " + clienteId() + " para agregar...");
        T registro = bdd.creaRegistro();
        if (!registro.carga(in)) {
            log("Registro inválido recibido de " + clienteId() +
                " para agregar.");
            return;
        }
        log("Registro recibido de " + clienteId() + ".");
        synchronized(bdd) {
            bdd.agregaRegistro(registro);
            sbdd.guarda();
        }
        notificaHilos(Protocolo.REGISTRO_AGREGADO, registro);
        log("Registro agregado de " + clienteId() + ".");
    }

    /* Elimina un registro recibido. */
    private void registroEliminado()
        throws IOException {
        log("Recibiendo registro de " + clienteId() + " para eliminar...");
        T registro = bdd.creaRegistro();
        if (!registro.carga(in)) {
            log("Registro inválido recibido de " + clienteId() +
                " para eliminar.");
            return;
        }
        log("Registro recibido de " + clienteId() + ".");
        synchronized(bdd) {
            bdd.eliminaRegistro(registro);
            sbdd.guarda();
        }
        notificaHilos(Protocolo.REGISTRO_ELIMINADO, registro);
        log("Registro agregado de " + clienteId() + ".");
    }

    /* Modifica un registro recibido. */
    private void registroModificado()
        throws IOException {
        log("Recibiendo registro original de " + clienteId() +
            " para ser modificado...");
        T original = bdd.creaRegistro();
        if (!original.carga(in)) {
            log("Registro original inválido recibido de" + clienteId() +
                " para ser modificado.");
            return;
        }
        log("Registro original recibido de " + clienteId() + ".");
        log("Recibiendo registro modificado de " + clienteId() + "...");
        T modificado = bdd.creaRegistro();
        if (!modificado.carga(in)) {
            log("Registro modificado inválido recibido de " + clienteId() + ".");
            return;
        }
        log("Registro modificado recibido de " + clienteId() + ".");
        synchronized(bdd) {
            bdd.eliminaRegistro(original);
            bdd.agregaRegistro(modificado);
            sbdd.guarda();
        }
        notificaHilos(Protocolo.REGISTRO_MODIFICADO, original, modificado);
        log("Registro modificado de " + clienteId() + ".");
    }
}
