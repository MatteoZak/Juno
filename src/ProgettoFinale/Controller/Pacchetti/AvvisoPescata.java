package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.Model.Giocatori.Giocatori;
import java.awt.event.ActionListener;

/**
 * Pacchetto utilizzato per notificare la pescata
 * da parte del giocatore
 */
public class AvvisoPescata{
    private Carta cartaPescata;
    private Giocatore giocatorePescante;
    private Controller ctrl;

    /**
     * Costruttore del pacchetto
     * @param cartaPescata
     * @param giocatorePescante
     * @param ctrl
     */
    public AvvisoPescata(Carta cartaPescata, Giocatore giocatorePescante, Controller ctrl) {
        this.cartaPescata = cartaPescata;
        this.giocatorePescante = giocatorePescante;
        this.ctrl = ctrl;
    }

    /**
     * Gettere della carta pescata
     * @return carta pescata
     */
    public Carta getCartaPescata() {
        return cartaPescata;
    }

    /**
     * Gettere del giocatore che ha pescato
     * @return giocatore che ha pescato
     */
    public Giocatori getGiocatorePescante() {
        return giocatorePescante;
    }

    /**
     * Getter del Controller
     * @return Controller
     */
    public Controller getCtrl() {
        return ctrl;
    }
}