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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Clase que contiene los metodos que ayudan con la administracion del inicio de
 * sesión y registro de los usuarios
 * @author Esmeralda Jimenez Ramos 
 * @author Mariana Cadena Romero 
 */
public class AuxiliarDAO {
    /**
     * Metodo que realiza el hasheo de cualquier texto que reciba, es utilizado
     * para la encriptacion de la contraseña del usuario al momento del inicio 
     * de sesión.
     * @param string texto que sera hasheado
     * @return retorna una cadena de texto el cual ya fue hasheado
     * @throws NoSuchAlgorithmException Esta excepción se produce cuando se 
     * solicita un algoritmo criptográfico particular pero no está disponible 
     * en el entorno 
     */
    public String makeHash(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(string.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }
}
