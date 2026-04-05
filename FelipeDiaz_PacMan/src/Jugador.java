import javafx.scene.control.Label;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

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

    public Jugador(int fila, int columna, ImageView imagen){
        this.fila = fila;
        this.columna = columna;
        this.imagen = imagen;
        puntuacion = 0;
        vidas = 3;
        AsustadoActivado = false;
        PuntosRecolectados = 0;
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
                break;
                
        }
        if(mapa.getCelda(nuevaFila, nuevaColumna) != 1 && mapa.getCelda(nuevaFila, nuevaColumna) != 4){
            rotarImagen(tecla);
            

            boolean hayFantasmas = (fila == RED.getFila() && columna == RED.getColumna()) || (fila == BLUE.getColumna());


            fila = nuevaFila;
            columna = nuevaColumna;

            if(!hayFantasmas){
                Node n = obtenerNodoCeldaNode(tablero, nuevaColumna, nuevaFila);
                if(n != null && n.isVisible()){
                    if(mapa.getCelda(nuevaFila, nuevaColumna) == 0){
                        puntuacion+=10;
                        PuntosRecolectados ++;
                    }
                    else if(mapa.getCelda(nuevaFila, nuevaColumna) == 2){
                        puntuacion += 20;
                        PuntosRecolectados++;
                        AsustadoActivado = true;
                        RED.setEstaAsustado(AsustadoActivado);
                        BLUE.setEstaAsustado(AsustadoActivado);
                        milisAsustado = System.currentTimeMillis();

                    }
                    n.setVisible(false);
                    Platform.runLater(()->{
                        puntajeLabel.setText(puntuacion + " ");
                    });

                }

            }
            tablero.setRowIndex(imagen, fila);
            tablero.setColumnIndex(imagen, columna);

        }

    }
    public Node obtenerNodoCeldaNode(GridPane gridpane, int fila, int columna){
        for (Node node : gridpane.getChildren()){
            Integer nodeFila = GridPane.getRowIndex(node);
            Integer nodeColumna = GridPane.getColumnIndex(node);
            if (nodeFila == null) nodeFila = 0;
            if (nodeColumna == null) nodeColumna = 0;
            if(nodeFila == fila && nodeColumna == columna){
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
        public void setPuntajeLabel(Label labelPuntuacion){
            this.puntajeLabel = puntajeLabel;
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




    
    

