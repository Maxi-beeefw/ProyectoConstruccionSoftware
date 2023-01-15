package Controlador;

import Modelo.Hotel;
import java.sql.*;
import Modelo.ConsultasHotel;
import Vista.frmHotel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pc
 */
public class CtrlHotel implements ActionListener {

    private Hotel mod;
    private ConsultasHotel modC;
    private frmHotel frm;

    public CtrlHotel(Hotel mod, ConsultasHotel modC, frmHotel frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnListar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Hoteles");
        frm.setLocationRelativeTo(null);
    }
    
    
    //LISTAR HOTEL CONTROLADOR
    public void Listar() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ConsultasHotel.ListarTabla("select * from HOTEL");
        md.setColumnIdentifiers(new Object[]{"IDHOTEL", "Nombre", "Ubicacion", "Telefono", "Disponibilidad", "Servicios"});

        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("IDHOTEL"), rs.getString("Nombre"), rs.getString("Ubicacion"), rs.getString("Telefono"), rs.getString("Disponibilidad"), rs.getString("Servicios")});
                frm.tblHotel.setModel(md);
            }

        } catch (Exception e) {
            System.out.println(e);

        }

    }
    
    //MODIFICAR CLIENTE CONTROLADOR
    
    public void Modificar(){
        int fila = frm.tblHotel.getSelectedRow();

        Hotel ht = new Hotel();

        ht.setIdHotel(Integer.parseInt(frm.tblHotel.getValueAt(fila, 0).toString()));
        ht.setNombre(frm.tblHotel.getValueAt(fila, 1).toString());
        ht.setUbicacion(frm.tblHotel.getValueAt(fila, 2).toString());
        ht.setTelefono(frm.tblHotel.getValueAt(fila, 3).toString());
        ht.setDisponibilidad(frm.tblHotel.getValueAt(fila, 4).toString());
        ht.setServicios(frm.tblHotel.getValueAt(fila, 5).toString());

        if (modC.modificar(ht)) {
            JOptionPane.showMessageDialog(null, "Hotel Modificado");
        }
    }
    
    
    //ELIMINAR CLIENTE CONTROLADOR
    public void Eliminar(){
        int fila =frm.tblHotel.getSelectedRowCount();
        //si no selecciona ningun registro
        if(fila<1){
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro de la tabla ","AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{//caso contrario eliminar registro
            if(modC.Eliminar(frm.tblHotel.getValueAt(frm.tblHotel.getSelectedRow(), 0).toString())){
            JOptionPane.showMessageDialog(null, "Registro Eliminado!"); 
            }
        
        }
    
    }

    
    
//BOTONES frmCliente --- llamando a los metodos en ConsultasHoteles
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnGuardar) {
            mod.setIdHotel(Integer.parseInt(frm.txtIdHotel.getText()));
            mod.setNombre(frm.txtNombre.getText());
            mod.setUbicacion(frm.txtUbicacion.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setDisponibilidad(frm.txtDisponibilidad.getText());
            mod.setServicios(frm.txtServicios.getText());

            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnModificar) {
            Modificar();
        }
        
        if (e.getSource() == frm.btnEliminar) {
            Eliminar();
        }

        
         if (e.getSource() == frm.btnListar) {
            Listar();
        }
        
    }

    public void limpiar() {
        frm.txtIdHotel.setText(null);
        frm.txtNombre.setText(null);
        frm.txtUbicacion.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtDisponibilidad.setText(null);
        frm.txtServicios.setText(null);
    }
}
