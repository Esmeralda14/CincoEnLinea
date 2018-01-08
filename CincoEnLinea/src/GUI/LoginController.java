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

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Dominio.JugadorDAO;
import Persistencia.consultas.JugadorCONS;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.util.Locale;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 * Controller de la interfaz gráfica del inicio de sesión.
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class LoginController implements Initializable {

    @FXML
    private Label labelUsuario;

    @FXML
    private Label inicioSesion;

    @FXML
    private Label labelContraseña;

    @FXML
    private TextField fieldUsuario;

    @FXML
    private TextField fieldContraseña;

    @FXML
    private Button ingresar;

    @FXML
    private Button registrar;

    @FXML
    private Button ingles;

    @FXML
    private Button espanol;

    private Stage stage = new Stage();

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    private Socket socket;
    JugadorDAO jugador = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }

    /**
     * Metodo para abrir la ventana registrar desde el inicio de sesión.
     */
    @FXML
    private void abrirRegistrar() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().
                    getResource("Registrarse.fxml"), resources);
            Scene sceneRegistro = new Scene(pane);
            stage.setScene(sceneRegistro);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        stage = (Stage) registrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo para iniciar sesión, valida campos vacios, contraseña o usuario
     * incorrecto y abre la ventana del menu principal
     */
    @FXML
    public void iniciarSesion() {

        String resultado;
        JugadorCONS jugadorCONS = new JugadorCONS();
        if (fieldUsuario.getText().equals("")
                || fieldContraseña.getText().equals("")) {
            alertaCamposVacios();

        } else {
            jugador = obtenerValores();
            resultado = jugadorCONS.validarInisioSesion(jugador);
            switch (resultado) {
                case "1":
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
                        agregarJugadorListaServidor(jugador.getUsuario());
                        menuController.setSocket(socket);
                        menuController.setUsuario(fieldUsuario.getText());
                    } catch (IOException ex) {
                        Logger.getLogger(MenuPrincipalController.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    stage = (Stage) inicioSesion.getScene().getWindow();
                    stage.close();
                    break;
                case "2":
                    mensajeAlerta("2");
                    break;
                case "3":
                    mensajeAlerta("3");
                    break;

            }
        }
    }

    /**
     * Metodo que muestra una alerta de contraseña o usuario incorrecto
     *
     * @param resultado Caracter de tipo entero
     */
    private void mensajeAlerta(String resultado) {
        if (resultado.equals("2")) {
            AnchorPane pane;
            try {
                pane = FXMLLoader.
                        load(getClass().
                                getResource("AlertaContraseñaIncorrecta.fxml"),
                                resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
                stage.setScene(sceneAlerta);
                stage.show();
                stage.setResizable(false);

            } catch (RuntimeException ex) {
                Logger.getLogger(LoginController.class.getName()).
                        log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        if (resultado.equals("3")) {
            AnchorPane pane;
            try {
                pane = FXMLLoader.load(getClass().
                        getResource("AlertaUsuarioIncorrecto.fxml"), resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
                stage.setScene(sceneAlerta);
                stage.show();
                stage.setResizable(false);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        inicioSesion.setText(resources.getString("inicioSesion"));
        labelUsuario.setText(resources.getString("labelUsuario"));
        labelContraseña.setText(resources.getString("labelContrasena"));
        ingresar.setText(resources.getString("ingresar"));
        registrar.setText(resources.getString("registrar"));
        ingles.setText(resources.getString("english"));
        espanol.setText(resources.getString("espanol"));

    }

    /**
     * Metodo que obtiene los datos de la interfaz grafica
     *
     * @return Objeto de tipo JugadorDAO
     */
    public JugadorDAO obtenerValores() {

        try {
            String user = fieldUsuario.getText();
            String clave = fieldContraseña.getText();
            JugadorDAO auxJugador = new JugadorDAO();
            jugador = new JugadorDAO(user, auxJugador.makeHash(clave));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

        return jugador;
    }

    /**
     * Metodo que muestra una alerta de campos vacios
     */
    public void alertaCamposVacios() {
        try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().
                    getResource("AlertaCamposVacios.fxml"), resources);
            Scene sceneAlerta = new Scene(pane);
            sceneAlerta.setFill(Color.TRANSPARENT);
            stage.setScene(sceneAlerta);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que agrega al jugador a la lista de jugadores conectados al
     * momento de inciar sesión
     *
     * @param jugador Usuario del jugador
     */
    public void agregarJugadorListaServidor(String jugador) {
        try {
            socket = IO.socket("http://192.168.43.162:7000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                    System.out.println("conectado con el servidor");
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... os) {
                   
                }
            });
            socket.connect();
            socket.emit("Jugadores conectados", jugador);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }

}
