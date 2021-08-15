package practica02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Lector de graficas desde archivos de texto
 * Basado en el lector enviado para la práctica 2
 * @author Kike
 * @author Jorge Martinez Flores
 */
public class Lector{
  
    // Atributos de Lector
    ArrayList<String> vertices;
    ArrayList<String> aristas;
    
    // Scanner para leer la entrada del archivo
    Scanner scanner;

    /**
     * Constructor del lector
     * Recibe el nombre de un archivo de texto y almacena el arreglo de vertices y aristas en el objeto.
     * @param filename Nombre del archivo de texto a procesar
     */
    public Lector(){
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
        scanner = new Scanner(System.in);
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
     * Metodo para obtener el nombre del archivo.
     * @return Una cadena con el nombre del archivo.
     */
    public String setFileName(){
        System.out.println("Introduce el nombre completo de tu archivo: ");
        String fileName = scanner.nextLine();
        return fileName;
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
                if(i == -1){
                    String[] lineaVertices = line.trim().split("\\s*,\\s*");
                    vertices.addAll(Arrays.asList(lineaVertices));
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
