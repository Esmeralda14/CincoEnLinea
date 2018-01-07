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
import Persistencia.consultas.JugadorCONS;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Controller del tablero
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class TableroController implements Initializable {

    @FXML
    private Label jugadorEnTurno;
    @FXML
    private TextField textJugador;
    @FXML
    private Button botonReiniciar;
    @FXML
    private Button botonMenu;
    @FXML
    private GridPane gridPaneTablero;

    private Stage stage = new Stage();
    PartidaDAO aux = new PartidaDAO();
    private Socket socket;
    private String usuario;
    private String usuarioRival;
    private Stage stageTablero;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
    int turno = 1;
    boolean esMiTurno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.resources = rb;

    }

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        jugadorEnTurno.setText(resources.getString("jugadorEnTurno"));
        botonReiniciar.setText(resources.getString("Reiniciar"));
        botonMenu.setText(resources.getString("Menu"));

    }

    /**
     * Metodo que obtiene el boton en el cual fue realizado el tiro y pinta la
     * casilla dependiendo del turno
     *
     * @param arg0 Parametro de tipo ActionEvent, es utilizado para obtener el
     * nombre del boton seleccionado
     */
    @FXML
    public void marcarCasilla(ActionEvent arg0) {

        AuxiliarTablero auxiliarTab = new AuxiliarTablero();
        Button boton = (Button) arg0.getSource();
        if (esMiTurno) {
            if (turno == 1) {
                boton.setStyle("-fx-background-image: url('/resources/fichaAzul.png')");

                boton.setDisable(true);
                auxiliarTab.separarPosicion(boton.getId(), turno);
                socket.emit("Realizar tiro", boton.getId());
                esMiTurno = false;
                mostrarJugadorEnTurno(usuarioRival);
                if (aux.validarColumna(turno) || aux.validarFila(turno)
                        || aux.validarDiagonalIzquierda(turno)
                        || aux.validarDiagonalDerecha(turno)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().
                                getResource("AlertaGanador.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        AlertaGanadorController ganadorController
                                = loader.getController();
                        Scene scenePartida = new Scene(parent);
                        stage.setScene(scenePartida);
                        stage.show();
                        ganadorController.setGanador(stage);
                        stage.setResizable(false);
                        ganadorController.setSocket(socket);
                        ganadorController.setUsuario(usuario);
                        JugadorCONS jugadorcons = new JugadorCONS();
                        jugadorcons.actualizarPuntuacion(usuario);
                        socket.emit("Avisar a perdedor");
                        socket.emit("Separar canales");
                        aux = null;
                        stageTablero.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

            } else {
                boton.setStyle("-fx-background-image: "
                        + "url('/resources/fichaVerde.png')");
                boton.setDisable(true);
                auxiliarTab.separarPosicion(boton.getId(), turno);
                socket.emit("Realizar tiro", boton.getId());
                esMiTurno = false;
                mostrarJugadorEnTurno(usuarioRival);
                if (aux.validarColumna(turno) || aux.validarFila(turno)
                        || aux.validarDiagonalIzquierda(turno)
                        || aux.validarDiagonalDerecha(turno)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().
                                getResource("AlertaGanador.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        AlertaGanadorController ganadorController
                                = loader.getController();
                        Scene scenePartida = new Scene(parent);
                        stage.setScene(scenePartida);
                        stage.show();
                        ganadorController.setGanador(stage);
                        stage.setResizable(false);
                        ganadorController.setSocket(socket);
                        ganadorController.setUsuario(usuario);
                        JugadorCONS jugadorcons = new JugadorCONS();
                        jugadorcons.actualizarPuntuacion(usuario);
                        socket.emit("Avisar a perdedor");
                        socket.emit("Separar canales");
                        aux = null;
                        stageTablero.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuPrincipalController.class.getName()).
                                log(Level.SEVERE, null, ex);

                    }
                } else {
                    if (aux.validarColumna(turno) == false
                            && aux.validarFila(turno) == false
                            && aux.validarDiagonalIzquierda(turno) == false
                            && aux.validarDiagonalDerecha(turno) && aux.validarEmpate()) {
                        mostrarEmpate();
                    }
                }
            }
        }
    }

    /**
     * Metodo que pinta el tiro en el tablero del rival
     */
    public void mostrarTiroRival() {
        AuxiliarTablero auxiliarTab = new AuxiliarTablero();
        socket.on("MostrarTiroRival", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    int[] coordenadas = auxiliarTab.obtenerPosicion((String) os[0]);
                    ImageView ficha;
                    if (turno == 1) {
                        ficha = new ImageView("/resources/fichaVerde.png");
                    } else {
                        ficha = new ImageView("/resources/fichaAzul.png");
                    }
                    ficha.setFitHeight(40);
                    ficha.setFitWidth(40);
                    GridPane.setConstraints(ficha, coordenadas[1], coordenadas[0]);
                    gridPaneTablero.getChildren().add(ficha);
                    esMiTurno = true;
                    mostrarJugadorEnTurno(usuario);
                });
            }
        });
    }

    /**
     * Metodo que muestra la ventana de perdedor
     */
    public void mostrarVentanaPerdedor() {
        socket.on("Mostrar mensaje perdedor", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().
                                getResource("AlertaPerdedor.fxml"), resources);
                        Parent parent = (Parent) loader.load();
                        AlertaPerdedorController perdedorController
                                = loader.getController();
                        Scene scenePartida = new Scene(parent);
                        stage.setScene(scenePartida);
                        stage.show();
                        perdedorController.setStagePerdedor(stage);
                        stage.setResizable(false);
                        perdedorController.setSocket(socket);
                        perdedorController.setUsuario(usuario);
                        socket.emit("Separar canales");
                        aux = null;
                        stageTablero.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        });
    }

    /**
     * Ventana que muestra la vetana de empate
     */
    public void mostrarEmpate() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().
                    getResource("AlertaEmpate.fxml"), resources);
            Parent parent = (Parent) loader.load();
            AlertaEmpateController empateController
                    = loader.getController();
            Scene scenePartida = new Scene(parent);
            stage.setScene(scenePartida);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(TableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que muestra en pantalla el usuario del jugador en turno
     *
     * @param usuario Cadena de texto que contiene el usuario del jugador
     */
    public void mostrarJugadorEnTurno(String usuario) {
        textJugador.setText(usuario);
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        mostrarTiroRival();
        mostrarVentanaPerdedor();
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setEsMiTurno(boolean esMiTurno) {
        this.esMiTurno = esMiTurno;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setUsuarioRival(String usuarioRival) {
        this.usuarioRival = usuarioRival;
    }

    public void setStageTablero(Stage stageTablero) {
        this.stageTablero = stageTablero;
    }
}
