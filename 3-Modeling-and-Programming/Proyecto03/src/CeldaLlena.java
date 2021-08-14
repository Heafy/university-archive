import java.awt.Graphics;
import java.awt.Color;

/**
 * Una Celda Llena es una Celda que no es parte de una Pista
 */
public class CeldaLlena extends Celda{
   /**
    * Llena la celda con un fondo negro
    * @param g EL objeto de la clase Graphics
    */
   public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
