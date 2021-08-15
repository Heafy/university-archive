package algorithm;

/**
 * Clase para representar una cuadricula.
 * Una cuadrícula es una matriz de celdas.
 * @author Jorge Martinez Flores
 */
public class Cuadricula {
    
    /*Tamaño de celdas a lo ancho de la cuadrícula.*/
    int ancho;
    /*Tamaño de celdas a lo largo de la cuadrícula.*/
    int alto;
    /* Tamaño en pixeles de cada celda. */
    int tamanio;
    /* Mundo de celdas (Cuadricula del laberinto). */
    Celda[][] matriz;
    
    /**
     * Constructor del modelo
     *
     * @param ancho Cantidad de celdas a lo ancho en la cuadricula.
     * @param alto Cantidad de celdas a lo alto de la cuadricula.
     * @param tamanio Tamaño (en pixeles) de cada celda cuadrada que compone
     * la cuadricula.
     */
    public Cuadricula(int ancho, int alto, int tamanio) {
        this.ancho = ancho;
        this.alto = alto;
        this.tamanio = tamanio;
        matriz = new Celda[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                matriz[i][j] = new Celda(0, 0, 0);
            }
        }
    }
    
    /**
     * Metodo para dibujar la cuadricula.
     * Lee cada casilla del terreno y le dibuja un color en base
     * al identificador encontrado.
     * @param terreno El terreno a leer 
     */
    public void drawCopy(char[][] terreno){
        for(int i=0;i<terreno.length;i++){
            for(int j=0;j<terreno.length;j++){
                switch(terreno[j][i]){
                    // Cuadricula especial, color blanco
                    case 'X': 
                        matriz[i][j].setColor(255, 255, 255);
                        break;
                    // Color ladrillo
                    case 'Y': 
                        matriz[i][j].setColor(178, 34, 34);
                        break;
                    // Color verde
                    case 'A': 
                        matriz[i][j].setColor(0, 255, 0);
                        break;
                    // Color azul
                    case 'B': 
                        matriz[i][j].setColor(0, 0, 255);
                        break;
                    // Color amarillo
                    case 'C': 
                        matriz[i][j].setColor(255, 255, 0);
                        break;
                    // Color cyan
                    case 'D': 
                        matriz[i][j].setColor(0, 255, 255);
                        break;
                    // Color magenta
                    case 'E': 
                        matriz[i][j].setColor(255, 0, 255);
                        break;
                    // Color gris
                    case 'F': 
                        matriz[i][j].setColor(128, 128, 128);
                        break;
                    // Color marron
                    case 'G': 
                        matriz[i][j].setColor(128, 0, 0);
                        break;
                    // Color arena
                    case 'H': 
                        matriz[i][j].setColor(139, 69, 19);
                        break;
                    // Color rojo
                    case 'I': 
                        matriz[i][j].setColor(255, 0, 0);
                        break;
                    // Color purpura
                    case 'J': 
                        matriz[i][j].setColor(128, 0, 128);
                        break;
                    // Color teal
                    case 'K': 
                        matriz[i][j].setColor(0, 128, 128);
                        break;
                    // Color navy
                    case 'L': 
                        matriz[i][j].setColor(0, 0, 128);
                        break;
                    // Color naranja
                    case 'M': 
                        matriz[i][j].setColor(266, 69, 0);
                        break;
                }
            }
        }
    }
}
