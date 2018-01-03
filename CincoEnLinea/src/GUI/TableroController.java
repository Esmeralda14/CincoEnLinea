/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dominio.AuxiliarDAO;
import Dominio.PartidaDAO;
import io.socket.client.Socket;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 
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
    private GridPane gridPaneTablero;
    
    private Stage stage = new Stage();
    PartidaDAO aux = new PartidaDAO();
    private Socket socket;
     
    /**
     * Initializes the controller class.
     */
        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);;
    int turno = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
        
        }

    public void configurarIdioma() {
        labelJugador.setText(resources.getString("Jugador"));
        botonReiniciar.setText(resources.getString("Reiniciar"));
        botonMenu.setText(resources.getString("Menu"));

    }
    
    @FXML
    public void cambiarIdiomaUS() {
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }

    @FXML
    public void cambiarIdiomaMX() {
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();

    }


    @FXML
    public void marcarCasilla(ActionEvent arg0) {
        
        AuxiliarTablero auxiliarTab = new AuxiliarTablero();
        Button boton = (Button) arg0.getSource();
        if (turno == 1) {
            boton.setStyle("-fx-background-image: url('/resources/fichaAzul.png')");

            boton.setDisable(true);
            auxiliarTab.separarPosicion(boton.getId(), turno);
            if (aux.validarColumna(turno) || aux.validarFila(turno) || aux.validarDiagonalIzquierda(turno) || aux.validarDiagonalDerecha(turno)) {
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("AlertaGanador.fxml"), resources);
                    Scene scenePartida = new Scene(pane);
                    stage.setScene(scenePartida);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
            this.turno = 2;

        }else{
            boton.setStyle("-fx-background-image: url('/resources/fichaVerde.png')");
            turno=2;
            boton.setDisable(true);
            auxiliarTab.separarPosicion(boton.getId(), turno);
            if (aux.validarColumna(turno) || aux.validarFila(turno) || aux.validarDiagonalIzquierda(turno) || aux.validarDiagonalDerecha(turno)) {
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("AlertaGanador.fxml"), resources);
                    Scene scenePartida = new Scene(pane);
                    stage.setScene(scenePartida);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
                  this.turno = 1;
        }
        
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    
    
    
    
    
}
