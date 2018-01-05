/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import static org.eclipse.persistence.sessions.SessionProfiler.Transaction;

/**
 *
 * @author marianacro
 */
public class JugadorCONS {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CincoEnLineaPU");
    EntityManager entitymanager = emfactory.createEntityManager();
    
    public String validarInisioSesion(JugadorDAO jugador){
      String message = "Unknow";
      JugadoresJpaController controller = new JugadoresJpaController(emfactory);
      Jugadores jugadores = new Jugadores();        
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
        System.out.println(message);
      return message;
    }
    
    public boolean validarUsuarioRepetido(String usuario) {
        JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        Jugadores jugadores = new Jugadores();
        try {
            jugadores = controller.findJugadores(usuario);
            if (jugadores.getUsuario().equals(usuario)) {
                return true;
            }
        }catch (Exception e) {

        }
        return false;
    }
    
    
    
    
    
    public boolean registrarJugador(Jugadores jugador) {
    JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        try {
            controller.create(jugador);
        } catch(PreexistingEntityException e){
                
            }catch (NullPointerException e){
            }catch (Exception ex) {
            Logger.getLogger(JugadorCONS.class.getName()).log(Level.SEVERE, null, ex);
        }
          return true;
      }
    
    public List<Jugadores> recuperarUsuariosRankiados(){
        List<Jugadores> jugadores;
        jugadores = entitymanager.createNamedQuery("Jugadores.findByPuntuacionTotal").getResultList();
         System.out.println(jugadores.get(0).toString());
        return jugadores;
    }
    
    public void actualizarPuntuacion(String id) {
        JugadoresJpaController controller = new JugadoresJpaController(emfactory);
        Jugadores jugador = new Jugadores();
        jugador = controller.findJugadores(id);
        jugador.setPuntuacionTotal(jugador.getPuntuacionTotal() + 10);
    }
    
//    public List<Jugadores> recuperarPuntajeJugadores(){
//        List<Jugadores> puntajes;
//        puntajes = entitymanager.createNamedQuery("puntaje.jugadores").getResultList();
//        return puntajes;
//    }
}
