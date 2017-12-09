package cincoenlinea;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author marianacro
 */
public class CincoEnLinea extends Application {
    
    @Override
    public void start(Stage stage){  
    try{
   
              
    String idioma = Locale.getDefault().toString();
    String idiomaResource = "resources.idioma_" + idioma;
        System.out.println(idioma);
        System.out.println(idiomaResource);
        ResourceBundle resources = ResourceBundle.getBundle(idiomaResource);
        AnchorPane page  = FXMLLoader.load(getClass().getResource("/GUI/SeleccionarJugador.fxml"),resources);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.show();
       }catch (IOException ex){
           Logger.getLogger(CincoEnLinea.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);

    }
    
}
