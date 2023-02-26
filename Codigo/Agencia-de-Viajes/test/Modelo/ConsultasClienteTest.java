/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import Vista.frmCliente;
import Vista.frmModCliente;
import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class ConsultasClienteTest {
    
    public ConsultasClienteTest() {
    }

    @Test
    public void testRegistrar() {
        System.out.println("registrar");
        Cliente c = new Cliente();
        ConsultasCliente instance = new ConsultasCliente();
        boolean expResult = instance.registrar(c);;
        boolean result = instance.registrar(c);
        assertEquals(expResult, result);
    }

    @Test
    public void testModificar() {
        System.out.println("modificar");
        Cliente c = new Cliente();
        ConsultasCliente instance = new ConsultasCliente();
        boolean expResult = instance.modificar(c);
        boolean result = instance.modificar(c);
        assertEquals(expResult, result);
    }

    @Test
    public void testListarTabla() {
        System.out.println("ListarTabla");
        String consulta = "";
        ResultSet expResult = ConsultasCliente.ListarTabla(consulta);
        ResultSet result = ConsultasCliente.ListarTabla(consulta);
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminar() {
        System.out.println("Eliminar");
        String id = "2";
        boolean expResult =  ConsultasCliente.Eliminar(id);
        boolean result = ConsultasCliente.Eliminar(id);
        assertEquals(expResult, result);
    }
    
}
