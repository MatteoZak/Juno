package ProgettoFinale.Model.Carte;

import ProgettoFinale.View.Decorator.DecoratoreCarta;

public abstract class CartaSpeciale extends Carta {
    public CartaSpeciale(Colori colore, Valori speciale) {
        setColore(colore);
        setValore(speciale);
        setImmagine(new DecoratoreCarta(this).visualizzaCarta());
    }

    public abstract void applicaEffetto();

//    @Override
//    public String getColore() {
//        return colore.toString();
//    }
//
//    @Override
//    public String getValoreIntero() {
//        return valore.getValoreIntero();
//    }
}
