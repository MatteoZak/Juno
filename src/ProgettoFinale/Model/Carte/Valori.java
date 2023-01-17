package ProgettoFinale.Model.Carte;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe che identifica i valori delle carte
 */
public enum Valori {
    ZERO("0"),
    UNO("1"),
    DUE("2"),
    TRE("3"),
    QUATTRO("4"),
    CINQUE("5"),
    SEI("6"),
    SETTE("7"),
    OTTO("8"),
    NOVE("9"),
    INVERTIGIRO("10"),
    BLOCCO("11"),
    PESCADUE("12"),
    CAMBIOCOLORE("13"),
    PESCAQUATTRO("14"),
    BLOCCOTUTTI("15"),
    PESCADUETUTTI("16"),
    PESCATRETUTTI("17"),
    SCAMBIOMANI("18"),
    BLOCCOINVERTIGIRO("19"),
    DUELLO("20");
    private String valoreIntero;

    /**
     * Costruttore
     * @param i
     */
    Valori(String i) {
        valoreIntero = i;
    }

    /**
     * Metodo che ritorna il valore
     * @return il valore sotto forma di stringa
     */
    public String getValoreIntero(){
        return valoreIntero;
    }

    /**
     * Metodo che aggiunge in una lista tutte le carte normali (da 0 a 9)
     * @return ritorna la lista con le carte normali
     */
    public static List<Valori> getCarteClassiche() {
        List<Valori> listaCarteClassiche = new LinkedList<>();
        for(Valori valore : Valori.values()) {
            if(!(valore.equals(Valori.BLOCCOTUTTI) || valore.equals(Valori.PESCADUETUTTI) || valore.equals(Valori.PESCATRETUTTI) ||
                    valore.equals(Valori.SCAMBIOMANI) || valore.equals(Valori.BLOCCOINVERTIGIRO) || valore.equals(Valori.DUELLO)))
                listaCarteClassiche.add(valore);
        }
        return listaCarteClassiche;
    }

}

//    ZERO{public String toString(){return "0";}},
//    UNO{public String toString(){return "1";}},
//    DUE{public String toString(){return "2";}},
//    TRE{public String toString(){return "3";}},
//    QUATTRO{public String toString(){return "4";}},
//    CINQUE{public String toString(){return "5";}},
//    SEI{public String toString(){return "6";}},
//    SETTE{public String toString(){return "7";}},
//    OTTO{public String toString(){return "8";}},
//    NOVE{public String toString(){return "9";}},
//    INVERTIGIRO{public String toString(){return "10";}},
//    BLOCCO{public String toString(){return "11";}},
//    PESCADUE{public String toString(){return "12";}},
//    CAMBIOCOLORE{public String toString(){return "13";}},
//    PESCAQUATTRO{public String toString(){return "14";}},

// public static Valori getValoreDaValoreIntero(Integer i){
//        for(Valori v:Valori.values()){
//            if (v.getValoreIntero().equals(i.toString()))
//                return v;
//        }
//        return null;
//    }