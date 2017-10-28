/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.consultas;

import logica.JugadorLOG;
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
public class JugadorCONSIT {
    JugadorLOG jug = new JugadorLOG("esme","perrito");
    public JugadorCONSIT() {
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
     * Test of registrarJugador method, of class JugadorCONS.
     */
    @Test
    public void testRegistrarJugador() throws Exception {
        System.out.println("registrarJugador");
        Object jugador = jug;
        JugadorCONS instance = new JugadorCONS();
        boolean resultEsperado = instance.registrarJugador(jugador);
        boolean result = true;
        assertEquals(result, resultEsperado);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
