package icc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase para el controlador del diálogo para editar y crear estudiantes.
 */   
public class ControladorDialogo {

    /* El campo de texto para el nombre. */
    @FXML private TextField nombre;
    /* El campo de texto para el número de cuenta. */
    @FXML private TextField cuenta;
    /* El campo de texto para el promedio. */
    @FXML private TextField promedio;
    /* El campo de texto para la edad. */
    @FXML private TextField edad;

    /* La ventana principal. */
    private Stage escenario;
    /* El nuevo estudiante. */
    private Estudiante estudiante;
    /* Si el usuario aceptó la edición. */
    private boolean aceptado;

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        if (estudianteValido()) {
            aceptado = true;
            escenario.close();
        }
    }
    
    /* Manejador para cuando se activa el botón cancelar. */
    @FXML public void cancelar(ActionEvent evento) {
        estudiante = null;
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
     * Define el estudiante del diálogo.
     * @param estudiante el nuevo estudiante del diálogo.
     */
    public void setEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            this.estudiante = null;
            return;
        }
        this.estudiante = new Estudiante(estudiante.getNombre(),
                                         estudiante.getCuenta(),
                                         estudiante.getPromedio(),
                                         estudiante.getEdad());
        nombre.setText(estudiante.getNombre());
        cuenta.setText(String.format("%09d", estudiante.getCuenta()));
        promedio.setText(String.format("%2.2f", estudiante.getPromedio()));
        edad.setText(String.valueOf(estudiante.getEdad()));
    }

    /* Determina si los cuatro campos son válidos. */
    private boolean estudianteValido() {
        String error = "";
        String n = nombre.getText();
        error += (n == null || n.length() == 0) ? "Nombre inválido.\n" : "";
        int c = -1;
        String s = cuenta.getText();
        if (s == null || s.length() == 0) {
            error += "Número de cuenta inválido.\n";
        } else {
            try {
                c = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                error += "Número de cuenta inválido.\n";
            }
        }
        double p = -1.0;
        s = promedio.getText();
        if (s == null || s.length() == 0) {
            error += "Promedio inválido.\n";
        } else {
            try {
                p = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                error += "Promedio inválido.\n";
            }
        }
        int e = -1;
        s = edad.getText();
        if (s == null || s.length() == 0) {
            error += "Edad inválida.\n";
        } else {
            try {
                e = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                error += "Edad inválida.\n";
            }
        }
        if (error.equals("")) {
            if (estudiante != null) {
                estudiante.setNombre(n);
                estudiante.setCuenta(c);
                estudiante.setPromedio(p);
                estudiante.setEdad(e);
            } else {
                estudiante = new Estudiante(n, c, p, e);
            }
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
     * Regresa el estudiante del diálogo.
     * @return el estudiante del diálogo.
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**
     * Regresa el escenario del diálogo.
     * @return el escenario del diálogo.
     */
    public Stage getEscenario() {
        return escenario;
    }
}
