package icc;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Práctica 9: Interfaces gráficas.
 */
public class Practica9 extends Application {

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        escenario.setTitle("Administrador de Estudiantes");
        String url = cl.getResource("icons/ciencias.png").toString();
        escenario.getIcons().add(new Image(url));

        FXMLLoader cargadorVentana = new FXMLLoader();
        cargadorVentana.setLocation(cl.getResource("fxml/ventana.fxml"));
        BorderPane ventana = (BorderPane)cargadorVentana.load();
        ControladorVentana controladorVentana = cargadorVentana.getController();

        FXMLLoader cargadorTabla = new FXMLLoader();
        cargadorTabla.setLocation(cl.getResource("fxml/tabla.fxml"));
        GridPane tabla = (GridPane)cargadorTabla.load();
        ControladorTabla controladorTabla = cargadorTabla.getController();

        controladorVentana.setEscenario(escenario);
        controladorTabla.setEscenario(escenario);
        controladorVentana.setControladorTabla(controladorTabla);
        controladorTabla.setControladorVentana(controladorVentana);

        ventana.setCenter(tabla);
        Scene escena = new Scene(ventana);
        escenario.setScene(escena);
        escenario.setOnCloseRequest((e) -> controladorVentana.salir(null));
        escenario.show();
    }
 
    public static void main(String[] args) {
        /* Español de México para los elementos de la interfaz gráfica
         * predefinidos por JavaFX. */
        Locale.setDefault(new Locale("es", "MX"));
        launch(args);
    }
}
