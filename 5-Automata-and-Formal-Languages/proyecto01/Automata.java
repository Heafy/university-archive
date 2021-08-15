import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Proyecto 1: Construccion de un Automata Finito Determinista
 *  @author: Jorge Yael Martinez Flores
 */
public class Automata{

	public static void main(String[] args) {
		/* Imprime un mensaje de como utilizar el programa */
		if (args.length != 1){
			System.out.println("Uso: java Automata (archivo con las entradas)");
        	System.exit(1);
		}

		String[] arrArchivo = null; // Arreglo para leer el archivo

		/* Lee el archivo pasado como parametro linea por linea
		 * y cada linea lo asigna a un elemento en una lista
		 * Posteriormente la lista se convierte en un arreglo */
		try{
			BufferedReader bR = new BufferedReader(new FileReader(args[0]));
			String linea;

			List<String> lista = new ArrayList<String>();
			while((linea = bR.readLine()) != null){
	    	lista.add(linea);
			}
			arrArchivo = lista.toArray(new String[0]);
		}catch(FileNotFoundException fnfe){
			System.out.println("No se encontro el archivo de texto\nEl programa termino");
			System.exit(0);
		}catch(IOException ioe){
			System.out.println("Hubo un error leyendo el archivo de texto\nEl programa termino");
			System.exit(0);
		}

		int numEstados; // Variable para el numero de estados
		int posAux; // Variable auxiliar

		String abc; // Variable auxiliar para el alfabeto
		String[] alfabeto; // El arreglo para el alfabeto
		String[] estadosFinales; // El arreglo para los estados finales
		String[] estadosFinalesMarcados; // Arreglo auxiliar para los estados finales

		int filas; // Variable para las filas de la tabla de transiciones
		int columnas; // Variable para las columnas de la tabla de transiciones
		String[][] tabla; // Arreglo de dos dimensiones para la tabla de transiciones
		
		/* Creacion del Scanner */
		Scanner scanner = new Scanner(System.in);

		/* Se llena el numero de estados */
		numEstados = Integer.parseInt(arrArchivo[0]);

		/* Se llena el alfabeto con la linea correspondiente
		 * Es necesaria una cadena con el alfabeto para comparaciones posteriores */
		abc = arrArchivo[1];
		alfabeto = abc.trim().split(",");	// El alfabeto recibido se separa a un arreglo de cadenas

		/* Se llenan los estados finales con la linea correspondiente */
		estadosFinalesMarcados = arrArchivo[2].trim().split(",");

		/* Se llenan los estados finales con un 0*/
		estadosFinales = new String[numEstados];
		for (int i = 0; i < estadosFinales.length; i++)
			estadosFinales[i] = "0";

		/* Con ayuda de un auxiliar toma los enteros marcados como finales
		 * y los pasa en el arreglo de estados finales y los marca como finales */
		for (int i = 0; i < estadosFinalesMarcados.length; i++){
			posAux = Integer.parseInt(estadosFinalesMarcados[i]);
			estadosFinales[posAux-1] = "1";
		}

		/* El numero de filas es el numero de estados mas la fila superior con
		 * los encabezados de cada columna de la tabla */
		filas = numEstados +1;
		/* El numero de columnas es la longitud del arreglo del alfabeto mas
		 * La columna de los estados y de los simbolos finales*/
		columnas = alfabeto.length + 2; 

		/* Dada la informacion que ya se ha obtenido se crea la tabla con
		 * las filas y columnas correspondientes */
		tabla = new String[filas][columnas];

		/* Llena los espacios de toda la tabla con un guion 
		 * Util tanto para el formato como para que no haya referencias vacias*/
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++)
				tabla[i][j] = "-";
		}

		/* Notacion para la primera fila */
		tabla[0][0] = "Q";
		tabla[0][columnas-1] = "F";

		/* Llena la primera fila de la tabla con los elementos del alfabeto */		
		for(int i = 1; i < columnas-1; i++)
			tabla[0][i] = alfabeto[i-1];

		/* Llena la primera columna con los estados */
		for(int i = 1; i < filas; i++)
			tabla[i][0] = String.valueOf(i);

		/* Llena la ultima columna con los estados que son finales*/
		for(int i = 0; i < estadosFinales.length; i++)
			tabla[i+1][columnas-1] = estadosFinales[i]; 

		String[] transiciones;
		/* Se pide para todos los estados */
		for (int i = 3; i < arrArchivo.length; i++){
			transiciones = arrArchivo[i].trim().split(" "); // Se separan en un arreglo de caracteres
			posAux = Integer.parseInt(transiciones[0]); // Entero auxiliar, es el numero del estado i
			for(int j = 1; j < filas; j++){ // Busca el estado en la tabla
				// Si encuentra el estado sucede el if	
				if(posAux == Integer.parseInt(tabla[j][0])){
					for (int k = 1; k < transiciones.length; k++) { 
						tabla[j][k] = transiciones[k]; // Llena la tabla con las transiciones para cada estado
					}
					break;				
				}
			}
		}

		/* Imprime la tabla con el formato establecido */
		System.out.println("\nTABLA DE TRANSICIONES");
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++){
				System.out.print(tabla[i][j] + " | ");
			}
			System.out.println();
		}

		/* Ciclo para pedir un numero arbitrario de cadenas*/
		boolean otra;
		String opcion;
		do{
			/* Recibe la cadena */
			System.out.println("Introduce una cadena: ");
			String cadena = scanner.nextLine();
			//char[] cadena = cad.toCharArray();
			
			/* Revisa que la cadena contenga caracteres que exlusivamente
			 * esten en el alfabeto.
			 * En otro caso el programa termina */
			for (int i = 0; i < cadena.length(); i++) {
				if(!(abc.contains(String.valueOf(cadena.charAt(i))))){
					System.out.println("La cadena contiene caracteres que no estan en el alfabeto");
					System.out.println("El programa termino.");
					System.exit(1);
				}	
			}

			int posChar = 0; // Numero auxiliar para la posicion del caracter
			int posEdo = Integer.valueOf(tabla[1][0]); // Numero con el estado que lee, empieza en el primero

			/* Recore la cadena obtenida caracter por caracter hasta que 
			 * no queden caracteres */
			for (int i = 0; i < cadena.length(); i++){
				for (int j = 1; j < columnas-1; j++){
					if(String.valueOf(cadena.charAt(i)).equals(tabla[0][j])){ // Cuando encuentra el caracter en la tabla
						//System.out.println("Caracter leido: " + String.valueOf(cadena.charAt(i)));
						//System.out.println("posicion: " + j);
						posChar = j;
						break;
					}
				}
				posEdo = Integer.valueOf(tabla[posEdo][posChar]); // Se posiciona en el estado siguiente
				/*
				 * Codigo para pruebas.
				 * Quitar los comentarios mostrara las transiciones y las posiciones de los estados.
				 *
				System.out.println("Posicion estado: [" + posEdo + "] [" + posChar + "]" + 
									"Caracter consumido:" + cadena.charAt(i) + 
									"Estado al que va: " + tabla[posEdo][0] + 
									"Estado final: " + tabla[posEdo][columnas-1] + "\n");
				 */
			}

			
			System.out.println("Cadena introducida: "+ cadena);
			if((tabla[posEdo][columnas-1]).equals("1")){
				System.out.println("El automata acepta la cadena");
			}else{
				System.out.println("El automata no acepta la cadena");
			}

			
			System.out.println("Quieres introducir otra cadena? (Si/No)");
			opcion = scanner.nextLine();
			opcion = opcion.toLowerCase();
			if(opcion.equals("si"))
				otra = true;
			else if(opcion.equals("no"))
				otra = false;
			else{
				otra = false;
				System.out.println("Opcion incorrecta");
			}
		} while(otra);
		System.out.println("El programa termino.");
	}
}