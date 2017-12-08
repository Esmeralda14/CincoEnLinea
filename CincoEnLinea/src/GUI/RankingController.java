/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class RankingController implements Initializable {

    @FXML
    private TableColumn<?, ?> columnaPosicion;
    @FXML
    private TableColumn<?, ?> columnaUsuario;
    @FXML
    private TableColumn<?, ?> columnaPuntaje;

        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }  
    
        public void configurarIdioma(){
        columnaUsuario.setText(resources.getString("jugador"));
        columnaPuntaje.setText(resources.getString("puntaje"));
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
