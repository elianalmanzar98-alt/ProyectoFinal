import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Fantasma {
    

    private int fila;
    private int columna;
    private ImageView fantasmaImag;
    private int [] ultimoMovimiento = null;
    private boolean estaAsustado;
    private int [] posicionInicial;
    private int objetivoFila;
    private int objetivoColumna;

    public boolean isEstaAsustado(){
        return estaAsustado;
    }
    public void setEstaAsustado(boolean estaAsustado){
        this.estaAsustado = estaAsustado;

    }
    public int [] getPosicionInicial(){
        return posicionInicial;
    }
    public ImageView getImagen(){
        return fantasmaImag;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public void setPosicion(int[] pos){
        this.fila = pos[0];
        this.columna = pos[1];
    }
    

    nombreFantasmas nombre;
    

    public Fantasma(int fila, int columna, ImageView fantasmaImag, nombreFantasmas nombre){
        this.fila = fila;
        this.columna =  columna;
        this.fantasmaImag = fantasmaImag;
        this.nombre = nombre;
        this.estaAsustado = false;
        this.posicionInicial = new int[]{fila, columna};
    }
    public void iniciarMovimiento(Jugador jugador, GridPane tablero, Mapa mapa){

        if (nombre == nombreFantasmas.RED){
            objetivoFila = jugador.getFila() -2;
            objetivoColumna = jugador.getColumna() + 2;
        }
        if(estaAsustado){
            objetivoFila = jugador.getFila();
            objetivoColumna =jugador.getColumna();
        }
        if(estaAsustado){
            objetivoFila = 2 * fila - objetivoFila;
            objetivoColumna = 2 * columna - objetivoColumna;
        }

        List<int[]> posiblesMovimientos = new ArrayList<>();

        int [][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] d : dirs){
            int nf = fila + d[0];
            int nc = columna + d[1];
            int celda = mapa.getCelda(nf, nc);
            if(celda != 1 && celda !=4){
                posiblesMovimientos.add(d);
            }
        }

        if(!posiblesMovimientos.isEmpty()){
            if(ultimoMovimiento != null && posiblesMovimientos.size() > 1){
                posiblesMovimientos.removeIf(mov ->  mov[0] == -ultimoMovimiento[0]&&mov[1] == -ultimoMovimiento[1]);
                
            }
            int [] mejorMovimiento = posiblesMovimientos.get(0);
            double distanciaMenor = Double.MAX_VALUE;
            for(int[] mov : posiblesMovimientos){
                int nuevaFila = fila + mov[0];
                int nuevaColumna = columna + mov[1];
                double distancia = Math.sqrt(Math.pow(nuevaFila - objetivoFila, 2)+ Math.pow(nuevaColumna - objetivoColumna, 2));
                if(distancia < distanciaMenor){
                    distanciaMenor = distancia;
                    mejorMovimiento = mov;
                }

            }
            fila += mejorMovimiento[0];
            columna += mejorMovimiento[1];
            ultimoMovimiento = mejorMovimiento;
        }
        tablero.setRowIndex(fantasmaImag, fila);
        tablero.setColumnIndex(fantasmaImag, columna);
    }

    public void recuperarAsustado(nombreFantasmas n){
        try{
            String ruta = ( n == nombreFantasmas.RED) ? "/Imagenes usadas/RED.png" : "/Imagenes usadas/BLUE.png";
            Image img = new Image(getClass().getResource(ruta).toExternalForm());
            fantasmaImag.setImage(img);
        }catch(Exception e){
            System.out.println("no encontrada");
        }
        fantasmaImag.setVisible(true);
        this.estaAsustado = false;
    }
    




}
