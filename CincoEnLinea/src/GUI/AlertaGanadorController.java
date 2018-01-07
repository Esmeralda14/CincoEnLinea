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

import Dominio.PartidaDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Ventana que se muestra al ganador en una partida
 * 
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class AlertaGanadorController implements Initializable {

    @FXML
    private Label labelGanaste;

    @FXML
    private Button botonAceptar;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    Stage stage = new Stage();
    String usuario;
    private Socket socket;
    private Stage ganador;

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        labelGanaste.setText(resources.getString("ganaste"));
        botonAceptar.setText(resources.getString("aceptar"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Metodo que cierra la ventana de ganador, limpia el tablero y abre el menu
     * principal
     */
    @FXML
    public void clicAceptar() {
        PartidaDAO partida = new PartidaDAO();
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
            partida.limpiarTablero();
        } catch (NullPointerException e) {
            System.out.println("no se limpia el tablero");
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ganador.close();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setGanador(Stage ganador) {
        this.ganador = ganador;

    }

}
