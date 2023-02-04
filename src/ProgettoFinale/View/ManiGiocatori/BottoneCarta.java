package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.View.Decorator.DecoratoreCarta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Classe utilizzata per quando l'utente preme le carte
 * nella sua mano
 */
public class BottoneCarta extends JButton {
    private Carta carta;

    /**
     * Costruttore in cui viene passato anche il controller
     * in cui viene generato il bottone con l'immagine della carta passata in input
     * e vengono implementati tutti i metodi necessari per l'utilizzo del mouse
     * tramite un MouseListener
     * @param c
     * @param ctrl
     */
    public BottoneCarta(Carta c, Controller ctrl){
        this.carta = c;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(new DecoratoreCarta(c).visualizzaCarta()));
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ctrl.cartaCliccata(BottoneCarta.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Point p = ((BottoneCarta) e.getSource()).getLocation();
                p.y -= 30;
                BottoneCarta.this.setLocation(p);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Point p = ((BottoneCarta) e.getSource()).getLocation();
                p.y += 30;
                BottoneCarta.this.setLocation(p);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

        });
    }

    /**
     * Metodo che ritorna la carta del bottone
     * @return
     */
    public Carta getCarta() {
        return carta;
    }
}

//public BottoneCarta(Carta c, Controller ctrl){
//        this.carta = c;
//        setBorderPainted(false);
//        setContentAreaFilled(false);
//        setIcon(new ImageIcon(c.getImmagine()));
//        addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                ctrl.cartaCliccata(BottoneCarta.this);
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                Point p = ((BottoneCarta) e.getSource()).getLocation();
//                p.y -= 30;
//                BottoneCarta.this.setLocation(p);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                Point p = ((BottoneCarta) e.getSource()).getLocation();
//                p.y += 30;
//                BottoneCarta.this.setLocation(p);
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//        });
//    }