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
import javafx.scene.control.Label;

/**
 * Controller de la ventana que contiene las reglas del juego
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class ReglasController implements Initializable {

    @FXML
    private Label reglas;

    @FXML
    private Label regla1;

    @FXML
    private Label regla2;

    @FXML
    private Label regla3;

    @FXML
    private Label regla4;

    @FXML
    private Label regla5;

    @FXML
    private Label regla6;

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
        reglas.setText(resources.getString("labelReglas"));
        regla1.setText(resources.getString("regla1"));
        regla2.setText(resources.getString("regla2"));
        regla3.setText(resources.getString("regla3"));
        regla4.setText(resources.getString("regla4"));
        regla5.setText(resources.getString("regla5"));
        regla6.setText(resources.getString("regla6"));

    }

}
