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
package Persistencia.consultas;

import Dominio.JugadorDAO;
import Persistencia.Jugadores;
import PersistenciaControladores.JugadoresJpaController;
import PersistenciaControladores.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que realiza las autenticaciones del inicio de sesión y registro de
 * nuevos usuarios y a obtener la informacion requerida del usuarioen clases
 * superiores
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class JugadorCONS {

    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CincoEnLineaPU4");
    EntityManager entitymanager = emfactory.createEntityManager();

    /**
     * Metodo que valida que los datos ingresados en el login correspondan con
     * los registrados en la base de datos
     *
     * @param jugador Objeto de tipo JugadorDao que contiene la informacion del
     * jugador
     * @return Retorna un mensaje de tipo String dependiendo de si los datos son
     * correctos o no
     */
    public String validarInisioSesion(JugadorDAO jugador) {
        String message = "Unknow";
        JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        Jugadores jugadores;
        try {
            jugadores = controller.findJugadores(jugador.getUsuario());
            if (jugadores.getUsuario().equals(jugador.getUsuario())) {
                if (jugadores.getClave().equals(jugador.getClave())) {
                    message = "1";
                } else {
                    message = "2";
                }
            }
        } catch (NullPointerException e) {
            message = "3";
        }
        return message;
    }

    /**
     * Metodo que valida que el nombre de usuario que se intenta registrar no
     * exista en la base de datos
     *
     * @param usuario Nombre de usuario que se intenta registrar
     * @return Retorna verdadero si el usuario ya existe en la base de datos
     */
    public boolean validarUsuarioRepetido(String usuario) {
        JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        Jugadores jugadores;
        try {
            jugadores = controller.findJugadores(usuario);
            if (jugadores.getUsuario().equals(usuario)) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Metodo que realiza el registro del jugador en la base de datos
     *
     * @param jugador Objeto del tipo Jugador que contiene la informacion del
     * jugador a registrar
     * @return Retorna verdadero si el registro se realiza correctamente
     */
    public boolean registrarJugador(Jugadores jugador) {
        JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        try {
            controller.create(jugador);
        } catch (PreexistingEntityException e) {

        } catch (NullPointerException e) {
        } catch (Exception ex) {
            Logger.getLogger(JugadorCONS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Metodo que recupera de la base de datos los nombres de usuarios con su
     * puntuacion total de manera descendente
     *
     * @return Retorna una lista de tipo Jugadores que contiene los usuarios y
     * sus puntuaciones totales
     */
    public List<Jugadores> recuperarUsuariosRankiados() {
        List<Jugadores> jugadores;
        jugadores = entitymanager.createNamedQuery("Jugadores.findByPuntuacionTotal").getResultList();
        System.out.println(jugadores.get(0).toString());
        return jugadores;
    }

    /**
     * Metodo que actualiza la puntuacion de los jugadores despues de terminar
     * una partida
     *
     * @param id Identificador del jugador al cual se le actualizara la
     * puntuacion
     */
    public void actualizarPuntuacion(String id) {
        try {
            JugadoresJpaController controller = new JugadoresJpaController(emfactory);
            Jugadores jugador;
            jugador = controller.findJugadores(id);
            if (jugador.getPuntuacionTotal() != null) {
                jugador.setPuntuacionTotal(jugador.getPuntuacionTotal() + 10);
            } else {
                jugador.setPuntuacionTotal(10);
            }
            controller.edit(jugador);
        } catch (Exception ex) {
            Logger.getLogger(JugadorCONS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
