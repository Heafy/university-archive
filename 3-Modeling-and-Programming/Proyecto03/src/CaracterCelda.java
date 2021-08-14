import java.awt.*;
import java.awt.event.*;

/**
 * A Celda que contiene una Pista
 */
public class CaracterCelda extends Celda{
    
    private Pista acrossPista, downPista;
    private final int x, y;
    private boolean esPrimero; //True iSi es el primer caracter de una Pista
    private boolean isFocus, isHighlighted, isDown, defaultDown;
    private char[] character; // Un arreglo para poder imprimir
    private char[] PistaNumber;

    /**
     * Constructor unico
     * Crea la celda y los Listeners
     * @param x La coordenada x de la celda
     * @param y La coordenada x de la celda
     */
    public CaracterCelda(int x, int y){
        super();
        this.x = x;
        this.y = y;
        esPrimero = false;
        isFocus = false;
        isHighlighted = false;
        isDown = false;

        character = new char[1];
        character[0] = ' ';

        //Permite a la celda obtener el 'focus'
        setFocusable(true);
        setRequestFocusEnabled(true);

        addMouseListener(new MouseAdapter(){
            //En caso de que ya tenga el focus
            public void mousePressed(MouseEvent e){
                if(isFocus){ 
                    ((Cuadricula)getParent()).setFocus(CaracterCelda.this.x, CaracterCelda.this.y, !isDown);
                }else{
                    ((Cuadricula)getParent()).setFocus(CaracterCelda.this.x, CaracterCelda.this.y, defaultDown);
                }

            }
});
        
        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                //Todas las letras son mayusculas para evitarnos de problemas
                character[0] = Character.toUpperCase(e.getKeyChar());
                // Por si se agregan caracteres extraños usamos las Huellas digitales de cada caracter
                if (((int) character[0] >= 32) && ((int) character[0] <= 126)) {  
                    ((Cuadricula)getParent()).focusNextCelda();
                    if(acrossPista != null) 
                        acrossPista.setRespuestaUsuario(CaracterCelda.this.x,CaracterCelda.this.y,character[0]);
                    if(downPista != null) 
                        downPista.setRespuestaUsuario(CaracterCelda.this.x,CaracterCelda.this.y,character[0]);
                    repaint();
                }
                // Si el caracter es un retroceso, nos movemos y borramos la celda, para que se sienta mas fluido e programa
                else if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    character[0] = ' ';
                    ((Cuadricula)getParent()).focusPreviousCelda();
            
                    if(acrossPista != null) acrossPista.setRespuestaUsuario(CaracterCelda.this.x,CaracterCelda.this.y,character[0]);
                    if(downPista != null) downPista.setRespuestaUsuario(CaracterCelda.this.x,CaracterCelda.this.y,character[0]);

                    repaint();
                }
                //Si el caracter es borrado, vacia la celda
                else if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    character[0] = ' ';
                    repaint();
                }
            }
        });

        addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent e){
                isFocus = true;
                repaint();
            }
            public void focusLost(FocusEvent e){
                isFocus = false;
                repaint();
            }
        });
    }

    /**
     * Agrega una Pista Horizontal con la Celda
     * @param Pista La Pista a agregar
     */
    public void agregaPistaHorizontal(Pista Pista){
        acrossPista = Pista;
        if ((acrossPista.getX() == x) && (acrossPista.getY() == y)){ 
            esPrimero = true;
            PistaNumber = Integer.toString(Pista.getNumero()).toCharArray();
        }
        calculaAbajo();
        character[0] = Pista.getRespuestaUsuario(x,y); 
    }

   /**
     * Agrega una Pista Vertical con la Celda
     * Hace exactamente lo mismo que el metodo anterior
     * @param Pista La Pista a agregar
     */
    public void agregaPistaVertical(Pista Pista){
        downPista = Pista;
        if ((downPista.getX() == x) && (downPista.getY() == y)){ 
            esPrimero = true;
            PistaNumber = Integer.toString(Pista.getNumero()).toCharArray();
        }
        calculaAbajo();
        character[0] = Pista.getRespuestaUsuario(x,y); 
    }

    /**
     * Metodo auxiliar para definir la orienteacion de una Pista
     * Ayudandose de la primer letra de pista
     */
    private void calculaAbajo(){
        if(downPista != null){
            if((downPista.getX() == x) && (downPista.getY() == y)) defaultDown = true;
        }
        
        if (acrossPista != null){
            if((acrossPista.getX() == x) && (acrossPista.getY() == y)) defaultDown = false;
        }
         
        if (acrossPista == null) defaultDown = true;
        if (downPista == null) defaultDown = false;
           
        isDown = defaultDown;    
    }

    /**
     * Metodo para saber si la Celda tiene el 'focus'
     * @return true Si la celda tiene el 'focus'
     */
    public boolean getIsFocus(){
        return isFocus;
    }

    /**
     * Metodo para cambiar el 'focus' de la celda
     * @param isFocus cambia el 'focus' de la celda
     */
    public void setFocus(boolean isFocus){
        this.isFocus = isFocus;
        if(isFocus) requestFocusInWindow();
    }

    /**
     * Metodo para cambiar la iluminacion de una Celda
     * @isHighlighted el booleano apra saber si esta iluminada
     */
    public void setHighlighted(boolean isHighlighted){
        this.isHighlighted = isHighlighted;
    }

    /**
     * Metodo para obtener la coordenada x de la celda
     * @return La cordenada x de la Celda
     */
    public int getCeldaX(){
        return x;
    }

    /**
     * Metodo para obtener la coordenada y de la celda
     * @return La cordenada y de la Celda
     */
    public int getCeldaY(){
        return y;
    }

    /**
     * @return true if isDown
     */
    public boolean getIsDown(){
        return isDown;
    }

    
    public void setIsDown(boolean isDown){
        this.isDown = isDown;
    }

    /**
     * Metodo para obtener la pista asociada
     * @return La pista actual
     */
    public Pista getPista(){
        if((isDown) && (downPista != null)) 
            return downPista;
        else if((!isDown) && (acrossPista != null)) 
            return acrossPista;
            
        //Cambiamos el estado de la variable   
        isDown = !isDown;

        if((isDown) && (downPista != null)) 
            return downPista;
        else if((!isDown) && (acrossPista != null)) 
            return acrossPista;

        System.out.println("No hay Pista");
        return null;
    }

    /**
     * Pinbta toda la celda
     * @param g EL objeto grafica de la celda
     */
    public void paint(Graphics g){
        if(isHighlighted){
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else{
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        //Dibuja un brode rojo para el 'focus'
        if(isFocus){
            int grosor = 4;
            g.setColor(Color.RED);
            g.fillRect(0,0,getWidth()-0,getHeight()-0);
            g.setColor(Color.YELLOW);
            g.fillRect(grosor + 1, grosor + 1, getWidth() - (2 * grosor) - 1, getHeight() - (2 * grosor) - 1);
        }
        //Dibuja el borde
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth(), 0);
        g.drawLine(0, 0, 0, getHeight());

        //Dibuja el numero de la ṕista
        //Usamos otra fuente diferente a la que se usa por defecto para evitar un par de problemas
        if(esPrimero){
            g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, getHeight()/3));
            if (downPista != null)
                g.drawChars(PistaNumber, 0, PistaNumber.length , 3, g.getFontMetrics().getAscent()); 
            else if (acrossPista != null)
                g.drawChars(PistaNumber, 0, PistaNumber.length,  3, g.getFontMetrics().getAscent());
        }

        //Dibuja el caracter de la celda
        if(character[0] != ' '){
            g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, getHeight()/2));
            //Caso especial para la M, que por razones desconocidas da problemas
            g.drawChars(character, 0, 1, getWidth() - g.getFontMetrics().stringWidth("M") - 5, getHeight() - g.getFontMetrics().getDescent() - 3);
        }
    }
}
