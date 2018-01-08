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
package GUI;

import Dominio.PartidaDAO;

/**
 * Clase auxiliar donde que se ocupara para la declaración e implementación de
 * herramientas para la obtención de datos del tablero.
 *
 * @author Mariana Cadena Romero
 * @author Esmeralda Jimenez Ramos
 */
public class AuxiliarTablero {

    PartidaDAO partida = new PartidaDAO();

    public void separarPosicion(String nombreBoton, int turno) {
        String posX;
        String posY;
        int cont = 0;
        posX = nombreBoton.substring(cont, cont + 1);
        posY = nombreBoton.substring(cont + 1, cont + 2);
        partida.guardarTiro(convertirPosicion(posX), convertirPosicion(posY), turno);
    }

    /**
     * Metodo que separa el nombre del boton para obtener las cordenada del tiro
     * en el tablero
     *
     * @param nombreBoton Nombre que tiene el boton en donde se realizo el tiro
     * @return Un arreglo con las coordenadas convertidas a numeros
     */
    public int[] obtenerPosicion(String nombreBoton) {
        char[] coordenadas = nombreBoton.toCharArray();
        int[] coordenadasObtenidas = {convertirPosicion(String.valueOf(coordenadas[0])), convertirPosicion(String.valueOf(coordenadas[1]))};
        return coordenadasObtenidas;
    }

    /**
     * Metodo que convierte un caracter a un digito en especifico
     *
     * @param posicion Caracter del nombre del boton
     * @return El numero correspondiente al caracter recibido
     */
    public int convertirPosicion(String posicion) {
        int posicionObtenida = 0;
        switch (posicion) {
            case "A":
                posicionObtenida = 0;
                break;

            case "B":
                posicionObtenida = 1;
                break;

            case "C":
                posicionObtenida = 2;
                break;

            case "D":
                posicionObtenida = 3;
                break;

            case "E":
                posicionObtenida = 4;
                break;

            case "F":
                posicionObtenida = 5;
                break;

            case "G":
                posicionObtenida = 6;
                break;

            case "H":
                posicionObtenida = 7;
                break;
        }
        return posicionObtenida;
    }

}
