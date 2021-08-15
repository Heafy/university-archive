import java.util.ArrayList;

public class Dijkstra{

    /**
     * Metodo para mostrar el uso correcto del programa
     */
    private static void uso(){
        System.out.println("Uso: java Dijkstra file");
        System.exit(1);
    }

    public static void main(String[] args) {
        // Revisa que los parametros pasados sean correctos
        if(args.length != 1)
            uso();


        // Crea el lector y obtiene sus vertices y aristas del archivo
        Lector lector = new Lector();
        lector.leerArchivo(args[0]);
        ArrayList<String> vertices = lector.getVertices();
        ArrayList<String> aristas = lector.getAristas();

        Grafica<String> graph = new Grafica<String>();

        for(int i = 0; i < vertices.size(); i++){
            graph.agrega(vertices.get(i));
        }

        for(int i = 0; i < aristas.size(); i++){
            String[] aristaAux = aristas.get(i).split("\\s*,\\s*");
            graph.conecta(aristaAux[0], aristaAux[1], Integer.parseInt(aristaAux[2]));            
        }

        /* Dijkstra */
        Lista<VerticeGrafica<String>> dijkstra;
        String s = "";

        for(int i = 0; i < vertices.size(); i++){
            dijkstra = graph.dijkstra(vertices.get(0), vertices.get(i));
            System.out.println("Dijkstra de " + vertices.get(0) + " a " + vertices.get(i) + ":");
            for (VerticeGrafica<String> v : dijkstra)
                s += v.getElemento() + ", ";
            // Para remover la ultima coma
            System.out.println(s);
        }

    }
}