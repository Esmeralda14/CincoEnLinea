
package GUI;

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
public class RegistrarseController implements Initializable {

   @FXML
   private Label labelRegistrarse;
   
   @FXML
   private Button espanol;
   
   @FXML
   private Button ingles;
   
   @FXML
   private Label usuario;
   
   @FXML
   private Label labelContrasena;
   
   @FXML
   private Button Bregistrarse;
   
   @FXML 
   private Button regresar;
   
   ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }    
    
    public void configurarIdioma(){
        labelRegistrarse.setText(resources.getString("labelRegistrarse"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        usuario.setText(resources.getString("labelUsuario"));
        labelContrasena.setText(resources.getString("labelContrasena"));
        Bregistrarse.setText(resources.getString("Bregistrarse"));
        regresar.setText(resources.getString("regresar"));
        
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
