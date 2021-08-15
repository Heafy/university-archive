package com.jorgemartinez.programa03;

import java.util.ArrayList;
import java.util.Random;

/** Clase para modelar una asignacion de verdad.
 * Una asignación de verdad es una lista de bits (0,1) i,.e true or false
 * Y un número fitness el cual indica el número de cláusulas que
 * satisface.
 */
public class TruthAssign implements Comparable<TruthAssign>, Cloneable{

    /* Arreglo con la asignación de verdad. */
    boolean[] assign;
    /* Fitness de la asignación de verdad. */
    int fitness;
    /* Random para generar valores aleatorios. */
    Random random = new Random();

    /**
     * Constructor.
     * Crea una asignación de verdad aleatoria.
     * @param varList La lista de variables.  
     */
    public TruthAssign(ArrayList<String> varList) {
        this.assign = new boolean[varList.size()];
        this.fitness = 0;
        fillAssignArray(varList);
    }

    /**
     * Constructor.
     * Crea una asignación  de verdad a partir del arreglo recibido.
     * @param assign La asignación de verdad.
     */
    public TruthAssign(boolean[] assign) {
        this.assign = assign;
        this.fitness = 0;
    }

    /** 
     * Método para obtener el arreglo con la asignaciones de verdad.
     * @return El arreglo con las asignaciones de verdad.
     */
    public boolean[] getAssign() {
        return assign;
    }

    /**
     * Método para obtener el fitness de la asignación de verdad.
     * @return El fitness de la asignación de verdad.
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * Método para modificar el fitness de la asignacion de verdad.
     * @param fitness El nuevo fitness.
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    /**
     * Método para incrementar el fitness de la asignación de verdad en 1.
     * Se usa cuando la asignación de verdad satisface a la cláusula
     */
    public void increaseFitness() {
        this.fitness++;
    }

    /**
     * Método para llenar con valores aleatorios la lista.
     * Itera sobre la lista de variables y les asigna un valor de asignación
     * aleatorio.
     * @param varList La lista de variables.
     */
    public void fillAssignArray(ArrayList<String> varList) {
        // Itera sobre los valores de la lista agregando un booleano aleatorio
        // para cada elemento de la lista de variables
        for(int i = 0; i < varList.size(); i++)
            assign[i] = random.nextBoolean();
    }

    /**
     * Método para mostrar la informacíon de la asignación de verdad.
     */
    public void showInfo() {
        for(int i = 0; i < assign.length; i++)
            System.out.printf("[X%d, %b]\n", i, assign[i]);
        //System.out.println("Fitness: " + fitness);
    }

    /**
     * Compara dos asignaciónes de verdad mediante su fitness
     * @param o La asignación de verdad a comparar
     * @return -1 Si fitness < o.getFitness()
     * 0 Si o.getFitness() == fitness
     * 1 Si o.getFitness() > fitness 
     */
    @Override
    public int compareTo(TruthAssign o) {
        int comparator = ((TruthAssign)o).getFitness();
        return this.fitness-comparator;
    }

    /**
     * Método para clonar un Objeto de tipo TruthAssign
     * @return la copia del objeto.
     */
    public Object clone() throws CloneNotSupportedException{ 
        return (TruthAssign)super.clone();
    }
        
}