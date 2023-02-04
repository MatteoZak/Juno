package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte pescate dell'avversario in alto
 */
public class AnimazioneComputerSu extends Animazione {
    /**
     * Costruttore con una velocità sull'asse y
     */
    public AnimazioneComputerSu(){
        velocitaY = height/40;
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backJuno1.png")
                .getScaledInstance(100,150,16)).getImage();
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
