import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Lector de graficas desde archivos de texto
 * Modificado a partir de la clase Lector de la practica 02
 * @author Jorge Martinez Flores
 */
public class Lector{
  
    // Atributos de Lector
    ArrayList<String> vertices;
    ArrayList<String> aristas;

    /**
     * Constructor del lector
     * Recibe el nombre de un archivo de texto y almacena el arreglo de vertices y aristas en el objeto.
     * @param filename Nombre del archivo de texto a procesar
     */
    public Lector(){
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }
    
    /**
     * Metodo para obtener los vertices del lector.
     * @return Los vertices del lector.
     */
    public ArrayList<String> getVertices(){
        return vertices;
    }
    
    /**
     * Metodo para obtener las aristas del lector.
     * @return Las aristas del lector.
     */
    public ArrayList<String> getAristas(){
        return aristas;
    }
    
    /**
     * Metodo que dado un nombre de archivo almacena en el objeto los vertices y las aristas del archivo de texto.
     * @param filename Nombre del archivo de texto a procesar.
     */
    public void leerArchivo(String filename){
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            for(int i = -1; (line = br.readLine()) != null; i++){
                // Lee vertices
                if(i == -1){
                    String[] lineaVertices = line.trim().split("\\s*,\\s*");
                    vertices.addAll(Arrays.asList(lineaVertices));
                // Lee aristas
                }else{
                    String lineaAristas = line.trim();
                    aristas.add(lineaAristas);
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
