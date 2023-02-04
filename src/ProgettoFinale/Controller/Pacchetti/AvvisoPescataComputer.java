package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatori;

/**
 * Pacchetto utilizzato per notificare la pescata
 * da parte del computer
 */
public class AvvisoPescataComputer{
    private Computer giocatorePescante;

    /**
     * Costruttore del pacchetto
     * @param giocatorePescante
     */
    public AvvisoPescataComputer(Computer giocatorePescante) {
        this.giocatorePescante = giocatorePescante;
    }

    /**
     * Getter del giocatore pescante
     * @return
     */
    public Giocatori getGiocatorePescante() {
        return giocatorePescante;
    }

}