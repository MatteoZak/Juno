package ProgettoFinale;

import ProgettoFinale.Controller.Controller;
import ProgettoFinale.Model.Tavolo.Tavolo;
import ProgettoFinale.View.NoLayoutTry;

public class Juno {
    public static void main(String[] args) {
        Tavolo t = Tavolo.getTavoloInstance();
        FinestraGioco frame = new FinestraGioco();
        Controller ctrl = Controller.getInstance(frame,t);
    }
}
