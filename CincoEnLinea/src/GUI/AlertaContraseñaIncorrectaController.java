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
 * Ventana que alerta sobre una contraseña incorrecta al
 * momento de inciar sesión
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

       
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    
    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que 
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelErrorIS.setText(resources.getString("LabelErrorInicioSesion"));
        labelContrasenaProporcionado.setText(resources.getString("LabelContrasenaProporcionada"));
        LabelEsIncorrecta.setText(resources.getString("LabelEsIncorrecta"));
        
    }
    
    /**
     * Metodo que cierra la ventana de alerta
     */
    @FXML
    public void clicAceptar(){
        Stage stage;
        stage = (Stage) botonAceptar.getScene().getWindow();
        stage.close();
    }

      
    
}
