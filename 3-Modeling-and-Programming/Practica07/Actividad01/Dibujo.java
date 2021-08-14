import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.Random;

public class Dibujo extends JPanel {

	//Dibuja una x desde las esquinas del panel
	public void paintComponent(Graphics g) {
		//Llama a paintComponent para asegurar que el panel se muestre correctamente
		super.paintComponent( g );
		Random aleatorios = new Random();

		// Representan los puntos en R^2, con sus respectivos ejes
		int anchura = getWidth();
		int altura = getHeight();
		int red;
		int green;
		int blue;
		
		// Dibuja un asterisco, la base para la figura
		// Diagonal \
		g.drawLine( 0, 0, anchura, altura);
		// Diafonal /
		g.drawLine( 0, altura, anchura, 0);
		// Horizontal
		g.drawLine( 0, altura/2, anchura, altura/2);
		// Vertical
		g.drawLine( anchura/2, 0, anchura/2, altura);

		int num1 = 0;
		int num2 = 1;
		while( num1<=14 & num2<=15 ) {
			red=aleatorios.nextInt(256);
			green=aleatorios.nextInt(256);
			blue=aleatorios.nextInt(256);
			Color col = new Color(red,green,blue);
			g.setColor(col);
			// Cuadrante superior izquierdo
			g.drawLine( anchura/2, (altura*num1/30), 
						(-anchura*num2/30)+(anchura/2), altura/2 );
			// Cuadrante inferior izquierdo
			g.drawLine( anchura/2, (altura*num1/30)+(altura/2), 
						(anchura*num2/30), altura/2);
			// Cuadrante superior derecho
			g.drawLine( anchura/2, (altura*num1/30), 
						(anchura*num2/30)+(anchura/2), altura/2 );
			// Cuadrante inferior derecho
			//g.drawLine( anchura/2, (altura*num1/30)+(altura/2), 
			//		(anchura*num2/30)+(anchura/2), altura/2 );

			num1 +=1;
			num2+=1;
		}
	}

} 
	
