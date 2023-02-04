package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;
import java.awt.*;

/**
 * Classe astratta utilizzata per la creazione delle carte da gioco
 */
public abstract class DecoratoreCartaAstratto extends Carta {
    protected Carta carta;

    /**
     * Costruttore che inizializza il campo con la carta passata in input
     * @param carta
     */
    public DecoratoreCartaAstratto(Carta carta){
        this.carta = carta;
    }

    /**
     * Metodo astratto che verrà implementato dalla sotto classe
     * @return
     */
    abstract public Image visualizzaCarta();
}
