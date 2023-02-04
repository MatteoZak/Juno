package ProgettoFinale.View.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe usata per i versi degli avversari
 */
public class Versi implements Musica{
    private Clip clip;
    private List<File> playListCompleta= new ArrayList<>();
    private List<File> playList = new ArrayList<>();
    private FloatControl fc;

    private List<String> lines;

    private File file = new File("musica.txt");
    /**
     * Costruttore in cui vengono aggiunte delle canzoni nella playlist
     */
    public Versi(){
        playListCompleta.add(new File("Risorse/audio/Versi/charizard.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/typhlosion.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/blaziken.wav"));

        playListCompleta.add(new File("Risorse/audio/Versi/blastoise.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/feraligatr.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/swampert.wav"));

        playListCompleta.add(new File("Risorse/audio/Versi/venusaur.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/meganium.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/sceptile.wav"));
        playListCompleta.add(new File("Risorse/audio/Versi/avvio.wav"));
        /*
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(playListCompleta.get(0));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

         */
    }
    /**
     * Metodo che inizializza la clip con la traccia utilizzando un indice
     * @param i
     */
    @Override
    public void setTraccia(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(playListCompleta.get(i));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo che viene regolato a seconda del volume pre impostato
     */
    @Override
    public void regola() {
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(getVolume());
    }

    /**
     * Metodo usato per aggiungere solo i versi necessari dei tre avversari
     * @param i
     */
    public void aggiungiCanzone(int i){
        playList.add(playListCompleta.get(i));
    }
    /**
     * Metodo che regola il volume in base al file di testo
     * @param volume
     */
    public void setVolume(float volume){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(2, String.valueOf(volume));
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo che ritorna il volume leggendolo dal file di testo
     * @return
     */
    public float getVolume(){
        try {
            return Float.parseFloat(Files.readAllLines(file.toPath()).get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo che chiude la clip
     */
    @Override
    public void close() {
        clip.close();
    }
    /**
     * Metodo che fa partire la clip
     */
    @Override
    public void play() {
        clip.start();
    }
    /**
     * Metodo che ferma la musica
     */
    @Override
    public void stop() {
        clip.stop();
    }

    /**
     * Metodo che ritorna la playlist con i 3 versi degli avversari
     * @return
     */
    @Override
    public List<File> getPlayList() {
        return playList;
    }

    /**
     * Metodo che pulisce la playlist dalle tracce
     */
    public void resetPlaylist(){
        playList.clear();
    }
    public void riproduciVerso(int i){
        setTraccia(i);
        regola();
        play();
    }
    /**
     * Metodo che riproduce la traccia nella posizione i
     * utilizzando i metodi sopra citati
     * @param i
     */
    public void riproduciPlaylist(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(playList.get(i));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        //setTraccia(i);
        regola();
        play();
    }
}
