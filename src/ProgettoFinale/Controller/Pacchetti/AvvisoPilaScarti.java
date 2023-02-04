package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Carte.Carta;

import java.awt.*;

public class AvvisoPilaScarti {
    private Carta imgCarta;

    public AvvisoPilaScarti(Carta imgCarta) {
        this.imgCarta = imgCarta;
    }

    public Carta getImgCarta() {
        return imgCarta;
    }
}
