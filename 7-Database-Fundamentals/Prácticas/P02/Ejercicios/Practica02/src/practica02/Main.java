/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica02;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * CLase principal
 * @author Martinez Flores Jorge Yael
 * @author Sanchez Morales Rodrigo Alejandro
 */
public class Main {
    
    Scanner scanner = new Scanner(System.in);
    
    /* Archivo para la clase Cliente. */
    File clienteF;
    /* FileWriter para la clase Cliente. */
    FileWriter clienteFW;
    /* FileReader para la clase Cliente. */
    FileReader clienteFR;
    /* BufferedReader para la clase Cliente. */
    BufferedReader clienteBR;
    
    /* Archivo para la clase Clase. */
    File claseF;
    /* FileWriter para la clase Clase. */
    FileWriter claseFW;
    /* FileReader para la clase Clase. */
    FileReader claseFR;
    /* BufferedReader para la clase Clase. */
    BufferedReader claseBR;
    
    /* Archivo para la claseImpartidas. */
    File claseImpartidaF;
    /* FileWriter para la claseImpartidas. */ 
    FileWriter claseImpartidaFW;
    /* FileReader para las claseImpartida. */
    FileReader claseImpartidaFR;
    /* BufferedReader para la claseImpartida. */
    BufferedReader claseImpartidaBR;   
    
    private final String msgBienvenida = "Bienvenido al Hercules Gym \n";
    private final String opcionesMenu = "Introduce una de las opciones"
                                    + " numericas del siguiente menu:\n"
                                    + "CLIENTES\n"
                                    + "1. Crear cliente\n"
                                    + "2. Actualizar cliente\n"
                                    + "3. Buscar un cliente\n"
                                    + "4. Eliminar un cliente\n"
                                    + "5. Listar todos los clientes\n"
                                    + "CLASES\n"
                                    + "6. Crear una clase\n"
                                    + "7. Actualizar una clase\n"
                                    + "8. Buscar una clase\n"
                                    + "9. Eliminar una clase\n"
                                    + "10. Listar todas las clases\n"
                                    + "CLASES IMPARTIDAS\n"
                                    + "11. Crear una clase a impartir\n"
                                    + "12. Actualzar una clase a impartir\n"
                                    + "13. Buscar una clase a impartir\n"
                                    + "14. Eliminar una clase a impartir\n"
                                    + "15. Listar todas las clases a impartir\n"
                                    + "OTROS\n"
                                    + "16. Volver a mostrar el menu.\n"
                                    + "17. Cerrar todo y salir";
    
    String opcion;

    /**
     * Metodo para iniciar los archivos que se usan
     * para el gym.
     */
    public void creaArchivos(){
        try{
            clienteF = new File("Clientes.txt");
            if(!clienteF.exists())
                clienteF.createNewFile();
            clienteFW = new FileWriter(clienteF, true);
            clienteFR = new FileReader(clienteF);
            clienteBR = new BufferedReader(clienteFR);
            
            claseF = new File("Clases.txt");
            if(!claseF.exists())
                claseF.createNewFile();
            claseFW = new FileWriter(claseF, true);
            claseFR = new FileReader(claseF);
            claseBR = new BufferedReader(claseFR);
            
            claseImpartidaF = new File("ClasesImpartidas.txt");
            if(!claseImpartidaF.exists())
                claseImpartidaF.createNewFile();
            claseImpartidaFW = new FileWriter(claseImpartidaF, true);
            claseImpartidaFR = new FileReader(claseImpartidaF);
            claseImpartidaBR = new BufferedReader(claseImpartidaFR);
            /*for(int i = 0; i < 10; i++)
                clienteFW.write("Line " + i + "\n");
            clienteFW.close();
            */
        } catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
    }
    
    /**
     * Metodo para cerrar los archivos que se usan
     * para el gym.
     */
    public void cierraArchivos(){
        try{
            clienteFW.close();
            claseFW.close();
            claseImpartidaFW.close();
        } catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        System.out.println("Cerrando todo.\nGuardando todo.\nHastaluego.");
    }
    
