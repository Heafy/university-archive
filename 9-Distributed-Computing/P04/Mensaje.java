public class Mensaje{

    public static final int DEFAULT_TTL = 100;
    
    private NodoD origen;
    private NodoD destino;
    private String siguiente;
    private String contenido;
    private int ttl;

    public Mensaje(NodoD origen, NodoD destino, String mensaje){
    	this.origen = origen;
    	this.destino = destino;
    	this.ttl = DEFAULT_TTL;
    	this.contenido = mensaje;
    }

    public NodoD getOrigen(){
	   return origen;
    }

    public NodoD getDestino(){
    	return destino;
    }

    public String getContenido(){
	   return contenido;
    }

    public void setSiguiente(String siguiente){
	   this.siguiente = siguiente;
    }

    public String getSiguiente(){
	   return siguiente;
    }

    public void decrementaTTL(){
    	if(ttl>0){
    	    this.ttl--;
    	}
    }

    public int getTTL(){
	   return ttl;
    }

    public boolean equals(Object o){
    	boolean status = false;
    	if(o instanceof Mensaje){
    	    Mensaje tmp = (Mensaje)o;
    	    status = tmp.origen.equals(this.origen) && tmp.destino.equals(this.destino) && tmp.contenido.equals(this.contenido);
    	}
    	return status;
    }

}
