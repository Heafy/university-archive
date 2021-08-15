import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import java.util.Iterator;
import java.util.Random;
import java.util.LinkedList;
import java.util.ArrayList;

public class NodoD extends Thread{

    private Graph grafica;
    private Node nodo;
    private boolean activo;
    public ArrayList<ArrayList> listaListas = new ArrayList<ArrayList>();
    public ArrayList<String> listaActual = new ArrayList<String>();

    public NodoD(Node d, Graph grafica){
		this.nodo = d;
		this.grafica = grafica;
		this.activo = true;
    }

    public Node getNode(){
		return nodo;
    }

    public Graph getGraph(){
		return grafica;
    }

    public void run(){
		Transporte t = Transporte.getInstance();
		while(activo){
		    NodoD destino = new NodoD(getNodeRandom(), grafica);
		    System.out.println("Soy el nodo: " + nodo.getId() + 
		    					" estoy comenzando a enviar un mensaje al nodo final: " + 
		    					destino.getNode().getId());
		    Mensaje nuevo = new Mensaje(this, destino, nodo.getId() + "-" + destino.getNode().getId());
		    Iterator<Node> iterator = nodo.getNeighborNodeIterator();
		    if(iterator.hasNext()){
				for(Node n = iterator.next();iterator.hasNext();n = iterator.next()){
				    t.enviar(nuevo, n.getId());
				    listaActual.add(n.getId());
				}
		    }

		    if(listaListas.contains(listaActual)){
		    	System.out.println("Camino concurrente:");
		    	System.out.println(listaActual.toString());
		    	try{
		    		Thread.sleep(2500);
		    	}catch(Exception e){}
		    }else{
		    	listaListas.add(listaActual);
		    }
		    listaActual = new ArrayList<String>();

		    Mensaje recibido = t.recibir(nodo.getId());
		    if(recibido != null){
				if(recibido.getDestino().equals(this)){
				    System.out.println("Soy el nodo: " + nodo.getId() + 
				    					" y recibi un mensaje de: " + 
				    					recibido.getOrigen().getNode().getId());
				}else if(recibido.getTTL()>0){
				    System.out.println("Soy el nodo " + nodo.getId() +
				    					"y estoy reenviando un mensaje con destino final: " + 
				    					recibido.getDestino().getNode().getId());
				    recibido.decrementaTTL();
				    iterator = nodo.getNeighborNodeIterator();
				    if(iterator.hasNext()){
						for(Node n = iterator.next();iterator.hasNext();n = iterator.next()){
						    t.enviar(recibido, n.getId());
						}
				    }
				}		   
		    }
		    try{
				sleep(100);
		    }catch(Exception ex){}
		}
    }

    public boolean equals(Object o){
		boolean status = false;
		if(o != null && o instanceof NodoD){
		    NodoD tmp = (NodoD)o;
		    status = tmp.nodo.getId().equals(this.nodo.getId());
		}
		return status;
    }

    private Node getNodeRandom(){
		Node node = null;
		LinkedList<Node> lista = new LinkedList<>();
		Iterator<Node> iterator = grafica.getNodeIterator();
		if(iterator.hasNext()){
		    for(Node n = iterator.next();iterator.hasNext();n = iterator.next()){
				lista.add(n);
		    }
		}
		//	System.out.println(lista);
		Random r = new Random();
		if(!lista.isEmpty()){
		    node = lista.get(r.nextInt(lista.size()));
		}
		//	System.out.println(node);
		return node;
    }
}
