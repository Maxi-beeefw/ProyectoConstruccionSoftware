package Modelo;

import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Grupo E
 */
public class ConsultasHotel extends Conexion {
    
    //METODO REGISTRAR Hotel
    public boolean registrar(Hotel h) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO HOTEL (IDHOTEL, Nombre , Ubicacion, Telefono, Disponibilidad, Servicios) "
                + "VALUES(?,?,?,?,?,?)";//Insertando datos en la tabla HOTEL

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, h.getIdHotel());
            ps.setString(2, h.getNombre());
            ps.setString(3, h.getUbicacion());
            ps.setString(4, h.getTelefono());
            ps.setString(5, h.getDisponibilidad());
            ps.setString(6, h.getServicios());
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
    
    //METODO MODIFICAR HOTEL
     public boolean modificar(Hotel h) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE HOTEL SET Nombre = ?, Ubicacion=?, Telefono=?, Disponibilidad=?, Servicios=? WHERE IdHotel= ?";
   
        try {
            
            ps = con.prepareStatement(sql);
 
            ps.setString(1, h.getNombre());
            ps.setString(2, h.getUbicacion());
            ps.setString(3, h.getTelefono());
            ps.setString(4, h.getDisponibilidad());
            ps.setString(5, h.getServicios());
            ps.setInt(6, h.getIdHotel());
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
       
    
    //METODO LISTAR HOTELES
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
    
    //METODO ELIMINAR HOTEL
    public static boolean Eliminar(String id) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "delete from HOTEL where IdHotel="+id;

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
