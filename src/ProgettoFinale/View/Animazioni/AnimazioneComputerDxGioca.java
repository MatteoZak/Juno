package ProgettoFinale.View.Animazioni;

import java.awt.event.ActionEvent;

public class AnimazioneComputerDxGioca extends Animazione{

    public AnimazioneComputerDxGioca(){

        velocitaX = width/70;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x<width/70-100)
            timer.stop();
        x-=velocitaX;

        //y+=velocitaY;
        repaint();
    }
}
