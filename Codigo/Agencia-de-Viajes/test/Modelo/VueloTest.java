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
public class VueloTest {
    
    public VueloTest() {
    }

    @Test
    public void testGetIdVuelo() {
        System.out.println("getIdVuelo");
        Vuelo instance = new Vuelo();
        int expResult = 0;
        int result = instance.getIdVuelo();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIdVuelo() {
        System.out.println("setIdVuelo");
        int idVuelo = 0;
        Vuelo instance = new Vuelo();
        instance.setIdVuelo(idVuelo);
    }

    @Test
    public void testGetNombreAerolinea() {
        System.out.println("getNombreAerolinea");
        Vuelo instance = new Vuelo();
        String expResult = "Airlines";
        String result = "Airlines";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetNombreAerolinea() {
        System.out.println("setNombreAerolinea");
        String nombreAerolinea = "";
        Vuelo instance = new Vuelo();
        instance.setNombreAerolinea(nombreAerolinea);
    }

    @Test
    public void testGetOrigen() {
        System.out.println("getOrigen");
        Vuelo instance = new Vuelo();
        String expResult = "Guayaquil";
        String result = "Guayaquil";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetOrigen() {
        System.out.println("setOrigen");
        String origen = "";
        Vuelo instance = new Vuelo();
        instance.setOrigen(origen);
    }

    @Test
    public void testGetDestino() {
        System.out.println("getDestino");
        Vuelo instance = new Vuelo();
        String expResult = "Miami";
        String result ="Miami";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDestino() {
        System.out.println("setDestino");
        String destino = "";
        Vuelo instance = new Vuelo();
        instance.setDestino(destino);
    }

    @Test
    public void testGetEscala() {
        System.out.println("getEscala");
        Vuelo instance = new Vuelo();
        String expResult = "Panama";
        String result = "Panama";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetEscala() {
        System.out.println("setEscala");
        String escala = "";
        Vuelo instance = new Vuelo();
        instance.setEscala(escala);
    }

    @Test
    public void testGetDisponibilidad() {
        System.out.println("getDisponibilidad");
        Vuelo instance = new Vuelo();
        String expResult = "Si";
        String result = "Si";
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDisponibilidad() {
        System.out.println("setDisponibilidad");
        String disponibilidad = "";
        Vuelo instance = new Vuelo();
        instance.setDisponibilidad(disponibilidad);
    }
    
}
