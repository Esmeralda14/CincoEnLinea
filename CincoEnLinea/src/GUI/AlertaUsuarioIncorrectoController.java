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
import javafx.stage.Stage;

/**
 * Ventana que alerta sobre un usuario ingresado
 * incorrectamente al momento de iniciar sesión
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class AlertaUsuarioIncorrectoController implements Initializable {

    @FXML
    private JFXButton botonAceptar;

    @FXML
    private Label labelErrorIS;

    @FXML
    private Label labelUsuarioProporcionado;

    @FXML
    private Label labelEsIncorrecto;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        botonAceptar.setText(resources.getString("aceptar"));
        labelErrorIS.setText(resources.getString("LabelErrorInicioSesion"));
        labelUsuarioProporcionado.setText(resources.getString("LabelUsuarioProporcionado"));
        labelEsIncorrecto.setText(resources.getString("LabelEsIncorrecto"));
    }

    /**
     * Metodo que cierra la ventana de alerta
     */
    @FXML
    public void clicAceptar() {
        Stage stage = new Stage();
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
