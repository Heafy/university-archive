public class CalculadoraInterna {
    
    private int total;
    
    /** Constructor */
    public CalculadoraInterna() {
        total = 0;
    }
    
    public String getTotal() {
        return "" + total;
    }
    
    public void setTotal(String n) {
        total = cast(n);
    }
    
    public void suma(String n) {
        total += cast(n);
    }
    
    public void resta(String n) {
        total -= cast(n);
    }
    
    public void multiplica(String n) {
        total *= cast(n);
    }
    
    public void divide(String n) {
        total /= cast(n);
    }
    
    private int cast(String n) {
        return Integer.parseInt(n);
    }
}