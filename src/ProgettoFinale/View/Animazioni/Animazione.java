package ProgettoFinale.View.Animazioni;

import ProgettoFinale.Model.Carte.Carta;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;



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

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,x,y,null);
    }

    public void paintScoperta(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image,x,y,width,height,null);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
    public int getxDestinazione() {return xDestinazione;}

    public void setxDestinazione(int xDestinazione) {
        this.xDestinazione = xDestinazione;
    }

    public int getyDestinazione() {
        return yDestinazione;
    }

    public void setyDestinazione(int yDestinazione) {
        this.yDestinazione = yDestinazione;
    }

    public  void timer() {
            timer.start();
        }

    public int getXX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getYY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocitaX() {
        return velocitaX;
    }

    public void setVelocitaX(int velocitaX) {
        this.velocitaX = velocitaX;
    }

    public int getVelocitaY() {
        return velocitaY;
    }

    public void setVelocitaY(int velocitaY) {
        this.velocitaY = velocitaY;
    }
}
