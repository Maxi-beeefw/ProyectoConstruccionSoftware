/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import static Modelo.Conexion.getConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class ConsultasClienteTest {
    Cliente c= new Cliente();
    String consulta,id="1";
  
    public ConsultasClienteTest() {
    }

    @Test
    public void testRegistrar() {
        CallableStatement ps = null;
        Connection con = getConnection();
        

        String sql = "{CALL REGISTRAR_CLIENTE(INCREMENTADOIDCLIENTE.NEXTVAL,?,?,?,?,?,?)}";//Insertando datos en la tabla CLIENTE

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getApellidos());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getEmail());
            ps.execute();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void testModificar() {
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ACTUALIZAR_CLIENTE(?,?,?,?,?,?,?)}";
   
        try {
            
            ps = con.prepareCall(sql);
            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getCedula());
            ps.setString(3, c.getNombres());
            ps.setString(4, c.getApellidos());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getDireccion());
            ps.setString(7, c.getEmail());
            //Envia la sentencia de Actualizar
            ps.executeUpdate();
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    @Test
    public void testListarTabla() {
        Statement sql;
        ResultSet rs=null;
        Connection con = getConnection();
        try {
            sql=con.createStatement();
            rs=sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testEliminar() {
        int idC=Integer.parseInt(id);
        CallableStatement ps = null;
        Connection con = getConnection();
        
        String sql = "{CALL ELIMINAR_CLIENTE(?)}";

        try {
            ps = con.prepareCall(sql);
            ps.setInt(1, idC);
            ps.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
