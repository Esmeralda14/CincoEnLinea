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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * Ventana en la cual se puede buscar un jugador para iniciar una nueva partida
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
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

    @FXML
    private Label ingresaElUsuarioConElQueDeseasIniciarPartida;

    @FXML
    private Label lUsuario;

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

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        botonInciarPartida.setText(resources.getString("¡IniciarPartida!"));
        botonMenuPrincipal.setText(resources.getString("MenuPrincipal"));
        jugadoresConectados.setText(resources.getString("jugadoresConectados"));
        ingresaElUsuarioConElQueDeseasIniciarPartida.setText(resources.getString
        ("IngresaElUsuarioConElQueDeseasIniciarPartida"));
        lUsuario.setText(resources.getString("lUsuario"));
    }

    /**
     * Metodo que abre la ventana del menu principal
     */
    @FXML
    public void abrirMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("MenuPrincipal.fxml"), resources);
            Parent parent = (Parent) loader.load();
            MenuPrincipalController menuController
                    = loader.getController();
            Scene scenePartida = new Scene(parent);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
            menuController.setStageMenuPrincipal(stage);
            menuController.setSocket(socket);
            menuController.setUsuario(usuario);
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) botonMenuPrincipal.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo que compara el nombre de usuario ingresado con la lista de
     * usuarios conectados, si encuentra coincidencia envia la invitacion para
     * iniciar una partida y en caso de realizar la conexion abre el tablero
     */
    @FXML
    public void clicBotonIniciarPartida() {
        socket.on("UsuarioEncontrado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().
                                getResource("AlertaInvitacionEnviada.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        Scene sceneAlerta = new Scene(parent);
                        stage.setScene(sceneAlerta);
                        stage.show();
                        stage.setResizable(false);
                    } catch (IOException ex) {
                        Logger.getLogger(SeleccionarJugadorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

        }).on("UsuarioNoEncontrado", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().
                                getResource("AlertaUsuarioNoEncontrado.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        Scene sceneAlerta = new Scene(parent);
                        stage.setScene(sceneAlerta);
                        stage.show();
                        stage.setResizable(false);
                    } catch (IOException ex) {
                        Logger.getLogger(SeleccionarJugadorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
