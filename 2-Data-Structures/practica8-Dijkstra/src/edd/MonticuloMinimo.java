package edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para montículos mínimos (<i>min heaps</i>). Podemos crear un montículo
 * mínimo con <em>n</em> elementos en tiempo <em>O</em>(<em>n</em>), y podemos
 * agregar y actualizar elementos en tiempo <em>O</em>(log <em>n</em>). Eliminar
 * el elemento mínimo también nos toma tiempo <em>O</em>(log <em>n</em>).
 */
public class MonticuloMinimo<T extends ComparableIndexable<T>>
    implements Coleccion<T> {

    /* Clase privada para iteradores de montículos. */
    private class Iterador<T extends ComparableIndexable<T>>
        implements Iterator<T> {

        /* Índice del iterador. */
        private int indice;
        /* El montículo mínimo. */
        private MonticuloMinimo<T> monticulo;

        /* Construye un nuevo iterador, auxiliándose del montículo mínimo. */
        public Iterador(MonticuloMinimo<T> monticulo) {
            // Aquí va su código.
            this.monticulo = monticulo;
            indice = 0;
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            // Aquí va su código.
            if(indice < monticulo.siguiente)
                return true;
            return false;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            // Aquí va su código.
            return monticulo.arbol[indice ++];
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* El siguiente índice dónde agregar un elemento. */
    private int siguiente;
    /* Usamos un truco para poder utilizar arreglos genéricos. */
    private T[] arbol;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new ComparableIndexable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #MonticuloMinimo(Lista)}, pero se ofrece este constructor por completez.
     */
    public MonticuloMinimo() {
        // Aquí va su código.
        arbol = creaArregloGenerico(100);
        siguiente = 0;
    }

    /**
     * Constructor para montículo mínimo que recibe una lista. Es más barato
     * construir un montículo con todos sus elementos de antemano (tiempo
     * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
     * <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param lista la lista a partir de la cuál queremos construir el
     *              montículo.
     */
    public MonticuloMinimo(Lista<T> lista) {
        // Aquí va su código.
        siguiente = lista.getLongitud();
        arbol = creaArregloGenerico(siguiente);
        int j = 0;
        for(T elemento : lista){
            arbol[j] = elemento;
            arbol[j].setIndice(j);
            j++;
        }
        for(int a = siguiente/2; a>= 0; a--){
            int b = a;
            int c = auxiMin(a);
            while(c != b){
                T auxiliar = arbol[b];
                arbol[b] = arbol[c];
                arbol[b].setIndice(b);
                arbol[c] = auxiliar;
                arbol[c].setIndice(c);
                b = c;
                c = auxiMin(b);
            }  
       }
    }

    //Método Auxiliar para obtener el índice mínimo
   
    private int auxiMin(int i){ 
        int izquierdo = (2*i)+1;
        int derecho = izquierdo + 1;
        int min;
        if(izquierdo >= siguiente)
            return i;
        if(arbol[i].compareTo(arbol[izquierdo]) < 0){
            min = i;
        } else {
            min = izquierdo;
        }
        if(derecho < siguiente){
            if(arbol[min].compareTo(arbol[derecho]) > 0){
                min = derecho;
            }    
        }
        return min;
    }

    /**
     * Agrega un nuevo elemento en el montículo.
     * @param elemento el elemento a agregar en el montículo.
     */
    @Override public void agrega(T elemento) {
        // Aquí va su código.
        if(siguiente + 1 > arbol.length){
          T [] arbolAux = creaArregloGenerico((arbol.length+1)*2);
       for(int j= 0; j< siguiente; j++){
           arbolAux[j] = arbol[j];      
       }
           arbol = arbolAux;
       }
       arbol[siguiente] = elemento;
       arbol[siguiente].setIndice(siguiente);
       siguiente++;
       reordena(elemento);
    }

    /**
     * Elimina el elemento mínimo del montículo.
     * @return el elemento mínimo del montículo.
     * @throws IllegalStateException si el montículo es vacío.
     */
    public T elimina() {
        // Aquí va su código.
        if(esVacio())
            throw new IllegalStateException();
        T elemento = arbol[0];
        arbol[0] = arbol[siguiente-1];
        arbol[0].setIndice(0);
        auxiElimina(0);
        siguiente--;
        elemento.setIndice(-1);
        return elemento;
    }

    private void auxiElimina(int i) {
        int m = minimo(i,(2*i) + 1,(2*i) + 2);
        if(m == i) {
            return;
        } 
        intercambia(i, m);
        auxiElimina(m);
    }

    private int minimo(int i, int izquierdo, int derecho){
      int minimo;
      if (siguiente > derecho){
          if (arbol[derecho].compareTo(arbol[izquierdo]) < 0){
              if (arbol[derecho].compareTo(arbol[i]) < 0)
                  return derecho;
              return i;  
          }
          else if (arbol[izquierdo].compareTo(arbol[i]) < 0)
              return izquierdo;
          return i;
       }
       else if (siguiente > izquierdo)
            if (arbol[izquierdo].compareTo(arbol[i]) < 0)
            return izquierdo;
       return i;
   }    

    /**
     * Elimina un elemento del montículo.
     * @param elemento a eliminar del montículo.
     */
    @Override public void elimina(T elemento) {
        // Aquí va su código.
        int i = elemento.getIndice();
        if (i < 0 || i >= siguiente || !arbol[i].equals(elemento))
            return;
        while (elemento.getIndice() < siguiente) {
            int izq = 2 * elemento.getIndice() + 1;
            int der = 2 * elemento.getIndice() + 2;
            if (izq < siguiente && der < siguiente) {
                if (arbol[izq].compareTo(arbol[der]) < 0) {
                    intercambia(elemento.getIndice(), izq);
                } else {
                    intercambia(elemento.getIndice(), der);
                }
            } else if (izq < siguiente) {
                intercambia(elemento.getIndice(), izq);
            } else if (der < siguiente) {
                intercambia(elemento.getIndice(), der);
                i = der;
            } else {
                break;
            }
        }
        i = elemento.getIndice();
        int ultimo = siguiente - 1;
        if (i != ultimo) {
            intercambia(i, ultimo);
            reordena(arbol[i]);
        }
        arbol[ultimo] = null;
        siguiente--;
        elemento.setIndice(-1);
    }

    // Método Auxiliar intercambia
    
    private void intercambia(int i, int j){
        T t = arbol[i];
        arbol[i] = arbol[j];
        arbol[j] = t;
        arbol[i].setIndice(i);
        arbol[j].setIndice(j);
    }
    
    /**
     * Nos dice si un elemento está contenido en el montículo.
     * @param elemento el elemento que queremos saber si está contenido.
     * @return <code>true</code> si el elemento está contenido,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        // Aquí va su código.
        for(T e : arbol)
            if (elemento.equals(e))
                return true;
        return false;
    }

    /**
     * Nos dice si el montículo es vacío.
     * @return <tt>true</tt> si ya no hay elementos en el montículo,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacio() {
        // Aquí va su código.
       return siguiente == 0;
    }

   /**
     * Reordena un elemento en el árbol.
     * @param elemento el elemento que hay que reordenar.
     */
    public void reordena(T elemento) {
        // Aquí va su código.
        int i = elemento.getIndice();
        int p = (i-1)/2;
        while(p >= 0 && arbol[p].compareTo(arbol[i]) > 0){
          T padre = arbol[p];
          arbol[p] = arbol[i];
          arbol[p].setIndice(p);
          arbol[i] = padre;
          arbol[i].setIndice(i);
          i = p;
          p = (i-1)/2;
        }
    }

    /**
     * Regresa el número de elementos en el montículo mínimo.
     * @return el número de elementos en el montículo mínimo.
     */
    @Override public int getElementos() {
        // Aquí va su código.
        return siguiente;
    }

    /**
     * Regresa el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @param i el índice del elemento que queremos, en <em>in-order</em>.
     * @return el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @throws NoSuchElementException si i es menor que cero, o mayor o igual
     *         que el número de elementos.
     */
    public T get(int i) {
        // Aquí va su código.
         if(i< 0 || i>= siguiente)
           throw new NoSuchElementException();
         return arbol[i]; 
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador<T>(this);
    }
}
