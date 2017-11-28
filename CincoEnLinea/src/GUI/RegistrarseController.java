package GUI;

import Dominio.AuxiliarDAO;
import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Dominio.JugadorDAO;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class RegistrarseController implements Initializable {

    @FXML
    private Label labelRegistrarse;

    @FXML
    private Button espanol;

    @FXML
    private Button ingles;

    @FXML
    private Label usuario;

    @FXML
    private TextField fieldUsuario;

    @FXML
    private TextField fieldContraseña;

    @FXML
    private Label labelContrasena;

    @FXML
    private Button Bregistrarse;

    @FXML
    private Button regresar;

    ResourceBundle resources = ResourceBundle.getBundle("resources.idioma");
    private Stage stage = new Stage();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;

    }

    public void configurarIdioma() {
        labelRegistrarse.setText(resources.getString("labelRegistrarse"));
        usuario.setText(resources.getString("labelUsuario"));
        labelContrasena.setText(resources.getString("labelContrasena"));
        Bregistrarse.setText(resources.getString("Bregistrarse"));
        regresar.setText(resources.getString("regresar"));

    }
    @FXML
    public void registrarUsuario(ActionEvent registrar) {
        JugadorCONS jugador = new JugadorCONS();
        try {
            jugador.registrarJugador(obtenerValores());
            alertaRegistrada();
            abrirInicioSesion();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Jugadores obtenerValores() {
        Jugadores entidadJugador = null;
        AuxiliarDAO aux = new AuxiliarDAO();
        if (fieldUsuario.getText().equals("") || fieldContraseña.getText().equals("")) {
            alertaCamposVacios();
        } else {
            String user = fieldUsuario.getText();
            String clave = fieldContraseña.getText();
            try {
                entidadJugador = new Jugadores(user,aux.makeHash(clave));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return entidadJugador;
    }

    

    public void alertaCamposVacios() {
        Alert alertaCampos = new Alert(Alert.AlertType.WARNING);
        alertaCampos.setTitle("Campos incompletos");
        alertaCampos.setHeaderText("Alerta");
        alertaCampos.setContentText("Por favor completa todos los campos");
        alertaCampos.showAndWait();
    }

    /**
     * Método que muestra una alerta que informa que el registro de gastos se
     * realizó exitosamente.
     */
    public void alertaRegistrada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
        alertaUsuario.setTitle("¡Registro exitoso!");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("Usuario registrado exitosamente");
        alertaUsuario.showAndWait();
    }
    
        @FXML
    public void cambiarIdiomaEs(){
        resources = ResourceBundle.getBundle("resources.idioma_es_MX");
        configurarIdioma();
    }
    
    @FXML
    public void cambiarIdiomaIngles(){
        resources = ResourceBundle.getBundle("resources.idioma_en_US");
        configurarIdioma();
    }
    @FXML
    public void abrirInicioSesion() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"), resources);
            Scene scenePartida = new Scene(pane);
            stage.setScene(scenePartida);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) regresar.getScene().getWindow();
        stage.close();
    }
}

