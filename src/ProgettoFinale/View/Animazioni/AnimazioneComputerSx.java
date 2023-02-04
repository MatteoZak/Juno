package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte pescate dell'avversario a sinistra
 */
public class AnimazioneComputerSx extends Animazione {
    /**
     * Costruttore con una velocità dell'immagine sull'asse x
     */
    public AnimazioneComputerSx(){
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
        if(x< -150) {
            timer.stop();
        }
        setY(0);
        x -=velocitaX;
        repaint();
    }
}
