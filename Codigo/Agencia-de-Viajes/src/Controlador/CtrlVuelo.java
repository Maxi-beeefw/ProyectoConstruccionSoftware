package Controlador;

import Modelo.Vuelo;
import java.sql.*;
import Modelo.*;
import Vista.frmVuelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pc
 */
public class CtrlVuelo implements ActionListener {

    private Vuelo mod;
    private ConsultasVuelo modC;
    private frmVuelo frm;

    public CtrlVuelo(Vuelo mod, ConsultasVuelo modC, frmVuelo frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnListar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Vuelos");
        frm.setLocationRelativeTo(null);
    }
    
    
    //LISTAR VUELO CONTROLADOR
    public void Listar() {
        DefaultTableModel md = new DefaultTableModel();
        ResultSet rs = ConsultasVuelo.ListarTabla("select * from VUELO");
        md.setColumnIdentifiers(new Object[]{"IDVUELO", "NOMBREAEROLINEA", "ORIGEN", "DESTINO", "ESCALA", "DISPONIBILIDAD"});

        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("IDVUELO"), rs.getString("NombreAerolinea"), rs.getString("Origen"), rs.getString("Destino"), rs.getString("Escala"), rs.getString("Disponibilidad")});
                frm.tblVuelo.setModel(md);
            }

        } catch (Exception e) {
            System.out.println(e);

        }

    }
    
    //MODIFICAR CLIENTE CONTROLADOR
    
    public void Modificar(){
        int fila = frm.tblVuelo.getSelectedRow();

        Vuelo vl = new Vuelo();

        vl.setIdVuelo(Integer.parseInt(frm.tblVuelo.getValueAt(fila, 0).toString()));
        vl.setNombreAerolinea(frm.tblVuelo.getValueAt(fila, 1).toString());
        vl.setOrigen(frm.tblVuelo.getValueAt(fila, 2).toString());
        vl.setDestino(frm.tblVuelo.getValueAt(fila, 3).toString());
        vl.setEscala(frm.tblVuelo.getValueAt(fila, 4).toString());
        vl.setDisponibilidad(frm.tblVuelo.getValueAt(fila, 5).toString());

        if (modC.modificar(vl)) {
            JOptionPane.showMessageDialog(null, "Vuelo Modificado");
        }
    }
    
    
    //ELIMINAR CLIENTE CONTROLADOR
    public void Eliminar(){
        int fila =frm.tblVuelo.getSelectedRowCount();
        //si no selecciona ningun registro
        if(fila<1){
            JOptionPane.showMessageDialog(null, "Debe de seleccionar un registro de la tabla ","AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{//caso contrario eliminar registro
            if(modC.Eliminar(frm.tblVuelo.getValueAt(frm.tblVuelo.getSelectedRow(), 0).toString())){
            JOptionPane.showMessageDialog(null, "Registro Eliminado!"); 
            }
        
        }
    
    }

    
    
//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnGuardar) {
            mod.setIdVuelo(Integer.parseInt(frm.txtIdVuelo.getText()));
            mod.setNombreAerolinea(frm.txtNombreAero.getText());
            mod.setOrigen(frm.txtOrigen.getText());
            mod.setDestino(frm.txtDestino.getText());
            mod.setEscala(frm.txtEscala.getText());
            mod.setDisponibilidad(frm.txtDisponibilidad.getText());

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
        frm.txtIdVuelo.setText(null);
        frm.txtNombreAero.setText(null);
        frm.txtOrigen.setText(null);
        frm.txtDestino.setText(null);
        frm.txtEscala.setText(null);
        frm.txtDisponibilidad.setText(null);
    }
}
