package ProgettoFinale.View.Decorator;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe che estende la super classe e serve per creare il profilo del giocatore (utente)
 */
public class DecoratoreProfilo extends DecoratoreProfiliAstratto{
    /**
     * Costruttore che richiama quello della super classe
     * @param bottoneIcona
     */
    public DecoratoreProfilo(JButton bottoneIcona) {
        super(bottoneIcona);
    }

    /**
     * Metodo che controlla se è da modificare il giocatore e va
     * a prendere l'immagine dai file
     */
    @Override
    public void visualizzaAvatar() {
        String nome = bottoneIcona.getName();
        if(!bottoneIcona.getName().contains("Giocatore"))
            nome = bottoneIcona.getName().substring(10);
        try {
            bottoneIcona.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Avatars/" + nome +".png"))
                    .getScaledInstance(70, 70, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
