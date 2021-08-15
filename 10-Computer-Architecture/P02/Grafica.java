import java.util.Iterator;
import java.util.Random;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Genera un grafica aleatoria con RandomGenerator. Se colorea de diferentes
 * colores todos los nodos que no tengas conexión con ningun otro nodo. Se
 * colorean del mismo color todos los nodos que tengan al menos algún vecino.
 *
 * @author Flores Gonzalez Luis Brandon.
 * @version 1.0
 */
public class Grafica {


    private final int orden;

    /**
     * Se crea un grafica con orden 200 por omision.
     */
    public Grafica(){
        this.orden = 200;
    }

    public int getOrden(){
        return this.orden;
    }

    /**
     * Se crea una gráfica aleatoria.
     *
     * @return Grafica aleatoria.
     */
    public Graph crearGraficaAleatoria() {
        //Se crea una gráfica aleatoria.
        Graph grafica = new SingleGraph("Aleatoria");
        Generator generador = new RandomGenerator(2);
        generador.addSink(grafica);
        generador.begin();
        for (int i = 0; i < this.orden; i++) {
            generador.nextEvents();
        }
        generador.end();
        return grafica;
    }

    /**
     * Se agisnan los colores a los nodos y el tamaño.
     *
     * @param graph - Grafica a modificar.
     */
    private void setCSS(Graph graph) {
        String css = "node{size: 15px;}"
                + "node.tieneVecinos {fill-color: brown;} " + "\n"
                + "node.BurlyWood {fill-color: BurlyWood;} " + "\n"
                + "node.DarkCyan {fill-color: DarkCyan;} " + "\n"
                + "node.RoyalBlue {fill-color: RoyalBlue;} ";
        graph.addAttribute("ui.stylesheet", css);
    }

    /**
     * Se muestra una gráfica aleatoria tal que cumple con los requisitos
     * especificados en la descripción de la clase.
     */
    public void mostrarGrafica() {

        Graph grafica = crearGraficaAleatoria();

        //Recorre la gráfica aleatoria.
        Iterator<? extends Node> nodos = grafica.getNodeIterator();
        Node nodo;
        while (nodos.hasNext()) {
            nodo = nodos.next();
            if (nodo.getDegree() > 0) {
                nodo.addAttribute("ui.class", "tieneVecinos");
            } else {
                Random aleatorio = new Random();
                int n = aleatorio.nextInt(3);
                switch (n) {
                    case 0:
                        nodo.addAttribute("ui.class", "BurlyWood");
                        break;
                    case 1:
                        nodo.addAttribute("ui.class", "DarkCyan");
                        break;
                    case 2:
                        nodo.addAttribute("ui.class", "RoyalBlue");
                        break;
                    default:
                        break;
                }
            }
        }

        //Asignamos las propiedades a los nodos(color y tamaño).
        setCSS(grafica);

        //Muestra la gráfica en pantalla.
        grafica.display();
    }

}
