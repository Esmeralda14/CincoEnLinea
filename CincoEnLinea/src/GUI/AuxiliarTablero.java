/**
 * @author Mariana Cadena Romero
 * @author Esmeralda Jimenez Ramos
 */
package GUI;


/**
 * Clase auxiliar donde que se ocupara para la declaración e implementación de
 * herramientas para la obtención de datos del tablero.
 */
public class AuxiliarTablero {
    
    public void separarPosicion(String nombreBoton){
        String posX ="";
        String posY="";
        int cont = 0;
        posX = nombreBoton.substring(cont, cont+1);
        posY = nombreBoton.substring(cont + 1, cont+2);
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
