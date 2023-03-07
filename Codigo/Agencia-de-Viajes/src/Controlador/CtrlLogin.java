package Controlador;

import java.sql.*;
import Modelo.*;
import Vista.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

/**
 *
 * @author Pc
 */
public class CtrlLogin implements ActionListener {
//Variables
//    private Cliente mod;

    private Usuario us;
    private ConsultasLogin clo;
    private FrmLogin frml;

    public CtrlLogin(ConsultasLogin lo, FrmLogin frml, Usuario us) {
        this.clo = clo;
        this.frml = frml;
        this.us = us;

        this.frml.btnEntrar.addActionListener(this);
        this.frml.btnCancelar.addActionListener(this);
    }
    
    public void iniciar() {
        frml.setTitle("LOGIN");
        frml.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == frml.btnEntrar) {
            String usuario = frml.txtUsuario.getText();
            String clave = frml.txtContraseña.getText();

            try {
                if (usuario.isEmpty() || clave.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Llene los dos campos");
                } else {
                    // Validar usuario
                    boolean valido = clo.validarUsuario(usuario, clave);
                    if (valido) {
                        frml.mostrarMensaje("Bienvenido " + usuario + "!");
                        frml.cerrarVentana();
                    } else {
                        frml.mostrarMensaje("Credenciales incorrectas");
                    }
                }
            } catch (SQLException ex) {
                frml.mostrarMensaje("Error al conectar a la base de datos");
            }

        }
    }
       

}
