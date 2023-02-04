package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;

/**
 * Pacchetto utilizzato per notificare la giocata di una carta
 * da parte del computer
 */
public class AvvisoGiocataComputer {
    private Carta cartaGiocata;
    private Giocatori giocatore;

    /**
     * Costruttore del pacchetto
     * @param cartaGiocata
     * @param giocatore
     */
    public AvvisoGiocataComputer(Carta cartaGiocata, Giocatori giocatore) {
        this.cartaGiocata = cartaGiocata;
        this.giocatore = giocatore;
    }

    /**
     * Getter della carta giocata
     * @return carta giocata
     */
    public Carta getCartaGiocata() {
        return cartaGiocata;
    }

    /**
     * Getter del giocatore
     * @return Giocatore
     */
    public Giocatori getGiocatore() {
        return giocatore;
    }

}
