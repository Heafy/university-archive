/**
 * Clase Coordenada
 * Una coordenada es una tupla de posiciones representada con x,y.
 */
class Coordenada{
  /* Coordenada x. Representa renglones. */
  int x;
  /* Coordenada y. Representa filas. */
  int y;
   
 /**
  * Constructor unico.
  */
  public Coordenada(int x , int y){
    this.x = x;
    this.y = y;
   }
   
   /**
    * Metodo para obtener la coordenada X.
    * @return La coordenada x.
    */
   public int getX(){
     return x;
   }
   
   /**
    * Metodo para obtener la coordenada y.
    * @return La coordenada y.
    */
   public int getY(){
    return y; 
   }
   
   /**
    * Método para comprar dos objetos, en este caso Coordenadas.
    * Si este método no está el agente no funciona.
    * @return  true si la Coordenada es igual a obj.
    *          false en otro caso.
    */
   @Override
   public boolean equals(Object obj){
     Coordenada coordenada = (Coordenada)obj;
     return this.getX() == coordenada.getX() && this.getY() == coordenada.getY();
   }
}
