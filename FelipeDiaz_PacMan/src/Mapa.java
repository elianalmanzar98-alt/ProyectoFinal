public class Mapa {

    //aqui la logica sera de  0 para comer puntos 1 para la pared
    //2 para puntosespeciales 3 para zonas vacias 4
    // 4 para zona restringida 

    public int [][] MAPA;
    public Mapa(int [][] m){
        this.MAPA = m;
    }

    public int getCelda(int nuevaFila, int nuevaColumna){
        return MAPA[nuevaFila][nuevaColumna];
    }
}


