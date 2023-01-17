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
        //x+=velocitaX;
        //System.out.println(yDestinazione);
        //System.out.println(y);
        y+=velocitaY;
        //System.out.println(x);
        repaint();
    }
}
