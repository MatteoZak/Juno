package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Carte.Carta;

import java.awt.*;

/**
 * Pacchetto utilizzato per notificare il cambiamento
 * della pila scarti
 */
public class AvvisoPilaScarti {
    private Carta imgCarta;

    /**
     * Costruttore del pacchetto
     * @param imgCarta
     */
    public AvvisoPilaScarti(Carta imgCarta) {
        this.imgCarta = imgCarta;
    }

    /**
     * Getter della carta
     * @return Carta
     */
    public Carta getImgCarta() {
        return imgCarta;
    }
}
