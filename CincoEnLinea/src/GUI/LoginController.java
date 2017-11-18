/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.JugadorLOG;
import Persistencia.consultas.JugadorCONS;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class LoginController implements Initializable {
    @FXML
    private Label labelUsuario;

    @FXML
    private Label inicioSesion;

    @FXML
    private Label labelContraseña;

    @FXML
    private TextField fieldUsuario;

    @FXML
    private TextField fieldContraseña;

    @FXML
    private Button ingresar;

    @FXML
    private Button registrar;
    
    @FXML
    private Button ingles;
    
    @FXML
    private Button espanol;

    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    private Stage stage = new Stage();
    
    

     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.resources = rb;
      
      ingresar.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            String resultado = "";
            JugadorCONS jugadorCONS = new JugadorCONS();
          try {
              resultado = jugadorCONS.validarInisioSesion(obtenerValores());
          } catch (NoSuchAlgorithmException ex) {
              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
          }
          switch (resultado) {
              case "1":
                  try {
                      AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"), resources);
                      Scene scenePartida = new Scene(pane);
                      stage.setScene(scenePartida);
                      stage.showAndWait();
                  } catch (IOException ex) {
                      Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  break;
              case "2":
                  mensajeAlerta("2");
               break;
              case "3":
                  mensajeAlerta("3");
                  break;
                
            }
          
          });
      
          registrar.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Registrarse.fxml"), resources);
                Scene sceneRegistrarse = new Scene(pane);
                stage.setScene(sceneRegistrarse);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
      });
      
      
     
      
      
    }
    
    private void mensajeAlerta(String resultado){
        String mensaje = "";
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("¡Advertencia!");
        if(resultado.equals("2")){
            mensaje = "Contraseña incorrecta";
            alert.setHeaderText(mensaje);
        }
        if(resultado.equals("3")){
            mensaje = "Este usuario no existe o esta incorrecto";
            alert.setHeaderText(mensaje);
        }
        alert.showAndWait();
    }
    
    public void configurarIdioma(){
        inicioSesion.setText(resources.getString("inicioSesion"));
        labelUsuario.setText(resources.getString("labelUsuario"));
        labelContraseña.setText(resources.getString("labelContrasena"));
        ingresar.setText(resources.getString("ingresar"));
        registrar.setText(resources.getString("registrar"));
        ingles.setText(resources.getString("english"));
        espanol.setText(resources.getString("espanol"));
        fieldContraseña.setText("");
        fieldUsuario.setText("");

    }
    
    
    

    @FXML
    public void cambiarIdiomaEs(){
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
    }
    
    @FXML
    public void cambiarIdiomaIngles(){
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }
    
    private JugadorLOG obtenerValores() throws NoSuchAlgorithmException{
    JugadorLOG jugador = null;
    
   if(fieldUsuario.getText().equals("") || fieldContraseña.getText().equals("")){     
   }else{
      String user = fieldUsuario.getText();
      String clave = fieldContraseña.getText();
      jugador = new JugadorLOG(user, makeHash(clave));
   }
   return jugador;
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
