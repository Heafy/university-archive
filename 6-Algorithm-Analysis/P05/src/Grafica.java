import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase para modelar una grafica.
 * Modificada a partir de la clase Grafica de la práctica 2.
 * @author Jorge Martinez Flores
 */
public class Grafica{
	
	/* Numero de vertices de la grafica. */
	private int numV;
	/* Lista de vertices. */
	private ArrayList<Integer> vertices;
	/* Lista de adyacencias. */
	private ArrayList<Integer> adyacencias[];

	/* Constructor de la clase. 
	 * Una gráfica es una lista de vertices
	 * Y su lista de adyacencias.
	 */
	@SuppressWarnings("unchecked")
	public Grafica(int numV, ArrayList<Integer> vertices){
		this.numV = numV;
		this.vertices = vertices;
		this.adyacencias = new ArrayList[numV];
		for (int i = 0; i < numV; i++){
			adyacencias[i] = new ArrayList();
		}
	}

	/**
	 * Metodo para agregar una arista a la gráfica.
	 * @param a Nodo a de la grafica.
	 * @param b Nodo b de la grafica.
	 */
	public void addArista(int a, int b){
		adyacencias[a].add(b);
	}

	/**
     * Metodo para agregar todos las vertices a la grafica.
     * @param aristas La lista de las aristas a agregar.
     */
    public void setVertices(ArrayList<Integer> vertices){
    	this.vertices = vertices;       
    } 

	/**
     * Metodo para agregar todas las aristas a la grafica.
     * @param aristas La lista de las aristas a agregar.
     */
	public void setAristas(ArrayList<String> aristas){
   		for(int i = 0; i < aristas.size(); i++){
			String[] arista = aristas.get(i).split("\\s*,\\s*");
            addArista(Integer.parseInt(arista[0]), Integer.parseInt(arista[1]));
		}       
    }

    /**
     * Metodo para imprimir los vertices de la grafica
     */
    public void printVertices(){
    	System.out.print("Vertices: ");
    	for(int i = 0; i < vertices.size(); i++){
    		System.out.print(String.format("%d, ", vertices.get(i)));
    	}
    	System.out.println("");
    }

	/**
	 * Algoritmo BFS para graficas
	 * @param x el vertice sobre el que aplicar BFS.
	 */
	public ArrayList<Integer> BFS(int x){
        ArrayList<Integer>  bfsVertex = new ArrayList<>();
		boolean visitados[] = new boolean[numV];
		LinkedList<Integer> cola = new LinkedList<Integer>();
		visitados[x] = true;
		cola.add(x);
		while(cola.size() != 0){
			x = cola.poll();
			//System.out.print(x + " ");
            bfsVertex.add(x);
			Iterator<Integer> iterador = adyacencias[x].listIterator();
			while(iterador.hasNext()){
				int n = iterador.next();
				if(!visitados[n]){
					visitados[n] = true;
					cola.add(n);
				}
			}
		}
        return bfsVertex;
	}

	/**
	 * Algoritmo BFS para graficas
	 * @param x el vertice sobre el que aplicar BFS.
	 */
	public ArrayList<Integer> DFS(int x){
		boolean visitados[] = new boolean[numV];
        ArrayList<Integer> dfsVertex = new ArrayList<>();
		return DFSaux(x, visitados, dfsVertex);
	}

	/**
	 * Metodo auxiliar para el algoritmo DFS
	 * @param int x el vertice sobre el que aplicar DFS
	 * @param visitados la lista de vertices visitados
	 */
	public ArrayList<Integer> DFSaux(int x, boolean visitados[], ArrayList<Integer> dfsVertex){
		visitados[x] = true;
		//System.out.print(x + " ");
        dfsVertex.add(x);
		Iterator<Integer> iterador = adyacencias[x].listIterator();
		while(iterador.hasNext()){
			int n = iterador.next();
			if(!visitados[n]){
				DFSaux(n, visitados, dfsVertex);
			}
		}
        return dfsVertex;
	}

}