package icc;

import java.io.IOException;

/**
 * Clase para servidores de bases de datos de peliculas.
 */
public class ServidorBaseDeDatosPelicula
    extends ServidorBaseDeDatos<Pelicula> {

    /**
     * @param archivo el archivo con el cual poblar la base de datos. Puede ser
     *        <tt>null</tt>, en cuyo caso la base de datos será vacía.
     * @param puerto el puerto dónde escuchar por conexiones.  Construye un
     *        servidor de base de datos de peliculas.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosPelicula(String archivo, int puerto)
        throws IOException {
        super(archivo, puerto);
    }

    /**
     * Crea una base de datos de estudiantes.
     * @return una base de datos de estudiantes.
     */
    @Override public BaseDeDatos<Pelicula> creaBaseDeDatos() {
        return new BaseDeDatosPelicula();
    }
}
