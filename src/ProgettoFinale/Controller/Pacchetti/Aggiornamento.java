package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class Aggiornamento {
    private Giocatori[] giocatore;
    private Controller ctrl;

    public Aggiornamento(Controller ctrl, Giocatori... giocatore) {
        this.giocatore = giocatore;
        this.ctrl = ctrl;
    }

    public Giocatori[] getGiocatore() {
        return giocatore;
    }

    public Controller getCtrl() {
        return ctrl;
    }
}
