package ProgettoFinale.Model.Carte;

import java.awt.*;

/**
 * Classe che rappresenta tutti i tipi di carta
 */
public abstract class Carta {
    protected Image img;
    protected Colori colore;
    protected Valori valore;
    //TODO:refactorEnumColori

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
     * Metodo che ritorna l'immagine della carta
     * @return immagine della carta
     */
    public Image getImmagine(){
        return img;
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
     * Metodo che dato in input un oggetto Image cambia la sua immagine con quello
     * @param img
     */
    public void setImmagine(Image img){
        this.img = img;
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
