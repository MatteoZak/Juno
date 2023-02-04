package ProgettoFinale.Model;

import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.Model.Giocatori.Giocatori;
import java.util.*;

/**
 * Classe che gestisce i turni dei giocatori
 */
public class GestoreTurno {
    private int turno;
    private boolean direzione;
    private Giocatore g;
    private Computer computerSx;
    private Computer computerSu;
    private Computer computerDx;
    private List<Giocatori> giocatori = new ArrayList<>();

    /**
     * Costruttore che inizializza i giocatori
     * @param g
     * @param computerSx
     * @param computerSu
     * @param computerDx
     */
    public GestoreTurno(Giocatore g, Computer computerSx, Computer computerSu, Computer computerDx){
        this.g = g;
        this.computerSx = computerSx;
        this.computerSu = computerSu;
        this.computerDx = computerDx;
        turno = 4000;
        giocatori.addAll(List.of(g,computerSx,computerSu,computerDx));
        direzione = true;
    }

    /**
     * Metodo che ritorna il giocatore corrispettivo al turno
     * @return
     */
    public Giocatori getGiocatoreDiTurno() {
        return giocatori.get(turno % giocatori.size());
    }

    /**
     * Metodo che cambia il senso di gioco in base al booleano passato (true = orario, false = antiorario)
     * @param direzione è il senso di gioco
     */
    public void setDirezione(boolean direzione) {
        this.direzione = direzione;
    }

    /**
     * Metodo che ritorna il senso di gioco
     * @return
     */
    public boolean getDirezione(){
        return direzione;
    }

    /**
     * Metodo per cambiare la direzione:
     * effettua lo XOR tra la nostra variabile
     * direzione e il valore true
     * in questo modo inverte la nostra variabile
     */
    public void cambiaDirezione(){
        this.direzione ^= true;
    }

    /**
     * Metodo che in base alla direzione cambia il turno
     */
    public void passaTurno(){
        if (direzione)
            turno++;
        else
            turno--;
    }

    /**
     * Metodo che ritorna il turno corrente
     * @return turno
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Metodo che resetta i turni a una quantità elevata
     */
    public void resetTurni(){
        turno = 4000;
    }

    /**
     * Metodo che ritorna il giocatore successivo in base alla direzione
     * @return giocatore successivo
     */
    public Giocatori getGiocatoreSuccessivo(){
        int i = turno;
        if(direzione)
            return giocatori.get(++i % giocatori.size());
        return giocatori.get(--i % giocatori.size());
    }

    /**
     * Metodo che ritorna tutti i giocatori in una lista
     * @return lista dei giocatori
     */
    public List<Giocatori> getGiocatori() {
        return giocatori;
    }

    /**
     * Metodo che cambia il turno con un intero dato in input
     * @param turno
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }
}

//        if(i<=0) {
//            i = Math.abs(i);
//            int posizione = i % giocatori.size();
//            if (posizione == 3)
//                posizione = 1;
//            else if (posizione == 1)
//                posizione = 3;
//            if (direzione) {
//                return giocatori.get(++posizione% giocatori.size());
//            }
//            return giocatori.get(--posizione% giocatori.size());
//        }
