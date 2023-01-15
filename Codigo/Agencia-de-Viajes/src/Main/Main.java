
package Main;


import Modelo.Cliente;
import Modelo.ConsultasCliente;
import Vista.MenuPrincipal;
import Vista.frmCliente;

/**
 *
 * @author Pc
 */
public class Main {

    public static void main(String[] args) {
        Cliente mod = new Cliente();
        ConsultasCliente modC =new ConsultasCliente();
        frmCliente frm=new frmCliente();
        MenuPrincipal men= new MenuPrincipal();
        men.setVisible(true);
        

    }
}
