/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIConexionClient;

import RMIConexionServer.Administracion;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import logica.JugadorLOG;

/**
 *
 * @author marianacro
 */
public class AdminClient {
     static String host;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        host = (args.length < 1) ? null : args[0];
        
    }
    
    public static String conexionRMI(JugadorLOG jugador){
        try{
            Registry registry = LocateRegistry.getRegistry(host);
            Administracion admin = (Administracion) registry.lookup("Login practico");
            String responseUsuario = admin.iniciarSesion(jugador);
            
            
        }catch (NotBoundException | RemoteException e){
            System.err.println("Client exception: " + e.toString());
        }
        return "Cliente conectado";
    }
    
}
