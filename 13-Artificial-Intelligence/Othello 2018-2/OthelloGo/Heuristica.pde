
/**
 * Clase Heuristica.
 * Diferentes heurísticas para obtener la utilidad de un tablero.
 * La utilidad se mide con un valor float.
 */
class Heuristica{
  
  /**
   * Calcula las diferencias de fichas entre los dos jugadores.
   * @param tablero El tablero de donde verificar las diferencias
   * @return Un valor asignado con base en si la suma de las fichas es mayor a 0, 
   *         dando prioridad a las blancas.
   */
  float diferencias(Tablero tablero){
    float v = 0;
    float fichasNegras = tablero.cuentaFichas(1);
    float fichasBlancas = tablero.cuentaFichas(2);
    if((fichasBlancas+fichasNegras)>0){
      v = 10 * (fichasBlancas - fichasNegras) / (fichasBlancas + fichasNegras);
    }
    return v;
  }

  /**
   * Verifica si las esquinas están ocupadas y si eso ocurre les asigna un valor.
   * @param tablero El tablero de donde verificar las diferencias.
   * @return Un valor asignado con base en si la suma de las fichas ocupadas en las
   *         esquinas es mayor a 0.
   */
  float esquinas(Tablero tablero){
   int fichasBlancas = 0;
   int fichasNegras = 0;
   
   //Esquina superior izquierda.
   if(tablero.mundo[0][0] == 1){
     fichasNegras++;
   }
   if(tablero.mundo[0][0] == -1){
     fichasBlancas++;
   }
   
   //Esquina inferior izquierda.
   if(tablero.mundo[tablero.dimension - 1][0] == 1){
     fichasNegras++;
   }
   if(tablero.mundo[tablero.dimension - 1][0] == -1){
     fichasBlancas++;
   }
   
   //Esquina superior derecha.
   if(tablero.mundo[0][tablero.dimension - 1] == 1){
     fichasNegras++;
   }
   if(tablero.mundo[0][tablero.dimension - 1] == -1){
     fichasBlancas++;
   }
   
   //Esquina inferior derecha.
   if(tablero.mundo[tablero.dimension - 1][tablero.dimension - 1] == 1){
     fichasNegras++;
   }
   if(tablero.mundo[tablero.dimension - 1][tablero.dimension - 1] == -1){
     fichasBlancas++;
   }
   
   // Les da un valor alto mientras estas fichas estén ocupadas
   if((fichasBlancas + fichasNegras)>0)
     return 10 * (fichasBlancas - fichasNegras) / (fichasBlancas + fichasNegras);
   else
     return 0;
  }

  /**
   * Método para calcular la utilidad dado un tablero.
   * La utilidad es representada como una primitiva tipo float.
   * @param El tablero del cual verificar su utilidad
   * @return La suma de las heurísticas.
   */
  float utilidad(Tablero tablero){
    return diferencias(tablero) + esquinas(tablero);
  }
}
