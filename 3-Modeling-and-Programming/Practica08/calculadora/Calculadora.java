import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/** Clase Calculadora */
class Calculadora extends JFrame {
    
    /* Campo de texto para mostrar resultados e introducir datos */
    private JTextField display;
    
    /* Variables para el estado de la Calculadora */
    private boolean numeroInicio = true;
    private String operadorAnterior  = "=";
    private CalculadoraInterna calcInterna = new CalculadoraInterna();
    
    public static void main(String[] args) {
        // Crea la Calculadora
        Calculadora window = new Calculadora();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    /* Metodo constructor unico */
    public Calculadora() {
        // Crea los atributos para el campo de texto
        display = new JTextField("0", 12);
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Los atributos para el boton 'C'
        JButton botonC = new JButton("C");
        botonC.addActionListener(new BotonC());
         
        // Un ActionListener para los botones numericos
        ActionListener numListener = new NumListener();
        
        // Crea los botones en un ciclo a partir de una cadena
        String numBotones = "7894561230  ";
        JPanel botonPanel = new JPanel();
        botonPanel.setLayout(new GridLayout(5, 3, 2, 2));
        for (int i = 0; i < numBotones.length(); i++) {
            String aux = numBotones.substring(i, i+1);
            JButton b = new JButton(aux);
            if (aux.equals(" ")) {
                // Pone un boton vacio en esa posicion
                // El boton vacio y los espacios existen
                // Por que de otra manera, quedan solo en
                // 2 columnas los numeros
                b.setEnabled(false);
            } else {
                // Pone un boton valido
                b.addActionListener(numListener);
            }
            botonPanel.add(b);
        }
        
        // Un ActionListener para los operadores
        ActionListener opListener = new OpListener();
        
        
        // Crea un panel y un layout con los operadores
        // Crea los botones en un ciclo
        JPanel opPanel = new JPanel();
        opPanel.setLayout(new GridLayout(5, 1, 2, 2));
        String[] opOrder = {"+", "-", "*", "/", "="};
        for (int i = 0; i < opOrder.length; i++) {
            JButton b = new JButton(opOrder[i]);
            b.addActionListener(opListener);
            opPanel.add(b);
        }
        
        // Agrega el boton C
        JPanel clearPanel = new JPanel();
        clearPanel.add(botonC);
        
        JPanel contenido = new JPanel();
        contenido.setLayout(new BorderLayout(5, 5));
        contenido.add(display, BorderLayout.NORTH );
        contenido.add(botonPanel   , BorderLayout.CENTER);
        contenido.add(opPanel       , BorderLayout.EAST  );
        contenido.add(clearPanel    , BorderLayout.SOUTH );
        
        // Le pone bordes bonitos, para que no esten los botones pegados al marco
        contenido.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        // Termina de construir el Frame
        this.setContentPane(contenido);
        this.pack();
        this.setTitle("Calculadora de Jorge");
        this.setResizable(true);
    }// Constructor
    
    
    /**
    * Metodo para limpiar el campo de texto
    * Llamado por el boton C
    */
    private void limpia() {
        numeroInicio = true;
        display.setText("0");
        operadorAnterior  = "=";
        calcInterna.setTotal("0");
    }
    
    /** Clase Listener para los operadores */
    class OpListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // La calculadores siempre espara un numero o un operador
            if (numeroInicio) {
                // Se espera un numero y no un operador
                limpia();
                display.setText("ERROR - Sin operador");
            } else {
                numeroInicio = true;  // Verifica que reciba un numero
                try {
                    // Se obtiene elvalor del campo de texto
                    // Se realizan las operaciones de la calculadora
                    String displayText = display.getText();
                    
                    if (operadorAnterior.equals("=")) {
                        calcInterna.setTotal(displayText);
                    } else if (operadorAnterior.equals("+")) {
                        calcInterna.suma(displayText);
                    } else if (operadorAnterior.equals("-")) {
                        calcInterna.resta(displayText);
                    } else if (operadorAnterior.equals("*")) {
                        calcInterna.multiplica(displayText);
                    } else if (operadorAnterior.equals("/")) {
                        calcInterna.divide(displayText);
                    }
                    
                    display.setText("" + calcInterna.getTotal());
                    
                } catch (NumberFormatException ex) {
                    limpia();
                    display.setText("Error");
                }
                
                // cambia el operadorAnterior por el siguiente
                operadorAnterior = e.getActionCommand();
            }
        }
    }// Clase
    
    
    
    /** Clase para los botones y sus numeros */
    class NumListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String digito = e.getActionCommand();
            if (numeroInicio) {
                display.setText(digito);
                numeroInicio = false;
            } else {
                display.setText(display.getText() + digito);
            }
        }
    }
    
    
    /** Clase para el boton C */
    class BotonC implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            limpia();
        }
    }
}