import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que los algoritmos de ordenamiento:
 * - ExchangeSort
 * - BubbleSort
 * @author Jorge Martinez Flores
 */
public class Ordenamientos{

    /** 
     * Clase interna para el Lector de Archivos
     */
    private class Lector{

        /**
         * Metodo para leer un archivo de texto y almacenarlo 
         * como un arreglo.
         * Actualmente solo sirve para archivos con solo una linea de texto
         * @param fileName El nombre del archivo a leer como una cadena.
         * @return El arreglo de enteros creado del archivo.
         */
        private int[] leerArchivo(String fileName){
            int[] arreglo = new int[0];
            int[] arregloAux = new int[0];
            try{
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                for(int i = -1; (line = br.readLine()) != null; i++){
                    // En caso de que el archivo tenga solo una linea
                    if(i == -1){
                        String[] linea = line.trim().split("\\s* \\s*");
                        arreglo = convierteArreglo(linea);
                    // En otro caso crea un arreglo por cada linea y lo concatena
                    }else{
                        String[] linea = line.trim().split("\\s* \\s*");
                        arregloAux = convierteArreglo(linea);
                        arreglo = concatArreglo(arreglo, arregloAux);
                    }
                }
            }catch(Exception e){
                System.err.println("ERROR: " + e);
                //System.exit(1);
            }
            return arreglo;
        }

        /**
         * Funcion auxiliar que convierte un arreglo de cadenas a un
         * arreglo de enteros.
         * @param arrString El arreglo de cadenas que se modificara.
         * @return El arreglo de cadenas que recibe como parametro en forma de
         *         arreglo de enteros.
         */
        private int[] convierteArreglo(String[] arrString){
            int[] arreglo = new int[arrString.length];
            for(int i = 0; i < arrString.length; i++){
                try{
                    arreglo[i] = Integer.parseInt(arrString[i]);
                // En caso de que no sea un numero el que lee
                }catch(NumberFormatException nfe){
                    System.err.println("ERROR: " + nfe);
                    System.exit(1);
                }
            }
            return arreglo;
        }

        /**
         * Funcion auxiliar para concatenar dos arreglos de enteros
         * @param a El primer arreglo
         * @param b El segundo arreglo
         * @return Un arreglo con a y b concatenados
         */
        private int[] concatArreglo(int[] a, int[] b){
            int aLength = a.length;
            int bLength = b.length;
            int[] arreglo = new int[aLength+bLength];
            System.arraycopy(a, 0, arreglo, 0, aLength);
            System.arraycopy(b, 0, arreglo, aLength, bLength);
            return arreglo;
        }
    }// Clase Lector
    
     /**
     * Algoritmo de ordenamiento Exchange Sort.
     * Ordena de manera ascendente o descendente
     * @param arreglo El arreglo a ordenar.
     * @param orden true arregla de manera ascendente, false descendente. 
     * @return El arreglo ordenado.
     */
    public int[] exchangeSort(int[] arreglo, boolean orden){
        int aux;
        for (int i = 0; i < arreglo.length; i++){
            for (int j = i+1; j < arreglo.length; j++){
                // Ascendente
                if(orden == true){
                    if(arreglo[i] > arreglo[j]){
                        aux = arreglo[i];
                        arreglo[i] = arreglo[j];
                        arreglo[j] = aux;
                    }
                // Descendente
                }else{
                    if(arreglo[i] < arreglo[j]){
                        aux = arreglo[i];
                        arreglo[i] = arreglo[j];
                        arreglo[j] = aux;
                    }
                }
            }
        }
        return arreglo;
    }

    /**
     * Algoritmo de ordenamiento Bubble Sort.
     * Ordena de manera ascendente o descendente.
     * @param arreglo El arreglo a ordenar.
     * @param orden true arregla de manera ascendente, false descendente.
     * @return El arreglo ordenado.
     */
    public int[] bubbleSort(int[] arreglo, boolean orden){
        int aux;
        int n = arreglo.length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n-i-1; j++){
                // Ascendente
                if(orden == true){
                    if(arreglo[j] > arreglo[j+1]){
                    aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                    }
                // Descendente
                }else{
                    if(arreglo[j] < arreglo[j+1]){
                    aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                    }
                }

            }
        }
        return arreglo;
    }

    /**
     * Metodo para imprimir un arreglo
     * @param arreglo El arreglo a imprimir
     * @param b booleano para determinar si el arreglo esta ordenado o no
     */
    public void printArreglo(int[] arreglo, boolean b){
        if(b ==true)
            System.out.println("Arreglo ordenado:");
        else
            System.out.println("Arreglo original");
        for (int i = 0; i < arreglo.length; i++)
            System.out.println("Arr[" + i + "]: " + arreglo[i]);
    }

    /**
     * Metodo para mostrar el uso correcto del programa
     */
    private static void uso(){
        System.out.println("Uso: java Ordenamientos file.txt nombreAlgoritmo ascendente/descendente");
        System.exit(1);
    }

    public static void main(String[] args) {
        if(args.length != 3)
            uso();

        Ordenamientos orden = new Ordenamientos();
        Lector lector = new Ordenamientos().new Lector();
        /* Se crea el arreglo a partir del archivo. */
        int[] arreglo = lector.leerArchivo(args[0]);
        /* Se obtiene el algoritmo que quiere el usuario. */
        String opcion = args[1].toUpperCase();
        String opcion2 = args[2].toUpperCase();
        int[] arrOrdenado = new int[0];
        /* Switch para seleccionar entre los dos algoritmos.
         * Nota: debe de haber una mejor implementacion que switch anidados.
         */
        switch(opcion){
            case "EXCHANGE":
                orden.printArreglo(arreglo, false);
                switch(opcion2){    
                    case "ASCENDENTE":
                        arrOrdenado = orden.exchangeSort(arreglo, true);
                        break;
                    case "DESCENDENTE":
                        arrOrdenado = orden.exchangeSort(arreglo, false);
                        break;
                }
                orden.printArreglo(arrOrdenado, true);
                break;
            case "BUBBLE":
                orden.printArreglo(arreglo, false);
                switch(opcion2){    
                    case "ASCENDENTE":
                        arrOrdenado = orden.bubbleSort(arreglo, true);
                        break;
                    case "DESCENDENTE":
                        arrOrdenado = orden.bubbleSort(arreglo, false);
                        break;
                }
                orden.printArreglo(arrOrdenado, true);
                break;
            default:
                System.out.println("Algoritmo de ordenamiento incorrecto, intenta de nuevo");
                break;
        }
    }
}