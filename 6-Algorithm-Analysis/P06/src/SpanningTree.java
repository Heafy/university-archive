import java.util.ArrayList;

public class SpanningTree{

    /**
     * Metodo para mostrar el uso correcto del programa
     */
    private static void uso(){
        System.out.println("Uso: java ConnectedComponents file prim/kruskal");
        System.exit(1);
    }

    public static void main(String[] args) {
        // Revisa que los parametros pasados sean correctos
        if(args.length != 2)
            uso();

        // Crea el lector y obtiene sus vertices y aristas del archivo
        Lector lector = new Lector();
        lector.leerArchivo(args[0]);
        String opcion = args[1];
        ArrayList<String> vertices = lector.getVertices();
        ArrayList<String> aristas = lector.getAristas();

        switch(opcion){
            case "kruskal":
                Kruskal kruskal = new Kruskal();
                kruskal.hazKruskal(vertices, aristas);
                break;
            case "prim":
                System.out.println("Algoritmo de Prim no implementado \n :(");
        }
    }
}