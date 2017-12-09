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
    private Label labelContrasenaProporcionado;
    @FXML
    private Label LabelEsIncorrecta;

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
        labelErrorIS.setText(resources.getString("LabelErrorInicioSesion"));
        labelContrasenaProporcionado.setText(resources.getString("LabelContrasenaProporcionada"));
        LabelEsIncorrecta.setText(resources.getString("LabelEsIncorrecta"));
        
    }
    
    
    @FXML
    public void clicAceptar(){
        Stage stage = new Stage();
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }

      
    
}
