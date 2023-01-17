package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabelManoComputerSx extends JLabel{

//    public void visualizzaMano(Computer computer){
//        removeAll();
//        int var = (getHeight()-200)/(computer.getMano().size()+1);
//        for(int i = 0; i < computer.getMano().size();i++) {
//            JLabel carta = new JLabel();
//            try {
//                carta.setIcon(new ImageIcon((rotate(ImageIO.read(new File("Risorse/images/Carte/backJuno1.png")), 90)
//                        .getScaledInstance(150, 100, 16))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            if (computer.getMano().size() * 100 > getHeight() - 100) {
//                carta.setBounds(30, (i + 1) * var, 150, 100);
//            } else {
//                carta.setBounds(30, (i + 1) * 100, 150, 100);
//            }
//            add(carta);
//            repaint();
//        }
//    }
    public void visualizzaMano(Computer computer){
        removeAll();
        int puntoIniziale = calcolaCentro(getHeight(),computer.getMano().size());
        int offset = calcolaOffset(getHeight(),computer.getMano().size());

        for(Carta carta : computer.getMano()){
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
