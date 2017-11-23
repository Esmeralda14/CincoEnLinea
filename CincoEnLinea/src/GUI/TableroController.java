/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class TableroController implements Initializable {

    @FXML
    private Label labelJugador;
    @FXML
    private TextField textJugador;
    @FXML
    private Button botonReiniciar;
    @FXML
    private Button botonMenu;
    @FXML
    private Button AH;

    @FXML
    private Button AG;

    @FXML
    private Button AF;

    @FXML
    private Button AE;

    @FXML
    private Button AD;

    @FXML
    private Button AC;

    @FXML
    private Button AB;

    @FXML
    private Button AA;

    @FXML
    private Button AJ;

    @FXML
    private Button AI;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");  
    
    
    public void configurarIdioma(){
        labelJugador.setText(resources.getString("Jugador"));
        botonReiniciar.setText(resources.getString("Reiniciar"));
        botonMenu.setText(resources.getString("Menu"));

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
    @FXML
    public void marcarCasilla(ActionEvent arg0){
        Button boton = (Button) arg0.getSource();
        boton.setStyle("-fx-background-image: url('/resources/fichaAzul.png')");
    }}
