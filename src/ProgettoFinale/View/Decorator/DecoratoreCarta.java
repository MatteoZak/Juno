package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DecoratoreCarta extends DecoratoreCartaAstratto {
    public DecoratoreCarta(Carta carta) {
        super(carta);
    }


    @Override
    public String getColore() {
        return carta.getColore();
    }
    @Override
    public String getValoreIntero() {
        return carta.getValoreIntero();
    }
    @Override
    public Image visualizzaCarta() {
        try {
             return ImageIO.read(new File("Risorse/images/Carte/" + getColore().toLowerCase() + getValoreIntero() + ".png"))
                                        .getScaledInstance(100, 150, 16);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
