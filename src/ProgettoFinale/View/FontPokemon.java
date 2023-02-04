package ProgettoFinale.View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe realizzata per personalizzare le scritte
 */
public class FontPokemon {
    private Font font;

    /**
     * Costruttore con la grandezza da impostare e utilizza
     * il font dato da un percorso
     * @param size
     */
    public FontPokemon(float size){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Risorse/Pokemon Solid.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che ritorna il font
     * @return
     */
    public Font getFont(){
        return font;
    }
}
