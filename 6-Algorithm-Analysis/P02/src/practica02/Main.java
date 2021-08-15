package practica02;

import java.util.ArrayList;

/**
 * Clase Main
 * @author Jorge Martinez Flores
 */
public class Main{
    
    
    /**
     * Metodo principal.
     * Ejecuta el lector, crea la grafica y escribe el conjunto maximal.
     * @param args
     */
    public static void main(String[] args){
        try{
            Lector lector = new Lector();
            String fileName = lector.setFileName();
            lector.leerArchivo(fileName);
            ArrayList<String> v = lector.getVertices();
            ArrayList<String> a = lector.getAristas();

            Grafica grafica = new Grafica(fileName);
            grafica.creaGrafica(v, a);
            grafica.displayGrafica();

            ArrayList<String> maximal = grafica.getSetIndMaximal();
            Escritor escritor = new Escritor();
            escritor.creaArchivo(fileName, maximal);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
