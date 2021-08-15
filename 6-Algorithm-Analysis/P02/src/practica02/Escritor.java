package practica02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Escritor de archivos para el conjunto independiente maximal
 * @author Jorge Martinez Flores
 */
public class Escritor {
    
    /* ArrayList de vertices. */
    ArrayList<String> vertices;
    /* Nombre del archivo. */
    String fileName;
    /* BufferedWriter para escribir en el archivo. */
    BufferedWriter br;
    
    /* Constructor unico de la clase */
    public Escritor(){
        vertices = new ArrayList<>();
        fileName = "";
    }
    
    /**
     * Metodo para crear el archivo que contiene el conjunto 
     * independiente maximal.
     * @param fileName El nombre del archivo
     * @param vertices ArrayList con los vertices del conjunto independiente maximal
     */
    public void creaArchivo(String fileName, ArrayList<String> vertices){
        fileName = "Salida" + fileName;
        try{
            br = new BufferedWriter(new FileWriter(fileName));
            for(int i = 0; i < vertices.size(); i++){
                br.write(vertices.get(i) + ", ");
            }
        br.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }   
}
