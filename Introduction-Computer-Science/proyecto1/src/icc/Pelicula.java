package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
    private String titulo;
    /* Director de la pelicula*/
    private String director;
    /* Año de grabacion*/
    private int year;
    /* Genero de la pelicula*/
    private String genero;
    /* Duracion en minutos de la pelicula*/
    private int duracion;

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
    	this.titulo = titulo;
    	this.director = director;
    	this.year = year;
    	this.genero = genero;
    	this.duracion = duracion;
    }

    /**
     * Regresa el titulo de la pelicula.
     * @return el titulo de la pelicula.
     */
    public String getTitulo(){
	   return titulo;
    }

    /**
     * Define el titulo de la pelicula.
     * @param titulo el nuevo titulo de la pelicula.
     */
    public void setTitulo(String titulo){
	   this.titulo = titulo;
    }

    /**
     * Regresa el director de la pelicula.
     * @return el director de la pelicula.
     */
    public String getDirector(){
	   return director;
    }

    /**
     * Define el director de la pelicula.
     * @param director el nuevo director de la pelicula.
     */
    public void setDirector(String director){
	   this.director = director;
    }

    /**
     * Regresa el año en el que se filmo la pelicula.
     * @return el año en el que se filmo la pelicula.
     */
    public int getYear(){
	   return year;
    }

    /**
     * Define el año en el que se filmo la pelicula.
     * @param year el nuevo año en el que se filmo la pelicula.
     */
    public void setYear(int year){
	   this.year = year;
    }
    
    /**
     * Regresa el genero de la pelicula.
     * @return el genero de la pelicula.
     */
    public String getGenero(){
	   return genero;
    }

    /**
     * Define el genero de la pelicula
     * @param genero el genero de la pelicula.
     */
    public void setGenero(String genero){
	   this.genero = genero;
    }

    /**
     * Regresa la duracion de la pelicula.
     * @return la duracion de la pelicula.
     */
    public int getDuracion(){
	   return duracion;
    }

    /**
     * Define la duracion de la pelicula.
     * @param duracion la duracion de la pelicula.
     */
    public void setDuracion(int duracion){
	   this.duracion = duracion;
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
        if(titulo.equals(m.getTitulo()) && director.equals(m.getDirector()) && 
            year == m.getYear() && genero.equals(m.getGenero()) && duracion == m.getDuracion())
            return true;
        return false;
    }

    /**
     * Regresa una representación en cadena de la pelicula.
     * @return una representación en cadena de la pelicula.
     */
    @Override public String toString() {
	   return String.format("Titulo   : %s\n" + "Director : %s\n" +
                            "Año      : %d\n" + "Genero   : %s\n" + "Duracion : %d",
                            titulo, director, year, genero, duracion);
    }

    /**
     * Guarda la pelicula  en la salida recibida.
     * @param out la salida dónde hay que guardar la pelicula.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    /*@Override*/public void guarda(BufferedWriter out) 
	throws IOException {
    	out.write(String.format("%s\t%s\t%d\t%s\t%d\n", 
    			 titulo, director, year, genero, duracion));
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
	titulo = t[0];
	director = t[1];
	genero = t[3];
	try{
	    year = Integer.parseInt(t[2]);
	    duracion = Integer.parseInt(t[4]);
	}catch(NumberFormatException n){
	    throw new IOException("Numero de formato invalido");
	}
	return true;
    }
}
