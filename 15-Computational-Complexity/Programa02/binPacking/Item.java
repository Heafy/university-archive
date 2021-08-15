/**
 * Clase para modelar un Item.
 * Un item es su valor en tamaño y un booleano para saber si ha sido empaquetado.
 */
public class Item{

    /* El tamaño del item. */
    double size;
    /* Booleano para representar si fue empaquetado. */
    boolean packed;

    /**
     * Constructor único.
     * Inicializa el Item con sus valores.
     * Por defecto no está empaquetado
     * @param size el tamaño del item.
     */
    public Item(double size) {
        this.size = size;
        packed = false;
    }

    /**
     * Método para obtener el tamaño del item.
     * @return El tamaño del item.
     */
    public double getSize() {
        return size;
    }

    /**
     * Método para saber si el item esta empacado en un Bin.
     * @return True si el item ya fue empacado, false en otro caso.
     */
    public boolean getpacked() {
        return packed;
    }

    /**
     * Método para establacer si un Item fue empacado.
     * @param packed El nuevo valor de empaquetado del item.
     */
    public void setPacked(boolean packed) {
        this.packed = packed;      
    }
}