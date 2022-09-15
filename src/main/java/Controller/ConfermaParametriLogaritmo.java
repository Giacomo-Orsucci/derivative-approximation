
package Controller;

import java.awt.event.*;
import Model.LogaritmoModel;
import View.LogaritmoView;
import View.ParametriLogaritmoView;
import javax.swing.*;

/**
 * Classe di controllo per l'inserimento dei parametri della funzione logaritmica.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class ConfermaParametriLogaritmo implements ActionListener{
    
    private ParametriLogaritmoView pLV; //view da cui prendere i parametri inseriti

    /**
     * Costruttore parametrizzato
     * @param pLV, riferimento della view dei parametri. 
     */
    public ConfermaParametriLogaritmo(ParametriLogaritmoView pLV) {
        this.pLV = pLV;
    }
    
    @Override
    public void actionPerformed(ActionEvent aE) {
        
        int x; //appoggio per la gestione della finestra di dialogo con conferma
        
        //stringhe di appoggio per i parametri inseriti
        String appoggioA = null;
        String appoggioDX = null;
        String appoggioSX = null;
        String appoggioPrecisione = null;
        
        //variabili contenenti le conversioni delle stringhe dei parametri
        double parametroA = 0;
        double parametroDX = 0;
        double parametroSX = 0;
        double precisione = 0.1;
        
        //appoggio per il controllo sul dominio
        Double calcoloPerControllo;
        
        //per controllare se ci sono eccezioni
        boolean eccezione = false;
        
        //model della funzione corrispettiva
        LogaritmoModel lM;
        
        /*
            gestiamo tutti gli inserimenti non numerici dei parametri
        */
        
        try{
            appoggioA = pLV.getaTF().getText();
            parametroA = Double.parseDouble(appoggioA);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro a è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        try {
            appoggioDX = pLV.getLimDXTF().getText();
            parametroDX = Double.parseDouble(appoggioDX);
        } catch (NumberFormatException nFE) {
            JOptionPane.showMessageDialog(null, "Il limite destro è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        try {
            appoggioSX = pLV.getLimSXTF().getText();
            parametroSX = Double.parseDouble(appoggioSX);
            
        } catch (NumberFormatException nFE) {
            JOptionPane.showMessageDialog(null, "Il limite sinistro è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        //controlliamo che l'estremo destro sia più grande del sinistro
        if(parametroSX >= parametroDX){
            JOptionPane.showMessageDialog(null, "Il limite sinistro deve essere minore di quello destro", "Inserire correttamente i dati", JOptionPane.WARNING_MESSAGE);
            eccezione = true;  
        }
        
        /*
            facciamo i vari controlli sulla precisione; avvertiamo l'utente nel caso in cui
            stia inserendo una precisione troppo grande o troppo piccola perché potrebbe dare dei 
            problemi sul calcolo
        */
        
        try {
            appoggioPrecisione = pLV.getPrecisioneTF().getText();
            precisione = Double.parseDouble(appoggioPrecisione);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Il valore della precisione è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        if(precisione <= 0){
            JOptionPane.showMessageDialog(null, "Il valore della precisione deve essere maggiore di 0", "Inserire un numero positivo", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        else if(precisione >= 1 || precisione < 0.000000000000001){
            x = JOptionPane.showConfirmDialog(null, "Continuare? Potresti avere dei problemi sul calcolo del coefficiente", "Valore troppo grande o troppo piccolo", JOptionPane.YES_NO_OPTION);
            if(x != 0){
                eccezione = true;
            }
        }
        
        /*
            controlliamo che i parametri inseriti rispettino il dominio della funzione
            e che, i parametri con le modifiche apportate quando si calcola la derivata con 
            la formula, rispettino il dominio della funzione
        */
        
        calcoloPerControllo = Math.log10(parametroA*parametroSX);
        
        
        if(calcoloPerControllo.isNaN() || calcoloPerControllo.isInfinite()) {
            JOptionPane.showMessageDialog(null, "l'estremo destro non rispetta il dominio della funzione. Affinchè lo rispetti a*limiteSinistro deve essere maggiore di 0.", 
                    "Inserire un numero che rispetti il dominio", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.log10(parametroA*parametroDX);
        
        if(calcoloPerControllo.isNaN() || calcoloPerControllo.isInfinite()) {
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non rispetta il dominio della funzione. Affinchè lo rispetti a*limiteDestro deve essere maggiore di 0.",
                    "Inserire un numero che rispetti il dominio", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.log10(parametroA*(parametroSX-0.1));
        
        if(calcoloPerControllo.isNaN() || calcoloPerControllo.isInfinite()){
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non permette di calcolare correttamente la derivata prima della funzione."
                    + " Affinchè il calcolo avvenga correttamente a(estremosinistro - 0.1) deve essere maggiore di 0.",
                    "Inserire un numero che rispetti la condizione", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.log10(parametroA*(parametroSX+0.1));
        
        if(calcoloPerControllo.isNaN() || calcoloPerControllo.isInfinite()){
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non permette di calcolare correttamente la derivata prima della funzione."
                    + " Affinchè il calcolo avvenga correttamente a(estremosinistro + 0.1) deve essere maggiore di 0.",
                    "Inserire un numero che rispetti la condizione", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        /*
            se non ci sono state eccezioni o sono state risolte tutte,
            si chiude la view dei parametri e si apre la view della funzione
            con i vari calcoli ed il grafico.
        */
        
        if(eccezione == false){
            lM = new LogaritmoModel(parametroA, parametroSX, parametroDX, precisione);
            pLV.dispose();
            lM.calcoloCiclo();
            lM.getListaM();
            
            LogaritmoView lW = new LogaritmoView(lM);
        } 
    }
}
    

