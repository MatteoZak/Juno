package ProgettoFinale.View.Animazioni;

import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte giocate dell'avversario a destra
 */
public class AnimazioneComputerDxGioca extends Animazione{
    /**
     * Costruttore con velocità sull'asse x della carta giocata
     */
    public AnimazioneComputerDxGioca(){

        velocitaX = width/70;
    }
    /**
     * Metodo che disegna l'immagine e superato un limite di pixel smette di disegnare
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x<width/70-100)
            timer.stop();
        x-=velocitaX;
        repaint();
    }
}
