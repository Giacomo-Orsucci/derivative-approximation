
package View;

import Model.RadicaleModel;
import java.awt.*;
import javax.swing.*;

/**
 * Classe per la view (pannello grafico con calcoli e disegno del grafico) della funzione radicale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class RadicaleView extends JFrame{
    
    private JPanel panelCenter = new JPanel();
    private Grafica panelGrafico;
    private JPanel panelAnalitico = new JPanel();
    
    private JTextArea valoriMArea = new JTextArea();  
    private JTextArea valoreDArea = new JTextArea();
    
    private JScrollPane valoriMScroll= new JScrollPane(valoriMArea);
    
    private RadicaleModel rM;
    
   /**
    * Costruttre parametrizzato
    * @param rM, riferimento del model della funzione radicale 
    */
    public RadicaleView(RadicaleModel rM){
        
        super("Calcolo del grafico, dei coefficienti angolari e della derivata della funzione radicale");
        setBounds(300, 100, 1080, 720);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        this.rM = rM;
        
        //settiamo i pannelli
        panelCenter.setLayout(new GridLayout(2, 1));
        panelAnalitico.setLayout(new GridLayout(1, 2));
        panelGrafico = new Grafica(this, rM);
        
        //settiamo le TA 
        valoreDArea.setEditable(false);
        valoreDArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoreDArea.append("Valore della derivata prima con la formula: \n\n");
        valoreDArea.append(String.valueOf(rM.calcoloDerivata()));
        valoreDArea.append("\n\n\n");
        valoreDArea.append("Differenza tra l'ultimo coefficiente e la derivata prima: \n\n");
        valoreDArea.append(Double.toString(Math.abs(rM.getListaM().get(rM.getListaM().size()-1)-rM.calcoloDerivata())));
        valoriMArea.setFont(new Font("Courier New", Font.BOLD, 15));
        valoriMArea.append("Valori dei coeffincenti angolari: \n");
        valoriMArea.append("\n");
       
        //scriviamo i risultati nella TA
        for(int i = 0; i < rM.getListaM().size(); i++)
        {
            valoriMArea.append(rM.getListaM().get(i).toString());
            valoriMArea.append("\n");
        }
        
        valoriMArea.setEditable(false);
        
        //aggiungiamo i vari componenti al frame
        panelAnalitico.add(valoriMScroll);
        panelAnalitico.add(valoreDArea);
        panelCenter.add(panelGrafico);
        panelCenter.add(panelAnalitico);
        add(panelCenter,BorderLayout.CENTER);
        
        //settiamo lo scrollPane
        valoriMScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
    }   
}
