import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class inicioJuegoController {

    @FXML 
    private ImageView Mapa1;
    @FXML 
    private ImageView Mapa2;
    @FXML 
    private ImageView redFantasma;
    @FXML 
    private Button    btnMapa1;
    @FXML 
    private Button    btnMapa2;
    @FXML 
    private ImageView blueFantasma;
    @FXML 
    private ImageView logoPM;
    private MediaPlayer musicaInicio;


    private Stage primaryStage;

    public void initialize(){
        reproducirMusica();
    }

    private void reproducirMusica(){
        try{
            String ruta = getClass().getResource("/Imagenes usadas/pacman-intermission.mp3").toExternalForm();
            Media media = new Media(ruta);
            musicaInicio = new MediaPlayer(media);
            musicaInicio.setCycleCount(MediaPlayer.INDEFINITE);
            musicaInicio.setVolume(0.7);
            musicaInicio.play();
        }catch(Exception e){
            System.out.println("musica no cargo" + e.getMessage());
        }
    }
    private void detenerMusica(){
        if(musicaInicio != null){
            musicaInicio.stop();
            musicaInicio.dispose();
        }
    }

    
    @FXML
    public void iniciarMapa1(ActionEvent event) throws IOException {
        Mapa mapa = new Mapa(new int[][] {
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
        cargarJuego(event, mapa);
    }

    
    @FXML
    public void iniciarMapa2(ActionEvent event) throws IOException {
        Mapa mapa = new Mapa(new int[][] {
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
        cargarJuego(event, mapa);
    }

    
    private void cargarJuego(ActionEvent event, Mapa mapa) throws IOException {
        detenerMusica();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/juegoPrincipal.fxml"));

        
        javafx.scene.Parent root = loader.load();

        
        JuegoController controller = loader.getController();
        controller.setMapa(mapa);

        
        int cols = mapa.MAPA[0].length; 
        int rows = mapa.MAPA.length;     
        double sceneW = cols * 26 + 12 * 2;   
        double sceneH = rows * 26 + 55 + 12;  

        
        Scene scene = new Scene(root, sceneW, sceneH);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        controller.setPrimaryStage(stage);
        stage.setTitle("PACMAN");
        stage.setScene(scene);
        stage.show();

        
        controller.iniciarJuego();
    }

    public void setStage(Stage stage) { 
        this.primaryStage = stage; }
}
