
package View;

import Controller.MenuController;
import javax.swing.*;

/**
 * Classe per la view del menu.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class MenuView extends JFrame{
    
    private JPanel rootPanel;
            
    private final JButton polinomioButton = new JButton(new ImageIcon("./ImmaginiFunzioni/Polinomiale.png"));
    private final JButton radicaleButton = new JButton(new ImageIcon("./ImmaginiFunzioni/Radicale.png"));
    private final JButton logaritmoButton = new JButton(new ImageIcon("./ImmaginiFunzioni/Logaritmica.png"));
    private final JButton sinButton = new JButton(new ImageIcon("./ImmaginiFunzioni/Sinusoidale.png"));
    private final JButton infoButton = new JButton(new ImageIcon("./ImmaginiFunzioni/Info.png"));
    
    
    
    public MenuView(String titolo){  
        
        super(titolo);
        
        rootPanel = (JPanel) getContentPane();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setLayout(null);
        setResizable(false);
        
        
        setBounds(100, 200, 720, 480);
        setVisible(true);
        
        
        
        //settiamo i bottoni
        polinomioButton.setName("PolinomioButton");
        radicaleButton.setName("RadicaleButton");
        logaritmoButton.setName("LogaritmoButton");
        sinButton.setName("SinButton");
        infoButton.setName("InfoButton");
        
        infoButton.setBounds(30, 20, 100, 50);
        polinomioButton.setBounds(260, 75, 200, 60);
        radicaleButton.setBounds(30, 210, 200, 60);
        sinButton.setBounds(490, 210, 200, 60);
        logaritmoButton.setBounds(260, 345, 200, 60);
        
        //aggiungiamo i listener ai bottoni
        polinomioButton.addActionListener(new MenuController());
        radicaleButton.addActionListener(new MenuController());
        logaritmoButton.addActionListener(new MenuController());
        sinButton.addActionListener(new MenuController());
        infoButton.addActionListener(new MenuController());
        
        //aggiungiamo i componenti al pannello
        rootPanel.add(infoButton);
        rootPanel.add(polinomioButton);
        rootPanel.add(radicaleButton);
        rootPanel.add(logaritmoButton);
        rootPanel.add(sinButton);
        
        
        
    }
}
