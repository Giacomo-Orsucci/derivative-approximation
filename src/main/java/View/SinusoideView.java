
package View;

import Model.SinusoideModel;
import java.awt.*;
import javax.swing.*;

/**
 * Classe per la view (pannello grafico con calcoli e disegno del grafico) della funzione sinusoidale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class SinusoideView extends JFrame{
    
    private JPanel panelCenter = new JPanel();
    private Grafica panelGrafico;
    private JPanel panelAnalitico = new JPanel();
    
    private JTextArea valoriMArea = new JTextArea();
    private JTextArea valoreDArea = new JTextArea();
    
    private JScrollPane valoriMScroll= new JScrollPane(valoriMArea);
    
    private SinusoideModel sM;
    
    /**
     * Costruttore parametrizzato
     * @param sM, riferimento del model della fuzione sinusoidale 
     */
    public SinusoideView(SinusoideModel sM){
        
        super("Calcolo del grafico, dei coefficienti angolari e della derivata della funzione sinusoidale");
        setBounds(300, 100, 1080, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        this.sM = sM;
        
        //settiamo i pannelli
        panelCenter.setLayout(new GridLayout(2, 1));
        panelAnalitico.setLayout(new GridLayout(1, 2));
        panelGrafico = new Grafica(this, sM);
        
        //settoamo le TA
        valoreDArea.setEditable(false);
        valoreDArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoreDArea.append("Valore della derivata prima con la formula: \n\n");
        valoreDArea.append(String.valueOf(sM.calcoloDerivata()));
        valoreDArea.append("\n\n\n");
        valoreDArea.append("Differenza tra l'ultimo coefficiente e la derivata prima: \n\n");
        valoreDArea.append(Double.toString(Math.abs(sM.getListaM().get(sM.getListaM().size()-1)-sM.calcoloDerivata())));
        valoriMArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoriMArea.append("Valori dei coeffincenti angolari: \n");
        valoriMArea.append("\n");
       
        //scriviamo i risultati nella TA
        for(int i = 0; i < sM.getListaM().size(); i++)
        {
            valoriMArea.append(sM.getListaM().get(i).toString());
            valoriMArea.append("\n");
        }
        
        valoriMArea.setEditable(false);
       
        //aggiungiamo i vari componenti al frame
        panelAnalitico.add(valoriMScroll);
        panelAnalitico.add(valoreDArea);
        panelCenter.add(panelGrafico);
        panelCenter.add(panelAnalitico);
        add(panelCenter,BorderLayout.CENTER);
        
        //settiamo lo scrollpanel
        valoriMScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
    }  
}
