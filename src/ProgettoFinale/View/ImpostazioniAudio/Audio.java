package ProgettoFinale.View.ImpostazioniAudio;


import ProgettoFinale.View.FontPokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

/**
 * Classe che rappresenta la schermata dell'audio nel gioco
 */
public class Audio extends JLabel {
    private JSlider barraVolumeAmbiente = new JSlider(-80,5);
    private JSlider barraVolumeEffetti = new JSlider(-80,5);
    private JSlider barraVolumeVersi = new JSlider(-80,5);
    private JTextField volumeAmbiente = new JTextField(2);
    private JTextField volumeEffetti = new JTextField(2);
    private JTextField volumeVersi = new JTextField(2);

    private JLabel labelAmbiente = new JLabel("Ambiente");
    private JLabel labelEffetti = new JLabel("Effetti");
    private JLabel labelVersi = new JLabel("Voci");

    private JButton uscita = new JButton("Menu Principale");
    private JButton riprendiPartita = new JButton("Riprendi");
    private JPanel menuAudio = new JPanel();

    /**
     * Costruttore creato con le dimensioni dello schermo e con un
     * GridBagLayout
     * @param width
     * @param height
     */
    public Audio(int width, int height){

        Font font = new FontPokemon(20f).getFont();


        setSize(width,height);

        setLayout(null);

        menuAudio.setBounds(width/2-300,height/2-200,600,400);
        menuAudio.setLayout(new GridBagLayout());
        menuAudio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10,true));

////////////////////////////////////////////// AMBIENTE //////////////////////////////////////////////
        labelAmbiente.setPreferredSize(new Dimension(580,30));
        labelAmbiente.setHorizontalAlignment(CENTER);
        labelAmbiente.setFont(font);
        barraVolumeAmbiente.setMajorTickSpacing(17);
        barraVolumeAmbiente.setPaintTicks(true);

        Hashtable labelTableAmbiente = new Hashtable();
        labelTableAmbiente.put( 5, new JLabel("100") );
        labelTableAmbiente.put( -12, new JLabel("80") );
        labelTableAmbiente.put( -29, new JLabel("60") );
        labelTableAmbiente.put( -46, new JLabel("40") );
        labelTableAmbiente.put( -63, new JLabel("20") );
        labelTableAmbiente.put( -80, new JLabel("0") );
        barraVolumeAmbiente.setLabelTable(labelTableAmbiente);

        barraVolumeAmbiente.setPaintLabels(true);
        barraVolumeAmbiente.setPreferredSize(new Dimension(530,50));

        volumeAmbiente.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    barraVolumeAmbiente.setValue((int) (Integer.parseInt(volumeAmbiente.getText())*0.85F-80.0F));
                }
            }
        });

        menuAudio.add(labelAmbiente);
        menuAudio.add(barraVolumeAmbiente);
        menuAudio.add(volumeAmbiente);

//////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////// EFFETTI //////////////////////////////////////////////
        labelEffetti.setPreferredSize(new Dimension(580,30));
        labelEffetti.setHorizontalAlignment(CENTER);
        labelEffetti.setFont(font);
        barraVolumeEffetti.setMajorTickSpacing(17);
        barraVolumeEffetti.setPaintTicks(true);

        Hashtable labelTableEffetti = new Hashtable();
        labelTableEffetti.put( 5, new JLabel("100") );
        labelTableEffetti.put( -12, new JLabel("80") );
        labelTableEffetti.put( -29, new JLabel("60") );
        labelTableEffetti.put( -46, new JLabel("40") );
        labelTableEffetti.put( -63, new JLabel("20") );
        labelTableEffetti.put( -80, new JLabel("0") );
        barraVolumeEffetti.setLabelTable(labelTableEffetti);

        barraVolumeEffetti.setPaintLabels(true);
        barraVolumeEffetti.setPreferredSize(new Dimension(530,50));

        volumeEffetti.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    barraVolumeEffetti.setValue((int) (Integer.parseInt(volumeEffetti.getText())*0.85F-80.0F));
                }
            }
        });


        menuAudio.add(labelEffetti);
        menuAudio.add(barraVolumeEffetti);
        menuAudio.add(volumeEffetti);

//////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////// VERSI //////////////////////////////////////////////
        labelVersi.setPreferredSize(new Dimension(580,30));
        labelVersi.setHorizontalAlignment(CENTER);
        labelVersi.setFont(font);
        barraVolumeVersi.setMajorTickSpacing(17);
        barraVolumeVersi.setPaintTicks(true);

        Hashtable labelTableVoci = new Hashtable();
        labelTableVoci.put( 5, new JLabel("100") );
        labelTableVoci.put( -12, new JLabel("80") );
        labelTableVoci.put( -29, new JLabel("60") );
        labelTableVoci.put( -46, new JLabel("40") );
        labelTableVoci.put( -63, new JLabel("20") );
        labelTableVoci.put( -80, new JLabel("0") );
        barraVolumeVersi.setLabelTable(labelTableVoci);

        barraVolumeVersi.setPaintLabels(true);
        barraVolumeVersi.setPreferredSize(new Dimension(530,50));

        volumeVersi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    barraVolumeVersi.setValue((int) (Integer.parseInt(volumeVersi.getText())*0.85F-80.0F));
                }
            }
        });

        menuAudio.add(labelVersi);
        menuAudio.add(barraVolumeVersi);
        menuAudio.add(volumeVersi);

