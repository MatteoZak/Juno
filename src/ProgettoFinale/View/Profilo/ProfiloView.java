package ProgettoFinale.View.Profilo;

import ProgettoFinale.View.FontPokemon;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe utilizzata per la creazione del profilo utente a schermo
 * utilizzando dei label e il GridBagLayout
 */
public class ProfiloView extends JLabel {
    private JFileChooser fileChooser;
    private JLabel menuProfilo = new JLabel();
    private JLabel labelIcona = new JLabel();
    private JButton scelta = new JButton("Scegli");

    private JLabel labelUsername = new JLabel("Username: ");
    private JTextField username = new JTextField(15);
    private JButton applica = new JButton("Applica");

    private JLabel giocate = new JLabel();

    private JLabel vittorie = new JLabel();

    private JLabel sconfitte = new JLabel();
    private JLabel stellaLivello = new JLabel();
    private JLabel livello = new JLabel();

    private JButton azzeraDati = new JButton("Azzera");
    private String usernameStr;

    private JButton uscita = new JButton();

    /**
     * Costruttore con le dimensioni dello schermo
     * @param width
     * @param height
     */
    public ProfiloView(int width, int height){
        setSize(width, height);
        setVisible(false);

        Font font = new FontPokemon(20f).getFont();

        menuProfilo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Utilita/SchedaAllenatore.png")
                .getScaledInstance(600,400, 16)));

        menuProfilo.setBounds(width/2-300,height/2-200,600,400);
        menuProfilo.setLayout(new GridBagLayout());
//        menuProfilo.setBorder(BorderFactory.createLineBorder(Color.BLACK,15,true));

        labelIcona.setPreferredSize(new Dimension(100,100));
        labelIcona.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5,true));

        labelUsername.setFont(font);
        username.setFont(font);
        username.setText(usernameStr);

        giocate.setFont(font);
        giocate.setPreferredSize(new Dimension(535,30));
        vittorie.setFont(font);
        vittorie.setPreferredSize(new Dimension(345,30));
        sconfitte.setFont(font);
        sconfitte.setPreferredSize(new Dimension(345,30));
        livello.setFont(font);
        livello.setPreferredSize(new Dimension(345,30));

        fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Risorse/images/Avatars/IconaGiocatore.png"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files (*.PNG,*.JPG, *.JPEG)" ,"PNG","JPG", "JPEG"));

        scelta.setActionCommand("SCEGLI");
        applica.setActionCommand("APPLICA");
        uscita.setActionCommand("PRINCIPALE");
        azzeraDati.setActionCommand("AZZERADATI");

        uscita.setPreferredSize(new Dimension(30,30));

        uscita.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Utilita/uscita.png")
                .getScaledInstance(30,30, 16)));
        uscita.setBorderPainted(false);
        uscita.setContentAreaFilled(false);

        applica.setFocusPainted(false);
        applica.setFont(font);
        scelta.setFocusPainted(false);
        scelta.setFont(font);
        azzeraDati.setFocusPainted(false);
        azzeraDati.setFont(font);

