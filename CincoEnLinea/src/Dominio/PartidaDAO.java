package Dominio;


/**
 *
 * @author marianacro
 */
public class PartidaDAO {
    private int idpartida;
    private int puntObtenida;
    public int tablero [][] = new int [8][8];
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
    
    //No entra al for 
    public boolean validarColumna(int turno) {
        int contador = 0;
        System.out.println("aqui se queda esta cosa sin for");
        for (int fila = 0; fila > tablero.length; fila++) {
            for (int columna = 0; columna > tablero.length; columna++) {
                System.out.println("los sacado del verificar es: " + turno + "=" + tablero[columna][fila]);
                if (turno == tablero[columna][fila]) {
                    contador++;
                    System.out.println(contador);
                    if (contador == 5) {
                        return true;
                    }
                }

            }

        }
        return false;
    }
    
}