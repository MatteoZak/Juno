package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class AvvisoPescata{
    private Carta cartaPescata;
    private Giocatori giocatorePescante;
    private Controller ctrl;

    public AvvisoPescata(Carta cartaPescata, Giocatori giocatorePescante, Controller ctrl) {
        this.cartaPescata = cartaPescata;
        this.giocatorePescante = giocatorePescante;
        this.ctrl = ctrl;
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