package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javafx.application.Platform;

/**
 * Clase para hilos de ejecución que manejan conexiones de servidores.
 */
public class HiloCliente<T extends Registro> extends Thread {

    /* La base de datos. */
    private BaseDeDatos<T> bdd;
    /* El enchufe. */
    private Socket enchufe;
    /* La entrada del servidor. */
    private BufferedReader in;
    /* La salida hacia el servidor. */
    private BufferedWriter out;
    /* Bandera de sincronización. */
    private boolean espera;
    /* Bandera de terminación. */
    private boolean terminado;
    /* Bandera para ignorar peticiones de aviso.  */
    private boolean ignorar;

    /**
     * Crea un nuevo hilo de ejecución para manejar la conexión de
     * un cliente.
     * @param bdd la base de datos.
     * @param enchufe el enchufe conectado al servidor.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public HiloCliente(BaseDeDatos<T> bdd, Socket enchufe)
        throws IOException {
        this.bdd = bdd;
        this.enchufe = enchufe;
        in =
            new BufferedReader(
                new InputStreamReader(enchufe.getInputStream()));
        out =
            new BufferedWriter(
                new OutputStreamWriter(enchufe.getOutputStream()));
    }

    /* Carga la base de datos de la conexión. */
    private void cargaBaseDeDatos() {
        try {
            ignorar = true;
            bdd.carga(in);
            ignorar = false;
        } catch (IOException ioe) {
            log("Hubo un error al comunicarnos con el servidor.");
            log("Nos desconectaremos.");
            cierraTodo();
        }
        espera = false;
    }

    /**
     * Método principal del hilo de ejecución.
     */
    public void run() {
        try {
            out.write(Protocolo.generaComando(Protocolo.ENVIAR_BASE_DE_DATOS));
            out.newLine();
            out.flush();
            espera = true;
            Platform.runLater(() -> cargaBaseDeDatos());
            while (espera) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {}
            }
            String linea = null;
            while ((linea = in.readLine()) != null) {
                Protocolo comando = Protocolo.obtenComando(linea);
                switch (comando) {
                case ENVIAR_BASE_DE_DATOS:
                    log("Error: clientes no pueden enviar la base de datos.");
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
                    log("Comando inválido: " + linea);
                }
            }
        } catch (IOException ioe) {
            if (!terminado) {
                log("Hubo un error al comunicarnos con el servidor.");
                log("Nos desconectaremos.");
                cierraTodo();
            }
        }
        Platform.runLater(() -> bdd.limpia());
    }

    /**
     * Cierra la conexión con el servidor.
     */
    public void cierraTodo() {
        terminado = true;
        try {
            enchufe.close();
        } catch (Exception ioe) {
            /* No importa qué ocurra, terminamos el hilo de ejecución. */
        }
    }

    /* Manda un mensaje a la consola del cliente. */
    private void log(String mensaje) {
        System.err.println(mensaje);
    }

    /* Agrega un registro recibido. */
    private void registroAgregado()
        throws IOException {
        final T registro = bdd.creaRegistro();
        if (!registro.carga(in)) {
            log("Registro inválido recibido para agregar.");
            return;
        }
        Platform.runLater(() -> bdd.agregaRegistro(registro));
    }

    /* Elimina un registro recibido. */
    private void registroEliminado()
        throws IOException {
        final T registro = bdd.creaRegistro();
        if (!registro.carga(in)) {
            log("Registro inválido recibido para eliminar.");
            return;
        }
        Platform.runLater(() -> bdd.eliminaRegistro(registro));
    }

    /* Modifica un registro recibido. */
    private void registroModificado()
        throws IOException {
        final T original = bdd.creaRegistro();
        if (!original.carga(in)) {
            log("Registro original inválido recibido.");
            return;
        }
        final T modificado = bdd.creaRegistro();
        if (!modificado.carga(in)) {
            log("Registro modificado inválido recibido.");
            return;
        }
        Platform.runLater(() -> {
                bdd.eliminaRegistro(original);
                bdd.agregaRegistro(modificado);
            });
    }

    /**
     * Avisa un cambio al servidor.
     * @param accion el cambio realizado.
     * @param registro el registro afectado.
     * @return <code>true</code> si el cambio se puede avisar sin problemas,
     *         <code>false</code> en otro caso.
     */
    public boolean avisaCambio(Protocolo accion, T registro) {
        return avisaCambio(accion, registro, null);
    }

    /**
     * Avisa un cambio al servidor.
     * @param accion el cambio realizado.
     * @param registro el registro afectado.
     * @param modificado el registro modificado.
     * @return <code>true</code> si el cambio se puede avisar sin problemas,
     *         <code>false</code> en otro caso.
     */
    public boolean avisaCambio(Protocolo accion, T registro, T modificado) {
        if (ignorar)
            return true;
        try {
            String comando = Protocolo.generaComando(accion);
            out.write(comando);
            out.newLine();
            registro.guarda(out);
            if (modificado != null)
                modificado.guarda(out);
            out.flush();
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }
}
