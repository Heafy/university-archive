package com.ia.tsp2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Class for the genetic algorithms and the crossovers.
 */
public class GeneticAlgorithm {
    
    /** Generation size. */
    static final int SIZEPOPULATION = TSP.SIZEPOPULATION;
    /** Number of cities. */
    static final int NUMBERCITIES = TSP.NUMBERCITIES;
    ArrayList<Tour> listChild;
    Random random = new Random();
    
    /**
     * Constructor.
     */
    public GeneticAlgorithm() {
        this.listChild = new ArrayList<>();
    }
    
    /**
     * Get list child after each generation.
     * @return La lista de hijos.
     */
    public ArrayList<Tour> getListChild() {
        return listChild;
    }
    
    /**
     * Remove all elements from the list of the child.
     */
    public void clearListChild() {
        listChild.clear();
    }
    
    /**
     * Create the initial population.
     * Generate a random array of numbers between 0-50 without repeat
     * @return A random array
     */
    public int [] generationZero() {
        ArrayList<Integer> lista = new ArrayList<>(NUMBERCITIES-1);
        for(int i = 0; i <= NUMBERCITIES; i++)
            lista.add(i);
        int[] population = new int[NUMBERCITIES+1];
        int counter = 0;
        // Remove random int from list and added to the array
        while(lista.size() > 0) {
            int elem = random.nextInt(lista.size());
            population[counter] = lista.remove(elem);
            counter++;
        }
        return population;
    }
    
    /**
     * Auxiliary method.
     * Get a list of transitive key given the key and the map
     * @param map Hashmap for searching the key
     * @param key Key to search the transitive keys
     * @return 
     */
    public List<Integer> getTransitiveKey(HashMap<Integer, Integer> map,
            int key) {
        List<Integer> result = new LinkedList<>();
        while(map.containsKey(key)) {
            if(result.contains(key))
                break;
            result.add(key);  
            key = map.get(key);
        }
        return result;
    }
    
    /**
     * Auxiliary method.
     * Get the new Map with the transitive keys
     * @param map HashMap for searching the transitive keys
     * @return A new HashMap with the transitive keys
     */
    public HashMap<Integer, Integer> getTransitiveMap(HashMap<Integer,
            Integer> map) {
        HashMap<Integer, Integer> transitiveMap = new HashMap<>();
        map.entrySet().forEach((entry) -> {
            List<Integer> list = getTransitiveKey(map, entry.getKey());
            int key = entry.getKey();
            // Get value with the last transitive key
            int value = map.get(list.get(list.size() - 1));
            transitiveMap.put(key, value);
        });
        return transitiveMap;
    }
    
