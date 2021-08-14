package icc;

/**
 * Clase para bases de datos de peliculas.
 */
public class BaseDeDatosPelicula extends BaseDeDatos<Pelicula> {

    /**
     * Constructor único.
     */
    public BaseDeDatosPelicula() {
        super();
    }

    /**
     * Crea una pelicula sin información relevante.
     * @return una pelicula sin información relevante.
     */
    @Override protected Pelicula creaRegistro() {
        return new Pelicula(null, null, 0, null, 0);
    }

    /**
     * Busca peliculas por un campo específico.
     * @param campo el campo del registro por el cuál buscar; puede
     *              ser
     *              <ul>
     *               <li><tt>"titulo"</tt></li>
     *               <li><tt>"director"</tt></li>
     *               <li><tt>"año"</tt></li>
     *               <li><tt>"genero"</tt></li>
     *               <li><tt>"duracion"</tt></li>
     *              </ul>
     * @param texto el texto a buscar.
     * @return una lista con las peliculas tales que en el campo
     *         especificado contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es ninguno de
     *         los especificados arriba.
     */
    @Override public Lista<Pelicula> buscaRegistros(String campo, 
						    String texto){
        Lista<Pelicula> l = new Lista<Pelicula>();
        IteradorLista<Pelicula> it = registros.iteradorLista();
        for(Pelicula t: registros){
            Pelicula e = it.next();
            String valor = "";
            switch(campo){
                case "titulo":
                    valor = e.getTitulo();
                    break;
                case "director":
                    valor = e.getDirector();
                    break;
                case "year":
                    valor = String.valueOf(e.getYear());
                    break;
                case "genero":
                    valor = e.getGenero();
                    break;
                case "duracion":
                    valor = String.valueOf(e.getDuracion());
                    break;
                default:
                    throw new IllegalArgumentException("Campo no valido");
            }//Switch
            if(valor.indexOf(texto)!= -1)
                l.agregaFinal(e);
        }//for,each
        return l;
    }
}
