import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.Random;

public class GraficaBarras extends JPanel {

	/* Tomamos un arreglo para que tenga las calificaciones y sea nuestra
	 * El arreglo se le iran agregando elementos y dibujará las gráfica conforme
	 * reciba los elementos.
	 * Por defecto las calificaciones estan en 0 */
	int [] arrCalificaciones = {0,0,0,0,0,0};

	/* Agrega un elemento al arreglo de calificaciones .
	 * @param pos la posicion donde se va a agregar la calificacion.
	 * @param calificacion La calificacion que se va a agregar.*/
	public void agregaCalificacion(int pos, int calificacion){
		arrCalificaciones[pos] = calificacion;
		// Llamamos a repaint para dibujar la gráfica actualizada
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Random aleatorios = new Random();
		/* Obtenemos la anchura y la altura */
		int anchura = getWidth();
		int altura = getHeight();
		
		/* Variables auxiliares para cada fraccion de la barra */
		int fracAnchura = anchura/6;
		int fracAltura = altura/10;
		/* El ancho de cualquier barra */
		int anchoBarra = (anchura/6)*1;
		int red, green, blue;

		/* Itera para dibujar las lineas verticales
		 * 6 lineas representan los 6 grupos
		 */
		for (int i = 1; i <= 6; i++)
			g.drawLine(fracAnchura*i, altura, fracAnchura*i,0);

		/* Itera para dibujar las lineas horizontales
		 * 10 lineas representan el promedio [1-10]
		 */
		for (int i = 1; i <= 10;i++)
			g.drawLine(anchura, fracAltura*i, 0, fracAltura*i);

		for (int i = 0; i < arrCalificaciones.length; i++){
			/* Se crean los colores RGB aleatoriamente. */
			red = aleatorios.nextInt(256);
			green = aleatorios.nextInt(256);
			blue = aleatorios.nextInt(256);

			Color col = new Color(red,green,blue);
			g.setColor(col);

			/* Por razones desconocidas (tal vez el diablo) el entero
	 		* no se dibuja correctamente, dibuja 10-entero
	 		* restando se corrig e ese error */ 
			int aux = 10 - arrCalificaciones[i];
			/* Se va creando una a una cada barra */
			g.fillRect(fracAnchura*i, fracAltura*aux, anchoBarra, fracAnchura*(10-aux));
		}

	}

}  