import java.io.*;
import java.util.*;

/**
 * Clase para encontrat todas las minas
 * dibujadas en un campo.
 * @autor Jorge Martinez 
 */
public class Buscaminas{
    
	public void cuentaMinas(int fila, int columna, int matriz[][]){
		char res;			
	
		for(int i=1; i<=fila; i++){
			for(int j=1; j<=columna; j++){
				if (matriz[i][j] == 1)
					res = '*';
				else
					/* Si el elemento es distinto de una mina hace los conteos alrededor
					 * de ellas para asignarle en número correcto. */
					res = Integer.toString(matriz[i-1][j] + matriz[i-1][j-1] + matriz[i-1][j+1] + matriz[i][j-1] + matriz[i][j+1]+matriz[i+1][j-1]+matriz[i+1][j]+matriz[i+1][j+1]).charAt(0);
				System.out.print(res);
			}
			System.out.println();// Util para los saltos de linea
		}		
	}

    public void lee(String nombreArchivo) {
    	try {
			int numField = 1;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreArchivo)));
			String entradaLinea;

			/* Lee mientras la linea que reciba sean diferente de null. */
			while ((entradaLinea = br.readLine( )) != null) {	
				String a[] = entradaLinea.trim().split("\\s+");
				int n = Integer.parseInt(a[0]);
				int m = Integer.parseInt(a[1]);
				int matriz[][] = new int[n+2][m+2];
				/* Verifica que los enteros sean diferentes de 0 para contar las minas*/
				if ((n!=0) && (m!=0)){
					for(int i=0; i<n+2;i++){
						for(int j=0; j<m+2;j++){
							matriz[i][j] = 0;
						} // for j
					} // for i
					for (int i =1; i <= n; i++){
						String fila = br.readLine();
						fila = fila.trim();
						for(int j=1;j<= m;j++){
							if (fila.charAt(j-1) == '*')
								matriz[i][j] = 1;						
						} // for j
					} // for i
					/* Imprimimos la primera parte */
					if (numField == 1)
						System.out.println("Field #"+numField++ + ":");
					else
						/* Los demas campos diferentes del primero llevan un salto
					 	* de linea respecto al campo anterior */
						System.out.println("\nField #"+numField++ + ":");
						/* El método se encarga de imprimir los numeros */
					cuentaMinas(n, m, matriz);
				}
			}//while	
			br.close( );
		} catch (Exception e) {
			System.out.println(e);
		}
    }

    public static void main(String args[]){
    	if (args.length != 1){
    		System.out.println("Uso: java Buscaminas <Archivo de texto>");
    		System.exit(1);
    	}
        String archivoTexto = args[0];
    	Buscaminas buscaminas = new Buscaminas();
        buscaminas.lee(archivoTexto);
    }
}
