
package View;

import Controller.ConfermaParametriSinusoide;
import java.awt.*;
import javax.swing.*;

/**
 * Classe per la view dei parametri della funzione sinusoidale.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class ParametriSinusoideView extends JFrame{
    
    private JPanel rootPanel;
    private JPanel westPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    
    private JLabel parametriLabel = new JLabel("Inserisci i parametri della funzione");
    private JLabel aLabel = new JLabel("Inserisci il valore di a");
    private JLabel bLabel = new JLabel("Inserisci il valore di b");
    private JLabel limDXLabel = new JLabel("Inserisci il limite destro");
    private JLabel limSXLabel = new JLabel("Inserisci il limite sinistro");
    private JLabel precisioneLabel = new JLabel("Inserisci la precisione");
    
    private JTextField aTF = new JTextField();
    private JTextField bTF = new JTextField();
    private JTextField limDXTF = new JTextField();
    private JTextField limSXTF = new JTextField();
    private JTextField precisioneTF = new JTextField();
    
    private JButton confermaButton = new JButton("CONFERMA");
    
    public ParametriSinusoideView(){
        
        super("Inserimento parametri della funzione sinusoidale");
        rootPanel = (JPanel) getContentPane();
        setBounds(900, 200, 720, 480);
        setVisible(true);
        setLayout(new BorderLayout(15, 15));
        
        //settiamo i panel
        westPanel.setLayout(new GridLayout(0, 1, 15, 15));
        centerPanel.setLayout(new GridLayout(0, 1, 15, 15));
        
        //settiamo i label
        parametriLabel.setHorizontalAlignment(JLabel.CENTER);
        aLabel.setHorizontalAlignment(JLabel.CENTER);
        bLabel.setHorizontalAlignment(JLabel.CENTER);
        limDXLabel.setHorizontalAlignment(JLabel.CENTER);
        limSXLabel.setHorizontalAlignment(JLabel.CENTER);
        precisioneLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //settiamo il bottone
        confermaButton.addActionListener(new ConfermaParametriSinusoide(this));
        confermaButton.setPreferredSize(new Dimension(200, 50));
        
        //aggiungiamo i vari componenti
        westPanel.add(aLabel);
        westPanel.add(bLabel);
        westPanel.add(limSXLabel);
        westPanel.add(limDXLabel);
        westPanel.add(precisioneLabel);
        
        centerPanel.add(aTF);
        centerPanel.add(bTF);
        centerPanel.add(limSXTF);
        centerPanel.add(limDXTF);
        centerPanel.add(precisioneTF);
        
        rootPanel.add(parametriLabel, BorderLayout.NORTH);
        rootPanel.add(westPanel, BorderLayout.WEST);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
        rootPanel.add(confermaButton, BorderLayout.SOUTH);
        
        setResizable(false);
    }
    
    //GETTER E SETTER

    public JTextField getaTF() {
        return aTF;
    }

    public void setaTF(JTextField aTF) {
        this.aTF = aTF;
    }

    public JTextField getbTF() {
        return bTF;
    }

    public void setbTF(JTextField bTF) {
        this.bTF = bTF;
    }

    public JTextField getLimDXTF() {
        return limDXTF;
    }

    public void setLimDXTF(JTextField limDXTF) {
        this.limDXTF = limDXTF;
    }

    public JTextField getLimSXTF() {
        return limSXTF;
    }

    public void setLimSXTF(JTextField limSXTF) {
        this.limSXTF = limSXTF;
    }

    public JTextField getPrecisioneTF() {
        return precisioneTF;
    }

    public void setPrecisioneTF(JTextField precisioneTF) {
        this.precisioneTF = precisioneTF;
    }
    
    
    
}
