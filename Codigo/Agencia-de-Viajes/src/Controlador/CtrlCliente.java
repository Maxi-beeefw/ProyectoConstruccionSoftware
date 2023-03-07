package Controlador;

import java.sql.*;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @Grupo E
 */
public class CtrlCliente implements ActionListener {

    //Variables
    private Cliente mod;
    private ConsultasCliente modC;
    private frmCliente frmC;
    private frmModCliente frmMod;

    //metodo constructor
    public CtrlCliente(Cliente mod, ConsultasCliente modC, frmCliente frmC, frmModCliente frmMod) {
        this.mod = mod;
        this.modC = modC;
        this.frmC = frmC;
        this.frmMod = frmMod;

        this.frmC.btnGuardar.addActionListener(this);
        this.frmMod.btnActualizar.addActionListener(this);
    }

    public void iniciar() {
        frmC.setTitle("Clientes");
        frmC.setLocationRelativeTo(null);
    }



    //Validar correo
    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);/*Pattern y Matcher  clases de Java que se utilizan para trabajar con expresiones regulares.*/
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Validar numero de cedula
    public static boolean validarCedulatelefono(String cedula) {
        String regex = "^[0-9]{10}$";
        return cedula.matches(regex);
    }

//BOTONES frmCliente --- llamando a los metodos en ConsultasClientes
    @Override
    public void actionPerformed(ActionEvent e) {

        //Guardar Cliente 
        if (e.getSource() == frmC.btnGuardar) {

            mod.setCedula(frmC.txtCedula.getText());
            mod.setNombres(frmC.txtNombres.getText());
            mod.setApellidos(frmC.txtApellidos.getText());
            mod.setTelefono(frmC.txtTelefono.getText());
            mod.setDireccion(frmC.txtDireccion.getText());
            mod.setEmail(frmC.txtEmail.getText());

            String email = frmC.txtEmail.getText().trim();
            String cedula = frmC.txtCedula.getText().trim();
            String telefono = frmC.txtTelefono.getText().trim();
            try {
                if (isValidEmail(email) && validarCedulatelefono(cedula) && validarCedulatelefono(telefono)) {
                    if (modC.registrar(mod)) {
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                        limpiar();
                        Listar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Revise si los datos ingresados son correctos");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == frmMod.btnActualizar) {
            mod.setIdCliente(Integer.parseInt(frmMod.txtIdCliente.getText()));
            mod.setCedula(frmMod.txtCedula.getText());
            mod.setNombres(frmMod.txtNombres.getText());
            mod.setApellidos(frmMod.txtApellidos.getText());
            mod.setTelefono(frmMod.txtTelefono.getText());
            mod.setDireccion(frmMod.txtDireccion.getText());
            mod.setEmail(frmMod.txtEmail.getText());

            String email = frmMod.txtEmail.getText().trim();
            String telefono = frmMod.txtTelefono.getText().trim();
            try {
                if (isValidEmail(email) && validarCedulatelefono(telefono)) {
                    if (modC.modificar(mod)) {
                        JOptionPane.showMessageDialog(null, "Registro Actualizado");
                        frmMod.setVisible(false);
                        Listar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Revise si los datos ingresados son correctos");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void limpiar() {
        frmC.txtCedula.setText("");
        frmC.txtNombres.setText("");
        frmC.txtApellidos.setText("");
        frmC.txtTelefono.setText("");
        frmC.txtDireccion.setText("");
        frmC.txtEmail.setText("");
    }

    
    
    //LISTAR en la interfaz de LISTAR CLIENTES
    public void Listar() {
        frmC.tblCliente.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel md = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        frmC.tblCliente.setRowHeight(20);
        JButton BOTONModificar = new JButton("Modificar");
        BOTONModificar.setName("m");
        JButton BOTONEliminar = new JButton("Eliminar");
        BOTONEliminar.setName("e");
        ResultSet rs = ConsultasCliente.ListarTabla("select * from CLIENTE");
        md.setColumnIdentifiers(new Object[]{"Id", "Cedula", "Nombres", "Apellidos", "Telefono", "Direccion", "Email", "", ""});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("id"), rs.getString("Cedula"), rs.getString("Nombres"), rs.getString("Apellidos"),
                    rs.getString("Telefono"), rs.getString("Direccion"), rs.getString("Email"), BOTONModificar, BOTONEliminar});
                frmC.tblCliente.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
