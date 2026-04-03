
    import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class FinJuegoController {



    @FXML
    private void jugarDnuevo(ActionEvent event) {
        System.out.println("Reiniciando el juego ");
        cambiarEscena(event, "/vistas/JuegoPrincipal.fxml");
    }

    @FXML
    private void elegirMapa (ActionEvent event) {
        System.out.println("en camino a seleccionar mapa");
        cambiarEscena(event, "/vistaInicio.fxml");
    }


    private void cambiarEscena(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

