package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;

/**
 * Pacchetto utilizzato per notificare la giocata di una carta
 * da parte del giocatore
 */
public class AvvisoGiocata {
    private BottoneCarta carta;
    private Giocatori giocatore;
    private Controller ctrl;

    /**
     * Costruttore del pacchetto
     * @param carta
     * @param giocatore
     * @param ctrl
     */
    public AvvisoGiocata(BottoneCarta carta, Giocatori giocatore, Controller ctrl) {
        this.carta = carta;
        this.giocatore = giocatore;
        this.ctrl = ctrl;
    }

    /**
     * Getter del bottone della carta
     * @return bottone della carta
     */
    public BottoneCarta getBottoneCarta() {
        return carta;
    }

    /**
     * Getter del giocatore
     * @return giocatore
     */
    public Giocatori getGiocatore() {
        return giocatore;
    }

    /**
     * Getter del Controller per assegnare l'ActionListener
     * @return Controller
     */
    public Controller getCtrl() {
        return ctrl;
    }
}
