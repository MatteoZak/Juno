package ProgettoFinale.View.Modalita;

import ProgettoFinale.View.FontPokemon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelezionaCarteExtra extends JLabel {
    private JLabel spiegazione = new JLabel();
    private JLabel labelBloccaTutti = new JLabel();
    private JLabel labelPescaDueTutti = new JLabel();
    private JLabel labelPescaTreTutti = new JLabel();
    private JLabel labelScambiaMani = new JLabel();
    private JLabel labelBloccoInverti = new JLabel();
    private JLabel labelDuello = new JLabel();

    private JCheckBox checkBloccaTutti = new JCheckBox();
    private JCheckBox checkPescaDueTutti = new JCheckBox();
    private JCheckBox checkPescaTreTutti = new JCheckBox();
    private JCheckBox checkScambiaMani = new JCheckBox();
    private JCheckBox checkBloccoInverti = new JCheckBox();
    private JCheckBox checkDuello = new JCheckBox();
    private JButton bottoneGioca = new JButton("Gioca");
    private JLabel spiegazioneBloccaTutti = new JLabel();
    private JLabel spiegazionePescaDueTutti = new JLabel();
    private JLabel spiegazionePescaTreTutti = new JLabel();
    private JLabel spiegazioneScambiaMani = new JLabel();
    private JLabel spiegazioneBloccoInverti = new JLabel();
    private JLabel spiegazioneDuello = new JLabel();
    private JButton tornaSceltaModalita = new JButton("Torna Indietro");


    private final String ACTIONCOMMAND = "CONTROLLO";
    public SelezionaCarteExtra(int width, int height) {
        setBounds(0, 0, width, height);
        setLayout(new GridBagLayout());
        Font font = new FontPokemon(20f).getFont();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        getCheckBoxes().forEach(x->x.setBackground(new java.awt.Color(42, 162, 42)));
        getLabelSpiegazioni().forEach(x->x.setFont(font));
        getLabelSpiegazioni().forEach(x->x.setPreferredSize(new Dimension(300,200)));
        getLabelCarte().forEach(x->x.setPreferredSize(new Dimension(100,150)));
        getLabelCarte().forEach(x->x.setEnabled(false));

        spiegazione.setFont(font);
        spiegazione.setText("Scegliere due di queste carte per poter giocare.");
                //"Ogni carta verrà aggiunta 4 volte a eccezione " +
                //"della carta Regalino che verrà aggiunta 2 volte");
        spiegazioneBloccaTutti.setText(convertToMultiline("Blocca Tutti: "+
                        "\nquando giocata blocca tutti gli avversari per un round"));
        labelBloccaTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola15.png")
                .getScaledInstance(100,150,16)));

        spiegazionePescaDueTutti.setText(convertToMultiline("Pesca Due Tutti: " +
                        "\nquando giocata tutti gli avversari pescano due carte" +
                "\n saltando il loro prossimo turno"));
        labelPescaDueTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola16.png")
                .getScaledInstance(100,150,16)));

        spiegazionePescaTreTutti.setText(convertToMultiline("Pesca Tre Tutti: " +
                        "\nquando giocata tutti gli avversari pescano tre carte" +
                "\n saltando il loro prossimo turno"));
        labelPescaTreTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola17.png")
                .getScaledInstance(100,150,16)));

        spiegazioneScambiaMani.setText(convertToMultiline("Scambia Mani: " +
                        "\nchi la gioca sceglie un avversario e scambia la sua mano con lui"));
        labelScambiaMani.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola18.png")
                .getScaledInstance(100,150,16)));

        spiegazioneBloccoInverti.setText(convertToMultiline("Blocca e Inverti Giro: " +
                        "\nquando giocata inverte il giro di gioco e blocca il prossimo avversario"));
        labelBloccoInverti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola19.png")
                .getScaledInstance(100,150,16)));

        spiegazioneDuello.setText(convertToMultiline("Duello: \nchi la gioca sceglie un avversario " +
                        "vengono prese le carte più alte nelle due mani e " +
                        "il giocatore con la carta più bassa pesca due carte"));
        labelDuello.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola20.png")
                .getScaledInstance(100,150,16)));

        checkBloccaTutti.setName("bloccotutti");
        checkPescaDueTutti.setName("pescaduetutti");
        checkPescaTreTutti.setName("pescatretutti");
        checkScambiaMani.setName("scambiomani");
        checkBloccoInverti.setName("bloccoinvertigiro");
        checkDuello.setName("duello");
        labelBloccaTutti.setName("bloccotutti");
        labelPescaDueTutti.setName("pescaduetutti");
        labelPescaTreTutti.setName("pescatretutti");
        labelScambiaMani.setName("scambiomani");
        labelBloccoInverti.setName("bloccoinvertigiro");
        labelDuello.setName("duello");

        getCheckBoxes().forEach(x->x.setActionCommand(ACTIONCOMMAND));
        bottoneGioca.setActionCommand("EXTRA");
        tornaSceltaModalita.setActionCommand("GIOCA");

        bottoneGioca.setEnabled(false);
        bottoneGioca.setFocusPainted(false);
        bottoneGioca.setFont(font);

        tornaSceltaModalita.setFocusPainted(false);
        tornaSceltaModalita.setFont(font);

        ////////////////////   SPIEGAZIONE  ////////////////////
        GridBagConstraints gbc00 = new GridBagConstraints();
        gbc00.gridx = 0;
        gbc00.gridy = 0;
        gbc00.gridwidth = 9;
        add(spiegazione,gbc00);

        ////////////////////   BLOCCATUTTI  ////////////////////
        GridBagConstraints gbc01 = new GridBagConstraints();
        gbc01.gridx = 0;
        gbc01.gridy = 1;
        add(labelBloccaTutti,gbc01);

        ////////////////////  CHECKBOX BLOCCATUTTI  ////////////////////
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 1;
        gbc11.gridy = 1;
        add(checkBloccaTutti,gbc11);

        ////////////////////  SPIEGAZIONE BLOCCATUTTI  ////////////////////
        GridBagConstraints gbc21 = new GridBagConstraints();
        gbc21.gridx = 2;
        gbc21.gridy = 1;
        add(spiegazioneBloccaTutti,gbc21);

        ////////////////////   PESCADUETUTTI  ////////////////////
        GridBagConstraints gbc02 = new GridBagConstraints();
        gbc02.gridx = 0;
        gbc02.gridy = 2;
        add(labelPescaDueTutti,gbc02);

        ////////////////////  CHECKBOX PESCADUETUTTI  ////////////////////
        GridBagConstraints gbc12 = new GridBagConstraints();
        gbc12.gridx = 1;
        gbc12.gridy = 2;
        add(checkPescaDueTutti,gbc12);

        ////////////////////  SPIEGAZIONE PESCADUETUTTI  ////////////////////
        GridBagConstraints gbc22 = new GridBagConstraints();
        gbc22.gridx = 2;
        gbc22.gridy = 2;
        add(spiegazionePescaDueTutti,gbc22);

        ////////////////////   PESCATRETUTTI  ////////////////////
        GridBagConstraints gbc31 = new GridBagConstraints();
        gbc31.gridx = 3;
        gbc31.gridy = 1;
        add(labelPescaTreTutti,gbc31);

        ////////////////////  CHECKBOX PESCATRETUTTI  ////////////////////
        GridBagConstraints gbc41 = new GridBagConstraints();
        gbc41.gridx = 4;
        gbc41.gridy = 1;
        add(checkPescaTreTutti,gbc41);

        ////////////////////  SPIEGAZIONE PESCATRETUTTI  ////////////////////
        GridBagConstraints gbc51 = new GridBagConstraints();
        gbc51.gridx = 5;
        gbc51.gridy = 1;
        add(spiegazionePescaTreTutti,gbc51);

        ////////////////////   SCAMBIAMANI  ////////////////////
        GridBagConstraints gbc32 = new GridBagConstraints();
        gbc32.gridx = 3;
        gbc32.gridy = 2;
        add(labelScambiaMani,gbc32);

        ////////////////////  CHECKBOX SCAMBIAMANI  ////////////////////
        GridBagConstraints gbc42 = new GridBagConstraints();
        gbc42.gridx = 4;
        gbc42.gridy = 2;
        add(checkScambiaMani,gbc42);

        ////////////////////  SPIEGAZIONE SCAMBIAMANI  ////////////////////
        GridBagConstraints gbc52 = new GridBagConstraints();
        gbc52.gridx = 5;
        gbc52.gridy = 2;
        add(spiegazioneScambiaMani,gbc52);

        ////////////////////   BLOCCOINVERTI  ////////////////////
        GridBagConstraints gbc61 = new GridBagConstraints();
        gbc61.gridx = 6;
        gbc61.gridy = 1;
        add(labelBloccoInverti,gbc61);

        ////////////////////  CHECKBOX BLOCCOINVERTI  ////////////////////
        GridBagConstraints gbc71 = new GridBagConstraints();
        gbc71.gridx = 7;
        gbc71.gridy = 1;
        add(checkBloccoInverti,gbc71);

        ////////////////////  SPIEGAZIONE BLOCCOINVERTI  ////////////////////
        GridBagConstraints gbc81 = new GridBagConstraints();
        gbc81.gridx = 8;
        gbc81.gridy = 1;
        add(spiegazioneBloccoInverti,gbc81);

        ////////////////////   REGALADUE  ////////////////////
        GridBagConstraints gbc62 = new GridBagConstraints();
        gbc62.gridx = 6;
        gbc62.gridy = 2;
        add(labelDuello,gbc62);

        ////////////////////  CHECKBOX REGALADUE  ////////////////////
        GridBagConstraints gbc72 = new GridBagConstraints();
        gbc72.gridx = 7;
        gbc72.gridy = 2;
        add(checkDuello,gbc72);

        ////////////////////  SPIEGAZIONE REGALADUE  ////////////////////
        GridBagConstraints gbc82 = new GridBagConstraints();
        gbc82.gridx = 8;
        gbc82.gridy = 2;
        add(spiegazioneDuello,gbc82);

        ////////////////////   BOTTONE GIOCA  ////////////////////
        GridBagConstraints gbc53 = new GridBagConstraints();
        gbc53.gridx = 5;
        gbc53.gridy = 3;
        gbc53.gridwidth = 5;
        add(bottoneGioca,gbc53);

        ////////////////////   BOTTONE GIOCA  ////////////////////
        GridBagConstraints gbc03 = new GridBagConstraints();
        gbc03.gridx = 0;
        gbc03.gridy = 3;
        gbc03.gridwidth = 4;
        add(tornaSceltaModalita,gbc03);

        setVisible(false);

    }
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    public List<JCheckBox> getCheckBoxes() {
        return new ArrayList<>(List.of(checkBloccaTutti,checkPescaDueTutti,checkPescaTreTutti,
                checkScambiaMani,checkBloccoInverti, checkDuello));
    }

    public List<JLabel> getLabelCarte(){
        return new ArrayList<>(List.of(labelBloccaTutti,labelPescaDueTutti,labelPescaTreTutti,labelScambiaMani,
                labelBloccoInverti, labelDuello));
    }

    public List<JLabel> getLabelSpiegazioni(){
        return new ArrayList<>(List.of(spiegazioneBloccaTutti,spiegazionePescaDueTutti,spiegazionePescaTreTutti,
                spiegazioneScambiaMani, spiegazioneBloccoInverti, spiegazioneDuello));
    }

    public JLabel getLabelBloccaTutti() {
        return labelBloccaTutti;
    }

    public JLabel getLabelPescaDueTutti() {
        return labelPescaDueTutti;
    }

    public JLabel getLabelPescaTreTutti() {
        return labelPescaTreTutti;
    }

    public JLabel getLabelScambiaMani() {
        return labelScambiaMani;
    }

    public JLabel getLabelBloccoInverti() {
        return labelBloccoInverti;
    }

    public JLabel getLabelDuello() {
        return labelDuello;
    }

    public JCheckBox getCheckBloccaTutti() {
        return checkBloccaTutti;
    }

    public JCheckBox getCheckPescaDueTutti() {
        return checkPescaDueTutti;
    }

    public JCheckBox getCheckPescaTreTutti() {
        return checkPescaTreTutti;
    }

    public JCheckBox getCheckScambiaMani() {
        return checkScambiaMani;
    }

    public JCheckBox getCheckBloccoInverti() {
        return checkBloccoInverti;
    }

    public JCheckBox getCheckDuello() {
        return checkDuello;
    }

    public JButton getBottoneGioca() {
        return bottoneGioca;
    }

    public JButton getTornaSceltaModalita() {
        return tornaSceltaModalita;
    }
}
