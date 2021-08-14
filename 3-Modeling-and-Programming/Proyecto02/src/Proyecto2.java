import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Proyecto2{

	Agenda agenda = new Agenda();
	Scanner sc = new Scanner(System.in);

	/**
    * Un menu para la agenda
    */
    public void menu(){
        int opcion = 0;
        while(opcion != 7){
            opcion = menuAux();
            switch(opcion){
                case 1: // Agregar contacto
                    // TO DO Compare Strings with regular expresions
                	agregaMenu();
                    break;
                case 2: // Buscar contacto
                    buscaMenu();
                    break;
                case 3: // Eliminar contacto
                    eliminaMenu();
                    break;
                case 4: // Editar contacto
                    editaMenu();
            		break;
                case 5: // Ver todos los contactos
                    System.out.println(agenda.toString());
                    break;
                case 6: // Borrar toda la agenda
                   vaciaMenu();
                    break;
                case 7: // Terminar el programa
                    System.out.println("Agenda cerrada. Hasta luego!");
            } // Switch (opcion)
        } // While(true)
    } // Menu

    /**
    * Metodo auxiliar para el menu de la agenda.
    * Este ciclo permitira elegir la opcion correcta para el ciclo donde se ejecutaran
    * los algoritmos para la agenda, permitira que el usuario pueda introducir numeros 
    * o la cadena con la opcion deseada.
    */
    private int menuAux(){
        System.out.println("Bienvenido a la Agenda de Jorge Martinez");
        while(true){
            // Espera recibir el entero o la opcion
            System.out.println("Selecciona una opcion:\n" +
                                "1. Agregar contacto\n"+
                                "2. Buscar contacto\n"+
                                "3. Eliminar contacto\n"+
                                "4. Editar contacto\n"+
                                "5. Ver todos los contactos\n"+
                                "6. Borrar toda la agenda\n" + 
                                "7. Terminar el programa\n");
            String opcion = sc.nextLine();
            opcion = opcion.toLowerCase().replaceAll(" ","");
            switch(opcion){
                case "1":
                case "1.agregarcontacto":
                case "agregarcontacto":
                    return 1;
                case "2":
                case "2.buscarcontacto":
                case "buscarcontacto":
                    return 2;
                case "3":
                case "3.eliminarcontacto":
                case "eliminar contacto":
                    return 3;
                case "4":
                case "4.editarcontacto":
                case "editarcontacto":
                    return 4;
                case "5":
                case "5.vertodosloscontactos":
                case "vertodosloscontactos":
                    return 5;
                case "6":
                case "6.borrartodalaagenda":
                case "borrartodalaagenda":
                    return 6;
                case "7":
                case "7.terminarelprograma":
                case "terminarelprograma":
                    return 7;
                default:
                    System.out.println("Opcion incorrecta, intenta de nuevo.");
            } // Switch (opcion)
        } //While(true)
    }// Menu Aux

    /**
    * Metodo auxiliar del menu para agregar un contacto a la agenda.
    * Verifica con expresiones regulares que cumplan los parametros solicitados
    */
    private void agregaMenu(){
        String regexChar = "[a-zA-Z]+";
        String regexNumber = "[0-9]+";
    	System.out.println("Vamos a agregar un nuevo contacto.\nIntroduce el nombre: ");
    	// No aplica para el nombre porque este puede contener espacios u otros caracteres
    	String nombre = sc.nextLine();
    	System.out.println("Introduce el telefono de casa: ");
    	String telefono = sc.nextLine();
        if(!telefono.matches(regexNumber)){
            System.out.println("El numero introducido no es valido, se descartara este contacto\n");
            return;
        }
    	System.out.println("Introduce el telefono celular: ");
    	String celular = sc.nextLine();
        if(!celular.matches(regexNumber)){
            System.out.println("El numero introducido no es valido, se descartara este contacto\n");
            return;
        }
    	System.out.println("Introduce el correo electronico: ");
    	String email = sc.nextLine();
        if(!email.contains("@")){
            System.out.println("El email introducido no es valido, se descartara este contacto\n");
            return;
        }
    	Persona persona = new Persona(nombre, telefono, celular, email);
    	agenda.agregaRegistro(persona);
    	System.out.println("Persona agregada correctamente.\n");
    }


    /**
    * Metodo auxiliar del menu para buscar personas en la agenda
    */
    private void buscaMenu(){
    	System.out.println("Introduce el nombre de la persona que quieres buscar:");
    	String nom = sc.nextLine();
    	Lista<Persona> busqueda = agenda.buscaPersonas(nom);
    	System.out.println(busqueda.toString());
    }


    /**
    * Metodo auxiliar del menu para eliminar personas en la agenda.
    */
    private void eliminaMenu(){
        System.out.println("Introduce el nombre completo del contacto que deseas borrar:");
        String nombre = sc.nextLine();
        if(agenda.buscaElimina(nombre) == true)
            System.out.println("Contacto eliminado exitosamente.");
        else
            System.out.println("Contacto no encontrado");

    }


    /**
    * Metodo auxiliar del menu para editar personas en la agenda.
    */
    private void editaMenu(){
        String regexChar = "[a-zA-Z]+";
        String regexNumber = "[0-9]+";
        System.out.println("Introduce el nombre completo del contacto que deseas borrar:");
        String nombre = sc.nextLine();
        Persona persona = agenda.buscaEdita(nombre);
        boolean b = agenda.buscaElimina(nombre);
        if(persona==null)
            System.out.println("Lo siento, no hay un contacto con ese nombre");
        else{
            System.out.println("Introduce el parametro que deseas editar");
            String parametro = sc.nextLine();
            parametro = parametro.toLowerCase().replaceAll(" ","");
            switch(parametro){
                case "nombre":
                    System.out.println("El nombre del contacto es: " + persona.getNombre() + "\nIntroduce el nuevo nombre:");
                    String nom = sc.nextLine();
                    // No aplica para el nombre porque este puede contener espacios u otros caracteres
                    persona.setNombre(nom);
                    break;
                case "telefono":
                    System.out.println("El telefono del contacto es: " + persona.getTelefonoCasa() + "\nIntroduce el nuevo telefono:");
                    String tel = sc.nextLine();
                    if(!tel.matches(regexNumber)){
                        System.out.println("El numero introducido no es valido, se descartara esta edicion\n");
                        return;
                    }
                    persona.setTelefonoCasa(tel);
                    break;
                case "telefonocelular":
                case "celular":
                    System.out.println("El telefono celular del contacto es: " + persona.getTelefonoCelular() + "\nIntroduce el nuevo telefono celular:");
                    String cel = sc.nextLine();
                    if(!cel.matches(regexNumber)){
                        System.out.println("El numero introducido no es valido, se descartara esta edicion\n");
                        return;
                    }
                    persona.setTelefonoCelular(cel);
                    break;
                case "correoelectronico":
                case "email":
                    System.out.println("El email del contacto es: " + persona.getEmail() + "\nIntroduce el nuevo email:");
                    String email = sc.nextLine();
                    if(!email.contains("@")){
                        System.out.println("El email introducido no es valido, se descartara esta edicion\n");
                        return;
                    }
                    persona.setEmail(email);
                    break;
                default:
                    System.out.println("Lo siento, ese parametro es incorrecto");
            } // Switch
            agenda.agregaRegistro(persona);
        } // Else
    }


    /**
    * Metodo auxiliar del menu para vaciar la agenda
    */
    private void vaciaMenu(){
    	 System.out.println("Estas seguro de que quieres borrar toda la agenda? \n" +
                                        "Esta accion no se puede deshacer \nSi/No\n" );
    	 String res = sc.nextLine();
    	 res = res.toLowerCase().replaceAll(" ","");
    	 if(res.equals("si")){
    	 	agenda.vaciaAgenda();
    	 	System.out.println("Todos los registros de la agenda han sido borrados.\n");
    	 }else if(res.equals("no"))
    	 	System.out.println("Menos mal, se hubieran borrado todos los datos.\n");
    	 else
    	  	System.out.println("Opcion invalida, no se han realizado cambios\n");
    }

    /**
    * Guarda la agenda en el archivo
    * @param nombreArchivo el nombre del archivo donde guardara la agenda.
    */
    private void guardaMenu(String nombreArchivo){
        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            agenda.guarda(out);
            out.close();
            } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n",
                            nombreArchivo);
            System.exit(1);
            }
            System.out.printf("\nAgenda guardada exitosamente en \"%s\".\n",
                                nombreArchivo);
    }

    /**
    * Lee del archivo y agrega los elementos a la agenda
    * @param nombreArchivo el nombre del archivo donde guardara la agenda.
    */
    private void leeMenu(String nombreArchivo){
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            agenda.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }
        System.out.printf("Agenda cargada exitosamente de \"%s\".\n\n",
                          nombreArchivo);
    }

    public static void main(String[] args) {
    	if (args.length != 1){
    		System.out.println("Ejecuta el programa con el siguiente comando:\njava Proyecto2 agenda.txt (O el archivo donde desees guardarla informacion)");
    		System.exit(1);
    	}
        String bandera = args[0];
        Proyecto2 menu = new Proyecto2();
        menu.leeMenu(bandera);
        menu.menu();
        menu.guardaMenu(bandera);

    }
}
