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
 * @Grupo E
 */
public class ConsultasVueloTest {
    Vuelo v= new Vuelo();
    String consulta="";
    String id="2";
    public ConsultasVueloTest() {
    }

    @Test
    public void testRegistrar() {
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL REGISTRAR_VUELO(INCREMENTADOIDVUELO.NEXTVAL,?,?,?,?,?)}";//Insertando datos en la tabla VUELO

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            ps.setString(1, v.getNombreAerolinea());
            ps.setString(2, v.getOrigen());
            ps.setString(3, v.getDestino());
            ps.setString(4, v.getEscala());
            ps.setString(5, v.getDisponibilidad());
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

        String sql = "{CALL ACTUALIZAR_VUELO(?,?,?,?,?,?)}";
   
        try {
            
            ps = con.prepareCall(sql);
            ps.setInt(1, v.getIdVuelo());
            ps.setString(2, v.getNombreAerolinea());
            ps.setString(3, v.getOrigen());
            ps.setString(4, v.getDestino());
            ps.setString(5, v.getEscala());
            ps.setString(6, v.getDisponibilidad());
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

        String sql = "{CALL ELIMINAR_VUELO(?)}";

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
