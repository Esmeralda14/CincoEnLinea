/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIConexionServer;

import controladores.JugadoresJpaController;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.JugadorLOG;
import persistencia.Jugadores;

/**
 *
 * @author marianacro
 */
public class AdminServer implements Administracion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            AdminServer obj = new AdminServer();
            Administracion admin = (Administracion) UnicastRemoteObject.exportObject(obj, 0);
            
            //Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            //registralo como administrador del juego en tus sevidores
            registry.bind("Administrador del juego", admin);
            System.err.println("Server ready");
        }catch (AlreadyBoundException | RemoteException e){
            System.err.println("Server exception: " + e.toString());
        }
    }

    @Override
    public String iniciarSesion(JugadorLOG jugador) throws RemoteException {
        String message = "Unknown";
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CincoEnLineaPU", null);
      JugadoresJpaController controller = new JugadoresJpaController();
      Jugadores jugadores = null;        
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
         message=  "3";
      }
      return message;
    }
    }

    

