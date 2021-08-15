import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para que el proyecto pueda conectarse a la base de datos.
 */
public class ConnectionDB {
    
    /* La conexión a la Base de Datos. */
    private Connection connection;
    
    /**
     * Método para conectarse a la base de datos.
     */
    public void connectDB(){
        this.connection = null;
        // Puerto por defecto
        String urlDatabase = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
            // Usuario: postgres
            // Contraseña: 
            connection = DriverManager.getConnection(urlDatabase, "postgres", "");
            //System.out.println("Database opened");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Ocurrio un error : " + e.getMessage());
        }
    }

    /**
     * Método interno para obtener la conexión a la base
     * @return 
     */
    private Connection getInnerConnection(){
        return connection;
    }
    
    /**
     * Método para obtener la conexión a la base de datos.
     * @return La conexión a la base de datos.
     */
    public Connection getConnection(){
        connectDB();
        return getInnerConnection();
    }
    
    /**
     * Método para desconectarse de la base de datos.
     */
    public void disconnectBD(){
        try{
            connection.commit();
            connection.close();
        }catch(SQLException e){
            System.out.println("No se puede desconectar de la base de datos");
        }
    }

}
