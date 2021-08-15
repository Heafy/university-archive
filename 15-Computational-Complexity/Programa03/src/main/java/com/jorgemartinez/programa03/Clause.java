package com.jorgemartinez.programa03;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para representar una cláusula.
 * Una cláusula es una colección finita de literales.
 */
public class Clause{

    /* Tamaño mínimo de la clausula. */
    final int MIN_SIZE_CLAUSE = MaxSat.MIN_SIZE_CLAUSE;
    /* Tamaño máximo de la clausula. */
    final int MAX_SIZE_CLAUSE = MaxSat.MAX_SIZE_CLAUSE;
    /* Lista con las cláusulas. */
    ArrayList<String> clauselist;
    /* Número de literales por cláusula generadas. */
    int numLits;
    /* Símbolo de negación. */
    final String not = "¬";
    /* Random para generar valores aleatorios. */
    Random random = new Random();

    /**
     * Constructor unico.
     * Inicializa la lista y su número de literales.
     * La llena con variables aleatorias.
     * @param list La lista con las variables para poder crear una cláusula.
     */
    public Clause(ArrayList<String> varList) {
        clauselist = new ArrayList<>();
        this.numLits = random.nextInt((MAX_SIZE_CLAUSE - MIN_SIZE_CLAUSE) + 1)
                        + MIN_SIZE_CLAUSE;
        fillClause(varList);
    }

    /** 
     * Método para llenar la cláusula con literales.
     * A partir de la lista obtiene cláusulas aleatorias.
     * @param varList La lista con las variables.
     */
    public void fillClause(ArrayList<String> varList) {
        int sizeList = varList.size();
        int randomIndex;
        String randomVar;
        // Itera sobre la lista y le agrega valores aleatorios
        for(int i = 0; i < numLits; i++){
            randomIndex = random.nextInt(sizeList);
            randomVar = varList.get(randomIndex);
            // Agrega aleatoriamente clausulas negadas
            if(random.nextBoolean())
                randomVar = not + randomVar;
            clauselist.add(randomVar);
        }
    }
 
    /**
     * Método para mostrar la cláusula como cadena.
     */
    @Override
    public String toString() {
        return clauselist.toString();
    }

    /**
     * Método para evaluar una clásula a partir de la asignación de verdad.
     * Evalua cada elemento de la cláusula con los valores de la asignación.
     * @param truthAssign La asignación de verdad.
     * @return true si la cláusula se evalua a verdadero, false en otro caso.
     */
    public boolean evaluate(TruthAssign truthAssign) {
        // Obtiene la lista de asignación de verdad
        boolean[] assign = truthAssign.getAssign();
        // Lista auxiliar para el valor de los elementos de la cláusula
        ArrayList<Boolean> booleanList = new ArrayList<>();
        int index;
        String var;
        boolean value;
        // Itera sobre los elementos de la cláusula
        for(String clause : clauselist) {
            // Si la cláusula es negada omite el caracter y asigna 
            // el valor contrario
            if(clause.startsWith(not)) {
                var = clause.substring(1);
                index = getIndexClause(var);
                value = !assign[index];
            // En otro caso hace la evaluacion normal
            } else {
                var = clause;
                index = getIndexClause(var);
                value = assign[index];
            }
            // Se agrega la clausula a la lista
            booleanList.add(value);
        }
        // Revisa si la asignación de verdad satisface a la cláusula
        boolean satisfied = evaluateBooleanList(booleanList);
        // Si la satisface aumenta su fitness en 1
        if(satisfied)
            truthAssign.increaseFitness();
        return satisfied;
    }

    /**
     * Método auxiliar para evaluar una lista de elementos booleanos.
     * @param boolList Lista de booleanos.
     * @return false si la lista contiene puros elementos falsos o al final
     * existen dos elementos falsos, true en otro caso.
     */
    public boolean evaluateBooleanList(ArrayList<Boolean> boolList) {
        // Obtiene el primer valor de la lista
        boolean value = boolList.get(0);
        // Itera a partir del segundo elemento
        for(int i = 1; i < boolList.size(); i++)
            value = value || boolList.get(i);
        return value;
    }

    /**
     * Método para obtener el indice de la literal.
     * @param lit La literal de la cual obtener el índice.
     * @return El índice de la literal.
     */
    public int getIndexClause(String lit) {
        return Integer.parseInt(lit.substring(1));
    }
}