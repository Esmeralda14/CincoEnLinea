/**
 * Nombre del proyecto:
 *    5 en linea.
 *
 * Nombres de los desarrolladores:
 *    Mariana Cadena Romero
 *    Esmeralda Jimenez Ramos
 *
 * Fecha en la que se inició el programa:
 *    28-noviembre-2017
 *
 * Descripción: Juego que lleva por nombre '5 en linea' el cual esta disponible
 * para todo publico, tiene la capacidad de soportar multijugador de dos
 * participantes en tiempo real y de realizar registro de nuevos usuarios,
 * así como consultar la puntuacion de todos los jugadores.
 */
package persistencia.consultas;

import Dominio.JugadorDAO;
import Persistencia.Jugadores;
import org.junit.Test;
import static org.junit.Assert.*;
import Persistencia.consultas.JugadorCONS;

/**
 * Clase que se encarga de verificcar el funcionamiento de los metodos
 * relacionados a la administracion de los jugadores
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class JugadorCONSIT {

    @Test
    public void testRegistrarJugador() throws Exception {
        System.out.println("registrarJugador");
        Jugadores jugador = new Jugadores("mariana", "12345");
        JugadorCONS instance = new JugadorCONS();
        boolean expResult = true;
        boolean result = instance.registrarJugador(jugador);
        System.out.println(result);
        assertEquals(expResult, result);
    }

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
    public void testValidadInisioSesionContrasenaIncorrecta() {
        System.out.println("Validar inicio de sesión no"
                + "exitoso debido a la constraseña incorrecta");
        JugadorDAO jugador = new JugadorDAO("marianacro", "mc12345");
        JugadorCONS instance = new JugadorCONS();
        String expResult = "2";
        String result = instance.validarInisioSesion(jugador);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidadInisioSesionUsuarioIncorrecto() {
        System.out.println("Validar inicio de sesión no"
                + "exitoso debido al usuario incorrecto");
        JugadorDAO jugador = new JugadorDAO("esme1", "perrito");
        JugadorCONS instance = new JugadorCONS();
        String expResult = "3";
        String result = instance.validarInisioSesion(jugador);
        assertEquals(expResult, result);
    }

}
