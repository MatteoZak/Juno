package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;
import java.awt.*;

public abstract class DecoratoreCartaAstratto extends Carta {
    protected Carta carta;
    public DecoratoreCartaAstratto(Carta carta){
        this.carta = carta;
    }

    abstract public Image visualizzaCarta();
}
