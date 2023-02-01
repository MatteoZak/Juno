package ProgettoFinale.Controller.Pacchetti;

import ProgettoFinale.Model.Giocatori.Giocatori;

public class PassaTurno {

    private String giocatoreDiTurno;

    public PassaTurno(String giocatoreDiTurno) {
        this.giocatoreDiTurno = giocatoreDiTurno;
    }

    public String getGiocatoreDiTurno() {
        return giocatoreDiTurno;
    }
}
