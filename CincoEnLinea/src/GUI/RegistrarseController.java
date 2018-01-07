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

import Dominio.AuxiliarDAO;
import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
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
import java.util.Locale;
import javafx.scene.paint.Color;

/**
 * Controller de la ventana donde un nuevo usuario puede registrarse
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class RegistrarseController implements Initializable {

    @FXML
    private Label labelRegistrarse;

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

    @FXML
    private Label laContraDebeDeSerDeOcho;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    JugadorCONS jugadorCONS = new JugadorCONS();

    private Stage stage = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        labelRegistrarse.setText(resources.getString("labelRegistrarse"));
        usuario.setText(resources.getString("labelUsuario"));
        labelContrasena.setText(resources.getString("labelContrasena"));
        Bregistrarse.setText(resources.getString("Bregistrarse"));
        regresar.setText(resources.getString("regresar"));
        laContraDebeDeSerDeOcho.setText(resources.getString("laContraDebeDeSerDeOcho"));
    }

    /**
     * Metodo que realiza el registro de un nuevo jugador, valida campos vacios
     * y usuarios repetidos
     */
    @FXML
    public void registrarUsuario() {
        JugadorCONS jugador = new JugadorCONS();
        if (fieldUsuario.getText().equals("")
                || fieldContraseña.getText().equals("")
                || fieldUsuario.getText().equals(" ")
                || fieldContraseña.getText().equals(" ")) {
            alertaCamposVacios();
        } else {
            boolean resultado = jugadorCONS.validarUsuarioRepetido(fieldUsuario.getText());
            if (resultado) {
                alertaUsuarioRepetido();
            } else {
                jugador.registrarJugador(obtenerValores());
                alertaRegistrado();
                abrirInicioSesion();
            }
        }
    }

    /**
     * Metodo que obtiene los valores de la interfaz grafica
     *
     * @return Retorma un objeto de tipo Jugadores
     */
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

    /**
     * Metodo que muestra una alerta en caso de usuario repetido
     */
    public void alertaUsuarioRepetido() {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("AlertaUsuarioRepetido.fxml"), resources);
            Scene sceneAlerta = new Scene(pane);
            sceneAlerta.setFill(Color.TRANSPARENT);

            stage.setScene(sceneAlerta);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).
                    log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        } catch (RuntimeException ex) {
            Logger.getLogger(LoginController.class.getName()).
                    log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo que muestra una alerta en caso de querer registrar con campos
     * vacios
     */
    public void alertaCamposVacios() {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("AlertaCamposVaciosRegistro.fxml"), resources);
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

    /**
     * Metodo que abre la pantalla del login despues de realizarse el registro
     */
    @FXML
    public void abrirInicioSesion() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"), resources);
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

    /**
     * Meotodo que muestra una alerta si se realizo el registro de manera
     * exitosa
     */
    private void alertaRegistrado() {
        try {
            AnchorPane pane;
            pane = FXMLLoader.load(getClass().getResource("AlertaRegistroExitoso.fxml"), resources);
            Scene sceneAlerta = new Scene(pane);
            sceneAlerta.setFill(Color.TRANSPARENT);
            stage.setScene(sceneAlerta);
            stage.showAndWait();
            stage.setResizable(false);
            System.out.println("si se registro el men");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que verifica que la contraseña sea mayor a 8 caracteres
     */
    @FXML
    private void verificarTamanoContrasena() {
        String contra = fieldContraseña.getText();
        if (contra.length() >= 7) {
            fieldContraseña.setStyle("-fx-text-box-border: green;"
                    + "-fx-focus-color: green; ");
            laContraDebeDeSerDeOcho.setVisible(false);
            Bregistrarse.setDisable(false);

        } else {
            if (contra.length() < 7) {
                fieldContraseña.setStyle("-fx-text-box-border: red;"
                        + "-fx-focus-color: red;");
                Bregistrarse.setDisable(true);
                laContraDebeDeSerDeOcho.setVisible(true);
            }
        }
    }
}
