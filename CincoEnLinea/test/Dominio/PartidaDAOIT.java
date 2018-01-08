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
package Dominio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase que se encarga de probar el funcionamiento de los metodos relacionados
 * con la partida
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class PartidaDAOIT {

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

    }

    @Test
    public void testValidarColumnaFalso() {
        System.out.println("validarColumnaResultadoFalso");
        int turno = 2;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(1, 0, 1);
        instance.guardarTiro(2, 0, 1);
        instance.guardarTiro(3, 0, 1);
        instance.guardarTiro(4, 0, 1);
        instance.guardarTiro(5, 0, 1);

        boolean expResult = false;
        boolean result = instance.validarColumna(turno);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidarFilaFalso() {
        System.out.println("validarFilaResultadoFalso");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(0, 1, 2);
        instance.guardarTiro(0, 2, 2);
        instance.guardarTiro(0, 3, 2);
        instance.guardarTiro(0, 4, 2);
        instance.guardarTiro(0, 5, 2);
        boolean expResult = false;
        boolean result = instance.validarFila(turno);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidarDiagonalIzquierdaFalso() {
        System.out.println("validarDiagonalIzquierdaResultadoFalso");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(0, 0, 1);
        instance.guardarTiro(1, 1, 1);
        instance.guardarTiro(2, 2, 2);
        instance.guardarTiro(3, 3, 1);
        instance.guardarTiro(4, 4, 1);
        boolean expResult = false;
        boolean result = instance.validarDiagonalIzquierda(turno);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidarDiagonalDerechaFalso() {
        System.out.println("validarDiagonalDerechaResultadoFalso");
        int turno = 1;
        PartidaDAO instance = new PartidaDAO();
        instance.guardarTiro(2, 6, 1);
        instance.guardarTiro(3, 5, 2);
        instance.guardarTiro(4, 4, 1);
        instance.guardarTiro(5, 3, 1);
        instance.guardarTiro(6, 2, 1);
        boolean expResult = false;
        boolean result = instance.validarDiagonalDerecha(turno);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
