package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;

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
