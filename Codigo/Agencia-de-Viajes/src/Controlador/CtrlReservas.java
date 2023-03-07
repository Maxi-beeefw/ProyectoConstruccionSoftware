package Controlador;

import static Controlador.CtrlCliente.validarCedulatelefono;
import Controlador.CedulaInvalidaException;
import Modelo.*;
import Vista.frmReserva;
import Vista.frmModReservas;
import Vista.frmPagos;
import com.toedter.calendar.JCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pc
 */
public class CtrlReservas implements ActionListener {

    //Variables
    private Reserva r;
    private ConsultasReservas cr;
    private frmReserva frmR;
    private frmModReservas frmMR;
 

    public CtrlReservas(Reserva r, ConsultasReservas cr, frmReserva frmR,frmModReservas frmMR) {
        this.r = r;
        this.cr = cr;
        this.frmR = frmR;
        this.frmMR = frmMR;

        this.frmR.btnReservar.addActionListener(this);
        this.frmMR.btnActualizar.addActionListener(this);

    }

  

    public void iniciar() {
        frmR.setTitle("Reservas");
        frmR.setLocationRelativeTo(null);
    }

   
    private boolean verificarCamposCompletos(JTextField[] campos) {
        for (JTextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean validarFecha(Date fechaSeleccionada) {
        // Obtener la fecha actual
        Date fechaActual = new Date();

        // Comparar las fechas
        if (fechaSeleccionada.after(fechaActual)) {
            // La fecha seleccionada es a futuro
            return true;
        } else {
            // La fecha seleccionada es anterior o igual a la fecha actual
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Validaciones campos vacios y fechas
        if (e.getSource() == frmR.btnReservar) {
            if (frmR.dateFechaInicio.getDate() == null || frmR.dateFechaFin.getDate() == null || frmR.combohoteles.getSelectedItem()=="Seleccione" || frmR.txtHabitaciones.getText().isEmpty() || frmR.txtPersonas.getText().isEmpty() || frmR.txtCliente.getText().isEmpty() || frmR.txtcedula.getText().isEmpty() || frmR.txtPreciototal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de Reservar.");
            } else if (!validarFecha(frmR.dateFechaInicio.getDate()) || !validarFecha(frmR.dateFechaFin.getDate())) {
                JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a hoy.");
            } else {
                //código para guardar la reserva

                r.setFechaInicio(frmR.dateFechaInicio.getDate());
                r.setFechaFin(frmR.dateFechaFin.getDate());
                r.setHotel((String) frmR.combohoteles.getSelectedItem());
                r.setHabitaciones(Integer.parseInt(frmR.txtHabitaciones.getText()));
                r.setPersonas(Integer.parseInt(frmR.txtPersonas.getText()));
                r.setCliente(frmR.txtCliente.getText());
                r.setCedula(frmR.txtcedula.getText());
                r.setPrecioTotal(Double.parseDouble(frmR.txtPreciototal.getText()));
                r.setEstado((String) frmR.txtEstado.getSelectedItem());

                try {
                    if (validarCedulatelefono(r.getCedula())) {
                        if (cr.registrar(r)) {
                            JOptionPane.showMessageDialog(null, "Reserva Realizada ,Tenga un buen día!");
                            limpiar();
                            Listar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al realizar la Reserva");
                        }
                    } else {
                        throw new CedulaInvalidaException();
                    }
                } catch (CedulaInvalidaException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frmMR.btnActualizar) {
            if (!verificarCamposCompletos(new JTextField[]{frmMR.txtIdReserva, frmMR.txtHabitaciones,
                frmMR.txtPersonas, frmMR.txtCliente, frmMR.txtcedula,
                frmMR.txtPreciototal, frmMR.txtEstado})
                    || frmMR.dateFechaInicio.getDate() == null ||frmMR.combohoteles.getSelectedItem()=="Sleccione"|| frmMR.dateFechaFin.getDate() == null) {

                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos antes de Reservar.");
            } else if (!validarFecha(frmMR.dateFechaInicio.getDate()) || !validarFecha(frmMR.dateFechaFin.getDate())) {
                JOptionPane.showMessageDialog(null, "La fecha no puede ser anterior a hoy.");
            } else {
                try {
                    r.setIdReserva(Integer.parseInt(frmMR.txtIdReserva.getText()));
                    r.setFechaInicio(frmMR.dateFechaInicio.getDate());
                    r.setFechaFin(frmMR.dateFechaFin.getDate());
                    r.setHotel((String) frmMR.combohoteles.getSelectedItem());
                    r.setHabitaciones(Integer.parseInt(frmMR.txtHabitaciones.getText()));
                    r.setPersonas(Integer.parseInt(frmMR.txtPersonas.getText()));
                    r.setCliente(frmMR.txtCliente.getText());
                    r.setCedula(frmMR.txtcedula.getText());
                    r.setPrecioTotal(Double.parseDouble(frmMR.txtPreciototal.getText()));
                    r.setEstado(frmMR.txtEstado.getText());

                    if (cr.modificar(r)) {
                        JOptionPane.showMessageDialog(null, "Reserva Actualizada");
                        frmMR.setVisible(false);
                       Listar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar");
                    }
                } catch (NumberFormatException ex) {
                    try {
                        throw new ValorNoNumericoException();
                    } catch (ValorNoNumericoException ex1) {
                        JOptionPane.showMessageDialog(null, "Error");

                    }
                }
            }
        }

    }

    public void limpiar() {
        frmR.combohoteles.setSelectedItem("Seleccione");
        frmR.txtHabitaciones.setText("");
        frmR.txtPersonas.setText("");
        frmR.txtCliente.setText("");
        frmR.txtcedula.setText("");
        frmR.txtPreciototal.setText("");
        frmR.dateFechaInicio.setDate(null);
        frmR.dateFechaFin.setDate(null);

    }

     //Listar 
    public void Listar() {
        frmR.tblReservas.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel md = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        frmR.tblReservas.setRowHeight(20);
        JButton BOTONModificar = new JButton("Modificar");
        BOTONModificar.setName("m");
        JButton BOTONEliminar = new JButton("Eliminar");
        BOTONEliminar.setName("e");

        ResultSet rs = ConsultasCliente.ListarTabla("select * from RESERVAS ORDER BY IDRESERVA");
        md.setColumnIdentifiers(new Object[]{"IdReserva", "Cliente", "Cedula", "Hotel", "Habitaciones", "Personas", "Fecha Inicio", "Fecha Fin", "Precio Total", "Estado", "", ""});
        try {
            while (rs.next()) {
                md.addRow(new Object[]{rs.getInt("IdReserva"), rs.getString("Cliente"), rs.getString("Cedula"), rs.getString("Hotel"),
                    rs.getInt("Habitaciones"), rs.getInt("Personas"), rs.getDate("FechaInicio"), rs.getDate("FechaFin"), rs.getDouble("PrecioTotal"), rs.getString("Estado"), BOTONModificar, BOTONEliminar});
                frmR.tblReservas.setModel(md);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
