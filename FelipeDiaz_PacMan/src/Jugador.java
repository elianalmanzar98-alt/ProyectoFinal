import javafx.scene.control.Label;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;

public class Jugador {

    public int fila;
    public int columna;
    public ImageView imagen;
    public Mapa mapa;
    private double puntuacion;
    int vidas;
    int PuntosRecolectados;
    Label puntajeLabel;
    private boolean AsustadoActivado;
    private Fantasma RED;
    private Fantasma BLUE;
    private long milisAsustado;

    private MediaPlayer sonidoPunto;

    public Jugador(int fila, int columna, ImageView imagen){
        this.fila = fila;
        this.columna = columna;
        this.imagen = imagen;
        puntuacion = 0;
        vidas = 3;
        AsustadoActivado = false;
        PuntosRecolectados = 0;
        cargarSonidoPuntos();
    }

    private void cargarSonidoPuntos(){
        try{
        
            String ruta = getClass().getResource("/Imagenes usadas/wakawaka-pacman.mp3").toExternalForm();
            Media media = new Media(ruta);
            sonidoPunto = new MediaPlayer(media);
            sonidoPunto.setVolume(0.8);
        }catch(Exception e){
            System.out.println("no cargo " + e.getMessage());
            sonidoPunto = null;
        }
        
    }
    private void reproducirSonidoPunto(){
        if(sonidoPunto == null )
            return;
        sonidoPunto.stop();
        sonidoPunto.seek(sonidoPunto.getStartTime());
        sonidoPunto.play();
    }
    private void detenerSonidoPunto(){
        if(sonidoPunto == null)
            return;
        sonidoPunto.stop();
    }

    public void mover(KeyCode tecla, GridPane tablero)throws InterruptedException{
        int nuevaFila = fila;
        int nuevaColumna = columna;

        switch (tecla) {
            case UP:
                nuevaFila--;
                break;
            case DOWN:
                nuevaFila++;
                break;  
            case LEFT:
                nuevaColumna--;
                break;
            case RIGHT:
                nuevaColumna++;
                break;
            default:
                return;   
        }
        if (nuevaFila < 0 || nuevaFila >= mapa.MAPA.length || nuevaColumna < 0 || nuevaColumna >= mapa.MAPA[0].length)
            return;

        int celda = mapa.getCelda(nuevaFila, nuevaColumna);
        if(celda != 0 && celda != 2 && celda != 3)
            return;
        rotarImagen(tecla);
        fila = nuevaFila;
        columna = nuevaColumna;

        Node punto = buscarPuntosEnCelda(tablero, nuevaColumna, nuevaFila);
        if(punto != null && punto.isVisible()){
            if(celda == 0){
                puntuacion += 10;
                PuntosRecolectados++;
                reproducirSonidoPunto();
            }else if(celda ==2){
                puntuacion += 20;
                PuntosRecolectados++;
                reproducirSonidoPunto();
                AsustadoActivado = true;
                if(RED != null ) RED.setEstaAsustado(true);
                if(BLUE != null) BLUE.setEstaAsustado(true);
                milisAsustado = System.currentTimeMillis();

            }
            punto.setVisible(false);
            final double pts = puntuacion;
            Platform.runLater(()->{
                if(puntajeLabel != null) puntajeLabel.setText(String.valueOf((int)pts));
            });
        }else{
            detenerSonidoPunto();
        }

        
        tablero.setRowIndex(imagen, fila);
        tablero.setColumnIndex(imagen, columna);
        }

        private Node buscarPuntosEnCelda(GridPane grid, int columna, int fila){
            for (Node node : grid.getChildren()){
                if(node == imagen) continue;
                if(!(node instanceof ImageView) && ! (node instanceof Circle)) continue;
                if(node instanceof ImageView){
                    ImageView iv = (ImageView) node;
                    if(iv.getFitWidth() >= 24) continue;
                }
                Integer nFila = GridPane.getRowIndex(node);
                Integer nCol = GridPane.getColumnIndex(node);
                if(nFila == null  ) nFila = 0;
                if( nCol == null ) nCol = 0;
                if (nFila == fila && nCol == columna){
                    return node;
                }

            }
            return null;
        }
            

        private void rotarImagen(KeyCode tecla){
            switch (tecla) {
                case UP:
                    imagen.setRotate(270);

                    break;
                case DOWN:
                    imagen.setRotate(90);
                    break;
                case RIGHT:
                    imagen.setRotate(0);
                    break;
                case LEFT:
                    imagen.setRotate(180);
                default:
                    break;
            }
            
            
        }

        public int getFila(){
            return fila ;
        }
        public int getColumna(){
            return columna;
        }

        public void setMapa(Mapa mapa){
            this.mapa = mapa;
        }
        public double getPuntuacion(){
            return puntuacion;
        }
        public ImageView getImageView(){
            return imagen;
        }
        public void setFila(int fila){
            this.fila = fila;
        }
        public void setLabelPuntuacion( Label puntLabel){
            this.puntajeLabel = puntLabel;
        }
        public void setColumna( int columna){
            this.columna = columna;
        }
        public void setImagen(ImageView imagen){
            this.imagen = imagen;
        }
        public Mapa getMapa(){
            return mapa;
        }
        public void setPuntuacion(double puntuacion){
            this.puntuacion = puntuacion;
        }
        public int getVidas(){
            return vidas;
        }
        public void setVidas( int vidas){
            this.vidas = vidas;
        }
        public void setPuntajeLabel(Label label){
            this.puntajeLabel = label;
        }
        
        public boolean isAsustadoActivado(){
            return AsustadoActivado;
        }
        public void setAsustadoActivado(boolean AsustadoActivado){
            this.AsustadoActivado = AsustadoActivado;

        }
        public void setPosicion(int fila, int columna){
            this.fila = fila;
            this.columna = columna;
        }
        public void setFantasmas(Fantasma RED, Fantasma BLUE){
            this.RED = RED;
            this.BLUE = BLUE;
        }
        public long getMilisAsustado(){
            return milisAsustado;
        }        

        }




    
    

