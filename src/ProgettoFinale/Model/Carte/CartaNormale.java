package ProgettoFinale.Model.Carte;

import ProgettoFinale.View.Decorator.DecoratoreCarta;

/**
 * Classe concreta che estende Carta e permette la creazione delle varie carte
 */
public class CartaNormale extends Carta {
    /**
     * Costruttore che crea l'oggetto con un colore e un valore e un'immagine
     * @param colore della carta
     * @param valore della carta
     */
    public CartaNormale(Colori colore, Valori valore) {
        setColore(colore);
        setValore(valore);
        setImmagine(new DecoratoreCarta(this).visualizzaCarta());
    }
}
