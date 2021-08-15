import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Representación del tablero para el proyecto.
 * Elaborado en clase junto con el ayudante.
 */
class Tablero {
  /* La dimension fija del tablero. */
  int dimension = 8;
  /* Representación del tablero como una matriz. */
   int[][] mundo;
  boolean[] direccion;
  ArrayList<Coordenada> casillasValidas = new ArrayList<Coordenada>();
    
  /**
   * Constructor de un tablero con otro tablero.
   * @param Tablero el tablero con el que se construirá un nuevo tablero.
   */
  Tablero(Tablero tablero){
    this(tablero.mundo);
    this.casillasValidas = (ArrayList<Coordenada>)tablero.casillasValidas.clone();
    direccion = new boolean[8];
  }

  /**
   * Constructor principal.
   * Crea un tablero a partir de su dimension.
   * @param dimension La dimensión del tablero.
   */
  Tablero(int dimension){
    this.dimension = dimension;
    // Mundo lleno de 0's por defecto
    mundo = new int[dimension][dimension];
    // Inicializa los valores auxiliares
    direccion = new boolean[8];
    // Configuración inicial
    mundo[dimension/2][dimension/2] = 1;
    mundo[dimension/2-1][dimension/2] = -1;
    mundo[dimension/2][dimension/2-1] = -1;
    mundo[dimension/2-1][dimension/2-1] = 1;
  }

