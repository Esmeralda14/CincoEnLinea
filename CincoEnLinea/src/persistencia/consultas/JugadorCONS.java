/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.consultas;

import Controladores.JugadoresJpaController;
import logica.JugadorLOG;
import Persistencia.Jugadores;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marianacro
 */
public class JugadorCONS {
    
    public String validarInisioSesion(JugadorLOG jugador){
      String message = "Unknow";
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CincoEnLineaPU", null);
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
    
    public Jugadores registrarJugador(){
        
        return jugador;
    } 
}
