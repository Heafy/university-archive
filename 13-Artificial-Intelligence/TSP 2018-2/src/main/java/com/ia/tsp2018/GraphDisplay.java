package com.ia.tsp2018;

import java.util.HashMap;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Class to display a Graph using Graphstream.
 */
public class GraphDisplay {
    
    /** Graphstream Graph. */
    Graph graph;
    
    /**
     * Constructor
     */
    public GraphDisplay() {
        graph = new SingleGraph("");
    }
    
    /**
     * Draw the nodes and edges of the graph.
     * @param arr Array with the graph's index
     * @param map HashMap with the index and cities names
     */
    public void drawGraph(int [] arr, HashMap<Integer, String> map) {
        for(int i : arr)
           graph.addNode(String.valueOf(i));
        
        for(int i = 0; i < arr.length-1; i++) {
            graph.addEdge(String.valueOf(i)+ String.valueOf(i+1), 
                    String.valueOf(i), String.valueOf(i+1));
        }
        // Add the last edge for make a cycle
        graph.addEdge(String.valueOf(0) + String.valueOf(arr.length-1), 
                String.valueOf(0), String.valueOf(arr.length-1));
        
        // Sprite for the cities names
        int j = 0;
        for(Node node : graph) {
            node.addAttribute("ui.label", map.get(j));
            j++;
        }
    } 

    /**
     * Display the UI for the graph
     */
    public void displayGraph() {
        graph.display();
    }
}
