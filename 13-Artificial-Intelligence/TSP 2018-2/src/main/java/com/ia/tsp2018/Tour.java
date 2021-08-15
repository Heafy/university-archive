package com.ia.tsp2018;

import java.util.Arrays;

/**
 * Class to represent the Tour.
 */
public class Tour implements Comparable<Tour> {

    /** Array that represent the index tour. */
    int[] tour;
    /** Fitness of the tour, sum of total cost. */
    int fitness;
    /** Tour length, always is 50 cities. */
    static final int LENGTH = TSP.NUMBERCITIES;
    /** Bidimensional array with the travel costs obtained from the .csv file. */
    int[][] costos = new Reader().getCosts();

    /**
     * Constructor.
     * @param tour The array tour
     */
    public Tour(int[] tour) {
        this.tour = tour;
        this.fitness = calculateFitness();
    }
    
    /**
     * Get the tour of the object.
     * @return The tour of the object
     */
    public int[] getTour() {
        return tour;
    }
    
    /**
     * Set the tour of the object.
     * @param tour The new tour of the object
     */
    public void setTour(int[] tour) {
        this.tour = tour;
    }
    
    /**
     * Get the fitness of the object.
     * @return The fitness of the object
     */
    public int getFitness() {
        return fitness;
    }
    
    /**
     * Set the fitness of the object.
     * @param fitness The new fitness of the object
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    
    /**
     * Calculate fitness of the tour.
     * Indicates how good of bad is the tour
     * @return Sum of cost of the tour
     */
    public int calculateFitness() {
        int k = 0; int l;
        int fit = 0;
        int right = 0; 
        int left = 0;
        for (l = 1; l<LENGTH; l++){
            if(tour[k]>tour[l]){
                right = tour[k];
                left = tour[l];
            }else{
                right = tour[l];
                left = tour[k];
            }
            fit = fit + costos[right][left];
            k++;
        }
        int start = tour[0];
        int lastPosition = tour[tour.length-1];
        if(tour[0] > tour[tour.length-1]){
            fit = fit + costos[start][lastPosition];
        }else{
            fit = fit + costos[lastPosition][start];
        }
        return fit;
    }
    
    /**
     * Represent the Tour as a String for print it.
     * @return The tour info as a a String
     */
    @Override
    public String toString() {
        return Arrays.toString(tour) + "\nFitness: " + fitness;
    }
    
    /**
     * Compare two Tours by their fitness.
     * @param o The compared tour
     * @return -1 if the fitness object is less than the specified object
     * 0 if the fitness object is less than the specified object
     * 1 if the fitness object is greater than the specified object
     */
    @Override
    public int compareTo(Tour o) {
        int comparator = ((Tour)o).getFitness();
        return this.fitness-comparator;
    }
    
    
}
