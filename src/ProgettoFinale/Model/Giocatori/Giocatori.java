package ProgettoFinale.Model.Giocatori;

import ProgettoFinale.Model.Carte.Carta;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Classe astratta dei giocatori
 */
public abstract class Giocatori extends Observable {
    protected ArrayList<Carta> mano = new ArrayList<>();
    protected String nome;

    /**
     * Metodo che ritorna la mano del giocatore
     * @return
     */
    public ArrayList<Carta> getMano() {
        return mano;
    }

    /**
     * Metodo che ritorna il nome del giocatore
     * @return
     */
    public String getNome(){
        return nome;
    }

    /**
     * Metodo che dato in input una stringa cambia il nome del giocatore
     * @param nome
     */
    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     *
     */
    @Override
    public void notifyObservers() {
        this.setChanged();
        super.notifyObservers();
    }
}