//////////////////////////////////////////////////////////////////////////////////////////////////////

        uscita.setActionCommand("PRINCIPALE");
        uscita.setPreferredSize(new Dimension(190,30));
        uscita.setFont(font);
        uscita.setFocusPainted(false);

        riprendiPartita.setActionCommand("RIPRENDIPARTITA");
        riprendiPartita.setPreferredSize(new Dimension(120,30));
        riprendiPartita.setFont(font);
        riprendiPartita.setFocusPainted(false);
        riprendiPartita.setVisible(false);

////////////////////////    GRIDBAG    ///////////////////////////////

        ////////////////////   LABELAMBIENTE  ////////////////////
        GridBagConstraints gbc00 = new GridBagConstraints();
        gbc00.gridx = 0;
        gbc00.gridy = 0;
        gbc00.gridwidth = 3;
        menuAudio.add(labelAmbiente,gbc00);

        ////////////////////   BARRAVOLUMEAMBIENTE  ////////////////////
        GridBagConstraints gbc01 = new GridBagConstraints();
        gbc01.gridx = 0;
        gbc01.gridy = 1;
        gbc01.gridwidth = 2;
        gbc01.insets = new Insets(10,0,10,0);
        menuAudio.add(barraVolumeAmbiente,gbc01);

        ////////////////////   VOLUMEAMBIENTE  ////////////////////
        GridBagConstraints gbc11 = new GridBagConstraints();
        gbc11.gridx = 2;
        gbc11.gridy = 1;
        menuAudio.add(volumeAmbiente,gbc11);

        ////////////////////   LABELEFFETTI  ////////////////////
        GridBagConstraints gbc02 = new GridBagConstraints();
        gbc02.gridx = 0;
        gbc02.gridy = 2;
        gbc02.gridwidth = 3;
        menuAudio.add(labelEffetti,gbc02);

        ////////////////////   BARRAVOLUMEEFFETTI  ////////////////////
        GridBagConstraints gbc03 = new GridBagConstraints();
        gbc03.gridx = 0;
        gbc03.gridy = 3;
        gbc03.gridwidth = 2;
        gbc03.insets = new Insets(10,0,10,0);
        menuAudio.add(barraVolumeEffetti,gbc03);

        ////////////////////   VOLUMEEFFETTI  ////////////////////
        GridBagConstraints gbc13 = new GridBagConstraints();
        gbc13.gridx = 2;
        gbc13.gridy = 3;
        menuAudio.add(volumeEffetti,gbc13);

        ////////////////////   LABELVOCI  ////////////////////
        GridBagConstraints gbc04 = new GridBagConstraints();
        gbc04.gridx = 0;
        gbc04.gridy = 4;
        gbc04.gridwidth = 3;
        menuAudio.add(labelVersi,gbc04);

        ////////////////////   BARRAVOLUMEVOCI  ////////////////////
        GridBagConstraints gbc05 = new GridBagConstraints();
        gbc05.gridx = 0;
        gbc05.gridy = 5;
        gbc05.gridwidth = 2;
        gbc05.insets = new Insets(10,0,10,0);
        menuAudio.add(barraVolumeVersi,gbc05);

        ////////////////////   VOLUMEVERSI  ////////////////////
        GridBagConstraints gbc25 = new GridBagConstraints();
        gbc25.gridx = 2;
        gbc25.gridy = 5;
        menuAudio.add(volumeVersi,gbc25);

        ////////////////////   RIPRENDI  ////////////////////
        GridBagConstraints gbc06 = new GridBagConstraints();
        gbc06.gridx = 0;
        gbc06.gridy = 6;
        gbc06.anchor = GridBagConstraints.CENTER;
        gbc06.insets = new Insets(0,15,0,0);
        menuAudio.add(riprendiPartita,gbc06);

        ////////////////////   USCITA  ////////////////////
        GridBagConstraints gbc26 = new GridBagConstraints();
        gbc26.gridx = 1;
        gbc26.gridy = 6;
        menuAudio.add(uscita,gbc26);

////////////////////////////////////////////////////////////////////



        add(menuAudio);

        setVisible(false);
    }

    /**
     * Metodo che ritorna il bottone per  uscire dalle
     * impostazioni audio
     * @return
     */
    public JButton getUscita() {
        return uscita;
    }

    /**
     * Metodo che ritorna il text field per gli effetti sonori
     * @return
     */
    public JTextField getVolumeEffetti() {
        return volumeEffetti;
    }

    /**
     * Metodo che ritorna lo slider degli effetti speciali
     * @return
     */
    public JSlider getBarraVolumeEffetti() {
        return barraVolumeEffetti;
    }
    /**
     * Metodo che ritorna lo slider della musica in sottofondo
     * @return
     */
    public JSlider getBarraVolumeAmbiente() {
        return barraVolumeAmbiente;
    }
    /**
     * Metodo che ritorna il text field per la musica in sottofondo
     * @return
     */
    public JTextField getVolumeAmbiente() {
        return volumeAmbiente;
    }
    /**
     * Metodo che ritorna lo slider dei versi degli avversari
     * @return
     */
    public JSlider getBarraVolumeVersi() {
        return barraVolumeVersi;
    }
    /**
     * Metodo che ritorna il text field per gi versi degli avversari
     * @return
     */
    public JTextField getVolumeVersi() {
        return volumeVersi;
    }

    /**
     * Metodo che ritorna il bottone per riprendere la partita
     * @return
     */
    public JButton getRiprendiPartita() {
        return riprendiPartita;
    }

    public JLabel getLabelAmbiente() {
        return labelAmbiente;
    }

    public JLabel getLabelEffetti() {
        return labelEffetti;
    }

    public JLabel getLabelVersi() {
        return labelVersi;
    }
}
