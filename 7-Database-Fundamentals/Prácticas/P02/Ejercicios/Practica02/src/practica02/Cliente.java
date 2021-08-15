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
public class Cliente {
    
    /* Identificador unico del cliente. */
    int idCliente;
    /* El nombre completo del cliente. */
    String nombre;
    /* Direccion del cliente. */
    String direccion;
    /* Telefono del cliente. */
    String telefono;
    
    /**
     * Constructor de la clase Cliente.
     * @param idCliente el identificador unico del cliente.
     * @param nombre el nombre del cliente.
     * @param direccion la direccion del cliente.
     * @param telefono el telefono del cliente.
     */
    public Cliente(int idCliente, String nombre, String direccion, String telefono){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el id del cliente.
     * @return id del cliente.
     */
    public int getidCliente(){
        return idCliente;
    }
    
    /**
     * Cambia el id del cliente
     * @param idCliente el id del cliente a cambiar.
     */
    public void setidCliente(int idCliente){
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return nombre del cliente.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Cambia el nombre del cliente.
     * @param nombre del cliente a cambiar.
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }	
    
    /**
     * Obtiene la direccion del cliente.
     * @return direccion del cliente.
     */
    public String getDireccion(){
        return direccion;
    }

    /**
     * Cambia la direccion del cliente.
     * @param direccion del cliente a cambiar.
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    /**
     * Obtiene el telefono del cliente.
     * @return telefono del cliente.
     */
    public String getTelefono(){
        return telefono;
    }

    /**
     * Cambia el nombre del cliente.
     * @param telefono del cliente a cambiar.
     */
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    
    /**
     * Muestra el Cliente como una String
     * Util para escribirlo en un archivo
     * @return el cliente como una String
     */
    @Override
    public String toString(){
        return idCliente + "  " + nombre + "  " + direccion + "  " + telefono;
    }
}