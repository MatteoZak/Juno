package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe che rappresenta lo spazio dove viene rappresentata
 * la mano del giocatore (utente)
 */
public class LabelManoGiocatore extends JLabel {
//    public void visualizzaCarte(Giocatore giocatore,Controller ctrl){
//        removeAll();
//        int puntoIniziale = calcolaCentro(getWidth(),giocatore.getMano().size());
//        int offset = calcolaOffset(getWidth(),giocatore.getMano().size());
//        for(Carta carta : giocatore.getMano()){
//            BottoneCarta bottone = new BottoneCarta(carta,ctrl);
//            bottone.setBounds(puntoIniziale,40,100,150);
//            add(bottone);
//            puntoIniziale += offset;
//        }
//        repaint();
//    }

    /**
     * Metodo che visualizza le carte nella mano utilizzando
     * dei suoi metodi, rimuovendole prima tutte e poi aggiungerle tramite un forEach
     * le carte aggiunte sono bottoni a differenza delle altre mani
     * @param carteInMano
     * @param ctrl
     */
    public void visualizzaCarte(ArrayList<Carta> carteInMano, Controller ctrl){
        removeAll();
        int puntoIniziale = calcolaCentro(getWidth(),carteInMano.size());
        int offset = calcolaOffset(getWidth(),carteInMano.size());
        for(Carta carta : carteInMano){
            BottoneCarta bottone = new BottoneCarta(carta, ctrl);
            bottone.setBounds(puntoIniziale,40,100,150);
            add(bottone);
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
//        return larghezza/2-carteInMano*(calcolaOffset(larghezza,carteInMano))/2;
        return larghezza/2 - (100+calcolaOffset(larghezza,carteInMano)*(carteInMano-1))/2;
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
            offset = 100-(carteInMano*100-(larghezza))/(carteInMano-1);
//            offset = (larghezza - 131) / (carteInMano-1);
        }
        return offset;
    }
}


/*
    public void visualizzaMano(Giocatore giocatore,Controller ctrl){
        removeAll();
        int var = (getWidth()-101)/(giocatore.getMano().size()+1);
        for(int i = 0; i < giocatore.getMano().size();i++) {
            BottoneCarta bottone = new BottoneCarta(giocatore.getMano().get(i), ctrl);
            if (giocatore.getMano().size() * 100 > getWidth()) {
                bottone.setBounds((i + 1) * var, 30, 100, 150);
            } else {
                bottone.setBounds((i + 1) * 100, 30, 100, 150);
            }
            add(bottone);
            repaint();
        }
    }
*/
