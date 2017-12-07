/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class AlertaContraseñaIncorrectaController implements Initializable {

    @FXML
    private JFXButton botonAceptar;

    @FXML
    private Label labelErrorIS;

    @FXML
    private Label labelUsuarioProporcionado;

    @FXML
    private Label labelEsIncorrecto;
    
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelErrorIS.setText(resources.getString("LabelErrorInicioSesion"));
        labelUsuarioProporcionado.setText(resources.getString("LabelUsuarioProporcionado"));
        labelEsIncorrecto.setText(resources.getString("LabelEsIncorrecto"));
        
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
    
    @FXML
    public void clicAceptar(){
        Stage stage = new Stage();
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
}
