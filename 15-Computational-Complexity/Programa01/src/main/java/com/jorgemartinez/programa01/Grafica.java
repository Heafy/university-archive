package com.jorgemartinez.programa01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

/**
 * Clase para modelar una gráfica.
 * @author Jorge Martinez
 */
public class Grafica {

    /* La gráfica de Graphstream. */
    Graph graph;
    Random random = new Random();
    /* El número de nodos */
    int nodes;
    /* La lista de nombres de los posibles nodos. */
    ArrayList<String> nodeList = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
        add("D");
        add("E");
        add("F");
        add("G");
        add("H");
        add("I");
        add("J");
    }};
    /* Lista de nodos asignados a la gráfica. */
    ArrayList<String> subNodeList;
    /* Lista con los nodos candidatos a ser solución */
    ArrayList<String> independentSet;

    /** 
     * Constructor unico de una gráfica aleatoria
     */
    public Grafica() {
        graph = new MultiGraph("Random");
        nodes = random.nextInt(5+1) + 5; // (5-10)
        subNodeList =  new ArrayList<String>(nodeList.subList(0, nodes));
        independentSet = new ArrayList<String>();
        // Crea los nodos y les asigna un valor de la lista
        for(int i = 0; i < nodes; i++) {
            String nodeName = nodeList.get(i);
            graph.addNode(nodeName).addAttribute("ui.label", nodeName);
        }
        // Itera sobre los nodos creados
        for(Node node : graph) {
            // Marca todos los vertices
            node.setAttribute("independent", false);
            for(String str : subNodeList) {
                // Decide con probabilidad 0.25 que vértices crear
                if(quarterProbability()){
                    graph.addEdge(node.getId()+str, node.getId(), str);
                }
            }
        }
    }

    /**
     * Método para obtener el número de nodos de la gráfica.
     * @return el número de nodos.
     */
    public int getNodes() {
        return nodes;
    }

    /**
     * Método para obtener el candidato a ser solución del problema.
     * @return La lista de índices del conjunto independiente generado.
     */
    public ArrayList<String> getIndepedentSet() {
        return independentSet;
    }

    /**
     * Método para decidir si se agrega o no un vértice aleatorio
     * Probabilidad = 0.25
     * @return true Si el número aleatorio está entre 0 y 0.25
     * false en otro caso.
     */
    public boolean quarterProbability() {
        double randomProb = random.nextFloat();
        // 0 <= randomProb <= 0.25
        return 0 <= randomProb && randomProb <= 0.25;
    }

    /**
     * Método auxiliar para la fase adivinadora.
     * Decide si un vértice es candidato a ser parte del conjunto solución.
     * Simula el lanzamiento de una moneda.
     * Probabilidad = 0.5
     * @return true si el número aleatorio está entre 0 y 0.5
     * false en otro caso.
     */
    public boolean halfProbability() {
        double randomProb = random.nextFloat();
        // 0 <= randomProb <= 0.5
        return 0 <= randomProb && randomProb <= 0.50;
    }

    /**
     * Fase adivinadora del algoritmo.
     * Itera sobre todos los vértices y los selecciona con probabilidad 0.50
     * para ser candidatos del conjunto solución, los pinta de color rojo
     * para poder identificarlos.
     */
    public void faseAdivinadora() {
        for(Node node : graph) {
            if(halfProbability()) {
                node.addAttribute("ui.style", "fill-color: rgb(255,0,0);");
                node.setAttribute("independent", true);
                independentSet.add(node.getId());
            }
        }
    }

    /**
     * Fase verificadora del algoritmo.
     * Itera sobre los vértices de la gráfica, si el vértice actual es parte
     * del candidato de solución verifica que sus vecinos no lo sean.
     * @return true si el conjunto es candidato a ser conjunto independiente
     * false en otro caso.
     */
    public boolean faseVerificadora() {
        for(Node node : graph) {
            boolean check = node.getAttribute("independent");
            // Si el vértice actual es del conjunto independiente los vecinos
            // no deben de estar en el conjunto
            if(check) {
                // Itera sobre los vecinos del vértice actual
                Iterator<? extends Node> it = node.getNeighborNodeIterator();
                while(it.hasNext()) {
                    Node n = it.next();
                    boolean check2 = n.getAttribute("independent");
                    // Si uno de sus vecinos tambien es independiente no es
                    // candidato a solución
                    if(check2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Método para mostrar la interfaz visual del la gráfica.
     */
    public void displayGraph() {
        graph.display();
    }

}
