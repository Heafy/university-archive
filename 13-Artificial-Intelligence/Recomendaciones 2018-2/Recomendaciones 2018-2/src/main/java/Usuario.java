import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase para modelar un objeto Usuario.
 * Un usuario contiene los siguientes elementos:
 * likedMovies: Lista con las películas que le gustan.
 * unlikedMovies: Lista con las películas que NO le gustan.
 * userMovies: Map con los ID's de las películas y su valor para saber
 *             si al usuario le gustan o no.
 * userProfile: Map con los valores calculados para representar los gustos
 *              de cada usuario por género.
 */
public class Usuario {
    
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
    
    /* Lista de películas que le gustan. */
    ArrayList<Movie> likedMovies;
    /* Lista de películas que NO le gustan. */
    ArrayList<Movie> unlikedMovies;
    
    /** 
     * Lista con los gustos marcados del usuario introducidos de las películas
     * seleccionadas.
     * Cuando un usuario selecciona una película que le gusta o no, este valor
     * se modifica por un 1 o -1 respectivamente.
     */
    HashMap<Integer, Integer> userMovies;
    
    /** 
     * HashMap con el perfil de los gustos del usuario calculados.
     * Se calcula con una suma de productos entre la Term Frequency
     * de las películas por género y el Map userMovies
     */
    HashMap<String, Double>userProfile; 
    
    /* La conexión a la base de datos */
    ConnectionDB cbd = new ConnectionDB();
    
    /**
     * Constructor único de la clase Usuario.
     */
    public Usuario(){
        likedMovies = new ArrayList<>();
        unlikedMovies = new ArrayList<>();
        userProfile = new HashMap<>();
        // Llena el perfil del usuario con 0's por defecto
        for(String genero: lstGeneros){
            userProfile.put(genero, 0.0);
        }
        userMovies = new HashMap<>();
        // Se calcula una vez para utilizar en el for
        int total = totalMovies();
        for(int i = 1; i <= total; i++){
            // Inicializa los valores por defecto de sus gustos en 0
           userMovies.put(i, 0);
        }
    }
    
    /**
     * Método para obtener la lista de películas que le gustan al usuario.
     * @return La lista de películas que le gustan al usuario.
     */
    public ArrayList<Movie> getLikedMovies(){
        return likedMovies;
    }

    /**
     * Método para obtener la lista de películas que NO le gustan al usuario.
     * @return La lista de películas que NO le gustan al usuario.
     */    
    public ArrayList<Movie> getUnlikedMovies(){
        return unlikedMovies;
    }
    
    /**
     * Método para obtener el Map con el perfil de gustos del usuario.
     * @return Map con el perfil de gustos del usuario.
     */
    public HashMap<String, Double> getUserProfile(){
        return userProfile;
    }
    
    /**
     * Método para obtener el Map con las películas seleccionadas por 
     * el usuario.
     * @return Map con las películas seleccionadas por el usuario.
     */
    public HashMap<Integer, Integer> getUserMovies(){
        return userMovies;
    }
    
    /**
     * Método para modificar el perfil de gustos del usuario.
     * @param key El género a modificar del usuario.
     * @param value El nuevo valor de gusto del género.
     */
    public void setProfileUser(String key, Double value){
        userProfile.put(key, value);
    }
            
    /**
     * Método para agregar una película a la lista del usuario
     * @param id El id de la película a agregar.
     * @param liked Bandera para saber si le gusta o no la película.
     */
    public void addMovie(int id, boolean liked){
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Movies WHERE movieId = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            // Se para en el primer elemento del apuntador
            if(resultSet.next() == false){
                System.out.println("ID no valido, no se agregó a la lista");
                return;
            }
            int movieId = resultSet.getInt("movieId");
            String titulo = resultSet.getString("titulo");
            String genero = resultSet.getString("genero");
            Movie movie = new Movie(movieId, titulo, genero);
            
            // TODO: Evitar que se agregen elementos duplicados y repetidos
            // en ambas listas
            
            if(liked){
                likedMovies.add(movie);
                userMovies.put(movieId, 1);
                //System.out.println("Liked movie added");
            }else{
                unlikedMovies.add(movie);
                userMovies.put(movieId, -1);
                //System.out.println("Unliked movie added");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
        /**
     * Método para agregar una película a la lista del usuario
     * @param strTitulo El título de la película.
     * @param liked Bandera para saber si le gusta o no la película.
     */
    public void addMovie(String strTitulo, boolean liked){
        
        strTitulo = strTitulo.replace("'", "''");
        
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Movies WHERE titulo = '" + strTitulo + "';";
            ResultSet resultSet = statement.executeQuery(query);
            // Se para en el primer elemento del apuntador
            // Si no encuentra la película, busca una posible sugerencia con
            // base en lo que escribió el usuario
            if(resultSet.next() == false){
                //System.out.println("Titulo no valido, no se agregó a la lista");
                //return;
                System.out.println("\nTítulo no encontrado, quizás quisiste decir:");
                String queryAlternative = "SELECT titulo from MOVIES WHERE titulo LIKE '%" + strTitulo + "%';";
                ResultSet resultSetAlternative = statement.executeQuery(queryAlternative);
                while(resultSetAlternative.next()){
                    System.out.println(resultSetAlternative.getString("titulo"));
                }
                System.out.println();
                return;
            }
            int movieId = resultSet.getInt("movieId");
            String titulo = resultSet.getString("titulo");
            String genero = resultSet.getString("genero");
            Movie movie = new Movie(movieId, titulo, genero);
            
            // TODO: Evitar que se agregen elementos duplicados y repetidos
            // en ambas listas
            
            if(liked){
                likedMovies.add(movie);
                userMovies.put(movieId, 1);
                //System.out.println("Liked movie added");
            }else{
                unlikedMovies.add(movie);
                userMovies.put(movieId, -1);
                //System.out.println("Unliked movie added");
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Método para calcular el total de películas en la Base de datos.
     * @return El número de películas en la base de datos.
     */
    private int totalMovies(){
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT count(*) FROM Movies;";
            ResultSet resultSet = statement.executeQuery(query);
            // Se para en el primer elemento del apuntador
            if(resultSet.next() == false){
                System.out.println("Algo salió terriblemente mal obteniendo el número de películas");
                return 0;
            }
            int numMovies = resultSet.getInt("count");
            return numMovies;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    /**
     * Método para imprimir la lista de películas que reciba.
     * @param movieList La lista de películas a imprimir.
     */
    private void printList(ArrayList<Movie> movieList){
        for(Movie movie : movieList){
            System.out.println(movie.toString());
        }
    }
   
}
