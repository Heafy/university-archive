package com.jorgemartinez.programa01;

/**
 * Clase con la ejecución principal del progama
 * @author Jorge Martínez
 */
public class App {
    public static void main( String[] args ) {
        Grafica graph = new Grafica();

        System.out.println("# Nodos: " + graph.getNodes());

        graph.faseAdivinadora();
        boolean solucion = graph.faseVerificadora();

        System.out.println("Vértices candidato a ser solución: " + graph.getIndepedentSet());
        System.out.println("El conjunto es solución: " + solucion);
        
        graph.displayGraph();
    }
}
