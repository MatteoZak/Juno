package ProgettoFinale.View.Decorator;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class DecoratoreProfilo extends DecoratoreProfiliAstratto{

    public DecoratoreProfilo(JButton bottoneIcona) {
        super(bottoneIcona);
    }

    @Override
    public void visualizzaAvatar() {
        //TODO: cambiare trattino con costante nell'apposita classe            nome = bottoneIcona.getName().split("-")[1];
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
