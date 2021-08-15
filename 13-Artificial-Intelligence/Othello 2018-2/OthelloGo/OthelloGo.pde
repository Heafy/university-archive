/*Tablero del juego. */
Tablero tablero;
/* La dimension fija del tablero. */
int dimension = 8;
/* El turno del jugador representado con un booleano. */
boolean turno = true;
/* Boolean para mostrar el menu */
boolean menu = true;
/* Int para representar al jugador actual. (-1, 1) */
int jugador;
/* Profundidad del arbol */
int profundidad;

/**
 * Metodo con la configuracion inicial del juego
 */
void setup() {
  size(500,500);
  background(#23A231); 
  tablero = new Tablero(dimension);
  jugador = 1;
  // Asigna las casillas validas para el primer turno
  tablero.asignaMovimientosValidos(jugador);
  // En caso de que algo salga mal con el menu, 
  // la profundidad del árbol será 3 por defecto.
  profundidad = 3; 
}

/**
 * Metodo que se dibuja en cada frame del juego
 */
void draw() {
  background(#23A231);
  tablero.display();
      
  // Menu para la dificultad
  if(menu){
    // x, y, w, h
    // w: anchura
    // h: largo
    fill(255);
    // Rectangulo principal
    rect(100, 100, 300, 300);
    fill(165);
    // Retangulo fácil
    rect(150, 180, 200, 60);
    // Retangulo medio
    rect(150, 250, 200, 60);
    // Retangulo difícil
    rect(150, 320, 200, 60);
   
    // Texto
    fill(0);
    textSize(40);
    text("Dificultad", 150, 150);
    text("Fácil", 210, 225);
    text("Medio", 195, 295);
    text("Difícil", 200, 365);
  }
  
  // Jugada del agente
  if(jugador == -1){
   Coordenada coordenada = jugadaAgente();
   int x = coordenada.getX();
   int y = coordenada.getY();
   infoJugada(x, y, jugador);
   tablero.colocaCasilla(x, y, jugador);
    
     tablero.liberaValidas();
     turno = !turno;
     if (turno) {
        jugador = 1;
     } else {
        jugador = -1;
     }   
  }
  // Vuelve a asignar las casillas validas
  tablero.asignaMovimientosValidos(jugador);
  
  // Si no hay casillas validas
  if(tablero.casillasValidas.isEmpty()){
    // Si hay casillas libres el jugador actual pasa.
    if(tablero.casillasLibres(jugador)){
      if(jugador == 1){
        println(" El jugador no puede tirar. Pasa.");
      }else{
        println(" El agente no puede tirar. Pasa.");
      }
      tablero.liberaValidas();
      turno = !turno;
      if (turno) {
        jugador = 1;
      } else {
        jugador = -1;
     }   
    }else{
      // Si no hay casillas validas cuenta las fichas actuales para 
      // decidir quien ganó
      println("Fichas negras: " + tablero.cuentaFichas(1));
      println("Fichas blancas: " + tablero.cuentaFichas(-1));
      if(tablero.cuentaFichas(1)>=tablero.cuentaFichas(-1)){
          if(tablero.cuentaFichas(1) == tablero.cuentaFichas(-1)){
            println("Empate");
          }else{
            println("Ganó el jugador");
          }    
        }else{
          println("Ganó el agente");
        }
        stop();
      }
    }
}


/**
  * Método que se ejecuta cuando se da click en una casillla
  */
void mouseClicked() {
  
  // Menu para elegir dificultad
  if(menu){
    // Selecciona la dificultad facil
    // Cambia la profunidad a 1 y elimina el menu
    if((150 < mouseX) && (mouseX < 350) &&
    (180 < mouseY) && (mouseY < 240)){
      println("Dificultad facil seleccionada");
      profundidad = 1;
    }
    // Selecciona la dificultad medio
    // Cambia la profunidad a 3 y elimina el menu
    if((150 < mouseX) && (mouseX < 350) &&
    (250 < mouseY) && (mouseY < 310)){
      println("Dificultad medio seleccionada");
      profundidad = 3;
    }
    // Selecciona la dificultad difícil
    // Cambia la profunidad a 5 y elimina el menu
    if((150 < mouseX) && (mouseX < 350) &&
    (320 < mouseY) && (mouseY < 380)){
      println("Dificiltad difícil seleccionada");
      profundidad = 5;
    }
    menu = false;
  }// Menu

  int x = 0;
  int y = 0;

  if(jugador==1){
    x = mouseX / (width / tablero.dimension);
    y = mouseY / (height / tablero.dimension);
  }

  // Coloca una ficha en el tablero:
  if(tablero.casillaValida(x , y )){
       //println("Posicion donde tiró el jugador: (" + x + ", " + y +")" );
       infoJugada(x, y, jugador);
       tablero.colocaCasilla(x, y, jugador);
      
       tablero.liberaValidas();
       // Cambia de turno y de jugador
       turno = !turno;
       if (turno) {
          jugador = 1;
       } else {
          jugador = -1;
       }             
  }
}

/**
 * Método con el cual el agente tira una coordenada
 * @return la coordenada donde tiró el agente.
 */
Coordenada jugadaAgente(){
   Agente agente = new Agente(tablero);
   agente.arboldeJugadas(jugador, agente.arbol , profundidad);
   Coordenada coordenada = agente.miniMax(agente.arbol);
   return coordenada;
}

/**
 * Metodo para inprimir la informacion de la jugada.
 */
 void infoJugada(int x, int y, int jugador){
   String str;
   if(jugador == 1){
     str = "jugador"; 
   }else{
     str = "agente"; 
   }
   println(String.format("Ficha %s: (%d, %d)", str, x, y));
 }
