/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class ConexionTest {
    Connection conn;
    String url,login,password;
    public ConexionTest() {
        
    }

    @Test
    public void testGetConnection() throws SQLException{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//Driver jdbc para establecer la conexion
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1522/XE","Proyecto","proyecto");
            conn.setAutoCommit(false);
            
            if(conn!=null){
                System.out.println("Conexion Exitosa");
            }else{
                System.out.println("Conexion Erronea");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Conexion Erronea" + e.getMessage());
        }
    }

    @Test
    public void testDesconexion() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al desconectar "+ e.getMessage());
        }
    }

    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Conexion.main(args);
        fail("The test case is a prototype.");
    }
    
}
