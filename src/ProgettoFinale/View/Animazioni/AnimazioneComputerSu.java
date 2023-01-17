package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerSu extends Animazione {

    public AnimazioneComputerSu(){
        velocitaY = height/40;
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backJuno1.png")
                .getScaledInstance(100,150,16)).getImage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(y<-150)
            timer.stop();
        //System.out.println(y);
        setX(0);
        //System.out.println(x);
        y-=velocitaY;

        //x+=velocitaX;
        repaint();
    }
}
