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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class InvitacionPartidaController implements Initializable {

    @FXML
    private Label label1Usuario;

    @FXML
    private Label nombreUsuario;

    @FXML
    private Label label2Invitacion;

    @FXML
    private JFXButton botonDeclinar;

    @FXML
    private JFXButton botonJugar;

    @FXML
    private Label labelInvitacion;
    
    
    
    
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    private Stage stage = new Stage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void configurarIdioma(){
        label1Usuario.setText(resources.getString("Elusuario"));
        label2Invitacion.setText(resources.getString("teestainvitandoajugar"));
        botonDeclinar.setText(resources.getString("declinar"));
        botonJugar.setText(resources.getString("jugar"));
        labelInvitacion.setText(resources.getString("invitacion"));
        }
}
