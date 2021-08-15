import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase para resolver el problema de aproximación SubetSum
 */
public class SubsetSum {

    /* La lista de elementos para el algoritmo. */
    ArrayList<Integer> S;
    /* Valor de aproximación del resultado. */
    int t;
    /* Factor de aproximación. */
    double epsilon;

    /**
     * Constructor unico.
     * Inicializa las variables de clase.
     */
    public SubsetSum() {
        S = new ArrayList<>();
        t = 0;
        epsilon = 0;
    }

    /**
     * Método para recibir la entrada del usuario.
     * Recibe la lista, el valor máximo aproximado y el valor epsilon.
     */
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Agrega el tamaño de los elementos de la lista uno por uno. " +
        "\nIntroduce solo valores enteros." + 
        "\nDeja el espacio en blanco cuando hayas terminado:");
        // Agrega la entrada a la lista mientras no sea vacia
        try{
            String input = scanner.nextLine();
            int intInput = Integer.parseInt(input);
            S.add(intInput);
            while(!input.isEmpty()){
                input = scanner.nextLine();
                intInput = Integer.parseInt(input);
                S.add(intInput);
            }
        }catch(NumberFormatException nfe){
            // Sucede cuando detecta una entrada vacia, termina la entrada
        }

        try {
            System.out.print("Introduce el valor aproximado t. " +
            "\nIntroduce solo valores enteros: ");
            t = scanner.nextInt();
            
            System.out.print("Introduce el factor de aproximación epsilon: ");
            epsilon = scanner.nextDouble();
        }catch(InputMismatchException ime) {
            System.out.println("Valor no válido, programa terminado.");
            System.exit(1);
        }

        System.out.println("\nLista: " + S);
        System.out.println("t: " + t);
        System.out.println("ε: " + epsilon);
        scanner.close();
    }

    /**
     * Método para mezclar dos listas respetando el orden.
     * Previamente deben estar ordenadas las listas.
     * @param list1 Lista 1 para mezclar.
     * @param list2 Lista 2 para mezclar.
     * @return Una lista con los elementos de la lista mezclados
     */
    public ArrayList<Integer> mergeList(ArrayList<Integer> list1, 
        ArrayList<Integer> list2) {
        // Lista con el resultado de la mezcla
        ArrayList<Integer> list3 = new ArrayList<>();
        
        int n1 = list1.size();
        int n2 = list2.size();

        int i, j, k;
        i = j = k = 0;

        // Recorre ambos arreglos
        while(i < n1 && j < n2) {
            // Si el elemento actual de la primera lista es menor que el
            // elemento actual del segundo guarda el del primer elemento
            // e incrementa el indice, en otro caso hace lo mismo pero 
            // con el del segundo arreglo
            if(list1.get(i) < list2.get(j))
                list3.add(k++, list1.get(i++));
            else
                list3.add(k++, list2.get(j++));
        }
        // Guarda los elementos restantes del primer arreglo
        while(i < n1) {
            list3.add(k++, list1.get(i++));
        }
        // Guarda los elementos restantes del primer arreglo
        while(j < n2) {
            list3.add(k++, list2.get(j++));
        }
        return list3;
    }

    /**
     * Algoritmo trim descrito en el Cormen pp.1130
     * Recorta la lista eligiendo representantes de aproximación basándose
     * en el parámetro delta.
     * @param list La lista de la cual recortar elementos.
     * @param delta Factor de aproximación,
     * @return Una lista con los elementos recortados.
     */
    public ArrayList<Integer> trim(ArrayList<Integer> list, double delta) {
        int m = list.size();
        int first = list.get(0);

        ArrayList<Integer> listTrimmed = new ArrayList<>();
        listTrimmed.add(first);

        int last = first;
        for(int i = 1; i < m; i++) {
            if(list.get(i) > last*(1 + delta)) {
                listTrimmed.add(list.get(i));
                last = list.get(i);
            }
        }
        return listTrimmed;
    }

    /**
     * Método para sumar un entero a los elementos de una lista
     * @param list La lista con los elementos
     * @param x_i el i-ésimo elemento que se suma a los de la lista.
     * @return Una nueva lista con los elementos sumados a x_i.
     */
    public ArrayList<Integer> sumAux(ArrayList<Integer> list, int x_i) {
        // Crea una copia de la lista pasada como parámetro
        // Nota: Poner un = solamente copia el valor de la referencia 
        // y del objeto
        ArrayList<Integer> result = new ArrayList<>(list);
        for(int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i)+x_i);
        }
        return result;
    }

    /**
     * Método para crear un arreglo n listas.
     * @param n El número de listas a crear.
     * @return Un arreglo de n listas vacias.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Integer>[] createList(int n) {
        ArrayList<Integer>[] arrayList = new ArrayList[n];
        // El primer elemento es la lista con el 0
        ArrayList<Integer> L_0 = new ArrayList<>();
        L_0.add(0);
        arrayList[0] = L_0;

        for(int i = 1; i < n; i++) 
            arrayList[i] = new ArrayList<>();    
        return arrayList;
    }

    /**
     * Método para eliminar los elementos mayores a t en una lista.
     * @param list La lista a iterar para remover elementos.
     * @param t Entero t límite.
     * @return La lista con los elementos mayores a t eliminados.
     */
    public ArrayList<Integer> remove(ArrayList<Integer> list, int t) {
        int next;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            next = iterator.next();
            if(next > t)
                iterator.remove();
        }
        return list;
    }

    /**
     * Algoritmo de aproximación para SubsetSum.
     * Descrito en el Cormen pp: 1128 - 1133.
     * Trabaja con los elementos pasados de la entrada estándar.
     */
    public int aproxSubsetSum() {
        int x_i;
        ArrayList<Integer> l1, l2;
        int n = S.size();
        double delta = epsilon/(2*n);

        ArrayList<Integer>[] L_n = createList(n);

        for(int i = 1; i < n; i++) {
            x_i = S.get(i);
            // Li-1
            l1 = L_n[i-1];
            // Li-1+x_i
            l2 = sumAux(l1, x_i);

            L_n[i] = mergeList(l1, l2);
            L_n[i] = trim(L_n[i], delta);
            L_n[i] = remove(L_n[i], t);
        }
        // Máximo valor de Ln
        int z = Collections.max(L_n[n-1]);
        return z;
    }

    /**
     * Método principal.
     * @param args Lista de argumentos.
     */
    public static void main(String[] args) {
        SubsetSum ss = new SubsetSum();
        ss.getInput();
        int z = ss.aproxSubsetSum();
        System.out.println("Aproximación z* = " + z);
    }
}