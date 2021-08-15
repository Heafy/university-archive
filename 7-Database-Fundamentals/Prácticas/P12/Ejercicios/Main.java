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
            /*
            //Se crea tabla test.
            String create_table = "CREATE TABLE test(colum1 INTEGER, colum2 VARCHAR(20))";
            sentencia.executeUpdate(create_table);

            //mytable ya existe en Oracle, se inserta un dato.
            String insert = "INSERT INTO test (colum1,colum2) VALUES (2,'dato2')";
            sentencia.executeQuery(insert);
            */ 
            
            /*Instructores que han dado clase de box y que no empiecen con A en su nombre y cuya edad 
            no sea 25,26 y 27 años (nombre completo, edad y clase impartida).*/
            String select = "SELECT NOMBRE, AP_PATERNO, AP_MATERNO, EDAD, NOMBRE_CLASE\n" +
                                "FROM ENTRENADOR, IMPARTIR_CLASE, CLASE\n" +
                                "WHERE (ENTRENADOR.ID_EMPLEADO = IMPARTIR_CLASE.ID_EMPLEADO) AND\n" +
                                "(CLASE.ID_CLASE = IMPARTIR_CLASE.ID_CLASE) AND\n" +
                                "NOMBRE NOT LIKE 'A%' AND\n" +
                                "EDAD NOT BETWEEN 25 AND 27";

            ResultSet resultSelectQuery = sentencia.executeQuery(select);
            
            System.out.println("NOMBRE, APELLIDO PATERNO, APELLIDO MATERNO, EDAD, NOMBRE DE LA CLASE");
            while (resultSelectQuery.next()) {
                System.out.println(String.format(resultSelectQuery.getString("NOMBRE") + "  " +
                                    resultSelectQuery.getString("AP_PATERNO") + "  " + 
                                    resultSelectQuery.getString("AP_MATERNO") + "  " + 
                                    resultSelectQuery.getString("EDAD") + "  " + 
                                    resultSelectQuery.getString("NOMBRE_CLASE")));
            }
            
            /*Productos que han comprado y que terminan con ‘n’, cuya fecha de compra año sido en agosto 
            (nombre de producto y fecha de compra).*/
            select = "SELECT PRODUCTO.NOMBRE_PRODUCTO\n" +
                "FROM COMPRAR_SOCIO, PRODUCTO\n" +
                "WHERE (COMPRAR_SOCIO.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND\n" +
                "PRODUCTO.NOMBRE_PRODUCTO LIKE '%n'\n" +
                "UNION\n" +
                "SELECT PRODUCTO.NOMBRE_PRODUCTO\n" +
                "FROM COMPRAR_CLIENTE, PRODUCTO\n" +
                "WHERE (COMPRAR_CLIENTE.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO) AND\n" +
                "PRODUCTO.NOMBRE_PRODUCTO LIKE '%n'";
            resultSelectQuery = sentencia.executeQuery(select);
            
             System.out.println("PRODUCTO");
             while (resultSelectQuery.next()) {
                System.out.println(String.format(resultSelectQuery.getString("NOMBRE_PRODUCTO")));
            }
             
            String insert = "INSERT INTO SOCIO (ID_SOCIO,NOMBRE,AP_PATERNO,AP_MATERNO,EMAIL,EDAD,SEXO,TELEFONO,FECHA_INGRESO,PERSONA_CONTACTO,PUNTOS) VALUES (122,'Montana','Whitehead','Weaver','ac@semper.net',82,'FEMENINO','2295695249','31-03-12','Griffith',457)";
            sentencia.executeQuery(insert);
            
            insert = "INSERT INTO CLIENTE (ID_CLIENTE,NOMBRE,AP_PATERNO,AP_MATERNO,EMAIL,EDAD,SEXO,"
                    + "TELEFONO,FECHA_INGRESO) VALUES (111,'Camilla','Hickman','Mercer','lectus@in.edu',"
                    + "60,'FEMENINO','3073922101','30-03-14')";
            sentencia.executeQuery(insert);
            
            String delete = "DELETE FROM SOCIO WHERE ID_SOCIO = 15";
            sentencia.executeQuery(delete);
            
            String update = "UPDATE MEMBRESIA SET COSTO=100 WHERE ID_MEMBRESIA=58";
            sentencia.executeQuery(update);
            
            
             
        }catch(Exception e){
             System.out.println("Exception:" + e);
        }
    }
    
}
