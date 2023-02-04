package ProgettoFinale.Model.Tavolo;

import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Carte.Mazzo;
import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.Model.GestoreTurno;
import java.util.Observable;
import java.util.Stack;

/**
 * Classe che rappresenta il tavolo del gioco
 */
public class Tavolo extends Observable {
    private static Tavolo tavoloInstance;
    private Giocatore giocatore = new Giocatore();
    private Computer computerSx = new Computer();
    private Computer computerSu = new Computer();
    private Computer computerDx = new Computer();
    private GestoreTurno tm = new GestoreTurno(giocatore,computerSx,computerSu,computerDx );
    private Mazzo mazzo;
    private Stack<Carta> scarti = new Stack();

    /**
     * Costruttore del tavolo in cui vengono messi i nomi dei giocatori e creato il mazzo
     */
    private Tavolo(){
        giocatore.setNome("giocatore");
        computerSx.setNome("computerSx");
        computerSu.setNome("computerSu");
        computerDx.setNome("computerDx");
        setNuovoMazzo();
    }

    /**
     * Metodo per creare il nuovo mazzo
     */
    public void setNuovoMazzo() {
        this.mazzo = new Mazzo();
    }

    /**
     * Metodo che ritorna il giocatore (utente)
     * @return il giocatore
     */
    public Giocatore getGiocatore() {
        return giocatore;
    }

    /**
     * Metodo che ritorna l'avversario a sinistra dell'utente
     * @return avversario a sinistra
     */
    public Computer getComputerSx() {
        return computerSx;
    }

    /**
     * Metodo che ritorna l'avversario sopra l'utente
     * @return avversario in alto
     */
    public Computer getComputerSu() {
        return computerSu;
    }

    /**
     * Metodo che ritorna l'avversario a destra dell'utente
     * @return avversario a destra
     */
    public Computer getComputerDx() {
        return computerDx;
    }

    /**
     * Metodo che ritorna il mazzo
     * @return il mazzo
     */
    public Mazzo getMazzo() {
        return mazzo;
    }

    /**
     * Metodo che ritorna la pila degli scarti
     * @return
     */
    public Stack<Carta> getScarti() {
        return scarti;
    }

    /**
     * Metodo per rendere il Tavolo Singleton
     * @return l'istanza del Tavolo senza crearne uno nuovo se già esiste
     */
    public static Tavolo getTavoloInstance() {
        if (tavoloInstance == null) tavoloInstance = new Tavolo();
        return tavoloInstance;
    }

    public void pescata(Giocatori giocatore, Carta c){
        giocatore.pescata(c);
    }


    public void giocata(Giocatori giocatore, Carta carta){
        giocatore.getMano().remove(carta);
    }

    public void cambioPilaScarti(Carta carta){
        getScarti().push(carta);
    }

    public void turnoPassato(){
        tm.passaTurno();
    }

    public void finePartita(boolean esito){
        if (esito){
            getGiocatore().vittoria();
            getGiocatore().livellamento(100);
        } else {
            getGiocatore().sconfitta();
            getGiocatore().livellamento(50);
        }
    }

    public GestoreTurno getTm() {
        return tm;
    }


    public void notificaCambiamenti(Object obj) {
        this.setChanged();
        this.notifyObservers(obj);
    }
}