////////////////////////    GRIDBAG    ///////////////////////////////

        ////////////////////   USCITA  ////////////////////
        GridBagConstraints gbc20 = new GridBagConstraints();
        gbc20.gridx = 2;
        gbc20.gridy = 0;
        gbc20.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc20.insets = new Insets(0,0,0,0);
        menuProfilo.add(uscita,gbc20);

        ////////////////////   LABELICONA  ////////////////////
        GridBagConstraints gbc23 = new GridBagConstraints();
        gbc23.gridx = 2;
        gbc23.gridy = 3;
        gbc23.insets = new Insets(5,23,0,0);
        gbc23.anchor = GridBagConstraints.LINE_START;
        gbc23.gridheight = 2;
        gbc23.weightx = 0.4;
        menuProfilo.add(labelIcona,gbc23);

        ////////////////////   SCELTA  ////////////////////
        GridBagConstraints gbc25 = new GridBagConstraints();
        gbc25.gridx = 2;
        gbc25.gridy = 5;
        gbc25.anchor = GridBagConstraints.LINE_START;
        gbc25.weighty = 0.5;
        gbc25.insets = new Insets(0,30,0,0);
        menuProfilo.add(scelta,gbc25);

        ////////////////////   LABELUSERNAME  ////////////////////
        GridBagConstraints gbc01 = new GridBagConstraints();
        gbc01.gridx = 0;
        gbc01.gridy = 1;
        gbc01.insets = new Insets(0,20,20,0);
        menuProfilo.add(labelUsername,gbc01);

        ////////////////////   USERNAME  ////////////////////
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 1;
        gbc11.gridy = 1;
        gbc11.weighty = 0.5;
        gbc11.insets = new Insets(0,0,20,0);
        gbc11.anchor = GridBagConstraints.LINE_START;
        menuProfilo.add(username,gbc11);

        ////////////////////   GIOCATE  ////////////////////
        GridBagConstraints gbc02 = new GridBagConstraints();
        gbc02.gridx = 0;
        gbc02.gridy = 2;
        gbc02.weighty = 0.5;
        gbc02.gridwidth = 3;
        gbc02.insets = new Insets(0,20,0,0);
        gbc02.anchor = GridBagConstraints.LINE_START;
        menuProfilo.add(giocate,gbc02);

        ////////////////////   VITTORIE  ////////////////////
        GridBagConstraints gbc03 = new GridBagConstraints();
        gbc03.gridx = 0;
        gbc03.gridy = 3;
        gbc03.weighty = 0.5;
        gbc03.gridwidth = 2;
        gbc03.insets = new Insets(20,20,0,0);
        gbc03.anchor = GridBagConstraints.LINE_START;
        menuProfilo.add(vittorie,gbc03);

        ////////////////////   SCONFITTE  ////////////////////
        GridBagConstraints gbc04 = new GridBagConstraints();
        gbc04.gridx = 0;
        gbc04.gridy = 4;
        gbc04.weighty = 0.5;
        gbc04.gridwidth = 2;
        gbc04.insets = new Insets(20,20,0,0);
        gbc04.anchor = GridBagConstraints.LINE_START;
        menuProfilo.add(sconfitte,gbc04);

        ////////////////////   LIVELLO  ////////////////////
        GridBagConstraints gbc05 = new GridBagConstraints();
        gbc05.gridx = 0;
        gbc05.gridy = 5;
        gbc05.weighty = 0.5;
        gbc05.gridwidth = 2;
        gbc05.insets = new Insets(20,20,0,0);
        gbc05.anchor = GridBagConstraints.LINE_START;
        menuProfilo.add(livello,gbc05);

        ////////////////////   APPLICA  ////////////////////
        GridBagConstraints gbc16 = new GridBagConstraints();
        gbc16.gridx = 1;
        gbc16.gridy = 6;
        gbc16.anchor = GridBagConstraints.CENTER;
        gbc16.weighty = 0.5;
        gbc16.gridwidth = 2;
        gbc16.insets = new Insets(30,0,30,0);
        menuProfilo.add(applica,gbc16);

        ////////////////////   AZZERADATI  ////////////////////
        GridBagConstraints gbc06 = new GridBagConstraints();
        gbc06.gridx = 0;
        gbc06.gridy = 6;
        gbc06.anchor = GridBagConstraints.CENTER;
        gbc06.weighty = 0.5;
        gbc06.insets = new Insets(30,0,30,0);
        menuProfilo.add(azzeraDati,gbc06);

////////////////////////////////////////////////////////////////////

        add(menuProfilo);
    }

    /**
     * Metodo che ritorna il bottone per azzerare i dati dell'utente
     * @return
     */
    public JButton getAzzeraDati() {
        return azzeraDati;
    }

    /**
     * Metodo che ritorna il nome dell'utente
     * @return
     */
    public String getUsername() {return username.getText();}

    /**
     * Metodo che sovrascrive il nome dell'utente
     * @param str
     */
    public void setUsername(String str) {
        this.username.setText(str);
    }

    /**
     * Metodo che ritorna il file scelto
     * @return
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * Metodo che ritorna il label dell'icona dell'utente
     * @return
     */
    public JLabel getLabelIcona(){
        return labelIcona;
    }
    public Image getIcona() {
        return Toolkit.getDefaultToolkit().createImage(fileChooser.getSelectedFile().getAbsolutePath());
    }

    /**
     * Metodo che ritorna il bottone per scegliere l'immagine
     * @return
     */
    public JButton getScelta() {
        return scelta;
    }

    /**
     * Metodo che ritorna il bottone per confermare le modifiche
     * @return
     */
    public JButton getApplica() {
        return applica;
    }

    /**
     * Metodo che ritorna il label delle partite giocate dall'utente
     * @return
     */
    public JLabel getGiocate() {
        return giocate;
    }

    /**
     * Metodo che ritorna il label delle partite vinte dall'utente
     * @return
     */
    public JLabel getVittorie() {
        return vittorie;
    }

    /**
     * Metodo che ritorna il label delle partite perse dall'utente
     * @return
     */
    public JLabel getSconfitte() {
        return sconfitte;
    }

    public JLabel getStellaLivello() {
        return stellaLivello;
    }

    /**
     * Metodo che ritorna il label del livello del giocatore
     * @return
     */
    public JLabel getLivello() {
        return livello;
    }

    public void setUsernameStr(String usernameStr) {
        this.usernameStr = usernameStr;
    }

    public String getUsernameStr() {
        return usernameStr;
    }

    /**
     * Metodo che ritorna il bottone per uscire dal profilo
     * @return
     */
    public JButton getUscita() {
        return uscita;
    }

}


