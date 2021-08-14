package edd;

/**
 * Clase para pilas genéricas.
 */
public class Pila<T> extends MeteSaca<T> {

    /**
     * Agrega un elemento al tope de la pila.
     * @param elemento el elemento a agregar.
     */
    @Override public void mete(T elemento) {
        // Aquí va su código.
        Nodo nuevo = new Nodo(elemento);
        if(elementos==0){
          cabeza=rabo=nuevo;
          elementos++;
        }else{
          nuevo.siguiente=cabeza;
          cabeza=nuevo;
          this.elementos++;
        }
    }
}
