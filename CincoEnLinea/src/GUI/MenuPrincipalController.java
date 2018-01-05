package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author marianacro
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private Label menuPrincipal;

    @FXML
    private JFXButton espanol;

    @FXML
    private JFXButton ingles;

    @FXML
    private Button iniciarPartida;

    @FXML
    private Button hamburgerReglas;

    @FXML
    private Button reglas;

    @FXML
    private JFXDrawer draweMenu;

    @FXML
    private Button raking;

    @FXML
    private Button informacion;

    @FXML
    private Button cerrarSesion;
    
    @FXML
    private Label sesionUsuario;
    

        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    private Stage stage = new Stage();
    private Socket socket;
    private String usuario;
    private Stage stageMenuPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;
    }

    public void configurarIdioma() {
        menuPrincipal.setText(resources.getString("menuPrincipal"));
        iniciarPartida.setText(resources.getString("iniciarPartida"));
        hamburgerReglas.setText(resources.getString("reglas"));
        raking.setText(resources.getString("ranking"));
        informacion.setText(resources.getString("informacion"));
        cerrarSesion.setText(resources.getString("CerrarSesion"));
    }

    @FXML
    public void mostrarReglas() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Reglas.fxml"), resources);
            draweMenu.setSidePane(pane);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        draweMenu.open();
    }
    
    @FXML
    public void mostrarRanking() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Ranking.fxml"), resources);
            draweMenu.setSidePane(pane);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        draweMenu.open();
    }

    @FXML
    public void mostrarInformacion() {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Informacion.fxml"), resources);
            draweMenu.setSidePane(pane);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        draweMenu.open();
    }

    @FXML
    public void iniciarPartida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SeleccionarJugador.fxml"), resources);
            Parent parent = (Parent) loader.load();
            SeleccionarJugadorController selectJugadorController = loader.getController();
            Scene scenePartida = new Scene(parent);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
            selectJugadorController.setSocket(socket);
            selectJugadorController.setUsuario(usuario);
            selectJugadorController.setSeleccionarJugador(stage);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) iniciarPartida.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cerrarSesion(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"), resources);
            Scene scenePartida = new Scene(pane);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
            socket.emit("Finalizar sesion", usuario);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) cerrarSesion.getScene().getWindow();
        stage.close();
    }
    
    
    public void mostrarInvitacion() {
        socket.on("MostrarInvitacion", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader  = new FXMLLoader(getClass().getResource("InvitacionPartida.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        InvitacionPartidaController invitacionController = loader.getController();
                        Scene scenePartida = new Scene(parent);
                        stage.setScene(scenePartida);
                        stage.show();
                        stage.setResizable(false);
                        invitacionController.setSocket(socket);
                        invitacionController.setRoom((String)os[0]);
                        invitacionController.setUsuarioRival((String)os[1]);
                        invitacionController.setUsuario(usuario);
                        invitacionController.setStageMenuPrincipal(stageMenuPrincipal);
                    } catch (IOException ex) {
                        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }

        });

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        mostrarInvitacion();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        sesionUsuario.setText(usuario);

    }

    public void setStageMenuPrincipal(Stage stageMenuPrincipal) {
        this.stageMenuPrincipal = stageMenuPrincipal;
    }
    
    

}
