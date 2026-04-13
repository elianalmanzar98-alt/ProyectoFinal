public class Mapa {

    //aqui la logica sera de  0 para comer puntos 1 para la pared
    //2 para puntosespeciales 3 para zonas vacias 4
    // 4 para zona restringida 

    public int [][] MAPA;
    public Mapa(int [][] m){
        this.MAPA = m;
    }
    public int getCelda(int fila, int columna){
        if(fila < 0 || fila >= MAPA.length || columna < 0 || columna >= MAPA[0].length){
            return 1;
        }
        return MAPA[fila][columna];
    }

    
    }



