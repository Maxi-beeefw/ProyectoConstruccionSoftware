package Controlador;


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
public class CtrlCliente implements ActionListener {

    //Variables
    private Cliente mod;
    private ConsultasCliente modC;
    private frmCliente frmC;
    private frmModCliente frmMod;


    
    //metodo constructor
    public CtrlCliente(Cliente mod, ConsultasCliente modC,frmCliente frmC, frmModCliente frmMod) {
        this.mod = mod;
        this.modC = modC;
        this.frmC = frmC;
        this.frmMod= frmMod;
        
        this.frmC.btnGuardar.addActionListener(this);
        this.frmMod.btnActualizar.addActionListener(this);
    }

    
    public void iniciar() {
        frmC.setTitle("Clientes");
        frmC.setLocationRelativeTo(null);
    }
    
    
    
    //LISTAR en la interfaz de LISTAR CLIENTES
    public void Listar() {
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
            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
                Listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
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

            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                frmMod.setVisible(false);
                Listar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        }

      

        
    }
    public void limpiar(){
        frmC.txtCedula.setText("");
        frmC.txtNombres.setText("");
        frmC.txtApellidos.setText("");
        frmC.txtTelefono.setText("");
        frmC.txtDireccion.setText("");
        frmC.txtEmail.setText("");
    }
    
    
    
    
    
    
}
