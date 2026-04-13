import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoController {

    @FXML
    private AnchorPane rootPane;
    @FXML 
    private GridPane   tablero;
    @FXML 
    private Label      labelPuntuacion;
    @FXML 
    private Label      labelMensaje;
    @FXML 
    private HBox       hboxVidas;
    @FXML 
    private ImageView  vida1, vida2, vida3;

    private Mapa     mapa;
    private Jugador  jugador;
    private Fantasma fantasmaRed;
    private Fantasma fantasmaBlue;
    private Stage    primaryStage;

    private Image imgPacman, imgPunto, imgPuntoEspecial;
    private Image imgFantasmaRed, imgFantasmaBlue, imgFantasmaAsustado;

    private KeyCode ultimaTecla   = KeyCode.RIGHT;
    private boolean juegoActivo   = false;
    private boolean juegoIniciado = false;
    private int     totalPuntosEnMapa = 0;

    private static final int CELDA   = 26;  
    private static final int HEADER  = 55;  
    private static final int MARGIN  = 12; 

    private Timeline gameLoop;
    private Timeline fantasmaLoop;

    

    public void iniciarJuego() {
        cargarImagenes();
        posicionarTablero();   
        construirTablero();    
        ajustarVentana();      
        crearJugador();
        crearFantasmas();
        configurarTeclado();
        iniciarGameLoop();
        actualizarIconosVidas(3);
        labelMensaje.setText(" Enter para comenzar ");
        labelMensaje.setVisible(true);
    }



    private void posicionarTablero() {
        int cols = mapa.MAPA[0].length;
        int rows = mapa.MAPA.length;

        
        tablero.getColumnConstraints().clear();
        tablero.getRowConstraints().clear();
        tablero.getChildren().clear();

        
        for (int c = 0; c < cols; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(CELDA);
            cc.setPrefWidth(CELDA);
            cc.setMaxWidth(CELDA);
            tablero.getColumnConstraints().add(cc);
        }
        for (int r = 0; r < rows; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(CELDA);
            rc.setPrefHeight(CELDA);
            rc.setMaxHeight(CELDA);
            tablero.getRowConstraints().add(rc);
        }

        
        double tableroW = cols * CELDA;
        tablero.setLayoutX(MARGIN);
        tablero.setLayoutY(HEADER);
        tablero.setPrefWidth(tableroW);
        tablero.setPrefHeight(rows * CELDA);
        tablero.setMinWidth(tableroW);
        tablero.setMinHeight(rows * CELDA);

        System.out.println("tablero " + cols + " cols x " + rows + " filas = "  + tableroW + "x" + (rows * CELDA) + "px");
    }

    

    private void ajustarVentana() {
        int cols = mapa.MAPA[0].length;
        int rows = mapa.MAPA.length;
        double w = cols * CELDA + MARGIN * 2;      
        double h = rows * CELDA + HEADER + MARGIN; 

        
        rootPane.setPrefWidth(w);
        rootPane.setPrefHeight(h);
        rootPane.setMinWidth(w);
        rootPane.setMinHeight(h);

        
        labelMensaje.setLayoutX(MARGIN + (cols * CELDA) / 2.0 - 200);
        labelMensaje.setLayoutY(HEADER + (rows * CELDA) / 2.0 - 20);

    
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.sizeToScene();
            stage.centerOnScreen();
            System.out.println("Ventana " + stage.getWidth() + "x" + stage.getHeight());
        });
    }



    private Image cargarImg(String ruta) {
        try {
            return new Image(getClass().getResource("/" + ruta).toExternalForm());
        } catch (Exception e) {
            System.out.println("Imagen no encontrada " + ruta);
            return null;
        }
    }

    private void cargarImagenes() {
        imgPacman           = cargarImg("Imagenes usadas/pacman.gif");
        imgPunto            = cargarImg("Imagenes usadas/puntos.png");
        imgPuntoEspecial    = cargarImg("Imagenes usadas/puntos.png");
        imgFantasmaRed      = cargarImg("Imagenes usadas/RED.png");
        imgFantasmaBlue     = cargarImg("Imagenes usadas/BLUE.png");
        imgFantasmaAsustado = cargarImg("Imagenes usadas/modoAsustado.png");
    }



    private Node crearNodo(int valor) {
        switch (valor) {
            case 1: {
                
                Rectangle r = new Rectangle(CELDA, CELDA, Color.web("#1a1aff"));
                r.setArcWidth(6); r.setArcHeight(6);
                return r;
            }
            case 2: {
            
                if (imgPuntoEspecial != null) {
                    ImageView iv = new ImageView(imgPuntoEspecial);
                    iv.setFitWidth(16); iv.setFitHeight(16);
                    iv.setTranslateX(5); iv.setTranslateY(5);
                    return iv;
                }
                Circle c = new Circle(7, Color.WHITE);
                c.setTranslateX(13); c.setTranslateY(13);
                return c;
            }
            case 4: {
                
                Rectangle r = new Rectangle(CELDA, CELDA, Color.web("#220044"));
                return r;
            }
            case 0: {
                
                if (imgPunto != null) {
                    ImageView iv = new ImageView(imgPunto);
                    iv.setFitWidth(8); iv.setFitHeight(8);
                    iv.setTranslateX(9); iv.setTranslateY(9);
                    return iv;
                }
                Circle c = new Circle(3, Color.WHITE);
                c.setTranslateX(13); c.setTranslateY(13);
                return c;
            }
            case 3:
            default:
                
                return null;
        }
    }

    private void construirTablero() {
        tablero.getChildren().clear();
        totalPuntosEnMapa = 0;
        int[][] m = mapa.MAPA;
        int totalNodos = 0;

        for (int fila = 0; fila < m.length; fila++) {
            for (int col = 0; col < m[fila].length; col++) {
                int valor = m[fila][col];
                Node nodo = crearNodo(valor);
                if (nodo != null) {
                    tablero.add(nodo, col, fila); 
                    totalNodos++;
                    if (valor == 0 || valor == 2) totalPuntosEnMapa++;
                }
            }
        }
        System.out.println("Tablero " + totalNodos + "  agregados, "
                + totalPuntosEnMapa + " puntos comibles");
    }

    

    private void crearJugador() {
        int fi = 1, ci = 1;
        outer:
        for (int f = 1; f < mapa.MAPA.length - 1; f++)
            for (int c = 1; c < mapa.MAPA[f].length - 1; c++)
                if (mapa.MAPA[f][c] == 0) { fi = f; ci = c; break outer; }

        ImageView iv = new ImageView(imgPacman);
        iv.setFitWidth(CELDA); iv.setFitHeight(CELDA);
        jugador = new Jugador(fi, ci, iv);
        jugador.setMapa(mapa);
        jugador.setPuntajeLabel(labelPuntuacion);
        tablero.add(iv, ci, fi);
        System.out.println(" en fila=" + fi + " col=" + ci);
    }

    

    private void crearFantasmas() {
        
        int filaSpawn = 9;
        ImageView ivBlue = new ImageView(imgFantasmaBlue);
        ivBlue.setFitWidth(CELDA);
        ivBlue.setFitHeight(CELDA);
        fantasmaBlue = new Fantasma(filaSpawn, 11, ivBlue, nombreFantasmas.BLUE);
        tablero.add(ivBlue, 11, filaSpawn);
        ImageView ivRed = new ImageView(imgFantasmaRed);
        ivRed.setFitWidth(CELDA);
        ivRed.setFitHeight(CELDA);
        fantasmaRed = new Fantasma(filaSpawn, 12, ivRed, nombreFantasmas.RED);
        tablero.add(ivRed, 12, filaSpawn);

        jugador.setFantasmas(fantasmaRed, fantasmaBlue);
        System.out.println("en fila " + filaSpawn + " | blue :" + mapa.getCelda(filaSpawn, 11) + " | red : " + mapa.getCelda(filaSpawn, 12));
    
    }



    private void configurarTeclado() {
        Platform.runLater(() -> {
            Scene sc = rootPane.getScene();
            if (sc == null) {
                System.out.println(" error");
                return;
            }
            sc.setOnKeyPressed(e -> {
                KeyCode k = e.getCode();
                if (k == KeyCode.ENTER) {
                    if (!juegoIniciado) {
                        juegoIniciado = true; juegoActivo = true;
                        labelMensaje.setVisible(false);
                    } else {
                        togglePausa();
                    }
                    return;
                }
                if (k == KeyCode.UP    || k == KeyCode.W) ultimaTecla = KeyCode.UP;
                if (k == KeyCode.DOWN  || k == KeyCode.S) ultimaTecla = KeyCode.DOWN;
                if (k == KeyCode.LEFT  || k == KeyCode.A) ultimaTecla = KeyCode.LEFT;
                if (k == KeyCode.RIGHT || k == KeyCode.D) ultimaTecla = KeyCode.RIGHT;
            });
            rootPane.requestFocus();
        });
    }

    private void togglePausa() {
        juegoActivo = !juegoActivo;
        labelMensaje.setText(juegoActivo ? "" : "PAUSA  —  ENTER para continuar");
        labelMensaje.setVisible(!juegoActivo);
    }

    

    private void iniciarGameLoop() {
        
        gameLoop = new Timeline(new KeyFrame(Duration.millis(150), e -> {
            if (!juegoActivo) return;
            try { jugador.mover(ultimaTecla, tablero); }
            catch (InterruptedException ex) { ex.printStackTrace(); }
            verificarColisionFantasmas();
            verificarVictoria();
            if (jugador.isAsustadoActivado()) activarModoAsustado();
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);

        
        fantasmaLoop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            if (!juegoActivo) return;
            fantasmaBlue.iniciarMovimiento(jugador, tablero, mapa);
            fantasmaRed.iniciarMovimiento(jugador, tablero, mapa);
            verificarColisionFantasmas();

            if (jugador.isAsustadoActivado()) {
                long elapsed = System.currentTimeMillis() - jugador.getMilisAsustado();
                if (elapsed >= 8000) {
                    terminarModoAsustado();
                } else if (elapsed >= 6000) {
                    
                    boolean vis = (elapsed / 300) % 2 == 0;
                    fantasmaBlue.getImagen().setVisible(vis);
                    fantasmaRed.getImagen().setVisible(vis);
                }
            }
        }));
        fantasmaLoop.setCycleCount(Animation.INDEFINITE);

        gameLoop.play();
        fantasmaLoop.play();
    }

    

    private void activarModoAsustado() {
        if (imgFantasmaAsustado != null) {
            fantasmaRed.getImagen().setImage(imgFantasmaAsustado);
            fantasmaBlue.getImagen().setImage(imgFantasmaAsustado);
        }
        fantasmaRed.setEstaAsustado(true);
        fantasmaBlue.setEstaAsustado(true);
        fantasmaRed.getImagen().setVisible(true);
        fantasmaBlue.getImagen().setVisible(true);
    }

    private void terminarModoAsustado() {
        jugador.setAsustadoActivado(false);
        fantasmaRed.recuperarAsustado(nombreFantasmas.RED);
        fantasmaBlue.recuperarAsustado(nombreFantasmas.BLUE);
        if (imgFantasmaRed  != null) fantasmaRed.getImagen().setImage(imgFantasmaRed);
        if (imgFantasmaBlue != null) fantasmaBlue.getImagen().setImage(imgFantasmaBlue);
        fantasmaRed.getImagen().setVisible(true);
        fantasmaBlue.getImagen().setVisible(true);
    }

    

    private void verificarColisionFantasmas() {
        boolean hitRed  = jugador.getFila() == fantasmaRed.getFila()
                    && jugador.getColumna() == fantasmaRed.getColumna();
        boolean hitBlue = jugador.getFila() == fantasmaBlue.getFila()
                    && jugador.getColumna() == fantasmaBlue.getColumna();
        if (!(hitRed || hitBlue)) return;

        if (jugador.isAsustadoActivado()) {
            Fantasma comido = hitRed ? fantasmaRed : fantasmaBlue;
            comido.getImagen().setVisible(false);
            comido.setPosicion(comido.getPosicionInicial());
            tablero.setRowIndex(comido.getImagen(), comido.getPosicionInicial()[0]);
            tablero.setColumnIndex(comido.getImagen(), comido.getPosicionInicial()[1]);
            new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
                comido.getImagen().setVisible(true);
                Image img = (comido == fantasmaRed) ? imgFantasmaRed : imgFantasmaBlue;
                if (img != null) comido.getImagen().setImage(img);
            })).play();
        } else {
            perderVida();
        }
    }

    private void verificarVictoria() {
        if (totalPuntosEnMapa > 0 && jugador.PuntosRecolectados >= totalPuntosEnMapa) {
            juegoActivo = false;
            detenerLoops();
            labelMensaje.setText("  haz ganado ");
            labelMensaje.setVisible(true);
            new Timeline(new KeyFrame(Duration.seconds(2), e -> irAFinJuego())).play();
        }
    }

    private void actualizarIconosVidas(int vidas) {
        if (vida1 != null) vida1.setVisible(vidas >= 1);
        if (vida2 != null) vida2.setVisible(vidas >= 2);
        if (vida3 != null) vida3.setVisible(vidas >= 3);
    }

    public void reproducirSonidoMuerte(){
        try{
            String ruta = getClass().getResource("/Imagenes usadas/pacman-dies.mp3").toExternalForm();
            javafx.scene.media.Media media = new javafx.scene.media.Media(ruta);
            javafx.scene.media.MediaPlayer sonidoMuerte = new javafx.scene.media.MediaPlayer(media);
            sonidoMuerte.setVolume(1.0);
            sonidoMuerte.setOnEndOfMedia(() ->{
                sonidoMuerte.dispose();
                Platform.runLater(()-> irAFinJuego());
            });
            sonidoMuerte.play();
        }catch(Exception e){
            System.out.println("no cargo" + e.getMessage());
            irAFinJuego();
        }
    }

    private void perderVida() {
        juegoActivo = false;
        int vidas = jugador.getVidas() - 1;
        jugador.setVidas(vidas);
        actualizarIconosVidas(vidas);
        if (vidas <= 0) {
            reproducirSonidoMuerte();
        } else {
            resetPosiciones();
            juegoIniciado = false;
            labelMensaje.setText(" se perdioo una vida   ENTER para continuar");
            labelMensaje.setVisible(true);
        }
    }

    private void resetPosiciones() {
        int fi = 1, ci = 1;
        outer:
        for (int f = 1; f < mapa.MAPA.length - 1; f++)
            for (int c = 1; c < mapa.MAPA[f].length - 1; c++)
                if (mapa.MAPA[f][c] == 0) { fi = f; ci = c; break outer; }

        jugador.setPosicion(fi, ci);
        tablero.setRowIndex(jugador.getImageView(), fi);
        tablero.setColumnIndex(jugador.getImageView(), ci);

        for (Fantasma fg : new Fantasma[]{fantasmaRed, fantasmaBlue}) {
            fg.setPosicion(fg.getPosicionInicial());
            tablero.setRowIndex(fg.getImagen(), fg.getPosicionInicial()[0]);
            tablero.setColumnIndex(fg.getImagen(), fg.getPosicionInicial()[1]);
        }
        terminarModoAsustado();
        jugador.setAsustadoActivado(false);
    }

    

    private void detenerLoops() {
        if (gameLoop     != null) gameLoop.stop();
        if (fantasmaLoop != null) fantasmaLoop.stop();
    }

    private void irAFinJuego() {
        detenerLoops();
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/finJuego.fxml"));
                Parent root = loader.load();
                FinJuegoController finCtrl = loader.getController();
                finCtrl.setMapaAnterior(mapa);
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) { e.printStackTrace(); }
        });
    }

    public void setMapa(Mapa mapa){ 
        this.mapa = mapa; }

    public void setPrimaryStage(Stage s) { 
        this.primaryStage = s; }
}
