import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Random;

public class TablaCalificaciones extends JPanel{

	/* Tomamos un arreglo para que tenga las calificaciones y sea nuestra
	 * El arreglo se le iran agregando elementos y dibujará las gráfica conforme
	 * reciba los elementos.
	 * Por defecto las calificaciones estan en 0 
	 * El ultimo valor es el promedioTotal*/
	double [] arrCalificaciones = {0,0,0,0,0,0,0};
	double promedioTotal = 0;

	/* Agrega un elemento al arreglo de calificaciones .
	 * @param pos la posicion donde se va a agregar la calificacion.
	 * @param calificacion La calificacion que se va a agregar.*/
	public void agregaCalificacion(int pos, double calificacion){
		arrCalificaciones[pos] = calificacion;
		// Llamamos a repaint para dibujar la gráfica actualizada
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i = 0; i< arrCalificaciones.length; i++)
			promedioTotal += arrCalificaciones[i];
		promedioTotal = promedioTotal/6;

		// Por razones desconocidas al intentar insertar los draw en un ciclo
		// Este los dibuja muy abajo, asi que se dibujan uno por uno.
		g.drawString("Primer grado: " + arrCalificaciones[0], 15, 20);
		g.drawString("Segundo grado: "+ arrCalificaciones[1], 15, 40);
		g.drawString("Tercer grado: " + arrCalificaciones[2], 15, 60);
		g.drawString("Primer grado: " + arrCalificaciones[3], 15, 80);
		g.drawString("Primer grado: " + arrCalificaciones[4], 15, 100);
		g.drawString("Primer grado: " + arrCalificaciones[5], 15, 120);
		String s = String.format("%.2f", arrCalificaciones[6]);
		g.drawString("Promedio total: "+ s, 15, 140);
	}
}