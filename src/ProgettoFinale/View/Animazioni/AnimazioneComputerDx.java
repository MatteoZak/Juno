package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerDx extends Animazione {
    public AnimazioneComputerDx(){
        velocitaX = width/70;
        /*
        System.out.println(x);
        setX(0);
        System.out.println(x);
         */
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backRuotato.png")
                .getScaledInstance(150,100,16)).getImage();
    }
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
