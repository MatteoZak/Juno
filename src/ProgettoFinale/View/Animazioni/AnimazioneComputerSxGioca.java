package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerSxGioca extends Animazione{

    private boolean visible=true;
    public AnimazioneComputerSxGioca(){
        velocitaX = width/100;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x>xDestinazione-velocitaX)
            timer.stop();
        x += velocitaX;
        repaint();
    }
}
