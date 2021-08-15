package com.jorgemartinez.programa03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** 
 * Clase que contiene los algoritmos genéticos.
 * No es orientada a objetos, si no una clase con comportamiento imperativo.
 * Los métodos de esta clase son los cruces y mutaciones genéticas
 * para los elementos de la clase @see TruthAssign
 */
public class GeneticAlgorithm {

    /* Tamaño de cada población. */
    final int SIZE_POPULATION = MaxSat.SIZE_POPULATION;
    /* Numero total de generaciones. */
    final int NUM_GENERATIONS = MaxSat.NUM_GENERATIONS;
    /* Lista con los hijos. */
    ArrayList<TruthAssign> listChild;
    /* Random para generar valores aleatorios. */
    Random random = new Random(); 

    public GeneticAlgorithm() {
        this.listChild = new ArrayList<>();
    }

    /**
     * Método para obtener la lista de hijos.
     * @return La lista de hijos.
     */
    public ArrayList<TruthAssign> getListChild() {
        return listChild;
    }
    
    /**
     * Método para eliminar todos los elementos de la lista.
     */
    public void clearListChild() {
        listChild.clear();
    }

    public void genetic(boolean [] father1, boolean father2[]) {
        int randomCross = random.nextInt(2);
        boolean child[] = new boolean[SIZE_POPULATION];
        switch(randomCross) {
            // Partially Mapped crossover
            case 0:
                child = partiallyMappedCrossover(father1, father2);
                break;
            // Order crossover
            case 1:
                child = orderCrossover(father1, father2);
                break;
        }
        // Revisa si se realiza una mutación
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
        listChild.add(new TruthAssign(child));
    }

    /**
     * Decide si se aplica la mutación
     * Probabilidad de mutación = 0.2
     * @return true si el número aleatorio es mayor a 0.2, false en otro caso.
     */
    public boolean doMutation() {;
        double randomProb = random.nextFloat();
        // 0 <= randomProb <= 0.2
        return 0 <= randomProb && randomProb <= 0.2;
    }

    /**
     * Partially mapped crossover.
     * De un subarreglo del padre2, pone el padre1 y reasigna todos los valores
     * sobreescribiendolos en un hijo.
     * @param father1 padre1.
     * @param father2 padre2.
     * @return Un hijo producto del PMX.
     */
    public boolean [] partiallyMappedCrossover(boolean father1[], 
        boolean father2[]) {
        boolean[] child = new boolean[father1.length];
        // Limites generados aleatoriamente
        int lowerBound = random.nextInt(father1.length-1);
        int upperBound = random.nextInt(
                father1.length-1 - lowerBound + 1) + lowerBound;
        // Mapea los valores del padre2 en el hijo 
        for(int i = lowerBound; i < upperBound; i++)
            child[i] = father2[i];
        // Itera sobre el limite inferior y reasigna los elementos
        for(int i = 0; i < lowerBound; i++)
                child[i] = father1[i];
        // Itera sobre el limite superior y reasigna los elementos
        for(int i = upperBound; i < father1.length; i++)
                child[i] = father1[i];
        return child;
    }

    /**
     * Order crossover.
     * Escoge un subrarreglo del padre1 y llena los elementos restantes a
     * partir del padre2 preservando el orden relativo.
     * @param father1 padre1.
     * @param father2 padre2.
     * @return Un hijo producto del order crossover.
     */
    public boolean[] orderCrossover(boolean father1[], boolean father2[]) {
        boolean[] child = new boolean[father1.length];
        // Cotas aleatorias
        int lowerBound = random.nextInt(father1.length-1);
        int upperBound = random.nextInt(
                father1.length-1 - lowerBound + 1) + lowerBound;
        // Subarreglo del padre1
        boolean[] subArray = Arrays.
                            copyOfRange(father1, lowerBound, upperBound);
        // Elementos restantes
        ArrayList<Boolean> listRemain = new ArrayList<>();
        
        // Llena la lista a partir de los índices
        for(int i = upperBound; i < father2.length; i++)
            listRemain.add(father2[i]);
        for(int i = 0; i < upperBound; i++)
            listRemain.add(father2[i]);
        
        for(int i = lowerBound, j = 0; i < upperBound; i++, j++)
            child[i] = subArray[j];
        // Agrega los elementos restantes
        for(int i = upperBound; i < child.length; i++)
            child[i] = listRemain.remove(0);
        for(int i = 0; i < lowerBound; i++)
            child[i] = listRemain.remove(0);
        //listRemain.clear();
        return child;
    }

    /**
     * ExchangeMutation.
     * Intercambia los valores entre la posicion i y j
     * @param arr[] Arreglo al cual aplicar la mutación
     */
    public void exchangeMutation(boolean arr[]) {
        int i = random.nextInt(arr.length-1);
        int j = random.nextInt(arr.length-1);
        boolean aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    /**
     * Displacement mutation.
     *Selecciona un subarreglo y lo aplica sobre una posicion aleatoria
     * @param arr Arreglo al cual aplicar la mutación
     */
    public void displacementMutation(boolean arr[]) {
        int i = random.nextInt(arr.length-1);
        int j = random.nextInt(arr.length-1 - i + 1) + i;
        boolean aux = arr[j];
        for(int k = j; k>i; k--)
            arr[k] = arr[k-1];
        arr[i] = aux;
    }
}