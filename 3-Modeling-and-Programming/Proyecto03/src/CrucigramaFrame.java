import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * La ventana principal que contiene al Crucigrama
 */
public class CrucigramaFrame extends JFrame{

      //Swing Components 
    private JLabel NombreLabel, nombreEtiqueta, etiquetaHorizontal, etiquetaVertical, etiquetaResueltos;
    private JList listaHorizontal, listaVertical;
    private RegistroResuelto RegistroResuelto;
    private JScrollPane listaHorizontalPane, listaVerticalPane, RegistroResueltoPane;
    private JButton loadButton, saveButton;
    private JCheckBox cajaResueltos;
    private JPanel[] panel;
    private JTextField valorCampo;
    private Cuadricula cuadricula;

    /**
     * Constructor unico para el Crucigrama
     */
    public CrucigramaFrame(){
        //Colocamos el nombre del Crucigrama
        super("Crucigrama de Jorge Martinez");
    }

   /**
    * Inicializa la ventana, crea los componenentes necesarios
    * (Para evitar llamar a todos uno por uno)
    */
    public void inicia(){
        setSize(1100, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        //Crea los componentes
        creaCuadricula();
        creaEtiquetas();
        creaListas();
        creaRegistroResuelto();
        creaBotones();
        creaCaja();
        creaTextFields();
        creaPanel();
        iniciaCuadricula();
        
        repaint();
        setVisible(true);
        // Evitamos cambiar el tamaño, para que no pasen cosas raras
        setResizable(false);

        // Mantiene el 'focus' en los campos
        valorCampo.requestFocus();
    }
   
    /**
     * Inicializa la cuadricula
     * Y crea los demas componentes   
     * DefaultListModel nos ayuda a no perder la precision en las Listas 
     * Usamos SupressWarnings para eliminar las advertencias
     */
    @SuppressWarnings("unchecked")
    private void iniciaCuadricula(){

        ((DefaultListModel)listaHorizontal.getModel()).removeAllElements();
        ((DefaultListModel)listaVertical.getModel()).removeAllElements();

        //Agrega las pistas a las listas
        for(Pista c: cuadricula.getCrucigrama().getHorizontalPistas()){
            ((DefaultListModel)listaHorizontal.getModel()).addElement(c);
        }
        for(Pista c: cuadricula.getCrucigrama().getVerticalPistas()){
            ((DefaultListModel)listaVertical.getModel()).addElement(c);
        }
        cuadricula.addList(listaHorizontal);
        cuadricula.addList(listaVertical);

        //Agrega las pistas resueltas
        RegistroResuelto.eliminaPistasResueltas();
        for(Pista Pista: cuadricula.getCrucigrama().getPistas()){
            Pista.agregaRegistroResuelto(RegistroResuelto);
        }
    }

    /**
     * Creates las etiquetas que usaremos en la ventana
     */
    private void creaEtiquetas(){
        NombreLabel = new JLabel(cuadricula.getCrucigrama().getNombre());
        nombreEtiqueta = new JLabel("Nombre: ");
        etiquetaHorizontal = new JLabel("Horizontales");
        etiquetaVertical = new JLabel("Verticales");
        etiquetaResueltos = new JLabel("Resueltas");
       }
    
    /**
     * Crea las listas que contienen las Pistas
     */
    /* Usamos un SupressWarnings para eliminar las advertencias. */
    @SuppressWarnings("unchecked")
    private void creaListas(){
        listaHorizontal = new JList(new DefaultListModel());
        listaVertical = new JList(new DefaultListModel());

        //Permite hacer scroll en las Listas de Pistas
        listaHorizontalPane = new JScrollPane(listaHorizontal);
        listaVerticalPane = new JScrollPane(listaVertical);
    
        listaHorizontalPane.setPreferredSize(new Dimension(300, 265));
        listaVerticalPane.setPreferredSize(new Dimension(300, 265));

        listaHorizontal.addMouseListener(new ListaListener(listaHorizontal, false));
        listaVertical.addMouseListener(new ListaListener(listaVertical, true));
    }

    /**
     * Un Listener para las Listas
     * Permite conectas las Listas con las pistas, para que sea mas fluido
     */
    private class ListaListener extends  MouseAdapter{
        private JList lista;
        private boolean isDown;
        
        /**
         * Constructor para ListaListener
         * @param lista La lista para el Listener
         * @param isDown indica el estado de la lista
         */
        public ListaListener(JList lista, boolean isDown){
            super();
            this.lista = lista;
            this.isDown = isDown;
        }
        
        /**
         * La accion que dara con un click en la listaa
         * @param e El evento del raton
         */
        public void mouseClicked(MouseEvent e){
            int indice = lista.getSelectedIndex();
                    if (indice >= 0){
                        ListModel listaModel = lista.getModel();
                        Pista Pista = (Pista) listaModel.getElementAt(indice);
                        int x = Pista.getX();
                        int y = Pista.getY();
            
                        cuadricula.setFocus(x,y, isDown);
                    }
        }
    }

    /**
     * Crea el Registro Resuelto
     */
    private void creaRegistroResuelto(){
        RegistroResuelto = new RegistroResuelto();
        RegistroResueltoPane = new JScrollPane(RegistroResuelto);
        RegistroResuelto.setEditable(false);
        RegistroResueltoPane.setPreferredSize(new Dimension(600, 140));
        //Esconde el registro resuelto
        RegistroResuelto.setVisible(false);
    }

    /**
     * Crea los bototnes y sus Listeners
     */
    private void creaBotones(){
        loadButton = new JButton("Carga");
        loadButton.addActionListener(new ActionListener(){
            //Carga el crucigrama de un archivo
            public void actionPerformed(ActionEvent e){
                Crucigrama cw =  CrucigramaIO.leeCrucigrama();
                if (cw != null){              
                    //Agrega el nuevo Crucigrama
                    panel[3].remove(cuadricula);
                    cuadricula = new Cuadricula(400,400, cw);
                    panel[3].add(cuadricula, BorderLayout.CENTER);

                    NombreLabel = new JLabel(cuadricula.getCrucigrama().getNombre());

                    //Pinta la nueva cuadricula 
                    repaint();
                    setVisible(true); 

                    //Cambia el nombre del usuario
                    valorCampo.setText(cw.getUsername());
                    Pista.setUsuarioActual(cw.getUsername());

                    //Llamamos de nuevo al metodo para iniciar todo lo demás
                    iniciaCuadricula();               
                }
            }
        });

        saveButton = new JButton("Guarda");
        saveButton.addActionListener(new ActionListener(){
            //Lee el crucigrama de un archivo
            public void actionPerformed(ActionEvent e){
                CrucigramaIO.guardaCrucigrama(cuadricula.getCrucigrama());
            }
        });
    }

    /**
     * Crea los cuadros para activar o desactivar las respuestas
     */
    private void creaCaja(){
        cajaResueltos = new JCheckBox("Ocultar preguntas resueltas");
        cajaResueltos.addChangeListener(new ChangeListener(){
            //Muestra y esconde el Registro Resulto
            public void stateChanged(ChangeEvent e){
                if ( ((JCheckBox)e.getSource()).isSelected() ){
                    RegistroResuelto.setVisible(false);
                }
                else RegistroResuelto.setVisible(true);
            }
        });
    }

    /**
     * Crea los campos de texto
     */
    private void creaTextFields(){
        valorCampo = new JTextField(10);
        valorCampo.addFocusListener(new FocusAdapter(){
            public void focusLost(FocusEvent e){
                 String username = valorCampo.getText();
                 cuadricula.getCrucigrama().setUsername(username);
            }
        });
    }

    /**
     * Crea la cuadricula usando la clase CrucigramaEjemplo
     */
    private void creaCuadricula(){
        cuadricula = new Cuadricula(400, 400, (new CrucigramaEjemplo()).getPuzzle());
    }

    /**
     * Crea todos los paneles neesarios
     */
    private void creaPanel(){
        panel = new JPanel[10];
        for(int i = 0; i < 10; i++){
            panel[i] = new JPanel();
        }

        panel[1].setLayout(new BorderLayout());
        panel[3].setLayout(new BorderLayout());
        panel[2].setLayout(new BorderLayout());
        panel[7].setLayout(new BorderLayout());
        panel[8].setLayout(new BorderLayout());
        panel[9].setLayout(new BorderLayout());
     

        //Usamos una estructura 'especial' para repartir los elementos
        panel[0].add(panel[1]); // Izquierda
        panel[0].add(panel[2]); // Derecha
        panel[1].add(panel[3], BorderLayout.NORTH); // Cuadricula
        panel[1].add(panel[4], BorderLayout.CENTER); // Nombre
        panel[1].add(panel[5], BorderLayout.SOUTH); // Carga
        panel[2].add(panel[6], BorderLayout.NORTH);
        panel[2].add(panel[7], BorderLayout.SOUTH); // Resuelto
        panel[6].add(panel[8]); // Horizontales
        panel[6].add(panel[9]); // Verticales

       
        //Agrega todo lo necesario a los paneles
        panel[3].add(NombreLabel, BorderLayout.NORTH);
        panel[3].add(cuadricula, BorderLayout.CENTER);
        panel[4].add(nombreEtiqueta);
        panel[4].add(valorCampo);
        panel[5].add(loadButton);
        panel[5].add(saveButton);
        panel[7].add(etiquetaResueltos, BorderLayout.NORTH);
        panel[7].add(RegistroResueltoPane, BorderLayout.CENTER);
        panel[7].add(cajaResueltos, BorderLayout.SOUTH);
        panel[8].add(etiquetaHorizontal, BorderLayout.NORTH);
        panel[8].add(listaHorizontalPane, BorderLayout.CENTER) ;
        panel[9].add(etiquetaVertical, BorderLayout.NORTH);
        panel[9].add(listaVerticalPane, BorderLayout.CENTER);

        //Agrega el panel principal a la ventana
        getContentPane().add(panel[0]);
    }
}
