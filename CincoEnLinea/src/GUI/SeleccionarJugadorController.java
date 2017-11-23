/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class SeleccionarJugadorController implements Initializable {

    @FXML
    private Label jugadoresConectados;

    @FXML
    private ListView<?> listaJugadores;

    @FXML
    private JFXButton botonInciarPartida;

    @FXML
    private JFXButton botonMenuPrincipal;

    @FXML
    private JFXButton botonActualizarLista;


    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    MenuPrincipalController menu = new MenuPrincipalController();
    static Socket socket = null;
    
    private Stage stage = new Stage();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonInciarPartida.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("Tablero.fxml"), resources);
                 Scene scenePartida = new Scene(pane);
                stage.setScene(scenePartida);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void configurarIdioma() {
        botonInciarPartida.setText(resources.getString("¡IniciarPartida!"));
        botonMenuPrincipal.setText(resources.getString("MenuPrincipal"));
        jugadoresConectados.setText(resources.getString("jugadoresConectados"));
        botonActualizarLista.setText(resources.getString("actualizarLista"));
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
    public void actualizarListaUsuarios(){
        
    }
//    public void conexionServidor(){
//        socket = IO.socket("http://localhost:7000");
//        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
//            
//        }
//    }
}
