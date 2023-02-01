package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatori;

public class AvvisoPescataComputer{
    private Computer giocatorePescante;

    public AvvisoPescataComputer(Computer giocatorePescante) {
        this.giocatorePescante = giocatorePescante;
    }


    public Giocatori getGiocatorePescante() {
        return giocatorePescante;
    }

}