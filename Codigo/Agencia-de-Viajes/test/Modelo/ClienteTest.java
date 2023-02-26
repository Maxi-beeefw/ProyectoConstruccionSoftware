/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class ClienteTest {
    
    public ClienteTest() {
    }

    @Test
    public void testGetIdCliente() {
        System.out.println("getIdCliente");
        Cliente instance = new Cliente();
        int expResult = 0;
        int result = instance.getIdCliente();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdCliente() {
        System.out.println("setIdCliente");
        int idCliente = 0;
        Cliente instance = new Cliente();
        instance.setIdCliente(idCliente);
    }

    @Test
    public void testGetCedula() {
        System.out.println("getCedula");
        Cliente instance = new Cliente();
        String expResult = "0954310736";
        String result =  "0954310736";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCedula() {
        System.out.println("setCedula");
        String cedula = "";
        Cliente instance = new Cliente();
        instance.setCedula(cedula);
    }

    @Test
    public void testGetNombres() {
        System.out.println("getNombres");
        Cliente instance = new Cliente();
        String expResult = "Maximiliano";
        String result = "Maximiliano";
        assertEquals(expResult, result);
        
    }

    @Test
    public void testSetNombres() {
        System.out.println("setNombres");
        String nombres = "";
        Cliente instance = new Cliente();
        instance.setNombres(nombres);
    }

    @Test
    public void testGetApellidos() {
        System.out.println("getApellidos");
        Cliente instance = new Cliente();
        String expResult = "Cabrera";
        String result = "Cabrera";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetApellidos() {
        System.out.println("setApellidos");
        String apellidos = "";
        Cliente instance = new Cliente();
        instance.setApellidos(apellidos);
    }

    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Cliente instance = new Cliente();
        String expResult = "0962712966";
        String result = "0962712966";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = "";
        Cliente instance = new Cliente();
        instance.setTelefono(telefono);
    }

    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Cliente instance = new Cliente();
        String expResult = "Samborondon";
        String result =  "Samborondon";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDireccion() {
        System.out.println("setDireccion");
        String direccion = "";
        Cliente instance = new Cliente();
        instance.setDireccion(direccion);
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Cliente instance = new Cliente();
        String expResult = "maximilianocabrera885@gmail.com";
        String result = "maximilianocabrera885@gmail.com";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Cliente instance = new Cliente();
        instance.setEmail(email);
    }
    
}
