package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Classe utilizzata per le animazioni delle carte pescate dell'avversario a destra
 */
public class AnimazioneComputerDx extends Animazione {
    /**
     * Costruttore in cui viene inserita la velocità che ha l'immagine sull'asse x
     */
    public AnimazioneComputerDx(){
        velocitaX = width/70;
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backRuotato.png")
                .getScaledInstance(150,100,16)).getImage();
    }

    /**
     * Metodo che disegna l'immagine e superato un limite di pixel smette di disegnare
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (x>520)
            x=0;
        if(x>520-velocitaX)
            timer.stop();
        x+=velocitaX;
        setY(0);
        repaint();
    }
}
