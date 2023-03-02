/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controlador;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hp
 */
public class RenderTest {
    JTable table;
    Object value;
    boolean isSelected;
    boolean hasFocus;
    int row;
    int column;
    public RenderTest() {
    }

    @Test
    public void testGetTableCellRendererComponent() {
         if (value instanceof JButton) {
            JButton btn= (JButton)value;
        }
    }
    
}
