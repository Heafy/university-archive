package icc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clase para el controlador de la ventana principal de la aplicación.
 */
public class ControladorVentana {

    /* Opción de menu para conectar. */
    @FXML private MenuItem conectar;
    /* Opción de menu para desconectar. */
    @FXML private MenuItem desconectar;

    /* Opción de menu para agregar. */
    @FXML private MenuItem agregar;
    /* Opción de menu para editar. */
    @FXML private MenuItem editar;
    /* Opción de menu para eliminar. */
    @FXML private MenuItem eliminar;
    /* Opción de menu para buscar. */
    @FXML private MenuItem buscar;

    /* La ventana. */
    private Stage escenario;
    /* El controlador de tabla. */
    private ControladorTabla tabla;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        setConectada(false);
    }

    /**
     * Conecta a la base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void conectar(ActionEvent evento) {
        if (tabla.isConectada())
            return;
        tabla.conecta();
        setConectada(tabla.isConectada());
    }

    /**
     * Desconecta de la base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void desconectar(ActionEvent evento) {
        if (!tabla.isConectada())
            return;
        tabla.desconecta();
        setConectada(false);
    }

    /**
     * Termina el programa.
     * @param evento el evento que generó la acción.
     */
    @FXML public void salir(ActionEvent evento) {
        desconectar(evento);
        System.exit(0);
    }

    /**
     * Agrega una nuevo pelicula.
     * @param evento el evento que generó la acción.
     */
    @FXML public void agregaPelicula(ActionEvent evento) {
        tabla.agregaPelicula(evento);
    }

    /**
     * Edita una pelicula.
     * @param evento el evento que generó la acción.
     */
    @FXML public void editaPelicula(ActionEvent evento) {
        tabla.editaPelicula(evento);
    }

    /**
     * Elimina una o varias peliculas.
     * @param evento el evento que generó la acción.
     */
    @FXML public void eliminaPeliculas(ActionEvent evento) {
        tabla.eliminaPeliculas(evento);
    }
    
    /**
     * Busca peliculas.
     * @param evento el evento que generó la acción.
     */
    @FXML public void buscaPeliculas(ActionEvent evento) {
        tabla.buscaPeliculas(evento);
    }

    /**
     * Muestra un diálogo con información del programa.
     * @param evento el evento que generó la acción.
     */
    @FXML public void acercaDe(ActionEvent evento) {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Acerca de Administrador de Peliculas.");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Aplicación para administrar Peliculas.\n"  +
                             "Copyright © 2015 Facultad de Ciencias, UNAM.");
        dialogo.showAndWait();
    }

    /**
     * Define el controlador de tabla.
     * @param tabla el controlador de tabla.
     */
    public void setControladorTabla(ControladorTabla tabla) {
        this.tabla = tabla;
    }

    /**
     * Define el escenario.
     * @param escenario el escenario.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
    }

    /**
     * Actualiza los menúes dependiendo de cuántos renglones estén
     * seleccionados.
     * @param seleccionados el número de renglones seleccionados.
     */
    public void actualizaMenues(int seleccionados) {
        editar.setDisable(seleccionados != 1);
        eliminar.setDisable(seleccionados == 0);
    }

    /* Define los menúes habilitados dependiendo de la conexión. */
    private void setConectada(boolean conectada) {
        conectar.setDisable(conectada);
        desconectar.setDisable(!conectada);

        agregar.setDisable(!conectada);
        editar.setDisable(!conectada);
        eliminar.setDisable(!conectada);
        buscar.setDisable(!conectada);
    }
}
