import java.io.*;
import javax.swing.JFileChooser;


/**
 * La clase que maneja la entrada y salida de un Crucigrama
 */
public class CrucigramaIO{
    
    /**
     * Lee un Crucigrama de un archivo
     * @return el Crucigrama que leyo
     */
    public static Crucigrama leeCrucigrama(){
        // Para usar una interfaz de seleccion de archivo
        JFileChooser jfc = new JFileChooser();            
        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            try{
                File f = jfc.getSelectedFile();
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Crucigrama result = (Crucigrama) ois.readObject();
                ois.close();
                return result;
            }catch(IOException e){
                System.err.println("Un error de IO");
            }
            catch(ClassNotFoundException e){
                System.err.println("No se encontro la clase");    
            }
        }
        return null;
    }

    /**
     * Escribe un Cricugrama
     * @param Crucigrama EL Crucigrama que va a ser guardado
     */
    public static void guardaCrucigrama(Crucigrama Crucigrama){
        JFileChooser jfc = new JFileChooser();
        if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            try{
                File f = jfc.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(Crucigrama);

                oos.flush();
                oos.close();
            }catch(IOException e){
                System.err.println("Un error de IO"); 
            }
        }
    }
}

