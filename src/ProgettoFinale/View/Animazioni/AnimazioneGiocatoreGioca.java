package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AnimazioneGiocatoreGioca extends Animazione {

    public AnimazioneGiocatoreGioca(){
        velocitaY = height/40;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(y<-150)
            timer.stop();
        setX(0);
        y-=velocitaY;
        repaint();
    }

}
