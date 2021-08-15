package algorithm;

import processing.core.PApplet;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Interfaz Grafica para el problema del adoquinamiento.
 * @author Kike
 * @author Jorge Martinez Flores
 */
public class Adoquinamiento extends PApplet {
    /* Longitud de la cuadricula (k x k). */
    int k = 0;
    /* Coordenada x del adoquin especial. */
    int x = 0;
    /* Coordenada y del adoquin especial. */
    int y = 0;
    /* Tamaño de cada celda cuadrada (en pixeles). */
    int celda = 35;
    /* El objeto que representa la cuadricula. */
    Cuadricula modelo;
    /* Mundo de celdas (Cuadricula del laberinto). */
    Celda[][] matriz;
    /* Scanner para la entrada de datos. */
    Scanner scanner = new Scanner(System.in);
    
    /**
     * Metodo para definiar las variables a utilizar y darle dimension
     * a la cuadricula.
     */
    @Override
    public void settings() {
         try{
             System.out.print("Introduzca la longitud de los lados de la cuadricula (k)\n"+
                                "Recuerda que k tiene que ser potencia de dos: ");  
            k=scanner.nextInt();
            // Revisa si elnumero es potencia de 2
            if(!((k & -k) == k)){
                System.out.println("El valor introducido no es potencia de dos.\n" + 
                                    "Intenta con un numero adecuado.");
                System.exit(0);
            }
            System.out.print("Introduzca la coordenada x del adoquin especial(de 0 a " + (k-1) + "): ");
            x=scanner.nextInt();
            if(x >= k){
                System.out.println("El valor introducido es mayor a la longitud de la cuadricula.\n" + 
                                    "Intenta con un numero adecuado.");
                System.exit(0);
            }
            System.out.print("Introduzca la coordenada y del adoquin especial(de 0 a " + (k-1) + "): ");
            y=scanner.nextInt();
            if(y >= k){
                System.out.println("El valor introducido es mayor a la longitud de la cuadricula.\n" + 
                                    "Intenta con un numero adecuado.");
                System.exit(0);
            }
        }catch(InputMismatchException ime){
            System.out.println("Intenta correr de nuevo el programa con una entrada valida.");
            System.exit(0);
        }
        System.out.print("Región Generada:");
        size(k*celda, k*celda);
    }

    /**
     * Metodo para iniciar la cuadricula, ademas de que
     * se llama el algoritmo del adoquinamiento.
     */
    @Override
    public void setup() {
        frameRate(15); //Ajusta velocidad de iteraciones por segundo     
        Adoquin terreno = new Adoquin(k);
        terreno.fillTerreno(x, y, 0, k-1, 0, k-1);
        modelo = new Cuadricula(k, k, celda);
        modelo.drawCopy(terreno.terreno);
    }

    /**
     * Pinta el matriz del modelo.
     */
    @Override
    public void draw() {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                fill(modelo.matriz[i][j].r, modelo.matriz[i][j].g, modelo.matriz[i][j].b);
                rect(j * modelo.tamanio, i * modelo.tamanio, modelo.tamanio, modelo.tamanio);

                stroke(255); //Paredes (Blancas)
                line(j * modelo.tamanio, i * modelo.tamanio, j * modelo.tamanio, ((i + 1) * modelo.tamanio));
                line(j * modelo.tamanio, i * modelo.tamanio, ((j + 1) * modelo.tamanio), i * modelo.tamanio);
                line((j * modelo.tamanio) + modelo.tamanio, i * modelo.tamanio, (j + 1) * modelo.tamanio, (((i + 1) * modelo.tamanio)));
                line(j * modelo.tamanio, (i * modelo.tamanio) + modelo.tamanio, ((j + 1) * modelo.tamanio), ((i + 1) * modelo.tamanio));
            }
        }
    }

    /**
     * Metodo main a ejecutar.
     * @param args 
     */
    public static void main(String args[]) {
        PApplet.main(new String[]{"algorithm.Adoquinamiento"});
    }
}