package icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;
import java.util.Random;
import org.junit.Test;
import org.junit.Assert;
import icc.Pelicula;

/**
 * Clase para pruebas unitarias de la clase {@link Estudiante}.
 */
public class TestPelicula {

	private Random random;
	private Pelicula pelicula;

	/**
	 * Crea un generador de números aleatorios para cada prueba.
	 */
	public TestPelicula() {
		random = new Random();
	}

	/**
	 * Prueba unitaria para {@link
	 * Pelicula#Pelicula(String, String, int, String, int)}.
	 */
	@Test public void testConstructor() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getTitulo().equals(titulo));
		Assert.assertTrue(pelicula.getDirector().equals(director));
		Assert.assertTrue(pelicula.getYear() == year);
		Assert.assertTrue(pelicula.getGenero().equals(genero));
		Assert.assertTrue(pelicula.getDuracion() == duracion);
	}

	/**
	 * Prueba unitaria para {@link Pelicula#setTitulo} y 
	 *  {@link Pelicula#getTitulo}.
	 */
	@Test public void testSetGetTitulo() {
		int n = random.nextInt();
		String titulo = String.valueOf(n);
		String director = String.valueOf(n);
		int year = random.nextInt(2015);
		String genero = String.valueOf(n);
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getTitulo().equals(titulo));
		String nuevoTitulo = String.valueOf(n + 1);
		pelicula.setTitulo(nuevoTitulo);
		Assert.assertTrue(pelicula.getTitulo().equals(nuevoTitulo));
		Assert.assertFalse(pelicula.getTitulo().equals(titulo));
	}

	/**
	 * Prueba unitaria para {@link Pelicula#setDirector} y 
	 * {@link Pelicula#getDirector}.
	 */
	@Test public void testSetGetDirector() {
		int n = random.nextInt();
		String titulo = String.valueOf(n);
		String director = String.valueOf(n);
		int year = random.nextInt(2015);
		String genero = String.valueOf(n);
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getDirector().equals(director));
		String nuevoDirector = String.valueOf(n + 1);
		pelicula.setDirector(nuevoDirector);
		Assert.assertTrue(pelicula.getDirector().equals(nuevoDirector));
		Assert.assertFalse(pelicula.getDirector().equals(director));
	}

	/**
	 * Prueba unitaria para {@link Pelicula#setYear} y 
	 * {@link Pelicula#getYear}.
	 */
	@Test public void testSetGetYear() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getYear() == year);
		int nuevoYear = year + 50;
		pelicula.setYear(nuevoYear);
		Assert.assertTrue(pelicula.getYear() == nuevoYear);
		Assert.assertFalse(pelicula.getYear() == year);	
	}

	
	/**
	 * Prueba unitaria para {@link Pelicula#setGenero} y 
	 * {@link Pelicula#getGenero}.
	 */
	@Test public void testSetGetGenero() {
		int n = random.nextInt();
		String titulo = String.valueOf(n);
		String director = String.valueOf(n);
		int year = random.nextInt(2015);
		String genero = String.valueOf(n);
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getGenero().equals(genero));
		String nuevoGenero = String.valueOf(n + 1);
		pelicula.setGenero(nuevoGenero);
		Assert.assertTrue(pelicula.getGenero().equals(nuevoGenero));
		Assert.assertFalse(pelicula.getGenero().equals(genero));
	}

	/**
	 * Prueba unitaria para {@link Pelicula#setDuracion} y 
	 * {@link Pelicula#getDuracion}.
	 */
	@Test public void testSetGetDuracion() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.getDuracion() == duracion);
		int nuevoDuracion = duracion + 50;
		pelicula.setDuracion(nuevoDuracion);
		Assert.assertTrue(pelicula.getDuracion() == nuevoDuracion);
		Assert.assertFalse(pelicula.getDuracion() == duracion);	
	}

