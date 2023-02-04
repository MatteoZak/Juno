package ProgettoFinale.Model.Giocatori;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

//TODO:singleton?

/**
 * Classe del giocatore (utente) con tutte le sue informazioni
 */
public class Giocatore extends Giocatori{
    private int sogliaExp = 1000;
    private File file = new File("profilo.txt");
    private List<String> lines;

    /**
     * Metodo che utilizza un path per inserire un immagine nell'icona del giocatore
     * @param path
     */
    public void setIcona(String path){
        try {
            Files.copy(Path.of(path), Path.of("Risorse/images/Avatars/IconaGiocatore.png"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare il nome del giocatore
     * @param username
     */
    public void setUsername(String username) {
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(1, username);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare le vittore del giocatore
     * @param vittorie
     */
    public void setVittorie(String vittorie) {
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(2, vittorie);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare le sconfitte del giocatore
     * @param sconfitte
     */
    public void setSconfitte(String sconfitte) {
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(3, sconfitte);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per salvare il livello del giocatore
     * @param livello
     */
    public void setLivello(String livello){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(4, livello);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sogliaExp = 1000;
    }

    /**
     * Metodo che ritorna l'immagine del giocatore dentro un try-catch
     * @return
     */
    public Image getIcona(){
        try {
            return ImageIO.read(new File("Risorse/images/Avatars/IconaGiocatore.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che ritorna il nome del giocatore dentro un try-catch
     * @return
     */
    public String getUsername() {
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(1);
    }

    /**
     * Metodo che ritorna le vittorie del giocatore
     * @return
     */
    public String getVittorie() {
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(2);
    }

    /**
     * Metodo che ritorna le sconfitte del giocatore
     * @return
     */
    public String getSconfitte() {
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(3);
    }

    /**
     * Metodo che ritorna le partite giocate (vittorie e sconfitte)
     * @return
     */
    public String getGiocate(){
        return String.valueOf(Integer.parseInt(getVittorie())+Integer.parseInt(getSconfitte()));
    }

    /**
     * Metodo che ritorna il livello attuale del giocatore
     * @return
     */
    public String getLivello() {
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.get(4);
    }

    /**
     * Metodo che aumenta l'esperienza del giocatore tramite l'esperienza
     * ottenuta con vittorie e sconfitte
     * @param exp
     */
    public void livellamento(int exp){
        String str = String.valueOf(Integer.parseInt(getLivello())+1);
        sogliaExp -= exp;
        if(sogliaExp <= 0){
            lines.set(3, str);
            try {
                Files.write(file.toPath(), lines);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sogliaExp = 1000;
        }
    }

    /**
     * Metodo che aumenta le vittorie del giocatore e le salva sul file di testo
     */
    public void vittoria(){
        String str = String.valueOf(Integer.parseInt(getVittorie())+1);
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(2, str);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che aumenta le sconfitte del giocatore e le salva sul file di testo
     */
    public void sconfitta(){
        String str = String.valueOf(Integer.parseInt(getSconfitte())+1);
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(3, str);
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}