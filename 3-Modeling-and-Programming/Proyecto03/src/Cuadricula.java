import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Cuadricula representa una cuadricula en el Crucigrama
 */
public class Cuadricula extends JPanel{

    private int anchura, altura;
    private Crucigrama Crucigrama;
    private CaracterCelda focus;
    private ArrayList<JList> lista = new ArrayList<JList>();
    private boolean actualAbajo;
    private Celda[][] Celda;

    /**
     * Constructor para una cuadricula
     * @param anchura La anchura de la cuadricula
     * @param altura La altura de la cuadricula
     * @param Crucigrama EL crucigrama asociado
     */
    public Cuadricula(int anchura, int altura, Crucigrama Crucigrama){
        super();        
        this.anchura = anchura;
        this.altura = altura;
        setSize(anchura, altura);
        setPreferredSize(new Dimension(anchura, altura));
        
        this.Crucigrama = Crucigrama;
        
        // Crea celdas en el panel
        Celda = new Celda[Crucigrama.getDimension()][Crucigrama.getDimension()];
        setLayout(new GridLayout(Crucigrama.getDimension(), Crucigrama.getDimension() ));

        // Llena todas las celdas
        for(int i = 0; i < Crucigrama.getDimension(); i++){
            for (int j = 0; j < Crucigrama.getDimension(); j++){
                Celda[i][j] =  new CeldaLlena();
            }
        }

        // Remplaza las celdas con las celdas que contienen un caracter
        for(Pista c: Crucigrama.getHorizontalPistas()){
            for(int i = 0; i < c.getDimension(); i++){
                // Para saber si la celda tiene un Caracter
                if (Celda[c.getX() + i][c.getY()] instanceof CaracterCelda){
                    ((CaracterCelda) Celda[c.getX() + i][c.getY()] ).agregaPistaHorizontal(c);
                }
                else {
                    Celda[c.getX() + i][c.getY()] = new CaracterCelda(c.getX() + i, c.getY());
                    ((CaracterCelda)Celda[c.getX() + i][c.getY()] ).agregaPistaHorizontal(c);
                }
            }
        }
        for(Pista c: Crucigrama.getVerticalPistas()){
            for(int i = 0; i < c.getDimension(); i++){
                // Para saber si la celda tiene un Caracter
                if (Celda[c.getX()][c.getY() + i] instanceof CaracterCelda){
                    ((CaracterCelda)Celda[c.getX()][c.getY() + i]).agregaPistaVertical(c);
                }
                else{ 
                    Celda[c.getX()][c.getY() + i] = new CaracterCelda( c.getX(), c.getY() + i);
                    ((CaracterCelda)Celda[c.getX()][c.getY() + i]).agregaPistaVertical(c);
                }
            }
        }
        
        //Argrega las celdas a la cuadricula
        for(int i = 0; i < Crucigrama.getDimension(); i++){
            for (int j = 0; j < Crucigrama.getDimension(); j++){
                add(Celda[j][i]);
            }
        }
          
    }

    /**
     * Maneja una Celda resaltada asociada a una Pista
     * @param Pista La Pista a ser resaltada
     * @param isHighlighted Para indicar el estado de la psita
     */
    public void setHighlightedPista(Pista Pista, boolean isHighlighted){
        for(int i = 0; i < Pista.getDimension(); i++){
            if(Pista.getIsDown()) ((CaracterCelda)Celda[Pista.getX()][Pista.getY() + i]).setHighlighted(isHighlighted);
            else ((CaracterCelda)Celda[Pista.getX() + i][Pista.getY()]).setHighlighted(isHighlighted);
        }
    }

    /**
     * Resalta una celda y su pista
     * @param x La coordenada x de la celda
     * @param y La coordenada y de la celda
     * @param isDown true Si la pista esta debado
     */
    public void setFocus(int x, int y, boolean isDown){
        //Revisa que la Celda contenga un caracter
        if(Celda[x][y] instanceof CaracterCelda){
            CaracterCelda sender = ((CaracterCelda)Celda[x][y]);
            
            if (focus != null){
                setHighlightedPista(focus.getPista(), false);
                if(focus != sender){
                    focus.setFocus(false);
                }
            }
            
            // Da el 'focus' a le celda
            sender.setFocus(true);
            sender.setIsDown(isDown);
            setHighlightedPista(sender.getPista(), true);
            focus = sender;
            repaint();

            actualAbajo = isDown;

            for(JList l: lista){
                l.clearSelection(); 
                l.setSelectedValue(sender.getPista(), true);
            }

        }else System.err.println("No es una Celda con caracter");

    } 

    /**
     * Da el 'focus' a la siguiente celda en una Pista
     */
    public void focusNextCelda(){
        if(focus != null){
            int x = focus.getCeldaX();
            int y = focus.getCeldaY();

            if(actualAbajo) y++;
            else x++;
       
            int Dimension = Crucigrama.getDimension();

            if((x < Dimension) && (y < Dimension)){        
                if(Celda[x][y] instanceof CaracterCelda) setFocus(x,y,actualAbajo);        
            }
        }
    }
 
    /**
     * Da el 'focus' a la anterior celda en una Pista
     * Parecido al metodo anterior, pero al reves
     */
    public void focusPreviousCelda(){
        if(focus != null){
            int x = focus.getCeldaX();
            int y = focus.getCeldaY();

            if(actualAbajo) y--;
            else x--;
       
            int Dimension = Crucigrama.getDimension();

            if((x >= 0) && (y >= 0)){        
                if(Celda[x][y] instanceof CaracterCelda) setFocus(x,y,actualAbajo);        
            }
        }
    }      

    /**
     * Metodo para obtener el crucigrama
     * @return El crucigrama asociado a la Cuadricula
     */
    public Crucigrama getCrucigrama(){
        return Crucigrama;
    }

    /**
     * Agrega una Lista
     * @param lista La Lista a agregar
     */
    public void addList(JList lista){
        this.lista.add(lista);
    }

    /**
     * Dibuja todo lo asociado a la cuadricula
     * @param g EL objeto con el que se dibujara
     */
    public void paint(Graphics g){
        //Paints the JPanel stuff
        super.paint(g);
        g.setColor(Color.BLACK);
        // Dibuja lines entre los lados del panel, para arreglar un pequeño error
        g.drawLine(getWidth() - 2, 2, getWidth() - 2, getHeight() - 2);
        g.drawLine(2, getHeight() - 2, getWidth() - 2, getHeight() -2);
    }
}
