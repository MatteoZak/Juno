package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * Classe utilizzata per le animazioni delle carte pescate dal giocatore (utente)
 */
public class AnimazioneGiocatore extends Animazione {
    /**
     * Costruttore con una velocità sull'asse y
     */
    public AnimazioneGiocatore(){
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
        if(y > height){
            timer.stop();
        }
        y +=velocitaY;
        x +=velocitaX;
        repaint();
    }
}
