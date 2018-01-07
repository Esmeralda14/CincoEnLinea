/**
 * Nombre del proyecto:
 *    5 en linea.
 *
 * Nombres de los desarrolladores:
 *    Mariana Cadena Romero
 *    Esmeralda Jimenez Ramos
 *
 * Fecha en la que se inició el programa:
 *    28-noviembre-2017
 *
 * Descripción: Juego que lleva por nombre '5 en linea' el cual esta disponible
 * para todo publico, tiene la capacidad de soportar multijugador de dos
 * participantes en tiempo real y de realizar registro de nuevos usuarios,
 * así como consultar la puntuacion de todos los jugadores.
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
 * Ventana que alerta sobre un registro nuevo exitoso
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class AlertaRegistroExitosoController implements Initializable {

    @FXML
    private Label LabelRegistroExitoso;
    @FXML
    private Label elRegistroFueExitoso;
    @FXML
    private Label favorIniciarSesion;
    @FXML
    private JFXButton botonAceptar;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        botonAceptar.setText(resources.getString("aceptar"));
        LabelRegistroExitoso.setText(resources.getString("LabelRegistroExitoso"));
        elRegistroFueExitoso.setText(resources.getString("elRegistroFueExitoso"));
        favorIniciarSesion.setText(resources.getString("favorIniciarSesion"));
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
    public void initialize(URL url, ResourceBundle rb) {

    }

}
