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
 * @GRUPO E
 */
public class ConsultasHotelTest {
    Hotel h = new Hotel();
    String consulta;
    String id="1";
    public ConsultasHotelTest() {
    }

    @Test
    public void testRegistrar() {
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL REGISTRAR_HOTEL(INCREMENTADOIDHOTEL.NEXTVAL,?,?,?,?,?)}";//Insertando datos en la tabla HOTEL

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            ps.setString(1, h.getNombre());
            ps.setString(2, h.getUbicacion());
            ps.setString(3, h.getTelefono());
            ps.setString(4, h.getDisponibilidad());
            ps.setString(5, h.getServicios());
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

        String sql = "{CALL ACTUALIZAR_HOTEL(?,?,?,?,?,?)}";
   
        try {
            
            ps = con.prepareCall(sql);
            ps.setInt(1, h.getIdHotel());
            ps.setString(2, h.getNombre());
            ps.setString(3, h.getUbicacion());
            ps.setString(4, h.getTelefono());
            ps.setString(5, h.getDisponibilidad());
            ps.setString(6, h.getServicios());
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
        int idH=Integer.parseInt(id);
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ELIMINAR_HOTEL(?)}";

        try {
            ps = con.prepareCall(sql);
             ps.setInt(1, idH);
            
            
            ps.execute();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
       
        }
    }
    
}
