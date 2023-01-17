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

    Avversari(String tipo){
        this.tipo = tipo;
    }

    //TODO:metodi statici per ottenere tutti e 3 i tipi dei pokemon
}
