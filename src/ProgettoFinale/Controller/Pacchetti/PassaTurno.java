package ProgettoFinale.Controller.Pacchetti;

/**
 * Pacchetto per notificare il passaggio
 * del turno
 */
public class PassaTurno {

    private String giocatoreDiTurno;

    /**
     * Costruttore del pacchetto
     * @param giocatoreDiTurno
     */
    public PassaTurno(String giocatoreDiTurno) {
        this.giocatoreDiTurno = giocatoreDiTurno;
    }

    /**
     * Getter del giocatore di turno
     * @return giocatore di turno
     */
    public String getGiocatoreDiTurno() {
        return giocatoreDiTurno;
    }
}
