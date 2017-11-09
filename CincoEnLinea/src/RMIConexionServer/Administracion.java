/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIConexionServer;
import java.rmi.Remote;
import java.rmi.RemoteException;
import logica.JugadorLOG;

/**
 *
 * @author marianacro
 */
public interface Administracion extends Remote {
    public String iniciarSesion(JugadorLOG jugador) throws RemoteException;
}
