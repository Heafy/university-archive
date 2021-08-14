package icc;

import java.io.IOException;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Clase para el controlador de la tabla para mostrar la base de datos.
 */   
public class ControladorTabla {

    /* La tabla. */
    @FXML private TableView<Estudiante> tabla;

    /* La columna del nombre. */
    @FXML private TableColumn<Estudiante, String> nombre;
    /* La columna del número de cuenta. */
    @FXML private TableColumn<Estudiante, Number> cuenta;
    /* La columna del promedio. */
    @FXML private TableColumn<Estudiante, Number> promedio;
    /* La columna de la edad. */
    @FXML private TableColumn<Estudiante, Number> edad;

    /* El botón de agregar. */
    @FXML private Button agregar;
    /* El botón de editar. */
    @FXML private Button editar;
    /* El botón de eliminar. */
    @FXML private Button eliminar;
    /* El botón de buscar. */
    @FXML private Button buscar;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Estudiante> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Los elementos en la tabla. */
    private ObservableList<Estudiante> elementos;
    /* La ventana. */
    private Stage escenario;

    /* El controlador de la ventana. */
    private ControladorVentana ventana;
    /* La base de datos. */
    private BaseDeDatosEstudiantes bdd;

    /**
     * Construye un nuevo controlador para la tabla.
     */
    public ControladorTabla() {
        bdd = new BaseDeDatosEstudiantes();
    }

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        seleccion.addListener(new ListChangeListener<TablePosition>() {
                public void onChanged(Change c) {
                    actualizaBotones(seleccion.size());
                    ventana.actualizaMenues(seleccion.size());
                }
            });
        nombre.setCellValueFactory((c) -> c.getValue().getNombreProperty());
        cuenta.setCellValueFactory((c) -> c.getValue().getCuentaProperty());
        promedio.setCellValueFactory((c) -> c.getValue().getPromedioProperty());
        edad.setCellValueFactory((c) -> c.getValue().getEdadProperty());
        elementos = tabla.getItems();
    }

    /**
     * Agrega un estudiante, abriendo un diálogo para pedir los datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void agregaEstudiante(ActionEvent evento) {
        ControladorDialogo controlador = generaDialogo("Agregar estudiante", null);
        if (controlador == null)
            return;
        controlador.getEscenario().showAndWait();
        if (controlador.isAceptado()) {
            Estudiante e = controlador.getEstudiante();
            bdd.agregaRegistro(e);
            elementos.add(e);
            ventana.setModificado(true);
        }        
    }
    
    /**
     * Edita un estudiante, abriendo un diálogo para editar los datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void editaEstudiante(ActionEvent evento) {
        int r = seleccion.get(0).getRow();
        Estudiante estudiante = elementos.get(r);
        ControladorDialogo controlador =
            generaDialogo("Editar estudiante", estudiante);
        if (controlador == null)
            return;
        controlador.getEscenario().showAndWait();
        if (controlador.isAceptado())
            ventana.setModificado(true);
    }

    /**
     * Elimina los estudiantes selecciones, si está de acuerdo el usuario.
     * @param evento el evento que generó la acción.
     */
    @FXML public void eliminaEstudiantes(ActionEvent evento) {
        if (!ventana.dialogoSiNo("Eliminar estudiantes",
                                 "Esto eliminará los estudiantes seleccionados.",
                                 "¿Está seguro?"))
            return;
        Lista<Estudiante> eliminar = new Lista<Estudiante>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            eliminar.agregaFinal(elementos.get(r));
        }
        for (Estudiante e : eliminar) {
            elementos.remove(e);
            bdd.eliminaRegistro(e);
        }
        ventana.setModificado(true);
    }

    /**
     * Muestra un diálogo para buscar estudiantes en la base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void buscaEstudiantes(ActionEvent evento) {
        try {
            FXMLLoader cargador = new FXMLLoader();
            ClassLoader cl = getClass().getClassLoader();
            cargador.setLocation(cl.getResource("fxml/busqueda.fxml"));
            AnchorPane pagina = (AnchorPane)cargador.load();

            Stage escenario = new Stage();
            escenario.setTitle("Buscar estudiantes");
            escenario.initModality(Modality.WINDOW_MODAL);
            Scene escena = new Scene(pagina);
            escenario.setScene(escena);

            ControladorBusqueda controlador = cargador.getController();
            controlador.setEscenario(escenario);

            escenario.showAndWait();
            if (!controlador.isAceptado())
                return;
            Lista<Estudiante> r;
            r = bdd.buscaRegistros(controlador.getCampo(),
                                   controlador.getValor());
            for (Estudiante e : r)
                modeloSeleccion.select(e);
        } catch (IOException e) {
            e.printStackTrace();
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al cargar el diálogo");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "cargar el diálogo 'fxml/busqueda.fxml'.");
            dialogo.showAndWait();
        }
    }

    /* Genera un diálogo para crear o editar un estudiante. */
    private ControladorDialogo generaDialogo(String titulo, Estudiante estudiante) {
        try {
            FXMLLoader cargador = new FXMLLoader();
            ClassLoader cl = getClass().getClassLoader();
            cargador.setLocation(cl.getResource("fxml/dialogo.fxml"));
            AnchorPane pagina = (AnchorPane)cargador.load();

            Stage escenario = new Stage();
            escenario.setTitle(titulo);
            escenario.initModality(Modality.WINDOW_MODAL);
            Scene escena = new Scene(pagina);
            escenario.setScene(escena);

            ControladorDialogo controlador = cargador.getController();
            controlador.setEscenario(escenario);
            controlador.setEstudiante(estudiante);

            return controlador;
        } catch (IOException e) {
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al cargar el diálogo");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "cargar el diálogo 'fxml/dialogo.fxml'.");
            dialogo.showAndWait();
            return null;
        }
    }

    /**
     * Actualiza la tabla con una nueva base de datos.
     * @param bdd la nueva base de datos.
     */
    public void actualizaTabla(BaseDeDatosEstudiantes bdd) {
        this.bdd = bdd;
        elementos.removeAll();
        for (Estudiante e : bdd.getRegistros())
            elementos.add(e);
    }

    /**
     * Define el controlador de ventana.
     * @param ventana el controlador de ventana.
     */
    public void setControladorVentana(ControladorVentana ventana) {
        this.ventana = ventana;
    }

    /**
     * Actualiza los botones de la tabla.
     * @param seleccionados el número de renglones seleccionados en la tabla.
     */
    private void actualizaBotones(int seleccionados) {
        eliminar.setDisable(seleccionados == 0);
        editar.setDisable(seleccionados != 1);
    }

    /**
     * Define el escenario de la tabla.
     * @param escenario el escenario de la tabla.
     */
    public void setEscenario(Stage escenario) {
        this.escenario = escenario;
    }

    /**
     * Regresa la base de datos de estudiantes.
     * @return la base de datos de estudiantes.
     */
    public BaseDeDatosEstudiantes getBaseDeDatosEstudiantes() {
        return bdd;
    }

    /**
     * Limpia la tabla.
     */
    public void limpiaTabla() {
        bdd = new BaseDeDatosEstudiantes();
        elementos.removeAll();
    }
}
