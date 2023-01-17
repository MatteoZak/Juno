package ProgettoFinale.View.Modalita;

import ProgettoFinale.View.FontPokemon;

import javax.swing.*;
import java.awt.*;

public class SelezioneModalita extends JLabel {
    private JButton modStandard = new JButton();
    private JButton modCarteExtra = new JButton();
    private JButton modInvertita = new JButton();
    private JLabel labelStandard = new JLabel();
    private JLabel labelCarteExtra = new JLabel("La modalità Carte Extra");
    private JLabel labelInvertita = new JLabel("La modalità Invertita");

    private JButton tastoIndietro = new JButton("Torna Indietro");

    public SelezioneModalita(int width, int height){
        setBounds(0,0,width,height);
        setLayout(new GridBagLayout());

        Font font = new FontPokemon(15f).getFont();

        modStandard.setPreferredSize(new Dimension(300,100));
        modCarteExtra.setPreferredSize(new Dimension(600,100));
        modInvertita.setPreferredSize((new Dimension(300,100)));

        modStandard.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Utilita/Standard.png")
                .getScaledInstance(300,100, 16)));
        modCarteExtra.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Utilita/CarteExtra.png")
                .getScaledInstance(600,100, 16)));
        modInvertita.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Utilita/Invertita.png")
                .getScaledInstance(300,100, 16)));

        modStandard.setBorderPainted(false);
        modStandard.setContentAreaFilled(false);
        modStandard.setFocusPainted(false);

        modCarteExtra.setBorderPainted(false);
        modCarteExtra.setContentAreaFilled(false);
        modCarteExtra.setFocusPainted(false);

        modInvertita.setBorderPainted(false);
        modInvertita.setContentAreaFilled(false);
        modInvertita.setFocusPainted(false);

        labelStandard.setText(convertToMultiline("La modalità standard segue le regole standard del gioco Uno," +
                "\nIl blocco, blocca il turno avversario," +
                "\nIl pesca due fa pescare due carte al giocatore successivo facendogli saltare il turno," +
                "\nL'inverti giro cambia la direzione di gioco," +
                "\nIl cambio colore permette di cambiare il colore in gioco," +
                "\nIl pesca quattro fa pescare quattro carte all'avversario facendogli passare il turno, inoltre puoi cambiare il colore in gioco"));
        labelStandard.setPreferredSize(new Dimension(300,300));
        labelStandard.setFont(font);

        labelCarteExtra.setText(convertToMultiline("La modalità carte speciali extra, segue le regole standard Uno," +
                "\n con l'aggiunta di alcune carte speciali a tua scelta," +
                "\n tra le quali:" +
                "\n Blocca tutti," +
                "\n Pesca due a tutti," +
                "\n Pesca tre a tutti," +
                "\n Scambio carte in mano," +
                "\n Blocca e inverti il giro," +
                "\n Duello contro un avversario."));
        labelCarteExtra.setPreferredSize(new Dimension(600,200));
        labelCarteExtra.setFont(font);

        labelInvertita.setText(convertToMultiline("La modalità invertita, segue le regole standard Uno," +
                "\n ma cambio il funzionamento di gioco, ovvero permette di giocare solo carte con numeri o colori diversi dall'ultima giocata," +
                "\n fatta eccezione per le carte nere, che rimangono carte Jolly"));
        labelInvertita.setPreferredSize(new Dimension(300,180));
        labelInvertita.setFont(font);

        tastoIndietro.setFont(font);
        tastoIndietro.setFocusPainted(false);

        modStandard.setActionCommand("STANDARD");
        modCarteExtra.setActionCommand("SCELTACARTE");
        modInvertita.setActionCommand("INVERTITA");
        tastoIndietro.setActionCommand("PRINCIPALE");

        ////////////////////   MODSTANDARD  ////////////////////
        GridBagConstraints gbc00 = new GridBagConstraints();
        gbc00.gridx = 0;
        gbc00.gridy = 0;
        gbc00.weightx = 0.3;
        add(modStandard,gbc00);

        ////////////////////   LABELSTANDARD  ////////////////////
        GridBagConstraints gbc01 = new GridBagConstraints();
        gbc01.gridx = 0;
        gbc01.gridy = 1;
        gbc01.weightx = 0.3;
        gbc01.anchor = GridBagConstraints.PAGE_START;
        add(labelStandard,gbc01);

        ////////////////////   MODCARTESPECIALI  ////////////////////
        GridBagConstraints gbc10 = new GridBagConstraints();
        gbc10.gridx = 1;
        gbc10.gridy = 0;
        gbc10.weightx = 0.3;
        add(modCarteExtra,gbc10);

        ////////////////////   LABELCARTESPECIALI  ////////////////////
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 1;
        gbc11.gridy = 1;
        gbc11.weightx = 0.3;
        gbc11.anchor = GridBagConstraints.PAGE_START;
        add(labelCarteExtra,gbc11);

        ////////////////////   MODINVERTITA  ////////////////////
        GridBagConstraints gbc20 = new GridBagConstraints();
        gbc20.gridx = 2;
        gbc20.gridy = 0;
        gbc20.weightx = 0.3;
        add(modInvertita,gbc20);

        ////////////////////   LABELINVERTITA  ////////////////////
        GridBagConstraints gbc22 = new GridBagConstraints();
        gbc22.gridx = 2;
        gbc22.gridy = 1;
        gbc22.weightx = 0.3;
        gbc22.anchor = GridBagConstraints.PAGE_START;
        add(labelInvertita,gbc22);

        ////////////////////   TASTO INDIETRO  ////////////////////
        GridBagConstraints gbc02 = new GridBagConstraints();
        gbc02.gridx = 0;
        gbc02.gridy = 2;
        gbc02.gridwidth = 3;
        add(tastoIndietro,gbc02);

        setVisible(false);
    }

    public JButton getModStandard() {
        return modStandard;
    }

    public JButton getModCarteExtra() {
        return modCarteExtra;
    }

    public JButton getModInvertita() {
        return modInvertita;
    }

    public JButton getTastoIndietro() {
        return tastoIndietro;
    }

    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
}
