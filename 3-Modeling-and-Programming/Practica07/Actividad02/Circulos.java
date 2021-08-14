import java.awt.Graphics;
import javax.swing.JPanel;

public class Circulos extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pos = 10;
		for(int i = 0; i < 10; i++){
			g.drawOval(pos, pos, pos+10, pos+10);
			pos += 10;
		}
	}
}
