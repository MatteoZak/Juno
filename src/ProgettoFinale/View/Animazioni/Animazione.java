package ProgettoFinale.View.Animazioni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe astratta che gestisce i metodi e le rappresentazioni delle animazioni
 */
abstract public class Animazione extends JLabel implements ActionListener {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int height = (int) screenSize.getHeight();
    protected int width = (int) screenSize.getWidth();
    protected int xDestinazione;
    protected int yDestinazione;
    protected int velocitaX = 5;
    protected int velocitaY = 5;
    private final int DELAY = 10;

    protected Image image;
    protected Timer timer;
    protected int x ;
    protected int y;

    protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    public Animazione() {
        timer = new Timer(DELAY,this);
    }

    /**
     * Metodo che disegna le carte partendo da cordinate x e y
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,x,y,null);
    }


    /**
     * Metodo astratto che verrà implementato dalle sottoclassi per gestire la rappresentazione
     * delle carte
     * @param e the event to be processed
     */
    @Override
    public abstract void actionPerformed(ActionEvent e);

    /**
     * Metodo usato per salvare la posizione finale sull'asse x
     * da cui parte l'immagine
     * @param xDestinazione
     */
    public void setxDestinazione(int xDestinazione) {
        this.xDestinazione = xDestinazione;
    }

    /**
     * Metodo usato per salvare la posizione finale sull'asse y
     * da cui parte l'immagine
     * @param yDestinazione
     */
    public void setyDestinazione(int yDestinazione) {
        this.yDestinazione = yDestinazione;
    }

    /**
     * Metodo che fa partire il timer e ogni DELAY di tempo
     * viene disegnata una nuova immagine
     */
    public  void timer() {
            timer.start();
        }

    /**
     * Metodo usato per salvare la posizione finale sull'asse x
     * da cui parte l'immagine
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo per salvare l'immagine da dover disegnare
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     *Metodo usato per salvare la posizione finale sull'asse y
     *da cui parte l'immagine
    */
    public void setY(int y) {
        this.y = y;
    }

}
