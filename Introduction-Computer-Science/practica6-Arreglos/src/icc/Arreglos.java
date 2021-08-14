package icc;

import java.util.Random;

/**
 * Clase para ordenar y buscar en arreglos genéricos.
 */
public class Arreglos {

    private static <T extends Comparable<T>>
    void intercambia(T[] a, int i, int j) {
		T aux = a[i];
		a[i] = a[j];
		a[j] = aux;
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param a un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>>
    void selectionSort(T[] a) {
		for(int i = 0; i < a.length; i ++){
	    	int min = i;
	    	for(int j = i+1; j < a.length; j++){
				if(a[j].compareTo(a[min]) <= 0)
		    		min = j;
	    	}//for j
	    	intercambia(a, i, min);
		}//for i
    }

    /**
     * Ordena el arreglo recibido usando QickSort.
     * @param a un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>>
    void quickSort(T[] a) {
		quickSortAux(a, 0, a.length-1);
    }

    public static <T extends Comparable<T>>
    void quickSortAux(T[] a, int ini, int fin) {
		if(ini < fin){
	    	int i = ini;
	    	int j = fin;
	    	T aux = a[(i+j) / 2];
	    	do{
	    		while(a[i].compareTo(aux) < 0)
					i++;
	    		while(aux.compareTo(a[j]) < 0)
					j--;
	    		if(i <= j){
					intercambia(a, i, j);
					i++;
					j--;
				    }//if i <= j
	    	} while(i <= j);
			quickSortAux(a, ini, j);
			quickSortAux(a, i, fin);
		}//if
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa
     * el índice del elemento en el arreglo, o -1 si no se
     * encuentra.
     * @param a el arreglo dónde buscar.
     * @param e el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se
     * encuentra.
     */
    public static <T extends Comparable<T>>
    int busquedaBinaria(T[] a, T e) {
		return busquedaBinariaAux(a, e, 0, a.length-1);
    }

    private static <T extends Comparable<T>>
    int busquedaBinariaAux(T[] a, T e, int ini, int fin){
		if(ini > fin)
	    	return -1;
		int n = (ini + fin)/2;
		if(a[n].equals(e))
	    	return n;
		if(a[n].compareTo(e) > 0)
	    	return busquedaBinariaAux(a, e, ini, n-1);
		return busquedaBinariaAux(a, e, n+1, fin);
    }
}
