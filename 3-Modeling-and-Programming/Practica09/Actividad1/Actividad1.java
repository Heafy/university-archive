import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Actividad1{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		// El promedioArreglo de tamaño 6 para guardar las calificaciones
		double promedioArr [] = new double[6];
		double promedioTotal = 0;
		for (int i = 0; i < promedioArr.length; i++){
			try{
				// Como empezamos desde cero le agregamos uno para que el usuario sepa que año agregar
				int aux = i+1;
				String promedio = JOptionPane.showInputDialog("Escribe el promedio de los alumnos de " + aux + "° año");
				// Cast String -> Double
				Double d = Double.parseDouble(promedio);
				promedioArr[i] = d;
				promedioTotal += d;
				System.out.println("Promedio del "+ aux + "° año: " + d);
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(frame, "Formato invalido", "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
		// Se calcula el promedio total sobre lo que ya se ha sumado
		promedioTotal = promedioTotal/6;
		System.out.printf("Promedio Total %.2f", promedioTotal);

		//Crea un gb que contiene a GraficaBarras
		GraficaBarras gb = new GraficaBarras();
		TablaCalificaciones tc = new TablaCalificaciones();

		// Agregamos las calificaciones con el metodo auxiliar de la clase GraficaBarras
		for(int i = 0; i < promedioArr.length; i++){
			// Los valores son redondeados porque necesitamos int para dibujar
			// TODO Crear int sin perdida de precision
			Double d = promedioArr[i];
			int aux = d.intValue();
			gb.agregaCalificacion(i, aux);
			tc.agregaCalificacion(i, d);
		}
		// Se agrega el promedio para que se muestre en el JFrame
		tc.agregaCalificacion(6, promedioTotal);

		//Crea los JFrames
		JFrame aplicacion = new JFrame();
		JFrame aplicacion2 = new JFrame();

		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		aplicacion.add(gb);
		aplicacion.setSize(500,650);
		aplicacion.setVisible(true);

		aplicacion2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		aplicacion2.add(tc);
		aplicacion2.setSize(200,200);
		// Establece la posicion de la Tabla de Calificaciones
		aplicacion2.setLocation(520,0);
		aplicacion2.setVisible(true);

	}

}
