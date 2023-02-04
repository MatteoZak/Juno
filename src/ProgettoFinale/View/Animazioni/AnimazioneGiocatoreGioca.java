package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte giocate dal giocatore (utente)
 */
public class AnimazioneGiocatoreGioca extends Animazione {
    /**
     * Costruttore con una velocità dell'immagine sull'asse y
     */
    public AnimazioneGiocatoreGioca(){
        velocitaY = height/40;
    }
    /**
     * Metodo che disegna l'immagine e superato un limite di pixel smette di disegnare
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(y<-150)
            timer.stop();
        setX(0);
        y-=velocitaY;
        repaint();
    }

}
