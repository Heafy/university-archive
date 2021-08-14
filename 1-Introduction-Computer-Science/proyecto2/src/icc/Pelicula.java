package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar peliculas. Una pelicula tiene titulo,
 * director, año de grabacion, genero y duracion en minutos. 
 * La clase implementa {@link Registro}, por lo que puede cargarse
 * y guardarse utilizando objetos de las clases 
 * {@link BufferedReader} y {@link BufferedWriter} como entrada y 
 * salida respectivamente.
 */
public class Pelicula implements Registro {

    /* Titulo de la pelicula*/
    private StringProperty titulo;
    /* Director de la pelicula*/
    private StringProperty director;
    /* Año de grabacion*/
    private IntegerProperty year;
    /* Genero de la pelicula*/
    private StringProperty genero;
    /* Duracion en minutos de la pelicula*/
    private IntegerProperty duracion;

    /**
     * Construye una pelicula con todas sus propiedades.
     * @param titulo el titulo de la pelicula.
     * @param director el nombre del director.
     * @param year Año en el que se filmo.
     * @param genero el genero de la pelicula.
     * @param duracion la duracion de la pelicula.
     */
    public Pelicula(String titulo, String director, int year, 
                    String genero, int duracion){
        this.titulo = new SimpleStringProperty(titulo);
        this.director = new SimpleStringProperty(director);
        this.year = new SimpleIntegerProperty(year);
        this.genero = new SimpleStringProperty(genero);
        this.duracion = new SimpleIntegerProperty(duracion);
    }

    /**
     *Constructor sin parametros.
     */
    public Pelicula(){
	   this("", "", 0, "", 0);
    }

    // TITULO ----------------------------

    /**
     * Regresa el titulo de la pelicula.
     * @return el titulo de la pelicula.
     */
    public String getTitulo(){
	   return titulo.get();
    }

    /**
     * Define el titulo de la pelicula.
     * @param titulo el nuevo titulo de la pelicula.
     */
    public void setTitulo(String titulo){
	   this.titulo.set(titulo);
    }

    /**
     * Regresa la propiedad del titulo.
     * @return la propiedad del titulo.
     */
    public StringProperty getTituloProperty(){
	   return this.titulo;
    }

    // DIRECTOR --------------------------

    /**
     * Regresa el director de la pelicula.
     * @return el director de la pelicula.
     */
    public String getDirector(){
	   return director.get();
    }

    /**
     * Define el director de la pelicula.
     * @param director el nuevo director de la pelicula.
     */
    public void setDirector(String director){
	   this.director.set(director);
    }

    /**
     * Regresa la propiedad del director.
     * @return la propiedad del director.
     */
    public StringProperty getDirectorProperty(){
	   return this.director;
    }

    // AÑO -------------------------------

    /**
     * Regresa el año de grabacion de la pelicula.
     * @return el año de grabacion  de la pelicula.
     */
    public int getYear(){
	   return year.get();
    }

    /**
     * Define el año de grabacion de la pelicula.
     * @param year el nuevo año de grabacion de la pelicula.
     */
    public void setYear(int year){
	   this.year.set(year);
    }

    /**
     * Regresa la propiedad del año.
     * @return la propiedad del año.
     */
    public IntegerProperty getYearProperty(){
	   return this.year;
    }

    // GENERO ----------------------------

 /**
     * Regresa el genero de la pelicula.
     * @return el genero de la pelicula.
     */
    public String getGenero(){
	   return genero.get();
    }

    /**
     * Define el genero de la pelicula.
     * @param genero el nuevo genero de la pelicula.
     */
    public void setGenero(String genero){
	   this.genero.set(genero);
    }

    /**
     * Regresa la propiedad del genero.
     * @return la propiedad del genero.
     */
    public StringProperty getGeneroProperty(){
	   return this.genero;
    }

    // DURACION --------------------------

    /**
     * Regresa la duracion de la pelicula.
     * @return la duracion de la pelicula.
     */
    public int getDuracion(){
	   return duracion.get();
    }

    /**
     * Define la duracion de la pelicula.
     * @param duracion la nueva duracion de la pelicula.
     */
    public void setDuracion(int duracion){
	   this.duracion.set(duracion);
    }

    /**
     * Regresa la propiedad de la duracion.
     * @return la propiedad de la duacion.
     */
    public IntegerProperty getDuracionProperty(){
	   return this.duracion;
    }

    /**
     * Nos dice si el objeto recibido es una pelicula igual al que
     * manda llamar el método.
     * @param o el objeto con el que la pelicula se comparará.
     * @return <tt>true</tt> si el objeto o es una pelicula con las
     *         mismas propiedades que el objeto que manda llamar al
     *         método, <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o){
        if(o == null)
            return false;
        if(!(o instanceof Pelicula))
            return false;
        Pelicula m = (Pelicula)o;
        if(titulo.get().equals(m.getTitulo()) &&
            director.get().equals(m.getDirector()) &&
            year.get() == m.getYear() &&
            genero.get().equals(m.getGenero()) &&
            duracion.get() == m.getDuracion())
            return true;
        return false;
    }

   /**
     * Regresa una representación en cadena de la pelicula.
     * @return una representación en cadena de la pelicula.
     */
    @Override public String toString() {
	String cadena = String.format("Titulo   : %s\n" +
                                    "Director : %s\n" +
                                    "Año      : %d\n" +
                                    "Genero   : %s\n" +
				                    "Duracion : %d",
                        titulo.get(), 
				        director.get(), 
				        year.get(), 
				        genero.get(), 
				        duracion.get());
        return cadena;
    }

    /**
     * Guarda la pelicula  en la salida recibida.
     * @param out la salida dónde hay que guardar la pelicula.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    /*@Override*/public void guarda(BufferedWriter out) 
	throws IOException {
	out.write(String.format("%s\t%s\t%d\t%s\t%d\n", 
				titulo.get(),
				director.get(),
				year.get(),
				genero.get(),
				duracion.get()));
    }

    /**
     * Carga la pelicula de la entrada recibida.
     * @param in la entrada de dónde hay que cargar la pelicula.
     * @return <tt>true</tt> si el método carga una pelicula
     *         válida, <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    /*@Override*/ public boolean carga(BufferedReader in) 
	throws IOException {
	String l = in.readLine();
	if(l == null)
	    return false;
	l = l.trim();
	if(l.equals(""))
	   return false;
	String [] t= l.split("\t");
	if(t.length!=5)
	    throw new IOException("Formato invalido");
	setTitulo(t[0]);
	setDirector(t[1]);
	setGenero(t[3]);
	try{
	    setYear(Integer.parseInt(t[2]));
	    setDuracion(Integer.parseInt(t[4]));
	}catch(NumberFormatException n){
	    throw new IOException("Numero de formato invalido");
	}
	return true;
    }
}
