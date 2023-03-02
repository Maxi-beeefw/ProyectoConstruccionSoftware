/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ConsultasCliente;
import Vista.frmCliente;
import Vista.frmModCliente;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class CtrlClienteTest {
    Cliente mod= new Cliente();
    ConsultasCliente modC = new ConsultasCliente();
    frmCliente frmC= new frmCliente();
    frmModCliente frmMod= new frmModCliente();
    public CtrlClienteTest() {
    }


    @Test
    public void testListar() {
        frmC.tblCliente.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel md = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                                return false;
                            }
        };
        frmC.tblCliente.setRowHeight(20);
        JButton BOTONModificar= new JButton("Modificar");
        BOTONModificar.setName("m");
        JButton BOTONEliminar= new JButton("Eliminar");
        BOTONEliminar.setName("e");
        ResultSet rs = ConsultasCliente.ListarTabla("select * from CONSUL_CLIENTES");
        md.setColumnIdentifiers(new Object[]{"Id", "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email", "",""});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("id"), rs.getString("Cedula"), rs.getString("Nombres"), rs.getString("Apellidos"),
                    rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Email"),BOTONModificar,BOTONEliminar});
                frmC.tblCliente.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
