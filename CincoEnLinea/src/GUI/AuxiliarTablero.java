/**
 * @author Mariana Cadena Romero
 * @author Esmeralda Jimenez Ramos
 */
package GUI;

import Dominio.PartidaDAO;


/**
 * Clase auxiliar donde que se ocupara para la declaración e implementación de
 * herramientas para la obtención de datos del tablero.
 */
public class AuxiliarTablero {
    PartidaDAO partida = new PartidaDAO();
    
    public void separarPosicion(String nombreBoton, int turno){
        String posX ;
        String posY;
        int cont = 0;
        posX = nombreBoton.substring(cont, cont+1);
        posY = nombreBoton.substring(cont + 1, cont+2);
        System.out.println(posX + "-" + posY);
        partida.guardarTiro(convertirPosicion(posX), convertirPosicion(posY), turno);
    }
    
    public int[] obtenerPosicion(String nombreBoton){
        char[] coordenadas = nombreBoton.toCharArray();
        int[] coordenadasObtenidas = {convertirPosicion(String.valueOf(coordenadas[0])), convertirPosicion(String.valueOf(coordenadas[1]))};
        return coordenadasObtenidas; 
    }
    
    public int convertirPosicion(String posicion){
        int posicionObtenida = 0;
        switch(posicion){
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
