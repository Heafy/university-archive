package com.jorgemartinez.programa03;

import java.util.ArrayList;
import java.util.Collections;

public class MaxSat {

    /**
     * Variables globales.
     * Variables que controlan parámetros principales 
     * dentro del algoritmo genético.
     * DISCLAIMER: Aunque el programa responde bien ante diferentes 
     * parámetros, no se asegura el funcionamiento correcto si estos 
     * valores son modificadas.
    */
    /* Máximo numero de variables que se pueden generar. */
    static final int MAX_VARIABLES = 200;
    /* Mínimo numero de variables que se pueden generar. */
    static final int MIN_VARIABLES = 100;
    /* Máximo número de cláusulas que se pueden generar. */
    static final int MAX_CLAUSE = 60;
    /* Mínimo número de cláusulas que se pueden generar. */
    static final int MIN_CLAUSE = 50;
    /* Tamaño mínimo de la clausula. */
    static final int MIN_SIZE_CLAUSE = 3;
    /* Tamaño máximo de la clausula. */
    static final int MAX_SIZE_CLAUSE = 5;
    /* Tamaño de cada población. */
    static final int SIZE_POPULATION = 20;
    /* Numero total de generaciones. */
    static final int NUM_GENERATIONS = 10;

    /**
     * Método para la primera generación del algoritmo genético.
     * Crea la población inicial y la evalua.
     * @param varList La lista de variables
     * @param clauseList La lista de cláusulas
     * @param generationList La lista con los elementos de la población.
     */
    public static void firstGeneration(ArrayList<String> varList, 
        ArrayList<Clause> clauseList, ArrayList<TruthAssign> generationList) {
        // Primera generación de la población
        System.out.println("\nGeneración: 0");
        for(int i = 0; i < SIZE_POPULATION; i++) {
            TruthAssign truthAssign = new TruthAssign(varList);
            generationList.add(truthAssign);
        }
        evaluateForGeneration(clauseList, generationList);
        showFitnessByGen(generationList);        
    }

    /**
     * Método para evaluar la lista de cláusulas con las asignaciones de verdad
     * creadas por el algoritmo genético
     * @param clauseList La lista de cláusulas
     * @param generationList La lista con los elementos de la población.
     */
    public static void evaluateForGeneration(ArrayList<Clause> clauseList, 
        ArrayList<TruthAssign> generationList) {
        // Cada elemento de la primera generación evalua a las cláusulas
        for(Clause clause: clauseList) {
            for(TruthAssign truthAssign : generationList) {
                clause.evaluate(truthAssign);
            }
        }
    }

    /**
     * Método para mostrar el fitness de cada generación
     * @param generationList La lista con los elementos de la población.
     */
    public static void showFitnessByGen(ArrayList<TruthAssign> generationList) {
        for(int i = 0; i < generationList.size(); i++){
            System.out.printf("Elemento %d:\n Fitness: %d\n", 
                i+1, generationList.get(i).getFitness());
        }
    }
    
    /**
     * Método para limpiar el fitness al final de cada generación.
     * Evita que el fitness aumente innecesariamente tras cada generación.
     * @param generationList La lista con los elementos de la población.
     */
    public static void clearFitness(ArrayList<TruthAssign> generationList) {
        for(TruthAssign truthAssign : generationList) 
            truthAssign.setFitness(0);
    }

    /**
     * Método principal.
     * Ejecuta el algoritmo genético
     * @param args Arreglo de argumentos
     * @throws InterruptedException Excepción generada por los Threads.
     * @throws CloneNotSupportedException Excepción generada por los 
     * elementos clonados.
     */
    public static void main(String[] args) 
        throws InterruptedException, CloneNotSupportedException {
        Instance instance = new Instance();
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        ArrayList<TruthAssign> generationList = new ArrayList<>();
        ArrayList<String> varList = instance.getVarList();
        ArrayList<Clause> clauseList = instance.getClauseList();
        TruthAssign best;
        instance.showInfo();

        firstGeneration(varList, clauseList, generationList);

        best = (TruthAssign) 
                generationList.get(generationList.size() - 1).clone();
        System.out.println("Mejor fitness actual: " + best.getFitness() 
        + " / " +  clauseList.size());
        clearFitness(generationList);
        // Tiempo de espera entre cada generación
        Thread.sleep(3000);

        // Ciclo para el resto de las generaciones
        for(int i = 1; i <= NUM_GENERATIONS; i++) {
            System.out.println("\nGeneración: " + i);
            Collections.sort(generationList);
            // Cruza el mejor elemento de la generación con el peor
            int pivotDown = 0;
            int pivotUp = NUM_GENERATIONS-1;
            while(pivotDown < pivotUp) {
                // Para cada asignación aplica el algoritmo genético
                geneticAlgorithm.genetic(
                    generationList.get(pivotDown).getAssign(),
                    generationList.get(pivotUp).getAssign());
                pivotDown++;
                pivotUp--;
            }
            // Obtiene la lista de hijos y remplaza los peores elementos
            ArrayList<TruthAssign> listChild = geneticAlgorithm.getListChild();
            //System.out.println("CHILD SIZE:" + listChild.size());
            int j = 1;
            int position = 0;
            for(TruthAssign truthAssign : listChild) {
                position = generationList.size() - j;
                generationList.set(position, truthAssign);
                j++;
            }
            // Limpia la lista del algoritmo genético
            geneticAlgorithm.clearListChild();
            // Evalua para conseguir el fitness de cada elemento 
            // de la generación
            evaluateForGeneration(clauseList, generationList);
            // Muestra unicamente el fitness de la generación
            showFitnessByGen(generationList);
            // Ordena la lista de la generación por el fitness
            Collections.sort(generationList);

            // Compara entre la lista de la generación actual y el mejor para 
            // decidir cual tiene mejor fitness
            if(best.getFitness() < 
                generationList.get(generationList.size()-1).getFitness()) {
                best = (TruthAssign) 
                        generationList.get(generationList.size() - 1).clone(); 
            }
            //best.showInfo();
            System.out.println("Mejor fitness actual: " + best.getFitness() 
            + " / " +  clauseList.size());

            // Elimina el fitness para que la lista funcione 
            // para la próxima generación
            clearFitness(generationList);
            // Tiempo de espera entre cada generación
            Thread.sleep(3000);            
        }
        System.out.println("Mejor asignación de verdad del algoritmo: ");
        best.showInfo();
        System.out.println("Mejor fitness actual: " + best.getFitness() 
        + " / " +  clauseList.size());
    }
}