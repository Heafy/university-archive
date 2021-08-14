package edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase privada para iteradores de árboles binarios ordenados. */
    private class Iterador implements Iterator<T> {

        /* Pila para emular la pila de ejecución. */
        private Pila<ArbolBinario<T>.Vertice> pila;

        private void hijoi(ArbolBinario<T>.Vertice v){
            while(v != null){
                pila.mete(v);
                v = v.izquierdo;
            }
        }

        /* Construye un iterador con el vértice recibido. */
        public Iterador(ArbolBinario<T>.Vertice vertice) {
            pila = new Pila<ArbolBinario<T>.Vertice>();
            if(vertice != null)
                hijoi(vertice);
		}

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            if(pila.esVacia())
                return false;
            return true;
  
        }

        /* Regresa el siguiente elemento del árbol en orden. */
        @Override public T next() {
            ArbolBinario<T>.Vertice v = pila.saca();
            hijoi(v.derecho);
            return v.elemento;
	   }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Constructor sin parámetros. Sencillamente ejecuta el constructor sin
     * parámetros de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de un árbol binario. El
     * árbol binario ordenado tiene los mismos elementos que el árbol recibido,
     * pero ordenados.
     * @param arbol el árbol binario a partir del cuál creamos el
     *        árbol binario ordenado.
     */
    public ArbolBinarioOrdenado(ArbolBinario<T> arbol) {
        super();
        if(arbol.elementos == 0)
            return;
        Cola<Vertice> cola = new Cola<Vertice>();
        cola.mete(arbol.raiz);
        while(!cola.esVacia()){
            Vertice v = cola.saca();
            if(v.izquierdo != null)
                cola.mete(v.izquierdo);
            if(v.derecho != null)
                cola.mete(v.derecho);
                this.agrega(v.elemento);
        }
    }

    private void insertarIzquierda(Vertice v, Vertice i){
    	v.izquierdo = i;
    	i.padre = v;
    }

    private void insertarDerecha(Vertice v, Vertice i){
 		v.derecho = i;
 		i.padre = v;
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
		if(this.raiz == null){
			this.raiz = nuevoVertice(elemento);
			this.elementos++;
			ultimoAgregado = this.raiz;
			return;
		}
	   agrega(this.raiz, elemento);
	}

	private void agrega(Vertice v, T elemento){
		Vertice n = nuevoVertice(elemento);
		ultimoAgregado = n;
		if(elemento.compareTo(v.elemento) < 0){
			if(v.izquierdo == null){
				insertarIzquierda(v, n);
				this.elementos++;
			}
			else
				agrega(v.izquierdo, elemento);
			return;
		}
		if(elemento.compareTo(v.elemento) >= 0){
			if(v.derecho == null){
				insertarDerecha(v, n);
				this.elementos++;
			}
			else
				agrega(v.derecho, elemento);
			return;
		}
	}
        

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	 VerticeArbolBinario<T> vertice = busca(elemento);
        if (vertice == null)
            return;
        Vertice v = vertice(vertice);
        elimina(v);
        elementos--;
    }

	// Método auxiliar para eliminar un vértice. 
	private void elimina(Vertice vertice) {
	    Vertice anterior = maximoEnSubarbol(vertice.izquierdo);
	    if (anterior == null) {
		if (vertice.padre == null) {
                    raiz = vertice.derecho;
		    if (vertice.derecho != null)
			vertice.derecho.padre = null;
		    return;
		}
		if (vertice.padre.izquierdo == vertice)
		    vertice.padre.izquierdo = vertice.derecho;
		else
		    vertice.padre.derecho = vertice.derecho;
		if (vertice.derecho != null)
		    vertice.derecho.padre = vertice.padre;
		return;
	    }
	    T elemento = vertice.elemento;
	    vertice.elemento = anterior.elemento;
	    anterior.elemento = elemento;
	    if (anterior.padre.izquierdo == anterior){
		anterior.padre.izquierdo = anterior.izquierdo;
	    }else{
		anterior.padre.derecho = anterior.izquierdo;}
	    if (anterior.izquierdo != null)
		anterior.izquierdo.padre = anterior.padre;
	}
	
    /**
     * Nos dice si un elemento está contenido en el árbol.
     * @param elemento el elemento que queremos ver si está en el árbol.
     * @return <code>true</code> si el elemento está contenido en el árbol,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	   return busca(raiz, elemento) !=null;
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <tt>null</tt>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <tt>null</tt> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
        return busca(raiz, elemento);
    }

    /**
     * Busca recursivamente un elemento, a partir del vértice recibido.
     * @param vertice el vértice a partir del cuál comenzar la búsqueda. Puede
     *                ser <code>null</code>.
     * @param elemento el elemento a buscar a partir del vértice.
     * @return el vértice que contiene el elemento a buscar, si se encuentra en
     *         el árbol; <code>null</code> en otro caso.
     */
    @Override protected Vertice busca(Vertice vertice, T elemento) {
        if (vertice == null)
            return null;
        if (vertice.elemento.equals(elemento))
            return vertice;
        Vertice v = busca(vertice.izquierdo, elemento);
        if (v != null)
            return v;
        return busca(vertice.derecho, elemento);
    }

    /**
     * Regresa el vértice máximo en el subárbol cuya raíz es el vértice que
     * recibe.
     * @param vertice el vértice raíz del subárbol del que queremos encontrar el
     *                máximo.
     * @return el vértice máximo el subárbol cuya raíz es el vértice que recibe.
     */
    protected Vertice maximoEnSubarbol(Vertice vertice) {
        if(vertice == null)
            return null;
        if(vertice.derecho == null)
            return vertice;
        return maximoEnSubarbol(vertice.derecho);
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador(raiz);
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        Vertice v = vertice(vertice);
        giraDerecha(v);
    }
	
	/* Gira el árbol a la izquierda sobre el vértice recibido. */
	private void giraDerecha(Vertice vertice) {
	    Vertice d = vertice.izquierdo;
	    if (d == null)
            return;
        /* El padre del hijo derecho es el padre del vértice. */
	    d.padre = vertice.padre;
	    if (vertice.padre != null)
		/* El padre cambia a su hijo (sea izquierdo o
		 * derecho). */
		if (vertice == vertice.padre.derecho)
		    vertice.padre.derecho = d;
		else
		    vertice.padre.izquierdo = d;
	    /* El hijo derecho del vértice es el hijo izquierdo del hijo
	       derecho. */
	    vertice.izquierdo = d.derecho;
        /* El padre del hijo izquierdo del hijo derecho es el
         * vértice. */
	    if (d.derecho != null)
		d.derecho.padre = vertice;
	    /* El hijo izquierdo del derecho es el vértice. */
	    d.derecho = vertice;
	    /* El padre del vértice es el que era su hijo derecho. */
	    vertice.padre = d;
	    /* Si el vértice era la raíz, la raíz cambia al que era hijo
	       derecho. */
	    if (raiz == vertice)
		  raiz = d;
	}

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        
	Vertice v = vertice(vertice);
        giraIzquierda(v);
    }
	
	/* Gira el árbol a la izquierda sobre el vértice recibido. */
	private void giraIzquierda(Vertice vertice) {
	    Vertice d = vertice.derecho;
	    if (d == null)
            return;
        /* El padre del hijo derecho es el padre del vértice. */
	    d.padre = vertice.padre;
	    if (vertice.padre != null)
		/* El padre cambia a su hijo (sea izquierdo o
		 * derecho). */
		if (vertice == vertice.padre.izquierdo)
		    vertice.padre.izquierdo = d;
		else
		    vertice.padre.derecho = d;
	    /* El hijo derecho del vértice es el hijo izquierdo del hijo
	       derecho. */
	    vertice.derecho = d.izquierdo;
        /* El padre del hijo izquierdo del hijo derecho es el
         * vértice. */
	    if (d.izquierdo != null)
		d.izquierdo.padre = vertice;
	    /* El hijo izquierdo del derecho es el vértice. */
	    d.izquierdo = vertice;
	    /* El padre del vértice es el que era su hijo derecho. */
	    vertice.padre = d;
	    /* Si el vértice era la raíz, la raíz cambia al que era hijo
	       derecho. */
	    if (raiz == vertice)
		raiz = d;
	}
}
