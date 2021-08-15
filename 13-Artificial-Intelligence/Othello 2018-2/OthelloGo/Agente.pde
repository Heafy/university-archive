/**
 * Clase Agente
 * Modela el comportamiento del agente.
 */
class Agente{
  
  public Arbol<Tablero> arbol;

  Heuristica h;
  
  public Agente(Tablero raiz){
    arbol = new Arbol(new Tablero(raiz.mundo));
    h = new Heuristica();
  }
  
  /**
   * Calcula el arbol de jugadas dada una profundidad
   * @param jugador Número que representa al jugador.
   * @param padre Árbol padre del que se llama
   * @param profundidad Número de nodos que se expandirá al árbol. 
   */
  void arboldeJugadas(int jugador , Arbol<Tablero> padre , int profundidad){
     
    if(profundidad >0){
      Tablero tablero = padre.getTablero();
      int oponente = jugador * -1;
      tablero.asignaMovimientosValidos(jugador);
      
      // Si las casillas validas son diferentes de vacio, el jugador puede tirar
      if(!tablero.casillasValidas.isEmpty()){
        for(Coordenada coordenada : tablero.casillasValidas){
          // Copia del tablero padre
          Tablero tableroAux = new Tablero(tablero); 
          tableroAux.casillaValida(coordenada.getY(), coordenada.getX());
          tableroAux.mundo[coordenada.getX()][coordenada.getY()] = jugador;
          // Tira y hace los cambios en el mundo
          tableroAux.colocaCasilla(coordenada.getY() , coordenada.getX() , jugador);
          // Elimina las casillas validas
          tableroAux.liberaValidas();
          // Lo agrega al arbol
          padre.agregaHijo(tableroAux);
          tablero.mundo[coordenada.getX()][coordenada.getY()] = 0;
        }
        // Calcula el arbol para cada uno de sus hijos
        for(Arbol<Tablero> arbol : padre.getHijo()){
          arboldeJugadas (oponente , arbol , profundidad-1);  
        }
      }else{
        // Si no hay jugadas validas
        if(tablero.casillasLibres(jugador)){
          padre.agregaHijo(new Tablero(tablero.mundo));
          for(Arbol<Tablero> arbol : padre.getHijo()){
            arboldeJugadas (oponente , arbol , profundidad-1);     
          }       
        }
      }
    }    
  }
  
  /**
   * Metodo para obtener el máximo valor.
   * @param arbol el árbol del cual buscar el máximo valor. 
   */
  float max(Arbol<Tablero> arbol){
    // Una vez que está en la hoja calcula su utilidad
    if(arbol.esHoja()){
      arbol.valor = h.utilidad(arbol.getTablero());
      return arbol.getValor();
    }else{
      float v = -Float.MAX_VALUE;
      List<Arbol<Tablero>> hijos = arbol.getHijo();
      for(Arbol arbolH : hijos){
        v = Math.max(v, min(arbolH));
      }
      arbol.valor = v;
      println("Valor max: " + v);
      return v;
    }
  }
  
  /**
   * Metodo para obtener el minimo valor.
   * @param arbol el árbol del cual buscar el mínimo valor. 
   */
  float min(Arbol<Tablero> arbol){
    // Una vez que está en la hoja calcula su utilidad
    if(arbol.esHoja()){
      arbol.valor = h.utilidad(arbol.getTablero());
      return arbol.getValor();
    }else{
      float v = Float.MAX_VALUE;
      List<Arbol<Tablero>> hijos = arbol.getHijo();
      for(Arbol arbolH : hijos){
        v = Math.min(v, max(arbolH));
      }
      arbol.valor = v;
      println("Valor min: " + v);
      return v;
    }
  }
  
  /**
   * Algoritmo MiniMax.
   * @param arbol El árbol al cual aplicar MiniMax.
   */
  Coordenada miniMax(Arbol<Tablero> arbol){
    float v = max(arbol);
    int x=0;
    int y=0;
    //Tablero para la próxima jugada.
    Tablero tablero=null;
    List<Arbol<Tablero>> hijos = arbol.getHijo();
    // Guarda el tablero con el valor que nos intereza (max/min)imizar
    for(Arbol<Tablero> n : hijos){
      if(n.getValor() == v){
        tablero = n.getTablero();
      }
    }
    // Si encuentra el tablero pasa a buscar la casilla
    if(tablero!=null){
      int[][] mundo1 = arbol.getTablero().mundo;
      int[][] mundo2 = tablero.mundo;
      for(int i = 0; i < arbol.getTablero().dimension; i++){
        for(int j = 0; j < arbol.getTablero().dimension; j++){
        // Encuentra la casilla no ocupada seleccionada de aplicar max y min
        if((mundo1[i][j] == 0 || mundo1[i][j] == 2) && mundo2[i][j] == -1){
            x=i;
            y=j;
          }
        }
      }
    }
    return new Coordenada(y,x);
  }
}
