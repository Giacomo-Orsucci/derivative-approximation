
package View;

import Model.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Classe di disegno del grafico della varie funzioni.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class Grafica extends JPanel{
    
    
    private String tipoGrafica;
    
    //funzione polinomiale
    private PolinomioView pW;
    private PolinomioModel pM;
    
    //funzione logaritmica
    private LogaritmoView lW;
    private LogaritmoModel lM;
    
    //funzione radicale
    private RadicaleView rW;
    private RadicaleModel rM;
    
    //funzione sinusoidale
    private SinusoideView sW;
    private SinusoideModel sM;
    
    
    private RettaModel rettaModel;
    
    private double uX;//scala delle x
    private double uY;//scala delle y
    private double yMax = 0;
    private double yMin = 0;
    
    /*
        lista fatta per non calcolare due volte tutti punti. Prima per trovare yMin e yMax e poi per disegnare tutto il grafico.
        Salvando tutti i punti faccio tutti i calcoli una sola volta.
    */
    ArrayList<Double> listaY = new ArrayList();  
    
    /**
     * Costruttore parametrizzato 
     * @param pW, view della funzione polinomiale
     * @param pM, model della funzione polinomiale
     */
    public Grafica(PolinomioView pW, PolinomioModel pM){
        tipoGrafica = "Polinomio";
        this.pW = pW;
        this.pM = pM;
          
        setVisible(true);
    }
    
    /**
     * Costruttore parametrizzato
     * @param lW, view della funzione logaritmica
     * @param lM, model della funzione logaritmica
     */
    public Grafica(LogaritmoView lW, LogaritmoModel lM){
        tipoGrafica = "Logaritmo";
        this.lW = lW;
        this.lM = lM;
          
        setVisible(true);
    }
    
    /**
     * Costruttore parametrizzato
     * @param rW, view della funzione radicale
     * @param rM, model della funzione radicale
     */
    public Grafica(RadicaleView rW, RadicaleModel rM){
        tipoGrafica = "Radicale";
        this.rW = rW;
        this.rM = rM;
          
        setVisible(true);
    }
    
    /**
     * Costruttore parametrizzato
     * @param sW, view della funzione sinusoidale
     * @param sM, model della funzione sinusoidale
     */
    public Grafica(SinusoideView sW, SinusoideModel sM){
        tipoGrafica = "Sinusoide";
        this.sW = sW;
        this.sM = sM;
          
        setVisible(true);
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        //controllo su quale funzione è stata scelta 
        switch(tipoGrafica){
            
            case "Polinomio":{
                //creazione scala grafica x
                uX = getWidth()/(pM.getPrimoLimMobile() - pM.getLimFisso());
                
                int indice = 0;
                
                for(double x = pM.getLimFisso(); x <= pM.getPrimoLimMobile(); x += 0.01 ){
                    
                    //salvataggio valori delle y a 0.01 di distanza
                    listaY.add(pM.calcoloY(x));
                    
                    //inizializzo yMax e yMin
                    if(x == pM.getLimFisso()){
                        yMax = listaY.get(indice);
                        yMin = listaY.get(indice);
                    }
                    
                    //ricerca della y più grande
                    if(listaY.get(indice) > yMax){
                        yMax = listaY.get(indice);
                    }
                    
                    //ricerca della y più piccola
                    if(listaY.get(indice) < yMin){
                        yMin = listaY.get(indice);
                    }
                            
                    indice++;
                }
                
                //creazione scala grafica y
                uY = getHeight()/(yMax - yMin);
                
                g.setColor(Color.black);
                
                //disegno asse x
                g.drawLine(convertiXPolinomio(pM.getLimFisso()), convertiY(0), convertiXPolinomio(pM.getPrimoLimMobile()), convertiY(0));
                
                //disegno asse y
                g.drawLine(convertiXPolinomio(0), convertiY(yMax), convertiXPolinomio(0), convertiY(yMin));
                
                //creazione di una x precedente
                double xPrecedente = pM.getLimFisso();
                double x = xPrecedente + 0.01;
                
                int i =1;
                
                do{
                    //disegno di una linea dal punto precedente a quello attuale
                    g.drawLine(convertiXPolinomio(xPrecedente), convertiY(listaY.get(i-1)), convertiXPolinomio(x), convertiY(listaY.get(i)));
                    
                    //incremento di x e xPrecedente
                    x += 0.01;
                    xPrecedente += 0.01;
                    i++;
                }while(x <= pM.getPrimoLimMobile());
                
                
                for(int l=0; l < pM.getListaLimMobile().size(); l++){
                    
                    g.setColor(Color.red);
                    //disegno delle secanti
                    g.drawLine(convertiXPolinomio(pM.getLimFisso()), convertiY(listaY.get(0)), convertiXPolinomio(pM.getListaLimMobile().get(l)), convertiY(pM.calcoloY(pM.getListaLimMobile().get(l))));
                }
                
                g.setColor(Color.green);
                
                //creazione dell'oggetto per il calcolo della retta
                rettaModel = new RettaModel(pM.calcoloDerivata(), pM.getLimFisso(), pM.calcoloY(pM.getLimFisso()));
                
                //disegno della retta tangente
                g.drawLine(convertiXPolinomio(pM.getLimFisso()), convertiY(rettaModel.funzione(pM.getLimFisso())), convertiXPolinomio(pM.getPrimoLimMobile()), convertiY(rettaModel.funzione(pM.getPrimoLimMobile())));
                
                
                break;
        }
            case "Logaritmo":{
                
                int indice = 0;
                //creazione scala grafica x
                uX = getWidth()/(lM.getPrimoLimMobile() - lM.getLimFisso());
                
                for(double x = lM.getLimFisso(); x <= lM.getPrimoLimMobile(); x += 0.01 ){
                    
                    //salvataggio valori delle y a 0.01 di distanza
                    listaY.add(lM.calcoloY(x));
                    
                    //inizializzo yMax e yMin
                    if(x == lM.getLimFisso()){
                        yMax = listaY.get(indice);
                        yMin = listaY.get(indice);
                    }
                    //ricerca della y più grande
                    if(listaY.get(indice) > yMax){
                        yMax = listaY.get(indice);
                    }
                    
                    //ricerca della y più piccola
                    if(listaY.get(indice) < yMin){
                        yMin = listaY.get(indice);
                    }
                            
                    indice++;
                }
                
                //creazione scala grafica y
                uY = getHeight()/(yMax - yMin);
                
                g.setColor(Color.black);
                
                //disegno asse x
                g.drawLine(convertiXLogaritmo(lM.getLimFisso()), convertiY(0), convertiXLogaritmo(lM.getPrimoLimMobile()), convertiY(0));
                
                //disegno asse y
                g.drawLine(convertiXLogaritmo(0), convertiY(yMax), convertiXLogaritmo(0), convertiY(yMin));
                
                //creazione di una x precedente
                double xPrecedente = lM.getLimFisso();
                double x = xPrecedente + 0.01;
                
                int i =1;
                
                do{
                    //disegno di una linea dal punto precedente a quello attuale
                    g.drawLine(convertiXLogaritmo(xPrecedente), convertiY(listaY.get(i-1)), convertiXLogaritmo(x), convertiY(listaY.get(i)));
                   
                    //incremento di x e xPrecedente
                    x += 0.01;
                    xPrecedente += 0.01;
                    
                    i++;
                }while(x <= lM.getPrimoLimMobile());
                
                for(int l=0; l < lM.getListaLimMobile().size(); l++){
                    
                    g.setColor(Color.red);
                    //disegno delle secanti
                    g.drawLine(convertiXLogaritmo(lM.getLimFisso()), convertiY(listaY.get(0)), convertiXLogaritmo(lM.getListaLimMobile().get(l)), convertiY(lM.calcoloY(lM.getListaLimMobile().get(l))));
                }
                
                g.setColor(Color.green);
               
                //creazione dell'oggetto per il calcolo della retta
                rettaModel = new RettaModel(lM.calcoloDerivata(), lM.getLimFisso(), lM.calcoloY(lM.getLimFisso()));
                //disegno della retta tangente
                g.drawLine(convertiXLogaritmo(lM.getLimFisso()), convertiY(rettaModel.funzione(lM.getLimFisso())), convertiXLogaritmo(lM.getPrimoLimMobile()), convertiY(rettaModel.funzione(lM.getPrimoLimMobile())));
                
                break;
            }
                
            case "Radicale":{
                int indice = 0;
                //creazione scala grafica x
                uX = getWidth()/(rM.getPrimoLimMobile() - rM.getLimFisso());
                
                for(double x = rM.getLimFisso(); x <= rM.getPrimoLimMobile(); x += 0.01 ){
                    //salvataggio valori delle y a 0.01 di distanza
                    listaY.add(rM.calcoloY(x));
                    
                    //inizializzo yMax e yMin
                    if(x == rM.getLimFisso()){
                        yMax = listaY.get(indice);
                        yMin = listaY.get(indice);
                    }
                    //ricerca della y più grande
                    if(listaY.get(indice) > yMax){
                        yMax = listaY.get(indice);
                    }
                    //ricerca della y più piccola
                    if(listaY.get(indice) < yMin){
                        yMin = listaY.get(indice);
                    }
                            
                    indice++;
                }
                //creazione scala grafica y
                uY = getHeight()/(yMax - yMin);
                
                g.setColor(Color.black);
                
                //disegno asse x
                g.drawLine(convertiXRadicale(rM.getLimFisso()), convertiY(0), convertiXRadicale(rM.getPrimoLimMobile()), convertiY(0));
                
                //disegno asse y
                g.drawLine(convertiXRadicale(0), convertiY(yMax), convertiXRadicale(0), convertiY(yMin));
                
                //creazione di una x precedente
                double xPrecedente = rM.getLimFisso();
                double x = xPrecedente + 0.01;
                
                int i =1;
                
                do{
                    //disegno di una linea dal punto precedente a quello attuale
                    g.drawLine(convertiXRadicale(xPrecedente), convertiY(listaY.get(i-1)), convertiXRadicale(x), convertiY(listaY.get(i)));
                    
                    //incremento di x e xPrecedente
                    x += 0.01;
                    xPrecedente += 0.01;
                    i++;
                }while(x <= rM.getPrimoLimMobile());
                
                for(int l=0; l < rM.getListaLimMobile().size(); l++){
                    
                    g.setColor(Color.red);
                    //disegno delle secanti
                    g.drawLine(convertiXRadicale(rM.getLimFisso()), convertiY(listaY.get(0)), convertiXRadicale(rM.getListaLimMobile().get(l)), convertiY(rM.calcoloY(rM.getListaLimMobile().get(l))));
                }
                
                g.setColor(Color.green);
                //creazione dell'oggetto per il calcolo della retta
                rettaModel = new RettaModel(rM.calcoloDerivata(), rM.getLimFisso(), rM.calcoloY(rM.getLimFisso()));
                //disegno della retta tangente
                g.drawLine(convertiXRadicale(rM.getLimFisso()), convertiY(rettaModel.funzione(rM.getLimFisso())), convertiXRadicale(rM.getPrimoLimMobile()), convertiY(rettaModel.funzione(rM.getPrimoLimMobile())));
                
                break;
            }
                case "Sinusoide":{
                //creazione scala grafica x
                uX = getWidth()/(sM.getPrimoLimMobile() - sM.getLimFisso());
                
                int indice = 0;
                
                for(double x = sM.getLimFisso(); x <= sM.getPrimoLimMobile(); x += 0.01 ){
                    //salvataggio valori delle y a 0.01 di distanza
                    listaY.add(sM.calcoloY(x));
                    
                    //inizializzo yMax e yMin
                    if(x == sM.getLimFisso()){
                        yMax = listaY.get(indice);
                        yMin = listaY.get(indice);
                    }
                    //ricerca della y più grande
                    if(listaY.get(indice) > yMax){
                        yMax = listaY.get(indice);
                    }
                    //ricerca della y più piccola
                    if(listaY.get(indice) < yMin){
                        yMin = listaY.get(indice);
                    }
                            
                    indice++;
                }
                //creazione scala grafica y
                uY = getHeight()/(yMax - yMin);
                
                g.setColor(Color.black);
                
                //disegno asse x
                g.drawLine(convertiXSinusoide(sM.getLimFisso()), convertiY(0), convertiXSinusoide(sM.getPrimoLimMobile()), convertiY(0));
                
                //disegno asse y
                g.drawLine(convertiXSinusoide(0), convertiY(yMax), convertiXSinusoide(0), convertiY(yMin));
                
                //creazione di una x precedente
                double xPrecedente = sM.getLimFisso();
                double x = xPrecedente + 0.01;
                
                int i =1;
                
                do{
                    //disegno di una linea dal punto precedente a quello attuale
                    g.drawLine(convertiXSinusoide(xPrecedente), convertiY(listaY.get(i-1)), convertiXSinusoide(x), convertiY(listaY.get(i)));

                    //incremento di x e xPrecedente
                    x += 0.01;
                    xPrecedente += 0.01;
                    i++;
                }while(x <= sM.getPrimoLimMobile());
                
                for(int l=0; l < sM.getListaLimMobile().size(); l++){
                    
                    g.setColor(Color.red);
                    //disegno delle secanti
                    g.drawLine(convertiXSinusoide(sM.getLimFisso()), convertiY(listaY.get(0)), convertiXSinusoide(sM.getListaLimMobile().get(l)), convertiY(sM.calcoloY(sM.getListaLimMobile().get(l))));
                }
                
                g.setColor(Color.green);
                //creazione dell'oggetto per il calcolo della retta
                rettaModel = new RettaModel(sM.calcoloDerivata(), sM.getLimFisso(), sM.calcoloY(sM.getLimFisso()));
                //disegno della retta tangente
                g.drawLine(convertiXSinusoide(sM.getLimFisso()), convertiY(rettaModel.funzione(sM.getLimFisso())), convertiXSinusoide(sM.getPrimoLimMobile()), convertiY(rettaModel.funzione(sM.getPrimoLimMobile())));
                
                break;
            } 
        }
    }
    
    /**
     * Metodo per la conversione della y da reale a logica
     * @param y, y da convertire
     * @return yPixel, y convertita
     */
    public int convertiY(double y){
        
        int yPixel;
        
        yPixel = (int) ((yMax - y)*uY); 
       
        return yPixel; 
    }
    /**
     * Metodo per la conversione della x (funzione polinomiale) da reale a logica
     * @param x, x da convertire
     * @return xPixel, x convertita
     */
    public int convertiXPolinomio(double x){
        
        int xPixel;
        
        xPixel = (int) ((x - pM.getLimFisso())*uX); 
       
        return xPixel;
    }
    /**
     * Metodo per la conversione della x (funzione logaritmica) da reale a logica
     * @param x, x da convertire
     * @return xPixel, x convertita
     */
    public int convertiXLogaritmo(double x){
        
        int xPixel;
        
        xPixel = (int) ((x - lM.getLimFisso())*uX); 
       
        return xPixel;
    }
    /**
     * Metodo per la conversione della x (funzione radicale) da reale a logica
     * @param x, x da convertire
     * @return xPixel, x convertita
     */
    public int convertiXRadicale(double x){  //da sistemare
        
        int xPixel;
        
        xPixel = (int) ((x - rM.getLimFisso())*uX); 
       
        return xPixel;
    }
    /**
     * Metodo per la conversione della x (funzione sinusoidale) da reale a logica
     * @param x, x da convertire
     * @return xPixel, x convertita
     */
    public int convertiXSinusoide(double x){
        
        int xPixel;
        
        xPixel = (int) ((x - sM.getLimFisso())*uX); 
       
        return xPixel;
    }
}
