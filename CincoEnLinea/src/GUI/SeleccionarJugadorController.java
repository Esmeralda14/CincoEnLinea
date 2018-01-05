/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class SeleccionarJugadorController implements Initializable {

    @FXML
    private Label jugadoresConectados;

    @FXML
    private JFXButton botonInciarPartida;

    @FXML
    private JFXButton botonMenuPrincipal;
    
    @FXML
    private TextField textUsuario;


        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    MenuPrincipalController menu = new MenuPrincipalController();
    private Socket socket;
    private Stage stage = new Stage();
    private String usuario;
    private Stage seleccionarJugador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;

    }

    public void configurarIdioma() {
        botonInciarPartida.setText(resources.getString("¡IniciarPartida!"));
        botonMenuPrincipal.setText(resources.getString("MenuPrincipal"));
        jugadoresConectados.setText(resources.getString("jugadoresConectados"));
    }


    
    @FXML
    public void abrirMenuPrincipal(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"), resources);
            Scene scenePartida = new Scene(pane);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) botonMenuPrincipal.getScene().getWindow();
        stage.close();
    }

    
    
    @FXML
    public void clicBotonIniciarPartida(){
        socket.on("UsuarioEncontrado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Invitación enviada");
                    alert.show();
                    System.out.println("Usuario encontrado");
                });
            }
            
        }).on("UsuarioNoEncontrado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Usuario no encontrado");
                    alert.show();
                    System.out.println("Usuario no encontrado");
                });
            }
        }).on("AbrirTablero", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                    Platform.runLater(() -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"), resources);
                            Parent parent = (Parent) loader.load();
                            TableroController tableroController = loader.getController();
                            Scene scenePartida = new Scene(parent);
                            
                            stage.setScene(scenePartida);
                            stage.show();
                            stage.setResizable(false);
                            
                            tableroController.setSocket(socket);
                            tableroController.setTurno(1);
                            tableroController.setEsMiTurno(true);
                            tableroController.setUsuario(usuario);
                            tableroController.setUsuarioRival(obtenerValores());
                            tableroController.mostrarJugadorEnTurno(usuario);
                            tableroController.setStageTablero(stage);
                        } catch (IOException ex) {
                            Logger.getLogger(SeleccionarJugadorController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        seleccionarJugador.close();
                        
                });
                
            }
        });
        socket.connect();
        socket.emit("EnviarInvitacion", obtenerValores());
        
    }
    
        public String obtenerValores() {
            String user = textUsuario.getText();
            return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSeleccionarJugador(Stage seleccionarJugador) {
        this.seleccionarJugador = seleccionarJugador;
    }

    
    
    
        
}
