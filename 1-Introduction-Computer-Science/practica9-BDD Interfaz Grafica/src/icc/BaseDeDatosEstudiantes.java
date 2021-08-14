package icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosEstudiantes extends BaseDeDatos<Estudiante> {

    /**
     * Constructor único.
     */
    public BaseDeDatosEstudiantes() {
        super();
    }

    /**
     * Crea un estudiante sin información relevante.
     * @return un estudiante sin información relevante.
     */
    @Override protected Estudiante creaRegistro() {
        return new Estudiante(null, 0, 0, 0);
    }

    /**
     * Busca estudiantes por un campo específico.
     * Tiende a funcionar cuando quiere.
     * @param campo el campo del registro por el cuál buscar; puede
     *              ser
     *              <ul>
     *               <li><tt>"nombre"</tt></li>
     *               <li><tt>"cuenta"</tt></li>
     *               <li><tt>"promedio"</tt></li>
     *               <li><tt>"edad"</tt></li>
     *              </ul>
     * @param texto el texto a buscar.
     * @return una lista con los estudiantes tales que en el campo
     *         especificado contienen el texto recibido.
     * @throws IllegalArgumentException si el campo no es ninguno de
     *         los especificados arriba.
     */
    @Override public Lista<Estudiante> buscaRegistros(String campo, String texto) {
        Lista<Estudiante> l = new Lista<Estudiante>();
        IteradorLista<Estudiante> it = registros.iteradorLista();
        for(Estudiante t: registros){
            Estudiante e = it.next();
            String valor = "";
            switch(campo){
                case "nombre":
                    valor = e.getNombre();
                    break;
                case "cuenta":
                    valor = String.valueOf(e.getCuenta());
                    break;
                case "promedio":
                    valor = String.format("%2.2f", e.getPromedio());
                    break;
                case "edad":
                    valor = String.valueOf(e.getEdad());
                    break;
                default:
                    throw new IllegalArgumentException("Campo no valido");
            }//Switch
            if(valor.indexOf(texto)!= -1)
                .agregaFinal(e);
        }//for,each
        return l;
    }
}