/**
	 * Prueba unitaria para {@link Pelicula#equals}.
	 */
	@Test public void testEquals() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		Pelicula igual = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertTrue(pelicula.equals(igual));
		titulo = String.valueOf(random.nextInt());
		director = String.valueOf(random.nextInt());
		year = random.nextInt(2015);
		genero = String.valueOf(random.nextInt());
		duracion = random.nextInt(300);
		Pelicula distinto = new Pelicula(titulo, director, year, genero, duracion);
		Assert.assertFalse(pelicula.equals(distinto));
		Assert.assertFalse(pelicula.equals("Una cadena"));
		Assert.assertFalse(pelicula.equals(null));
	}

 	/**
	 * Prueba unitaria para {@link Pelicula#toString}.
	 */
	@Test public void testToString() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		String cadena = String.format("Titulo   : %s\n" +
										"Director : %s\n" + "Año      : %d\n" +
										"Genero   : %s\n" + "Duracion : %d",
										titulo, 
										director, year, 
										genero, duracion);
		Assert.assertTrue(pelicula.toString().equals(cadena));
	}

   /**
	 * Prueba unitaria para {@link Pelicula#guarda}.
	 */
	@Test public void testGuarda() {
		String titulo = String.valueOf(random.nextInt());
		String director = String.valueOf(random.nextInt());
		int year = random.nextInt(2015);
		String genero = String.valueOf(random.nextInt());
		int duracion = random.nextInt(300);
		pelicula = new Pelicula(titulo, director, year, genero, duracion);
		try {
			StringWriter swOut = new StringWriter();
			BufferedWriter out = new BufferedWriter(swOut);
			pelicula.guarda(out);
			out.close();
			String guardado = swOut.toString();
			String s =String.format("%s\t%s\t%d\t%s\t%d\n", 
					titulo, director, year, genero, duracion);
			Assert.assertTrue(guardado.equals(s));
		} catch (IOException ioe) {
			Assert.fail();
		}
	}

 /**
	 * Prueba unitaria para {@link Pelicula#carga}.
	 */
	@Test public void testCarga() {
	pelicula = new Pelicula(null, null, 0, null, 0);
	String titulo = String.valueOf(random.nextInt());
	String director = String.valueOf(random.nextInt());
	int year = random.nextInt(2015);
	String genero = String.valueOf(random.nextInt());
	int duracion = random.nextInt(300);
		String entrada = titulo    + "\t" + director  + "\t" +
		year      + "\t" + genero    + "\t" + duracion  + "\n";
		try {
			StringReader srIn = new StringReader(entrada);
			BufferedReader in = new BufferedReader(srIn);
			Assert.assertTrue(pelicula.carga(in));
			in.close();
			Assert.assertTrue(pelicula.getTitulo().equals(titulo));
			Assert.assertTrue(pelicula.getDirector().equals(director));
			Assert.assertTrue(pelicula.getYear() == year);
			Assert.assertTrue(pelicula.getGenero().equals(genero));
			Assert.assertTrue(pelicula.getDuracion() == duracion);
		} catch (IOException ioe) {
			Assert.fail();
		}
		entrada = "";
		try {
			StringReader srIn = new StringReader(entrada);
			BufferedReader in = new BufferedReader(srIn);
			Assert.assertFalse(pelicula.carga(in));
			in.close();
			Assert.assertTrue(pelicula.getTitulo().equals(titulo));
			Assert.assertTrue(pelicula.getDirector().equals(director));
			Assert.assertTrue(pelicula.getYear() == year);
			Assert.assertTrue(pelicula.getGenero().equals(genero));
			Assert.assertTrue(pelicula.getDuracion() == duracion);
		} catch (IOException ioe) {
			Assert.fail();
		}
		entrada = "a\ta\ta\ta";
		try {
			StringReader srIn = new StringReader(entrada);
			BufferedReader in = new BufferedReader(srIn);
			pelicula.carga(in);
			Assert.fail();
		} catch (IOException ioe) {}
		entrada = "a\ta";
		try {
			StringReader srIn = new StringReader(entrada);
			BufferedReader in = new BufferedReader(srIn);
			pelicula.carga(in);
			Assert.fail();
		} catch (IOException ioe) {}
	}
}
