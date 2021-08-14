package icc;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase genérica abstracta para bases de datos. Provee métodos para
 * agregar y eliminar registros, y para guardarse y cargarse de una
 * entrada y salida dados.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el
 * método {@link #creaRegistro}, que crea un registro sin
 * información relevante. También deben implementar el método {@link
 * #buscaRegistros} para hacer consultas en la base de datos.
 */
public abstract class BaseDeDatos<T extends Registro> {

    /** Lista de registros en la base de datos. */
    protected Lista<T> registros;
    /** Lista de escuchas de la base de datos. */
    protected Lista<EscuchaBaseDeDatos<T>> escuchas;

    /** Especifica que la base de datos fue limpiada. */
    public static final int BASE_LIMPIADA        = 0;
    /** Especifica que un registro fue agregado. */
    public static final int REGISTRO_AGREGADO    = 1;
    /** Especifica que un registro fue eliminado. */
    public static final int REGISTRO_ELIMINADO   = 2;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
    	registros = new Lista<T>();
    	escuchas = new Lista<EscuchaBaseDeDatos<T>>();
    }

    /**
     * Regresa el número de registros en la base de datos.
     * @return el número de registros en la base de datos.
     */
    public int getNumRegistros() {
    	int i = 0;
    	for(T t : registros)
    	    i++;
    	return i;
    }

    /**
     * Regresa una lista con los registros en la base de
     * datos. Modificar esta lista no cambia a la información en la
     * base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public Lista<T> getRegistros() {
        return registros.copia();
    }

    /**
     * Agrega el registro recibido a la base de datos.
     * @param registro el registro que hay que agregar a la base de
     *        datos.
     */
    public void agregaRegistro(T registro) {
        registros.agregaFinal(registro);
        for (EscuchaBaseDeDatos<T> escucha : escuchas)
            escucha.baseDeDatosModificada(REGISTRO_AGREGADO, registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base
     *        de datos.
     */
    public void eliminaRegistro(T registro) {
	   registros.elimina(registro);
	   for (EscuchaBaseDeDatos<T> escucha : escuchas)
            escucha.baseDeDatosModificada(REGISTRO_ELIMINADO, registro);
    }

    /**
     * Guarda todos los registros en la base de datos en la salida
     * recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
	IteradorLista<T> it = registros.iteradorLista();
    	for(T t: registros){
    	    T r =it.next();
    	    r.guarda(out);
    	}
    }

    /**
     * Guarda los registros de la entrada recibida en la base de
     * datos. Si antes de llamar el método había registros en la
     * base de datos, estos son eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
    	limpia();
    	boolean v;
    	do{
    	    T r = creaRegistro();
    	    v = r.carga(in);
    	    if(v)
                agregaRegistro(r);
    	}while(v);
    }

    /**
     * Limpia la base de datos.
     */
    public void limpia() {
    	registros.limpia();
      	for (EscuchaBaseDeDatos<T> escucha : escuchas)
    	    escucha.baseDeDatosModificada(BASE_LIMPIADA, null);
    }

    /**
     * Crea un registro sin información relevante.
     * @return un registro sin información relevante.
     */
    protected abstract T creaRegistro();

    /**
     * Busca registros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param texto el texto a buscar.
     * @return una lista con los registros tales que en el campo
     *         especificado contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es válido.
     */
    public abstract Lista<T> buscaRegistros(String campo, String texto);

    /**
     * Agrega un escucha a la base de datos.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaBaseDeDatos<T> escucha) {
        escuchas.agregaFinal(escucha);
    }
}
