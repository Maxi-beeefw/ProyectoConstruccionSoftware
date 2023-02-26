/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Modelo;

import java.sql.ResultSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class ConsultasHotelTest {
    
    public ConsultasHotelTest() {
    }

    @Test
    public void testRegistrar() {
        System.out.println("registrar");
        Hotel h = new Hotel();
        ConsultasHotel instance = new ConsultasHotel();
        boolean expResult = instance.registrar(h);
        boolean result = instance.registrar(h);
        assertEquals(expResult, result);
    }

    @Test
    public void testModificar() {
        System.out.println("modificar");
        Hotel h = new Hotel();
        ConsultasHotel instance = new ConsultasHotel();
        boolean expResult = instance.modificar(h);;
        boolean result = instance.modificar(h);
        assertEquals(expResult, result);
    }

    @Test
    public void testListarTabla() {
        System.out.println("ListarTabla");
        String consulta = "";
        ResultSet expResult = ConsultasHotel.ListarTabla(consulta);
        ResultSet result = ConsultasHotel.ListarTabla(consulta);
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminar() {
        System.out.println("Eliminar");
        String id = "2";
        boolean expResult = ConsultasHotel.Eliminar(id);
        boolean result = ConsultasHotel.Eliminar(id);
        assertEquals(expResult, result);
    }
    
}
