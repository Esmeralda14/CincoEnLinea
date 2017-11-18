
package GUI;

import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logica.JugadorLOG;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class RegistrarseController implements Initializable {

   @FXML
   private Label labelRegistrarse;
   
   @FXML
   private Button espanol;
   
   @FXML
   private Button ingles;
   
   @FXML
   private Label usuario;
   
   @FXML
    private TextField fieldUsuario;
   
   @FXML
    private TextField fieldContraseña;
   
   @FXML
   private Label labelContrasena;
   
   @FXML
   private Button Bregistrarse;
   
   @FXML 
   private Button regresar;
   
   ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
        Bregistrarse.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                JugadorCONS jugador = new JugadorCONS();
                try {
                    jugador.registrarJugador(obtenerValores());
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
    
    public void configurarIdioma(){
        labelRegistrarse.setText(resources.getString("labelRegistrarse"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        usuario.setText(resources.getString("labelUsuario"));
        labelContrasena.setText(resources.getString("labelContrasena"));
        Bregistrarse.setText(resources.getString("Bregistrarse"));
        regresar.setText(resources.getString("regresar"));
        
    }
    
    @FXML
    public void cambiarIdiomaUS(){
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }
    
    @FXML
    public void cambiarIdiomaMX(){
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
        
    }
    
    private Jugadores obtenerValores() throws NoSuchAlgorithmException{
    Jugadores entidadJugador = null;
   if(fieldUsuario.getText().equals("") || fieldContraseña.getText().equals("")){     
   }else{
      String user = fieldUsuario.getText();
      String clave = fieldContraseña.getText();
      entidadJugador = new Jugadores(user, makeHash(clave));
      
   }
   return entidadJugador;
}
    
    private String makeHash(String string) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash=messageDigest.digest(string.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i=0; i<hash.length;i++){
                stringBuilder.append(Integer.toString((hash[i]&0xff)+0x100,16).substring(1));
        }        
        return  stringBuilder.toString();
}
}
