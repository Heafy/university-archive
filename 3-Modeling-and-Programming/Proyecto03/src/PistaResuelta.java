import java.util.Date;
import java.io.Serializable;

/**
 * Clase para guardar la informacion de una Pista resuelta
 */
public class PistaResuelta implements Serializable{

    private String nombre;
    private Date tiempo;
    
    /**
     * Constructor unico para una Pista Resuelta
     * @param nombre El nombre del usuario que completo la pista
     * @param tiempo El tiempo que se tardo en completarlo
     */
    public PistaResuelta(String nombre, Date tiempo){
        this.nombre = nombre;
        this.tiempo = tiempo; 
    }
    
    /**
     * Metodo para obtener el nombre del usuario que termino la pista
     * @return El nombre del usuario que resolvio la pista
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Metodo para saber el tiempo usado para resolver a pista
     * @return La fecha o tiempo que se tardo
     */
    public Date getTiempo(){
        return tiempo;
    }
    
    /**
     * Metodo para representar una Pista Resuelta
     * @return Una cadena con el nombre y el tiempo
     */
    public String toString(){
        return " por " + nombre + " en " + tiempo;
    }
}
