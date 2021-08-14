import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.util.Arrays;

public class Actividad2{
	Random random = new Random();

	/* El resultado de cada operacion.*/
	int resultado = 0;

	public int getResultadoInt(){
		return resultado;
	}

	public void resetResultado(){
		resultado = 0;
	}

	/**
	* Metodo que se encarga de realiza una operacion
	* y las guarda en forma de cadena, a lo mucho
	* realiza operaciones de 4 sumandos */
	private String makeOps(){
		String suma = "";
		int operando = 0;
		int sumandos = generaAleatorios(2,4);
		for(int i = 0; i < sumandos; i++){
			operando = generaAleatorios(2,99);
			suma +=  operando + " + ";
			resultado += operando;
		}
		// Se arregla el hecho de que se agrega un '+'
		// Aunque no haya otro operando a sumar
		suma = suma.substring(0, suma.length()-3);
		suma = suma + " = ";
		return suma;
	}

	/**
	 * Metodo para generar un número aleatorio entre
	 * los dos parémetros que recibe, ambos incluyentes
	 * (Para que las sumas siempre tengan al menos 2 sumandos
	 * y siempre haya un sumando mayor a 0)
	 */
	private int generaAleatorios(int min, int max){
		return random.nextInt((max - min) + 1) + min;
	}

	public static void main(String[] args) {
		Actividad2 objetoAux = new Actividad2();
		JFrame frame = new JFrame("Actividad 2");
		/* El arreglo con los resultados reales. */
		int [] resultados = {0,0,0,0,0};
		/* El arreglo que contendrá los resultados introducidos por el usuario. */
		int [] resultadosUsuario = {0,0,0,0,0};

		JPanel panel = new JPanel(new GridLayout(6,6,10,10));

		/* Cada etiqueta junto con su JTextArea de resultado
		 * No puede hacerse ciclico ya que es necesario mantener
		 * en memoria cada JTextArea, para poder hacer un update.*/
		JLabel etiqueta0 = new JLabel(objetoAux.makeOps());
		JTextArea res0 =new JTextArea("0", 1, 6);
		resultados[0] = objetoAux.getResultadoInt();
		objetoAux.resetResultado();
		JLabel etiqueta1 = new JLabel(objetoAux.makeOps());
		JTextArea res1 =new JTextArea("0", 1, 6);
		resultados[1] = objetoAux.getResultadoInt();
		objetoAux.resetResultado();
		JLabel etiqueta2 = new JLabel(objetoAux.makeOps());
		JTextArea res2 =new JTextArea("0", 1, 6);
		resultados[2] = objetoAux.getResultadoInt();
		objetoAux.resetResultado();
		JLabel etiqueta3 = new JLabel(objetoAux.makeOps());
		JTextArea res3 =new JTextArea("0", 1, 6);
		resultados[3] = objetoAux.getResultadoInt();
		objetoAux.resetResultado(); 
		JLabel etiqueta4 = new JLabel(objetoAux.makeOps());
		JTextArea res4 =new JTextArea("0", 1, 6); 
		resultados[4] = objetoAux.getResultadoInt();
		objetoAux.resetResultado();

		panel.add(etiqueta0);
		panel.add(res0);
		panel.add(etiqueta1);
		panel.add(res1);
		panel.add(etiqueta2);
		panel.add(res2);
		panel.add(etiqueta3);
		panel.add(res3);
		panel.add(etiqueta4);
		panel.add(res4);

		JButton b1 = new JButton("Calificación");
		panel.add(b1);
		b1.addActionListener( new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		/* La calificacion del usuario.*/
				int calificacion = 0;
				Icon meme = new ImageIcon();
				JLabel etiquetaCalificacion = new JLabel();
				JFrame frame2 = new JFrame();

				/* Actualizamos la informacion de cada JTextArea
				 * y lo agregamos al segundo arreglo. */
	        	try{
	        		res0.update(res0.getGraphics());
		        	resultadosUsuario[0] = Integer.parseInt(res0.getText());
		        	res1.update(res1.getGraphics());
		        	resultadosUsuario[1] = Integer.parseInt(res1.getText());
		        	res2.update(res2.getGraphics());
		        	resultadosUsuario[2] = Integer.parseInt(res2.getText());
		        	res3.update(res3.getGraphics());
		        	resultadosUsuario[3] = Integer.parseInt(res3.getText());
		        	res4.update(res4.getGraphics());
		        	resultadosUsuario[4] = Integer.parseInt(res4.getText());
		        } catch(NumberFormatException nfe){
		        	JOptionPane.showMessageDialog(frame, "Formato Invalido, introduce solo números.", "Error", JOptionPane.WARNING_MESSAGE);
		        	System.exit(1);
		        }

	        	System.out.println("Resultados introducidos:\n" + Arrays.toString(resultadosUsuario));
	        	// Verificamos la calificacion
	        	for(int i = 0; i < resultados.length; i++){
					if(resultados[i] == resultadosUsuario[i])
						calificacion += 2;
				} // For
				/* Un Switch para llenar las imagenes de las calificaciones */
				switch(calificacion){
					case 0:
						meme = new ImageIcon(getClass().getResource("0.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>Eres un genio como para fallar todas.<br>Calificación: 0</html>");
						break;
					case 2:
						meme = new ImageIcon(getClass().getResource("2.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>Sólo tienes opción a dos sabores de helado <br>¿Porque? Por tu 2 en tu examen.<br>Calificación: 2</html>");
						break;
					case 4:
						meme = new ImageIcon(getClass().getResource("4.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>¿Cuántas patas tiene un gato? <br>Exacto, las mismas que tu calificación.<br>Calificación: 4</html>");
						break;
					case 6:
						meme = new ImageIcon(getClass().getResource("6.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>Si te sirve de consuelo pasaste, <br>pero no es suficiente.<br>Calificación: 6</html>");
						break;
					case 8:
						meme = new ImageIcon(getClass().getResource("8.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>Ocho Pinocho.<br>Calificación: 8</html>");
						break;
					case 10:
						meme = new ImageIcon(getClass().getResource("10.png"));
						etiquetaCalificacion = new JLabel(meme, JLabel.CENTER);
						etiquetaCalificacion.setText("<html>Estoy viendo al próximo Albert Einstein.<br>Calificación: 10</html>"); 
						break;
					default:
						JOptionPane.showMessageDialog(frame, "Esto nunca debería de pasar.\nAlgo salió mal con tu calificación", "Calificación", JOptionPane.WARNING_MESSAGE);
				} // Switch
				etiquetaCalificacion.setIcon(meme);
				etiquetaCalificacion.setHorizontalTextPosition(JLabel.CENTER);
				etiquetaCalificacion.setVerticalTextPosition(JLabel.TOP);
				
				frame2.add(etiquetaCalificacion);
				frame2.setSize(300,300);
				frame2.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				frame2.add(etiquetaCalificacion);
				frame2.setVisible(true);
	        	
    		} // Action Performed
		});//Action Listener
		System.out.println("Resultados:\n" + Arrays.toString(resultados));

		frame.add(panel);
	    frame.pack();
	    frame.setSize(350,350);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}