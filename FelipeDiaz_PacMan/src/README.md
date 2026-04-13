1- Descripcion del Preoyeto:
Se trata de la creacion del juego clasico de PacMan donde conmes e intentas escapar de los fantasmas.
intenta comer todos los punticos para lograr la victorias mientras escapas de los fantasmitas.

Movimiento: el juugador se puede  mover en 4 direcciones mediante las flechas del teclado o por la teclas de WADS

Vidas: el jugadore cuenta con 3 vidas en el juego y cada vez que el jugador colisiona con un fantasma pierde una hasta quedar sin nada

Modo Asustado en los fantasma: este modo hace qque pacman pueda comer a los fantasmas mediante un super punto o un punto especial que hay  en el mapa.

2- Funcionalidades implementadas: 
He implementado la funcion para cargar las imagenes que utilice correctamente y tambien una funcion por si no se encuentra la ruta de la imagen que utlilizo.

Sistama par amapas: implemente que se pueda seleccionar entre un mapa y otro para una mejor experienca.

Audio : el juego cuenta con sonidos lo cual lo hace aun mejor para la experiencia de juego.

Puntuacion : la puntuacioin va aumentando mediante el pacman va comiendo los puntos.

IA para los fatasmas: esta es la parte mas dificil del juego. 
los fantasmas tienen un movimiento automatizado con una logica de MAX_VALUE para que persigan al jugador.

Control: cuenta con controles de animaciones segun  la direccion  que tome y tambien la detencion de colisiones con laas paredes para que no las traspase.

Una de la funciones que deje pendientes fue la parte de la cereza, y es que en el juego de PacMan mediente un intervalo aparece un fruta(mayormente una cereza) que te da puntos adicionales, pero no la pude agregar a la funcionalidades porque cada vez que intentaba colocarla en el tablero se deformaba la forma del tablero y esto hacia que el pacman traspasara las paredes.
Otra funcionalidad que no pude implementar fue la de que al momento de que pacman colisionara con los fantasmas en el modoAsustado sumara puntos adicionales.


3- Requisitos PREVIO:

JAVA JDK : Version = o > a la 17 
JAVAFX SKD Instalada
Para el sonido ten en cuneta tener una biblioteca aadicional par alo ssoonids " javafx.media"

4- COMO EJECUTAR EL PROYECTO

Asegurarse de tener bien configurado el JAVAFX con todas sus librerias agregadas en el entorno del proyecto.

abre la carpeta raiz del proyecto en tu editor preferido luego dirigite a la clase App.java y ejecutas el proyecto puedes hacerlo seleccionando Run o mediante F5.

5- ESTRUCTURA DEL PROYECTO 
App.java : el punto de entrada  para la pantalla de inicio.
Mapa.java: aqui tenemos la logica definida de los niveles mediante matriz.
Jugador.java: aqui se maneja la rotacion de imagen, la colisiones, la puntuacion  y la logicapar ael movimiento.
Fantasma.java: el comportamiento de los fantasmas, los estados que tendran los fantasma.
Las clases controller: aqui se controla todas las iteraciones entra la interfaz y la la logica en java que estan en el fxml.

JuegoController.java: este es el cerebro del proyecto o se podria decir que el motor principal del juego, aqui es donde va toda la logica del juego en si.
tenemos un metod para iniciar el juego, que es la logica de entada para cuando la interfaz se presente y es que tenemos metodos para la carga de losa recursos utilizados como las imagenes por ejemplo.
tambien tengo metodos para coonfiguarar de forma dinamica con las rstrisciones de las columnas.
tambien tengo metodo para el dibujo del tablero con el metodo de construirTablero, esto lo que hace es que recorre todo lo que es la matriz de mi clase Mapa.java par aque lo haga de modo de Nodos visuales.
Tambien tengo metodos para las colisiones de los fantasmas esto lo que hace es que compara las columnas y la sfilas del jugaor con los fantasmas si estan en la misma posiciones ahi se produce la colision.
Tambien esta que si el fantasma esta en modoAsustado y se produce una colison y esto hhara que el fantasma vuelva a su base se inicio.
Tambien tengo los flujos para la navegacion y es que esta clase o controller se encarga de la transicion hacia el final, con el metodo de IrAFinJuego lo que hace es cargar la vista de FinJuego.fxml.
Tambien tengo un metodo para que mediante la pantalla del final del juego se pueda seleccionar jugar de nuevo en el mismo mapa o podria volver a la pantalla de inicio para seleccionar un mapa distinto.
Tambien esta la logica de la velocida de los fantasmas y del pacman y es que los fantasmas deben tener un intervalo mayor que el de pacaman para quye se muevan mas lento. 


Vistas.FXML: aqui esta todo lo visual del juego, estan hechos en Scene Builder y es l aparte visual del juego la cual se controla mediante onAction o fx:id: para los controller.


6- Decisiones del Dise;o: 

Gestion de recursos : las rutas de los sonidos y de las imagenes se cargan desde el directorio de recursos llamada Imagenes Usadas, esta estra en la raiz del proyecto para ai asegurar la portabilidad del proyecto a la hora de cargar las rutas de los recursos
Ciclo de Jueo: utilice los TimeLine y los KeyFrame de javafx para hacerle una mejora a los movimientos de forma asincronica del jugador y de los fantasmas.
GridPane: el tablero o el mapa se genera de una forma dinamica en un GridPane esto ayuda a la colision de los nodos para las cordenadas especificas.

7- AUTOR
Nombre : Felipe Elian Diaz Almanzar

Fecha de Entrega:  Lunes 13 de Abril del 2026




 



