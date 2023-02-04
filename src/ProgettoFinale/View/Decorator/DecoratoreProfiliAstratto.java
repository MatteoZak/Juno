package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.View.FontPokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Classe astratta utilizzata per la creazione del profilo del giocatore
 */
public abstract class DecoratoreProfiliAstratto {
    protected JButton bottoneIcona;

    /**
     * Costruttore che inizializza il campo col bottone passato in input
     * @param labelIcona
     */
    public DecoratoreProfiliAstratto(JButton labelIcona){
        this.bottoneIcona = labelIcona;
    }

    /**
     * Metodo astratto che verrà implementato dalla sotto classe
     */
    abstract public void visualizzaAvatar();
}
