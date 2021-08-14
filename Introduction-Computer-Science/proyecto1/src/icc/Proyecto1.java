package icc;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proyecto 1: Bases de Datos.
 */
public class Proyecto1 {

    /* Hace búsquedas por titulo y año de cuenta en la base de
       datos. */
   private static void busquedas(BaseDeDatosPelicula bdd) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        System.out.printf("Entra un titulo para buscar: ");
        String titulo = sc.next();

        Lista<Pelicula> r = bdd.buscaRegistros("titulo", titulo);
        if (r.getLongitud() == 0) {
            System.out.printf("\nNo se hallaron titulos " +
                              "con el nombre \"%s\".\n",
                              titulo);
        } else {
            System.out.printf("\nSe hallaron las siguientes " +
                              "peliculas con el titulo \"%s\":\n\n",
                              titulo);
            for (Pelicula e : r)
                System.out.println(e + "\n");
        }
	
	System.out.printf("Entra un nombre de director para buscar: ");
        String director = sc.next();

        r = bdd.buscaRegistros("director", director);
        if (r.getLongitud() == 0) {
            System.out.printf("\nNo se hallaron directores " +
                              "con el nombre \"%s\".\n",
                              director);
        } else {
            System.out.printf("\nSe hallaron los siguientes " +
                              "directores con el nombre \"%s\":\n\n",
                              director);
            for (Pelicula e : r)
                System.out.println(e + "\n");
        }

        System.out.printf("Entra un año para buscar: ");
        int year = 0;
        try {
            year = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.printf("Se entró un año invalido. " +
                              "Se interpretará como cero.\n");
        }

        r = bdd.buscaRegistros("year", String.valueOf(year));
        if (r.getLongitud() == 0) {
            System.out.printf("\nNo se hallaron peliculas " +
                              "de este año \"%d\".\n",
                              year);
        } else {
            System.out.printf("\nSe hallaron las siguientes " +
                              "peliculas del año \"%d\":\n\n",
                              year);
            for (Pelicula e : r)
                System.out.println(e + "\n");
        }

	System.out.printf("Entra genero para buscar: ");
        String genero = sc.next();

        r = bdd.buscaRegistros("genero", genero);
        if (r.getLongitud() == 0) {
            System.out.printf("\nNo se hallaron peliculas " +
                              "del genero \"%s\".\n",
                              genero);
        } else {
            System.out.printf("\nSe hallaron las siguientes " +
                              "peliculas del genero \"%s\":\n\n",
                              genero);
            for (Pelicula e : r)
                System.out.println(e + "\n");
        }

	System.out.printf("Entra los minutos de duracion para buscar: ");
        int duracion = 0;
        try {
            duracion = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.printf("Se entró una duracion invalida. " +
                              "Se interpretará como cero.\n");
        }

        r = bdd.buscaRegistros("duracion", String.valueOf(duracion));
        if (r.getLongitud() == 0) {
            System.out.printf("\nNo se hallaron peliculas " +
                              "de esta duracion \"%d\".\n",
                              duracion);
        } else {
            System.out.printf("\nSe hallaron las siguientes " +
                              "peliculas con esta duracion \"%d\":\n\n",
                              duracion);
            for (Pelicula e : r)
                System.out.println(e + "\n");
        }
    }

    /* Crea una base de datos y la llena a partir de los datos que
       el usuario escriba a través del teclado. Después la guarda en
       disco duro y la regresa. */
       private static BaseDeDatosPelicula escritura(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe.\n" +
                              "Presiona Ctrl-C si no quieres reescribirlo, " +
                              "o Enter para continuar...\n", nombreArchivo);
            sc.nextLine();
        }

        System.out.println("Entra peliculas a la base de datos.\n" +
                           "Cuando desees terminar, deja el titulo en blanco.\n");

        BaseDeDatosPelicula bdd = new BaseDeDatosPelicula();

        do {
	    String titulo;
	    String director;
	    int year = 0;
	    String genero;
	    int duracion = 0;

            System.out.printf("Titulo        : ");
            titulo = sc.next();
            if (titulo.equals(""))
                break;
            try {
                System.out.printf("Director      : ");
                director = sc.next();
                System.out.printf("Año           : ");
                year = sc.nextInt();
 		System.out.printf("Genero        : ");	
                genero = sc.next();
                System.out.printf("Duracion(min) : ");
                duracion = sc.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nValor invalido: se descartará " +
                                   "esta pelicula.\n");
                continue;
            }
            Pelicula e = new Pelicula(titulo,
				      director,	
				      year,
				      genero,
				      duracion);
            bdd.agregaRegistro(e);
            System.out.println();
        } while (true);

        int n = bdd.getNumRegistros();
        if (n == 1)
            System.out.printf("\nSe agregó 1 pelicula.\n");
        else
            System.out.printf("\nSe agregaron %d peliculas.\n", n);

        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("\nBase de datos guardada exitosamente en \"%s\".\n",
                          nombreArchivo);

        return bdd;
    }

    /* Crea una base de datos y la llena cargándola del disco
       duro. Después la regresa. */
   private static BaseDeDatosPelicula lectura(String nombreArchivo) {
        BaseDeDatosPelicula bdd = new BaseDeDatosPelicula();

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("Base de datos cargada exitosamente de \"%s\".\n\n",
                          nombreArchivo);

        Lista<Pelicula> r = bdd.getRegistros();
        for (Pelicula e : r)
            System.out.println(e + "\n");

        return bdd;
    }

   /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar proyecto1.jar [-g|-c] <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        String bandera = args[0];
        String nombreArchivo = args[1];

        if (!bandera.equals("-g") && !bandera.equals("-c"))
            uso();

        BaseDeDatosPelicula bdd;

        if (bandera.equals("-g"))
            bdd = escritura(nombreArchivo);
        else
            bdd = lectura(nombreArchivo);

        busquedas(bdd);
    }
}
