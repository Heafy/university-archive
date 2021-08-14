package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Clase para el controlador de la ventana principal de la aplicación.
 */
public class ControladorVentana {

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
    /* El archivo. */
    private File archivo;
    /* Si la base de datos actual ha sido modificada. */
    private boolean modificado;

    /**
     * Crea una nueva base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void nuevaBaseDeDatos(ActionEvent evento) {
        preguntaSiModificado("¿Desea guardarla antes de crear una nueva?");
        tabla.limpiaTabla();
        modificado = false;
    }

    /**
     * Carga una base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void cargaBaseDeDatos(ActionEvent evento) {
        preguntaSiModificado("¿Desea guardarla antes de cargar otra?");
        FileChooser fc = new FileChooser();
        fc.setTitle("Cargar Base de Datos");
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("Bases de datos", "*.bd"),
            new ExtensionFilter("Todos los archivos", "*.*"));
        File archivo = fc.showOpenDialog(escenario);
        if (archivo != null)
            cargaBaseDeDatosDeArchivo(archivo);
    }

    /**
     * Guarda la base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void guardaBaseDeDatos(ActionEvent evento) {
        if (archivo == null)
            guardaBaseDeDatosComo(evento);
        else
            guardaBaseDeDatosEnArchivo();
    }

    /**
     * Guarda la base de datos con un nombre distinto.
     * @param evento el evento que generó la acción.
     */
    @FXML public void guardaBaseDeDatosComo(ActionEvent evento) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Guardar Base de Datos como...");
        fc.getExtensionFilters().addAll(
            new ExtensionFilter("Bases de datos", "*.bd"),
            new ExtensionFilter("Todos los archivos", "*.*"));
        archivo = fc.showSaveDialog(escenario);
        if (archivo != null)
            guardaBaseDeDatosEnArchivo();
    }

    /**
     * Termina el programa.
     * @param evento el evento que generó la acción.
     */
    @FXML public void salir(ActionEvent evento) {
        preguntaSiModificado("¿Desea guardarla antes de salir?");
        System.exit(0);
    }

    /**
     * Agrega un nuevo estudiante.
     * @param evento el evento que generó la acción.
     */
    @FXML public void agregaEstudiante(ActionEvent evento) {
        tabla.agregaEstudiante(evento);
    }

    /**
     * Edita un estudiante.
     * @param evento el evento que generó la acción.
     */
    @FXML public void editaEstudiante(ActionEvent evento) {
        tabla.editaEstudiante(evento);
    }

    /**
     * Elimina uno o varios estudiantes.
     * @param evento el evento que generó la acción.
     */
    @FXML public void eliminaEstudiantes(ActionEvent evento) {
        tabla.eliminaEstudiantes(evento);
    }
    
    /**
     * Busca estudiantes.
     * @param evento el evento que generó la acción.
     */
    @FXML public void buscaEstudiantes(ActionEvent evento) {
        tabla.buscaEstudiantes(evento);
    }

    /**
     * Muestra un diálogo con información del programa.
     * @param evento el evento que generó la acción.
     */
    @FXML public void acercaDe(ActionEvent evento) {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Acerca de Administrador de Estudiantes.");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Aplicación para administrar estudiantes.\n"  +
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

    /* Carga la base de datos de un archivo. */
    private void cargaBaseDeDatosDeArchivo(File archivo) {
        BaseDeDatosEstudiantes nbdd = new BaseDeDatosEstudiantes();
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(archivo)));
            nbdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al cargar base de datos");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "cargar la base de datos en '" +
                                   archivo + "'.");
            dialogo.showAndWait();
            return;
        }
        tabla.actualizaTabla(nbdd);
        this.archivo = archivo;
        modificado = false;
    }

    /* Guarda la base de datos en un archivo. */
    private void guardaBaseDeDatosEnArchivo() {
        BaseDeDatosEstudiantes bdd = tabla.getBaseDeDatosEstudiantes();
        try {
            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(archivo)));
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al guardar base de datos");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "guardar la base de datos en '" +
                                   archivo + "'.");
            dialogo.showAndWait();
            return;
        }
        modificado = false;
    }

    /**
     * Actualiza los menúes dependiendo de cuántos renglones estén
     * seleccionados.
     * @param seleccionados el número de renglones seleccionados.
     */
    public void actualizaMenues(int seleccionados) {
        eliminar.setDisable(seleccionados == 0);
        editar.setDisable(seleccionados != 1);
    }

    /**
     * Nos dice si la base de datos ha sido modificada.
     * @return <code>true</code> si la base de datos ha sido modificada,
     *         <code>false</code> en otro caso.
     */
    public boolean isModificado() {
        return modificado;
    }

    /**
     * Define el estado de modificación de la base de datos.
     * @param modificado el estado de modificación.
     */
    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    /**
     * Crea un diálogo con una pregunta sí/no.
     * @param titulo el título del diálogo.
     * @param mensaje el mensaje en el diálogo.
     * @param pregunta la pregunta que debe responder el usuario.
     * @return <code>true</code> si el usuario contesta "sí", <code>false</code>
     *         en otro caso.
     */
    public boolean dialogoSiNo(String titulo, String mensaje, String pregunta) {
        Alert dialogo = new Alert(AlertType.CONFIRMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(mensaje);
        dialogo.setContentText(pregunta);

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        dialogo.getButtonTypes().setAll(si, no);

        Optional<ButtonType> resultado = dialogo.showAndWait();
        return resultado.get() == si;
    }

    /* Si la base de datos ha sido modificada, muestra un diálogo preguntando al
     * usuario si quiere guardarla. */
    private void preguntaSiModificado(String pregunta) {
        if (modificado) {
            if (dialogoSiNo("Base de datos modificada",
                            "La base de datos ha sido modificada.",
                            pregunta))
                guardaBaseDeDatos(null);
        }
    }
}
