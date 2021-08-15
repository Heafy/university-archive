package com.ia.tsp2018;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main class of the project.
 */
public class TSP {

    static int GENERATIONS;
    /** Size of generation. */
    static final int SIZEPOPULATION = 20;
    /** Number of cities. */
    static final int NUMBERCITIES = 50;
    
    /**
     * Main execution of the project.
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in);
        
        // Input for the number of generations
        while(true){
            try {
                System.out.print("Enter how many generations you want to run: ");
                GENERATIONS = scanner.nextInt();
                break;
            } catch(Exception e) {
                System.err.println("Not a number.");
                scanner.next();
            }
        }
             
        Reader reader = new Reader();
        GeneticAlgorithm ga = new GeneticAlgorithm();
        HashMap<Integer, String> map = reader.readIndexCSV();
        Tour best;
        int[] arr = new int[SIZEPOPULATION];
        
        ArrayList<Tour> listaGeneracion = new ArrayList<>();
        
        System.out.println("Generation: 0");
        
        // Create the initial population randomly
        for(int i = 0; i < SIZEPOPULATION; i++) {
            int[] array = ga.generationZero();
            Tour tour = new Tour(array);
            listaGeneracion.add(tour);
            
        }
     
        best = listaGeneracion.get(0);
        
        // Print the first generation
        listaGeneracion.forEach((tour) -> {
            System.out.println(tour.toString());
        });
        
        
        // Cycle for the rest generations
        for(int i = 1; i < GENERATIONS+1; i++) {
            System.out.println("\nGeneration: " + i);
            // Sort the list by fitness
            Collections.sort(listaGeneracion);
  
            // Cross the best tours with the worst
            int pivotDown = 0;
            int pivotUp = GENERATIONS-1;
            while(pivotDown < pivotUp) {
                // For the tours apply the genetic algoritm
                ga.genetic(listaGeneracion.get(pivotDown).getTour(),
                    listaGeneracion.get(pivotUp).getTour());
                pivotDown++;
                pivotUp--;
            }
            
            // Get the child list and replace with the worst elements
            ArrayList<Tour> listChild = ga.getListChild();            
            int j = 1;
            int position = 0;
            for(Tour tour : listChild) {
                position = listaGeneracion.size() - j;
                listaGeneracion.set(position, tour);
                j++;
            }
            // Print the actual population
            listaGeneracion.forEach((tour) -> {
                System.out.println(tour.toString());
            });
            // Clear the child list
            ga.clearListChild();
            
            Collections.sort(listaGeneracion);
            // Compare the list for get the best tour  
            if(best.getFitness() > listaGeneracion.get(0).getFitness())
               best = listaGeneracion.get(0); 
            
            System.out.println("\nBest tour: ");
            arr = best.getTour();
            
            // Show the best tour with the name of the cities
            System.out.print("[ ");
            for(int k : arr)
                System.out.print(map.get(k)+ ", ");
            System.out.print(map.get(arr[0]) + " ]\n");
            System.out.println("Fitness: " + best.getFitness());

            // Wait two seconds for each generation
            Thread.sleep(2000);
        } 

        // Display the graph
        GraphDisplay graph = new GraphDisplay();
        graph.drawGraph(arr, map);
        graph.displayGraph();
    }
}
