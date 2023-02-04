package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.Model.Giocatori.Giocatori;
import java.awt.event.ActionListener;

public class AvvisoPescata{
    private Carta cartaPescata;
    private Giocatore giocatorePescante;
    private Controller ctrl;
    private ActionListener actionListener;

    public AvvisoPescata(Carta cartaPescata, Giocatore giocatorePescante, Controller ctrl) {
        this.cartaPescata = cartaPescata;
        this.giocatorePescante = giocatorePescante;
        this.ctrl = ctrl;
    }

    public AvvisoPescata(Carta cartaPescata, Giocatore giocatorePescante, ActionListener actionListener) {
        this.cartaPescata = cartaPescata;
        this.giocatorePescante = giocatorePescante;
        this.actionListener = actionListener;
    }

    public Carta getCartaPescata() {
        return cartaPescata;
    }

    public Giocatori getGiocatorePescante() {
        return giocatorePescante;
    }

    public Controller getCtrl() {
        return ctrl;
    }
}