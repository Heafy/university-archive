import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import java.util.LinkedList;
    
public class Main{

    public static void main(String[] pps){
		Graph grafica = new SingleGraph("Practica 4");
		int num_nodos = 9;

		LinkedList<NodoD> listaD = new LinkedList<>();
		
		for(int i = 0; i < num_nodos; i++){
			Node node = grafica.addNode(String.valueOf(i));
			NodoD nodoD = new NodoD(node, grafica);
			listaD.add(nodoD);
		}

		grafica.addEdge("01", String.valueOf(0), String.valueOf(1));
		grafica.addEdge("02", String.valueOf(0), String.valueOf(2));
		grafica.addEdge("05", String.valueOf(0), String.valueOf(5));
		grafica.addEdge("13", String.valueOf(1), String.valueOf(3));
		grafica.addEdge("14", String.valueOf(1), String.valueOf(4));
		grafica.addEdge("23", String.valueOf(2), String.valueOf(3));
		grafica.addEdge("24", String.valueOf(2), String.valueOf(4));
		grafica.addEdge("35", String.valueOf(3), String.valueOf(5));
		grafica.addEdge("45", String.valueOf(4), String.valueOf(5));
		grafica.addEdge("52", String.valueOf(5), String.valueOf(2));
		grafica.addEdge("61", String.valueOf(6), String.valueOf(1));
		grafica.addEdge("64", String.valueOf(6), String.valueOf(4));
		grafica.addEdge("71", String.valueOf(7), String.valueOf(1));
		grafica.addEdge("70", String.valueOf(7), String.valueOf(0));
		grafica.addEdge("83", String.valueOf(8), String.valueOf(3));
		grafica.addEdge("86", String.valueOf(8), String.valueOf(6));
		
		grafica.display();
		
		for(NodoD n:listaD){
		    System.out.println("iniciando: " + n.getNode().getId());
		    n.start();
		}
    }
}
