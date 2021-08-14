package icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede cargarse y guardarse utilizando objetos de las clases {@link
 * BufferedReader} y {@link BufferedWriter} como entrada y salida
 * respectivamente.
 */
public class Estudiante implements Registro {

    /* Nombre del estudiante. */
    private StringProperty nombre;
    /* Número de cuenta. */
    private IntegerProperty cuenta;
    /* Pormedio del estudiante. */
    private DoubleProperty promedio;
    /* Edad del estudiante.*/
    private IntegerProperty edad;

    /**
     * Construye un estudiante con todas sus propiedades.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre = new SimpleStringProperty(nombre);
        // Aquí va su código.
	this.cuenta = new SimpleIntegerProperty(cuenta);
	this.promedio = new SimpleDoubleProperty(promedio);
	this.edad = new SimpleIntegerProperty(edad);
    }

    /**
     * Constructor sin parámetros.
     */
    public Estudiante() {
        this("", 0, 0, 0);
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty getNombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
	return cuenta.get();
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
	this.cuenta.set(cuenta);
    }

    /**
     * Regresa la propiedad del número de cuenta.
     * @return la propiedad del número de cuenta.
     */
    public IntegerProperty getCuentaProperty() {
        // Aquí va su código.
	return this.cuenta;	
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
	return promedio.get();
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
	this.promedio.set(promedio);
    }

    /**
     * Regresa la propiedad del promedio.
     * @return la propiedad del promedio.
     */
    public DoubleProperty getPromedioProperty() {
        // Aquí va su código.
	return promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
	return edad.get();
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
	 this.edad.set(edad);
    }

    /**
     * Regresa la propiedad de la edad.
     * @return la propiedad de la edad.
     */
    public IntegerProperty getEdadProperty() {
        // Aquí va su código.
	return edad;
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que
     * manda llamar el método.
     * @param o el objeto con el que el estudiante se comparará.
     * @return <tt>true</tt> si el objeto o es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al
     *         método, <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object o) {
        // Aquí va su código.
	if (o == null)
            return false;
        if (!(o instanceof Estudiante))
            return false;
	Estudiante m = (Estudiante)o;
	if(nombre.get().equals(m.getNombre()) &&
	   cuenta.get() == m.getCuenta() &&
	   promedio.get() == m.getPromedio() &&
	   edad.get() == m.getEdad())
	    return true;
	return false;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
	  String cadena = String.format("Nombre   : %s\n" +
                                      "Cuenta   : %d\n" +
                                      "Promedio : %2.2f\n" +
                                      "Edad     : %d",
					nombre.get(),
					cuenta.get(),
					promedio.get(),
					edad.get());
	  return cadena;
    }

    /**
     * Guarda al estudiante en la salida recibida.
     * @param out la salida dónde hay que guardar al estudiante.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public void guarda(BufferedWriter out) throws IOException {
        // Aquí va su código.
	out.write(String.format("%s\t%d\t%2.2f\t%d\n",
				nombre.get(),
				cuenta.get(),
				promedio.get(),
				edad.get()));
    }
    /**
     * Carga al estudiante de la entrada recibida.
     * @param in la entrada de dónde hay que cargar al estudiante.
     * @return <tt>true</tt> si el método carga un estudiante
     *         válido, <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    @Override public boolean carga(BufferedReader in) throws IOException {
        // Aquí va su código.
	String l = in.readLine();
	if(l == null)
	    return false;
	l = l.trim();
	if(l.equals(""))
	   return false;
	String [] t= l.split("\t");
	if(t.length!=4)
	    throw new IOException("Formato invalido");
	setNombre(t[0]);
	try{
	    setCuenta(Integer.parseInt(t[1]));
	    setPromedio(Double.parseDouble(t[2]));
	    setEdad(Integer.parseInt(t[3]));
	}catch(NumberFormatException n){
	    throw new IOException("Numero de formato invalido");
	}
	return true;
	}
}
