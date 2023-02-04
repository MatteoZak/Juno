package ProgettoFinale.Model.Carte;

import java.util.*;

/**
 * Classe che rappresenta il mazzo del gioco
 */
public class Mazzo {
    private Stack<Carta> mazzo = new Stack();
    private List<Valori> valoriCarteSpeciali = new LinkedList<>();

    /**
     * Costruttore che genera il mazzo tramite l'utilizzo di un suo metodo
     */
    public Mazzo() {
        formaMazzo();
    }

    /**
     * Metodo che aggiunge al mazzo la giusta quantità di carte in base al loro colore e valore
     */
    public void formaMazzo(){
        for (Colori c : Colori.values()){
            if (!(c.equals(Colori.NERO) || c.equals(Colori.VIOLA))) {
                mazzo.push(new CartaNormale(c, Valori.ZERO));
                mazzo.push(new CartaNormale(Colori.NERO, Valori.CAMBIOCOLORE));
                mazzo.push(new CartaNormale(Colori.NERO, Valori.PESCAQUATTRO));
                for (Valori v : Valori.getCarteClassiche()) {
                    if (!(v.equals(Valori.ZERO) || v.equals(Valori.CAMBIOCOLORE) || v.equals(Valori.PESCAQUATTRO))) {
                        mazzo.push(new CartaNormale(c, v));
                        mazzo.push(new CartaNormale(c, v));
                    }
                }
            }
        }
        aggiungiCarteSpeciali();
    }

    /**
     * Metodo che aggiunge le carte speciali personalizzate per la modalità
     */
    public void aggiungiCarteSpeciali(){
        valoriCarteSpeciali.forEach(x->{
            if(x.equals(Valori.DUELLO)){
                mazzo.push(new CartaNormale(Colori.VIOLA, x));
                mazzo.push(new CartaNormale(Colori.VIOLA, x));
            }else{
                for(int i = 0; i < 4; i++){
                    mazzo.push(new CartaNormale(Colori.VIOLA, x));
                }
            }
        });
    }

    /**
     * Metodo che aggiunge le carte speciali se vengono selezionate o le toglie se deselezionate (tramite checkBox)
     * @param valore della carta speciale
     * @param b un booleano che dice se la carta è selezionata o meno
     */
    public void setCarteSpeciali(Valori valore,boolean b){
        if(b)
            valoriCarteSpeciali.add(valore);
        else
            valoriCarteSpeciali.remove(valore);
        System.out.println(valoriCarteSpeciali.toString());
    }

    public List<Valori> getCarteSpecialiSelezionate(){
        return valoriCarteSpeciali;
    }

    /**
     * Metodo che mischia il mazzo
     */
    public void mischiaCarte() {
        Collections.shuffle(mazzo);
    }

    /**
     * Metodo che prende le carte dalla pila degli scarti e le mette nel mazzo e lo mischia
     * @param scarti è la pila degli scarti
     */
    public void mazzoFinito(Stack<Carta> scarti){
        for( Carta c : scarti ){
            mazzo.push(c);
        }
        mischiaCarte();
    }

    /**
     * Metodo che ritorna il mazzo
     * @return il mazzo
     */
    public Stack<Carta> getPilaMazzo() {
        return mazzo;
    }

    /**
     * Metodo che fa pescare tramite il pop del mazzo
     * @return la prima carta in cima
     */
    public Carta pesca(){
        return mazzo.pop();
    }

}

//        for(int i = mazzo.size() - 1; i > 0; --i){
//            int index = sourceRandom.nextInt(i);
//            Carta c = mazzo.get(index);
//            mazzo.set(index,mazzo.get(i));
//            mazzo.set(i,c);
//        }

//for( Colori c : Colori.values() ){
//                if(!c.equals(Colori.NERO)) {
//                    mazzo.push(new CartaNormale(c, Valori.ZERO));
//                    mazzo.push(new CartaNormale(Colori.NERO, Valori.CAMBIOCOLORE));
//                    mazzo.push(new CartaNormale(Colori.NERO, Valori.PESCAQUATTRO));
//                    for ( Valori v : Valori.values() ) {
//                        if (!(v.equals(Valori.ZERO) || v.equals(Valori.CAMBIOCOLORE) || v.equals(Valori.PESCAQUATTRO))) {
//                            mazzo.push(new CartaNormale(c, v));
//                            mazzo.push(new CartaNormale(c, v));
//                        }
//                    }
//                }
//            }