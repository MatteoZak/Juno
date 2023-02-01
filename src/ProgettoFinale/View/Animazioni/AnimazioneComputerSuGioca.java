package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerSuGioca extends Animazione{

    public AnimazioneComputerSuGioca(){

        velocitaY = height/100;


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(y>yDestinazione-velocitaY)
            timer.stop();
        y+=velocitaY;
        repaint();
    }
}
