package ProgettoFinale.View.ManiGiocatori;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Method;

public class BottoneCarta extends JButton {
    private Carta carta;

    public BottoneCarta(Carta c, ActionListener act){
        this.carta = c;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(c.getImmagine()));
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
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