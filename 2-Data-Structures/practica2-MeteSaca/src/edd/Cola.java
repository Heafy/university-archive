package edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Agrega un elemento al final de la cola.
     * @param elemento el elemento a agregar.
     */
    @Override public void mete(T elemento) {
		Nodo n = new Nodo(elemento);
		if(this.esVacia()){
			rabo = cabeza = n;
		} else {
			n.siguiente = rabo;
		   	rabo = n;
		}
		elementos++;
	}
}
	    
