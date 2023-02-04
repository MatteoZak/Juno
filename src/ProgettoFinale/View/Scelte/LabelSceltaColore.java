package ProgettoFinale.View.Scelte;

import ProgettoFinale.Model.Carte.Colori;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe usata per la visualizzazione della scelta del colore
 */
public class LabelSceltaColore extends JLabel {
    private JButton sceltaRosso = new JButton();
    private JButton sceltaBlu = new JButton();
    private JButton sceltaGiallo = new JButton();
    private JButton sceltaVerde = new JButton();

    /**
     * Costruttore che utilizza il BorderLayout
     */
    public LabelSceltaColore(){
        setLayout(new BorderLayout());
    }

    /**
     * Metodo che gestisce il colore rosso
     */
    public void setSceltaRosso() {
        try {
            sceltaRosso.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/sceltaRosso.png"))
                    .getScaledInstance(getHeight()/4, getHeight()/4, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sceltaRosso.setContentAreaFilled(false);
        sceltaRosso.setFocusPainted(false);
        sceltaRosso.setBorderPainted(false);
        sceltaRosso.setBounds(getWidth()/2+25,getHeight()/2-25,50,50);
        sceltaRosso.setName(Colori.ROSSO.toString());
        sceltaRosso.setActionCommand("SCELTACOLORE");
        add(sceltaRosso,BorderLayout.NORTH);
    }
    /**
     * Metodo che gestisce il colore blu
     */
    public void setSceltaBlu() {
        try {
            sceltaBlu.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/sceltaBlu.png"))
                    .getScaledInstance(getHeight()/4, getHeight()/4, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sceltaBlu.setContentAreaFilled(false);
        sceltaBlu.setFocusPainted(false);
        sceltaBlu.setBorderPainted(false);
        sceltaBlu.setBounds(getWidth()/2+25,getHeight()/2-25,50,50);
        sceltaBlu.setActionCommand("SCELTACOLORE");
        add(sceltaBlu,BorderLayout.WEST);
    }
    /**
     * Metodo che gestisce il colore giallo
     */
    public void setSceltaGiallo() {
        try {
            sceltaGiallo.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/sceltaGiallo.png"))
                    .getScaledInstance(getHeight()/4, getHeight()/4, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sceltaGiallo.setContentAreaFilled(false);
        sceltaGiallo.setFocusPainted(false);
        sceltaGiallo.setBorderPainted(false);
        sceltaGiallo.setBounds(getWidth()/2+25,getHeight()/2-25,50,50);
        sceltaGiallo.setActionCommand("SCELTACOLORE");
        add(sceltaGiallo,BorderLayout.EAST);
    }
    /**
     * Metodo che gestisce il colore verde
     */
    public void setSceltaVerde() {
        try {
            sceltaVerde.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/sceltaVerde.png"))
                    .getScaledInstance(getHeight()/4, getHeight()/4, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sceltaVerde.setContentAreaFilled(false);
        sceltaVerde.setFocusPainted(false);
        sceltaVerde.setBorderPainted(false);
        sceltaVerde.setBounds(getWidth()/2+25,getHeight()/2-25,50,50);
        sceltaVerde.setActionCommand("SCELTACOLORE");
        add(sceltaVerde,BorderLayout.SOUTH);
    }

    /**
     * Metodo che ritorna il bottone per il rosso
     * @return
     */
    public JButton getSceltaRosso() {
        return sceltaRosso;
    }
    /**
     * Metodo che ritorna il bottone per il blu
     * @return
     */
    public JButton getSceltaBlu() {
        return sceltaBlu;
    }
    /**
     * Metodo che ritorna il bottone per il giallo
     * @return
     */
    public JButton getSceltaGiallo() {
        return sceltaGiallo;
    }
    /**
     * Metodo che ritorna il bottone per il verde
     * @return
     */
    public JButton getSceltaVerde() {
        return sceltaVerde;
    }
}
