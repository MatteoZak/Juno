package ProgettoFinale.View.Animazioni;

import ProgettoFinale.View.Animazioni.Animazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AnimazioneGiocatoreGioca extends Animazione {

    //private int lar=100;
    //private int alt=150;

    public AnimazioneGiocatoreGioca(){
        //velocitaX = 10;
        //timer = new Timer(50,this);
        velocitaY = height/40;

    }



    /*
        @Override
        public void paint(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image,x,y,null);
        }

     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(y<-150)
            timer.stop();
        /*if(x< xDestinazione-160) {
            x += velocitaX;
            y-=velocitaY;
        }

         */
        //else {
        //    x -= velocitaX+(x/10);
        setX(0);
        y-=velocitaY;
        //}

        repaint();
    }

}
