import java.awt.Graphics;
import javax.swing.JPanel;

public class Rectangulos extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pos = 10;
		for(int i = 0; i < 10; i++){
			g.drawRect(pos, pos, pos+10, pos+10);
			pos += 10;
		}
	}
}
