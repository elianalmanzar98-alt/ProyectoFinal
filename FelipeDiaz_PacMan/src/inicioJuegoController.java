import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class inicioJuegoController {

    @FXML private ImageView Mapa1;
    @FXML private ImageView Mapa2;
    @FXML private ImageView redFantasma;
    @FXML private Button    btnMapa1;
    @FXML private Button    btnMapa2;
    @FXML private ImageView blueFantasma;
    @FXML private ImageView logoPM;

    private Stage primaryStage;
    private Mapa  mapa;


    @FXML
    public void iniciarMapa1(ActionEvent event) throws IOException {
        mapa = new Mapa(new int[][] {
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1},
            {1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 3, 3, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 3, 3, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 3, 1, 0, 3, 3, 1, 4, 4, 1, 3, 3, 0, 1, 3, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 3, 3, 1, 3, 3, 1, 3, 3, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 3, 3, 1, 1, 1, 1, 3, 3, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3}
        });
        cambiarAJuego(event);
    }


    @FXML
    public void iniciarMapa2(ActionEvent event) throws IOException {
        mapa = new Mapa(new int[][] {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
            {4, 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 4},
            {4, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 4},
            {4, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 4},
            {4, 3, 3, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 3, 3, 4},
            {4, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 4},
            {4, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 4},
            {4, 1, 0, 1, 1, 1, 1, 0, 1, 1, 3, 3, 3, 3, 1, 1, 0, 1, 1, 1, 1, 0, 1, 4},
            {4, 1, 0, 0, 0, 0, 0, 0, 1, 3, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 0, 0, 1, 4},
            {4, 1, 0, 1, 1, 1, 1, 0, 3, 3, 1, 4, 4, 1, 3, 3, 0, 1, 1, 1, 1, 0, 1, 4},
            {4, 1, 0, 1, 3, 3, 1, 0, 1, 3, 1, 3, 3, 1, 3, 1, 0, 1, 3, 3, 1, 0, 1, 4},
            {4, 1, 0, 1, 3, 3, 1, 0, 1, 3, 1, 1, 1, 1, 3, 1, 0, 1, 3, 3, 1, 0, 1, 4},
            {4, 1, 0, 1, 1, 1, 1, 0, 1, 3, 3, 3, 3, 3, 3, 1, 0, 1, 1, 1, 1, 0, 1, 4},
            {4, 1, 0, 0, 0, 0, 0, 0, 1, 3, 1, 1, 1, 1, 3, 1, 0, 0, 0, 0, 0, 0, 1, 4},
            {4, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 4},
            {4, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 4},
            {4, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 3, 3, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 4},
            {4, 1, 2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 4},
            {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
        });
        cambiarAJuego(event);
    }

    
    private void cambiarAJuego(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/juegoPrincipal.fxml"));
        Scene scene = new Scene(loader.load());

        JuegoController controller = loader.getController();
        controller.setMapa(mapa);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        controller.setPrimaryStage(stage);
        stage.setTitle("PACMAN");
        stage.setScene(scene);
        stage.show();

    
        controller.iniciarJuego();
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }
}
    

    
    









