
package cincoenlinea;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Label menuPrincipal;
    
    @FXML
    private Button espanol;
    
    @FXML
    private Button ingles;
    
    @FXML
    private Button iniciarPartida;
    
    @FXML
    private Button reglas;
    
    @FXML
    private Button raking;
    
    @FXML
    private Button informacion;
    
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }    
    
    public void configurarIdioma(){
        menuPrincipal.setText(resources.getString("menuPrincipal"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        iniciarPartida.setText(resources.getString("iniciarPartida"));
        reglas.setText(resources.getString("reglas"));
        raking.setText(resources.getString("ranking"));
        informacion.setText(resources.getString("informacion"));
    }
    
    @FXML
    public void cambiarIdiomaUS(){
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }
    
    @FXML
    public void cambiarIdiomaMX(){
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
    }
    
}
