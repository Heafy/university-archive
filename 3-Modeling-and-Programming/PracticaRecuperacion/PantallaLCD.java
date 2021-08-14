import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

// ESTOY SEGURO QUE HAY UNA MEJOR MANERA DE HACER TODO ESTO
public class PantallaLCD{

    public int [][][] lcd = new int [10][][];

    /* Constructor unico para la clase
     * Creamos numeros con matrices que crecen para la entrada deseada
     */
    public PantallaLCD() {
        /* Numero cero*/
        int[][] zero = {
                {0,2,0},
                {1,0,1},
                {0,0,0},
                {1,0,1},
                {0,2,0},
        };
        /* Numero uno*/
        int[][] one = {
                {0,0,0},
                {0,0,1},
                {0,0,0},
                {0,0,1},
                {0,0,0},
        };
        /* Numero dos*/
        int[][] two = {
                {0,2,0},
                {0,0,1},
                {0,2,0},
                {1,0,0},
                {0,2,0},
        };
        /* Numero tres*/
        int[][] three = {
                {0,2,0},
                {0,0,1},
                {0,2,0},
                {0,0,1},
                {0,2,0},
        };
        /* Numero cuatro*/
        int[][] four = {
                {0,0,0},
                {1,0,1},
                {0,2,0},
                {0,0,1},
                {0,0,0},
        };
        /* Numero cinco*/
        int[][] five = {
                {0,2,0},
                {1,0,0},
                {0,2,0},
                {0,0,1},
                {0,2,0},
        };
        /* Numero seis*/
        int[][] six = {
                {0,2,0},
                {1,0,0},
                {0,2,0},
                {1,0,1},
                {0,2,0},
        };
        /* Numero siete*/
        int[][] seven = {
                {0,2,0},
                {0,0,1},
                {0,0,0},
                {0,0,1},
                {0,0,0},
        };
        /* Numero ocho*/
        int[][] eight = {
                {0,2,0},
                {1,0,1},
                {0,2,0},
                {1,0,1},
                {0,2,0},
        };
        /* Numero nueve*/
        int[][] nine = {
                {0,2,0},
                {1,0,1},
                {0,2,0},
                {0,0,1},
                {0,2,0},
        };

        /* Los asignamos al arreglo */
        lcd[0]=zero;
        lcd[1]=one;
        lcd[2]=two;
        lcd[3]=three;
        lcd[4]=four;
        lcd[5]=five;
        lcd[6]=six;
        lcd[7]=seven;
        lcd[8]=eight;
        lcd[9]=nine;
    }

    public void printFila(int dimension, StringBuffer sb, boolean pmf) {
        if(pmf){
           System.out.println(sb);
        }else {
            for(int i =0 ; i <dimension;i++)
                System.out.println(sb);
        }
    }

      /* Auxiliar para manejar vacios*/
    public void esVacio(int dimension, StringBuffer sb, int columna) {
        if(!esPrimUltCol(columna)){
            for(int i =0 ; i <dimension;i++)
                sb.append(" ");
        }else {
            sb.append(" ");
        }
    }

    /* Metodo para crear los guiones y datos que dan forma a los numeros*/
    public void cambiaDatos(int dimension, StringBuffer sb, boolean pmf, int columna, int dato) {
        if(pmf){
            if(dato == 0){
                esVacio(dimension, sb, columna);
            }else if (dato == 2){
               for(int i =0 ; i <dimension;i++)
                   sb.append("-");
            }
        }else {
            if(dato == 0){
                esVacio(dimension, sb, columna);
            }else if (dato == 1){
                sb.append("|");
            }
        }
    }

  

    /* Verifica que este entre el principio medio o el final la fila*/
    public boolean esPrimMedioFInal(int fila) {
        if(fila==0 || fila == 4 || fila == 2)
            return true;
        else
            return false;
    }
    
    /* Metodo booleando para saber cuando es la primera o ultima columnaa
     * de los numeros*/
    public boolean esPrimUltCol(int col){
        if(col == 0 || col==2)
            return true;
        else
            return false;
    }

    public int[][][] getLCDDatos(String number) {
        int[][][] result = new int[number.length()][][];
        for(int i = 0; i<number.length();i++){
            result[i] = lcd[Character.getNumericValue(number.charAt(i))];
        }
        return result;
    }

    /* Lee las lineas del archivo */
    public static String readLn(int maxlongitud) {
        byte lin[] = new byte[maxlongitud];
        int longitud = 0;
        int aux = -1;
        String line = "";

        try {
            while (longitud < maxlongitud) {
                aux = System.in.read();
                if ((aux < 0) || (aux == '\n')) break;
                lin[longitud++] += aux;
            }
        } catch (IOException e) {
            return (null);
        }

        if ((aux < 0) && (longitud == 0))
            return (null); //Fin del programa
        return (new String(lin, 0, longitud));
    }

    public void numeroLCD(int[][][] number,int dimension){        
        for(int fila=0;fila<5;fila++){     
            StringBuffer sb = new StringBuffer();
            boolean pmf = esPrimMedioFInal(fila);
            for (int digito = 0; digito < number.length;digito++){
                for(int columna=0;columna<3;columna++){
                    int dato = number[digito][fila][columna];
                    cambiaDatos(dimension, sb, pmf, columna, dato);
                }
             if(digito != number.length -1)
                sb.append(" "); //nos da una columna vacia al terminar cada digitoo
            }
            printFila(dimension, sb, pmf);
        }
    }

    /* El algoritmo principal*/
    public void solucion() {
        System.out.println("Introduce un numero seguido de la cadena de numeros");
        String input;
        while ((input = PantallaLCD.readLn(255)) != null){
            if(input.trim().equals("0 0"))
                break; //Fin del programa
            String[] numbers = input.trim().trim().split("\\s+"); 
            String dimension = numbers[0];
            String number = numbers[1];
            numeroLCD(getLCDDatos(number),Integer.parseInt(dimension));
            System.out.println();//Una linea vacia tras cada numero
        }
    }

    public static void main (String args[])
    {
        PantallaLCD PantallaLCD = new PantallaLCD();
        PantallaLCD.solucion();
    }

}