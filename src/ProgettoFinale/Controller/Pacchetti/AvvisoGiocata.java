package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class AvvisoGiocata {
    private Carta cartaGiocata;
    private Giocatori giocatore;
    private Controller ctrl;

    public AvvisoGiocata(Carta cartaGiocata, Giocatori giocatore, Controller ctrl) {
        this.cartaGiocata = cartaGiocata;
        this.giocatore = giocatore;
        this.ctrl = ctrl;
    }

    public Carta getCartaGiocata() {
        return cartaGiocata;
    }

    public Giocatori getGiocatore() {
        return giocatore;
    }

    public Controller getCtrl() {
        return ctrl;
    }
}
