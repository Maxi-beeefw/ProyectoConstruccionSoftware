package Modelo;

import Controlador.Render;
import static Modelo.Conexion.getConnection;
import Vista.frmModReservas;
import Vista.frmReserva;
import java.awt.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pc
 */
public class ConsultasReservas {

    //METODO REGISTRAR RESERVAS
    public boolean registrar(Reserva r) {

        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL REGISTRAR_RESERVA(UNO.NEXTVAL,?,?,?,?,?,?,?,?,?)}";//Insertando datos en la tabla CLIENTE

        try {
            ps = (CallableStatement) con.prepareCall(sql);
            ps.setDate(1, new java.sql.Date(r.getFechaInicio().getTime()));
            ps.setDate(2, new java.sql.Date(r.getFechaFin().getTime()));
            ps.setString(3, r.getCliente());
            ps.setString(4, r.getCedula());
            ps.setString(5, r.getHotel());
            ps.setInt(6, r.getHabitaciones());
            ps.setInt(7, r.getPersonas());
            ps.setDouble(8, r.getPrecioTotal());
            ps.setString(9, r.getEstado());

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

    //METODO MODIFICAR RESERVA
    public boolean modificar(Reserva r) {

        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ACTUALIZAR_RESERVA(?,?,?,?,?,?,?,?,?,?)}";

        try {

            ps = con.prepareCall(sql);
            ps.setInt(1, r.getIdReserva());
            ps.setDate(2, new java.sql.Date(r.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(r.getFechaFin().getTime()));
            ps.setString(4, r.getCliente());
            ps.setString(5, r.getCedula());
            ps.setString(6, r.getHotel());
            ps.setInt(7, r.getHabitaciones());
            ps.setInt(8, r.getPersonas());
            ps.setDouble(9, r.getPrecioTotal());
            ps.setString(10, r.getEstado());
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

    public static boolean Eliminar(String id) {
        int idR = Integer.parseInt(id);
        CallableStatement ps = null;
        Connection con = getConnection();

        String sql = "{CALL ELIMINAR_RESERVA(?)}";

        try {
            ps = con.prepareCall(sql);
            ps.setInt(1, idR);
            ps.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;

        }
    }

    public DefaultComboBoxModel obtenerNombresHoteles() {
        DefaultComboBoxModel ListadoHoteles = new DefaultComboBoxModel();
        ListadoHoteles.addElement("Seleccione");
        try {

            Connection con = getConnection();
            CallableStatement stmt = con.prepareCall("{call SP_OBTENER_HOTELES_DISPONIBLES(?)}");
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
//            Obtener los resultados del cursor
            ResultSet rs = (ResultSet) stmt.getObject(1);
            while (rs.next()) {
                ListadoHoteles.addElement(rs.getString("NOMBRE"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            // Manejo de excepciones
            JOptionPane.showMessageDialog(null, e);
        }
        return ListadoHoteles;
    }

    //METODO LISTAR RESERVAS
    public static ResultSet ListarTabla(String consulta) {
        Statement sql;
        ResultSet rs = null;
        Connection con = getConnection();
        try {
            sql = con.createStatement();
            rs = sql.executeQuery(consulta);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    


}
