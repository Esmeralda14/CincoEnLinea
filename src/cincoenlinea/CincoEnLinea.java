package cincoenlinea;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marianacro
 */
public class CincoEnLinea extends Application {
    
    @Override
    public void start(Stage stage){  
    try{
        ResourceBundle bundle = ResourceBundle.getBundle("resources.idioma");
        FXMLLoader inicio = new FXMLLoader(getClass().getResource("/GUI/Informacion.fxml"),bundle);
        Parent page = inicio.load();
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
    public static void main(String[] args) {
        launch(args);
    }
    
}
