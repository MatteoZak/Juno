package ProgettoFinale.Model.Carte;

import java.awt.*;

/**
 * Classe astratta che rappresenta tutti i tipi di carta a cui diamo un colore
 * un valore e un immagine
 */
public abstract class Carta {
    protected Colori colore;
    protected Valori valore;

    /**
     * Metodo che ritorna il colore della carta
     * @return il colore sottoforma di stringa
     */
    public String getColore() {
        return this.colore.toString();
    }

    /**
     * Metodo che ritorna il valore della carta
     * @return il valore sottoforma di stringa
     */
    public String getValoreIntero() {
        return valore.getValoreIntero();
    }

    /**
     * Metodo che dato in input un oggetto Colori cambia il suo colore con quello
     * @param colore
     */
    public void setColore(Colori colore) {
        this.colore = colore;
    }

    /**
     * Metodo che dato in input un oggetto Valori cambia il suo valore con quello
     * @param valore
     */
    public void setValore(Valori valore) {
        this.valore = valore;
    }


    /**
     * Metodo che ritorna il colore e il valore della carta
     * @return il colore e il valore sottoforma di un'unica stringa
     */
    @Override
    public String toString() {
        return this.getColore() + this.getValoreIntero();
    }
}
