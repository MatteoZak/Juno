package ProgettoFinale.View.Scelte;

import ProgettoFinale.Model.Carte.Colori;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LabelSelezionaGiocatore extends JLabel {
    private JLabel selezioneSx = new JLabel();
    private JLabel selezioneSu = new JLabel();
    private JLabel selezioneDx = new JLabel();
    private JLabel testo = new JLabel();

    public LabelSelezionaGiocatore(){
        setVisible(false);
    }

    public void setTesto(int x, int y){
        testo.setBounds(x-50,y-50,100,100);
        testo.setText(convertToMultiline("Seleziona\n un avversario!"));
        add(testo);
    }
    public JLabel getTesto(){
        return testo;
    }
    public void setSelezioneSx(int x, int y) {
        selezioneSx.setBounds(x,y,70,70);
        try {
            selezioneSx.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/FrecciaSx.png"))
                    .getScaledInstance(selezioneSx.getWidth(),selezioneSx.getHeight(), 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(selezioneSx);
    }

    public void setSelezioneSu(int x, int y) {
        selezioneSu.setBounds(x,y,70,70);
        try {
            selezioneSu.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/FrecciaSu.png"))
                    .getScaledInstance(selezioneSu.getWidth(),selezioneSu.getHeight(), 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(selezioneSu);
    }

    public void setSelezioneDx(int x, int y) {
        selezioneDx.setBounds(x,y,70,70);
        try {
            selezioneDx.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/FrecciaDx.png"))
                    .getScaledInstance(selezioneDx.getWidth(),selezioneDx.getHeight(), 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(selezioneDx);
    }
    public static String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }

}
