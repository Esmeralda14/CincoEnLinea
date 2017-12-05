package Dominio;


/**
 *
 * @author marianacro
 */
public class PartidaDAO {
    private int idpartida;
    private int puntObtenida;
    static int tablero [][] = new int [8][8];
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

    public void guardarTiro(int posX, int posY, int turno) {
        this.tablero[posX][posY]=turno;
        System.out.println("pos en tablero " + posX +"-"+ posY + "=" + tablero[posX][posY]);
        
    }
    
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
}
