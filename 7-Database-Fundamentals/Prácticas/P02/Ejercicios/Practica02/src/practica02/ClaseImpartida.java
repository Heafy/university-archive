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
public class ClaseImpartida {
    
    String idCliente;
    String idClase;
    
    public ClaseImpartida(String idCliente, String idClase){
        this.idCliente = idCliente;
        this.idClase = idClase;
    }
    
    /**
     * Muestra la clase como una String
     * Util para escribirlo en un archivo
     * @return la clase como una String
     */
    @Override
    public String toString(){
        return idCliente + "  " + idClase;
    }
    
}
