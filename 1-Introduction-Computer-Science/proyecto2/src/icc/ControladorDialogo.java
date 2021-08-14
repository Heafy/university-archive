package icc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase para el controlador del diálogo para editar y crear peliculas.
 */   
public class ControladorDialogo {

    /* El campo de texto para el titulo. */
    @FXML private TextField titulo;
    /* El campo de texto para director. */
    @FXML private TextField director;
    /* El campo de texto para el año. */
    @FXML private TextField year;
    /* El campo de texto para el genero. */
    @FXML private TextField genero;
    /* El campo de texto para la duracion. */
    @FXML private TextField duracion;

    /* La ventana principal. */
    private Stage escenario;
    /* La nueva pelicula. */
    private Pelicula pelicula;
    /* Si el usuario aceptó la edición. */
    private boolean aceptado;

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        if (peliculaValida()) {
            aceptado = true;
            escenario.close();
        }
    }
    
    /* Manejador para cuando se activa el botón cancelar. */
    @FXML public void cancelar(ActionEvent evento) {
        pelicula = null;
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
     * Define la pelicula del diálogo.
     * @param pelicula la nueva pelicula del diálogo.
     */
    public void setPelicula(Pelicula pelicula) {
        if (pelicula == null) {
            this.pelicula = null;
            return;
        }
        this.pelicula = new Pelicula(pelicula.getTitulo(),
				     pelicula.getDirector(),
				     pelicula.getYear(),
				     pelicula.getGenero(),
				     pelicula.getDuracion());
	titulo.setText(pelicula.getTitulo());
	director.setText(pelicula.getDirector());
	year.setText(String.valueOf(pelicula.getYear()));
	genero.setText(pelicula.getGenero());
	duracion.setText(String.valueOf(pelicula.getDuracion()));
    }

    /* Determina si los cinco campos son válidos. */
    private boolean peliculaValida() {
        String error = "";
	//TITULO
        String t = titulo.getText();
        error += (t == null || t.length() == 0) ? "Titulo inválido.\n" : "";
	//DIRECTOR
	String d = director.getText();
        error += (d == null || d.length() == 0) ? "Director inválido.\n" : "";
	//AÑO
        int y = -1;
        String a = year.getText();
        if (a == null || a.length() == 0) {
            error += "Año inválido.\n";
        } else {
            try {
                y = Integer.parseInt(a);
            } catch (NumberFormatException nfe) {
                error += "Año inválido.\n";
            }
        }
	//GENERO
	String g = genero.getText();
        error += (g == null || g.length() == 0) ? "Genero inválido.\n" : "";
	//DURACION
	int z = -1;
        String p = year.getText();
        if (a == null || a.length() == 0) {
            error += "Año inválido.\n";
        } else {
            try {
                z = Integer.parseInt(p);
            } catch (NumberFormatException nfe) {
                error += "Año inválido.\n";
            }
        }
        if (error.equals("")) {
            if (pelicula != null) {
		pelicula.setTitulo(t);
		pelicula.setDirector(d);
		pelicula.setYear(y);
		pelicula.setGenero(g);
		pelicula.setDuracion(z);
            } else {
                pelicula = new Pelicula(t, d, y, g, z);
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
     * Regresa la pelicula del diálogo.
     * @return la pelicula del diálogo.
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Regresa el escenario del diálogo.
     * @return el escenario del diálogo.
     */
    public Stage getEscenario() {
        return escenario;
    }
}
