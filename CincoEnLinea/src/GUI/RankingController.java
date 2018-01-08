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

import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Ventana que muestra el ranking de los usuario que tienen puntuacion del mayor
 * al menor
 *
 * @author Esmeralda Jimenez Ramos
 * @author Mariana Cadena Romero
 */
public class RankingController implements Initializable {

    @FXML
    private TableColumn<Jugadores, String> columnaUsuario;
    @FXML
    private TableColumn<Jugadores, Integer> columnaPuntaje;
    @FXML
    private TableView<Jugadores> tablaRanking;

    ObservableList<Jugadores> jugadores = null;

    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Metodo que obtiene del archivo de idiomas la traducion de los textos que
     * se muestran en pantalla de acuerdo al idoma de la maquina
     */
    public void configurarIdioma() {
        columnaUsuario.setText(resources.getString("jugador"));
        columnaPuntaje.setText(resources.getString("puntaje"));
    }

    /**
     * Metodo que llena la tabla del ranking con el usuario de los jugadores y
     * sus puntuaciones totales
     */
    @FXML
    public void llenarTabla() {
        JugadorCONS consulta;
        consulta = new JugadorCONS();
        jugadores = FXCollections.observableList(consulta.
                recuperarUsuariosRankiados());
        columnaUsuario.setCellValueFactory(
                new PropertyValueFactory<>("usuario"));
        columnaPuntaje.setCellValueFactory(
                new PropertyValueFactory<>("puntuacionTotal"));
        tablaRanking.setItems(jugadores);
    }

}
