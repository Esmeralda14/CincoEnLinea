/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logica.JugadorLOG;

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
      
      ingresar.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"), resources);
                 Scene scenePartida = new Scene(pane);
                stage.setScene(scenePartida);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
      
     
      
    }
    
    
    public void configurarIdioma(){
        inicioSesion.setText(resources.getString("inicioSesion"));
        labelUsuario.setText(resources.getString("labelUsuario"));
        labelContraseña.setText(resources.getString("labelContrasena"));
        ingresar.setText(resources.getString("ingresar"));
        registrar.setText(resources.getString("registrar"));
        ingles.setText(resources.getString("ingles"));
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
    
    private JugadorLOG obtenerValores(){
    JugadorLOG jugador = null;
   if(fieldUsuario.getText().equals("") || fieldContraseña.getText().equals("")){     
   }else{
      String user = fieldUsuario.getText();
      String clave = fieldContraseña.getText();
      jugador = new JugadorLOG(user, clave);
   }
   return jugador;
}
       
    
}
