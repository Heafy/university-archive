import java.awt.Window;
import java.awt.event.*;

public class BasicWindowMonitor extends WindowAdapter{
        @Override
	public void windowClosing(WindowEvent e) {
	    Window w = e.getWindow();
	    w.setVisible(false);
	    w.dispose();
	    System.exit(0);
	  }
}
