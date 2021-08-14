package edd;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus vértices, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices de árboles AVL. La única diferencia
     * con los vértices de árbol binario, es que tienen una variable de clase
     * para la altura del vértice.
     */
    protected class VerticeAVL extends ArbolBinario<T>.Vertice {

        /** La altura del vértice. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
            super(elemento);
            altura = getAltura(ultimoAgregado);
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeAVL}, su elemento es igual al elemento de éste
         *         vértice, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object o) {
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeAVL vertice = (VerticeAVL)o;
            return altura == vertice.altura && super.equals(o);
        }
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link VerticeAVL}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol girándolo como
     * sea necesario. La complejidad en tiempo del método es <i>O</i>(log
     * <i>n</i>) garantizado.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
        VerticeAVL v = verticeAVL(ultimoAgregado);
        actualizarAltura(v);
        rebalancea(padre(v));
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo. La
     * complejidad en tiempo del método es <i>O</i>(log <i>n</i>) garantizado.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        Vertice v = (Vertice) busca(elemento);
        if(v == null)
            return;
        elementos--;
        VerticeAVL p = elimina(v);
        actualizarAltura(p);
        rebalancea(p);    
    }

    private VerticeAVL elimina(Vertice vertice) {
        Vertice padre = null;
        Vertice anterior = maximoEnSubarbol(vertice.izquierdo);
        if (anterior == null) {
            /* No hay subárbol izquierdo; sólo subimos el derecho. */
            padre = vertice.padre;
            if (vertice.padre == null) {
                /* Eliminamos la raíz. */
                raiz = vertice.derecho;
                if (vertice.derecho != null)
                    vertice.derecho.padre = null;
            } else {
                /* Subimos el vértice derecho. */
                if (vertice.padre.izquierdo == vertice)
                    vertice.padre.izquierdo = vertice.derecho;
                else
                    vertice.padre.derecho = vertice.derecho;
                if (vertice.derecho != null)
                    vertice.derecho.padre = vertice.padre;
            }
        } else {
            padre = anterior.padre;
            /* Reemplazamos a vértice con anterior. */
            vertice.elemento = anterior.elemento;
            /* Subimos el izquierdo del vértice, si existe. */
            if (anterior.padre.izquierdo == anterior)
                anterior.padre.izquierdo = anterior.izquierdo;
            else
                anterior.padre.derecho = anterior.izquierdo;
            if (anterior.izquierdo != null)
                anterior.izquierdo.padre = anterior.padre;
        }
        return padre != null ? verticeAVL(padre) : null;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada. Una vez hecho el giro, la
     * altura del vértice y de su nuevo padre se recalculan; ningún otro vértice
     * recalcula su altura. Este método no debe ser llamado por los usuarios de
     * la clase; puede desbalancear el árbol. La complejidad en tiempo del
     * método es <em>O</em>(1).
     * @param vertice el vértice sobre el que vamos a girar.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        VerticeAVL v = verticeAVL(vertice);
        super.giraDerecha(v);
        actualizarAltura(v);
        actualizarAltura(padre(v));
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada. Una vez hecho el giro, la
     * altura del vértice y de su nuevo padre se recalculan; ningún otro vértice
     * recalcula su altura. Este método no debe ser llamado por los usuarios de
     * la clase; puede desbalancear el árbol. La complejidad en tiempo del
     * método es <em>O</em>(1).
     * @param vertice el vértice sobre el que vamos a girar.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        VerticeAVL v = verticeAVL(vertice);
        super.giraIzquierda(v);
        actualizarAltura(v);
        actualizarAltura(padre(v));
    }

    /**
     * Regresa la altura del vértice AVL.
     * @param vertice el vértice del que queremos la altura.
     * @return la altura del vértice AVL.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeAVL}.
     */
    public int getAltura(VerticeArbolBinario<T> vertice) {
        return altura(verticeAVL(vertice));
    }

    private int altura(VerticeAVL vertice){
        return vertice == null ? -1 : vertice.altura;
    }
    
    private void actualizarAltura(VerticeAVL vertice){
        if(vertice == null)
            return;
        vertice.altura = Math.max(altura(izquierdo(vertice)),
                       altura(derecho(vertice))) + 1;
        actualizarAltura(padre(vertice));
    }

    private void rebalancea(VerticeAVL vertice) {
        if(vertice == null)
            return;
        int balance = altura(izquierdo(vertice)) - altura(derecho(vertice));
        if(balance == 0){
            rebalancea(padre(vertice));
            return;
        }else if(balance == -2){
            VerticeAVL d = derecho(vertice);
            if(altura(izquierdo(d)) - altura(derecho(d)) == 1)
                giraDerecha(d);
            giraIzquierda(vertice);
        }else if(balance == 2){
            Vertice i = izquierdo(vertice);
            if(altura(izquierdo(i)) - altura(derecho(i)) == -1)
                giraIzquierda(i);
            giraDerecha(vertice);
        }
        rebalancea(padre(vertice));
    }

    private VerticeAVL derecho(Vertice vertice){
        if(vertice.derecho == null)
            return null;
        return verticeAVL(vertice.derecho);
    }

    private VerticeAVL izquierdo(Vertice vertice){
        if(vertice.izquierdo == null)
            return null;
        return verticeAVL(vertice.izquierdo);
    }

    private VerticeAVL padre(Vertice vertice){
        if(vertice.padre == null)
            return null;
        return verticeAVL(vertice.padre);
    }

    /**
     * Convierte el vértice (visto como instancia de {@link
     * VerticeArbolBinario}) en vértice (visto como instancia de {@link
     * VerticeAVL}). Método auxililar para hacer esta audición en un único
     * lugar.
     * @param vertice el vértice de árbol binario que queremos como vértice AVL.
     * @return el vértice recibido visto como vértice AVL.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeAVL}.
     */
    protected VerticeAVL verticeAVL(VerticeArbolBinario<T> vertice) {
        VerticeAVL v = (VerticeAVL)vertice;
        return v;
    }
}
