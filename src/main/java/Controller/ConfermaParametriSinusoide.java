
package Controller;

import Model.SinusoideModel;
import View.ParametriSinusoideView;
import View.SinusoideView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Classe di controllo per l'inserimento dei parametri della funzione sinusoidale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class ConfermaParametriSinusoide implements ActionListener{
    
    private ParametriSinusoideView pSV; //view da cui prendere i parametri inseriti
    
    /**
     * Costruttore parametrizzato
     * @param pSV, riferimento della view dei parametri. 
     */
    public ConfermaParametriSinusoide(ParametriSinusoideView pSV) {
        this.pSV = pSV;
    }
    

    @Override
    public void actionPerformed(ActionEvent aE) {
        
        int x; //appoggio per la gestione della finestra di dialogo con conferma
       
        //stringhe di appoggio per i parametri inseriti
        String appoggioA = null;
        String appoggioB = null;
        String appoggioDX = null;
        String appoggioSX = null;
        String appoggioPrecisione = null;
        
        //variabili contenenti le conversioni delle stringhe dei parametri
        double parametroA = 0;
        double parametroB = 0;
        double parametroDX = 0;
        double parametroSX = 0;
        double precisione = 0.1;
        
        //per controllare se ci sono eccezioni
        boolean eccezione = false;
        
        //model della funzione corrispettiva
        SinusoideModel sM;
        
        /*
            gestiamo tutti gli inserimenti non numerici dei parametri
        */
        
        try{
            appoggioA = pSV.getaTF().getText();
            parametroA = Double.parseDouble(appoggioA);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro a è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        try{
            appoggioB = pSV.getbTF().getText();
            parametroB = Double.parseDouble(appoggioB);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro b è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        try {
            appoggioDX = pSV.getLimDXTF().getText();
            parametroDX = Double.parseDouble(appoggioDX);
        } catch (NumberFormatException nFE) {
            JOptionPane.showMessageDialog(null, "Il limite destro è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        try {
            appoggioSX = pSV.getLimSXTF().getText();
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
            appoggioPrecisione = pSV.getPrecisioneTF().getText();
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
            se non ci sono state eccezioni o sono state risolte tutte,
            si chiude la view dei parametri e si apre la view della funzione
            con i vari calcoli ed il grafico.
        */
        
        if(eccezione == false){
            sM = new SinusoideModel(parametroA, parametroB, parametroSX, parametroDX, precisione);
            pSV.dispose();
            sM.calcoloCiclo();
            sM.getListaM();
             
            SinusoideView sW = new SinusoideView(sM);
            
            sW.repaint();
            
        } 
    }
    
}
