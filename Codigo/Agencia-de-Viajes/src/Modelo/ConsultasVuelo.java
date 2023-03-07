package Modelo;

import Controlador.Render;
import Vista.frmVuelo;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Grupo E
 */
public class ConsultasVuelo extends Conexion {
    
    //METODO REGISTRAR CLIENTE
    public boolean registrar(Vuelo v) {

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
     public boolean modificar(Vuelo v) {

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
        int idH=Integer.parseInt(id);
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ELIMINAR_VUELO(?)}";

        try {
            ps = con.prepareCall(sql);
             ps.setInt(1, idH);
            
            
            ps.execute();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
            return false;
       
        }
    }
    
    
}
