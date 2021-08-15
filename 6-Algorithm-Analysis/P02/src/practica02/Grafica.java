package practica02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Clase para representar Graficas
 * @author Jorge Martinez Flores
 */
public class Grafica {
    
    /* La grafica de Graphstream. */
    Graph graph;
    /* ArrayList con el Conjunto Independiente Maximal*/
    ArrayList<String> setIndMaximal;
    
    /**
     * Constructor unico para la clase Grafica.
     * @param graphName El nombre de la grafica.
     */
    public Grafica(String graphName){
        graph = new MultiGraph(graphName);
        setIndMaximal = new ArrayList<>();
    }
    
    /**
     * Metodo para obtener el conjunto independiente maximal.
     * @return setIndMaximal el conjunto independiente maximal.
     */
    public ArrayList<String> getSetIndMaximal(){
        return setIndMaximal;
    }
    
    /**
     * Metodo para agregar todos los vertices a la grafica.
     * @param vertices La lista de los vertices a agregar.
     */
    public void addVertices(ArrayList<String> vertices){
        for(int i = 0; i < vertices.size(); i++)
            graph.addNode(vertices.get(i));
    }
    
    /**
     * Metodo para agregar todas las aristas a la grafica.
     * @param aristas La lista de las aristas a agregar.
     */
    public void addAristas(ArrayList<String> aristas){
        for(int i = 0; i < aristas.size(); i++){
            String[] arista = aristas.get(i).split("\\s*,\\s*");
            graph.addEdge(arista[0]+arista[1], arista[0], arista[1]);
        }        
    } 
    
    /**
     * Metodo para etiquetar los nodos de una grafica.
     */
    public void labelGrafica(){
        // Los etiqueta en base a su id, que es el nombre.
        for(Node node : graph)
            node.addAttribute("ui.label", node.getId());
    }
    
    /**
     * Metodo que pinta toda la grafica de color negro.
     * Ademas de que le asigna el valor de "false" a todos los nodos.
     * Nota: Asigna false como una cadena ya que no getAttribute no puede
     * asignar booleanos.
     */
    public void paintItBlack(){
        for(Node node : graph){
            node.addAttribute("ui.style", "fill-color: rgb(0,0,0);");
            node.setAttribute("max", "false");
        }
    }
    
    /**
     * Metodo que escoge un nodo aleatorio y lo propone como el primer
     * vertice (o nodo) del conjunto independiente maximal.
     * @param vertices La lista de vertices donde escoger el candidato.
     */
    public void verticeRandom(ArrayList<String> vertices){
        Random random = new Random();
        int randomPos = random.nextInt(vertices.size());
        String randomVertice = vertices.get(randomPos);
        Node randomNode = graph.getNode(randomVertice);
        randomNode.setAttribute("max", "true");
    }
    
    /**
     * Metodo que muestra la grafica con una UI.
     * Usa el metodo display de Graphstream.
     */ 
    public void displayGrafica(){
        graph.display();
    }
    
    /**
     * Metodo que llama todas las funciones auxiliares para crear la grafica.
     * Tambien colorea la grafica en base a su conjunto independiente maximal.
     * @param vertices La lista con los vertices.
     * @param aristas La lista con las aristas.
     */
    public void creaGrafica(ArrayList<String> vertices, ArrayList<String> aristas){
        addVertices(vertices);
        addAristas(aristas);
        labelGrafica();
        paintItBlack();
        verticeRandom(vertices);
        setIndependentMaximal();
    }
    
    /**
     * Algoritmo que dibuja el conjunto independiente maximal.
     */
    public void setIndependentMaximal(){
        String b;
        String revisar = "";
        /* Itera sobre todos los nodos de la grafica. */
       for(Node node : graph){
           // Itera sobre los vecinos del nodo.
           Iterator<? extends Node> it = node.getNeighborNodeIterator();
           while(it.hasNext()){
               Node next =it.next();
               b = next.getAttribute("max");
               /* Si un vecino es marcado con true, significa que el vertice
                * (o nodo) actual no es candidato para ser parte del conjunto
                * independiente maximal, ya que tiene un vecino que lo es.
                * Termina el ciclo y revisa con otro vertice (o nodo).
                */
               if(b.equals("true")){
                   revisar = "false";
                   break;
               } else {
                   // En otro caso es candidato
                   revisar = "true";
               }
           }
           /* Revisa el booleano auxiliar y si se mantuvo verdadero cuando 
            * termino de iterar sobre todos sus vecinos, es parte del conjunto
            * independiente maximal, en ese caso lo pinta de rojo y lo agrega
            * a la lista del conjunto independiente maximal.
            */
           if(revisar.equals("true")){
               node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
               setIndMaximal.add(node.getId());
           }  
       }
    }
}
