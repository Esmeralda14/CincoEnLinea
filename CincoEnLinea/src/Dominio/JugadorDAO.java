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
 * Clase que guarda la informacion de cada jugador que se registra e
 * inicia sesión
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class JugadorDAO {
    String usuario;
    String clave;
    int puntuacionTotal;

    public JugadorDAO(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
        public JugadorDAO(String usuario,int puntuacionTotal) {
        this.usuario = usuario;
        this.puntuacionTotal = puntuacionTotal;
    }
    
    public JugadorDAO(){}

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }
    
    
}
