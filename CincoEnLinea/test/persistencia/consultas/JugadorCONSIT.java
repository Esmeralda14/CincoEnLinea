/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.consultas;

import Dominio.JugadorDAO;
import Persistencia.Jugadores;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Persistencia.consultas.JugadorCONS;


/**
 *
 * @author marianacro
 */
public class JugadorCONSIT {
    
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

  
    @Test
    public void testRegistrarJugador() throws Exception {
        System.out.println("registrarJugador");
        Jugadores jugador = new Jugadores("mariana","12345");
        JugadorCONS instance = new JugadorCONS();
        boolean expResult = true;
        boolean result = instance.registrarJugador(jugador);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    
   
    /**
     * Pruebas unitarias para el metodo ValidadInisioSesion
     */
    @Test
    public void testValidarInisioSesion() {
        System.out.println("validar Inisiom de Sesion exitoso");
        JugadorDAO jugador = new JugadorDAO("esme14", "5ac782fe6107c6e7c750f2b96a9363503b993a382ff405f25b79dc9faf8d8734");
        JugadorCONS instance = new JugadorCONS();
        String expResult = "1";
        String result = instance.validarInisioSesion(jugador);
        System.out.println(result);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidadInisioSesionContrasenaIncorrecta(){
        System.out.println("Validar inicio de sesión no"+
                "exitoso debido a la constraseña incorrecta");
        JugadorDAO jugador = new JugadorDAO("marianacro", "mc12345");
        JugadorCONS instance = new JugadorCONS();
        String expResult = "2";
        String result = instance.validarInisioSesion(jugador);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidadInisioSesionUsuarioIncorrecto(){
        System.out.println("Validar inicio de sesión no"+
                "exitoso debido al usuario incorrecto");
        JugadorDAO jugador = new JugadorDAO("esme1", "perrito");
        JugadorCONS instance = new JugadorCONS();
        String expResult = "3";
        String result = instance.validarInisioSesion(jugador);
        assertEquals(expResult, result);
    }
    
    
    
    
    
}
