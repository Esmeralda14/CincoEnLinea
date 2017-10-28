/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.consultas;

import controladores.JugadoresJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.JugadorLOG;
import persistencia.Jugadores;

/**
 *
 * @author marianacro
 */
public class JugadorCONS {
    JugadoresJpaController controllerJugador;
    
    
    public boolean registrarJugador(Object jugador) throws Exception{
        return true;
    }
    
    
}
