package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte giocate dell'avversario in alto
 */
public class AnimazioneComputerSuGioca extends Animazione{

    public AnimazioneComputerSuGioca(){
        velocitaY = height/100;
    }
    /**
     * Metodo che disegna l'immagine e superato un limite di pixel smette di disegnare
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(y>yDestinazione-velocitaY)
            timer.stop();
        y+=velocitaY;
        repaint();
    }
}
