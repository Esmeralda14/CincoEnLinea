/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dominio.JugadorDAO;
import Persistencia.Jugadores;
import Persistencia.consultas.JugadorCONS;
import com.jfoenix.controls.JFXButton;
import java.awt.Insets;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author Esmeralda
 */
public class RankingController implements Initializable {

    @FXML
    private TableColumn<Jugadores, String> columnaUsuario;
    @FXML
    private TableColumn<Jugadores, Integer> columnaPuntaje;
    @FXML 
    private TableView<Jugadores> tablaRanking;
    
    ObservableList<Jugadores> jugadores = null;
    ObservableList<Jugadores> puntajes = null;
    
    JugadorCONS cons = new JugadorCONS();

        
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
    ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
        public void configurarIdioma(){
        columnaUsuario.setText(resources.getString("jugador"));
        columnaPuntaje.setText(resources.getString("puntaje"));
    }
    
    
     @FXML
     public void llenarTabla() {
        JugadorCONS cons = new JugadorCONS();
        jugadores = FXCollections.observableList(cons.recuperarUsuariosRankiados());
        //puntajes = FXCollections.observableList(cons.recuperarPuntajeJugadores());
         System.out.println("usuarios ranking");
        
       
         System.out.println("llenado de columnas");    
        columnaUsuario.setCellValueFactory(
        new PropertyValueFactory<>("usuario"));
        columnaPuntaje.setCellValueFactory(
        new PropertyValueFactory<>("puntuacionTotal"));
        tablaRanking.setItems(jugadores);
        //tablaRanking.setItems(puntajes);

        
    }
    
}
