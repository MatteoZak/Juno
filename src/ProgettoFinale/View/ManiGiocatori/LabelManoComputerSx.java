package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe che rappresenta lo spazio dove viene rappresentata
 * la mano del giocatore a sx
 */
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
    /**
     * Metodo che visualizza le carte nella mano utilizzando
     * dei suoi metodi
     * @param manoComputer
     */
    public void visualizzaMano(ArrayList<Carta> manoComputer){
        removeAll();
        int puntoIniziale = calcolaCentro(getHeight(),manoComputer.size());
        int offset = calcolaOffset(getHeight(),manoComputer.size());

        for(Carta carta : manoComputer){
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
    /**
     * Metodo che tramite una formula calcola il centro della mano utilizzando la sua
     * altezza e il numero di carte in mano
     * @param altezza
     * @param carteInMano
     * @return
     */
    private int calcolaCentro(int altezza, int carteInMano){
        return altezza/2 - (100+calcolaOffset(altezza,carteInMano)*(carteInMano-1))/2;
    }
    /**
     * Metodo che calcola lo spazio tra ogni carta in base a quante sono
     * nella mano
     * @param altezza
     * @param carteInMano
     * @return
     */
    private int calcolaOffset(int altezza, int carteInMano){
        int offset = 101;
        if (carteInMano*101 > altezza){
            offset = 100-(carteInMano*100-altezza)/(carteInMano-1);
        }
        return offset;
    }
    /**
     * Metodo che rappresenta le carte girate di 90 gradi
     * @param bimg
     * @param angle
     * @return
     */
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

//public void visualizzaMano(Computer computer){
//        removeAll();
//        int puntoIniziale = calcolaCentro(getHeight(),computer.getMano().size());
//        int offset = calcolaOffset(getHeight(),computer.getMano().size());
//
//        for(Carta carta : computer.getMano()){
//            JLabel dorso = new JLabel();
//            try {
//                dorso.setIcon(new ImageIcon((rotate(ImageIO.read(new File("Risorse/images/Carte/backJuno1.png")), 90)
//                        .getScaledInstance(150, 100, 16))));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            dorso.setBounds(15,puntoIniziale,150,100);
//            add(dorso);
//            puntoIniziale += offset;
//        }
//        repaint();
//    }