    /**
     * Metodo para imprimir por completo un archivo
     * @param br el BufferedReader del archivo a imprimir
     */
    public void printFile(BufferedReader br){
        String line;
        try{
            while((line=br.readLine()) != null){
               System.out.println(line);
            }
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
    }
    
    /**
     * Metodo para crear un Cliente y agregarlo al archivo.
     */
    public void creaCliente(){
        String nombre, direccion, telefono;
        System.out.println("Vamos a agregar un cliente.");
        try{
            int id = buscaUltimoID(clienteBR) + 1;
            System.out.print("Introduce el nombre: ");
            nombre = scanner.nextLine();
            System.out.print("Introduce la direccion: ");
            direccion = scanner.nextLine();
            System.out.print("Introduce el telefono: ");
            telefono = scanner.nextLine();
            Cliente cliente = new Cliente(id, nombre, direccion, telefono);
            clienteFW.write(cliente.toString());
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        System.out.println("Cliente agregado exitosamente");
    }
    
    /**
     * Metodo para crear una Clase y agregarlo al archivo.
     */
    public void creaClase(){
        String nombre, profesor, horario, diasImpartidos;
        System.out.println("Vamos a agregar una clase.");
        try{
            int id = buscaUltimoID(claseBR) + 1;
            System.out.print("Introduce el nombre: ");
            nombre = scanner.nextLine();
            System.out.print("Introduce el nombre del profesor: ");
            profesor = scanner.nextLine();
            System.out.print("Introduce el horario: ");
            horario = scanner.nextLine();
            System.out.print("Introduce los dias que se imparten: ");
            diasImpartidos = scanner.nextLine();
            Clase clase = new Clase(id, nombre, profesor, horario, diasImpartidos);
            claseFW.write(clase.toString());
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        System.out.println("Clase agregada exitosamente");
    }
    
    /**
     * Metodo para crear una Clase y agregarlo al archivo.
     */
    public void creaClaseImpartida(){
        String idCliente, idClase;
        boolean existeCliente = buscarPorEntidad(clienteBR, "cliente");
        boolean existeClase = buscarPorEntidad(claseBR, "clase");
        if(existeCliente && existeClase){
            try{
                System.out.print("Introduce el idCliente: ");
                idCliente = scanner.nextLine();
                System.out.print("Introduce el idClase: ");
                idClase = scanner.nextLine();
                ClaseImpartida claseImpartida = new ClaseImpartida(idCliente, idClase);
                claseImpartidaFW.write(claseImpartida.toString());
            }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
            }
        }else{
            System.out.println("No existe la clase o el cliente, "
                    + "intenta con otros id's");
        }
    }
    
    /**
     * Metodo auxiliar para saber el anterior id del elemento que se va
     * a agregar.
     * @param br el BufferedReader del archivo a buscar.
     * @return id el id encontrado +1 para su asignacion.
     */
    public int buscaUltimoID(BufferedReader br){
        String line;
        String ultLine =  "";
        try{
            while((line=br.readLine()) != null){
               ultLine = line;
            }
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        ultLine = ultLine.substring(0, 2);
        ultLine = ultLine.trim();
        int id = Integer.parseInt(ultLine);
        return id;
    }
    
    /**
     * Metodo para eliminar una entidad
     * @param br el BufferedReader de la entidad
     * @param entidad la entidad como cadena
     * @param file el archivo de la entidad
     */
    public void eliminaEntidad(BufferedReader br, String entidad, File file){
        try{
            File tempF = new File("myTempF.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
            String lineaBorrar = buscaLinea(br, entidad);
            String linea;
            while((linea = br.readLine()) != null) {
                String trimmedLinea = linea.trim();
                if(trimmedLinea.equals(lineaBorrar)) continue;
                    writer.write(linea + System.getProperty("line.separator"));
            }
                writer.close();
                boolean s = tempF.renameTo(file);
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
    }
    
    /**
     * Metodo auxiliar para buscar una linea en un archivo.
     * @param br el BufferedReader de la entidad.
     * @param entidad la entidad como cadena.
     * @return la linea que busca.
     */
    public String buscaLinea(BufferedReader br, String entidad){
        String line;
        System.out.print("Introduce cualquier valor de (el / la) " + entidad
                            + " que deseas buscar: ");
        String contenido = scanner.nextLine();
        try{
            while((line=br.readLine()) != null){
               if(line.contains(contenido)){
                   return line;
               }
            }
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        return null;
    }
   
    /**
     * Funcion que busca todas las similitudes en un archivo
     * @param br el BufferedReader del archivo
     * @param tipo auxiliar para saber con que entidad esta trabajando
     */
    public boolean buscarPorEntidad(BufferedReader br, String entidad){
        String line;
        Boolean checkRes = false;
        System.out.print("Introduce cualquier valor de (el / la) " + entidad
                            + " que deseas buscar: ");
        String contenido = scanner.nextLine();
        System.out.println("\nResultados de la busqueda:");
        try{
            while((line=br.readLine()) != null){
                if(line.contains(contenido)){
                   System.out.println(line + "\n");
                   checkRes = true;
                }
            }
        }catch(IOException ioe){
            System.err.printf("Error: ", ioe);
        }
        if(!checkRes)
            System.out.println("No se encontraron resultados\n");
        return checkRes;
    }
    
    /**
     * Metodo para seleccionar la opcion introducida en el menu
     * @param opcion la opcion seleccionada numericamente
     */
    public void menu(String opcion){
        switch(opcion){
            // Crear cliente
            case "1":
                creaCliente();
                break;
            // Actualizar cliente
            case "2":
                eliminaEntidad(clienteBR, "cliente", clienteF);
                creaCliente();
                break;
            // Buscar un cliente    
            case "3":
                buscarPorEntidad(clienteBR, "cliente");
                break;
            // Eliminar un cliente
            case "4":
                eliminaEntidad(clienteBR, "cliente", clienteF);
                break;
            // Listar todos los clientes
            case "5":
                printFile(clienteBR);
                break;
            // Crear una clase 
            case "6":
                creaClase();
                break;
            // Actualizar una clase    
            case "7":
                eliminaEntidad(claseBR, "clase", claseF);
                creaClase();
                break;
            // Buscar una clase
            case "8":
                buscarPorEntidad(claseBR, "clase");
                break;
            // Eliminar una clase
            case "9":
                break;
            // Listar todas las clases
            case "10":
                printFile(claseBR);
                break;
            // Crear una clase a impartir
            case "11":
                creaClaseImpartida();
                break;
            // Actualizar una clase a impartir
            case "12":
                eliminaEntidad(claseImpartidaBR, "claseImpartida", claseImpartidaF);
                creaClaseImpartida();
                break;
            // Buscar una clase a impartir
            case "13":
                buscarPorEntidad(claseImpartidaBR, "clase impartida");
                break;
            // Eliminar una clase a impartir
            case "14":
                eliminaEntidad(claseImpartidaBR, "claseImpartida", claseImpartidaF);
                break;
            // Listar todas las clases a impartir
            case "15":
                printFile(claseBR);
                break;
            // Mostrar el menu
            case "16":
                System.out.println(msgBienvenida);
                break;
            // Cerrar todo y salir
            case "17":
                cierraArchivos();
                System.exit(0);
                break;
            default:
                System.out.println("Opcion invalida, intenta con otro numero");
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Main main = new Main();

        main.creaArchivos();

        System.out.println(main.msgBienvenida);
        System.out.println(main.opcionesMenu);

        while(true){
            System.out.print("Tu opcion: ");
            main.opcion = main.scanner.nextLine();
            main.scanner.nextLine();
            main.menu(main.opcion);
        }
    }
}