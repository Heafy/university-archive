import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase para moder una película.
 * Una película contiene los siguientes elementos:
 * movieID: ID de la película
 * titulo: Título de la película
 * genero: Map con los géneros de la película y sus valores asignados
 *         si la película tiene ese género
 * generosTotales: Número de géneros de la película
 * TF: Map con la frecuencia de términos de cada género de la película.
 */
public class Movie {
    
    /* Lista con los géneros disponibles en el dataset. */
    final ArrayList<String> lstGeneros = new ArrayList<String>(){{
		   add("Action");
		   add("Adventure");
		   add("Animation");
                   add("Children");
                   add("Comedy");
                   add("Crime");
                   add("Documentary");
                   add("Drama");
                   add("Fantasy");
                   add("Film-Noir");
                   add("Horror");
                   add("IMAX");
                   add("Musical");
                   add("Mystery");
                   add("Romance");
                   add("Sci-Fi");
                   add("Thriller");
		   }};  
    
    /* Int con el id de la película */
    int movieId;
    /* Título de la película */
    String titulo;
    /* Géneros de las películas en forma de cadena. */
    String strGenero;
    /* Hash con el valor del genero y si le gustó al usuario o no. */    
    HashMap<String, Integer> genero;
    
    /**
     * HashMap con los valores de Term Frequency.
     * La frequencia del termino (en este caso el género) que aparece en la 
     * película.
     * TF = Genero (0 | 1) / generosTotales
     * Empiezan con valores 0 por defecto.
     */
    HashMap<String, Double> TF;
    
    /* Total de generos de una película. */
    int generosTotales;
    
    /**
     * Constructor para un objeto instancia de la clase Movie.
     * Contiene los elementos de la tabla mas los valores necesarios
     * para calcular su ángulo de relación y determinar si puede ser 
     * recomendada. 
     * @param movieId El id de la película.
     * @param titulo El título de la película.
     * @param strGeneros La cadena a separar con los géneros de la película.
     */
    public Movie(int movieId, String titulo, String strGenero){
        this.movieId = movieId;
        this.titulo = titulo;
        this.strGenero = strGenero;
        
        this.genero = new HashMap<>();
        // Incializa las marcas del genero con 0
        for(String strGen : lstGeneros){
            genero.put(strGen, 0);
        }
        // Para evitar expresiones regulares
        String[] partes = strGenero.split("\\|");
        // Sobreescribe la HashMap con los géneros encontrados
        for (String parte : partes) {
            genero.put(parte, 1);
        }
        generosTotales = partes.length;
        
        TF = new HashMap<>();
        // Inicializa la Term Frequency con valores 0 por defecto
        for(String strGen : lstGeneros){
            TF.put(strGen, 0.0);
        }
    }
    
    /**
     * Método para obtener el id de una película
     * @return El id de una película.
     */
    public int getMovieId(){
        return movieId;
    }
    
    /**
     * Método para obtener el título de una película. 
     * @return El título de una película.
     */
    public String getTitulo(){
        return titulo;
    }
    
    /**
     * Método para obtener el Map con los géneros de las películas.
     * @return el Map con los géneros de las películas.
     */
    public HashMap<String, Integer> getGenero(){
        return genero;
    }
    
    /**
     * Método para obtener la HashMap con la Term Frequency.
     * @return la HashMap con la Term Frequency.
     */
    public HashMap<String, Double> getTF(){
        return TF;
    }
    
    /**
     * Método para calcular el Term Frequency de cada película.
     */
    public void calculateTF(){
        // Solo es necesario calcularlo una vez, es el mismo valor para
        // todas las entradas
        double root = Math.sqrt(generosTotales);
        for(HashMap.Entry<String, Integer> entry : genero.entrySet()){
            double prevalueTF = entry.getValue();
            double valueTF = prevalueTF / root;
            TF.put(entry.getKey(), valueTF);
        }
        // Imprime el TF para pruebas
        //System.out.println(TF);
    }
    
    /**
     * Método para representar una película en forma de cadena.
     * @return La película en forma de cadena.
     */
    @Override
    public String toString(){
        return  String.format("ID: %s "
                + "TÍTULO: %s\n"
                + "GÉNEROS: %s\n", movieId, titulo, strGenero);
    }
    
}
