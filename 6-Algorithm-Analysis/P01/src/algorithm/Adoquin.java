package algorithm;

/**
 * Clase para representar el terreno de los adoquines
 * El terreno es una matriz de caracteres(k x k)
 * @author Jorge Martinez Flores
 */
public class Adoquin {
     //Matriz que representa el terrenoeno
    char[][] terreno;
    
    /**
     * Constructor de la clase.
     * Crea el terreno en forma de matriz
     * @param m Longitud de un lado del terreno
     */
    public Adoquin(int k){
        terreno = new char[k][k];
    }
    
    /**
     * Esta funcion es para rellenar una L en la matriz terreno.
     * Rellena los 3 cuadrantes diferentes al adoquin especial.
     * @param cuadrado numero del cuadrante,puede ser 1, 2, 3 o 4.
     * @param izq limite izquierdo.
     * @param der limite derecho.
     * @param up limite superior.
     * @param down limite inferior.
     */
    public void creaAdoquin(int cuadrado, int izq, int der, int up, int down){
        //Caso base
        if((der-izq)==3){
            switch(cuadrado){
                case 1: 
                    terreno[izq+2][up+1]='A'; terreno[izq+1][up+2]='A'; terreno[izq+2][up+2]='A';
                    terreno[izq+2][up]='B'; terreno[der][up]='B'; terreno[der][up+1]='B';
                    terreno[izq+2][down]='C'; terreno[der][down]='C'; terreno[der][up+2]='C';
                    terreno[izq][up+2]='D'; terreno[izq][down]='D'; terreno[izq+1][down]='D';
                    break;
                case 2: 
                    terreno[izq+1][up+1]='A'; terreno[izq+1][up+2]='A'; terreno[izq+2][up+2]='A';
                    terreno[der][up+2]='E'; terreno[der][down]='E'; terreno[izq+2][down]='E';
                    terreno[izq][up+2]='F'; terreno[izq][down]='F'; terreno[izq+1][down]='F';
                    terreno[izq+1][up]='G'; terreno[izq][up]='G'; terreno[izq][up+1]='G';
                    break;
                case 3: 
                    terreno[izq+1][up+1]='A'; terreno[izq+2][up+1]='A'; terreno[izq+2][up+2]='A';
                    terreno[izq+2][up]='H'; terreno[der][up]='H'; terreno[der][up+1]='H';
                    terreno[der][up+2]='I'; terreno[der][down]='I'; terreno[izq+2][down]='I';
                    terreno[izq][up+1]='J'; terreno[izq][up]='J'; terreno[izq+1][up]='J';
                    break;
                case 4: 
                    terreno[izq+1][up+2]='A'; terreno[izq+1][up+1]='A'; terreno[izq+2][up+1]='A';
                    terreno[izq][up+1]='K'; terreno[izq][up]='K'; terreno[izq+1][up]='K';
                    terreno[izq+2][up]='L'; terreno[der][up]='L'; terreno[der][up+1]='L';
                    terreno[izq][up+2]='M'; terreno[izq][down]='M'; terreno[izq+1][down]='M';
                    break;
            }
        }else{//Casos superiores
            int mitad = (der-izq+1)/2;
            switch(cuadrado){
                case 1: 
                    creaAdoquin(3,izq+mitad,der,up,down-mitad);
                    creaAdoquin(1,izq+(mitad/2),der-(mitad/2),up+(mitad/2),down-(mitad/2));
                    creaAdoquin(1,izq+mitad,der,up+mitad,down);
                    creaAdoquin(2,izq,der-mitad,up+mitad,down);
                    break;
                case 2: 
                    creaAdoquin(4,izq,der-mitad,up,down-mitad);
                    creaAdoquin(2,izq+(mitad/2),der-(mitad/2),up+(mitad/2),down-(mitad/2));
                    creaAdoquin(2,izq,der-mitad,up+mitad,down);
                    creaAdoquin(1,izq+mitad,der,up+mitad,down);
                    break;
                case 3: 
                    creaAdoquin(4,izq,der-mitad,up,down-mitad);
                    creaAdoquin(3,izq+mitad,der,up,down-mitad);
                    creaAdoquin(3,izq+(mitad/2),der-(mitad/2),up+(mitad/2),down-(mitad/2));
                    creaAdoquin(1,izq+mitad,der,up+mitad,down);
                    break;
                case 4: 
                    creaAdoquin(4,izq,der-mitad,up,down-mitad);
                    creaAdoquin(3,izq+mitad,der,up,down-mitad);
                    creaAdoquin(4,izq+(mitad/2),der-(mitad/2),up+(mitad/2),down-(mitad/2));
                    creaAdoquin(2,izq,der-mitad,up+mitad,down);
                    break;
            }
        }
    }
    
    /**
     * Esta función rellena la matriz terreno.
     * @param x columna donde está el cuadro vacío
     * @param y fila donde está el cuadro vacío
     * @param izq límite izquierdo
     * @param der límite derecho
     * @param up límite superior
     * @param down límite inferior
     */
    public void fillTerreno(int x, int y,int izq, int der, int up, int down){
        // Caso base con k = 1
        if((der-izq)==0){
            terreno[0][0]='X';
        }
        // Caso base con k = 2
        if((der-izq)==1){
            if(izq==x && up==y){
                terreno[izq][up]='X'; 
                terreno[der][up]='Y'; 
                terreno[izq][down]='Y'; 
                terreno[der][down]='Y';
            }
            if(der==x && up==y){
                terreno[izq][up]='Y'; 
                terreno[der][up]='X'; 
                terreno[izq][down]='Y'; 
                terreno[der][down]='Y';
            }
            if(izq==x && down==y){
                terreno[izq][up]='Y'; 
                terreno[der][up]='Y'; 
                terreno[izq][down]='X'; 
                terreno[der][down]='Y';
            }
            if(der==x && down==y){
                terreno[izq][up]='Y'; 
                terreno[der][up]='Y'; 
                terreno[izq][down]='Y'; 
                terreno[der][down]='X';
            }
        }
        if((der-izq)>1){
            int mitad = (der-izq+1)/2;
            // Cuadrante 1
            if(x<(izq+mitad) && y<(up+mitad)){
                fillTerreno(x,y,izq,der-mitad,up,down-mitad);
                creaAdoquin(1,izq,der,up,down);
            }
            // Cuadrante 2
            if(x>=(izq+mitad) && y<(up+mitad)){
                fillTerreno(x,y,izq+mitad,der,up,down-mitad);
                creaAdoquin(2,izq,der,up,down);
            }
            // Cuadrante 3
            if(x<(izq+mitad) && y>=(up+mitad)){
                fillTerreno(x,y,izq,der-mitad,up+mitad,down);
                creaAdoquin(3,izq,der,up,down);
            }
            // Cuadrante 4
            if(x>=(izq+mitad) && y>=(up+mitad)){
                fillTerreno(x,y,izq+mitad,der,up+mitad,down);
                creaAdoquin(4,izq,der,up,down);
            }
        }
    }
}
