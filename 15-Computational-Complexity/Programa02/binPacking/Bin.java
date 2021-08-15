import java.util.ArrayList;

/**
 * Clase para modelar un Bin.
 * Un bin es un contenedor de elementos representado por una lista.
 * El máximo tamaño que almacena un bin es 1.0.
 */
public class Bin {

    /* Lista de elementos del Bin. */
    ArrayList<Double> items;
    /* Tamaño máximo del bin. */
    final double MAX_SIZE = 1;

    /**
     * Constructor único.
     * Inicializa la lista.
     */
    public Bin() {
        items = new ArrayList<Double>();
    }

    /**
     * Método para añadir un valor al Bin.
     * @param d El valor a agregar.
     */
    public void add(double d) {
        items.add(d);
    }

    /**
     * Método para agregar un tamaño al bin.
     * @return El tamaño del bin como la suma de sus elementos.
     */
    public double getSize() {
        double sum = 0;
        for(double f : items) {
            sum += f;
        }
        return sum;
    }

    /**
     * Método para saber si se puede agregar el nuevo valor al Bin.
     * @param newSize El nuevo valor a agregar al Bin.
     * @return true Si se puede agregar el valor al Bin y este no hace
     * que el tamaño del bin sea mayor a 1, false en otro caso.
     */
    public boolean canAdd(double newSize) {
        double sum = getSize();
        if(sum > MAX_SIZE)
            return false;
        else if((sum + newSize) > MAX_SIZE)
            return false;
        else
            return true;
    }

    /**
     * Método para representar un Bin como cadena.
     * @return El Bin como una lista en cadena.
     */
    @Override
    public String toString() {
        String str = "[";
        int i = 0;
        for(;i < items.size()-1; i++)
            str += items.get(i) + ", ";
        str += items.get(i) + "]";
        return str;
    }
}