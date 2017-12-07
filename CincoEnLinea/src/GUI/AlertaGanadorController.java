/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dominio.PartidaDAO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class AlertaGanadorController implements Initializable {
    
    @FXML
    private Label labelGanaste;
    
    @FXML
    private Button botonAceptar;
    
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    
    public void configurarIdioma(){
        labelGanaste.setText(resources.getString("ganaste"));
        botonAceptar.setText(resources.getString("aceptar"));
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void clicAceptar(){
    Stage stage = new Stage();
    
    PartidaDAO partida = new PartidaDAO();
    
    
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"), resources);
            Scene scenePartida = new Scene(pane);
            stage.setScene(scenePartida);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
        partida.limpiarTablero();
    } 
    
}
