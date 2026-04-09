import java.io.IOException;

import javafx.scene.control.Label;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane tablero;     
    @FXML 
    private Label labelPuntuacion;     
    @FXML 
    private Label labelMensaje;     
    @FXML 
    private HBox hboxVidas;     
    @FXML 
    private ImageView vida1;     
    @FXML 
    private ImageView vida2;     
    @FXML 
    private ImageView vida3;
    private Mapa mapa;
    private Jugador jugador;
    private Fantasma fantasmaRed;
    private Fantasma fanatasmaBlue;
    private Stage primaryStage;
    private Image imgPacman;
    private Image imgPared;
    private Image imgPunto;
    private Image imgPuntoEspecial;
    private Image imgFantasmaRed;
    private Image imgFantasmaBlue;
    private Image imgFantasmaAsustado;
    private KeyCode ultimaTecla = KeyCode.RIGHT;
    private boolean juegoActivo = false;
    private boolean juegoIniciado = false;
    private int totalPuntos = 0;
    private static final int TAMANIO_CELDA = 26;
    private Timeline gameLoop;
    private Timeline fantasmaLoop;
    private Timeline asustadoTimer;

    public void iniciarJuego(){
        cargarImagenes();
        construirTablero();
        crearJugador();
        crearFantasmas();
        configurarTeclado();
        iniciarGameLoop();
        labelMensaje.setText("Enter para iniciar");
        labelMensaje.setVisible(true);


    
    }
    
    //aqui lo quiero para cargar las iamgenes que estare usando

    private Image cargarImg(String ruta){
        try{ 
            return new Image(getClass().getResource("/" + ruta).toExternalForm());
        }catch(Exception e){
            System.out.println("Imagen no encontrada: " + ruta);
            return null;
        }
    }
    


    public void cargarImagenes(){
        imgPacman  =cargarImg("Imagenes usadas/pacman.gif");
        imgPunto  = cargarImg("Imagenes usadas/puntos.png");
        imgPuntoEspecial = cargarImg("Imagenes usadas/puntos.png");  
        imgFantasmaRed = cargarImg("Imagenes usadas/RED.png");
        imgFantasmaBlue = cargarImg("Imagenes usadas/BLUE.png");
        imgFantasmaAsustado= cargarImg("Imagenes usadas/modoAsustado.png");

        imgPared = null;
}



    private Node crearNodeCelda(int fila, int valor, int col){
        switch (valor) {
            case 1:{
                Rectangle rect = new Rectangle(TAMANIO_CELDA, TAMANIO_CELDA, Color.web("#1a1aff"));
                rect.setArcHeight(4);
                rect.setArcWidth(4);
                return rect;
            }
            case 2: { 
                ImageView iv = new ImageView(imgPunto);
                iv.setFitHeight(16);
                iv.setFitWidth(16);
                iv.setTranslateX(5);
                iv.setTranslateY(5);
                return iv;
            }
            case 4 : { 
                Rectangle rect =  new Rectangle(TAMANIO_CELDA, TAMANIO_CELDA, Color.web("#110033"));
                return rect; 
            }
            case 0: {
                ImageView iv = new ImageView(imgPunto);
                iv.setFitHeight(8);
                iv.setFitWidth(8);
                iv.setTranslateX(9);
                iv.setTranslateX(9);
                return iv;
            }
            case 3  :
                default:
                    return null;
        
        }
    }
    private void construirTablero(){
        tablero.getChildren().clear();
        totalPuntos = 0;
        int [][] m = mapa.MAPA;


        for(int fila = 0;  fila < m.length; fila++){
            for(int col = 0; col < m[fila].length; col++){
                int valor = m[fila ][col];
                Node nodo = crearNodeCelda(valor, fila, col);
                if(nodo != null){
                    tablero.add(nodo,col,fila);
                    if(valor == 0 || valor ==2) totalPuntos++;
                }
            }
        }
    }
private void crearJugador(){
    int filaInicio = 1, colInicio = 1;
    outer: for( int f = 1; f < mapa.MAPA.length -1; f++){
        for(int c = 1; c< mapa.MAPA[f].length -1; c++){
            if(mapa.MAPA[f][c] == 0){
                filaInicio = f;
                colInicio = c;
                break outer;
            }
        }
    }
    ImageView ivPacman = new ImageView(imgPacman);
    ivPacman.setFitHeight(TAMANIO_CELDA);
    ivPacman.setFitWidth(TAMANIO_CELDA);
    jugador = new Jugador(filaInicio, colInicio, ivPacman);
    jugador.setMapa(mapa);
    jugador.setPuntajeLabel(labelPuntuacion);
    tablero.add(ivPacman, colInicio, filaInicio);

}

