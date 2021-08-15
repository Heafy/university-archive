import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase principal para el problema BinPacking.
 */
public class BinPacking {

    /* Lista con los tamaños introducida por el usuario. */
    ArrayList<Double> sizesList;
    /* Lista de items para almacenarlos en Bins. */
    ArrayList<Item> itemList;
    /* Lista de Bins. */
    ArrayList<Bin> binList;

    /**
     * Constructor único.
     * Inicializa las listas.
     */
    public BinPacking() {
        sizesList = new ArrayList<Double>();
        itemList = new ArrayList<Item>();
        binList = new ArrayList<Bin>();
        // Suponemos que existe al menos un Bin dentro de la lista
        binList.add(new Bin());
    }

    /**
     * Método para recibir la entrada del usuario.
     */
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Agrega el tamaño de los elementos uno por uno. " +
        "\n0.0 <= Valor <= 1.0" + 
        "\nDeja el espacio en blanco cuando hayas terminado:");
        // Agrega la entrada a sizesList mientras no sea vacia
        try{
            String input = scanner.nextLine();
            Double dInput = Double.parseDouble(input);
            if(0 > dInput || dInput > 1) {
                System.out.println("Valor no válido, programa terminado.");
                System.exit(1);
            }
            sizesList.add(dInput);
            while(!input.isEmpty()){
                input = scanner.nextLine();
                dInput = Double.parseDouble(input);
                if(0 > dInput || dInput > 1) {
                    System.out.println("Valor no válido, programa terminado.");
                    System.exit(1);
                }
                sizesList.add(dInput);
            }
        }catch(NumberFormatException nfe) {
            // Sucede cuando detecta una entrada vacia, termina la entrada
        }
        scanner.close();
    }

    /**
     * Algoritmo binPacking para ordenar los elementos introducidos en bins.
     */
    public void binPackingAlgorithm() {

        /* Ordena la lista en orden descendente. */
        Collections.sort(sizesList, Collections.reverseOrder());
        // Muestra la lista introducida 
        System.out.println(sizesList.toString());

        // Los elementos de la lista los pasa a Items para saber si podemos
        // empaquetarlos, como en este punto la lista está ordenada esta 
        // también estará ordenada
        for(double f : sizesList) {
            itemList.add(new Item(f));
        }

        // Iterador para la lista de tamaños introducida
        Iterator<Item> itItems = itemList.iterator();
        // Iterador para la lista de Bins
        Iterator<Bin> itBins = binList.iterator();

        /* Mientras haya elementos que no hayan sido empaquetados en bins. */
        while(itItems.hasNext()) {
            Item nextItem = itItems.next();
            double nextDouble = nextItem.getSize();
            /* Mientras el item no sea puesto en un bin y haya bins disponibles. */
            while(itBins.hasNext() && !nextItem.getpacked()) {
                Bin nextBin = itBins.next();
                /* Si el item puede ser empaquetado en el Bin actual. */
                if(nextBin.canAdd(nextDouble)) {
                    nextBin.add(nextDouble);
                    nextItem.setPacked(true);
                }
            }
            /* Si se acabaron los bins y el item no ha sido puesto en uno. */
            if(!nextItem.getpacked()) {
                Bin newBin = new Bin();
                newBin.add(nextDouble);
                binList.add(newBin);
            }
            // Apunta de nuevo el iterador a la cabeza de la lista de Bins
            // Evita errores de concurrencia
            itBins = binList.iterator();
        }
    }

    /**
     * Método para mostrar la lista de Bins en la salida estandar
     */
    public void printBinList() {
        Iterator itBins = binList.iterator();
        int i = 1;
        while(itBins.hasNext()) {
            System.out.println("Bin " + i + ": " + itBins.next().toString());
            i++;
        }
    }

    /**
     * Método principal.
     * @param args Lista de argumentos.
     */
    public static void main( String[] args ) {
        BinPacking binPacking = new BinPacking();
        binPacking.getInput();
        binPacking.binPackingAlgorithm();
        binPacking.printBinList(); 
    }
}