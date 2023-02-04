package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class AvvisoGiocataComputer {
    private Carta cartaGiocata;
    private Giocatori giocatore;

    public AvvisoGiocataComputer(Carta cartaGiocata, Giocatori giocatore) {
        this.cartaGiocata = cartaGiocata;
        this.giocatore = giocatore;
    }

    public Carta getCartaGiocata() {
        return cartaGiocata;
    }

    public Giocatori getGiocatore() {
        return giocatore;
    }

}
