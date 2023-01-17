package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;

import javax.swing.*;
import java.awt.*;

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

    public void visualizzaMano(Computer computer){
        removeAll();
        int puntoIniziale = calcolaCentro(getWidth(),computer.getMano().size());
        int offset = calcolaOffset(getWidth(),computer.getMano().size());

        for(Carta carta : computer.getMano()){
            JLabel dorso = new JLabel();
            dorso.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Risorse/images/Carte/backJuno1.png")
                    .getScaledInstance(100, 150, 16)));
            dorso.setBounds(puntoIniziale,15,100,150);
            add(dorso);
            puntoIniziale += offset;
        }
        repaint();
    }

    private int calcolaCentro(int larghezza, int carteInMano){
        return larghezza/2-carteInMano*(calcolaOffset(larghezza,carteInMano))/2;
    }
    private int calcolaOffset(int larghezza, int carteInMano){
        int offset = 101;
        if (carteInMano*101 > larghezza){
            offset = 100-(carteInMano*100-(larghezza-1))/(carteInMano+1);
        }
        return offset;
    }


}