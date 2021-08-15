/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica02;

/**
 *
 * @author heafy
 */
public class Clase {
    
    /* Identificador unico de la clase */
    int idClase;
    /* Nombre de la clase */
    String nombre;
    /* Profesor que imparte la clase */
    String profesor;
    /* Horario de clase */
    String horario;
    /* Dias que se imparten */
    String diasImpartidos;
    
    /**
     * Constructor de la clase Clase.
     * @param idClase identificador unico de la clase.
     * @param nombre de la clase.
     * @param profesor que imparte la clase.
     * @param horario de la clase.
     * @param diasImpartidos dias en los que se imparte la clase.
     */
    public Clase(int idClase, String nombre, String profesor, 
                String horario, String diasImpartidos){
        this.idClase = idClase;
        this.nombre = nombre;
        this.profesor = profesor;
        this.horario = horario;
        this.diasImpartidos = diasImpartidos;
    }
    
     /**
     * Obtiene el id de la clase.
     * @return id de la clase.
     */
    public int getidClase(){
        return idClase;
    }
    
    /**
     * Cambia el id de la clase.
     * @param idClase el id de la clase a cambiar.
     */
    public void setidClase(int idClase){
        this.idClase = idClase;
    }
    
    /**
     * Obtiene el nombre de la clase.
     * @return nombre de la clase.
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Cambia el nombre de la clase.
     * @param nombre de la clase a cambiar.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
     /**
     * Obtiene el nombre del profesor que imparte la clase.
     * @return nombre del profesor que imparte la clase.
     */
    public String getProfesor(){
        return profesor;
    }
    
    /**
     * Cambia el profesor que imparte la clase.
     * @param profesor el profesor de la clase a cambiar.
     */
    public void setProfesor(String profesor){
        this.profesor = profesor;
    }

    /**
     * Obtiene el horario en que se imparte la clase.
     * @return horario de la clase.
     */
    public String getHorario(){
        return horario;
    }
    
    /**
     * Cambia el horario de la clase.
     * @param horario el horario de la clase a cambiar.
     */
    public void setHorario(String horario){
        this.horario = horario;
    }
    
    /**
     * Obtiene los dias en que se imparte la clase.
     * @return diasImpartidos de la clase.
     */
    public String getdiasImpartidos(){
        return diasImpartidos;
    }
    
    /**
     * Cambia los dias impartidos de la clase.
     * @param diasImpartidos de la clase a cambiar.
     */
    public void setdiasImpartidos(String diasImpartidos){
        this.diasImpartidos = diasImpartidos;
    }
    
     /**
     * Muestra la clase como una String
     * Util para escribirlo en un archivo
     * @return la clase como una String
     */
    @Override
    public String toString(){
        return idClase + "  " + nombre + "  " + profesor + "  " + 
                horario + "  " + diasImpartidos;
    }
}