import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import java.util.Iterator;
import java.util.Random;

/*
 * Practica 1: Genera Graficas aleatorias con Graphstream
 * @author: Jorge Yael Martinez FLores
 *
 * Comandos para ejecución y compilacion:
 * javac -cp .:gs-algo-1.3.jar:gs-core-1.3.jar Practica01.java
 * java -cp .:gs-algo-1.3.jar:gs-core-1.3.jar Practica01
 */
public class Practica01{

    public static void main(String[] args) {
        /* Objeto de la clase Random de Java
         * Para hacer las graficas todavia mas aleatorias */
        Random random = new Random();
        /* Codigo para generar una grafica aleatoria */
        Graph graph = new SingleGraph("Grafica");
        /* Genera un grado promedio de un numero aleatorio entre el 1 y el 3 */
        Generator gen = new RandomGenerator(random.nextInt(5)+1);
        gen.addSink(graph);
        gen.begin();
        /* Genera por medio de un numero aleatorio entre 1 y 100 nodos */
        for(int i=0; i<random.nextInt(300)+1; i++)
            gen.nextEvents();
        gen.end();

        int r, g, b;
        String color;
        /* Itera primero sobre todos los nodos y los pinta de un color aleatorio*/
        for(Node n:graph){
            // Crea valores aleatorios para RGB y los construye en una cadena auxiliar
            r = random.nextInt(255)+1;
            g = random.nextInt(255)+1;
            b = random.nextInt(255)+1;
            color = "fill-color: rgb("+ r + "," + g + "," + b + ");";
            n.addAttribute("ui.style", color);
        }
        /* Vuelve a iterar sobre los nodos de la grafica, con ayuda del Iterador
         * de Java si encuentra al menos un vecino lo pinta de color azul*/
        for(Node n:graph){
            Iterator<? extends Node> it = n.getNeighborNodeIterator();
            while(it.hasNext()){
                n.addAttribute("ui.style", "fill-color:rgb(13, 101, 217);");
                it.next();
            }
        }
        /* Muestra la grafica */
        graph.display();
    }
}
