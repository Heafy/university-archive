package edd;

/**
 * Clase para fabricar generadores de huellas digitales.
 */
public class FabricaHuellasDigitales {

    /**
     * Regresa una instancia de {@link HuellaDigital} para cadenas.
     * @param algoritmo el algoritmo de huella digital que se desea.
     * @return una instancia de {@link HuellaDigital} para cadenas.
     * @throws IllegalArgumentException si recibe un identificador no
     *         reconocido.
     */
    public static HuellaDigital<String> getInstancia(AlgoritmoHuellaDigital algoritmo) {
        // Aquí va su código.
        BobJenkins bj = new BobJenkins();

        switch(algoritmo){
          case XOR_STRING:
            return (String s) -> {   
             byte[] k = s.getBytes();
                int l = k.length;
                int r= 0;
                int i = 0;
                int p = 1;
                   while(l >= 4){
                   int x = (k[i] << 24)|(k[i+1]<<16)|(k[i+2]<<8)| (k[i+3]);
                        if(p == 1){
                            r= x; 
                            p=0;
                        }else{
                            r ^= x;
                        }   
                   i +=4;
                   l -= 4;
                   }
                int t= 0;
                switch(l){
                    case 3: t|= (k[i+2]<<8);
                    case 2: t|= (k[i+1]<<16);
                    case 1: t|=  (k[i]<<24);
                }
                if(l > 0)
                    r ^= t;
                return r;
          };
            
          case BJ_STRING:
            return bj;
            
          case GLIB_STRING:
            return (String a)-> {
                byte[] n = a.getBytes();
                int e = n.length;
                int l = 5381;
                for(int i = 0; i < e; i++) {
                    char c = (char)n[i];
                    l = l*33+c;
                }
                return l;
            };       
            
          default:
            throw new IllegalArgumentException();
        }
      }

/**
     * Implementacion con una clase interna privada de la Hash de BobJenkins
     * */
      private static class BobJenkins implements HuellaDigital<String> {
        @Override
        public int huellaDigital(String s) {
            int  h = (int) hash(s.getBytes());
            return h;
        }
        long a,b,c;

        private int hash(byte [] k) {
            int l = k.length;
            a = 0x9e3779b9;
            b = a;
            c = 0xffffffff;
            int i = 0;

            while (l >= 12) {
                a += (k[i] + (k[i+1] << 8) + (k[i+2] << 16) + (k[i+3] << 24));
                b += (k[i+4] + (k[i+5] << 8) + (k[i+6] << 16) + (k[i+7] << 24));
                c += (k[i+8] + (k[i+9] << 8) + (k[i+10] << 16) + (k[i+11] << 24));
                mezcla();
                i += 12;
                l -= 12;
            }
                c += k.length;

            switch(l) {
                case 11:
                c = agrega(c, leftShift(byteLong(k[i + 10]), 24));
                case 10:
                    c = agrega(c, leftShift(byteLong(k[i + 9]), 16));
                case 9:
                    c = agrega(c, leftShift(byteLong(k[i + 8]), 8));                 
                case 8:
                    b = agrega(b, leftShift(byteLong(k[i + 7]), 24));
                case 7:
                    b = agrega(b, leftShift(byteLong(k[i + 6]), 16));
                case 6:
                    b = agrega(b, leftShift(byteLong(k[i + 5]), 8));
                case 5:
                    b = agrega(b, (k[i + 4]));
                case 4:
                    a = agrega(a, leftShift(byteLong(k[i + 3]), 24));
                case 3:
                    a = agrega(a, leftShift(byteLong(k[i + 2]), 16));
                case 2:
                    a = agrega(a, leftShift(byteLong(k[i + 1]), 8));
                case 1:
                    a = agrega(a, (k[i + 0]));
            }
            mezcla();
            return (int) (c&0xffffffff);
        }

        //Convierte de un byte a un long
         private long byteLong(byte b) {
            long val = b & 0x7F;
             return val;
        }

        private void mezcla() {
           a = resta(a, b); 
           a = resta(a, c); 
           a = xor(a, c >> 13);
           b = resta(b, c); 
           b = resta(b, a); 
           b = xor(b, leftShift(a, 8));
           c = resta(c, a);
           c = resta(c, b); 
           c = xor(c, (b >> 13));
           a = resta(a, b); 
           a = resta(a, c); 
           a = xor(a, (c >> 12));
           b = resta(b, c); 
           b = resta(b, a); 
           b = xor(b, leftShift(a, 16));
           c = resta(c, a); 
           c = resta(c, b); 
           c = xor(c, (b >> 5));
           a = resta(a, b); 
           a = resta(a, c); 
           a = xor(a, (c >> 3));
           b = resta(b, c);
            b = resta(b, a); b = xor(b, leftShift(a, 10));
           c = resta(c, a); c = resta(c, b); c = xor(c, (b >> 15));

        }
        //auxiliares para dejar mas clara la sintaxis de los metodos principales
        private long leftShift(long n, int r) {
            return (n << r) & 0x00000000ffffffffL;
        }

        private long resta(long a, long b) {
            return (a - b) & 0x00000000ffffffffL;
        }

        private long xor(long a, long b) {
            return (a ^ b) & 0x00000000ffffffffL;
        }
        
        private long agrega(long a, long b) {
            return (a + b) & 0x00000000ffffffffL;
        }

  
    }
  

}
