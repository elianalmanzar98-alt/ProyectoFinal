
    import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class inicioJuegoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnMapa1;

    @FXML
    private Button btnMapa2;

    @FXML
    void handleJugarMapa1(ActionEvent event) {

    }

    @FXML
    void handleJugarMapa2(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnMapa1 != null : "fx:id=\"btnMapa1\" was not injected: check your FXML file 'vistaInicio.fxml'.";
        assert btnMapa2 != null : "fx:id=\"btnMapa2\" was not injected: check your FXML file 'vistaInicio.fxml'.";

    }

}


