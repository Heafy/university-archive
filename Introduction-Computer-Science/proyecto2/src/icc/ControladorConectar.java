package icc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase para el controlador del diálogo para conectar al servidor.
 */   
public class ControladorConectar {

    /* El campo de texto para el servidor. */
    @FXML private TextField servidor;
    /* El campo de texto para el puerto. */
    @FXML private TextField puerto;

    /* La ventana principal. */
    private Stage escenario;
    /* Si el usuario aceptó la conexión. */
    private boolean aceptado;

    /* El servidor. */
    private String servidorValor;
    /* El puerto. */
    private int puertoValor;

    /* Manejador para cuando se activa el botón conectar. */
    @FXML private void conectar(ActionEvent evento) {
        if (conexionValida()) {
            aceptado = true;
            escenario.close();
        }
    }
    
    /* Manejador para cuando se activa el botón cancelar. */
    @FXML public void cancelar(ActionEvent evento) {
        escenario.close();
    }

    /**
     * Define el escenario del diálogo.
     * @param escenario el nuevo escenario del diálogo.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
    }

    /* Determina si los campos son válidos. */
    private boolean conexionValida() {
        String error = "";
        String s = servidor.getText();
        error += (s == null || s.length() == 0) ? "Servidor inválido.\n" : "";
        int pv = -1;
        String p = puerto.getText();
        if (p == null || p.length() == 0) {
            error += "Puerto inválido.\n";
        } else {
            try {
                pv = Integer.parseInt(p);
            } catch (NumberFormatException nfe) {
                error += "Puerto inválido.\n";
            }
        }
        if (error.equals("")) {
            servidorValor = s;
            puertoValor = pv;
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Valores inválidos");
            alert.setHeaderText("Por favor ingrese valores válidos");
            alert.setContentText(error);
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Nos dice si el usuario activó el botón de aceptar.
     * @return <code>true</code> si el usuario activó el botón de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return aceptado;
    }

    /**
     * Regresa el servidor del diálogo.
     * @return el servidor del diálogo.
     */
    public String getServidor() {
        return servidorValor;
    }

    /**
     * Regresa el puerto del diálogo.
     * @return el puerto del diálogo.
     */
    public int getPuerto() {
        return puertoValor;
    }

    /**
     * Regresa el escenario del diálogo.
     * @return el escenario del diálogo.
     */
    public Stage getEscenario() {
        return escenario;
    }
}
