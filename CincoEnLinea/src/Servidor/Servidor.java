/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Dominio.ConexionDAO;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marianacro
 */
public class Servidor {
    static ServerSocket serverSocket;
    static Socket socket;
    

    
    public static void prepararConexion(){
        ArrayList<String> jugadores = new ArrayList<>();
        try {  
            int port = 5000;
            System.out.println("El servidor esta corriendo");
            serverSocket = new ServerSocket(port);
            while(true){
                try{
//                    socket = serverSocket.accept();
                    ConexionDAO.socketEscritura = serverSocket.accept();
                    ConexionDAO.socketLectura = serverSocket.accept();
                    System.out.println("Cliente conectado :)");
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(ConexionDAO.socketLectura.getInputStream()));
                    ObjectOutputStream salida = new ObjectOutputStream(ConexionDAO.socketEscritura.getOutputStream());
                    String usuario;
                    usuario = entrada.readLine();
                    jugadores.add(usuario);
                    for (int i = 0; i < jugadores.size(); i++) {
                        salida.writeObject(jugadores.get(i));
                        salida.flush();
                    }
                    System.out.println(usuario);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
      
}
        public static void main (String [] args){
        prepararConexion();
    }
    
    
}
