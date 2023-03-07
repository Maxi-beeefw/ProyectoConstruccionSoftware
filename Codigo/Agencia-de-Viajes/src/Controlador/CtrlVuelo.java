package Controlador;

import static Controlador.CtrlHotel.validarCedulatelefono;
import java.sql.*;
import Modelo.*;
import static Modelo.Conexion.getConnection;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Grupo E
 */
public class CtrlVuelo implements ActionListener {

    //Variables
    private Vuelo mod;
    private ConsultasVuelo modV;
    private frmVuelo frmV;
    private frmModVuelo frmMod;

    //metodo constructor
    public CtrlVuelo(Vuelo mod, ConsultasVuelo modV, frmVuelo frmV, frmModVuelo frmMod) {
        this.mod = mod;
        this.modV = modV;
        this.frmV = frmV;
        this.frmMod = frmMod;

        this.frmV.btnGuardar.addActionListener(this);
        this.frmMod.btnActualizar.addActionListener(this);
    }

    public void iniciar() {
        frmV.setTitle("Hoteles");
        frmV.setLocationRelativeTo(null);
    }

    public static boolean validarTexto(String texto) {
        String regex = "^[a-zA-Z]+$";
        return texto.matches(regex);
    }

//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        //Guardar Cliente 
        if (e.getSource() == frmV.btnGuardar) {

            if (frmV.txtOrigen.getText().isEmpty() || frmV.txtDestino.getText().isEmpty() || frmV.txtEscala.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de guardar.");
            } else {
                mod.setNombreAerolinea((String) frmV.txtaerolinea.getSelectedItem());
                mod.setOrigen(frmV.txtOrigen.getText());
                mod.setDestino(frmV.txtDestino.getText());
                mod.setEscala(frmV.txtEscala.getText());
                mod.setDisponibilidad((String) frmV.txtDisponibilidad.getSelectedItem());

                try {
                    if (modV.registrar(mod)) {
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                        limpiar();
                        Listar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al guardar los datos: " + ex.getMessage());
                }
            }
        }

        if (e.getSource() == frmMod.btnActualizar) {
            mod.setIdVuelo(Integer.parseInt(frmMod.txtIdVuelo.getText()));
            mod.setNombreAerolinea(frmMod.txtAerolinea.getText());
            mod.setOrigen(frmMod.txtOrigen.getText());
            mod.setDestino(frmMod.txtDestino.getText());
            mod.setEscala(frmMod.txtEscala.getText());
            mod.setDisponibilidad(frmMod.txtDisponibilidad.getText());

            if (modV.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                frmMod.setVisible(false);
                Listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        }

    }

    public void limpiar() {
        frmV.txtOrigen.setText("");
        frmV.txtDestino.setText("");
        frmV.txtEscala.setText("");
    }
    //LISTAR en la interfaz de LISTAR VUELOS

    public void Listar() {
        frmV.tblVuelo.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel md = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        frmV.tblVuelo.setRowHeight(20);
        JButton BOTONModificar = new JButton("Modificar");
        BOTONModificar.setName("m");
        JButton BOTONEliminar = new JButton("Eliminar");
        BOTONEliminar.setName("e");
//        BOTONEliminar.b
        ResultSet rs = ConsultasCliente.ListarTabla("select * from VUELO ORDER BY IDVUELO");
        md.setColumnIdentifiers(new Object[]{"ID", "Aerolinea", "Origen", "Destino", "Escala", "Disponibilidad", "", ""});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("IDVUELO"), rs.getString("NOMBREAEROLINEA"), rs.getString("ORIGEN"), rs.getString("DESTINO"),
                    rs.getString("ESCALA"), rs.getString("Disponibilidad"), BOTONModificar, BOTONEliminar});
                frmV.tblVuelo.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
