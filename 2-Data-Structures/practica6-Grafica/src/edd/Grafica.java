package edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase privada para iteradores de gráficas. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Grafica<T>.Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador(Grafica<T> grafica) {
            iterador = grafica.vertices.iterator();
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
           return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
			return iterador.next().elemento;
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* Vertices para gráficas; implementan la interfaz ComparableIndexable y
     * VerticeGrafica */
    private class Vertice implements VerticeGrafica<T> {

        /* Iterador para las vecinos del vértice. */
        private class IteradorVecinos implements Iterator<VerticeGrafica<T>> {

            /* Iterador auxiliar. */
            private Iterator<Grafica<T>.Vertice> iterador;

            /* Construye un nuevo iterador, auxiliándose de la lista de
             * vecinos. */
            public IteradorVecinos(Iterator<Grafica<T>.Vertice> iterador) {
                this.iterador = iterador;
            }

            /* Nos dice si hay un siguiente vecino. */
            @Override public boolean hasNext() {
				return iterador.hasNext();
            }

            /* Regresa el siguiente vecino. */
            @Override public VerticeGrafica<T> next() {
                Grafica<T>.Vertice g = iterador.next();
				return (VerticeGrafica<T>)g;
            }

            /* No lo implementamos: siempre lanza una excepción. */
            @Override public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        /* El elemento del vértice. */
        public T elemento;
        /* El color del vértice. */
        public Color color;
        /* La distancia del vértice. */
        public double distancia;
        /* El índice del vértice. */
        public int indice;
        /* Lista de vecinos. */
        public Lista<Grafica<T>.Vertice> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento = elemento;
            color = Color.NINGUNO;
            vecinos = new Lista<Grafica<T>.Vertice>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T getElemento() {
            return elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return color;
        }

        /* Define el color del vértice. */
        @Override public void setColor(Color color) {
            this.color = color;
        }

        /* Regresa un iterador para los vecinos. */
        @Override public Iterator<VerticeGrafica<T>> iterator() {
            return new IteradorVecinos(vecinos.iterator());
        }
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        vertices = new Lista<Vertice>();
        aristas = 0;
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    public int getElementos() {
	   return vertices.getLongitud();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() { 
	   return aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
        if(this.contiene(elemento)){
			throw new IllegalArgumentException();
        }else{
			Vertice v = new Vertice(elemento);
			vertices.agrega(v);
			v.indice=vertices.getLongitud();
		}
	}
	
	/**
	 * Metodo auxiliar para buscar un vertice
	 */
	 
	 private Vertice buscaVertice(T elemento){
		for(Vertice v : vertices)
            if(v.elemento.equals(elemento))
                return v;
		return null;
	}

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
		if(!(contiene(a) || !contiene(b)))
			throw new NoSuchElementException();
		if(sonVecinos(a, b) || a.equals(b))
			throw new IllegalArgumentException();
		Vertice v1 = buscaVertice(a);
		Vertice v2 = buscaVertice(b);
		v1.vecinos.agregaFinal(v2);
		v2.vecinos.agregaFinal(v1);
		aristas++;
	}

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
        Vertice v1 = buscaVertice(a);
        Vertice v2 = buscaVertice(b);
        if(v1 == null || v2 == null)
			throw new NoSuchElementException();
		if(!v1.vecinos.contiene(v2) || !v2.vecinos.contiene(v1))
			throw new IllegalArgumentException();
		v1.vecinos.elimina(v2);
		v2.vecinos.elimina(v1);
		aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <tt>true</tt> si el elemento está contenido en la gráfica,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
		if(buscaVertice(elemento) != null)
            return true;
		return false;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
        Vertice v = buscaVertice(elemento);
        if(v != null){
			for(Vertice a : v.vecinos)
			desconecta(a.elemento, v.elemento);
			vertices.elimina(v);
		}else {
			throw new NoSuchElementException();
        }
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <tt>true</tt> si a y b son vecinos, <tt>false</tt> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
		if(!contiene(a) || !contiene(b))
			throw new NoSuchElementException();
		Vertice v1 = buscaVertice(a);
		Vertice v2 = buscaVertice(b);
		if(v1.vecinos.contiene(v2))
			return true;
		return false;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
       VerticeGrafica<T> v = buscaVertice(elemento);
       if(v == null)
			throw new NoSuchElementException();
		return v;
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        for(Vertice v : vertices)
			accion.actua(v); 
    }
    
	private void recorre(AccionVerticeGrafica<T> accion, MeteSaca<Vertice> meteSaca,
						Vertice vertice){
        for(Vertice v : vertices)
            v.color = Color.NEGRO;
        meteSaca.mete(vertice);
        vertice.color = Color.ROJO;
            while(!meteSaca.esVacia()){
            vertice = meteSaca.saca();
            accion.actua(vertice);
            for(Vertice n : vertice.vecinos)
                if(n.color == Color.NEGRO){
                    meteSaca.mete(n);
                    n.color = Color.ROJO;
                }
            }
        for(Vertice k : vertices)
            k.color = Color.NINGUNO;
    } 

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
         Vertice vertice = buscaVertice(elemento);
        Cola<Vertice> c = new Cola<Vertice>();
        recorre(accion, c, vertice);          
    }
    

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
        Vertice vertice = buscaVertice(elemento);
        Pila<Vertice> p = new Pila<Vertice>();
        recorre(accion, p, vertice);
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacio() {
        if(vertices.esVacio())
			return true;
		return false;	
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador(this);
    }
}
