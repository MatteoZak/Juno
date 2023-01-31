package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class AvvisoPescataComputer{
    private Carta cartaPescata;
    private Computer giocatorePescante;
    private Controller ctrl;

    public AvvisoPescataComputer(Carta cartaPescata, Computer giocatorePescante, Controller ctrl) {
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