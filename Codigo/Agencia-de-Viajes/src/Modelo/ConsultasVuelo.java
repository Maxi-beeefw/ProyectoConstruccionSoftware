package Modelo;

import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Grupo E
 */
public class ConsultasVuelo extends Conexion {
    
    //METODO REGISTRAR VUELO
    public boolean registrar(Vuelo v) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "INSERT INTO VUELO (IDVUELO, NOMBREAEROLINEA , ORIGEN, DESTINO, ESCALA, DISPONIBILIDAD) "
                + "VALUES(?,?,?,?,?,?)";//Insertando datos en la tabla Vuelo

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, v.getIdVuelo());
            ps.setString(2, v.getNombreAerolinea());
            ps.setString(3, v.getOrigen());
            ps.setString(4, v.getDestino());
            ps.setString(5, v.getEscala());
            ps.setString(6, v.getDisponibilidad());
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
    
    //METODO MODIFICAR VUELO
     public boolean modificar(Vuelo v) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "UPDATE VUELO SET NOMBREAEROLINEA = ?, ORIGEN=?, DESTINO=?, ESCALA=?, DISPONIBILIDAD=? WHERE IDVUELO= ?";
   
        try {
            
            ps = con.prepareStatement(sql);

            ps.setString(1, v.getNombreAerolinea());
            ps.setString(2, v.getOrigen());
            ps.setString(3, v.getDestino());
            ps.setString(4, v.getEscala());
            ps.setString(5, v.getDisponibilidad());
            ps.setInt(6, v.getIdVuelo());
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
       
    
    //METODO LISTAR VUELOS
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
    
    //METODO ELIMINAR VUELO
    public static boolean Eliminar(String id) {

        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "delete from VUELO where IDVUELO="+id;

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