  /**
   * Constructor de copia para un Tablero.
   * @param mundo El arreglo bidimensional que representa el mundo.
   */
  Tablero(int[][] mundo) {
    dimension = mundo.length;
    this.mundo = new int[mundo.length][mundo[0].length];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        this.mundo[i][j] = mundo[i][j];
      }
    }
     direccion = new boolean[8];
  }
  
  /**
   * Dibuja una representación gráfica del tablero en la ventana de Processing. 
   */
  void display() {
    // Usamos una variable para desplazar las casillas y sus dibujos, como el tablero es cuadrado
    // no habrá problema
    int posicion = width/tablero.dimension;
        
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        // Dibujar las casillas del tablero:
        fill(80);
        stroke(255);
        // Dibujar las jugadors del tablero:
        if (mundo[i][j] == 1) {
          fill(0);
          noStroke();
          ellipse((j*posicion)+(posicion/2), (i*posicion)+(posicion/2), posicion/2, posicion/2);
        } else if (mundo[i][j] == -1) {
          fill(255);
          noStroke();
          ellipse((j*posicion)+(posicion/2), (i*posicion)+(posicion/2), posicion/2, posicion/2);
        }else{
          if(mundo[i][j] == 2){
           fill(0, 0, 0, 127);
           noStroke();
           ellipse((j*posicion)+(posicion/2), (i*posicion)+(posicion/2), posicion/4, posicion/4);
          }
        }
      }
    }
    
    // Dibuja lineas
    // Presenta un bug aleatorio al dibujar las lineas
    for(int i = 0; i < dimension; i++){
      line(posicion*i, 0, posicion*i, height);
      line(0, posicion*i, width, posicion*i);
    }
  }
  
  /**
   * Cuenta la cantidad de fichas dado el jugador.
   * @param jugador El entero que representa al jugador.
   * @return El número de fichas del jugador seleccionado.
   */
  int cuentaFichas(int jugador) {
    int jugadors = 0;
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        if (mundo[i][j] == jugador)
          jugadors += 1;
    return jugadors;
  }
  
  /**
   * Modifica el valor del mundo dado sus coordenadas y el jugador. 
   * @param x La coordenada x seleccionada por el jugador.
   * @param y La coordenada y seleccionada por el jugador.
   * @param jugador Número que representa el jugador.
   */
  void colocaCasilla(int x, int y, int jugador) {
    mundo[y][x] = jugador;
    verificacion(x, y, jugador);
  }

  /**
   * Determina si la casilla seleccionada es válida para colocar una jugador
   * @param x La coordenada x seleccionada por el jugador.
   * @param y La coordenada y seleccionada por el jugador.
   * @return true si la casilla es válida para colocar una nueva ficha.
   *         false en otro caso.
   */
  boolean casillaValida(int x, int y) {
    try{
      if (mundo[y][x] == 2){
        casillasValidas.remove(new Coordenada(y , x));
         return true;
      }else{
         return false;
      }
    }catch(Exception e){}
    return false;
  }

   /**
    * Le asigna los movimientos validos a la lista de casillas validas
    * @param el número que representa al jugador.
    */
  void asignaMovimientosValidos(int jugador){
   for(int i = 0; i < dimension ; i++){
     for(int j = 0; j < dimension ; j++){
       if(mundo[i][j] == 0){
         if(movimientosValidos(i , j , jugador)){
          mundo[i][j] = 2;
          casillasValidas.add(new Coordenada(i, j));
         }   
       }  
     }
   }
  }
  
 /**
  * Veritica que queden tiros posibles para jugar
  * @param jugador Número que representa al jugador
  * @return true si existen casillas libres.
  *         false en otro caso.
  */
  public boolean casillasLibres(int jugador){
    int oponente = jugador * -1;
     for(int i = 0 ; i < dimension ; i++){
         for(int j = 0 ; j < dimension ; j++){
            if(mundo[i][j] == 0 || mundo[i][j] == 3){
              if(movimientosValidos(i, j , oponente)){
                casillasValidas.clear();
                return true;
              }
              
            }
         }
      }
      return false;
  }
    
  /**
   * Método que elimina las casillas válidas al final del turno.
   * Para poder reasignar casillas al nuevo turno.
   */
  public void liberaValidas (){
    // Itera sobre las coordenadas en la lista y las cambia a 0
    for(Coordenada coordenada : casillasValidas){
      mundo[coordenada.getX()][coordenada.getY()] = 0;  
    }
    // Limpia la lista
    casillasValidas.clear();
  }

  /**
   * Metodo para hacer la verificacion en busca de casillas adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   */
  public void verificacion(int y, int x, int jugador){
    // Lista para almacenar las casillas adquiridas en cada verificacion
    ArrayList <Coordenada> listCoordenadas = new ArrayList <Coordenada>();
    try{    
      // Llama a todas las verificaciones, 8 en total
      verificacionUp(x, y, jugador, listCoordenadas);
      // Limpiamos la lista para utilizarla tras cada verificacion
      listCoordenadas.clear();
      verificacionDown(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionLeft(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionRight(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionDiagUpLeft(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionDiagDownLeft(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionDiagDownRight(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
      verificacionDiagUpRight(x, y, jugador, listCoordenadas);
      listCoordenadas.clear();
    }catch(ArrayIndexOutOfBoundsException e){
      // No es posible verificar mas hacia arriba
    }
  }
    /**
   * Verificacion de arriba hacia abajo en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionUp(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x][y-1] == 0 || mundo[x][y-1] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x][y-1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x, y-1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionUp(x, y-1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de abajo hacia arriba en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionDown(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer      
    if(mundo[x][y+1] == 0 || mundo[x][y+1] == 2)
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    else if(mundo[x][y+1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x, y+1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionDown(x, y+1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de izquierda a derecha en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionLeft(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer        
    if(mundo[x-1][y] == 0 || mundo[x-1][y] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x-1][y] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x-1, y));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionLeft(x-1, y, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de derecha a izquierda en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionRight(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x+1][y] == 0 || mundo[x+1][y] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x+1][y] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x+1, y));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionRight(x+1, y, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de abajo hacia arriba diagonalmente hacia la izquierda en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionDiagUpLeft(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x+1][y+1] == 0 || mundo[x+1][y+1] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x+1][y+1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x+1, y+1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionDiagUpLeft(x+1, y+1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de arriba hacia abajo diagonalmente hacia la izquierda en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionDiagDownLeft(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x-1][y-1] == 0 || mundo[x-1][y-1] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x-1][y-1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x-1, y-1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionDiagDownLeft(x-1, y-1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de arriba hacia abajo diagonalmente hacia la derecha en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionDiagDownRight(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x+1][y-1] == 0 || mundo[x+1][y-1] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x+1][y-1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x+1, y-1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionDiagDownRight(x+1, y-1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Verificacion de abajo hacia arriba diagonalmente hacia la derecha en busca de mas jugadors adquiridas por el jugador
   * @param x coordenada x de la casilla introducida por el jugador
   * @param y coordenada y de la casilla introducida por el jugador
   * @param jugador la jugador del jugador (blanca o negra)
   * @param listCoordenadas la lista donde se agregan las jugadors a cambiar
   */
  void verificacionDiagUpRight(int x, int y, int jugador, ArrayList <Coordenada> listCoordenadas){
    // Casilla de recomendacion o vacia, nada que hacer
    if(mundo[x-1][y+1] == 0 || mundo[x-1][y+1] == 2){
      return;
    // Encuentra casilla del mismo color, pinta todas las encontradas
    }else if(mundo[x-1][y+1] == jugador){
      pintarSecuencia(listCoordenadas, jugador);
      return;
    }else{
      // Agrega la casilla contigua a la lista
      listCoordenadas.add(new Coordenada(x-1, y+1));
      // Llama la funcion recursiva con el siguiente a verificar en la misma direccion
      verificacionDiagUpRight(x-1, y+1, jugador, listCoordenadas);
    }
  }
  
  /**
   * Metodo que pinta una secuencia de jugadors
   * @param list la lista de las coordenadas a pintar
   * @param jugador el color a pintar
   */
  void pintarSecuencia(ArrayList <Coordenada> list, int jugador){
    try{
      for(Coordenada tupla : list){
        // TODO Seria bonito que esto lo haga esperando cada segundo
        mundo[tupla.getX()][tupla.getY()] = jugador;
      }
    }catch(Exception e){}
  }
  
  /**
   * Revisa que la casilla introducida sea un movimiento válido.
   * @param x Coordenada x.
   * @param y Coordenada y.
   * @param jugador Número que representa al jugador.
   * @return true si es un movimiento válido.
   *         false en otro caso.
   */
  boolean movimientosValidos(int  x , int y , int jugador ){
      int oponente = jugador * -1;
      if(x == 0){
        if(y == 0){
          // Esquina superior izquierda
          if(mundo[x][y+1] == oponente)
                direccion[4] = verifica(x , y , 4 , jugador);  
          if(mundo[x+1][y] == oponente)
             direccion[6] = verifica(x , y , 6, jugador);  
          if(mundo[x+1][y+1] == oponente)
             direccion[7] = verifica(x , y , 7, jugador);    
        }else if(y == dimension-1){
          // Esquina superior derecha
          if(mundo[x][y-1] == oponente)
             direccion[3] = verifica(x , y , 3, jugador); 
          if(mundo[x+1][y] == oponente)
            direccion[6] = verifica(x , y , 6, jugador);
          if(mundo[x+1][y-1] == oponente)
              direccion[5] = verifica(x , y , 5, jugador);
        }else{
         // Linea superior
         if(mundo[x][y-1] == oponente)
             direccion[3] = verifica(x , y , 3, jugador); 
          if(mundo[x+1][y-1] == oponente)
              direccion[5] = verifica(x , y , 5, jugador);
          if(mundo[x+1][y] == oponente)
            direccion[6] = verifica(x , y , 6, jugador);
          if(mundo[x+1][y+1] == oponente)
             direccion[7] = verifica(x , y , 7, jugador); 
          if(mundo[x][y+1] == oponente)
              direccion[4] = verifica(x , y , 4, jugador);  
            }
      }else{
         if( x  == dimension-1){
            if(y == 0){
              // Esquina inferior izquierda
               if(mundo[x][y+1] == oponente)
                  direccion[4] = verifica(x , y , 4, jugador);
               if(mundo[x-1][y] == oponente)
                  direccion[1] = verifica(x , y , 1, jugador);  
               if(mundo[x-1][y+1] == oponente)
                  direccion[2] = verifica(x , y , 2, jugador);  
            }else if(y == dimension-1){
              // Esquina inferior derecha 
              if(mundo[x][y-1] == oponente)
                direccion[3] = verifica(x , y , 3, jugador); 
              if(mundo[x-1][y] == oponente)
                direccion[1] = verifica(x , y , 1, jugador);  
              if(mundo[x-1][y-1] == oponente)
                direccion[0] = verifica(x , y , 0, jugador); 
            }else{
              // Borde inferior
              if(mundo[x][y-1] == oponente)
                direccion[3] = verifica(x , y , 3, jugador); 
              if(mundo[x-1][y] == oponente)
                direccion[1] = verifica(x , y , 1, jugador);  
              if(mundo[x-1][y-1] == oponente)
                direccion[0] = verifica(x , y , 0, jugador);  
              if(mundo[x][y+1] == oponente)
                direccion[4] = verifica(x , y , 4, jugador);  
              if(mundo[x-1][y+1] == oponente)
                direccion[2] = verifica(x , y , 2, jugador);  
           }
        }else{
            if(y == 0){
              // Borde izquierdo
             if(mundo[x-1][y] == oponente)
               direccion[1] = verifica(x , y , 1, jugador);    
             if(mundo[x][y+1] == oponente)
               direccion[4] = verifica(x , y , 4, jugador);  
             if(mundo[x-1][y+1] == oponente)
               direccion[2] = verifica(x , y , 2, jugador);  
             if(mundo[x+1][y] == oponente)
               direccion[6] = verifica(x , y , 6, jugador);
             if(mundo[x+1][y+1] == oponente)
               direccion[7] = verifica(x , y , 7, jugador); 
            }else if(y == dimension-1){
              // Borde derecho
               if(mundo[x][y-1] == oponente)
                 direccion[3] = verifica(x , y , 3, jugador); 
               if(mundo[x-1][y] == oponente)
                 direccion[1] = verifica(x , y , 1, jugador);  
               if(mundo[x-1][y-1] == oponente)
                 direccion[0] = verifica(x , y , 0, jugador);  
               if(mundo[x+1][y-1] == oponente)
                 direccion[5] = verifica(x , y , 5, jugador);
               if(mundo[x+1][y] == oponente)
                 direccion[6] = verifica(x , y , 6, jugador);
             }else{
               // Centro del tablero, todo lo que no es borde o esquina
               if(mundo[x-1][y-1] == oponente)
                 direccion[0] = verifica(x , y , 0, jugador);  
               if(mundo[x-1][y] == oponente)
                 direccion[1] = verifica(x , y , 1, jugador);  
               if(mundo[x-1][y+1] == oponente)
                 direccion[2] = verifica(x , y , 2, jugador);  
               if(mundo[x][y-1] == oponente)
                 direccion[3] = verifica(x , y , 3, jugador); 
               if(mundo[x][y+1] == oponente)
                 direccion[4] = verifica(x , y , 4, jugador);  
               if(mundo[x+1][y-1] == oponente)
                 direccion[5] = verifica(x , y , 5, jugador);
               if(mundo[x+1][y] == oponente)
                 direccion[6] = verifica(x , y , 6, jugador);
               if(mundo[x+1][y+1] == oponente)
                 direccion[7] = verifica(x , y , 7, jugador); 
            }
         }
      }
      boolean b = false;
      for(int i  = 0 ; i < 8 ; i++){
          b = b || direccion[i];
          direccion[i] = false;
      }
      return b;
  }
  
  /**
   * Verifica si en la direccion de la casillas se sigue de las fichas del color contrario 
   * terminando en una del color actual.
   * @param x Coordenada x.
   * @param y Coordenada y.
   * @param direccion Dirección hacia donde verifica que sigan las fichas, 
   *        representada con un número
   * @param jugador Número para representar al jugador.
   * @return true si existe una verificación en esa dirección
   *         false en otro caso.
   */
  boolean verifica(int x , int y , int direccion, int jugador){
    int i = 0;
    int j = 0;
    int oponente = jugador * -1;
    
    switch(direccion){
      case 0:
        for(i = 2; x-i >= 0; i++)
          if(y-i >= 0 && mundo[x-i][y-i] != oponente)
            return mundo[x-i][y-i] == jugador ? true : false;
        break;
      case 1:
        for(i = 2; x-i >= 0; i++)
          if(mundo[x-i][y] != oponente)
            return mundo[x-i][y] == jugador ? true : false;
        break;
      case 2:
        for(i = 2; x-i >= 0; i++)
          if(y+i < dimension && mundo[x-i][y+i] != oponente)
            return mundo[x-i][y+i] == jugador ? true : false;
        break;
      case 3:
        for(j = 2; y-j >= 0; j++)
          if(mundo[x][y-j] != oponente)
            return (mundo[x][y-j] == jugador) ? true : false;
        break;
      case 4:
        for(j  = 2; j+y < dimension; j++)
          if(mundo[x][y+j] != oponente)
            return (mundo[x][y+j] == jugador) ? true : false;
        break;
      case 5:
        for(i = 2; x+i < dimension; i++)
          if(y-i >= 0 && mundo[x+i][y-i] != oponente)
            return mundo[x+i][y-i] == jugador ? true : false;
        break; 
      case 6:
        for(i = 2; i+x < dimension; i++)
          if(mundo[x+i][y] != oponente)
            return mundo[x+i][y] == jugador ? true : false;
        break;
      case 7:
        for(i = 2; i+x < dimension; i++)
          if(y+i < dimension && mundo[x+i][y+i] != oponente)
            return mundo[x+i][y+i] == jugador ? true : false;
          break;
      default:
        return false;
    }
    return false;    
  }
    
}