private void crearFantasmas(){
    ImageView ivBlue = new ImageView(imgFantasmaBlue);
    ivBlue.setFitHeight(TAMANIO_CELDA);
    ivBlue.setFitWidth(TAMANIO_CELDA);
    fanatasmaBlue = new Fantasma(10, 11,ivBlue, nombreFantasmas.BLUE);
    tablero.add(ivBlue, 11,10);

    ImageView ivRed = new ImageView(imgFantasmaRed);
    ivRed.setFitHeight(TAMANIO_CELDA);
    ivRed.setFitWidth(TAMANIO_CELDA);
    fantasmaRed = new Fantasma(10,12, ivRed, nombreFantasmas.RED);
    tablero.add(ivRed,12,10);


    jugador.setFantasmas(fantasmaRed, fanatasmaBlue);



}
//PARA LAS PAUSAS Y CONTINUAR
private void togglePausa(){
    if(juegoActivo){
        juegoActivo = false;
        labelMensaje.setText("Pausa pulsa enter para continuar");
        labelMensaje.setVisible(true);
    }else{
        juegoActivo = true;
        labelMensaje.setVisible(false);
    }
}

private void configurarTeclado(){
    //aqui usare el detector las teclas qu e les indicare 
    //el q usare sera el rootPane q es ek queb recibe

    Platform.runLater(()->{
        if (rootPane.getScene()!= null){
            rootPane.getScene().setOnKeyPressed(event ->{
                KeyCode tecla = event.getCode();


                if (tecla == KeyCode.ENTER){
                    if(!juegoIniciado){
                        juegoIniciado = true;
                        juegoActivo = true;
                        labelMensaje.setVisible(false);

                    }else{
                        togglePausa();
                    }
                    return;
                }
                if(tecla == KeyCode.DOWN || tecla == KeyCode.S) ultimaTecla = KeyCode.DOWN;
                if(tecla == KeyCode.UP || tecla == KeyCode.W) ultimaTecla = KeyCode.UP;
                if(tecla == KeyCode.LEFT || tecla == KeyCode.A) ultimaTecla = KeyCode.LEFT;
                if(tecla == KeyCode.RIGHT || tecla == KeyCode.D) ultimaTecla = KeyCode.RIGHT;
            });
            rootPane.requestFocus();
        }

    });
}

private void activarModoAsustado(){
    if(imgFantasmaAsustado != null){
        fantasmaRed.getImagen().setImage(imgFantasmaAsustado);
        fanatasmaBlue.getImagen().setImage(imgFantasmaAsustado);

    }
    fantasmaRed.setEstaAsustado(true);
    fanatasmaBlue.setEstaAsustado(true);
    fantasmaRed.getImagen().setVisible(true);
    fanatasmaBlue.getImagen().setVisible(true);

}

private void terminarModoAsustado(){

    jugador.setAsustadoActivado(false);
    fantasmaRed.recuperarAsustado(nombreFantasmas.RED);
    fanatasmaBlue.recuperarAsustado(nombreFantasmas.BLUE);

    if(imgFantasmaRed != null) fantasmaRed.getImagen().setImage(imgFantasmaRed);
    if(imgFantasmaBlue != null) fanatasmaBlue.getImagen().setImage(imgFantasmaBlue);
    fantasmaRed.getImagen().setVisible(true);
    fanatasmaBlue.getImagen().setVisible(true);

}


// aui creare para los movimientos 
private void iniciarGameLoop(){
    gameLoop = new Timeline(new KeyFrame(Duration.millis(150), e ->{
        if(!juegoActivo) return;
        try{
            jugador.mover(ultimaTecla, tablero);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        //metodos que usare para colisiones y victoria
        verificarColisionFantasmas();
        verificarVictoria();
        if(jugador.isAsustadoActivado()){
            activarModoAsustado();
        }

    }));
    gameLoop.setCycleCount(Animation.INDEFINITE);

    //para los fantasmas 
    fantasmaLoop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
        if(!juegoActivo) return;
        fanatasmaBlue.iniciarMovimiento(jugador, tablero, mapa);
        fantasmaRed.iniciarMovimiento(jugador, tablero, mapa);
        verificarColisionFantasmas();


        //aqui pondre los segundos que durara el modoAsustado de los fantasmas 
        if(jugador.isAsustadoActivado()){
            long elapsed = System.currentTimeMillis() - jugador.getMilisAsustado();
            if(elapsed >= 8000){
                terminarModoAsustado();

            }else if (elapsed >= 6000){
                boolean visible = (elapsed / 300) % 2==0;
                fanatasmaBlue.getImagen().setVisible(visible);
                fantasmaRed.getImagen().setVisible(visible);
            }
        
        }
    }));
    fantasmaLoop.setCycleCount(Animation.INDEFINITE);
    gameLoop.play();
    fantasmaLoop.play();
}

