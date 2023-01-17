package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerSx extends Animazione {

    public AnimazioneComputerSx(){
        velocitaX = width/70;
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backRuotato.png")
                .getScaledInstance(150,100,16)).getImage();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(x< -150) {
            timer.stop();
        }
        setY(0);
        x -=velocitaX;
        //y+=velocitaY;
        repaint();
    }
}
