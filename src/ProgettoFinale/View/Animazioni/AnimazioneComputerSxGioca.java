package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AnimazioneComputerSxGioca extends Animazione{

    private boolean visible=true;
    public AnimazioneComputerSxGioca(){
        velocitaX = width/100;

        //timer = new Timer(50,this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(x);
        if(x>xDestinazione-velocitaX)
            timer.stop();
        x += velocitaX;
        //y+=velocitaY;

        repaint();
    }
}
