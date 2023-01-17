package ProgettoFinale.View.Scelte;

import ProgettoFinale.Model.Carte.Colori;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LabelSceltaColore extends JLabel {
    private JButton sceltaRosso = new JButton();
    private JButton sceltaBlu = new JButton();
    private JButton sceltaGiallo = new JButton();
    private JButton sceltaVerde = new JButton();
    public LabelSceltaColore(){
        setLayout(new BorderLayout());
    }

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

    public JButton getSceltaRosso() {
        return sceltaRosso;
    }

    public JButton getSceltaBlu() {
        return sceltaBlu;
    }

    public JButton getSceltaGiallo() {
        return sceltaGiallo;
    }

    public JButton getSceltaVerde() {
        return sceltaVerde;
    }
}
