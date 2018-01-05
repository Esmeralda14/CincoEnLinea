/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class AlertaCamposVaciosController implements Initializable {

    @FXML
    private Label labelErrorInicioSesion;
    @FXML
    private Label labelNoSePuedeIniciar;
    @FXML
    private Label labelCamposVacios;
    @FXML
    private JFXButton botonAceptar;
    @FXML
    public AnchorPane paneVentana;
    
    
        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    
    
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelErrorInicioSesion.setText(resources.getString("LabelErrorInicioSesion"));
        labelNoSePuedeIniciar.setText(resources.getString("noSePuedeIniciarSesion"));
        labelCamposVacios.setText(resources.getString("hayCamposVacios"));
        
    }
    
    @FXML
    public void clicAceptar(){
        Stage stage = new Stage();
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
