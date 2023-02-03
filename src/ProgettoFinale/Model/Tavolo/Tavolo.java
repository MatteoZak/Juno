package ProgettoFinale.Model.Tavolo;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Controller.Pacchetti.*;
import ProgettoFinale.Model.Carte.Carta;
import ProgettoFinale.Model.Carte.Mazzo;
import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.Model.TurnManager;

import javax.swing.*;
import java.awt.event.ActionListener;
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
    private TurnManager tm = new TurnManager(giocatore,computerSx,computerSu,computerDx );
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

    public void pescata(Giocatori giocatore, Controller ctrl){
        Carta c = mazzo.pesca();
        giocatore.pescata(c);
        if (giocatore instanceof Giocatore){
            notificaCambiamenti(new AvvisoPescata(c, (Giocatore) giocatore, ctrl));
        }else {
            notificaCambiamenti(new AvvisoPescataComputer((Computer) giocatore));
        }
    }

    public void giocataPescata(Carta carta){
        getScarti().push(carta);
        notificaCambiamenti(new AvvisoGiocataComputer(carta, giocatore));
    }

    public void giocata(Giocatori giocatore, Carta carta){
        giocatore.getMano().remove(carta);
        getScarti().push(carta);
        notificaCambiamenti(new AvvisoGiocataComputer(carta, giocatore));
    }

    public void giocata(Giocatori giocatore, Carta carta, Controller ctrl){
        giocatore.getMano().remove(carta);
        getScarti().push(carta);
        if (giocatore instanceof Giocatore){
            notificaCambiamenti(new AvvisoGiocata(carta, giocatore, ctrl));
        }else{
        notificaCambiamenti(new AvvisoGiocataComputer(carta, giocatore));
        }
    }

    public void turnoPassato(){
        tm.passaTurno();
        notificaCambiamenti(new PassaTurno(tm.getGiocatoreDiTurno().getNome()));
    }

    public void finePartita(boolean esito){
        if (esito){
            getGiocatore().vittoria();
            getGiocatore().livellamento(100);
        } else {
            getGiocatore().sconfitta();
            getGiocatore().livellamento(50);
        }
        notificaCambiamenti(new FinePartita(esito));
    }

    public TurnManager getTm() {
        return tm;
    }


    public void notificaCambiamenti(Object obj) {
        this.setChanged();
        this.notifyObservers(obj);
    }
}
