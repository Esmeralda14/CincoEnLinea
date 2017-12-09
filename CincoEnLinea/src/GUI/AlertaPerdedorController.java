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

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class AlertaPerdedorController implements Initializable {

    @FXML
    private JFXButton botonAceptar;
    
    @FXML
    private Label labelFelicidades;

    @FXML
    private Label labelPerdiste;

   
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    
    
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelFelicidades.setText(resources.getString("felicidades"));
        labelPerdiste.setText(resources.getString("perdiste"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
