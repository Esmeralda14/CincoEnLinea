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

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Clase controller sobre la interfaz gráfica de la
 * información del proyecto
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class InformacionController implements Initializable {

    @FXML
    private Label informacion;

    @FXML
    private Button espanol;

    @FXML
    private Button ingles;

    @FXML
    private Label materia;

    @FXML
    private Label nombreMateria;

    @FXML
    private Label alumnas;

    @FXML
    private Label alumna1;

    @FXML
    private Label alumna2;

    @FXML
    private Label version;

    @FXML
    private Button Bregresar;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        informacion.setText(resources.getString("labelInformacion"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        materia.setText(resources.getString("materia"));
        nombreMateria.setText(resources.getString("nombreMateria"));
        alumnas.setText(resources.getString("alumnas"));
        alumna1.setText(resources.getString("alumna1"));
        alumna2.setText(resources.getString("alumna2"));
        version.setText(resources.getString("version"));
        Bregresar.setText(resources.getString("regresar"));
    }

}
