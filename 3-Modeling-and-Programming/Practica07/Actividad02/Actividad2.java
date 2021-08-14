import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Clase para la Actividad 2 de la Práctica 07
 * @author: Jorge Martinez Flores
 */
public class Actividad2{

	public static void main(String[] args) {
		int a = 0;
		JFrame ventana = new JFrame();
		JFrame frame = new JFrame();
		String valor = JOptionPane.showInputDialog("Escribe 1 para trazar rectángulos\nEscribe 2 para trazar círculos");
		try{	
			a = Integer.parseInt(valor);
		} catch(NumberFormatException n){ // Se lanza en caso de que el valor no sea un int
			JOptionPane.showMessageDialog(frame,"Formato invalido", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		// Imprime rectangulos
		if(a == 1){
			Rectangulos rectangulos = new Rectangulos();
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.add(rectangulos);
			ventana.setSize(300,300);
			ventana.setVisible(true);
		}
		// Imprime circulos
		else if(a == 2){
			Circulos circulos = new Circulos();
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ventana.add(circulos);
			ventana.setSize(300,300);
			ventana.setVisible(true);
		}
		// Manda un mensaje de error
		else{
			JOptionPane.showMessageDialog(frame,"Opción invalida.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
}