package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;

public class AvvisoGiocata {
    private BottoneCarta bottoneCarta;
    private Giocatori giocatore;
    private Controller ctrl;

    public AvvisoGiocata(BottoneCarta bottoneCarta, Giocatori giocatore, Controller ctrl) {
        this.bottoneCarta = bottoneCarta;
        this.giocatore = giocatore;
        this.ctrl = ctrl;
    }

    public BottoneCarta getBottoneCarta() {
        return bottoneCarta;
    }

    public Giocatori getGiocatore() {
        return giocatore;
    }

    public Controller getCtrl() {
        return ctrl;
    }
}
