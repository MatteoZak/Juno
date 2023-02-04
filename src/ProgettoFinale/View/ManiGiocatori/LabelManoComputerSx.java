package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Model.Carte.Carta;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LabelManoComputerSx extends JLabel{

    public void visualizzaMano(ArrayList<Carta> manoComputer){
        removeAll();
        int puntoIniziale = calcolaCentro(getHeight(),manoComputer.size());
        int offset = calcolaOffset(getHeight(),manoComputer.size());

        for( int i = 0; i < manoComputer.size(); i++){
            JLabel dorso = new JLabel();
            try {
                dorso.setIcon(new ImageIcon((rotate(ImageIO.read(new File("Risorse/images/Carte/backJuno1.png")), 90)
                        .getScaledInstance(150, 100, 16))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dorso.setBounds(15,puntoIniziale,150,100);
            add(dorso);
            puntoIniziale += offset;
        }

        repaint();
    }

    private int calcolaCentro(int altezza, int carteInMano){
        return altezza/2 - (100+calcolaOffset(altezza,carteInMano)*(carteInMano-1))/2;
    }
    private int calcolaOffset(int altezza, int carteInMano){
        int offset = 101;
        if (carteInMano*101 > altezza){
            offset = 100-(carteInMano*100-altezza)/(carteInMano-1);
        }
        return offset;
    }

    public static BufferedImage rotate(BufferedImage bimg, double angle) {
        int h = bimg.getWidth();
        int w = bimg.getHeight();
        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, w/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

}
