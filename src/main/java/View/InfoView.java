package View;

import java.awt.Font;
import javax.swing.*;

/**
 * Classe per le informazioni sul programma.
 * @author Giacomo Orsucci & Leonardo Giambini 4IC.
 */
public class InfoView extends JFrame {

    private JPanel rootPanel = new JPanel();
    private final JTextArea testo = new JTextArea();

    public InfoView() {

        super("Informazioni sul programma");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(600, 100, 720, 480);
        rootPanel = (JPanel) getContentPane();
        setResizable(false);
        
        testo.setFont(new Font("Courier New", Font.BOLD, 13));

        //scriviamo nella TextArea
        testo.append("PROGRAMMA DI CALCOLO E CONFRONTO TRA COEFFICIENTI ANGOLARI E DERIVATE PRIME."
                + "\n\nSCOPO DEL PROGRAMMA: \n"
                + "\ncalcolare i coefficienti angolari delle rette tracciate tra il limite sinistro e quello"
                + "\ndestro dimezzando ogni volta il limite destro, fino a che la differenza tra"
                + "\nlimite sinistro e limite destro non è minore o uguale alla precisione inserita."
                + "\nI coefficienti angolari calcolati mano a mano, sono l'equivalente "
                + "\ndel rapporto incrementale; quindi l'ultimo coefficiente deve"
                + "\navvicinarsi il più possibile alla derivata prima calcolata con la formula nel limite"
                + "\nsinistro (quello che rimane fisso). Tale uguaglianza è dimostrata dalla differenza"
                + "\n(in valore assoluto) tra l'ultimo cofficiente angolare e la derivata prima calcolata."
                + "\n\nNOTE SUL PROGRAMMA: \n"
                + "\ntutti i domini sono stati controllati, perciò è necessario inserire parametri che li"
                + "\nrispettino (come evidenziato in caso di inserimento errato). "
                + "\nI risultati e la precisione presentano al massimo 15 decimali, il limite del double."
                + "\nGraficamente è possibile apprezzare il grafico (in nero), le rette dei vari"
                + "\ncoefficienti angolari, ovvero le rette che uniscono due punti e la cui pendenza"
                + "\nrappresenta il coefficiente angolare (in rosso) e la retta tangente all'estremo più"
                + "\npiccolo dell'intervallo inserito (in verde). Il grafico stampato va dal limite inferiore"
                + "\nal limite superiore inseriti, quindi non sempre verranno visualizzati gli assi.");

        testo.setEditable(false);

        rootPanel.add(testo);

        setVisible(true);
    }
}
