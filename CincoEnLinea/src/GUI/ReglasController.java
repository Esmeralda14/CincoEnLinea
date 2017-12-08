
package GUI;

import java.net.URL;
import java.util.Locale;
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
    
    @FXML
    private Button espanol;
    
    @FXML
    private Button ingles;
    
    @FXML
    private Button Bregresar;
    
        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);;
    private MenuPrincipalController origen;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }   
    
    public void configurarIdioma(){
        reglas.setText(resources.getString("labelReglas"));
        espanol.setText(resources.getString("espanol"));
        ingles.setText(resources.getString("ingles"));
        regla1.setText(resources.getString("regla1"));
        regla2.setText(resources.getString("regla2"));
        regla3.setText(resources.getString("regla3"));
        regla4.setText(resources.getString("regla4"));
        regla5.setText(resources.getString("regla5"));
        regla6.setText(resources.getString("regla6"));
        
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
    
     public void setOrigen(MenuPrincipalController origen){
    this.origen = origen; 
    }
}
