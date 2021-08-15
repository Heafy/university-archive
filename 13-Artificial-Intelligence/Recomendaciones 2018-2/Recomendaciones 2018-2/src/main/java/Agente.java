import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase que modela el comportamiento del Agente recomendador
 * de películas.
 */
public class Agente {
    
    /* La conexión a la base de datos. */
    ConnectionDB cbd = new ConnectionDB(); 
    
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
    
    /**
     * Genre Frequency:
     * La GF es la cantidad de veces que un genero aparece en el conjunto 
     * de películas
     * Representado en una tabla Hash con el género y su número de películas.
     */
    HashMap<String, Integer> GF = new HashMap<>();
    
    /**
     * Inversed Genre Frequency
     * LA IGF es calculada tomando el logaritmo inverso de la GF entre todo
     * el espacio muestral de los géneros del proyecto.
     * Representado en una tabla Hash con el género y su IDF calculado
     */
    HashMap<String, Double> IGF = new HashMap<>();
        
    /**
     * Método para obtener la Genre Frequency de las películas del dataset
     * Las almacena en un HashMap.
     */
    public void calculateGF(){
        Connection c = cbd.getConnection();
        try{
            Statement s = c.createStatement();
            for(String genero : lstGeneros){
                String query = "SELECT count(*) FROM Movies WHERE genero LIKE '%" + genero + "%';";
                ResultSet r = s.executeQuery(query);
                // Mueve el apuntador del ResultSet
                r.next();
                // Obtiene la cuenta obtenida por el query y la guarda
                int counter = r.getInt("count");
                GF.put(genero, counter);
            }
            // Imprime el DF para pruebas
            //System.out.println(DF);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
     * Método para obtener la Inversed Genre Frequency de las películas 
     * del dataset
     * Las almacena en un HashMap.
     * Nota: Inversamente proporcional al DF. 
     */
    public void calculateIGF(){
        for(HashMap.Entry<String, Integer> entry : GF.entrySet()){
            double valueGF = entry.getValue();
            double prevalueIGF = (double) 10000 / valueGF;
            
            // Se hace la división antes de calcular el logaritmo
            // Java tiene impresiciones aritméticas
            double valueIGF = Math.log10(prevalueIGF);
            IGF.put(entry.getKey(), valueIGF);
        }
        // Imprime el IDF para pruebas
        //System.out.println(IDF);   
    }
    
    /**
     * Método para crear una película a partir de su movieId buscandola
     * en la base de datos.
     * @param id El movieId de la película.
     * @return La película con el movieId.
     */
    public Movie createMovie(int id){
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Movies WHERE movieId = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            // Se para en el primer elemento del apuntador
            if(resultSet.next() == false){
                System.out.println("ID no valido, no se agregó a la base de datos");
                return null;
            }
            int movieId = resultSet.getInt("movieId");
            String titulo = resultSet.getString("titulo");
            String genero = resultSet.getString("genero");
            Movie movie = new Movie(movieId, titulo, genero);
            return movie;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Método para calcular el perfil de gustos del usuario por género
     * @param usuario El usuario del cual calcular sus gustos.
     */
    public void calculateUserProfile(Usuario usuario){
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Movies";
            ResultSet resultSet = statement.executeQuery(query);
            
            // Map con las películas ya seleccionadas por el usuario
            HashMap<Integer, Integer> userMovies = usuario.getUserMovies();
            
            // Variables para almacenar la suma de producto
            double sumAction = 0.0, sumAdventure = 0.0, sumAnimation = 0.0,
                    sumChildren = 0.0, sumCrime = 0.0, sumComedy = 0.0,
                    sumDrama = 0.0, sumDocumentary = 0.0, sumFantasy = 0.0,
                    sumFilmNoir = 0.0, sumHorror = 0.0, sumIMAX = 0.0, 
                    sumMistery = 0.0, sumMusical = 0.0, sumRomance = 0.0, 
                    sumSciFi = 0.0, sumThriller = 0.0;
            
            // Itera sobre todas las películas del dataset
            while(resultSet.next()){
                int movieId = resultSet.getInt("movieId");
                String titulo = resultSet.getString("titulo");
                String genero = resultSet.getString("genero");
                // Cre la película
                Movie movie = new Movie(movieId, titulo, genero);
                //Calcula y obtiene su Term Frequency
                movie.calculateTF();
                HashMap<String, Double> TF = movie.getTF();
                // Valor del usuario según la película [0, 1, -1]
                int valueUser = userMovies.get(movieId);

                // Calcula la suma productos de los gustos 
                // Obteniendo el valor obtenido por su Term Frequency
                // Multiplicandolo por el valor que le dió el usuario
                // a la película
                
                double valueTFAction = TF.get("Action");
                sumAction += valueTFAction * valueUser;
                
                double valueTFAdventure = TF.get("Adventure");
                sumAdventure += valueTFAdventure * valueUser;

                double valueTFAnimation = TF.get("Animation");
                sumAnimation += valueTFAnimation * valueUser;
                
                double valueTFChildren = TF.get("Children");
                sumChildren += valueTFChildren * valueUser;   

                double valueTFComedy = TF.get("Comedy");
                sumComedy += valueTFComedy * valueUser;
                
                double valueTFCrime = TF.get("Crime");
                sumCrime += valueTFCrime * valueUser;
                
                double valueTFDocumentary = TF.get("Documentary");
                sumDocumentary += valueTFDocumentary * valueUser;
                
                double valueTFDrama = TF.get("Drama");
                sumDrama += valueTFDrama * valueUser;
                
                double valueTFFantasy = TF.get("Fantasy");
                sumFantasy += valueTFFantasy * valueUser;
                
                double valueTFFilmNoir = TF.get("Film-Noir");
                sumFilmNoir += valueTFFilmNoir * valueUser;
                
                double valueTFHorror = TF.get("Horror");
                sumHorror += valueTFHorror * valueUser;
                
                double valueTFIMAX = TF.get("IMAX");
                sumIMAX += valueTFIMAX * valueUser;
                
                double valueTFMusical = TF.get("Musical");
                sumMusical += valueTFMusical * valueUser;
                
                double valueTFMistery = TF.get("Mystery");
                sumMistery += valueTFMistery * valueUser;
                
                double valueTFRomance = TF.get("Romance");
                sumRomance += valueTFRomance * valueUser;
                
                double valueTFSciFi = TF.get("Sci-Fi");
                sumSciFi += valueTFSciFi * valueUser;
                
                double valueTFThriller = TF.get("Thriller");
                sumThriller += valueTFThriller * valueUser;
            }
 
            // Agrega los valores al Map correspondiente
            usuario.setProfileUser("Action", sumAction);
            usuario.setProfileUser("Adventure", sumAdventure);
            usuario.setProfileUser("Animation", sumAnimation);
            usuario.setProfileUser("Children", sumChildren);
            usuario.setProfileUser("Comedy", sumComedy);
            usuario.setProfileUser("Crime", sumCrime);
            usuario.setProfileUser("Documentary", sumDocumentary);            
            usuario.setProfileUser("Drama", sumDrama);
            usuario.setProfileUser("Fantasy", sumFantasy);
            usuario.setProfileUser("Film-Noir", sumFilmNoir);
            usuario.setProfileUser("Horror", sumHorror);
            usuario.setProfileUser("IMAX", sumIMAX);
            usuario.setProfileUser("Musical", sumMusical);            
            usuario.setProfileUser("Mystery", sumMistery);
            usuario.setProfileUser("Romance", sumRomance);
            usuario.setProfileUser("Sci-Fi", sumSciFi);
            usuario.setProfileUser("Thriller", sumThriller);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }     
    }

    /**
     * Método para calcular las predicciones del usuario con toda la 
     * información calculada anteriormente.
     * Tambíen muestra las 10 películas que le pueden gustar al usuario.
     * @param usuario El usuario del cual calcular sus predicciones.
     */
    public void calculateUserPrediction(Usuario usuario){
        Connection connection = cbd.getConnection();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Movies";
            ResultSet resultSet = statement.executeQuery(query);
            
            // Perfil de gustos del usuario por cada género
            HashMap<String, Double> userProfile = usuario.getUserProfile();
            
            // Variable con el coseno del angulo que determina la relación
            // entre las películas seleccionadas y la película actual 
            double resultado = 0.0;
            // Estructura Map para almacenar los resultados ordenados
            // Los ordena de menor a mayor
            TreeMap<Double, Integer> result = new TreeMap<>();
            
            // Itera sobre todas las películas
            while(resultSet.next()){
                int movieId = resultSet.getInt("movieId");
                String titulo = resultSet.getString("titulo");
                String genero = resultSet.getString("genero");
                // Crea la película
                Movie movie = new Movie(movieId, titulo, genero);
                movie.calculateTF();
                
                // Term Frequency de cada película
                HashMap<String, Double> TF = movie.getTF();
                
                // Itera sobre los géneros de la película
                for(HashMap.Entry<String, Double> entry : userProfile.entrySet()){
                    // Valor del TF
                    double TFvalue = TF.get(entry.getKey());
                    // Valor del IGF
                    double IGFValue = IGF.get(entry.getKey());
                    // Valor del gusto calculado del usuario
                    double userValue = entry.getValue();
                    // Calcula el Sumproduct de los 3 valores para todos
                    // los generos
                    resultado += TFvalue * IGFValue * userValue;
                }
                // Almacena el resultado del cálculo con el id para encontrarlo
                result.put(resultado, movieId);
                resultado = 0; 
            }
            
            // Map con los valores obtenidos, pero invertido de 
            // mayor a menor para obtener las mejores coincidencias
            Map<Double, Integer> reversedResult = result.descendingMap();
            
            // Itera sobre las películas ordenadas por su ángulo obtenido de
            // mayor a menor
            int i = 0;
            System.out.println("\nPelículas que te pueden gustar: ");
            for(Map.Entry<Double, Integer> entry : reversedResult.entrySet()){
                // Crea la película a partir del ID
                Movie movie = createMovie(entry.getValue());
                // Muestra las 10 películas que le pueden gustar al usuario
                System.out.println(movie.toString());
                if(i == 10) break;
                i++;
            }     
        }catch(SQLException ex){                                                                                                                                        
            System.out.println(ex.getMessage());
        }
    }

}
