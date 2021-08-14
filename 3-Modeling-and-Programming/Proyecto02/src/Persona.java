/**
* Programa para crear objetos tipo Persona
* Esas personas iran almacenadas en una agenda
* Una persona tiene:
* -Nombre completo
* -Telefono de casa
* -Telefono celular
* -Correo electrónico
* @author: Jorge Martinez Flores
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Persona{

	/* El nombre de la persona. */
	private String nombre;
	/* El telefono de casa */
	private String telefonoCasa;
	/* El telefono celular. */
	private String telefonoCelular;
	/* El correo electronico. */
	private String email;

	/**
	* Constructor único.
	* @param nombre El nombre de la persona.
	* @param telefonoCasa El telefono fijo de la persona.
	* @param telefonoCelular El telefono celular de la persona.
	* @param email El correo electronico de la persona.
	*/
	public Persona(String nombre, String telefonoCasa, 
				   String telefonoCelular, String email){
		this.nombre = nombre;
		this.telefonoCasa = telefonoCasa;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
	}

	/** 
	* Regresa el nombre de la persona.
	* @return El nombre de la persona.
	*/
	public String getNombre(){
		return nombre;
	}

	/**
	* Define el nombre de la persona.
	* @param nombre El nuevo nombre de la persona.
	*/
	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	/**
	* Regresa el telefono de casa de la persona.
	* @return El telefono de casa de la persona.
	*/
	public String getTelefonoCasa(){
		return telefonoCasa;
	}

	/**
	* Define el telefono fijo de la persona.
	* @param nombre El nuevo telefono fijo de la persona.
	*/
	public void setTelefonoCasa(String telefonoCasa){
		this.telefonoCasa = telefonoCasa;
	}

	/**
	* Regresa el telefono celular de la persona.
	* @return El telefono celular de la persona.
	*/
	public String getTelefonoCelular(){
		return telefonoCelular;
	}

	/**
	* Define el telefono celular de la persona.
	* @param nombre El nuevo telefono celular de la persona.
	*/
	public void setTelefonoCelular(String telefonoCelular){
		this.telefonoCelular = telefonoCelular;
	}

	/**
	* Regresa el correo electronico de la persona.
	* @return El correo electronico de la persona.
	*/
	public String getEmail(){
		return email;
	}

	/**
	* Define el correo electrónico de la persona.
	* @param nombre El nuevo correo electronico de la persona.
	*/
	public void setEmail(String email){
		this.email = email;
	}

	 /**
     * Regresa una representación en cadena de la persona.
     * @return una representación en cadena de la persona.
     */
    @Override public String toString() {
	return String.format("Nombre    : %s\n" +
                                      "Telefono  : %s\n" +
                                      "Celular   : %s\n" +
                                      "Email     : %s",
                                      nombre, telefonoCasa, telefonoCelular, email);
    }

    /**
     * Guarda a la persona en la salida recibida.
     * @param out la salida dónde hay que guardar a la persona.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    public void guarda(BufferedWriter out) throws IOException {
		out.write(String.format("%s\t%s\t%s\t%s\n", nombre, telefonoCasa, telefonoCelular, email));
    }

    /**
     * Carga a la persona de la entrada recibida.
     * @param in la entrada de dónde hay que cargar a la persona.
     * @return <tt>true</tt> si el método carga una persona
     *         válido, <tt>false</tt> en otro caso.
     * @throws IOException si un error de entrada/salida ocurre.
     */
    public boolean carga(BufferedReader in) throws IOException {
		String l = in.readLine();
		if(l == null)
		    return false;
		l = l.trim();
		if(l.equals(""))
		   return false;
		String [] t= l.split("\t");
		if(t.length!=4)
		    throw new IOException("Formato invalido");
		nombre = t[0];
		telefonoCasa =t[1];
		telefonoCelular = t[2];
		email = t[3];
		return true;
	}



    /*
    * Main de Prueba
    public static void main(String[] args) {
    	Persona persona = new Persona("Jorge", "26524200", "5591634537", "jmtz.2878@gmail.com");
    	System.out.println(persona.toString());
    }
    */
} 