//    ////////////////////   USCITA  ////////////////////
//    GridBagConstraints gbc30 = new GridBagConstraints();
//        gbc30.gridx = 3;
//                gbc30.gridy = 0;
//                gbc30.anchor = GridBagConstraints.FIRST_LINE_END;
//                gbc30.insets = new Insets(4,0,5,0);
//                menuProfilo.add(uscita,gbc30);
//
//                ////////////////////   LABELICONA  ////////////////////
//                GridBagConstraints gbc01 = new GridBagConstraints();
//                gbc01.gridx = 0;
//                gbc01.gridy = 2;
//                gbc01.insets = new Insets(5,5,5,5);
//                gbc01.weighty = 0.5;
//                gbc01.gridheight = 8;
//                menuProfilo.add(labelIcona,gbc01);
//
//                ////////////////////   CONFERMANOME  ////////////////////
//                GridBagConstraints gbc31 = new GridBagConstraints();
//                gbc31.gridx = 0;
//                gbc31.gridy = 9;
//                gbc31.weighty = 0.5;
//                menuProfilo.add(scelta,gbc31);
//
//                ////////////////////   LABELUSERNAME  ////////////////////
//                GridBagConstraints gbc11 = new GridBagConstraints();
//                gbc11.gridx = 1;
//                gbc11.gridy = 2;
//                gbc11.weighty = 0.5;
//                menuProfilo.add(labelUsername,gbc11);
//
//                ////////////////////   USERNAME  ////////////////////
//                GridBagConstraints gbc21 = new GridBagConstraints();
//                gbc21.gridx = 2;
//                gbc21.gridy = 2;
//                gbc21.weighty = 0.5;
//                menuProfilo.add(username,gbc21);
//
//                ////////////////////   GIOCATE  ////////////////////
//                GridBagConstraints gbc13 = new GridBagConstraints();
//                gbc13.gridx = 1;
//                gbc13.gridy = 4;
//                gbc13.weighty = 0.5;
//                menuProfilo.add(giocate,gbc13);
//
//                ////////////////////   VITTORIE  ////////////////////
//                GridBagConstraints gbc14 = new GridBagConstraints();
//                gbc14.gridx = 1;
//                gbc14.gridy = 5;
//                gbc14.weighty = 0.5;
//                menuProfilo.add(vittorie,gbc14);
//
//                ////////////////////   SCONFITTE  ////////////////////
//                GridBagConstraints gbc15 = new GridBagConstraints();
//                gbc15.gridx = 1;
//                gbc15.gridy = 6;
//                gbc15.weighty = 0.5;
//                menuProfilo.add(sconfitte,gbc15);
//
//                ////////////////////   LIVELLO  ////////////////////
//                GridBagConstraints gbc16 = new GridBagConstraints();
//                gbc16.gridx = 1;
//                gbc16.gridy = 7;
//                gbc16.weighty = 0.5;
//                menuProfilo.add(livello,gbc16);
//
//                ////////////////////   APPLICA  ////////////////////
//                GridBagConstraints gbc07 = new GridBagConstraints();
//                gbc09.gridx = 0;
//                gbc09.gridy = 7;
//                gbc09.gridwidth = 4;
//                gbc09.weighty = 0.5;
//                gbc09.anchor = GridBagConstraints.CENTER;
//                menuProfilo.add(applica,gbc09);

//        ////////////////////   AZZERADATI  ////////////////////
//        GridBagConstraints gbc010 = new GridBagConstraints();
//        gbc30.gridx = 3;
//        gbc30.gridy = 0;
//        gbc30.anchor = GridBagConstraints.FIRST_LINE_END;
//        gbc30.insets = new Insets(4,0,5,0);
//        menuProfilo.add(azzeraDati,gbc30);
