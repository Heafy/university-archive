package com.jorgemartinez.programa03;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para representar un ejemplar del problema.
 * Un ejemplar es un conjunto de variables y de cláusulas.
 */
public class Instance {
   
    /* Máximo numero de variables que se pueden generar. */
    static final int MAX_VARIABLES = MaxSat.MAX_VARIABLES;
    /* Mínimo numero de variables que se pueden generar. */
    static final int MIN_VARIABLES = MaxSat.MIN_VARIABLES;
    /* Máximo número de cláusulas que se pueden generar. */
    static final int MAX_CLAUSE = MaxSat.MAX_CLAUSE;
    /* Mínimo número de cláusulas que se pueden generar. */
    static final int MIN_CLAUSE = MaxSat.MIN_CLAUSE;
    /* Número de variables generadas. */
    int numVars;
    /* Número de cláusulas generadas. */
    int numClauses;
    /* Lista con las variables. */
    ArrayList<String> varList;
    /* Lista con las clausulas. */
    ArrayList<Clause> clauseList;
    /* Random para generar valores aleatorios. */
    Random random = new Random(); 

    /** 
     * Constructor único.
     * Crea un ejemplar.
     * Un ejemplar es la lista de variables y sus cláusulas.
     */
    public Instance() {
        // + 1 para evitar que de 0
        this.numVars = random.nextInt((MAX_VARIABLES - MIN_VARIABLES) + 1)
                        + MIN_VARIABLES;
        this.numClauses = random.nextInt((MAX_CLAUSE - MIN_CLAUSE) + 1)
                            + MIN_CLAUSE;
        this.varList = new ArrayList<>();
        this.clauseList = new ArrayList<>();
        initializeVarList();
        initializeClauseList();
    }
 
    /**
     * Método para obtener la lista de variables.
     * @return La lista con las variables.
     */
    public ArrayList<String> getVarList() {
        return varList;
    }

    /** 
     * Método para obtener la lista con las cláusulas.
     */
    public ArrayList<Clause> getClauseList() {
        return clauseList;
    }

    /**
     * Método para inicializar las variables del ejemplar.
     */
    public void initializeVarList() {
        for(int i = 0; i < numVars; i++)
            varList.add("X" + i);
    }

     /**
     * Método para inicializar las variables aleatorias del ejemplar.
     */
    public void initializeClauseList() {
        for(int i = 0; i < numClauses; i++)
            clauseList.add(new Clause(varList));
    }

    /**
     * Método para mostrar la información del constructor.
     */
    public void showInfo() {
        System.out.println("EJEMPLAR:");
        System.out.printf("Variables: %d\nCláusulas: %d\n", 
                            numVars, numClauses);
        System.out.print("Variables:\n[");
        // Truco para que se imprima bonito
        if(numVars < 10){
            for(int i = 0; i < varList.size(); i++)
                System.out.print(varList.get(i) + ", ");
        } else {
            System.out.printf("X1, X2, .... , X%d, X%d", numVars-1, numVars);
        }
        System.out.print("]\n");
        System.out.println("Cláusulas:");
        for(Clause clause : clauseList)
            System.out.println(clause.toString());
    }
}