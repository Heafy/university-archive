import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Clase que contiene los algoritmos de busqueda:
 * - Busqueda Secuencial.
 * - Busqueda Binaria.
 * - Busqueda Exponencial.
 * - Busqueda por Interpolacion.
 * @author Jorge Martinez Flores.
 */
public class Search{

    /**
     * Metodo para mostrar el uso correcto del programa
     */
    private static void uso(){
        System.out.println("Uso: java Search file.txt numero algoritmo" + "\nO\n" +
                            "Uso: java Search aleatorio");
        System.exit(1);
    }

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
                    System.err.println("Error" + nfe);
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
     * Metodo para imprimir un arreglo
     * @param arreglo El arreglo a imprimir
     * @param b booleanopara determinar si el arreglo esta ordenado o no
     */
    public void printArreglo(int[] arreglo){
        for (int i = 0; i < arreglo.length; i++)
            System.out.println(String.format("A[%d] = ", i) + arreglo[i]);
    }

    /**
     * Metodo para mostrar el resultado con su indice
     * @param index El indice del arreglo
     */
    public void printResult(int index){
        if(index == -1)
            System.out.println("El elemento no se encuentra dentro del arreglo");
        else
            System.out.println("Elemento encontrado en el indice: " + index);
    }

    /**
     * Metodo para gener un arreglo aleatorio ordenado.
     * Genera un arreglo de longitud aleatoria (maximo 50 elementos).
     * Genera numeros aleatorios del rango (-10000, 10000)
     * @return Un arreglo aleatorio ordenado
     */
    public int[] generateRandom(){
        Random r = new Random();
        // Define la longitud aleatoria del arreglo
        int length = r.nextInt(50);
        // El rango de los numeros aleatorios
        int rango = 10000 - -10000 + 1;
        int[] arregloAux = new int[rango];
        // Se crea un arreglo que acumula los elementos aleatorios
        for (int i = 0; i < length; i++){
            arregloAux[r.nextInt(rango)]++;
        }
        int res = 0;
        int[] arreglo = new int[length];
        // Se ordenan los elementos 
        for(int i = 0; i < rango; i++){
            for(int j = 0; j < arregloAux[i]; i++)
                arreglo[res++] = i + (-10000);
        }
        return arreglo;
    }

    /**
     * Algoritmo de busqueda secuencial.
     * @param arreglo El arreglo donde se realiza la busqueda.
     * @param elemento El elemento del que se busca el indice.
     * @return El indice del arreglo donde esta el elemento.
     *         -1 Si el elemento no esta en el arreglo.
     */
    public int busquedaSecuencial(int[] arreglo, int elemento){
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i] == elemento)
                return i;
        }
        return -1;
    }

    /**
     * Algoritmo de busqueda binaria.
     * @param arreglo El arreglo donde se realiza la busqueda.
     * @param l indice menor
     * @param r indice mayor
     * @param elemento El elemento del que se busca el indice.
     * @return El indice del arreglo donde esta el elemento.
     *         -1 Si el elemento no esta en el arreglo.
     */
    public int busquedaBinaria(int[] arreglo, int l, int r, int elemento){
        if (r>=l){
            int mitad = l + (r - l)/2;
            // Si el elemento se encuentra en la mitad del arreglo.
            if (arreglo[mitad] == elemento)
               return mitad;
            // Si el elemento es menor que la mitad, esta en el
            // subarreglo izquierdo.
            if (arreglo[mitad] > elemento)
               return busquedaBinaria(arreglo, l, mitad-1, elemento);
            // En otro caso el elemento esta en el subarreglo izquierdo.
            return busquedaBinaria(arreglo, mitad+1, r, elemento);
        }
        // El elemento no se encuentra dentro del arreglo.
        return -1;
    }

    /**
     * Algoritmo de busqueda exponencial.
     * @param arreglo El arreglo donde se realiza la busqueda.
     * @param elemento El elemento del que se busca el indice.
     * @return El indice del arreglo donde esta el elemento.
     */
    public int busquedaExponencial(int[] arreglo, int elemento){
        if(arreglo[0] == elemento)
            return 0;
        // Encuentra el rango para la busqueda binaria.
        int i = 1;
        while(i < arreglo.length && arreglo[i] <= elemento)
            i = i*2;
        // Llama a la busqueda binaria.
        return busquedaBinaria(arreglo, i/2, Math.min(i, arreglo.length), elemento);
    }

    /**
     * TODO: REIMPLEMENTAR
     * Algoritmo de busqueda por interpolacion.
     * @param arreglo El arreglo donde se realiza la busqueda.
     * @param elemento El elemento del que se busca el indice.
     * @return El indice del arreglo donde esta el elemento.
     */
    public int busquedaInterpolacion(int[] arreglo, int elemento){
        int izq = 0;
        int der = (arreglo.length-1);
        /* Como el arreglo esta ordenado, el elemento debe estar
         * en cualquier lugar dentro de los limites del arreglo. */
        while(izq <= der && elemento >= arreglo[izq] && elemento <= arreglo[der]){
            /* Asigna la posicion inicia. */
            int pos = izq + (((der - izq) / 
                (arreglo[der] - arreglo[izq]))*(elemento - arreglo[izq]));
            /* Encuentra el elemento en el indice. */
            if(arreglo[pos] == elemento)
                return pos;
            /* Si el elemento es mayor, esta en la parte superior. */
            if(arreglo[pos] < elemento)
                izq = pos + 1;
            /* Si el elemento es mayor, esta en la parte inferior. */
            else
                der = pos -1;
        }
        return -1;
    }

    /**
     * Metodo main
     * @param args Arreglo de parametos.
     */
    public static void main(String[] args) {
        if(args.length == 0 || args.length == 2 || args.length > 3)
            uso();

        Search search = new Search();
        Lector lector = new Search().new Lector();
        
        int[] arreglo;
        // Crea un arreglo aleatorio.
        if(args[0].toUpperCase().equals("ALEATORIO")){
            arreglo = search.generateRandom();
            search.printArreglo(arreglo);
            System.exit(1);
        // Se crea el arreglo a partir del archivo.
        }else{       
            arreglo = lector.leerArchivo(args[0]);
        }
        // El elemento a buscar dado por el usuario. 
        int elemento = Integer.parseInt(args[1]);
        // El algoritmo a utilizar dado por el usuario.
        String opcion = args[2].toUpperCase();
        // El indice encontrado con el algoritmo. 
        int index = 0;
        search.printArreglo(arreglo);
        switch(opcion){
            case "SECUENCIAL":
                index = search.busquedaSecuencial(arreglo, elemento);
                break;
            case "BINARIA":
                index = search.busquedaBinaria(arreglo, 0, arreglo.length, elemento);
                break;
            case "EXPONENCIAL":
                index = search.busquedaExponencial(arreglo, elemento);
                break;
            case "INTERPOLACION":
                index = search.busquedaInterpolacion(arreglo, elemento);
                break;
            default:
                System.out.println("Opcion incorrecta, intenta con otros valores");
                System.exit(1);
                break;
        }
        // Imprime el resultado de la busqueda. 
        search.printResult(index); 
    }

}