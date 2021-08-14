package edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para diccionarios (<em>hash tables</em>). Un diccionario generaliza el
 * concepto de arreglo, permitiendo (en general, dependiendo de qué tan bueno
 * sea su método para generar huellas digitales) agregar, eliminar, y buscar
 * valores en tiempo <i>O</i>(1) (amortizado) en cada uno de estos casos.
 */
public class Diccionario<K, V> implements Iterable<V> {

    /** Máxima carga permitida por el diccionario. */
    public static final double MAXIMA_CARGA = 0.72;

    /* Clase privada para iteradores de diccionarios. */
    private class Iterador implements Iterator<V> {

        /* En qué lista estamos. */
        private int indice;
        /* Diccionario. */
        private Diccionario<K,V> diccionario;
        /* Iterador auxiliar. */
        private Iterator<Diccionario<K,V>.Entrada> iterador;

        /* Construye un nuevo iterador, auxiliándose de las listas del
         * diccionario. */
        public Iterador(Diccionario<K,V> diccionario) {
            this.diccionario = diccionario;
            while(indice < diccionario.entradas.length
            	&& diccionario.entradas[indice] == null)
            	indice++;
            if(indice < diccionario.entradas.length)
            	iterador = diccionario.entradas[indice].iterator();
        }

        /* Nos dice si hay un siguiente elemento. */
        public boolean hasNext() {
            if(iterador == null)
				return false;
	    	if(iterador.hasNext())
				return true;
	    	indice++;
	    	while(indice < diccionario.entradas.length 
		    	&& diccionario.entradas[indice] == null)
				indice++;
	    	if(indice >= entradas.length)
				return false;
	    	if(indice < diccionario.entradas.length)
				iterador = diccionario.entradas[indice].iterator();
	    	if(iterador == null)
				return false;
	    	return true;
        }

        /* Regresa el siguiente elemento. */
        public V next() {
            if(iterador == null)
            	throw new NoSuchElementException();
            return iterador.next().valor;
        }

        /* No lo implementamos: siempre lanza una excepción. */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* Tamaño mínimo; decidido arbitrariamente a 2^6. */
    private static final int MIN_N = 64;

    /* Máscara para no usar módulo. */
    private int mascara;
    /* Huella digital. */
    private HuellaDigital<K> huella;
    /* Nuestro diccionario. */
    private Lista<Entrada>[] entradas;
    /* Número de valores*/
    private int elementos;

    /* Clase para las entradas del diccionario. */
    private class Entrada {

        public K llave;
        public V valor;

        public Entrada(K llave, V valor) {
            this.llave = llave;
            this.valor = valor;
        }
    }

    /* Truco para crear un arreglo genérico. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private Lista<Entrada>[] nuevoArreglo(int n) {
        Lista[] arreglo = new Lista[n];
        return (Lista<Entrada>[])arreglo;
    }

    /**
     * Construye un diccionario con un tamaño inicial y huella digital
     * predeterminados.
     */
    public Diccionario() {
        this(MIN_N);
    }

    /**
     * Construye un diccionario con un tamaño inicial definido por el usuario, y
     * una huella digital predeterminada.
     * @param tam el tamaño a utilizar.
     */
    public Diccionario(int tam) {
        this(tam, new HuellaDigital<K>(){
        	public int huellaDigital(K llave){
        		return llave.hashCode();
        	}
        });
    }

    /**
     * Construye un diccionario con un tamaño inicial predeterminado, y una
     * huella digital definida por el usuario.
     * @param huella la huella digital a utilizar.
     */
    public Diccionario(HuellaDigital<K> huella) {
        this(MIN_N, huella);
    }

    /**
     * Construye un diccionario con un tamaño inicial, y un método de huella
     * digital definidos por el usuario.
     * @param tam el tamaño del diccionario.
     * @param huella la huella digital a utilizar.
     */
    public Diccionario(int tam, HuellaDigital<K> huella) {
        if(tam < 16)
        	tam = 16;
        entradas = nuevoArreglo((mascara(tam) + 1));
        this.huella = huella;
        mascara = entradas.length - 1;
    }

    /**
     * Agrega un nuevo valor al diccionario, usando la llave proporcionada. Si
     * la llave ya había sido utilizada antes para agregar un valor, el
     * diccionario reemplaza ese valor con el recibido aquí.
     * @param llave la llave para agregar el valor.
     * @param valor el valor a agregar.
     */
    public void agrega(K llave, V valor) {
        int i = indice(llave);
        Lista<Entrada> l = getLista(i);
        Entrada e = buscaEntrada(l, llave);
        if(e != null){
        	e.valor = valor;
        	return;
        }
        e = new Entrada(llave, valor);
        l.agregaFinal(e);
        elementos++;
        if(carga() > MAXIMA_CARGA)
        	creceArreglo();
    }

    /**
    * Metodo auxiliar que busca una entrada dada una K-Llave
    */
    private Entrada buscaEntrada(K llave){
    	int i = indice(llave);
    	if(entradas[i] == null)
    		return null;
    	for(Entrada e : entradas[i])
    		if(e.llave.equals(llave))
    			return e;
    	return null;
    }

    /**
     * Regresa el valor del diccionario asociado a la llave proporcionada.
     * @param llave la llave para buscar el valor.
     * @return el valor correspondiente a la llave.
     * @throws NoSuchElementException si la llave no está en el diccionario.
     */
    public V get(K llave) {
        Entrada e = buscaEntrada(llave);
        if(e == null)
        	throw new NoSuchElementException();
        return e.valor;
    }

