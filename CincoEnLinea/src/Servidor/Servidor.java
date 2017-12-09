/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
     int port = 5000;  
        try {
            System.out.println("El servidor esta corriendo");
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            System.out.println("Cliente conectado :)");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            String usuario;
            usuario = entrada.readLine();
            System.out.println(usuario);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
      
}
        public static void main (String [] args){
        prepararConexion();
    }
    
    
}
