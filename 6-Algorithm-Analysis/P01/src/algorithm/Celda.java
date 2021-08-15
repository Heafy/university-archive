package algorithm;

/**
 * Clase para representar una celda.
 * Una celda es una representacion de un cuadrado coloreado.
 * Una cuadricula esta compuesta por celdas.
 * @author Jorge Martinez Flores
 */
public class Celda {
    
    /* Variables para declarar colores en RGB */
    int r;
    int g; 
    int b;

    /**
     * Constructor de una celda con su color en RGB
     * @param r Color rojo
     * @param g Color verde
     * @param b Color azul
     */
    public Celda(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Metodo para colorear una celda con RGB
     * @param r Intensidad del color rojo
     * @param g Intensidad del color verde
     * @param b Intensidad del color azul
     */
    public void setColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
}
