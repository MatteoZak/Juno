package ProgettoFinale.View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontPokemon {
    private Font font;
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

    public Font getFont(){
        return font;
    }
}
