package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AnimazioneGiocatore extends Animazione {


    public AnimazioneGiocatore(){

        velocitaY = height/40;
        image = new ImageIcon(toolkit.createImage("Risorse/images/Carte/backJuno1.png")
              .getScaledInstance(100,150,16)).getImage();

    }

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
