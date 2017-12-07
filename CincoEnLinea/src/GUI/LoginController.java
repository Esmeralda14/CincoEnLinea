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

    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");

    private Stage stage = new Stage();

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
            
            
        }
        if (resultado.equals("3")) {
            AnchorPane pane;
            try {
                pane = FXMLLoader.load(getClass().getResource("AlertaContraseñaIncorrecta.fxml"), resources);
                Scene sceneAlerta = new Scene(pane);
                sceneAlerta.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
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

    @FXML
    public void cambiarIdiomaEs() {
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
    }

    @FXML
    public void cambiarIdiomaIngles() {
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }

    private JugadorDAO obtenerValores() throws NoSuchAlgorithmException {
        JugadorDAO jugador = null;

        if (fieldUsuario.getText().equals("")
                || fieldContraseña.getText().equals("")) {
        } else {
            String user = fieldUsuario.getText();
            String clave = fieldContraseña.getText();
            AuxiliarDAO aux = new AuxiliarDAO();
            jugador = new JugadorDAO(user, aux.makeHash(clave));
        }
        return jugador;
    }

}
