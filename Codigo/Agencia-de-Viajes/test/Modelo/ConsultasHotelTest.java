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
        //ESTABLECE LA CONEXION A LA BASE DE DATOS
        CallableStatement ps = null;
        Connection con = getConnection();
        
        //SETENCIA SQL
        String sql = "{CALL REGISTRAR_HOTEL(INCREMENTADOIDHOTEL.NEXTVAL,?,?,?,?,?)}";//Insertando datos en la tabla HOTEL

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            //NOMBRE DEL HOTEL
            ps.setString(1, "Boston Hotel");
            //DIRECCION DEL HOTEL
            ps.setString(2, "Padre Vicente Solano Vargas, Guayaquil 090312");
            //TELEFONO DEL HOTEL
            ps.setString(3, "04230-8015");
            //DISPONIBILIDAD DEL HOTEL
            ps.setString(4, "si");
            //SERVICIOS DEL HOTEL
            ps.setString(5, "Estacionamiento, WIFI, Desayuno");
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
        //ESTABLECE LA CONEXION A LA BASE DE DATOS
        CallableStatement ps = null;
        Connection con = getConnection();
        
        //SENTENCIA SQL
        String sql = "{CALL ACTUALIZAR_HOTEL(?,?,?,?,?,?)}";
   
        try {
            
            ps = con.prepareCall(sql);
            //ID DEL HOTEL
            ps.setInt(1, 1);
            //NOMBRE DEL HOTEL
            ps.setString(2, "Boston Hotel");
            //DIRECCION DEL HOTEL
            ps.setString(3, "Padre Vicente Solano Vargas, Guayaquil 090312");
            //TELEFONO DEL HOTEL
            ps.setString(4, "04230-8015");
            //DISPONIBILIDAD DEL HOTEL
            ps.setString(5, "No");
            //SERVICIOS DEL HOTEL
            ps.setString(6, "Estacionamiento, WIFI, Desayuno, Jacuzzi");
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
        //ESTABLECE LA CONEXION A LA BASE DE DATOS
        CallableStatement ps = null;
        Connection con = getConnection();
        
        //SENTENCIA SQL
        String sql = "{CALL ELIMINAR_HOTEL(?)}";

        try {
            ps = con.prepareCall(sql);
            
           //SE PASA EL ID_HOTEL PARA PROCEDER A LA ELIMINACION DEL REGISTRO 
            ps.setInt(1, 1);
            
            
            ps.execute();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
       
        }
    }
    
}
