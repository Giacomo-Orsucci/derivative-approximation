
package Controller;

import Model.RadicaleModel;
import View.ParametriRadicaleView;
import View.RadicaleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Classe di controllo per l'inserimento dei parametri della funzione con radicale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class ConfermaParametriRadicale implements ActionListener{
    
    private ParametriRadicaleView pRV; //view da cui prendere i parametri inseriti

    /**
     * Costruttore parametrizzato
     * @param pRV, riferimento della view dei parametri. 
     */
    public ConfermaParametriRadicale(ParametriRadicaleView pRV) {
        this.pRV = pRV;
    }

    @Override
    public void actionPerformed(ActionEvent aE) {
        
        int x; //appoggio per la gestione della finestra di dialogo con conferma
        
        //stringhe di appoggio per i parametri inseriti
        String appoggioA = null;
        String appoggioB = null;
        String appoggioC = null;
        String appoggioDX = null;
        String appoggioSX = null;
        String appoggioPrecisione = null;
        
        //variabili contenenti le conversioni delle stringhe dei parametri
        double parametroA = 0;
        double parametroB = 0;
        double parametroC = 0;
        double parametroDX = 0;
        double parametroSX = 0;
        double precisione = 0.1;
        
        //appoggio per il controllo sul dominio
        Double calcoloPerControllo;
        
        //per controllare se ci sono eccezioni
        boolean eccezione = false;
        
        //model della funzione corrispettiva
        RadicaleModel rM;
        
        /*
            gestiamo tutti gli inserimenti non numerici dei parametri
        */
        
        try{
            appoggioA = pRV.getaTF().getText();
            parametroA = Double.parseDouble(appoggioA);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro a è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        try{
            appoggioB = pRV.getbTF().getText();
            parametroB = Double.parseDouble(appoggioB);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro b è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        try{
            appoggioC = pRV.getcTF().getText();
            parametroC = Double.parseDouble(appoggioC);
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(null, "Il parametro c è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        try {
            appoggioDX = pRV.getLimDXTF().getText();
            parametroDX = Double.parseDouble(appoggioDX);
        } catch (NumberFormatException nFE) {
            JOptionPane.showMessageDialog(null, "Il limite destro è stato inserito scorrettamente", "Inserire un numero", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        try {
            appoggioSX = pRV.getLimSXTF().getText();
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
            appoggioPrecisione = pRV.getPrecisioneTF().getText();
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
            System.out.println(x);
        }
        
        /*
            controlliamo che i parametri inseriti rispettino il dominio della funzione
            e che, i parametri con le modifiche apportate quando si calcola la derivata con 
            la formula, rispettino il dominio della funzione
        */
        
        calcoloPerControllo = Math.sqrt(parametroA*parametroDX+parametroB);
        
        if(calcoloPerControllo.isNaN()) {
            JOptionPane.showMessageDialog(null, "l'estremo destro non rispetta il dominio della funzione. Affinchè lo rispetti deve essere maggiore o uguale di -b/a", 
                    "Inserire un numero che rispetti il dominio", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.sqrt(parametroA*parametroSX+parametroB);
        
        if(calcoloPerControllo.isNaN()) {
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non rispetta il dominio della funzione. Affinchè lo rispetti deve essere maggiore o uguale di -b/a",
                    "Inserire un numero che rispetti il dominio", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.sqrt(parametroA*(parametroSX-0.1)+parametroB);
        
        if(calcoloPerControllo.isNaN()){
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non permette di calcolare correttamente la derivata prima della funzione."
                    + " Affinchè il calcolo avvenga correttamente deve essere maggiore o uguale di -b/a +0.1",
                    "Inserire un numero che rispetti la condizione", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        calcoloPerControllo = Math.sqrt(parametroA*(parametroSX+0.1)+parametroB);
        
        if(calcoloPerControllo.isNaN()){
            JOptionPane.showMessageDialog(null, "l'estremo sinistro non permette di calcolare correttamente la derivata prima della funzione."
                    + " Affinchè il calcolo avvenga correttamente deve essere maggiore o uguale di -b/a -0.1",
                    "Inserire un numero che rispetti la condizione", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        
        /*
            se non ci sono state eccezioni o sono state risolte tutte,
            si chiude la view dei parametri e si apre la view della funzione
            con i vari calcoli ed il grafico.
        */
        
        if(eccezione == false){
            rM = new RadicaleModel(parametroA, parametroB, parametroC, parametroSX, parametroDX, precisione);
            pRV.dispose();
            rM.calcoloCiclo();
            rM.getListaM();
            
            RadicaleView rW = new RadicaleView(rM);   
        } 
    }  
}
