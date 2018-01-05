/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dominio.PartidaDAO;
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
 * FXML Controller class
 *
 * @author marianacro
 */
public class AlertaPerdedorController implements Initializable {

    @FXML
    private JFXButton botonAceptar;
    
    @FXML
    private Label labelFelicidades;

    @FXML
    private Label labelPerdiste;

   
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    Stage stage = new Stage();
    String usuario;
    private Socket socket;
    private Stage stagePerdedor;
    
    
    public void configurarIdioma(){
        botonAceptar.setText(resources.getString("aceptar"));
        labelFelicidades.setText(resources.getString("felicidades"));
        labelPerdiste.setText(resources.getString("perdiste"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
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
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);

            partida.limpiarTablero();
        }
        stagePerdedor.close();

    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setStagePerdedor(Stage stagePerdedor) {
        this.stagePerdedor = stagePerdedor;
    }
}
