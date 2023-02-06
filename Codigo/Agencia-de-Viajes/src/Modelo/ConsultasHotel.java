package Modelo;

import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Grupo E
 */
public class ConsultasHotel extends Conexion {
    
    //METODO REGISTRAR CLIENTE
    public boolean registrar(Hotel h) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO HOTEL (NOMBRE, UBICACION, TELEFONO, DISPONIBILIDAD, SERVICIOS) "
                + "VALUES(?,?,?,?,?)";//Insertando datos en la tabla HOTEL

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, h.getNombre());
            ps.setString(2, h.getUbicacion());
            ps.setString(3, h.getTelefono());
            ps.setString(4, h.getDisponibilidad());
            ps.setString(5, h.getServicios());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
    
    //METODO MODIFICAR CLIENTE
     public boolean modificar(Hotel h) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE HOTEL SET UBICACION=?, TELEFONO=?, DISPONIBILIDAD=?, SERVICIOS=? WHERE NOMBRE =? ";
   
        try {
            
            ps = con.prepareStatement(sql);
            ps.setString(1, h.getUbicacion());
            ps.setString(2, h.getTelefono());
            ps.setString(3, h.getDisponibilidad());
            ps.setString(4, h.getServicios());
            ps.setString(5, h.getNombre());
            //Envia la sentencia de Actualizar
            ps.executeUpdate();
            con.close();
            return true;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
    }
       
    
    //METODO LISTAR CLIENTES
    public static ResultSet ListarTabla(String consulta){
        Statement sql;
        ResultSet rs=null;
        Connection con = getConnection();
        try {
            sql=con.createStatement();
            rs=sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    
     //METODO ELIMINAR CLIENTE
    public static boolean Eliminar(String id) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "DELETE from HOTEL where IDHOTEL="+id;

        try {
            ps = con.prepareStatement(sql);
            
            
            ps.execute();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            return false;
       
        }
    }
}
