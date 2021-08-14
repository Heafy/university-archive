package icc;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas.</p>
 */
public class Lista<T> {

    /* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Nodo iterador. */
    private Nodo iterador;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
	   return longitud;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaFinal(T elemento) {
    	Nodo n = new Nodo(elemento);
    	if(rabo == null){
    	    cabeza = n;
    	    rabo = n;
    	}else{
    	    rabo.siguiente = n;
    	    n.anterior = rabo;
    	    rabo = n;
    	}
    	iterador = cabeza;
    	longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último. Después de llamar este
     * método, el iterador apunta a la cabeza de la lista.
     * @param elemento el elemento a agregar.
     */
    public void agregaInicio(T elemento) {
    	Nodo nuevo = new Nodo(elemento);
    	if(cabeza == null){
    	    cabeza = nuevo;
    	    rabo = nuevo;
    	}else{
    	    cabeza.anterior = nuevo;
    	    nuevo.siguiente = cabeza;
    	    cabeza = nuevo; 
    	}
    	iterador = cabeza;
    	longitud++;
    }

    /**
     * Busca un elemento de la lista
     * @param elemento el elemento a buscar
     * @return el nodo con el elemento de la lista
     *         o null en caso de que no se encuentre en la lista.
     */
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
     * lista, el método no la modifica. Si un elemento de la lista es
     * modificado, el iterador se mueve al primer elemento.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
    	Nodo n = buscaNodo(elemento);
    	if(n == null)
    	    return;
    	if(cabeza == rabo){
    	    cabeza = rabo = null;
    	} else if(n == cabeza){
    	    cabeza = cabeza.siguiente;
    	    cabeza.anterior = null;
    	} else if (n == rabo){
    	    rabo = rabo.anterior;
    	    rabo.siguiente = null;
    	} else {
    	    n.anterior.siguiente = n.siguiente;
    	    n.siguiente.anterior = n.anterior;
    	}
    	longitud--;
    	iterador = cabeza;
    }

    /**
     * Nos dice si un elemento está en la lista. El iterador no se mueve.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(T elemento) {
	   return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
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
    	iterador = null;
    	longitud = 0;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista. Si el índice es menor
     * que cero o mayor o igual que el número de elementos de la lista, el
     * método regresa <tt>null</tt>.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, si <em>i</em> es mayor
     *         o igual que cero y menor que el número de elementos en la lista;
     *         <tt>null</tt> en otro caso.
     */
    public T get(int i) {
    	if( i < 0 || i >= longitud){
    	    return null;
    	} else {
    	    Nodo temp = cabeza;
    	    int contador = 0;
    	    while(contador < i){
    		contador++;
    		temp = temp.siguiente;
    	}
    	    return temp.elemento;
    	}
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si
     *         el elemento no está contenido en la lista.
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
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
    	if(longitud == 0)
    	    return "";
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
     * Mueve el iterador de la lista a su primer elemento.
     */
    public void primero() {
	   iterador = cabeza;
    }

    /**
     * Mueve el iterador de la lista a su último elemento.
     */
    public void ultimo() {
	   iterador = rabo;
    }

    /**
     * Mueve el iterador al siguiente elemento.
     */
    public void siguiente() {
	   iterador = iterador.siguiente;
    }

    /**
     * Mueve el iterador al elemento anterior.
     */
    public void anterior() {
	   iterador = iterador.anterior;
    }

    /**
     * Regresa el elemento al que el iterador apunta.
     * @return el elemento al que el iterador apunta, o <tt>null</tt> si el
     *         iterador es inválido.
     */
    public T get() {
    	if(iteradorValido())
    	    return iterador.elemento;
    	return null;
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
    	if(iterador == null)
    	    return false;
    	return true;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param o el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
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
     * Regresa una copia de la lista recibida, pero ordenada. La
     * lista recibida tiene que contener nada más elementos que
     * implementan la interfaz {@link Comparable}.
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
        }//for
        for(; i < l.longitud ; i++){
            ld.agregaFinal(m.elemento);
            m = m.siguiente;
        }//for
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
            }else{
                l.agregaFinal(nd.elemento);
                nd = nd.siguiente;
            }
        }//While
        Lista<T>.Nodo n = (!(ni == null))? ni : nd;
        while(!(n == null)){
            l.agregaFinal(n.elemento);
            n = n.siguiente;
        }
        return l;
    }

 /**
     * Busca un elemento en una lista ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la
     * interfaz {@link Comparable}, y se da por hecho que está
     * ordenada.
     * @param l la lista donde se buscará.
     * @param e elemento a buscar.
     * @return <tt>true</tt> si e está contenido en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public static <T extends Comparable<T>>
     boolean busquedaLineal(Lista<T> l, T e) {
        return l.contiene(e);
    }
}
