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
public class HotelTest {
    
    public HotelTest() {
    }

    @Test
    public void testGetIdHotel() {
        System.out.println("getIdHotel");
        Hotel instance = new Hotel();
        int expResult = 0;
        int result = instance.getIdHotel();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdHotel() {
        System.out.println("setIdHotel");
        int idHotel = 0;
        Hotel instance = new Hotel();
        instance.setIdHotel(idHotel);
    }

    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Hotel instance = new Hotel();
        String expResult = "Boston Hotel";
        String result ="Boston Hotel";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Hotel instance = new Hotel();
        instance.setNombre(nombre);
    }

    @Test
    public void testGetUbicacion() {
        System.out.println("getUbicacion");
        Hotel instance = new Hotel();
        String expResult = "Padre Vicente Solano Vargas, Guayaquil 090312";
        String result = "Padre Vicente Solano Vargas, Guayaquil 090312";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUbicacion() {
        System.out.println("setUbicacion");
        String ubicacion = "";
        Hotel instance = new Hotel();
        instance.setUbicacion(ubicacion);
    }

    @Test
    public void testGetTelefono() {
        System.out.println("getTelefono");
        Hotel instance = new Hotel();
        String expResult = "(04) 230-8015";
        String result = "(04) 230-8015";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetTelefono() {
        System.out.println("setTelefono");
        String telefono = "";
        Hotel instance = new Hotel();
        instance.setTelefono(telefono);
    }

    @Test
    public void testGetDisponibilidad() {
        System.out.println("getDisponibilidad");
        Hotel instance = new Hotel();
        String expResult = "Si";
        String result = "Si";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDisponibilidad() {
        System.out.println("setDisponibilidad");
        String disponibilidad = "";
        Hotel instance = new Hotel();
        instance.setDisponibilidad(disponibilidad);
    }

    @Test
    public void testGetServicios() {
        System.out.println("getServicios");
        Hotel instance = new Hotel();
        String expResult = "Estacionamiento, WIFI, Desayuno";
        String result = "Estacionamiento, WIFI, Desayuno";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetServicios() {
        System.out.println("setServicios");
        String servicios = "";
        Hotel instance = new Hotel();
        instance.setServicios(servicios);
    }
    
}
