import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que contiene todos los crucigramas que se pueden mostrar
 */
public class CrucigramaEjemplo {

    Crucigrama getPuzzle(){

        Crucigrama c;
        Random random = new Random();
        String title = "";
        int dimension = 1;
        ArrayList<Pista> horizontalPistas = new ArrayList<Pista>();
        ArrayList<Pista> verticalPistas = new ArrayList<Pista>();
        int numCrucigrama = random.nextInt(3);
        //Numero, x, y, "Pista", "Respuesta"
        switch(numCrucigrama){
            case 0:
                title = "Crucigrama de palabras";
                dimension = 10;
                horizontalPistas.add(new Pista( 1, 0, 4, "Hidalgo de nobleza cualificada", "caballero") );
                verticalPistas.add(  new Pista( 2, 2, 4, "Extremidad, de la mano al hombro. Miembro del cuerpo que va del hombro a la mano", "brazo") );
                verticalPistas.add(  new Pista( 3, 6, 1, "Persona de sexo femenino. Señora, componente humano femenino", "mujer") );
                horizontalPistas.add(new Pista( 4, 2, 6, "Gas atmosférico respirable. Mezcla gaseosa compuesta en su mayor parte por oxígeno y nitrógeno", "aire") );
                horizontalPistas.add(new Pista( 5, 1, 9, "Astro rey del sistema solar", "sol") );
                horizontalPistas.add(new Pista( 6, 4, 1, "Espíritu inmortal. Sustancia inmaterial de los seres vivientes que en el ser humano es considerada como el principio del sentimiento y del pensamiento", "alma") );
                verticalPistas.add(  new Pista( 7, 8, 4, "Órgano de la vista en el hombre y en los animales. Globo ocular", "ojo") );
                horizontalPistas.add(new Pista( 8, 7, 6, "Sonido de habla humana", "voz") );
                verticalPistas.add(  new Pista( 9, 4, 0, "Masa de agua salada que cubre la mayor parte de la superficie terrestre", "mar") );
                verticalPistas.add(  new Pista( 10, 0, 2, "Abertura anterior del tubo digestivo de los animales, situada en la cabeza, que sirve de entrada a la cavidad bucal.", "boca") );
                break;
            case 1:
                title = "Crucigrama de colores";
                dimension = 12;
                horizontalPistas.add(new Pista( 1, 2, 6, "De color como la piel del limón maduro", "amarillo") );
                verticalPistas.add(  new Pista( 2, 8, 4, "Color azul claro, su nombre está relacionado con el cielo", "celeste") );
                horizontalPistas.add(new Pista( 3, 6, 8, "Tiene el mismo nombre que una flor", "rosa") );
                verticalPistas.add(  new Pista( 4, 6, 8, "Encarnado muy vivo. Es el primer color del espectro solar", "rojo") );
                horizontalPistas.add(new Pista( 5, 1, 11, "De color purpura azulado oscuro", "morado") );
                verticalPistas.add(  new Pista( 6, 2, 6, "Del color del cielo sin nubes o de la superficie del mar", "azul") );
                verticalPistas.add(  new Pista( 7, 4, 0, "A este color también se le llama lila", "violeta") );
                horizontalPistas.add(new Pista( 8, 4, 0, "De color semejante al de la hierba fresca, la esmeralda", "verde") );
                horizontalPistas.add(new Pista( 9, 0, 2, "Ausencia de todo color", "negro") );
                verticalPistas.add(  new Pista( 10, 0, 2, "Color entre rojo y amarillo", "naranja") );
                break;
            case 2:
                title = "Crucigrama de animales";
                dimension = 10;
                horizontalPistas.add(new Pista( 1, 2, 5, "Mamífero roedor parecido al conejo, pero más pequeño, con orejas y patas cortas y que habita en América del Sur", "cobaya") );
                verticalPistas.add(  new Pista( 2, 3, 2, "Molusco gasterópodo terrestre, sin concha externa, que cuando se arrastra deja abundante baba", "babosa") );
                verticalPistas.add(  new Pista( 3, 6, 5, "Hembra del caballo", "yegua") );
                horizontalPistas.add(new Pista( 4, 6, 7, "Animal mamífero felino, de piel muy suave, caza ratones y su sonido típico es miau.", "gato") );
                horizontalPistas.add(new Pista( 5, 0, 9, "Arácnido del grupo de los ácaros, parásito de ciertos animales a los que chupa la sangre", "garrapata") );
                verticalPistas.add(  new Pista( 6, 9, 3, "Mamífero roedor anfibio de unos 90 cm de longitud, de los que 30 corresponden a la cola, con pelo castaño muy fino y apreciado en peletería, patas cortas, pies con cinco dedos palmeados y cola aplastada, oval y escamosa", "castor") );
                horizontalPistas.add(new Pista( 7, 1, 2, "Nombre común de diversas especies de reptiles escamosos ofidios de cuerpo cilíndrico y alargado", "vibora") );
                verticalPistas.add(  new Pista( 8, 5, 0, "Papagayo, ave, y más particularmente el que tiene el plumaje con fondo rojo", "loro") );
                verticalPistas.add(  new Pista( 9, 1, 0, "Nombre común de diversas especies de aves. Son famosas por las largas plumas de su bonita cola y los humanos nos los comemos al horno, especialmente en Navidad", "pavo") );
                horizontalPistas.add(new Pista( 10, 1, 0, "Animal vertebrado acuático de respiración branquial y temperatura variable, generalmente ovíparo", "pez") );
                break;
            default:
                System.out.println("Esto nunca deberia de pasar.");
            }

        c = new Crucigrama(title,dimension,horizontalPistas,verticalPistas);

        return c;
    }

}
