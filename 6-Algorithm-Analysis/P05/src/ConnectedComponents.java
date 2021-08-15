import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Clase para mostrar las componentes conexas de la grafica.
 * @author Jorge Martinez Flores.
 */
public class ConnectedComponents{

    /* La lista con los vertices. */
    private ArrayList<Integer> newVertices;
    /* HashMap para relacionar un id con los vertices. */
    private HashMap<Integer, String> map;
    /* HashMap para relacionar el vertice con su id. */
    private HashMap<String, Integer> reverseMap;

	/**
     * Metodo para mostrar el uso correcto del programa
     */
    private static void uso(){
        System.out.println("Uso: java ConnectedComponents file DFS/BFS");
        System.exit(1);
    }

     /**
     * Metodo para imprimir los vertices de la grafica
     */
    private static void printVertices(ArrayList<String> vertices){
        System.out.print("Vertices: ");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print(String.format("%s, ", vertices.get(i)));
        }
        System.out.println("");
    }
 
	public static void main(String[] args) {
		// Revisa que los parametros pasados sean correctos
		if(args.length != 2)
            uso();

        Encoder encoder = new Encoder();

		try{
			// Crea el lector y obtiene sus vertices y aristas del archivo
			Lector lector = new Lector();
            lector.leerArchivo(args[0]);
            ArrayList<String> vertices = lector.getVertices();
            ArrayList<String> aristas = lector.getAristas();
            encoder.processVertex(vertices);
            ArrayList<String> aristasAux = encoder.processEdges(aristas);
            
            /* Guardamos la nueva lista de vertices.*/
            ArrayList<Integer> newVertices = encoder.getNewVertices();

            // Crea la grafica y sus aristas
            Grafica grafica = new Grafica(vertices.size(), newVertices);
            grafica.setAristas(aristasAux);

            /* Guardamos el HashMap. */
            HashMap<Integer, String> map = encoder.getMap();
            
            // Muestra las componentes conexas en base a la opcion elegida
            String opcion = args[1].toUpperCase();
            printVertices(vertices);
            switch(opcion){
            	case "BFS":
            		System.out.println("Componentes conexas usando BFS");
                    for(int v : newVertices){
                        ArrayList<Integer> bfs = grafica.BFS(v);
                		for (int i = 0; i < bfs.size(); i++){
                            System.out.print(map.get(bfs.get(i)) + " ");
                        }
                        System.out.println("");
                    }
            		break;
            	case "DFS":
            		System.out.println("Componentes conexas usando DFS");
            		for(int v : newVertices){
                        ArrayList<Integer> dfs = grafica.DFS(v);
                        for(int i = 0; i < dfs.size(); i++){
                            System.out.print(map.get(dfs.get(i)) + " ");
                        }
                        System.out.println("");
                    }
            		break;
            	default:
            		System.out.println("Opcion incorrecta, intenta con BFS o DFS");
            		break;
            }
            
		}catch(Exception e){
			System.err.println(e);
		}
	}
}