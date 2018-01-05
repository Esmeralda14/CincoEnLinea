/**
 * @author Mariana Cadena Romero
 * @author Esmeralda Jimenez Ramos
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import io.socket.client.Socket;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Clase controller de la interfaz gráfica de invitación que se envía al jugador
 * invitado a iniciar una partida, la cual puede aceptar o declinar.
 */
public class InvitacionPartidaController implements Initializable {

    @FXML
    private Label label1Usuario;

    @FXML
    private Label nombreUsuario;

    @FXML
    private Label label2Invitacion;

    @FXML
    private JFXButton botonDeclinar;

    @FXML
    private JFXButton botonJugar;

    @FXML
    private Label labelInvitacion;
    
    
    
    
        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    private Socket socket;
    private Stage stage = new Stage();
    String usuarioRival;
    String usuario;
    String room;
    private Stage stageMenuPrincipal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void configurarIdioma(){
        label1Usuario.setText(resources.getString("Elusuario"));
        label2Invitacion.setText(resources.getString("teestainvitandoajugar"));
        botonDeclinar.setText(resources.getString("declinar"));
        botonJugar.setText(resources.getString("jugar"));
        labelInvitacion.setText(resources.getString("invitacion"));
        }
    
    
    @FXML
    public void clicJugar() {
        try {
            socket.emit("IniciarPartida", room);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tablero.fxml"), resources);
            Parent parent = (Parent) loader.load();
            TableroController tableroController = loader.getController();
            Scene scenePartida = new Scene(parent);

            stage.setScene(scenePartida);
            stage.show();
            tableroController.setSocket(socket);
            tableroController.setTurno(2);
            tableroController.setEsMiTurno(false);
            tableroController.setUsuario(usuario);
            tableroController.setUsuarioRival(usuarioRival);
            tableroController.mostrarJugadorEnTurno(usuarioRival);
            stageMenuPrincipal.close();
            tableroController.setStageTablero(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) botonJugar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void clicDeclinar(){
        stage = (Stage) botonDeclinar.getScene().getWindow();
        stage.close();
    }
    

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsuarioRival() {
        return usuarioRival;
    }

    public void setUsuarioRival(String usuarioRival) {
        this.usuarioRival = usuarioRival;
        nombreUsuario.setText(usuarioRival);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setStageMenuPrincipal(Stage stageMenuPrincipal) {
        this.stageMenuPrincipal = stageMenuPrincipal;
    }
    
    
    
    
}
