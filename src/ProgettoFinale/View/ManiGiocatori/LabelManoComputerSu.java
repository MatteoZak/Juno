package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Classe che rappresenta lo spazio dove viene rappresentata
 * la mano del giocatore a sx
 */
public class LabelManoComputerSu extends JLabel {
//    public void visualizzaMano(Computer computer){
//        removeAll();
//
//        int var = (getWidth()-200)/(computer.getMano().size()+1);
//        for(int i = 0; i < computer.getMano().size();i++) {
//            JLabel carta = new JLabel();
//            carta.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Carte/backJuno1.png")
//                    .getScaledInstance(100, 150, 16)));
//            if (computer.getMano().size() * 100 > getWidth()) {
//                carta.setBounds((i + 1) * var, 30, 100, 150);
//            } else {
//                carta.setBounds((i + 1) * 100, 30, 100, 150);
//            }
//            add(carta);
//        }
//        repaint();
//    }
    /**
     * Metodo che visualizza le carte nella mano utilizzando
     * dei suoi metodi
     * @param manoComputer
     */
    public void visualizzaMano(ArrayList<Carta> manoComputer){
        removeAll();
        int puntoIniziale = calcolaCentro(getWidth(),manoComputer.size());
        int offset = calcolaOffset(getWidth(),manoComputer.size());

        for(Carta carta : manoComputer){
            JLabel dorso = new JLabel();
            dorso.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Carte/backJuno1.png")
                    .getScaledInstance(100, 150, 16)));
            dorso.setBounds(puntoIniziale,15,100,150);
            add(dorso);
            puntoIniziale += offset;
        }
        repaint();
    }
    /**
     * Metodo che tramite una formula calcola il centro della mano utilizzando la sua
     * larghezza e il numero di carte in mano
     * @param larghezza
     * @param carteInMano
     * @return
     */
    private int calcolaCentro(int larghezza, int carteInMano){
        return larghezza/2-carteInMano*(calcolaOffset(larghezza,carteInMano))/2;
    }
    /**
     * Metodo che calcola lo spazio tra ogni carta in base a quante sono
     * nella mano
     * @param larghezza
     * @param carteInMano
     * @return
     */
    private int calcolaOffset(int larghezza, int carteInMano){
        int offset = 101;
        if (carteInMano*101 > larghezza){
            offset = 100-(carteInMano*100-(larghezza-1))/(carteInMano+1);
        }
        return offset;
    }


}

//public void visualizzaMano(Computer computer){
//        removeAll();
//        int puntoIniziale = calcolaCentro(getWidth(),computer.getMano().size());
//        int offset = calcolaOffset(getWidth(),computer.getMano().size());
//
//        for(Carta carta : computer.getMano()){
//            JLabel dorso = new JLabel();
//            dorso.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Carte/backJuno1.png")
//                    .getScaledInstance(100, 150, 16)));
//            dorso.setBounds(puntoIniziale,15,100,150);
//            add(dorso);
//            puntoIniziale += offset;
//        }
//        repaint();
//    }