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

/**
 * Clase que guarda y genera la informacion y metodos de cada partida jugada
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class PartidaDAO {

    private int idpartida;
    private int puntObtenida;
    static int tablero[][] = new int[8][8];

    public int getIdpartida() {
        return idpartida;
    }

    public void setIdpartida(int idpartida) {
        this.idpartida = idpartida;
    }

    public int getPuntObtenida() {
        return puntObtenida;
    }

    public void setPuntObtenida(int puntObtenida) {
        this.puntObtenida = puntObtenida;
    }

    /**
     * Metodo que guarda el tiro de cada jugador en una matriz que representa al
     * tablero
     *
     * @param posX Representa el identificador de la posicion horizontal en el
     * tablero
     * @param posY Representa el identificador de la posicion vertical en el
     * tablero
     * @param turno Nos dice de quien es el tiro que se esta guardando
     */
    public void guardarTiro(int posX, int posY, int turno) {
        this.tablero[posX][posY] = turno;

    }

    /**
     * Metodo que busca en el tablero una agrupacion de cinco fichas 
     * por columnas
     *
     * @param turno Nos dice de que jugador es el tiro
     * @return Retorna verdadero si se encuentra el gane en alguna columna
     */
    public boolean validarColumna(int turno) {
        int contador = 0;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero.length; columna++) {
                if (turno == tablero[columna][fila]) {
                    contador++;
                    if (contador == 5) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
            contador = 0;
        }
        return false;
    }

    /**
     * Metodo que busca en el tablero una agrupacion de cinco fichas por filas
     *
     * @param turno Nos dice de que jugador es el tiro
     * @return Retorna verdadero si se encuentra el gane en alguna fila
     */
    public boolean validarFila(int turno) {
        int contador = 0;
        for (int fila = 0; fila < tablero.length; fila++) {
            for (int columna = 0; columna < tablero.length; columna++) {
                if (turno == tablero[fila][columna]) {
                    contador++;
                    if (contador == 5) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
            contador = 0;
        }
        return false;
    }

    /**
     * Metodo que busca una agrupacion de cinco fichas de manera diagonal de
     * izquierda a derecha
     *
     * @param turno Nos dice de que jugador es el tiro
     * @return Retorna verdadero si se encuentra el gane en alguna diagonal
     */
    public boolean validarDiagonalIzquierda(int turno) {
        boolean resultado = false;
        for (int fila = 0; fila < 8 - 4; fila++) {
            for (int columna = 0; columna < 8 - 4; columna++) {
                if (tablero[fila][columna] == turno
                        && tablero[fila][columna] == tablero[fila + 4][columna + 4]
                        && tablero[fila][columna] == tablero[fila + 3][columna + 3]
                        && tablero[fila][columna] == tablero[fila + 2][columna + 2]
                        && tablero[fila][columna] == tablero[fila + 1][columna + 1]) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }

    /**
     * Metodo que busca una agrupacion de cinco fichas de manera diagonal de
     * derecha a izquierda
     *
     * @param turno Nos dice de que jugador es el tiro
     * @return Retorna verdadero si se encuentra el gane en alguna diagonal
     */
    public boolean validarDiagonalDerecha(int turno) {
        boolean resultado = false;
        for (int fila = 4; fila < 8; fila++) {
            for (int columna = 0; columna < 8 - 4; columna++) {
                if (tablero[fila][columna] == turno
                        && tablero[fila][columna] == tablero[fila - 4][columna + 4]
                        && tablero[fila][columna] == tablero[fila - 3][columna + 3]
                        && tablero[fila][columna] == tablero[fila - 2][columna + 2]
                        && tablero[fila][columna] == tablero[fila - 1][columna + 1]) {
                    resultado = true;
                }
            }
        }
        return resultado;
    }
    
    /**
     * Metodo que recorre el tablero en busca del empate
     * @return Retorna verdadero si el tablero esta lleno y no hay ganador 
     */
    public boolean validarEmpate() {
        int contador = 0;
        for (int fila = 0; fila <= 7; fila++) {
            for (int columna = 0; columna <= 7; columna++) {
                if (tablero[fila][columna] != 0) {
                    contador++;
                    if (contador > 16) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * Metodo que limpia el tablero despues de terminar una partida llenandolo 
     * de ceros
     */
    public void limpiarTablero() {
        for (int fila = 0; fila <= 7; fila++) {
            for (int columna = 0; columna <= 7; columna++) {
                tablero[fila][columna] = 0;
            }

        }
    }

    public int[][] getTablero() {
        return tablero;
    }

}
