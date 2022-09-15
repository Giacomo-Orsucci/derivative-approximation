
package View;

import Model.PolinomioModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Classe per la view (pannello grafico con calcoli e disegno del grafico) della funzione polinomiale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class PolinomioView extends JFrame {
    
    private JPanel panelCenter = new JPanel();
    private Grafica panelGrafico;
    private JPanel panelAnalitico = new JPanel();
        
    private JScrollPane valoriMScroll;
    
    private JTextArea valoriMArea = new JTextArea();
    
    private JTextArea valoreDArea = new JTextArea();
    
    private PolinomioModel pM;
   
    /**
     * Costruttore parametrizzato
     * @param pM, riferimento del model della funzione polinomiale 
     */
    public PolinomioView(PolinomioModel pM){
        
        super("Calcolo del grafico, dei coefficienti angolari e della derivata della funzione polinomiale");
        setBounds(300, 100, 1080, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        this.pM = pM;
        
        //inizializzimo il pannello della grafica
        panelGrafico = new Grafica(this, pM);
        
        //settiamo i pannelli
        panelCenter.setLayout(new GridLayout(2, 1));
        panelAnalitico.setLayout(new GridLayout(1, 2));
        
        
        valoriMScroll = new JScrollPane(valoriMArea);
        
        //settiamo le TA
        valoreDArea.setEditable(false);
        valoreDArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoreDArea.append("Valore della derivata prima con la formula: \n\n");
        valoreDArea.append(String.valueOf(pM.calcoloDerivata()));
        valoreDArea.append("\n\n\n");
        valoreDArea.append("Differenza tra l'ultimo coefficiente e la derivata prima: \n\n");
        valoreDArea.append(Double.toString(Math.abs(pM.getListaM().get(pM.getListaM().size()-1)-pM.calcoloDerivata())));
        valoriMArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoriMArea.append("Valori dei coeffincenti angolari: \n");
        
        
        valoriMArea.append("\n");
        
        //scriviamo i valori nella TA
        for(int i = 0; i < pM.getListaM().size(); i++)
        {
            valoriMArea.append(pM.getListaM().get(i).toString());
            valoriMArea.append("\n");
        }
        
        valoriMArea.setEditable(false);
        
        //aggiungiamo i vari componenti al frame
        panelAnalitico.add(valoriMScroll);
        panelAnalitico.add(valoreDArea);   
        panelCenter.add(panelGrafico);
        panelCenter.add(panelAnalitico);
        add(panelCenter,BorderLayout.CENTER);
        
        //setto lo scrollpanel
        valoriMScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);     
    }

    //GETTER E SETTER
    
    public Grafica getPanelGrafico() {
        return panelGrafico;
    }

    public void setPanelGrafico(Grafica panelGrafico) {
        this.panelGrafico = panelGrafico;
    }

    public PolinomioModel getpM() {
        return pM;
    }
    
    
}
