/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica12;

import java.sql.*;

/**
 *
 * @author jmtz2
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
             Class.forName("oracle.jdbc.OracleDriver");
             String url = "jdbc:oracle:thin:FBD/Amarok2136@localhost:1521:orcl";
             Connection db = DriverManager.getConnection(url);
             Statement sentencia = db.createStatement();
            
            //Se crea tabla test.
            String create_table = "CREATE TABLE test(colum1 INTEGER, colum2 VARCHAR(20))";
            sentencia.executeUpdate(create_table);

            //mytable ya existe en Oracle, se inserta un dato.
            String insert = "INSERT INTO test (colum1,colum2) VALUES (2,'dato2')";
            sentencia.executeQuery(insert);
            
            
            
            
            
             
        }catch(Exception e){
             System.out.println("Exception:" + e);
        }
    }
    
}
