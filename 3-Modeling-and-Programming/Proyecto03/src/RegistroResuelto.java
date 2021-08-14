import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase para guardar el registro de las pistas resueltas
 * La clase empezó siendo una clase auxilar
 * Pero es util que el usuario sepa cuantas ha resuelto
 */
public class RegistroResuelto extends JTextArea{
        
    ArrayList<Pista> pistaResuelta = new ArrayList<Pista>();

    /**
     * Metodo para agregar una Pista Resuelta y la muestra en pantalla
     * @param Pista La Pista añadida a la Lista
     */
    public void agregaPistaResuelta(Pista Pista){
        if(pistaResuelta.contains(Pista) == false){
            pistaResuelta.add(Pista);
            Collections.sort(pistaResuelta);
            setText("");
            for(Pista c : pistaResuelta){
                append(c.getRespuesta()   + "\n");
            }
        }
    }
    
    /**
     * Metodo para reiniciar el registro
     */
    public void eliminaPistasResueltas(){
        pistaResuelta = new ArrayList<Pista>();
        setText("");
    }

}
