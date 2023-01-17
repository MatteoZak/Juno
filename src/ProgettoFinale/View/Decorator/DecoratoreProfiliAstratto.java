package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.View.FontPokemon;

import javax.swing.*;
import java.awt.*;

public abstract class DecoratoreProfiliAstratto {
    protected JButton bottoneIcona;
    public DecoratoreProfiliAstratto(JButton labelIcona){
        this.bottoneIcona = labelIcona;
    }

    abstract public void visualizzaAvatar();
}