private Node obtenerNodeCeldaNode(GridPane grid, int columna, int fila){
    for (Node n : grid.getChildren()){
        Integer r = GridPane.getRowIndex(n);
        Integer c = GridPane.getColumnIndex(n);
        if(r == null) r = 0;
        if(c == null) c = 0;
        if(r == fila && c ==columna && !(n instanceof Rectangle)){
            return n;
        }
    }
    return null;

}


private void resetPosiciones(){
    //para volver a las posiciones q incio
    int filaInicio = 1, colInicio = 1;
    outer:
    for(int f = 1; f < mapa.MAPA.length -1; f++){
        for(int c = 1; c < mapa.MAPA [f].length -1; c++){
            if(mapa.MAPA[f][c] == 0 ){
                filaInicio = f;
                colInicio = c;
                break outer;
            }
        }
    }
    jugador.setPosicion(filaInicio, colInicio);
    tablero.setRowIndex(jugador.getImageView(), filaInicio);
    tablero.setColumnIndex(jugador.getImageView(), colInicio);


    
    fantasmaRed.setPosicion(fantasmaRed.getPosicionInicial());
    tablero.setRowIndex(fantasmaRed.getImagen(), fantasmaRed.getPosicionInicial()[0]);
    tablero.setColumnIndex(fantasmaRed.getImagen(), fantasmaRed.getPosicionInicial()[1]);

    fanatasmaBlue.setPosicion(fanatasmaBlue.getPosicionInicial());
    tablero.setRowIndex(fanatasmaBlue.getImagen(), fanatasmaBlue.getPosicionInicial()[0]);
    tablero.setColumnIndex(fanatasmaBlue.getImagen(), fanatasmaBlue.getPosicionInicial()[1]);


    terminarModoAsustado();
    jugador.setAsustadoActivado(false);

}

private void verificarVictoria(){
    long puntosComidos = tablero.getChildren().stream().filter(n -> n instanceof ImageView && !n.isVisible()).count();
    if(puntosComidos >= totalPuntos && totalPuntos > 0 ){
        juegoActivo = false;
        labelMensaje.setText("victoria");
        labelMensaje.setVisible(true);
        detenerLoops();
        Timeline pausa = new Timeline(new KeyFrame(Duration.seconds(2), e -> irAfinJuego()));
        pausa.play();
    }
}


//conteo para las vidas forma iconos 
private void actualizarIonosVidas(int vidas){
    vida1.setVisible(vidas >= 1);
    vida2.setVisible(vidas >= 2);
    vida3.setVisible(vidas >= 3);

}

private void verificarColisionFantasmas(){
    boolean colisionRed = jugador.getFila() == fantasmaRed.getFila() && jugador.getColumna() == fantasmaRed.getColumna();
    boolean colisionBlue = jugador.getFila() == fanatasmaBlue.getFila() && jugador.getColumna() == fanatasmaBlue.getColumna();

    //para pacman comam a fantasnama

    if(colisionRed || colisionBlue){
        if(jugador.isAsustadoActivado()){
            Fantasma comido = colisionRed ? fantasmaRed : fanatasmaBlue;
            comido.getImagen().setVisible(false);
            tablero.setRowIndex(comido.getImagen(), comido.getPosicionInicial()[0]);
            tablero.setColumnIndex(comido.getImagen(), comido.getPosicionInicial()[1]);
            comido.setPosicion(comido.getPosicionInicial());

            Timeline respawn = new Timeline(new KeyFrame(Duration.seconds(3), ev ->{
                comido.getImagen().setVisible(true);
                if(imgFantasmaRed != null&& comido ==fantasmaRed) comido.getImagen().setImage(imgFantasmaRed);
                if(imgFantasmaBlue != null&& comido ==fanatasmaBlue) comido.getImagen().setImage(imgFantasmaBlue);

            }));
            respawn.play();
        }else{
            perderVida();
            //aqui se pierda una vida 
        }
    }
}
private void perderVida(){
    juegoActivo= false;
    int vidas = jugador.getVidas() - 1;
    jugador.setVidas(vidas);
    if(vidas <= 0){
        irAfinJuego();
    }else{
        resetPosiciones();
        labelMensaje.setText("vida perdida pulse oprime entre para continuar");
        labelMensaje.setVisible(true);
    }
}

private void detenerLoops(){
    if(gameLoop !=null) gameLoop.stop();
    if(fantasmaLoop != null) fantasmaLoop.stop();
}

private void irAfinJuego(){
    detenerLoops();
    Platform.runLater(()->{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/finJuego.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);


            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    });
}

public void setMapa(Mapa mapa){
    this.mapa = mapa;
}

public void setPrimaryStage(Stage stage){
    this.primaryStage = stage;
}



}
