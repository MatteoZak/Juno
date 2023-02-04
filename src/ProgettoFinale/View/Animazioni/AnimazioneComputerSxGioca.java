package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte giocate dell'avversario a sinistra
 */
public class AnimazioneComputerSxGioca extends Animazione{
    /**
     * Costruttore con una velocità dell'immagine sull'asse x
     */
    public AnimazioneComputerSxGioca(){
        velocitaX = width/100;
    }
    /**
     * Metodo che disegna l'immagine e superato un limite di pixel smette di disegnare
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x>xDestinazione-velocitaX)
            timer.stop();
        x += velocitaX;
        repaint();
    }
}
