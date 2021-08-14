package icc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase para el controlador del diálogo para buscar estudiantes.
 */   
public class ControladorBusqueda {

    /* El combo del campo. */
    @FXML private ComboBox campo;
    /* El campo de texto para el valor. */
    @FXML private TextField valor;

    /* La ventana principal. */
    private Stage escenario;
    /* Si el usuario aceptó la búsqueda. */
    private boolean aceptado;

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
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

    /**
     * Nos dice si el usuario activó el botón de aceptar.
     * @return <code>true</code> si el usuario activó el botón de aceptar,
     *         <code>false</code> en otro caso.
     */
    public boolean isAceptado() {
        return aceptado;
    }

    /**
     * Regresa el escenario del diálogo.
     * @return el escenario del diálogo.
     */
    public Stage getEscenario() {
        return escenario;
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public String getCampo() {
        switch (campo.getValue().toString().trim()) {
        case "Nombre":   return "nombre";
        case "# Cuenta": return "cuenta";
        case "Promedio": return "promedio";
        case "Edad":     return "edad";
        default:         return "";
        }
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public String getValor() {
        return valor.getText();
    }
}
