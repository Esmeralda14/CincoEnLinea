/**
 * @author Mariana Cadena Romero
 * @author Esmeralda Jimenez Ramos
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Dominio.JugadorDAO;
import Persistencia.consultas.JugadorCONS;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Modality;
import Dominio.AuxiliarDAO;
import Dominio.ConexionDAO;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;
import java.util.Locale;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * Clase controller de la interfaz gráfica del inisio de sesión.
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
    static Socket socket=null;
    

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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Registrarse.fxml"), resources);
            Scene sceneRegistro = new Scene(pane);
            stage.setScene(sceneRegistro);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) registrar.getScene().getWindow();
        stage.close();
    }

    
    /**
     * Metodo para iniciar sesión 
     */
    @FXML
    public void iniciarSesion() {

        String resultado = "";
        JugadorCONS jugadorCONS = new JugadorCONS();
        try {
            resultado = jugadorCONS.validarInisioSesion(obtenerValores());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (resultado) {
            case "1":
                try {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"), resources);
                    Scene scenePartida = new Scene(pane);
                    stage.setScene(scenePartida);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void mensajeAlerta(String resultado) {
        if (resultado.equals("2")) {
            AnchorPane pane;
            try {
                pane = FXMLLoader.load(getClass().getResource("AlertaContraseñaIncorrecta.fxml"), resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(sceneAlerta);
                stage.show();
            } catch (RuntimeException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (resultado.equals("3")) {
            AnchorPane pane;
            try {
                pane = FXMLLoader.load(getClass().getResource("AlertaUsuarioIncorrecto.fxml"), resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
//                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(sceneAlerta);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void configurarIdioma() {
        inicioSesion.setText(resources.getString("inicioSesion"));
        labelUsuario.setText(resources.getString("labelUsuario"));
        labelContraseña.setText(resources.getString("labelContrasena"));
        ingresar.setText(resources.getString("ingresar"));
        registrar.setText(resources.getString("registrar"));
        ingles.setText(resources.getString("english"));
        espanol.setText(resources.getString("espanol"));

    }


    private JugadorDAO obtenerValores() throws NoSuchAlgorithmException {
        JugadorDAO jugador = null;

        if (fieldUsuario.getText().equals("")
                || fieldContraseña.getText().equals("")) {
            try {
                AnchorPane pane;
                pane = FXMLLoader.load(getClass().getResource("AlertaCamposVacios.fxml"), resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(sceneAlerta);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String user = fieldUsuario.getText();
            String clave = fieldContraseña.getText();
            AuxiliarDAO aux = new AuxiliarDAO();
            jugador = new JugadorDAO(user, aux.makeHash(clave));
//            agregarJugadorListaServidor(jugador.getUsuario());
            
        }
        return jugador;
    }
    
//    public void agregarJugadorListaServidor(String jugador){
//        try {
//          socket = IO.socket("http://localhost:7000");
//          socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
//            @Override
//            public void call(Object... os) {
//                System.out.println("conectado con el servidor");
//            }
//         });
//          socket.connect();
//          socket.emit("Jugadores conectados", jugador);
//          
//        } catch (URISyntaxException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void agregarJugadorListaServidor(String jugador){
//        try {
//            ConexionDAO.socketEscritura = new Socket(host, puerto);
//            ConexionDAO.socketLectura = new Socket(host, puerto);
//            DataOutputStream mensaje = new DataOutputStream(ConexionDAO.socketEscritura.getOutputStream());
//            
//            mensaje.writeUTF(jugador);
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
