
package View;

import Model.LogaritmoModel;
import java.awt.*;
import javax.swing.*;

/**
 * Classe per la view (pannello grafico con calcoli e disegno del grafico) della funzione logaritmica.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class LogaritmoView extends JFrame{
    
     
    private JPanel panelCenter = new JPanel();
    private Grafica panelGrafico;
    private JPanel panelAnalitico = new JPanel();
    
    private JTextArea valoriMArea = new JTextArea();
    
    private JTextArea valoreDArea = new JTextArea();
    
    private JScrollPane valoriMScroll= new JScrollPane(valoriMArea);
    
    private LogaritmoModel lM;
    
    /**
     * Costruttore parametrizzato
     * @param lM, riferimento del model della funzione logaritmica 
     */
    public LogaritmoView(LogaritmoModel lM){
        
        super("Calcolo del grafico, dei coefficienti angolari e della derivata della funzione logaritmica");
        setBounds(300, 100, 1080, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        this.lM = lM;
        
        //settiamo i pannelli
        panelCenter.setLayout(new GridLayout(2, 1));
        panelAnalitico.setLayout(new GridLayout(1, 2));
        panelGrafico = new Grafica(this, lM);
        
        //settiamo le TA
        valoreDArea.setEditable(false);
        valoreDArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoreDArea.append("Valore della derivata prima con la formula: \n\n");
        valoreDArea.append(String.valueOf(lM.calcoloDerivata()));
        valoreDArea.append("\n\n\n");
        valoreDArea.append("Differenza tra l'ultimo coefficiente e la derivata prima: \n\n");
        valoreDArea.append(Double.toString(Math.abs(lM.getListaM().get(lM.getListaM().size()-1)-lM.calcoloDerivata())));
        valoriMArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoriMArea.append("Valori dei coeffincenti angolari: \n");
        valoriMArea.append("\n");
       
        //scriviamo i valori nella TA
        for(int i = 0; i < lM.getListaM().size(); i++)
        {
            valoriMArea.append(lM.getListaM().get(i).toString());
            valoriMArea.append("\n");
        }
        
        valoriMArea.setEditable(false);
        
        //aggiungiamo i vari componenti al pannello
        panelAnalitico.add(valoriMScroll);
        panelAnalitico.add(valoreDArea);  
        panelCenter.add(panelGrafico);
        panelCenter.add(panelAnalitico);
        
        add(panelCenter,BorderLayout.CENTER);
       
        //settiamo lo scrollpanel 
        valoriMScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
    }
}
