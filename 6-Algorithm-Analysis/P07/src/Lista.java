import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las instancias de la clase Lista implementan la interfaz {@link
 * Coleccion}, y por lo tanto también la interfaz {@link Iterator}, por lo que
 * el recorrerlas es muy sencillo:</p>
 *
<pre>
    for (String s : l)
        System.out.println(s);
</pre>
 *
 * <p>Además, se le puede pedir a una lista una instancia de {@link
 * IteradorLista} para recorrerla en ambas direcciones.</p>
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {

        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con el elemento especificado. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador<T> implements IteradorLista<T> {

        /* La lista a iterar. */
        Lista<T> lista;
        /* Elemento anterior. */
        private Lista<T>.Nodo anterior;
        /* Elemento siguiente. */
        private Lista<T>.Nodo siguiente;

        /* El constructor recibe una lista para inicializar su siguiente con la
         * cabeza. */
        public Iterador(Lista<T> lista) {
    	    this.lista = lista;
    	    anterior = null;
    	    siguiente = lista.cabeza;
        }

        /* Existe un siguiente elemento, si siguiente no es nulo. */
        @Override public boolean hasNext() { 
	       return siguiente != null;
        }

        /* Regresa el elemento del siguiente, a menos que sea nulo, en cuyo caso
         * lanza la excepción NoSuchElementException. */
        @Override public T next() {
    	    if(siguiente == null)
    		throw new NoSuchElementException();
    	    anterior = siguiente;
    	    siguiente = siguiente.siguiente;
    	    return anterior.elemento;
        }

        /* Existe un elemento anterior, si anterior no es nulo. */
        @Override public boolean hasPrevious() { 
	       return anterior != null;
        }

        /* Regresa el elemento del anterior, a menos que sea nulo, en cuyo caso
         * lanza la excepción NoSuchElementException. */
        @Override public T previous() {
    	    if(anterior == null)
                throw new NoSuchElementException();
    	    siguiente = anterior;
    	    anterior = anterior.anterior;
    	    return siguiente.elemento;
        }

        /* No implementamos el método remove(); sencillamente lanzamos la
         * excepción UnsupportedOperationException. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }

        /* Mueve el iterador al inicio de la lista; después de llamar este
         * método, y si la lista no es vacía, hasNext() regresa verdadero y
         * next() regresa el primer elemento. */
        @Override public void start() {
    	    anterior = null;
    	    siguiente = lista.cabeza;
        }

        /* Mueve el iterador al final de la lista; después de llamar este
         * método, y si la lista no es vacía, hasPrevious() regresa verdadero y
         * previous() regresa el último elemento. */
        @Override public void end() {
    	    siguiente = null;
    	    anterior = lista.rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() { 
	   return longitud;
    }

    /**
     * Regresa el número de elementos en la lista. El método es idéntico a
     * {@link #getLongitud}.
     * @return el número de elementos en la lista.
     */
    @Override public int getElementos() { 
	   return getLongitud();
    }

    /**
     * Nos dice si el conjunto de elementos en la colección es vacío.
     * @return <code>true</code> si el conjunto de elementos en la lista es
     *         vacío, <code>false</code> en otro caso.
     */
    public boolean esVacio() {
    	if(this.cabeza == null && this.rabo == null)
    	    return true;
    	else
    	    return false;
    }
    /**
     * Agrega un elemento al final de la lista. El método es idéntico a {@link
     * #agregaFinal}.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) { 
	   agregaFinal(elemento);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y el último a la vez.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(T elemento) {
    	Nodo n = new Nodo(elemento);
    	if(rabo == null){
    	    cabeza = n;
    	    rabo = n;
    	} else {
    	    rabo.siguiente = n;
    	    n.anterior = rabo;
    	    rabo = n;
    	}
    	longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y el último a la vez.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(T elemento) { 
    	Nodo nuevo = new Nodo(elemento);
    	if(cabeza == null){
    	    cabeza = nuevo;
    	    rabo = nuevo;
    	} else {
    	    cabeza.anterior = nuevo;
    	    nuevo.siguiente = cabeza;
    	    cabeza = nuevo;
    	}
    	longitud++;
    }

    private Nodo buscaNodo(T elemento){
    	Nodo t = cabeza;
    	while(!(t == null)){
    	    if(t.elemento.equals(elemento))
                return t;
    	    t = t.siguiente;
    	}
    	return null;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no hace nada. Si el elemento aparece varias veces en la
     * lista, el método elimina el primero.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	Nodo n = buscaNodo(elemento);
    	if(n == null)
    	    return;
    	if(cabeza == rabo){
    	    cabeza = rabo = null;
    	}else if(n == cabeza){
    	    cabeza = cabeza.siguiente;
    	    cabeza.anterior = null;
    	}else if (n == rabo){
    	    rabo = rabo.anterior;
    	    rabo.siguiente = null;
    	}else{
    	    n.anterior.siguiente = n.siguiente;
    	    n.siguiente.anterior = n.anterior;
    	}
    	longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        Nodo n = cabeza;
        Nodo m = rabo;
        if( n == null && m == null)
            throw new NoSuchElementException();
        T t = cabeza.elemento;
        elimina(t);
        return t;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {  
    	Nodo n = cabeza;
    	Nodo m = rabo;
    	if(n == null && m == null)
    	    throw new NoSuchElementException();
    	T t = rabo.elemento;
    	if(longitud == 1){
    	    limpia();
    	    return t;
    	}
    	rabo = rabo.anterior;
    	rabo.siguiente = null;
    	longitud--;
    	return t;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	   return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa de la que manda llamar el
     *         método.
     */
    public Lista<T> reversa() { 
    	Lista <T>t = new Lista<>();
    	Nodo n = cabeza;
    	while(!(n == null)){
    	    t.agregaInicio(n.elemento);
    	    n = n.siguiente;
    	}
    	return t;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
    	Lista <T>t = new Lista<>();
    	Nodo n = cabeza;
    	while(!(n == null)){
    	    t.agregaFinal(n.elemento);
    	    n = n.siguiente;
    	}
    	return t;
    }

    /**
     * Limpia la lista de elementos. El llamar este método es equivalente a
     * eliminar todos los elementos de la lista.
     */
    public void limpia() {  
    	cabeza = null;
    	rabo = null;
    	longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {  
    	Nodo n = cabeza;
    	Nodo m = rabo;
    	if( n == null && m == null)
    	    throw new NoSuchElementException();
    	return n.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {  
        Nodo n = cabeza;
        Nodo m = rabo;
        if(n == null && m == null)
            throw new NoSuchElementException();
        return m.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista.
     * @throws ExcepcionIndiceInvalido si el índice recibido es menor que cero,
     *         o mayor que el número de elementos en la lista menos uno.
     */
    public T get(int i) {
    	if( i < 0 || i >= longitud)
    	    throw new ExcepcionIndiceInvalido("Indice invalido");
    	return get(cabeza, i, 0);
    }

    /* Método auxiliar recursivo para get. */
    private T get(Nodo nodo, int i, int j) {   
    	if(i == j)
    	    return nodo.elemento;
    	return get(nodo.siguiente, i, ++j);
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
    	int i = 0;
    	return indiceDeAux(elemento, cabeza, i);
    }

    private int indiceDeAux(T elemento, Nodo m, int i){
    	if((!(m == null))){
    		if(m.elemento.equals(elemento))
    		    return i;
    		return indiceDeAux(elemento, m.siguiente, ++i);
    	}
    	return -1;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)o;  
    	if(!(longitud == lista.longitud))
    	    return false;
    	return equalsAux(this.cabeza, lista.cabeza);
        }

    private boolean equalsAux(Nodo n, Nodo m){
    	if(!(m == null && n == null)){
    	    if(!(m.elemento.equals(n.elemento)))
                return false;
    	    return equalsAux(n.siguiente, m.siguiente);
    	}
    	return true;    
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
    	if(longitud == 0)
    	    return "[]";
    	String s = "[";
    	Nodo n = cabeza;
    	while (n != rabo){
    	    s += String.format("%s, ", n.elemento);
    	    n = n.siguiente;
    	}
    	s += String.format("%s]", rabo.elemento);
    	return s;
    }

    /**
     * Regresa un iterador para recorrer la lista.
     * @return un iterador para recorrer la lista.
     */
    @Override public Iterator<T> iterator() {
        return iteradorLista();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador<T>(this);
    }

      /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param l la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> l) {
        if(l.longitud == 1)
            return l.copia();
        Lista<T> li = new Lista<T>();
        Lista<T> ld = new Lista<T>();
        Lista<T>.Nodo m = l.cabeza;
        int i = 0;
        for(; i < l.longitud/2 ; i++){
            li.agregaFinal(m.elemento);
            m = m.siguiente;
        }
        for(; i < l.longitud ; i++){
            ld.agregaFinal(m.elemento);
            m = m.siguiente;
        }
        ld = mergeSort (ld);
        return mezcla(mergeSort(li), ld);
    }

    private static <T extends Comparable<T>>
	Lista<T> mezcla(Lista<T> li, Lista<T> ld){
        Lista<T> l = new Lista<T>();
        Lista<T>.Nodo ni = li.cabeza;
        Lista<T>.Nodo nd = ld.cabeza;
        while(ni != null && nd != null){
            if((ni.elemento.compareTo(nd.elemento) < 0)){
                l.agregaFinal(ni.elemento);
                ni = ni.siguiente;
            } else {
                l.agregaFinal(nd.elemento);
                nd = nd.siguiente;
            }
        }
        Lista<T>.Nodo n = (!(ni == null))? ni : nd;
        while(!(n == null)){
            l.agregaFinal(n.elemento);
            n = n.siguiente;
        }
        return l;
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param l la lista donde se buscará.
     * @param e el elemento a buscar.
     * @return <tt>true</tt> si e está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> l, T e) { 
	   return l.contiene(e);
    }
}
