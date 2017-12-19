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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class AlertaCamposVaciosRegistroController implements Initializable {

    @FXML
    private Label labelErrorRegistro;
    @FXML
    private Label labelNoSePuedeRegistrar;
    @FXML
    private Label labelCamposVacios;
    @FXML
    private JFXButton botonAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    
    
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelErrorRegistro.setText(resources.getString("LabelErrorRegistro"));
        labelNoSePuedeRegistrar.setText(resources.getString("noSePuedeRegistrar"));
        labelCamposVacios.setText(resources.getString("hayCamposVacios"));
        
    }
    
    @FXML
    public void clicAceptar(){
        Stage stage = new Stage();
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }
}
