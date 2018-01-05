/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marianacro
 */
public class PartidaDAOIT {
    
    public PartidaDAOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }




    /**
     * Test of guardarTiro method, of class PartidaDAO.
     */
    @Test
    public void testGuardarTiro() {
        System.out.println("guardarTiro");
        int posX = 0;
        int posY = 1;
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(posX, posY, turno);
        assertEquals(1, instance.getTablero()[0][1]);
        
    }

    /**
     * Test of validarColumna method, of class PartidaDAO.
     */
    @Test
    public void testValidarColumna() {
        System.out.println("validarColumna");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(1, 0, 1);
        instance.guardarTiro(2, 0, 1);
        instance.guardarTiro(3, 0, 1);
        instance.guardarTiro(4, 0, 1);
        instance.guardarTiro(5, 0, 1);
        
        boolean expResult = true;
        boolean result = instance.validarColumna(turno);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarFila method, of class PartidaDAO.
     */
    @Test
    public void testValidarFila() {
        System.out.println("validarFila");
        int turno = 2;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(0, 1, 2);
        instance.guardarTiro(0, 2, 2);
        instance.guardarTiro(0, 3, 2);
        instance.guardarTiro(0, 4, 2);
        instance.guardarTiro(0, 5, 2);
        boolean expResult = true;
        boolean result = instance.validarFila(turno);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarDiagonalIzquierda method, of class PartidaDAO.
     */
    @Test
    public void testValidarDiagonalIzquierda() {
        System.out.println("validarDiagonalIzquierda");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(0, 0, 1);
        instance.guardarTiro(1, 1, 1);
        instance.guardarTiro(2, 2, 1);
        instance.guardarTiro(3, 3, 1);
        instance.guardarTiro(4, 4, 1);
        boolean expResult = true;
        boolean result = instance.validarDiagonalIzquierda(turno);
        assertEquals(expResult, result);
    }

    /**
     * Test of validarDiagonalDerecha method, of class PartidaDAO.
     */
    @Test
    public void testValidarDiagonalDerecha() {
        System.out.println("validarDiagonalDerecha");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(2, 6, 1);
        instance.guardarTiro(3, 5, 1);
        instance.guardarTiro(4, 4, 1);
        instance.guardarTiro(5, 3, 1);
        instance.guardarTiro(6, 2, 1);
        boolean expResult = true;
        boolean result = instance.validarDiagonalDerecha(turno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
