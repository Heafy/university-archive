/**
 * Clase para una agenda en Java
 * @author Jorge Martinez Flores
 */
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Agenda{

	protected Lista<Persona> agenda;

	public Agenda(){
		agenda = new Lista<Persona>();
	}

    /**
     * Crea una persona sin información relevante.
     * @return una persona sin información relevante.
     */
    protected Persona creaRegistro() {
        return new Persona(null, null, null,null);
    }

	/**
     * Regresa una lista con los registros en la agenda
     * Modificar esta lista no cambia a la información en la
     * agenda original.
     * @return una lista con los registros en la agenda.
     */
    public Lista<Persona> getRegistros() {
	   return agenda.copia();
    }

	/**
     * Agrega la persona recibida a la agenda.
     * @param persona La persona que hay que agregar a la agenda.
     */
    public void agregaRegistro(Persona persona){
    	agenda.agregaFinal(persona);
    }

    /**
     * Elimina la persona recibida a la agenda.
     * @param persona La persona que hay que eliminar de la agenda.
     */
    public void eliminaRegistro(Persona persona) {
        agenda.elimina(persona);
    }
    
    /**
    * Elimina y vacia todos los contactos de la agenda
    */
    public void vaciaAgenda(){
        agenda.limpia();
    }

    
    /**
     * Busca personas por su nombre para eliminarlas.
     * @param texto el nombre a buscar.
     * @return true Si se elimina a la persona buscada.
     */
    public boolean buscaElimina(String texto){
        Lista<Persona> l = new Lista<Persona>();
        IteradorLista<Persona> it = agenda.iteradorLista();
        String valor = "";
        for(Persona t: agenda){
            Persona e = it.next();
            valor = e.getNombre();
            if(valor.indexOf(texto)!= -1){
                eliminaRegistro(t);
                return true;
            }
        }
        return false;
    }

    /**
     * Busca personas por su nombre para editarlas.
     * @param texto el nombre a buscar.
     * @return la Persona que se va a editar.
     */
    public Persona buscaEdita(String texto){
        Lista<Persona> l = new Lista<Persona>();
        IteradorLista<Persona> it = agenda.iteradorLista();
        String valor = "";
        for(Persona t: agenda){
            Persona e = it.next();
            valor = e.getNombre();
            if(valor.indexOf(texto)!= -1){
                return t;
            }
        }
        return null;
    }

    /**
     * Busca personas por su nombre.
     * @param texto el nombre a buscar.
     * @return una lista con las Personas tales que en el campo
     *         especificado contienen el texto recibido.
     */
    public Lista<Persona> buscaPersonas(String texto) {
		Lista<Persona> l = new Lista<Persona>();
		IteradorLista<Persona> it = agenda.iteradorLista();
		String valor = "";
		for(Persona t: agenda){
		    Persona e = it.next();
		    valor = e.getNombre();
		    if(valor.indexOf(texto)!= -1)
				l.agregaFinal(e);
		}//for,each
		return l;
	}

    /**
     * Regresa una representación en cadena de la persona.
     * @return una representación en cadena de la persona.
     */
    @Override public String toString(){
        return agenda.toString();
    }

    /**
     * Guarda todos los registros de la agenda en la salida
     * recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        IteradorLista<Persona> it = agenda.iteradorLista();
        for(Persona t: agenda){
            Persona r =it.next();
            r.guarda(out);
        }
    }

    /**
     * Guarda los registros de la entrada recibida en la agenda
     * Si antes de llamar el método había registros en la
     * base de datos, estos son eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        agenda.limpia();
        boolean v;
        do{
            Persona r = creaRegistro();
            v = r.carga(in);
            if(v)
            agenda.agregaFinal(r);
        }while(v);
    }
    
} //Class Agenda