package icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import icc.BaseDeDatos;
import icc.BaseDeDatosPelicula;
import icc.Pelicula;
import icc.Lista;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link
 * BaseDeDatosPelicula}.
 */
public class TestBaseDeDatosPelicula {

     private static String[] TITULOS = {
	"Titanic", "Hulk", "Avengers", "Thor", "Tiburon"
    };

    private static String[] DIRECTORES = {
	"Cuaron", "Del Toro", "Scorcese", "Bay", "Whedon"
    };

    private static String[] GENEROS = {
	"Terror", "Comedia", "Suspenso", "Accion", "Romance"
    };     

    private Random random;
    private BaseDeDatosPelicula bdd;
    private int total;
    private boolean llamado;
    private int contador;

    /**
     * Crea un generador de números aleatorios para cada prueba y
     * una base de datos de peliculas.
     */
    public TestBaseDeDatosPelicula() {
        random = new Random();
        bdd = new BaseDeDatosPelicula();
        total = 1 + random.nextInt(100);
    }
    
    /* Genera un titulo  aleatorio. */
    private String generaTituloAleatorio() {
        int n = random.nextInt(TITULOS.length);
        return TITULOS[n];
    }

    /* Genera un director aleatorio. */
    private String generaDirectorAleatorio() {
        int n = random.nextInt(DIRECTORES.length);
        return DIRECTORES[n];
    }

    /* Genera un genero aleatorio. */
    private String generaGeneroAleatorio() {
        int n = random.nextInt(GENEROS.length);
        return GENEROS[n];
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosPelicula#BaseDeDatosPelicula}.
     */
    @Test public void testConstructor() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testNumeroDeRegistros() {
	for (int i = 0; i < total; i++) {
            Pelicula e = new Pelicula(generaTituloAleatorio(),
				      generaDirectorAleatorio(),
				      random.nextInt(2015),
				      generaGeneroAleatorio(),
				      random.nextInt(300));         
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Pelicula[] peliculas = new Pelicula[total];
        for (int i = 0; i < total; i++) {
            peliculas[i] = new Pelicula(generaTituloAleatorio(),
				       generaDirectorAleatorio(),
				       random.nextInt(2015),
				       generaGeneroAleatorio(),
				       random.nextInt(300));
            bdd.agregaRegistro(peliculas[i]);
        }
        int i = 0;
        Lista<Pelicula> l = bdd.getRegistros();
        for (Pelicula e : l)
            peliculas[i++].equals(e);
        l.elimina(peliculas[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
             Pelicula e = new Pelicula(generaTituloAleatorio(),
				      generaDirectorAleatorio(),
				      random.nextInt(2015),
				      generaGeneroAleatorio(),
				      random.nextInt(300));
            bdd.agregaRegistro(e);
            Lista<Pelicula> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
        llamado = false;
        bdd.agregaEscucha((t,e) -> {
                Assert.assertTrue(t == BaseDeDatos.REGISTRO_AGREGADO);
                Assert.assertTrue(e.getYear() == 1);
                llamado = true;
            });
        bdd.agregaRegistro(new Pelicula("A", "A", 1, "A", 1));
        Assert.assertTrue(llamado);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
	int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Pelicula e = new Pelicula(generaTituloAleatorio(),
				      generaDirectorAleatorio(),
				      random.nextInt(2015),
				      generaGeneroAleatorio(),
				      random.nextInt(300));
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Pelicula e = bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
        llamado = false;
	Pelicula pelicula = new Pelicula("A", "A", 1, "A", 1);
        bdd.agregaRegistro(pelicula);
        bdd.agregaEscucha((t,e) -> {
                Assert.assertTrue(t == BaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(e.getYear() == 1);
                llamado = true;
            });
        bdd.eliminaRegistro(pelicula);
        Assert.assertTrue(llamado);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosPeliculalal#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        	int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
	    Pelicula peli = new Pelicula(generaTituloAleatorio(),
					     generaDirectorAleatorio(),
					     ini + i,
					     generaGeneroAleatorio(),
					     random.nextInt(300));
            bdd.agregaRegistro(peli);
        }

        String busqueda = String.valueOf(ini).substring(0, 2);
        Lista<Pelicula> l = bdd.buscaRegistros("year", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        for (Pelicula e : l) {
            String c = String.valueOf(e.getYear());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("year", busqueda);
        Assert.assertFalse(l.getLongitud() != 0);

        busqueda = String.valueOf(ini/1000);
        l = bdd.buscaRegistros("year", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        for (Pelicula e : l) {
            String c = String.valueOf(e.getYear());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("year", busqueda);
        Assert.assertFalse(l.getLongitud() != 0);

        try {
            l = bdd.buscaRegistros("no-existe", "");
        } catch (IllegalArgumentException iae) {
            return;
        }
        Assert.fail();
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
         for (int i = 0; i < total; i++) {
           Pelicula e = new Pelicula(generaTituloAleatorio(),
				      generaDirectorAleatorio(),
				      random.nextInt(2015),
				      generaGeneroAleatorio(),
				      random.nextInt(300));
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista<Pelicula> l = bdd.getRegistros();
        int i = 0;
        for (Pelicula e : l) {
            String el = String.format("%s\t%s\t%d\t%s\t%d",
                                      e.getTitulo(),
                                      e.getDirector(),
                                      e.getYear(),
				      e.getGenero(),
                                      e.getDuracion());
            Assert.assertTrue(lineas[i++].equals(el));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Pelicula[] peliculas = new Pelicula[total];
        for (int i = 0; i < total; i++) {
             peliculas[i] = new Pelicula(generaTituloAleatorio(),
					generaDirectorAleatorio(),
					ini + i,
					generaGeneroAleatorio(),
					random.nextInt(300));
            entrada += String.format("%s\t%s\t%d\t%s\t%d\n",
                                     peliculas[i].getTitulo(),
				     peliculas[i].getDirector(),
                                     peliculas[i].getYear(),
                                     peliculas[i].getGenero(),
                                     peliculas[i].getDuracion());
            bdd.agregaRegistro(peliculas[i]);
        }
        contador = 0;
        llamado = false;
        bdd.agregaEscucha((t,e) -> {
                if (t == BaseDeDatos.BASE_LIMPIADA)
                    llamado = true;
                if (t == BaseDeDatos.REGISTRO_AGREGADO)
                    contador++;
            });
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Lista<Pelicula> l = bdd.getRegistros();
        Assert.assertTrue(l.getLongitud() == total);
        int i = 0;
        for (Pelicula e : l)
            Assert.assertTrue(peliculas[i++].equals(e));
        Assert.assertTrue(llamado);
        Assert.assertTrue(contador == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
	    Pelicula e = new Pelicula(generaTituloAleatorio(),
				      generaDirectorAleatorio(),
				      random.nextInt(2015),
				      generaGeneroAleatorio(),
				      random.nextInt(300));
            bdd.agregaRegistro(e);
        }
        llamado = false;
        bdd.agregaEscucha((t,e) -> {
                Assert.assertTrue(t == BaseDeDatos.BASE_LIMPIADA);
                Assert.assertTrue(e == null);
                llamado = true;
            });
        bdd.limpia();
        Assert.assertTrue(llamado);
    }
}
