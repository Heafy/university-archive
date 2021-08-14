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

    /* Aristas para gráficas; para poder guardar el peso de las aristas. */
    private class Arista {

        /* El vecino del vértice. */
        public Grafica<T>.Vertice vecino;
        /* El peso de arista conectando al vértice con el vecino. */
        public double peso;

        /* Construye una nueva arista con el vértice recibido como vecino y el
         * peso especificado. */
        public Arista(Grafica<T>.Vertice vecino, double peso) {
            this.vecino = vecino;
            this.peso = peso;
        }
    }

    /* Vertices para gráficas; implementan la interfaz ComparableIndexable y
     * VerticeGrafica */
    private class Vertice implements ComparableIndexable<Vertice>,
        VerticeGrafica<T> {

        /* Iterador para las vecinos del vértice. */
        private class IteradorVecinos implements Iterator<VerticeGrafica<T>> {

            /* Iterador auxiliar. */
            private Iterator<Grafica<T>.Arista> iterador;

            /* Construye un nuevo iterador, auxiliándose de la lista de
             * vecinos. */
            public IteradorVecinos(Iterator<Grafica<T>.Arista> iterador) {
                this.iterador = iterador;
            }

            /* Nos dice si hay un siguiente vecino. */
            @Override public boolean hasNext() {
                return iterador.hasNext();
            }

            /* Regresa el siguiente vecino. */
            @Override public VerticeGrafica<T> next() {
                
                Grafica<T>.Arista arista = iterador.next();
                return (VerticeGrafica<T>)arista.vecino;
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
        /* La lista de aristas que conectan al vértice con sus vecinos. */
        public Lista<Grafica<T>.Arista> aristas;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {            
            this.elemento = elemento;
            color = Color.NINGUNO;
            aristas = new Lista<Grafica<T>.Arista>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T getElemento() {
            return this.elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return aristas.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return this.color;
        }

        /* Define el color del vértice. */
        @Override public void setColor(Color color) {
            this.color = color;
        }

        /* Regresa un iterador para los vecinos. */
        @Override public Iterator<VerticeGrafica<T>> iterator() {
            return new IteradorVecinos(aristas.iterator());
        }

        /* Define el índice del vértice. */
        @Override public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Regresa el índice del vértice. */
        @Override public int getIndice() {
            return indice;
        }

        /* Compara dos vértices por distancia. */
        @Override public int compareTo(Vertice vertice) {
            if(distancia == vertice.distancia)
                return 0;
            /*La distancia es -1*/
            if(distancia == -1)
                return 1;
            /*La distancia del vertice es -1*/
            if(vertice.distancia == -1)
                return -1;
            return distancia < vertice.distancia ? -1 : 1;
        }
    }

    /* Interface para poder usar lambdas al buscar el elemento que sigue al
     * reconstruir un camino. No tienen que usar esta interfaz, pero les
     * conviene. */
    @FunctionalInterface
    private interface BuscadorCamino {
        public boolean seSiguen(Grafica.Vertice v, Grafica.Arista a);
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
    @Override public int getElementos() {
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
        if(contiene(elemento))
            throw new IllegalArgumentException();
        vertices.agregaFinal(new Vertice(elemento));
    }

    // Método auxiliar para buscar vértices. 
    private Vertice buscaVertice(T elemento) {
        for(Vertice v : vertices)
            if(v.elemento.equals(elemento))
                return v;
        return null;
    }
    
    //Método auxiliar para buscar aristas.
    private Arista buscaArista(Vertice v, Vertice u) {
        for(Arista e : v.aristas){
            if(e.vecino == u)
                return e;
        }
        return null;
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
        conecta(a, b, 1);
    }
  
  /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @param peso el peso de la nueva arista.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b, double peso) {
        Vertice va = buscaVertice(a), vb = buscaVertice(b);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        if(sonVecinos(a, b) || a == b)
            throw new IllegalArgumentException();
        Arista ea = new Arista(vb, peso);
        Arista eb = new Arista(va, peso);
        va.aristas.agregaFinal(ea);
        vb.aristas.agregaFinal(eb);
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
        Vertice va = buscaVertice(a);
        Vertice vb = buscaVertice(b);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        if(!sonVecinos(a, b))
            throw new IllegalArgumentException(); 
        va.aristas.elimina(buscaArista(va, vb));
        vb.aristas.elimina(buscaArista(vb, va));
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
        Vertice a = buscaVertice(elemento);
        if(a == null)
            throw new NoSuchElementException();
        for(Vertice e : vertices){
            if(e != a){
                e.aristas.elimina(buscaArista(e, a));
                aristas--;
            }
        }
        vertices.elimina(a);
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
        Vertice va = buscaVertice(a);
        Vertice vb = buscaVertice(b);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        for(Arista e : va.aristas){
            if(e.vecino == vb)
                return true;
        }
        return false;
    }

    /**
     * Regresa el peso de la arista que comparten los vértices que contienen a
     * los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return el peso de la arista que comparten los vértices que contienen a
     *         los elementos recibidos, o -1 si los elementos no están
     *         conectados.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public double getPeso(T a, T b) {
        Vertice va = buscaVertice(a);
        Vertice vb = buscaVertice(b);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        return buscaArista(va, vb).peso;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        VerticeGrafica<T> a = buscaVertice(elemento);
        if(a == null)
            throw new NoSuchElementException();
        return a;
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

    // Método Auxiliar recorre
    private void recorre(Vertice v, AccionVerticeGrafica<T> a, MeteSaca<Vertice> str){
        for(Vertice u : vertices)
            u.color = Color.NEGRO;
        v.color = Color.NINGUNO;
        str.mete(v);
        while(!str.esVacia()){
            Vertice actual = str.saca();
            a.actua(actual);
            for(Arista e : actual.aristas)
                if(e.vecino.color != Color.NINGUNO){
                    e.vecino.color = Color.NINGUNO;
                    str.mete(e.vecino);
                }
        }    
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
        Vertice v = buscaVertice(elemento);
            if(v == null)
                throw new NoSuchElementException();
            recorre(v, accion, new Cola<Vertice>());
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
        Vertice v = buscaVertice(elemento);
            if(v == null)
                throw new NoSuchElementException();
            recorre(v, accion, new Pila<Vertice>());
    }

     /**
      * Nos dice si la gráfica es vacía.
      * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
      *         otro caso.
      */
    @Override public boolean esVacio() {
        return vertices.esVacio();
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador(this);
    }

    /**
     * Calcula una trayectoria de distancia mínima entre dos vértices.
     * @param origen el vértice de origen.
     * @param destino el vértice de destino.
     * @return Una lista con vértices de la gráfica, tal que forman una
     *         trayectoria de distancia mínima entre los vértices <tt>a</tt> y
     *         <tt>b</tt>. Si los elementos se encuentran en componentes conexos
     *         distintos, el algoritmo regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> trayectoriaMinima(T origen, T destino) {
        Vertice va = buscaVertice(origen), vb = buscaVertice(destino);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        Lista<VerticeGrafica<T>> t = new Lista<VerticeGrafica<T>>();
        if(va == vb){
            t.agregaFinal(va);
            return t;
        }
        for(Vertice v : vertices)
            v.distancia = -1;
        va.distancia = 0;
        Cola<Vertice> c = new Cola<Vertice>();
        c.mete(va);
        calculaDistancias(c, 1);
        if(vb.distancia == 1)
            return t;
        construyeTrayectoria(t, vb, va);
        return t;
    }

    //Método auxiliar calculaDistancias
    private void calculaDistancias(Cola<Vertice> cola, double d) {
        if(cola.esVacia())
            return;
        Cola<Vertice> vec = new Cola<Vertice>();
        while(!cola.esVacia()){
            Vertice v = cola.saca();
            for(Arista a : v.aristas){
                if(a.vecino.distancia == -1){
                    a.vecino.distancia = d;
                    vec.mete(a.vecino);
                }
            }
        }
        calculaDistancias(vec, d+1);
    }

    //Método auxiliar construyeTrayectoria
    private void construyeTrayectoria(Lista<VerticeGrafica<T>> lista, Vertice va, Vertice vb) {
        lista.agregaInicio(va);
        if(va == vb)
            return;
        for(Arista a : va.aristas){
            if(a.vecino.distancia == va.distancia - 1){
                construyeTrayectoria(lista, a.vecino, vb);
                return;
            }
        }
    }

    /**
     * Calcula la ruta de peso mínimo entre el elemento de origen y el elemento
     * de destino.
     * @param origen el vértice origen.
     * @param destino el vértice destino.
     * @return una trayectoria de peso mínimo entre el vértice <tt>origen</tt> y
     *         el vértice <tt>destino</tt>. Si los vértices están en componentes
     *         conexas distintas, regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos no está en
     *         la gráfica.
     */
    public Lista<VerticeGrafica<T>> dijkstra(T origen, T destino) {
        Vertice va = buscaVertice(origen), vb = buscaVertice(destino);
        if(va == null || vb == null)
            throw new NoSuchElementException();
        Lista<VerticeGrafica<T>> t = new Lista<VerticeGrafica<T>>();
        if(va == vb){
            t.agregaInicio(va);
            return t;
        }
        for(Vertice v : vertices)
            v.distancia = -1;
            va.distancia = 0;
            MonticuloMinimo<Vertice> m = new MonticuloMinimo<Vertice>(vertices);
            colocaDistancias(m);
        if(vb.distancia == -1)
            return t;
        armaTrayectoria(t, va, vb);
            return t;
    }

    // Método Auxiliar colocaDistancias
    private void colocaDistancias(MonticuloMinimo<Vertice> m){
        if(m.esVacio())
            return;
        Vertice v = m.elimina();
        for(Arista a : v.aristas){
            if(a.vecino.distancia == -1 || a.vecino.distancia > v.distancia + a.peso){
                a.vecino.distancia = v.distancia + a.peso;
                a.peso += m.get(a.vecino.getIndice()).distancia;
                m.reordena(a.vecino);
            }
        }
        colocaDistancias(m);
    }

    //Método auxiliar armaTrayectoria
    private void armaTrayectoria(Lista<VerticeGrafica<T>> l, Vertice o, Vertice d){
        l.agregaInicio(d);
        if(o == d)
            return;
       for(Arista a : d.aristas)
            if(a.vecino.distancia == d.distancia-a.peso){
                armaTrayectoria(l, o, a.vecino);
                return;
            }   
    }
}
