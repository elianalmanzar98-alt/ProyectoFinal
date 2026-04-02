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

    public boolean isEstaAsustado(){
        return estaAsustado;
    }
    public void setEstaAsustado(boolean estaAsustado){
        this.estaAsustado = estaAsustado;

    }
    public int [] getPosicionInicial(){
        return posicionInicial;
    }

    nombreFantasmas nombre;
    

    public Fantasma(int fila, int columna, ImageView fantasmaImag, nombreFantasmas nombre){
        this.fila = fila;
        this.columna =  columna;
        this.fantasmaImag = fantasmaImag;
        this.nombre = nombre;
        this.estaAsustado = false;
        posicionInicial = new int[]{fila, columna};
    }
    public void iniciarMovimiento(Jugador jugador, GridPane tablero, Mapa mapa){
        int objetivodFila, objetivodColumna;
        if(nombre == nombreFantasmas.RED){
            objetivodFila = jugador.getFila() -2;
            posicionInicial = new int[]{11,12};
            objetivodColumna = jugador.getColumna() +2;
        }
        else{
            objetivodColumna = jugador.getColumna();
            objetivodFila = jugador.getFila();
            posicionInicial = new int[]{11,11};
        }

        List<int[]> posiblesMovimientos = new ArrayList<>();

        if (mapa.getCelda(fila + 1, columna)!=1){
            if((fila + 1) != posicionInicial[0] || columna != posicionInicial[1]) posiblesMovimientos.add(new int[]{1,0});
        }
        if (mapa.getCelda(fila - 1, columna - 1) !=1){
            if (fila != posicionInicial[0] || (columna -1) != posicionInicial[1]) posiblesMovimientos.add(new int[]{0,1});
        }

        if(mapa.getCelda(fila - 1, columna) !=1){
            if((fila -1) != posicionInicial[0] || columna != posicionInicial[1]) posiblesMovimientos.add(new int[]{-1,0});
        }
        if(mapa.getCelda(fila, columna + 1) !=1){
            if(fila != posicionInicial[0] || (columna + 1)  != posicionInicial[1]) posiblesMovimientos.add(new int[]{0,1});
        }

        if (!posiblesMovimientos.isEmpty()){


            if(ultimoMovimiento != null && posiblesMovimientos.size() > 1){
                posiblesMovimientos.removeIf(mov -> mov [0]== -ultimoMovimiento[0] && mov [1] == -ultimoMovimiento[1]);
            }
            int [] mejorMovimiento = posiblesMovimientos.get(0);
            double distanciaMayor = Double.MIN_VALUE;

            //aqui quiero buscar el movimiento de mi ppacman mas lejano 

            for (int[] mov : posiblesMovimientos){
                int nuevaFila = fila + mov[0];
                int nuevaColumna = columna + mov[1];
                double distancia = Math.sqrt(
                    Math.pow(nuevaFila - objetivodFila, 2)+ Math.pow(nuevaColumna - objetivodColumna, 2)
                );
                if(distancia > distanciaMayor){
                    distanciaMayor = distancia;
                    mejorMovimiento = mov;
                }
            }
            fila += mejorMovimiento[0];
            columna += mejorMovimiento[1];
            ultimoMovimiento = mejorMovimiento;
        }

        tablero.setRowIndex(fantasmaImag, fila);
        tablero.setColumnIndex(fantasmaImag,columna);

    }

    public void recuperarAsustado(nombreFantasmas n ){
        if( n == nombreFantasmas.RED)
        fantasmaImag.setImage(new Image());
        else fantasmaImag.setImage(new Image());

        fantasmaImag.setVisible(true);
        this.estaAsustado = false;

    }

    public void setImagen(Image image){
        fantasmaImag = new ImageView(image);
    }
    public ImageView getImagen(){
        return fantasmaImag;
    }
    public void setPosicion(int[] pos){
        this.fila = pos[0];
        this.columna = pos[1];
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }




}
