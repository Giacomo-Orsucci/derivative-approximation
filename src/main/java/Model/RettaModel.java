
package Model;

/**
 * Classe di calcolo per il disegno della retta tangente al punto fisso con pendenza pari alla derivata calcolata.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class RettaModel {
    
    private double m;
    private double x;
    private double y;
    private double q;

    /**
     * Costruttore parametrizzato
     * @param m, derivata calcolata, pendenza della retta, stessa cosa del suo coefficiente angolare
     * @param x, x da cui passa la retta
     * @param y, y da cui passa la retta 
     */
    public RettaModel(double m, double x, double y) {
        this.m = m;
        this.x = x;
        this.y = y;
        calcolaQ();
    }
    
    public void calcolaQ(){
        
        q = y - m*x;
        
    }
    
    public double funzione(double finale){
        
        return m*finale+q;  
    } 
}
