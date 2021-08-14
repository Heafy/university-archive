import javax.swing.*;
import javax.swing.event.*;

public class UsoCrucigrama{

	/**
     * EL metodo main, crea la ventana principal
     */
    public static void main(String[] args){
        final CrucigramaFrame cf = new CrucigramaFrame();
        //Un truco, asegura la iniciacion con la zona horaria
        SwingUtilities.invokeLater(
            new Runnable() {
                // No se si sea necesario que haya un run
                public void run(){
                    cf.inicia();
                }
            }
        );
    }
}