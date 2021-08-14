package icc;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Proyecto 2: Hilos de ejecución y enchufes.
 */
public class Proyecto2 extends Application {

    /* Imprime un mensaje de cómo usar el programa. */
    private static void uso() {
        System.out.println("Uso: java -jar proyecto2.jar " +
                           "[ --servidor puerto [archivo] | --cliente ]");
        System.exit(0);
    }

    /* Inicia el servidor. */
    private static void servidor(String[] args) {
        if (args.length < 2 || args.length > 3)
            uso();

        int puerto = 1234;
        try {
            puerto = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            uso();
        }

        String archivo = null;
        if (args.length == 3)
            archivo = args[2];

        try {
            ServidorBaseDeDatosPelicula servidor;
            servidor = new ServidorBaseDeDatosPelicula(archivo, puerto);
            servidor.sirve();
        } catch (IOException ioe) {
            System.out.println("Error al crear el servidor.");
        }
    }

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        escenario.setTitle("Administrador de Peliculas");
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
        if (args.length < 1)
            uso();

        switch (args[0]) {
        case "--servidor":
            servidor(args);
            break;
        case "--cliente":
            Locale.setDefault(new Locale("es", "MX"));
            launch(args);
            break;
        default:
            uso();
        }
    }
}
