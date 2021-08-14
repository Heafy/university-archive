
import javax.swing.JFrame;

public class Actividad1{

	public static void main(String[] args) {

		//Crea un panel que contiene nuestro dibujo
		Dibujo panel = new Dibujo();

		//Crea un nuevo marco 
		JFrame aplicacion = new JFrame();

		//Establece el marco para salir cuando se cierre
		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		aplicacion.add(panel);		//agrega el panel al marco
		aplicacion.setSize(500,700);	//establece tamaño del marco
		aplicacion.setVisible(true);

	}

}
