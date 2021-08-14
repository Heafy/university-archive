package icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import icc.BaseDeDatos;
import icc.BaseDeDatosEstudiantes;
import icc.Estudiante;
import icc.Lista;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link
 * BaseDeDatosEstudiantes}.
 */
public class TestBaseDeDatosEstudiantes {

    private static String[] NOMBRES = {
        "Aaron", "Benito", "Carlos", "Daniel", "Ernesto",
    };
    private static String[] APELLIDOS = {
        "Zapata", "Yacaxtle", "Xola", "Wellington", "Valdés",
    };    

    private Random random;
    private BaseDeDatosEstudiantes bdd;
    private int total;
    private boolean llamado;
    private int contador;

    /**
     * Crea un generador de números aleatorios para cada prueba y
     * una base de datos de estudiantes.
     */
    public TestBaseDeDatosEstudiantes() {
        random = new Random();
        bdd = new BaseDeDatosEstudiantes();
        total = 1 + random.nextInt(100);
    }

    /* Genera un nombre aleatorio. */
    private String generaNombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        int ap = random.nextInt(APELLIDOS.length);
        int am = random.nextInt(APELLIDOS.length);
        return NOMBRES[n] + " " + APELLIDOS[ap] + " " + APELLIDOS[am];
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosEstudiantes#BaseDeDatosEstudiantes}.
     */
    @Test public void testConstructor() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testNumeroDeRegistros() {
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Estudiante[] estudiantes = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            estudiantes[i] = new Estudiante(generaNombreAleatorio(),
                                            random.nextInt(1000000),
                                            random.nextDouble() * 10,
                                            18 + random.nextInt(10));
            bdd.agregaRegistro(estudiantes[i]);
        }
        int i = 0;
        Lista<Estudiante> l = bdd.getRegistros();
        for (Estudiante e : l)
            estudiantes[i++].equals(e);
        l.elimina(estudiantes[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
            Lista<Estudiante> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
        llamado = false;
        bdd.agregaEscucha((t,e) -> {
                Assert.assertTrue(t == BaseDeDatos.REGISTRO_AGREGADO);
                Assert.assertTrue(e.getCuenta() == 1);
                llamado = true;
            });
        bdd.agregaRegistro(new Estudiante("A", 1, 1, 1));
        Assert.assertTrue(llamado);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          ini + i,
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Estudiante e = bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
        llamado = false;
        Estudiante estudiante = new Estudiante("A", 1, 1, 1);
        bdd.agregaRegistro(estudiante);
        bdd.agregaEscucha((t,e) -> {
                Assert.assertTrue(t == BaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(e.getCuenta() == 1);
                llamado = true;
            });
        bdd.eliminaRegistro(estudiante);
        Assert.assertTrue(llamado);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosEstudiantes#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Estudiante estudiante = new Estudiante(generaNombreAleatorio(),
                                                   ini + i,
                                                   random.nextDouble() * 10,
                                                   18 + random.nextInt(10));
            bdd.agregaRegistro(estudiante);
        }

        String busqueda = String.valueOf(ini).substring(0, 2);
        Lista<Estudiante> l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        for (Estudiante e : l) {
            String c = String.valueOf(e.getCuenta());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertFalse(l.getLongitud() != 0);

        busqueda = String.valueOf(ini/1000);
        l = bdd.buscaRegistros("cuenta", busqueda);
        Assert.assertTrue(l.getLongitud() == total);
        for (Estudiante e : l) {
            String c = String.valueOf(e.getCuenta());
            Assert.assertTrue(c.indexOf(busqueda) != -1);
        }
        busqueda = String.valueOf(9999999);
        l = bdd.buscaRegistros("cuenta", busqueda);
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
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
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
        Lista<Estudiante> l = bdd.getRegistros();
        int i = 0;
        for (Estudiante e : l) {
            String el = String.format("%s\t%d\t%2.2f\t%d",
                                      e.getNombre(),
                                      e.getCuenta(),
                                      e.getPromedio(),
                                      e.getEdad());
            Assert.assertTrue(lineas[i++].equals(el));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Estudiante[] estudiantes = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            estudiantes[i] = new Estudiante(generaNombreAleatorio(),
                                            ini + i,
                                            /* Estúpida precisión. */
                                            random.nextInt(100) / 10.0,
                                            18 + random.nextInt(10));
            entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                     estudiantes[i].getNombre(),
                                     estudiantes[i].getCuenta(),
                                     estudiantes[i].getPromedio(),
                                     estudiantes[i].getEdad());
            bdd.agregaRegistro(estudiantes[i]);
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
        Lista<Estudiante> l = bdd.getRegistros();
        Assert.assertTrue(l.getLongitud() == total);
        int i = 0;
        for (Estudiante e : l)
            Assert.assertTrue(estudiantes[i++].equals(e));
        Assert.assertTrue(llamado);
        Assert.assertTrue(contador == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Estudiante e = new Estudiante(generaNombreAleatorio(),
                                          random.nextInt(1000000),
                                          random.nextDouble() * 10,
                                          18 + random.nextInt(10));
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
