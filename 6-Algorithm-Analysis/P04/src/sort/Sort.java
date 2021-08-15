package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class Sort{

    int[] numeros;

    public Sort(String archivo, int framerate, String metodo){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    JFrame frame = new JFrame("Ordenamientos");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.add(new Contenedor(archivo, framerate, metodo));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }catch(Exception e){
                    System.out.println("\t:(");
                }
            }
        });
    }

    public class Contenedor extends JPanel{

        private JLabel etiqueta;

        public Contenedor(String archivo, int framerate, String metodo){
            setLayout(new BorderLayout());
            etiqueta = new JLabel(new ImageIcon(createImage(archivo)));
            add(etiqueta);
            JButton botonOrdenar = new JButton("Ordenar");
            add(botonOrdenar, BorderLayout.SOUTH);
            botonOrdenar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    BufferedImage imagen = (BufferedImage) ((ImageIcon) etiqueta.getIcon()).getImage();
                    new UpdateWorker(imagen, etiqueta, archivo, framerate, metodo).execute();
                }
            });
        }

        public BufferedImage createImage(String archivo){
            BufferedImage imagen = null;
            try{
                imagen = ImageIO.read(new File("resource/"+archivo));
                ataqueHackerman(imagen);
                Graphics2D g = imagen.createGraphics();
                g.dispose();
            }catch(Exception e){
                System.err.println("(-)\tAsegurate de estar en el directorio 'src'");
                System.err.println("\ty de haber escrito bien el nombre de imagen (la cual debe estar en la carpeta resource)");
            }
            return imagen;
        }

        public void ataqueHackerman(BufferedImage imagen){
            int length = imagen.getHeight()*imagen.getWidth();
            numeros = new int[length];
            for(int i = 0; i < numeros.length; i++)
                numeros[i] = i;
            Random r = new Random();
            for(int i = 0; i < length; i++){
                int j = r.nextInt(length);
                swapImagen(imagen, i, j);
            }
        }

        public void swapImagen(BufferedImage imagen, int i, int j){
            int colI = i%imagen.getWidth();
            int renI = i/imagen.getWidth();
            int colJ = j%imagen.getWidth();
            int renJ = j/imagen.getWidth();
            int aux = imagen.getRGB(colI, renI);
            imagen.setRGB(colI, renI, imagen.getRGB(colJ, renJ));
            imagen.setRGB(colJ, renJ, aux);
            aux = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = aux;
        }
    }

    public class UpdateWorker extends SwingWorker<BufferedImage, BufferedImage>{

        private BufferedImage referencia;
        private BufferedImage copia;
        private JLabel target;
        int framerate;
        int n;
        String metodo;
        int iteracion;

        public UpdateWorker(BufferedImage master, JLabel target, String archivo, int speed, String algoritmo){
            this.target = target;
            try{
                referencia = ImageIO.read(new File("resource/"+archivo));
                copia = master;
                n = copia.getHeight()*copia.getWidth();
            }catch(Exception e){
                System.err.println(":c Esto no deberia ocurrir");
            }
            framerate = speed; // Indica cada cuantas iteraciones se actualizara la imagen
            metodo = algoritmo;
            iteracion = 0;
        }

        public BufferedImage updateImage(){
            Graphics2D g = copia.createGraphics();
            g.drawImage(copia, 0, 0, null);
            g.dispose();
            return copia;
        }

        @Override
        protected void process(List<BufferedImage> chunks){
            target.setIcon(new ImageIcon(chunks.get(chunks.size() - 1)));
        }

        public void update(){
            for(int i = 0; i < n; i++){
                int indiceDeOriginal = numeros[i];
                int colOriginal = indiceDeOriginal%copia.getWidth();
                int renOriginal = indiceDeOriginal/copia.getWidth();
                int colI = i%copia.getWidth();
                int renI = i/copia.getWidth();
                copia.setRGB(colI, renI, referencia.getRGB(colOriginal, renOriginal));
            }
            publish(updateImage());
        }

        @Override
        protected BufferedImage doInBackground() throws Exception{
            if(metodo.equals("bubble"))
                bubbleSort();
            if(metodo.equals("selection"))
                selectionSort();
            if(metodo.equals("insertion"))
                insertionSort();
            if(metodo.equals("merge"))
                mergeSort();
            if(metodo.equals("quick"))
                quickSort();
            update();
            return null;
        }

        private void bubbleSort(){
            for(int i = 0; i < n-1; i++){
                for(int j = 0; j < n-i-1; j++){
                    if(numeros[j] > numeros[j+1])
                        swap(j, j+1);
                }
                if(iteracion%framerate == 0) update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
                    iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
            }
        }

        /**
         * Algoritmo de ordenamiento SelectionSort.
         */
        private void selectionSort(){
            for(int i = 0; i < n; i++){
                // min trabaja sobre el minimo elemento del arreglo desordenado
                int min = 1;
                for(int j = i+1; j < n; j++){
                    if(numeros[j] < numeros[min])
                        min = j;
                }
                // Intercambia el elemento encontrado con el minimo elemento
                swap(i, min);
                // Actualiza la imagen.
                updateImg();
            }
        }

        /**
         * Algoritmo de ordenamiento InsertionSort.
         */
        private void insertionSort(){
            for(int i = 0; i < n; i++){
                int pivote = numeros[i];
                int j = i - 1;
                /* Mueve los elementos del arreglo que son
                 * mayores que el pivote una posición adelante
                 * de su posicion actual. */
                while(j >= 0 && numeros[j] > pivote){
                    numeros[j+1] = numeros[j];
                    j = j-1;
                }
                numeros[j+1] = pivote;
                // Actualiza la imagen.
                updateImg();
            }
        }

        /**
         * Algoritmo de ordenamiento MergeSort.
         * Divide el arreglo en mitades y va ordenando.
         */
        private void mergeSort(){
            mergeSortAux(0, n-1);
        }

        /**
         * Metodo auxiliar que llama al algoritmo MergeSort
         * @param left el indice izquierdo del sub-arreglo
         * @param right el indice derecho del sub-arreglo
         */
        private void mergeSortAux(int left, int right){
            if(left < right){
                // Obtiene la posicion en la mitad del arreglo
                int mid = left + (right - left) / 2;
                // Ordena el arreglo por mitades
                mergeSortAux(left, mid);
                mergeSortAux(mid+1, right);
                mezcla(left, mid, right);
                // Actualiza la imagen
                updateImg();
            }
        }

                /**
         * Mezcla dos subarreglos de numeros[]
         * @param left indice izquierdo
         * @param mid indice de la mitad
         * @param right indice derecho
         */
        private void mezcla(int left, int mid, int right){
            int i, j, k;
            int n1 = mid - left + 1;
            int n2 =  right - mid;
     
            /* Crea los sub-arreglos temporales */
            int L[] = new int[n1];
            int R[] = new int[n2];
         
            /* Copia los elementos a  L[] y R[] */
            for (i = 0; i < n1; i++)
                L[i] = numeros[left + i];
            for (j = 0; j < n2; j++)
                R[j] = numeros[mid + 1+ j];
         
            /* Mezcla el arreglo de vuelta en numeros[]*/
            i = 0; // Indice inicial del primer sub-arreglo
            j = 0; // Indice inicial del segundo sub-arreglo
            k = left; // Indice inicial del sub-arreglo mezclado
            while (i < n1 && j < n2){
                if (L[i] <= R[j]){
                    numeros[k] = L[i];
                    i++;
                }else{
                    numeros[k] = R[j];
                    j++;
                }
                k++;
            }
            // Copia los elementos restantes de L[]
            while (i < n1){
                numeros[k] = L[i];
                i++;
                k++;
            }
            // Copia los elementos restantes de R[]
            while (j < n2){
                numeros[k] = R[j];
                j++;
                k++;
            }
        }

        private void quickSort(){
            quickSortAux(0, n-1);
        }

        /**
         * Metodo auxiliar para usar QuickSort
         * @param left indice izquierdo
         * @param right indice derecho
         */
        private void quickSortAux(int left, int right){
            int pivote = numeros[left];
            int i = left;
            int j = right;
            int aux;

            while(i < j){
                // Revisa si los indices son menores o mayores que el pivote
                while(numeros[i] <= pivote && i < j)
                    i++;
                while(numeros[j] > pivote)
                    j--;
                if(i < j){
                    swap(i,j);
                }
                // Actualiza la imagen
                updateImg();
            }
            numeros[left] = numeros[j];
            numeros[j] = pivote;
            // Recursivamente ordena los elementos
            if(left < j-1)
                quickSortAux(left, j-1);
            if(j+1 < right)
                quickSortAux(j+1, right);
        }

        /**
         * Metodo auxiliar para intercambiar posiciones en el arreglo
         * @param i el indice a intercambiar
         * @param j el indice a intercambiar
         */
        public void swap(int i, int j){
            int aux = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = aux;
        }

        /**
         * Método para actualizar la imagen para cuando se esta
         * alterando por un algoritmo de ordenamiento.
         */
        public void updateImg(){
            if(iteracion%framerate == 0)
                update();
            iteracion = (iteracion + 1)%framerate;
        }
    }

}
