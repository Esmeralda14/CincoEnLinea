/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.consultas;

import Controladores.JugadoresJpaController;
import Dominio.JugadorDAO;
import Persistencia.Jugadores;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marianacro
 */
public class JugadorCONS {
    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CincoEnLineaPU");
    EntityManager entitymanager = emfactory.createEntityManager();
    
    public String validarInisioSesion(JugadorDAO jugador){
      String message = "Unknow";
      JugadoresJpaController controller = new JugadoresJpaController();
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
      return message;
    }
    
    
    
    
    public boolean registrarJugador(Jugadores jugador) throws Exception{
    JugadoresJpaController controller = new JugadoresJpaController();
    controller.create(jugador);
          return true;
      }
    
    public List<Jugadores> recuperarUsuariosRankiados(){
        List<Jugadores> jugadores;
        jugadores = entitymanager.createNamedQuery("usuarios.puntaje").getResultList();
        return jugadores;
    }
    
    public List<Jugadores> recuperarPuntajeJugadores(){
        List<Jugadores> puntajes;
        puntajes = entitymanager.createNamedQuery("puntaje.jugadores").getResultList();
        return puntajes;
    }
}
