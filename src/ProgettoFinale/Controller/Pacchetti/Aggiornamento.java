package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Giocatori.Giocatori;

/**
 * Pacchetto utilizzato per far aggiornare le mani dei giocatori
 */
public class Aggiornamento {
    private Giocatori[] giocatore;
    private Controller ctrl;

    /**
     * Costruttore del pacchetto
     * @param ctrl
     * @param giocatore
     */
    public Aggiornamento(Controller ctrl, Giocatori... giocatore) {
        this.giocatore = giocatore;
        this.ctrl = ctrl;
    }

    /**
     * Getter del giocatore
     * @return Giocatore
     */
    public Giocatori[] getGiocatore() {
        return giocatore;
    }

    /**
     * Getter del Controller
     * @return Controller
     */
    public Controller getCtrl() {
        return ctrl;
    }
}
