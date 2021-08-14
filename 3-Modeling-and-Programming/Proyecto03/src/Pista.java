import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * Representa una Pista, una respuesta en el Crucigrama
 */
public class Pista implements Serializable, Comparable<Pista>{
    private int numero, x,y;
    private String Pista, respuesta;
    private boolean isDown;
    private char[] userAnswer;
    private PistaResuelta PistaResuelta;
    private ArrayList<RegistroResuelto> RegistroResuelto = new ArrayList<RegistroResuelto>();
    private static String usuarioActual;

    /**
     * Constructor para una Pista
     * @param numero EL numero de la pista
     * @param x La coordenada x de la primera letra de la pista
     * @param y La coordenada y de la primera letra de la pista
     * @param Pista La descripcion de la Pista
     * @param respuesta La respuesta de la pista
     */ 
    public Pista(int numero, int x, int y, String Pista, String respuesta){
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.Pista = Pista;
        this.respuesta = respuesta;
        usuarioActual = "";
        userAnswer = new char[respuesta.length()];
    }

    /**
     * Metodo para conocer el estado de la pista
     * @return true Si la pista ha sido resuelta
     */
    public boolean revisaResuelto(){
       return ((new String(userAnswer)).equals(respuesta.toUpperCase()));
    }

    /**
     * Metodo para cambiar la respuesta por la introducida pro el usuario
     * @param x La coordenada x de la primera letra de la pista
     * @param y La coordenada y de la primera letra de la pista
     * @param character El caracter que se ha introducido
     */
    public void  setRespuestaUsuario(int x, int y, char character){
        int position;
        if (isDown) position =  y - this.y;
        else position = x - this.x;
        
        userAnswer[position] = character;
        
        if((revisaResuelto()) && (PistaResuelta == null)){
            PistaResuelta = new PistaResuelta(usuarioActual, new Date());
            actualizaRegistroResuelto();
        }
    }

    /**
     * Agrega los nuevos registros
     */
    public void actualizaRegistroResuelto(){
         if(revisaResuelto()){
            for(RegistroResuelto rr: RegistroResuelto){
                rr.agregaPistaResuelta(this);
            }
        }
    }

    /**
     * Metodo para obtener el caracter que el usuario ha introducido en la posicion dada
     * @param x La coordenada x de la primera letra de la pista
     * @param y La coordenada y de la primera letra de la pista
     * @return character El caracter que se ha introducido
     */
    public char getRespuestaUsuario(int x, int y){
        int position;
        if (isDown) position =  y - this.y;
        else position = x - this.x;
        return userAnswer[position];
    }

    /**
     * Metodo para cambiar el estado de la variable
     * @param isDown EL booleano con el valor
     */
    public void setIsDown(boolean isDown){
        this.isDown = isDown;
    }

    /**
     * Metodo para concoer el estado de la variable
     * @return El valor del booleano
     */
    public boolean getIsDown(){
        return isDown;
    }

    /**
     * Metodo para obtener eL numero de la pista
     * @return EL numero de la pista
     */
    public int getNumero(){
        return numero;
    }

    /**
     * Metodo para obtener la coordenada x
     * @return La coordenada x de la celda
     */
    public int getX(){
        return x;
    }

    /**
     * Metodo para obtener la coordenada y
     * @return La coordenada y de la celda
     */
    public int getY(){
        return y;
    }

    /**
     * Metodo para obtener la longitud de la pista  
     * @return La longitud de la pista
     */
    public int getDimension(){
        return respuesta.length();
    }

    /**
     * Metodo para obtener el tiempo que se tardo en resolver la pista
     * @return El tiempo que se tardo en resolver la pista
     */
    public Date getFecha(){
        if(PistaResuelta != null){
            return PistaResuelta.getTiempo();
        }
        return null;
    }

    /**
     * Agrega un Registro Resuelto a la lista, se agrega cuando la pista es Resuelta
     * Bastante raro
     * @param RegistroResuelto El Registro que sera agregado
     */
    public void agregaRegistroResuelto(RegistroResuelto RegistroResuelto){
        this.RegistroResuelto.add(RegistroResuelto);
        actualizaRegistroResuelto();

    }

    /**
     * Metodo para cambiar el nombre de la persona que resuelve el Crucigrama
     * @param solverName El neuvo nombre
     */
    public static void setUsuarioActual(String usuarioAc){
        usuarioActual = usuarioAc;
    }

    /**
     * Metodo para obtener la longitud de la respuesta
     * @return Una cadena describiendo la respuesta
     */
    public String getLongitudRespuesta(){
        String result = "(";
        int contador = 0;
        for(int i = 0; i < respuesta.length(); i++){
            if(respuesta.charAt(i) == ' '){
                result = result + Integer.toString(contador) + ",";
                contador = 0;
            }
            else if(respuesta.charAt(i) == '-'){
                result = result + Integer.toString(contador) + "-";
                contador = 0;
            }
            else contador++;
        }
        return result + Integer.toString(contador) + ")";
    }

    /**
     * Metodo para ver una Pista en forma de cadena
     * @return Una Pista en una representacion de cadena
     */
    public String toString(){
        return Integer.toString(numero) + ". "  + Pista + " " + getLongitudRespuesta(); 
    }

    /**
     * Metodo para una representacion de la Pista Resuelta en forma de cadena
     * @return Una cadena con la Pista Resuelta
     */
    public String getRespuesta(){
        return Integer.toString(numero) + ". "  + respuesta + " " + getLongitudRespuesta() + PistaResuelta; 
    }

    /**
     * Compara la pista con otra
     * @param c La pista con la que se comparara
     * @return a Un numero para representar la comparacion
     */
    public int compareTo(Pista c){
        if((getFecha() != null) && (c.getFecha() != null)){
            return getFecha().compareTo(c.getFecha());
        }
        else return 0;
    }
}

