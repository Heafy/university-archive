import java.util.Scanner;

/**
 * Clase con el método principal del proyecto para ejecutarse.
 */
public class Main {
    
    public static void main(String[] args) {
        
        Agente agente = new Agente();
        Usuario usuario = new Usuario();
        
        agente.calculateGF();
        agente.calculateIGF();

        // Scanner
        Scanner sc = new Scanner(System.in);
        
        // Comportamiento para agregar películas del usuario
        
        String message = "Bienvenido al Sistema de Recomendaciones \n"
                + "Primero debes agregar películas que te gustan y despúes \n"
                + "Películas que NO te gustan para conocer tus gustos \n"
                + "Se específico con los nombres para evitar malentendidos \n"
                + "y errores con la base de datos.\n";
        
        System.out.println(message);
        
        boolean salida = true;
        while(salida){
            System.out.println("Agregando peliculas que te gustan");
            System.out.print("Introduce el título de la película: ");
            String j = sc.nextLine();
            usuario.addMovie(j, true);
            System.out.print("¿Quieres seguir agregando películas? [Si / No] ");
            //sc.nextLine();
            String str = sc.nextLine();
            str = str.toUpperCase();
            if(str.equals("NO") || str.equals("N")) 
                salida = false;
        }
        
        salida = true;
        while(salida){
            System.out.println("Agregando peliculas que NO te gustan");
            System.out.print("Introduce el título de la película: ");
            String j = sc.nextLine();
            usuario.addMovie(j, false);
            System.out.print("¿Quieres seguir agregando películas? [Si / No] ");
            //sc.nextLine();
            String str = sc.nextLine();
            str = str.toUpperCase();
            if(str.equals("NO") || str.equals("N")) 
                salida = false;
        }
        
        agente.calculateUserProfile(usuario);
        agente.calculateUserPrediction(usuario);
    } 

}
