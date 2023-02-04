package ProgettoFinale.Model.Tavolo;

/**
 * Classe che rappresenta tutti i tipi di avversari presenti nel gioco
 */
public enum Avversari {
    CHARIZARD("fuoco"),
    TYPLHOSION("fuoco"),
    BLAZIKEN("fuoco"),
    BLASTOISE("acqua"),
    FERALIGATR("acqua"),
    SWAMPERT("acqua"),
    VENUSAUR("erba"),
    MEGANIUM("erba"),
    SCEPTILE("erba");

    private String tipo;

    /**
     * Costruttore a cui viene assegnato il tipo dell'avversario (fuoco,acqua,erba)
     * @param tipo
     */
    Avversari(String tipo){
        this.tipo = tipo;
    }

}
