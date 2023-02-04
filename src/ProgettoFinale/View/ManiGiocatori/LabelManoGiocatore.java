package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe che rappresenta lo spazio dove viene rappresentata
 * la mano del giocatore (utente)
 */
public class LabelManoGiocatore extends JLabel {

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
        }
        return offset;
    }
}