    /**
     * Nos dice si una llave se encuentra en el diccionario.
     * @param llave la llave que queremos ver si está en el diccionario.
     * @return <tt>true</tt> si la llave está en el diccionario,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(K llave) {
       	return buscaEntrada(llave) != null;
    }

    /**
     * Elimina el valor del diccionario asociado a la llave proporcionada.
     * @param llave la llave para buscar el valor a eliminar.
     * @throws NoSuchElementException si la llave no se encuentra en
     *         el diccionario.
     */
    public void elimina(K llave) {
        Entrada e = buscaEntrada(llave);
        if(e == null)
        	throw new NoSuchElementException();
        int i = indice(llave);
        entradas[i].elimina(e);
        if(entradas[i].getLongitud() == 0)
        	entradas[i] = null;
        elementos--;
    }

    /**
     * Regresa una lista con todas las llaves con valores asociados en el
     * diccionario. La lista no tiene ningún tipo de orden.
     * @return una lista con todas las llaves.
     */
    public Lista<K> llaves() {
        Lista<K> l = new Lista<K>();
        for(int i = 0; i < entradas.length; i++)
        	if(entradas[i] != null)
        		for(Entrada e : entradas[i])
        			l.agregaFinal(e.llave);
        return l;
    }

    /**
     * Regresa una lista con todos los valores en el diccionario. La lista no
     * tiene ningún tipo de orden.
     * @return una lista con todos los valores.
     */
    public Lista<V> valores() {
        Lista<V> l = new Lista<V>();
        for(int i = 0; i < entradas.length; i++)
        	if(entradas[i] != null)
        		for(Entrada e : entradas[i])
        			l.agregaFinal(e.valor);
        return l;
    }

    /**
     * Nos dice el máximo número de colisiones para una misma llave que tenemos
     * en el diccionario.
     * @return el máximo número de colisiones para una misma llave.
     */
    public int colisionMaxima() {
        int c = 0;
        for(int i = 0; i < entradas.length; i++)
        	if(entradas[i] != null && entradas[i].getLongitud() > c)
        		c = entradas[i].getLongitud();
        if(c == 0)
        	return c;
        return c-1;
    }

    /**
     * Nos dice cuántas colisiones hay en el diccionario.
     * @return cuántas colisiones hay en el diccionario.
     */
    public int colisiones() {
        int c = 0;
        for(int i = 0; i < entradas.length; i++)
        	if(entradas[i] != null && entradas[i].getLongitud() > 1)
        		c += entradas[i].getLongitud() -1;
        return c;
    }

    /**
     * Nos dice la carga del diccionario.
     * @return la carga del diccionario.
     */
    public double carga() {
        double carga = elementos/(mascara + 1.0);
        return carga;
    }

    /**
     * Regresa el número de entradas en el diccionario.
     * @return el número de entradas en el diccionario.
     */
    public int getElementos() {
        return elementos;
    }

    /**
     * Nos dice si el diccionario es vacío.
     * @return <code>true</code> si el diccionario es vacío, <code>false</code>
     *         en otro caso.
     */
    public boolean esVacio() {
        if(elementos == 0)
            return true;
        return false;
    }

    /**
     * Regresa un iterador para iterar los valores del diccionario. El
     * diccionario se itera sin ningún orden específico.
     * @return un iterador para iterar el diccionario.
     */
    @Override public Iterator<V> iterator() {
        return new Iterador(this);
    }
    
    /**
     * Método auxiliar que calcula la máscara de un entero
     * @param - n- el entero a calcular la mascara
     * @return - la mascara
     */
     
    private int mascara(int n) {
		int m = 1;
		while(m < n)
	    	m = (m << 1) | 1;
		m = (m << 1) | 1;
		return m;
    }
     
    /**
      * Método auciliar que calcula el índice
      * @param k- la llave para calcular el indice
      */
      
    private int indice(K k){
		return huella.huellaDigital(k) & mascara;
    }

	/**
	 * Método para incrementar el tamaño del arreglo
	 */
      
    private void creceArreglo(){
	   // Calculamos la máscara con el tamaño actual del arreglo
		mascara = mascara(entradas.length);
	   // Instanciamos un arreglos de listas y le asignamos el valor de la mascara + 1
		Lista<Entrada>[] a = nuevoArreglo(mascara + 1);
		Lista<Entrada> l = lista();
		entradas = a;
		elementos = 0;
		for(Entrada e : l)
	    	agrega(e.llave,e.valor);
    }

    /**
     * Método que busca la entrada
     */
       
    private Entrada buscaEntrada(Lista<Entrada> l, K ll) {
		for(Entrada e : l)
	    	if(e.llave.equals(ll))
				return e;
	return null;
    }

    /**
     * Método que devuelve la lista del i-esimo índice
     */
    
    private Lista<Entrada> getLista(int i) {
		if(entradas[i] != null)
	    	return entradas[i];
		Lista<Entrada> l = new Lista<Entrada>();
		entradas[i] = l;
		return l;
    }

    /**
     * Método para manejar los ArrayList
     */
    
    private Lista<Entrada> lista() {
		Lista<Entrada> l = new Lista<Entrada>();
		for(int i = 0; i < entradas.length; i++)
	    	if(entradas[i] != null)
				for(Entrada e : entradas[i])
		    		l.agregaFinal(e);
		return l;
    }
}
