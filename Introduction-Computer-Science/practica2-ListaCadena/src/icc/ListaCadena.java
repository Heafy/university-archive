package icc;

/**
 * <p>Clase para listas doblemente ligadas de cadenas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas tienen un iterador para poder recorrerlas.</p>
 */
public class ListaCadena {

    /* Clase Nodo privada para uso interno de la clase ListaCadena. */
    private class Nodo {
        public String elemento;
        public Nodo anterior;
        public Nodo siguiente;

        public Nodo(String elemento) {
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
    public void agregaFinal(String elemento) {
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
    public void agregaInicio(String elemento) {
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
    private Nodo buscaNodo(String elemento){
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
    public void elimina(String elemento) {
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
    public boolean contiene(String elemento){
	   return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaCadena reversa() {
    	ListaCadena l = new ListaCadena();
    	Nodo m = cabeza;
    	while(!(m == null)){
    	    l.agregaInicio(m.elemento);
    	    m = m.siguiente;
    	}
    	return l;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaCadena copia() {
    	ListaCadena t = new ListaCadena();
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
    public String get(int i) {
    	if( i < 0 || i >= longitud){
    	    return null;
    	}else{
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
    public int indiceDe(String elemento) {
    	Nodo m = cabeza;
    	int i = 0;
    	while(!(m == null)){
    	    if(m.elemento.equals(elemento))
    	       return i;
    	    m = m.siguiente;
    	    i++;
    	}
    	return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString(){
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
    public String get() {
    	if(iteradorValido()){
    	   return iterador.elemento;
    	}else{
    	   return null;
    	}
    }

    /**
     * Nos dice si el iterador es válido.
     * @return <tt>true</tt> si el iterador es válido; <tt>false</tt> en otro
     *         caso.
     */
    public boolean iteradorValido() {
	if(iterador == null){
	   return false;
    	}else{
    	    return true;
    	}
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
        if (!(o instanceof ListaCadena))
            return false;
        ListaCadena lista = (ListaCadena)o;
    	if(!(longitud == lista.longitud))
    	   return false;
    	Nodo m = this.cabeza;
    	Nodo n = lista.cabeza;
    	while(!(m == null && n == null)){
    	    if(!(m.elemento.equals(n.elemento)))
    		return false;
    	    m = m.siguiente;
    	    n = n.siguiente;
    	}
    	return true;
    }
}
