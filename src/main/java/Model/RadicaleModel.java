
package Model;

import java.util.ArrayList;

/**
 * Classe di calcolo della funzione polinomiale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class RadicaleModel {
    private final double a;
    private final double b;
    private final double c;
    private final double limFisso; //quello sinistro (a sugli appunti)
    private final double precisione;
    private final double primoLimMobile;
    
    private double limMobile; //quello destro (b sugli appunti), si porta b verso a, è sempre più grande di a
    
    private ArrayList <Double> listaM = new ArrayList();//per il salvataggio dei coefficienti angolari calcolati
    //per il salvataggio dei valori del limite destro portato verso il sinistro
    private ArrayList <Double> listaLimMobile = new ArrayList();
    
    /**
     * Costruttore parametrizzato
     * @param a, coefficiente della x della radice
     * @param b, valore sommato alla x della radice
     * @param c, sollevamento della funzione dall'origine
     * @param limFisso, estremo             più piccolo
     * @param limMobile, estremo più grande
     * @param precisione, precisione per il dimezzamento 
     */
    public RadicaleModel(double a, double b, double c, double limFisso, double limMobile, double precisione) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.limFisso = limFisso;
        this.limMobile = limMobile;
        this.precisione = precisione;
        this.primoLimMobile = limMobile;
        listaLimMobile.add(this.limMobile);
    }
    
    /**
     * Metodo per il calcolo della y della funzione in un punto x
     * @param puntoInCuiCalcolare, x in cui calcolare
     * @return y, il valore calcolato
     */
    public double calcoloY(double puntoInCuiCalcolare){
        double y;
        y = Math.sqrt(a * puntoInCuiCalcolare + b) + c;
        return y;
    }
    
    /**
     * Metodo per il calcolo del coefficiente angolare
     * @return m, il coefficiente angolare calcolato
     */
    public double calcoloM(){
        
        double m;

        m = (calcoloY(limFisso) - calcoloY(limMobile)) / (limFisso - limMobile);

        
        return m;
    }
    
    /**
     * Metodo per il calcolo della derivata nel punto fisso con la formula
     * @return la derivata calcolata
     */
    public double calcoloDerivata(){
        double h = 0.01d;
        
        double f1; //fattore 1
        double f2;
        double f3;
        double appoggio = 0;
        
        for(int i=0; i<2; i++){
            f1 = Math.pow(-1, i);
            f2 = calcoloY(limFisso + (1 - i)*h);
            f3 = calcoloY(limFisso - i*h);
            appoggio += f1*(f2 + f3)*0.5;   
        }
        return appoggio * 1/h;
    }
    
    /**
     * Metodo per il dimezzamento dell'intervallo
     */
    public void dimezzaIntervallo(){
        
        limMobile = limFisso + (limMobile-limFisso)/2;
        listaLimMobile.add(limMobile);
        
    }
    
    /**
     * Metodo per il calcolo del coefficiente angolare 
     * dimezzando l'intervallo fino alla precisione desiderata
     */
    public void calcoloCiclo(){
        
        double m;
        m = calcoloM();
        listaM.add(m);
        do{
            
            dimezzaIntervallo();
            m = calcoloM();
            listaM.add(m);  
        }while ((limMobile - limFisso) > precisione);
        
    }

    //GETTER E SETTER
    
    
    public ArrayList<Double> getListaM() {
        return listaM;
    }

    public void setListaM(ArrayList<Double> listaM) {
        this.listaM = listaM;
    }

    public double getLimFisso() {
        return limFisso;
    }

    public double getPrimoLimMobile() {
        return primoLimMobile;
    }

    public ArrayList<Double> getListaLimMobile() {
        return listaLimMobile;
    }
    
}
