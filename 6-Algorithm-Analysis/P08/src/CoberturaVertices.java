// TODO IMPLEMENTAR CLASE PARA LLAMAR METODO
// IMPLEMENTAR LECTOR
// IMPLEMENTAR ENCODER P05
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
 
// This class represents an undirected graph using adjacency list
public class CoberturaVertices{

    // Numero de vertices
    private int V;
    // Lista para la lista de adyacencias
    private LinkedList<Integer> adyacencias[];
 
    /**
     * Constructor unico,
     * @param v El numero de vertices de la grafica
     */
    @SuppressWarnings("unchecked")
    public CoberturaVertices(int v){
        V = v;
        adyacencias = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adyacencias[i] = new LinkedList();
    }
 
    /**
     * Metodo para añadir una arista a la grafica
     * @param v Vertice origen
     * @param w Vertice destino
     */
    public void addArista(int v, int w){
        adyacencias[v].add(w);
        adyacencias[w].add(v);
    }
 
    /**
     * Metodo que llama a la cobertura de vertices
     */
    public ArrayList<Integer> coberturaVertices(){
        ArrayList<Integer> cover = new ArrayList<>();
        // Inicializa los vertices como no visitados
        boolean visited[] = new boolean[V];
        for (int i=0; i<V; i++)
            visited[i] = false;
 
        Iterator<Integer> iterator;
 
        for (int u=0; u<V; u++){
            if (visited[u] == false){
                iterator = adyacencias[u].iterator();
                while (iterator.hasNext())
                {
                    int v = iterator.next();
                    if (visited[v] == false){
                         // Agrega los vertices u,v al resultado.
                         // y los compara.
                         visited[v] = true;
                         visited[u]  = true;
                         break;
                    }
                }
            }
        }
 
        // Imprime la cobertura delos vertices
        for (int j=0; j<V; j++){
            if (visited[j]){
                cover.add(j);
                //System.out.print(j+", ");
            }
        }
        return cover;
    }
 
    // Metodo main
    public static void main(String args[]){

        /* La lista con los vertices. */
        ArrayList<Integer> newVertices;
        /* La lista con las aristas. */
        ArrayList<String> newAristas;
        /* HashMap para relacionar un id con los vertices. */
        HashMap<Integer, String> map;
        /* HashMap para relacionar el vertice con su id. */
        HashMap<String, Integer> reverseMap;

        Encoder encoder = new Encoder();

        // Crea el lector y obtiene sus vertices y aristas del archivo
        Lector lector = new Lector();
        lector.leerArchivo(args[0]);
        ArrayList<String> vertices = lector.getVertices();
        ArrayList<String> aristas = lector.getAristas();
        encoder.processVertex(vertices);
        encoder.processEdges(aristas);
        
        /* Guardamos la nueva lista de vertices y aristas.*/
        newVertices = encoder.getNewVertices();
        newAristas = encoder.getnewAristas();
        /* Guardamos el HashMap. */
        map = encoder.getMap();

        CoberturaVertices graph = new CoberturaVertices(vertices.size()+1);

        /* Agrega las aristas convertidas a indices. */
        for(int i = 0; i < newAristas.size(); i++){
            String[] arista = aristas.get(i).split("\\s*,\\s*");
            graph.addArista(Integer.parseInt(arista[0]), Integer.parseInt(arista[1]));
        }

        System.out.println("Cobertura de vertices:");

        /* Obtiene las aristas originales y las imprime. */
        ArrayList<Integer> listCover = graph.coberturaVertices();
        for (int i = 0; i < listCover.size(); i++){
            System.out.print(map.get(listCover.get(i)) + ", ");
            }
        System.out.println("");

    }
}