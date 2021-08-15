import java.util.ArrayList;
import java.util.List;

/**
 * Clase Arbol
 * Un nodo es un conjunto de arboles.
 */
class Arbol<Tablero> {
    
    List<Arbol<Tablero>> hijo = new ArrayList<Arbol<Tablero>>();
    Arbol<Tablero> padre = null;
    Tablero tablero = null;
      
    //Esta variable es el valor que nos devolverá Minimax para escoger
    // un nodo.
    float valor=0;

    /**
     * Constructor de un árbol.
     * Dado un tablero construye un nodo del arbol.
     */
    public Arbol(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Constructor dado un tablero y un árbol padre.
     */
    public Arbol(Tablero tablero, Arbol<Tablero> padre) {
        this.tablero = tablero;
        this.padre = padre;
    }

    /**
     * Metodo para obtener el hijo de un arbol.
     * @return  El hijo del nodo.
     */
    public List<Arbol<Tablero>> getHijo() {
        return hijo;
    }

    /**
     * Metodo para modificar el padre de un árbol.
     * @param padre El árbol padre.
     */
    public void setPadre(Arbol<Tablero> padre) {
        padre.agregaHijo(this);
        this.padre = padre;
    }

    /**
     * Metodo para agregarle un hijo a un nodo.
     * @param  tablero El tablero a agregar al árbol.
     */
    public void agregaHijo(Tablero tablero) {
        Arbol<Tablero> hijo = new Arbol<Tablero>(tablero);
        hijo.setPadre(this);
    }

    /**
     * Metodo 2 para agregarle un hijo a un nodo.
     * Se ocupa en Arbol.setPadre()
     * @param hijo el hijo al cual agregar al árbol. 
     */
    public void agregaHijo(Arbol<Tablero> h) {
        this.hijo.add(h);
        h.padre = this;
    }

    /**
     * Metodo para obtener el tablero de un nodo.
     * @return el tablero del árbol.
     */
    public Tablero getTablero() {
        return this.tablero;
    }

    /**
     * Metodo para saber si un nodo es raiz.
     * @return true si el árbol no tiene padre.
     *         false en otro caso.
     */
    public boolean esRaiz() {
        return (this.padre == null);
    }

    /**
     * Metodo para saber si un nodo es una hoja.
     * @return  true si el árbol no tiene hijo.
     *          false en otro caso.
     */
    public boolean esHoja() {
        if(this.hijo.size() == 0) 
            return true;
        else 
            return false;
    }
    
    /**
      * Método para devolver el valor del nodo.
      * @return el valor del árbol.
      */
    public float getValor(){
      return valor;
    }
}
