
    import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class FinJuegoController {

    private Mapa mapaAnterior;
    public void setMapaAnterior(Mapa mapa){
        this.mapaAnterior = mapa;
}

    @FXML
    private void jugarDnuevo(ActionEvent event) {
        if(mapaAnterior == null){
            cambiarEscena(event, "/vistaInicio.fxml");
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/juegoPrincipal.fxml"));
            Parent root = loader.load();
            JuegoController controller = loader.getController();
            controller.setMapa(mapaAnterior);
            int cols = mapaAnterior.MAPA[0].length;
            int rows = mapaAnterior.MAPA.length;
            double sceneW = cols * 26 + 12 * 2;
            double sceneH = rows * 26 + 55 + 12;
            Scene scene = new Scene(root, sceneW, sceneH);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            controller.setPrimaryStage(stage);
            stage.setTitle("Pac Man");
            stage.setScene(scene);
            stage.show();
            controller.iniciarJuego();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    private void elegirMapa (ActionEvent event) {
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
    

