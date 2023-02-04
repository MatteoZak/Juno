package ProgettoFinale.View.Decorator;

import ProgettoFinale.Model.Carte.Carta;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe che estende la classe astratta e serve per la creazione delle carte
 */
public class DecoratoreCarta extends DecoratoreCartaAstratto {
    /**
     * Costruttore che richiama quello della super classe
     * @param carta
     */
    public DecoratoreCarta(Carta carta) {
        super(carta);
    }

    /**
     * Metodo che ritorna il colore della carta
     * @return
     */
    @Override
    public String getColore() {
        return carta.getColore();
    }

    /**
     * Metodoche ritorna il valore della carta
     * @return
     */
    @Override
    public String getValoreIntero() {
        return carta.getValoreIntero();
    }

    /**
     * Metodo che compone le carte con il suoi getColore e getValore e la ritorna
     * @return
     */
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
