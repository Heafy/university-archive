import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Practica 2
 *
 * @author Flores González Luis Brandon
 */
public class Main {

    public static void main(String[] args) {
        //Gráfica aleatoria de la practica anterior.
        Grafica aleatoria = new Grafica();
        Graph random = aleatoria.crearGraficaAleatoria();
        LinkedList<NodeProcess> nodos = new LinkedList<>();
        Set<Integer> vecinos;
        Iterator<? extends Node> iterador;
        Iterator<NodeProcess> inicio;
        Node punto;

        for (int i = 0; i < aleatoria.getOrden(); i++) {
            Node vertice = random.getNode(i);
            vecinos = new HashSet<>();
            iterador = vertice.getNeighborNodeIterator();

            while (iterador.hasNext()) {
                punto = iterador.next();
                vecinos.add(new Integer(punto.toString()));
            }
            nodos.add(i, new NodeProcess(i, vecinos, vecinos));
        }

        inicio = nodos.iterator();
        for (int i = 0; i < aleatoria.getOrden(); i++) {
            while (inicio.hasNext()) {
                inicio.next().start();
            }
        }
        random.display();
    }

}
