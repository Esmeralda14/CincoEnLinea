package GUI;

import Dominio.AuxiliarDAO;
import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.Locale;
import javafx.scene.paint.Color;

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
    
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    JugadorCONS jugadorCONS = new JugadorCONS();
    
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
        if (fieldUsuario.getText().equals("") || 
                fieldContraseña.getText().equals("")
                || fieldUsuario.getText().equals(" ") || 
                fieldContraseña.getText().equals(" ")) {
            alertaCamposVacios();
        } else {
            boolean resultado = jugadorCONS.validarUsuarioRepetido
        (fieldUsuario.getText());
            if (resultado) {
                alertaUsuarioRepetido();
            } else {
                jugador.registrarJugador(obtenerValores());
                //alertaRegistrado();
                abrirInicioSesion();
            }
            
        }
        
    }
    
    public Jugadores obtenerValores() {
        
        Jugadores entidadJugador = null;
        AuxiliarDAO aux = new AuxiliarDAO();
        
        String user = fieldUsuario.getText();
        String clave = fieldContraseña.getText();
        try {
            entidadJugador = new Jugadores(user, aux.makeHash(clave));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
        return entidadJugador;
    }
    
    public void alertaUsuarioRepetido() {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource
        ("AlertaUsuarioRepetido.fxml"), resources);
            Scene sceneAlerta = new Scene(pane);
            sceneAlerta.setFill(Color.TRANSPARENT);
            
            stage.setScene(sceneAlerta);
            stage.show();
            stage.setResizable(false);
            System.out.println("usuario repetidooooooo");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        } catch (RuntimeException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void alertaCamposVacios() {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource
        ("AlertaCamposVaciosRegistro.fxml"), resources);
            Scene sceneAlerta = new Scene(pane);
            sceneAlerta.setFill(Color.TRANSPARENT);
            stage.setScene(sceneAlerta);
            stage.show();
            stage.setResizable(false);
        } catch (NullPointerException ex) {
        } catch (RuntimeException ex) {
            Logger.getLogger(LoginController.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }        
    }
    
    @FXML
    public void abrirInicioSesion() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource
        ("Login.fxml"), resources);
            Scene scenePartida = new Scene(pane);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        stage = (Stage) regresar.getScene().getWindow();
        stage.close();
    }
}
