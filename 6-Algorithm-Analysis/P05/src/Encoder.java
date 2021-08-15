import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Clase para codificar los vertices recibidos.
 * Les asigna un id entero para poder aplicar BFS/DFS sobre estos.
 * @author Jorge Martinez Flores
 */
public class Encoder{
    
    /* La lista con los vertices. */
    private ArrayList<Integer> newVertices;
    /* HashMap para relacionar un id con los vertices. */
    private HashMap<Integer, String> map;
    /* HashMap para relacionar el vertice con su id. */
    private HashMap<String, Integer> reverseMap;

    /* Constructor unico para la clase. */
    public Encoder(){
        newVertices = new ArrayList<>();
        map = new HashMap<>();
        reverseMap = new HashMap<>();
    }

    /* Metodo para obtener la lista de los nuevos vertices. */
    public ArrayList<Integer> getNewVertices(){
        return newVertices;
    }

    /* Metodo para obtener el HashMap. */
    public HashMap<Integer, String> getMap(){
        return map;
    }

    /**
     * Metodo que lee la lista de vertices y los codifica en los HashMap
     * ademas de agregarlos a la lista de vertices nueva.
     * @param vertices La lista de vertices original.
     */
    public void processVertex(ArrayList<String> vertices){
        for(int i = 0; i <  vertices.size(); i++){
            map.put(i, vertices.get(i));
            reverseMap.put(vertices.get(i), i);
            newVertices.add(i);
        }
    }

    /**
     * Lee la lista de aristas y las reescribe con base al id establecido
     * @param aristas La lista de aristas.
     * @return La nueva lista de aristas.
     */
    public ArrayList<String> processEdges(ArrayList<String> aristas){
        ArrayList<String> newEdges = new ArrayList<>();
        for(int i = 0; i < aristas.size(); i++){
            String[] arista = aristas.get(i).split("\\s*,\\s*");
            newEdges.add(reverseMap.get(arista[0]) + "," + reverseMap.get(arista[1]));
            //System.out.println(reverseMap.get(arista[0]) + "," + reverseMap.get(arista[1]));
        }
        return newEdges;
    }
}