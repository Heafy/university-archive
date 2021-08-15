import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {

    static final int MAX = 1000;  //maximo numero de vértices

    ///UNION-FIND
    static int padre[] = new int[ MAX ];  //Este arreglo contiene el padre del i-esimo nodo

    // Crea el conjunto
    static void MakeSet( int n ){
        for( int i = 1 ; i <= n ; ++i ) padre[ i ] = i;
    }

    // Encontrar la raiz
    static int Find( int x ){
        return ( x == padre[ x ] ) ? x : ( padre[ x ] = Find( padre[ x ] ) );
    }

    // Union de dos conjuntos
    static void Union( int x , int y ){
        padre[ Find( x ) ] = Find( y );
    }

    // Para saber si están en el mismo conjunto
    static boolean sameComponent( int x , int y ){
        if( Find( x ) == Find( y ) ) return true;
        return false;
    }

    // Vertices
    static int V;
    // Aristas
    static int E; 
    
    /**
     * Class para representar aristas
     */
    static class Arista implements Comparator<Arista>{
        // Vertice origen
        int origen;
        //Vértice destino
        int destino;
        //Peso de la arista    
        int peso;  

        public Arista(){}
        
        /**
         * Metodo para comparar en base a su peso
         * @param e1
         * @param e2 
         * @return el comparador en base a su peso
         */
        @Override
        public int compare(Arista e1 , Arista e2 ) {
            return e1.peso - e2.peso;
        }
    };
    
    static Arista arista[] = new Arista[ MAX ];      //Arreglo de aristas para el uso en kruskal
    static Arista MST[] = new Arista[ MAX ];     //Arreglo de aristas del MST encontrado

    static void KruskalMST(){
        int origen , destino;
        //Peso del MST
        int total = 0;     
        //Numero de Aristas del MST     
        int numAristas = 0;     
        
        MakeSet( V );           //Inicializamos cada componente
        Arrays.sort( arista , 0 , E , new Arista() );

        //Recorremos las aristas ya ordenadas por peso
        for( int i = 0 ; i < E ; ++i ){     
            origen = arista[ i ].origen;
            destino = arista[ i ].destino;

            //Verificamos si estan o no en la misma componente conexa
            if( !sameComponent( origen , destino ) ){
                MST[ numAristas++ ] = arista[ i ];
                //Union de ambas
                Union( origen , destino );  
            }
        }
        if( V - 1 != numAristas ){
            System.out.println("No existe MST valido.");
            return;
        }
        System.out.println( "MST: ");
        for( int i = 0 ; i < numAristas ; ++i )
            System.out.printf("%d, %d\n" , MST[ i ].origen , MST[ i ].destino );
    }

    /**
     * Metodo para ejecutar el algoritmo de Kruskal desde otra clase.
     * @param vertices La lista de vertices
     * @param aristas La lista de aristas
     */
    public void hazKruskal(ArrayList<String> vertices, ArrayList<String> aristas){
        V = vertices.size();
        E = aristas.size();

        for(int i = 0; i < aristas.size(); i++){
            arista[i]= new Arista();
            String[] aristaAux = aristas.get(i).split("\\s*,\\s*");
            arista[ i ].origen = Integer.parseInt(aristaAux[0]);
            arista[ i ].destino = Integer.parseInt(aristaAux[1]);
            arista[ i ].peso = Integer.parseInt(aristaAux[2]);
        }

        KruskalMST();
    }
}