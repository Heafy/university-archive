import java.util.HashMap;
import java.util.LinkedList;

public class Transporte{

    private static Transporte transporte;
    
    public static Transporte getInstance(){
		if(transporte == null){
		    transporte = new Transporte();
		}
		return transporte;
    }

    private HashMap<String, LinkedList<Mensaje>> map;
    
    public Transporte(){
		map = new HashMap<>();
    }

    public boolean enviar(Mensaje m, String id){
		boolean status = false;
		Mensaje clon = new Mensaje(m.getOrigen(), m.getDestino(), m.getContenido());
		clon.setSiguiente(id);
		LinkedList<Mensaje> lista = null;
		if(map.get(id)==null){
		    lista = new LinkedList<>();
		}else{
		    lista = map.get(id);
		}
		if(!lista.contains(clon)){
		    lista.add(clon);
		}
		map.put(id, lista);
		status = true;
		return status;
	    }

	    public Mensaje recibir(String id){
		Mensaje m = null;
		if(map.get(id)!=null && !map.get(id).isEmpty()){
		    m = map.get(id).removeFirst();
		}
		return m;
    }
}
