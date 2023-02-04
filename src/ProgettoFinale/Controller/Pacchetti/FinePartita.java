package ProgettoFinale.Controller.Pacchetti;

/**
 * Pacchetto per notificare la fine della partita
 */
public class FinePartita {
    private boolean esitoPartita;

    /**
     * Costruttore del pacchetto
     * @param esitoPartita
     */
    public FinePartita(boolean esitoPartita) {
        this.esitoPartita = esitoPartita;
    }
}