    /**
     * Partially mapped crossover.
     * From a subset of father2, put in in father1 and remmap all the values
     * overwritten in the new child.
     * @param father1 father1.
     * @param father2 father2.
     * @return A child with the partially mapped crossover.
     */
    public int [] partiallyMappedCrossover(int father1[], int father2[]) {
        int[] child = new int[father1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        // Bounds generate randomly
        int lowerBound = random.nextInt(father1.length-1);
        int upperBound = random.nextInt(
                father1.length-1 - lowerBound + 1) + lowerBound;
        // Map the values of the father2 in the child and add the values
        // in a map
        for(int i = lowerBound; i < upperBound; i++) {
            map.put(father2[i], father1[i]);
            child[i] = father2[i];
        }
        // HashMap with the transitive keys
        HashMap<Integer, Integer> transitiveMap = getTransitiveMap(map);
        // Iterate over the lower bound and remap the elements
        for(int i = 0; i < lowerBound; i++){
            if(transitiveMap.containsKey(father1[i]))
                child[i] = transitiveMap.get(father1[i]);
            else
                child[i] = father1[i];
        }
        // Iterate over the upper bound and remap the elements
        for(int i = upperBound; i < father1.length; i++) {
            if(transitiveMap.containsKey(father1[i]))
                child[i] = transitiveMap.get(father1[i]);
            else
                child[i] = father1[i];
        }
        return child;
    }
    
    /**
     * Cycle crossover.
     * Generates a child such that each city and her index comes from one
     * of the fathers.
     * @param father1 father1.
     * @param father2 father2.
     * @return A child with the cycle crossover.
     */
    public int [] cycleCrossover(int father1[], int father2[]) {
        int[] child = new int[father1.length];
        // Set with the index of the added elements
        Set<Integer> setIndex = new HashSet<>();
        // Set with the value of the elements added
        Set<Integer> setAdded = new HashSet<>();
        // An element is orphaned if it is not in the parents and it is
        // not added yet
        ArrayList<Integer> orphanList = new ArrayList<>();
        int stop = 0;
        int i = 0;
        // Heredated values from father1
        do {
            setIndex.add(i);
            child[i] = father1[i];
            setAdded.add(child[i]);
            i = father2[i];
        }while(i != stop);
        
        for(int j = 0; j < child.length; j++) {
            // if j is not in setIndex it means that the index is not modified
            if(!setIndex.contains(j)) {
                // Add element j in father2 if it has not been added 
                if(!setAdded.contains(father2[j])) {
                    child[j] = father2[j];
                    setAdded.add(child[j]);
                } else {
                    // In other case check if the element j in father1
                    // for add in the child
                    if(!setAdded.contains(father1[j])) {
                        child[j] = father1[j];
                        setAdded.add(child[j]);
                    } else {
                        // In other case the element is orphan
                        orphanList.add(j);
                    }
                }
            }
        }
        // The orphans are added to the child checking the index and if
        // it has not been added
        for(int j : orphanList){
            for(int k = 0; k < child.length; k++){
                if(!setAdded.contains(k)){
                    child[j] = k;
                    setAdded.add(k);
                    break;
                }
            }
        }
        return child;
    }
    
    /**
     * Order crossover.
     * Choose a subarray from father1 and fill the other elements from the
     * other father preserving the relative order of cities
     * @param father1 father1.
     * @param father2 father2.
     * @return A child with the cycle crossover.
     */
    public int[] orderCrossover(int father1[], int father2[]) {
        int[] child = new int[father1.length];
        // Bounds generate randomly
        int lowerBound = random.nextInt(father1.length-1);
        int upperBound = random.nextInt(
                father1.length-1 - lowerBound + 1) + lowerBound;
        // Subarray from father1
        int[] subArray = Arrays.copyOfRange(father1, lowerBound, upperBound);
        // Set of element added in the child
        Set<Integer> setAdded = new HashSet<>();
        // Remaining elements
        ArrayList<Integer> listRemain = new ArrayList<>();
        
        // Fill the list from the bounds
        for(int i = upperBound; i < father2.length; i++)
            listRemain.add(father2[i]);
        for(int i = 0; i < upperBound; i++)
            listRemain.add(father2[i]);
        
        // Fill the set with the elements in the subArray
        for(int i : subArray)
            setAdded.add(i);
        // Remove the elements that already are in the set
        listRemain.removeAll(setAdded);
        
        for(int i = lowerBound, j = 0; i < upperBound; i++, j++)
            child[i] = subArray[j];
        // Add the remain elements from the bounds
        for(int i = upperBound; i < child.length; i++)
            child[i] = listRemain.remove(0);
        for(int i = 0; i < lowerBound; i++)
            child[i] = listRemain.remove(0);
        //listRemain.clear();
        return child;
    }
    
    /**
     * ExchangeMutation.
     * Exchange the value in position i and j
     * @param arr[] Array to apply the mutation
     */
    public void exchangeMutation(int arr[]) {
        int i = random.nextInt(arr.length-1);
        int j = random.nextInt(arr.length-1);
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }
    
    /**
     * Displacement mutation.
     * Change the values between i and j positions
     * @param arr Array to apply the mutations
     */
    public void displacementMutation(int arr[]) {
        int i = random.nextInt(arr.length-1);
        int j = random.nextInt(arr.length-1 - i + 1) + i;
        int aux = arr[j];
        for(int k = j; k>i; k--)
            arr[k] = arr[k-1];
        arr[i] = aux;
    }
    
    /**
     * Decide if a mutation is applied.
     * Probability of mutation = 0.25
     * @return true if the mutations is realized, false in other case
     */
    public boolean doMutation() {;
        double randomProb = random.nextFloat();
        // 0 <= randomProb <= 0.25
        return 0 <= randomProb && randomProb <= 0.25;
    }
    
    /**
     * Main genetic algorithm.
     * Given two parents apply one random crossover and check if one
     * mutation is applied. After a child is created, add it to the list.
     * @param father1 father1
     * @param father2 father2
     */
    public void genetic(int [] father1, int father2[]) {
        int randomCross = random.nextInt(3);
        int child[] = new int[SIZEPOPULATION];
        switch(randomCross) {
            // Partially Mapped crossover
            case 0:
                child = partiallyMappedCrossover(father1, father2);
                break;
            // Cycle crossover
            case 1:
                child = cycleCrossover(father1, father2);
                break;
            // Order crossover
            case 2:
                child = orderCrossover(father1, father2);
                break;
        }
        // Check if mutations is applied
        if(doMutation()) {
            int randomMutation = random.nextInt(2);
            switch(randomMutation) {
                // Exchange mutation
                case 0:
                    exchangeMutation(child);
                    break;
                // Displacement mutation
                case 1:
                    displacementMutation(child);
                    break;
            }
        }
        listChild.add(new Tour(child));
    }
    
    /**
     * Auxiliary method.
     * Given an array check if it has duplicate elements
     * @param arr Array to check
     * @return true if have duplicate elements, false in other case
     */
    public boolean checkRepeat(int [] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i))
                return true;
            set.add(i);
        }
        return false;  
    }
    
}
