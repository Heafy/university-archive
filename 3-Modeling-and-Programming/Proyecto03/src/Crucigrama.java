import java.util.ArrayList;
import java.io.Serializable;

/**
 * Clase para modelar el Crucigrama que se ejecuta cada vez que se inicia un juego
 */
public class Crucigrama implements Serializable {

    // Manejamos las pistas en dos diferentes arreglos para tener mejor control sobre cada uno
    private final ArrayList<Pista> horizontalPistas, verticalPistas;
    private final String nombre;
    private final int dimension;
    private String username;

    /**
     * Constructor unico para un Crucigrama
     * @param nombre El nombre del Crucigrama
     * @param dimension EL tamaño del crucigrama (Cada crucigrama es cuadrado, el entero representa 
     *             el numero de cuadros n x n que sera el tamaño del crucigrama)
     * @param horizontalPistas Las pistas horizontales
     * @param verticalPistas  Las pistas verticales
     */
    Crucigrama(String nombre, int dimension, ArrayList<Pista> horizontalPistas, ArrayList<Pista> verticalPistas){ 
        this.nombre = nombre;
        this.dimension = dimension;
        this.horizontalPistas = horizontalPistas;
        this.verticalPistas = verticalPistas;


        //Revisa las dimensiones de las palabras introducidas en las pistas para que quepan en el Crucigrama
        for(Pista c: horizontalPistas){
            c.setIsDown(false);
            if(c.getDimension() + c.getX() > dimension){
                System.out.println("El tamaño de la palabra es muy largo (horizontalmente)");
                System.out.println("Se intentara solucionar el problema");
                dimension = c.getDimension() + c.getX();
            }
        }
        for(Pista c: verticalPistas){
            c.setIsDown(true);
            if(c.getDimension() + c.getY()  > dimension){
                System.out.println("El tamaño de la palabra es muy largo (verticalmente)");
                System.out.println("Se intentara solucionar el problema");
                dimension = c.getDimension() + c.getY();
            }
        }
    }
    
    /**
     * Metodo para obtener la dimension del crucigrama
     * @return La dimension del crucigrama
     */
    public int getDimension(){
        return dimension;
    }

    /** 
     * Metodo para obtener el nombre del crucigrama
     * @return El nombre del Crucigrama
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Metodo para obtener las pistas horizontales
     * @return Una lista con las Pistas horizontales
     */
    public ArrayList<Pista> getHorizontalPistas(){
        return horizontalPistas;
    }

   /**
     * Metodo para obtener las pistas verticales
     * @return Una lista con las Pistas verticales
     */
    public ArrayList<Pista> getVerticalPistas(){
        return verticalPistas;
    }

    /** 
     * Metodo para obtener las pistas, por si es util
     * @return Una lista con todas las pistas
     */
    public ArrayList<Pista>  getPistas(){
        ArrayList<Pista> result = new ArrayList<Pista>();
        for(Pista Pista: horizontalPistas){
            result.add(Pista);
        }
        for(Pista Pista: verticalPistas){
            result.add(Pista);
        }
        return result;
    }

    /**
     * Cambia el username para la persona que esta resolveiendo el Crucigrama
     * @param username El nombre del usuario
     */
    public void setUsername(String username){
        this.username = username;
        Pista.setUsuarioActual(username);
    }

    /**
     * Metodo para obtener el username
     * @return El nombre del usuario
     */
    public String getUsername(){
        return this.username;
    }
}

