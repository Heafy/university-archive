package icc;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableView;
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
    @FXML private TableView<Pelicula> tabla;

    /* La columna del titulo. */
    @FXML private TableColumn<Pelicula, String> titulo;
    /* La columna del director. */
    @FXML private TableColumn<Pelicula, String> director;
    /* La columna del año. */
    @FXML private TableColumn<Pelicula, Number> year;
     /* La columna del genero. */
    @FXML private TableColumn<Pelicula, String> genero; 
    /* La columna de la duracion. */
    @FXML private TableColumn<Pelicula, Number> duracion;

    /* El botón de agregar. */
    @FXML private Button agregar;
    /* El botón de editar. */
    @FXML private Button editar;
    /* El botón de eliminar. */
    @FXML private Button eliminar;
    /* El botón de buscar. */
    @FXML private Button buscar;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Pelicula> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Los elementos en la tabla. */
    private ObservableList<Pelicula> elementos;
    /* La ventana. */
    private Stage escenario;

    /* El controlador de la ventana. */
    private ControladorVentana ventana;
    /* La base de datos. */
    private BaseDeDatosPelicula bdd;
    /* El hilo de ejecución del cliente. */
    private HiloCliente<Pelicula> hilo;
    /* Bandera de conexión. */
    private boolean conectada;

    /**
     * Construye un nuevo controlador para la tabla.
     */
    public ControladorTabla() {
        bdd = new BaseDeDatosPelicula();
        bdd.agregaEscucha((t,e) -> baseDeDatosModificada(t, e));
    }

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        setConectada(false);
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        seleccion.addListener(new ListChangeListener<TablePosition>() {
                public void onChanged(Change c) {
                    if (!conectada || seleccion == null)
                        return;
                    actualizaBotones(seleccion.size());
                    ventana.actualizaMenues(seleccion.size());
                }
            });
	titulo.setCellValueFactory((c) -> c.getValue().getTituloProperty());
	director.setCellValueFactory((c) -> c.getValue().getDirectorProperty());
	year.setCellValueFactory((c) -> c.getValue().getYearProperty());
	genero.setCellValueFactory((c) -> c.getValue().getGeneroProperty());
	duracion.setCellValueFactory((c) -> c.getValue().getDuracionProperty());
        elementos = tabla.getItems();
    }

    /**
     * Agrega una pelicula, abriendo un diálogo para pedir los datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void agregaPelicula(ActionEvent evento) {
        if (!conectada)
            return;
        ControladorDialogo controlador = generaDialogo("Agregar pelicula", null);
        if (controlador == null)
            return;
        controlador.getEscenario().showAndWait();
        if (controlador.isAceptado()) {
            Pelicula e = controlador.getPelicula();
            bdd.agregaRegistro(e);
            if (!hilo.avisaCambio(Protocolo.REGISTRO_AGREGADO, e))
                errorDeComunicacion();
        }
    }
    
    /**
     * Edita una pelicula, abriendo un diálogo para editar los datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void editaPelicula(ActionEvent evento) {
        if (!conectada)
            return;
        int r = seleccion.get(0).getRow();
        Pelicula pelicula= elementos.get(r);
        ControladorDialogo controlador =
            generaDialogo("Editar pelicula", pelicula);
        if (controlador == null)
            return;
        controlador.getEscenario().showAndWait();
        if (controlador.isAceptado()) {
            bdd.eliminaRegistro(pelicula);
            bdd.agregaRegistro(controlador.getPelicula());
            if (!hilo.avisaCambio(Protocolo.REGISTRO_MODIFICADO, pelicula,
                                  controlador.getPelicula()))
                errorDeComunicacion();
        }
    }

    /**
     * Elimina las peliculas seleccionadas, si está de acuerdo el usuario.
     * @param evento el evento que generó la acción.
     */
    @FXML public void eliminaPeliculas(ActionEvent evento) {
        if (!conectada)
            return;

        Alert dialogo = new Alert(AlertType.CONFIRMATION);
        dialogo.setTitle("Eliminar peliculas");
        dialogo.setHeaderText("Esto eliminará las peliculas seleccionadas.");
        dialogo.setContentText("¿Está seguro?");
        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        dialogo.getButtonTypes().setAll(si, no);
        Optional<ButtonType> resultado = dialogo.showAndWait();
        if (resultado.get() != si)
            return;

        Lista<Pelicula> eliminar = new Lista<Pelicula>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            eliminar.agregaFinal(elementos.get(r));
        }
        for (Pelicula e : eliminar) {
            bdd.eliminaRegistro(e);
            if (!hilo.avisaCambio(Protocolo.REGISTRO_ELIMINADO, e)) {
                errorDeComunicacion();
                break;
            }
        }
    }

    /**
     * Muestra un diálogo para buscar peliculas en la base de datos.
     * @param evento el evento que generó la acción.
     */
    @FXML public void buscaPeliculas(ActionEvent evento) {
        if (!conectada)
            return;
        try {
            FXMLLoader cargador = new FXMLLoader();
            ClassLoader cl = getClass().getClassLoader();
            cargador.setLocation(cl.getResource("fxml/busqueda.fxml"));
            AnchorPane pagina = (AnchorPane)cargador.load();

            Stage escenario = new Stage();
            escenario.setTitle("Buscar peliculas");
            escenario.initModality(Modality.WINDOW_MODAL);
            Scene escena = new Scene(pagina);
            escenario.setScene(escena);

            ControladorBusqueda controlador = cargador.getController();
            controlador.setEscenario(escenario);

            escenario.showAndWait();
            if (!controlador.isAceptado())
                return;
            Lista<Pelicula> r;
            r = bdd.buscaRegistros(controlador.getCampo(),
                                   controlador.getValor());
            for (Pelicula e : r)
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

    /* Genera un diálogo para crear o editar una pelicula. */
	//CUIDADO CON LA STRING T	
    private ControladorDialogo generaDialogo(String t, Pelicula pelicula) {
        try {
            FXMLLoader cargador = new FXMLLoader();
            ClassLoader cl = getClass().getClassLoader();
            cargador.setLocation(cl.getResource("fxml/dialogo.fxml"));
            AnchorPane pagina = (AnchorPane)cargador.load();

            Stage escenario = new Stage();
            escenario.setTitle(t);
            escenario.initModality(Modality.WINDOW_MODAL);
            Scene escena = new Scene(pagina);
            escenario.setScene(escena);

            ControladorDialogo controlador = cargador.getController();
            controlador.setEscenario(escenario);
            controlador.setPelicula(pelicula);

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
     * Regresa la base de datos de peliculas.
     * @return la base de datos de peliculas.
     */
    public BaseDeDatosPelicula getBaseDeDatosPelicula() {
        return bdd;
    }

    /**
     * Conecta la tabla al servidor.
     */
    public void conecta() {
        if (conectada)
            return;
        String servidor = null;
        int puerto = -1;
        try {
            FXMLLoader cargador = new FXMLLoader();
            ClassLoader cl = getClass().getClassLoader();
            cargador.setLocation(cl.getResource("fxml/conectar.fxml"));
            AnchorPane pagina = (AnchorPane)cargador.load();

            Stage escenario = new Stage();
            escenario.setTitle("Conectar a servidor");
            escenario.initModality(Modality.WINDOW_MODAL);
            Scene escena = new Scene(pagina);
            escenario.setScene(escena);

            ControladorConectar controlador = cargador.getController();
            controlador.setEscenario(escenario);

            controlador.getEscenario().showAndWait();
            if (controlador.isAceptado()) {
                servidor = controlador.getServidor();
                puerto = controlador.getPuerto();
            } else {
                return;
            }
        } catch (IOException e) {
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al cargar el diálogo");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "cargar el diálogo 'fxml/conectar.fxml'.");
            dialogo.showAndWait();
            return;
        }

        try {
            Socket enchufe = new Socket(servidor, puerto);
            hilo = new HiloCliente<Pelicula>(bdd, enchufe);
            hilo.start();
        } catch (IOException ioe) {
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("Error al establecer conexión");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ocurrió un error al tratar de " +
                                   "conectarnos a " + servidor + ":" +
                                   puerto + ".");
            dialogo.showAndWait();
            ventana.desconectar(null);
            bdd.limpia();
            return;
        }
        setConectada(true);
    }

    /**
     * Desconecta la tabla del servidor.
     */
    public void desconecta() {
        if (!conectada)
            return;
        hilo.cierraTodo();
        hilo = null;
        bdd.limpia();
        setConectada(false);
    }

    /* Define si la tabla está conectada o no. */
    private void setConectada(boolean conectada) {
        this.conectada = conectada;
        agregar.setDisable(!conectada);
        editar.setDisable(!conectada);
        eliminar.setDisable(!conectada);
        buscar.setDisable(!conectada);
        if (conectada == true) {
            actualizaBotones(seleccion.size());
            ventana.actualizaMenues(seleccion.size());
        }
    }

    /**
     * Nos dice si la tabla está o no conectada al servidor.
     * @return <code>true</code> si la tabla está conectada al servidor,
     *         <code>false</code> en otro caso.
     */
    public boolean isConectada() {
        return conectada;
    }

    /* El escucha de eventos en la base de datos. */
    private void baseDeDatosModificada(int tipo, Pelicula e) {
        switch (tipo) {
        case BaseDeDatos.BASE_LIMPIADA:
            elementos.clear();
            break;
        case BaseDeDatos.REGISTRO_AGREGADO:
            elementos.add(e);
            break;
        case BaseDeDatos.REGISTRO_ELIMINADO:
            elementos.remove(e);
            break;
        }
    }

    /* Muestra un diálogo de error si ocurre un problema al comunicarnos con el
     * servidor. */
    private void errorDeComunicacion() {
        Alert dialogo = new Alert(AlertType.ERROR);
        dialogo.setTitle("Error de comunicación");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ocurrió un error al tratar de " +
                               "comunicarnos con el servidor.");
        dialogo.showAndWait();
        bdd.limpia();
        elementos.clear();
        ventana.desconectar(null);
    }
}
