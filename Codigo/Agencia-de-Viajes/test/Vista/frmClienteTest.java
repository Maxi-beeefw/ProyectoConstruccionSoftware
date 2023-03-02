/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Vista;

import Controlador.Render;
import Modelo.ConsultasCliente;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class frmClienteTest {
    frmCliente frm= new frmCliente();
    String buscar;
    public frmClienteTest() {
    }

    @Test
    public void testBuscarNuevo() {
        frm.tblCliente.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel md = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                                return false;
                            }
        };
        frm.tblCliente.setRowHeight(20);
        JButton BOTONModificar= new JButton("Modificar");
        BOTONModificar.setName("m");
        JButton BOTONEliminar= new JButton("Eliminar");
        BOTONEliminar.setName("e");
        
        
        String sql = "";
        frm.buscar_box=(String)frm.cb_buscar.getSelectedItem();
        if (frm.buscar_box.equals("Cedula")) {
             sql = "Select*from CLIENTE where CEDULA like'" + buscar + "%'ORDER BY ID";
        }else if(frm.buscar_box.equals("Nombre")){
            sql = "Select*from CLIENTE where NOMBRES like'%" + buscar + "%' ORDER BY ID";
        }else if (frm.buscar_box.equals("Apellido")) {
            sql = "Select*from CLIENTE where APELLIDOS like'%" + buscar + "%'ORDER BY ID";
        } else if (frm.buscar_box.equals("Direccion")){
            sql = "Select*from CLIENTE where DIRECCION like'" + buscar + "%'ORDER BY ID";
        }
        ResultSet rs = ConsultasCliente.ListarTabla(sql);
        md.setColumnIdentifiers(new Object[]{"Id", "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email", "",""});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("id"), rs.getString("Cedula"), rs.getString("Nombres"), rs.getString("Apellidos"),
                    rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Email"),BOTONModificar,BOTONEliminar});
                frm.tblCliente.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
