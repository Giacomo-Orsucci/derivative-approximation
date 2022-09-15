
package Controller;

import View.InfoView;
import View.ParametriLogaritmoView;
import View.ParametriPolinomioView;
import View.ParametriRadicaleView;
import View.ParametriSinusoideView;
import java.awt.event.*;
import javax.swing.JButton;

/**
 * Classe di controllo per i bottoni del menù.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class MenuController implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent aE) {
        
        JButton source = (JButton) aE.getSource();
        
        /*
        switch per la gestione dei diversi bottoni del menù.
        case diversi a seconda dei bottoni premuti per aprire le differenti 
        view per l'inserimento dei parametri.
        */
        
        switch(source.getName()){   
            case "PolinomioButton": ParametriPolinomioView polinomioView = new ParametriPolinomioView();
                break;
            case "RadicaleButton": ParametriRadicaleView radicaleView = new ParametriRadicaleView();
                break;
            case "LogaritmoButton": ParametriLogaritmoView logaritmoView = new ParametriLogaritmoView();
                break;
            case "SinButton": ParametriSinusoideView sinusoideView = new ParametriSinusoideView();
                break;
            case "InfoButton": InfoView infoView = new InfoView();
                break;
        }
    }
    
}
