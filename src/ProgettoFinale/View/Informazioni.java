package ProgettoFinale.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Informazioni extends JLabel {

    private JLabel spiegazioneCarte = new JLabel("Spiegazione Carte:");

    private JLabel professore = new JLabel();

    private JLabel cartaNormale = new JLabel();
    private JLabel spiegazioneCartaNormale = new JLabel();

    private JLabel cartaBlocco = new JLabel();
    private JLabel spiegazioneCartaBlocco = new JLabel();

    private JLabel cartaInvertigiro = new JLabel();
    private JLabel spiegazioneCartaInvertigiro = new JLabel();

    private JLabel cartaPescaDue = new JLabel();
    private JLabel spiegazioneCartaPescaDue = new JLabel();

    private JLabel cartaPescaQuattro = new JLabel();
    private JLabel spiegazioneCartaPescaQuattro = new JLabel();

    private JLabel cartaCambiocolore = new JLabel();
    private JLabel spiegazioneCartaCambiocolore = new JLabel();

    private JLabel cartaBloccoTutti = new JLabel();
    private JLabel spiegazioneCartaBloccoTutti = new JLabel();

    private JLabel cartaPescadueTutti = new JLabel();
    private JLabel spiegazioneCartaPescadueTutti = new JLabel();

    private JLabel cartaPescatreTutti = new JLabel();
    private JLabel spiegazionePescatreTutti = new JLabel();

    private JLabel cartaInvertigiroBlocco = new JLabel();
    private JLabel spiegazioneCartaInvertigiroBlocco = new JLabel();

    private JLabel cartaScambioMani = new JLabel();
    private JLabel spiegazioneCartaScambioMani = new JLabel();

    private JLabel cartaDuello = new JLabel();
    private JLabel spiegazioneCartaDuello = new JLabel();

    private JButton bottoneCapito = new JButton("Capito, grazie!");

    public Informazioni(int width, int height){
        setBounds(0,0,width,height);
        setLayout(new GridBagLayout());
        setVisible(false);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        professore.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Utilita/ProfessorOak.png")
                .getScaledInstance(200,420,16)));
        professore.setPreferredSize(new Dimension(200,420));
        
        cartaNormale.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/rosso0.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaNormale.setText(convertToMultiline("Non ha un effetto\n speciale"));
        
        cartaInvertigiro.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/rosso10.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaInvertigiro.setText(convertToMultiline("Inverte la direzione\n di gioco"));
        
        cartaBlocco.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/rosso11.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaBlocco.setText(convertToMultiline("Blocca il giocatore\n successivo"));
        
        cartaPescaDue.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/rosso12.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaPescaDue.setText(convertToMultiline("Il giocatore successivo pesca\n 2 carte e salta" +
                " il turno"));
        
        cartaCambiocolore.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/nero13.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaCambiocolore.setText(convertToMultiline("Scegli un colore, cambia gli\n scarti con " +
                "quel colore"));

        cartaPescaQuattro.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/nero14.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaPescaQuattro.setText(convertToMultiline("Il giocatore successivo pesca\n 4 carte e " +
                "salta il turno"));

        cartaBloccoTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola15.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaBloccoTutti.setText(convertToMultiline("Blocca tutti \ngli avversari"));

        cartaPescadueTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola16.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaPescadueTutti.setText(convertToMultiline("Gli avversari \npescano 2 carte"));

        cartaPescatreTutti.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola17.png")
                .getScaledInstance(100,150,16)));
        spiegazionePescatreTutti.setText(convertToMultiline("Gli avversari \npescano 3 carte"));

        cartaScambioMani.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola18.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaScambioMani.setText(convertToMultiline("Scegli un giocatore,\n scambia la tua mano con " +
                "la sua"));

        cartaInvertigiroBlocco.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola19.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaInvertigiroBlocco.setText(convertToMultiline("Cambia la direzione\n e blocca " +
                "il giocatore successivo"));

        cartaDuello.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Carte/viola20.png")
                .getScaledInstance(100,150,16)));
        spiegazioneCartaDuello.setText(convertToMultiline("Scegli un giocatore,\n ognuno prende la carta " +
                "\ncol valore più alto nella propria mano,\n chi ha la carta col valore più basso\n pesca 2 carte"));

        Font font = new FontPokemon((width-100)/100f).getFont();
        getListaInformazioni().forEach(x -> x.setFont(font));
        spiegazioneCarte.setFont(font.deriveFont(23f));
        spiegazioneCarte.setPreferredSize(new Dimension(300,40));
        getListaCarte().forEach(x -> x.setPreferredSize(new Dimension(100,150)));

        bottoneCapito.setFocusPainted(false);

        bottoneCapito.setActionCommand("CAPITO");


        ////////////////////   TITOLO  ////////////////////
        GridBagConstraints gbc00 = new GridBagConstraints();
        gbc00.gridx = 0;
        gbc00.gridy = 0;
        gbc00.gridwidth = 9;
        add(spiegazioneCarte,gbc00);

        ////////////////////   PROFESSORE  ////////////////////
        GridBagConstraints gbc01 = new GridBagConstraints();
        gbc01.gridx = 0;
        gbc01.gridy = 1;
        gbc01.gridheight = 3;
        gbc01.anchor = GridBagConstraints.LINE_START;
        gbc01.insets = new Insets(0,0,0,30);
        add(professore,gbc01);

        ////////////////////   CARTANORMALE  ////////////////////
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 1;
        gbc11.gridy = 1;
        gbc11.insets = new Insets(10,10,10,10);
        add(cartaNormale,gbc11);

        ////////////////////  SPIEGAZIONE CARTANORMALE  ////////////////////
        GridBagConstraints gbc21 = new GridBagConstraints();
        gbc21.gridx = 2;
        gbc21.gridy = 1;
        gbc21.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaNormale,gbc21);

        ////////////////////  CARTABLOCCO  ////////////////////
        GridBagConstraints gbc12 = new GridBagConstraints();
        gbc12.gridx = 1;
        gbc12.gridy = 2;
        gbc12.insets = new Insets(10,10,10,10);
        add(cartaBlocco,gbc12);

        ////////////////////   SPIEGAZIONE CARTABLOCCO  ////////////////////
        GridBagConstraints gbc22 = new GridBagConstraints();
        gbc22.gridx = 2;
        gbc22.gridy = 2;
        gbc22.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaBlocco,gbc22);

        ////////////////////  INVERTIGIRO  ////////////////////
        GridBagConstraints gbc13 = new GridBagConstraints();
        gbc13.gridx = 1;
        gbc13.gridy = 3;
        gbc13.insets = new Insets(10,10,10,10);
        add(cartaInvertigiro,gbc13);

        ////////////////////  SPIEGAZIONE INVERTIGIRO  ////////////////////
        GridBagConstraints gbc23 = new GridBagConstraints();
        gbc23.gridx = 2;
        gbc23.gridy = 3;
        gbc23.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaInvertigiro,gbc23);

        ////////////////////   PESCADUE  ////////////////////
        GridBagConstraints gbc31 = new GridBagConstraints();
        gbc31.gridx = 3;
        gbc31.gridy = 1;
        gbc31.insets = new Insets(10,10,10,10);
        add(cartaPescaDue,gbc31);

        ////////////////////  SPIEGAZIONE PESCADUE  ////////////////////
        GridBagConstraints gbc41 = new GridBagConstraints();
        gbc41.gridx = 4;
        gbc41.gridy = 1;
        gbc41.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaPescaDue,gbc41);

        ////////////////////  PESCAQUATTRO  ////////////////////
        GridBagConstraints gbc32 = new GridBagConstraints();
        gbc32.gridx = 3;
        gbc32.gridy = 2;
        gbc32.insets = new Insets(10,10,10,10);
        add(cartaPescaQuattro,gbc32);

        ////////////////////   SPIEGAZIONE PESCQUATTRO  ////////////////////
        GridBagConstraints gbc42 = new GridBagConstraints();
        gbc42.gridx = 4;
        gbc42.gridy = 2;
        gbc42.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaPescaQuattro,gbc42);

        ////////////////////  CAMBIOCOLORE  ////////////////////
        GridBagConstraints gbc33 = new GridBagConstraints();
        gbc33.gridx = 3;
        gbc33.gridy = 3;
        gbc33.insets = new Insets(10,10,10,10);
        add(cartaCambiocolore,gbc33);

        ////////////////////  SPIEGAZIONE CAMBIOCOLORE  ////////////////////
        GridBagConstraints gbc43 = new GridBagConstraints();
        gbc43.gridx = 4;
        gbc43.gridy = 3;
        gbc43.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaCambiocolore,gbc43);

        ////////////////////   BLOCCOTUTTI  ////////////////////
        GridBagConstraints gbc51 = new GridBagConstraints();
        gbc51.gridx = 5;
        gbc51.gridy = 1;
        gbc51.insets = new Insets(10,10,10,10);
        add(cartaBloccoTutti,gbc51);

        ////////////////////  SPIEGAZIONE BLOCCOTUTTI  ////////////////////
        GridBagConstraints gbc61 = new GridBagConstraints();
        gbc61.gridx = 6;
        gbc61.gridy = 1;
        gbc61.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaBloccoTutti,gbc61);

        ////////////////////  PESCADUETUTTI  ////////////////////
        GridBagConstraints gbc52 = new GridBagConstraints();
        gbc52.gridx = 5;
        gbc52.gridy = 2;
        gbc52.insets = new Insets(10,10,10,10);
        add(cartaPescadueTutti,gbc52);

        ////////////////////   SPIEGAZIONE PESCADUETUTTI  ////////////////////
        GridBagConstraints gbc62 = new GridBagConstraints();
        gbc62.gridx = 6;
        gbc62.gridy = 2;
        gbc62.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaPescadueTutti,gbc62);

        ////////////////////  PESCATRETUTTI  ////////////////////
        GridBagConstraints gbc53 = new GridBagConstraints();
        gbc53.gridx = 5;
        gbc53.gridy = 3;
        gbc53.insets = new Insets(10,10,10,10);
        add(cartaPescatreTutti,gbc53);

        ////////////////////  SPIEGAZIONE PESCATRETUTTI  ////////////////////
        GridBagConstraints gbc63 = new GridBagConstraints();
        gbc63.gridx = 6;
        gbc63.gridy = 3;
        gbc63.insets = new Insets(10,10,10,10);
        add(spiegazionePescatreTutti,gbc63);

        ////////////////////   INVERTIGIROBLOCCO  ////////////////////
        GridBagConstraints gbc71 = new GridBagConstraints();
        gbc71.gridx = 7;
        gbc71.gridy = 1;
        gbc71.insets = new Insets(10,10,10,10);
        add(cartaInvertigiroBlocco,gbc71);

        ////////////////////  SPIEGAZIONE INVERTIGIROBLOCCO  ////////////////////
        GridBagConstraints gbc81 = new GridBagConstraints();
        gbc81.gridx = 8;
        gbc81.gridy = 1;
        gbc81.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaInvertigiroBlocco,gbc81);

        ////////////////////  SCAMBIO MANI  ////////////////////
        GridBagConstraints gbc72 = new GridBagConstraints();
        gbc72.gridx = 7;
        gbc72.gridy = 2;
        gbc72.insets = new Insets(10,10,10,10);
        add(cartaScambioMani,gbc72);

        ////////////////////   SPIEGAZIONE SCAMBIOMANI  ////////////////////
        GridBagConstraints gbc82 = new GridBagConstraints();
        gbc82.gridx = 8;
        gbc82.gridy = 2;
        gbc82.insets = new Insets(10,10,10,10);
        add(spiegazioneCartaScambioMani,gbc82);

        ////////////////////   DUELLO  ////////////////////
        GridBagConstraints gbc73 = new GridBagConstraints();
        gbc73.gridx = 7;
        gbc73.gridy = 3;
        gbc73.insets = new Insets(10,10,10,10);
        add(cartaDuello,gbc73);

        ////////////////////   SPIEGAZIONE DUELLO  ////////////////////
        GridBagConstraints gbc83 = new GridBagConstraints();
        gbc83.gridx = 8;
        gbc83.gridy = 3;
        add(spiegazioneCartaDuello,gbc83);

        ////////////////////   BOTTONE CAPITO  ////////////////////
        GridBagConstraints gbc04 = new GridBagConstraints();
        gbc04.gridx = 0;
        gbc04.gridy = 4;
        gbc04.gridwidth = 9;
        add(bottoneCapito,gbc04);

    }
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

    public JButton getBottoneCapito() {
        return bottoneCapito;
    }

    public List<JLabel> getListaInformazioni(){
        return new LinkedList<>(List.of(spiegazioneCartaNormale, spiegazioneCartaBlocco, spiegazioneCartaInvertigiro,
                spiegazioneCartaPescaDue, spiegazioneCartaPescaQuattro, spiegazioneCartaCambiocolore,
                spiegazioneCartaBloccoTutti, spiegazioneCartaPescadueTutti, spiegazionePescatreTutti,
                spiegazioneCartaInvertigiroBlocco, spiegazioneCartaScambioMani, spiegazioneCartaDuello));
    }

    public List<JLabel> getListaCarte(){
        return new LinkedList<>(List.of(cartaNormale, cartaBlocco, cartaInvertigiro,
                cartaPescaDue, cartaPescaQuattro, cartaCambiocolore,
                cartaBloccoTutti, cartaPescadueTutti, cartaPescatreTutti,
                cartaInvertigiroBlocco, cartaScambioMani, cartaDuello));
    }

    public List<JLabel> getListaCarteViola(){
        return new LinkedList<>(List.of(spiegazioneCartaBloccoTutti, spiegazioneCartaPescadueTutti, spiegazionePescatreTutti,
                spiegazioneCartaInvertigiroBlocco, spiegazioneCartaScambioMani, spiegazioneCartaDuello,
                cartaBloccoTutti, cartaPescadueTutti, cartaPescatreTutti,
                cartaInvertigiroBlocco, cartaScambioMani, cartaDuello));
    }

    public void nascondiCarteViola(){
        getListaCarteViola().stream().forEach(x -> x.setVisible(false));
    }
}
