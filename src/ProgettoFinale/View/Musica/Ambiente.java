package ProgettoFinale.View.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe usata per la musica in sottofondo
 */
public class Ambiente implements Musica{

    private Clip clip;
    private List<File> playList= new ArrayList<>();
    private FloatControl fc;

    private List<String> lines;

    private File file = new File("musica.txt");

    /**
     * Costruttore in cui vengono aggiunte delle canzoni nella playlist
     */
    public Ambiente(){
        playList.add(new File("Risorse/audio/Ambiente/lavandonia.wav")); //0
        playList.add(new File("Risorse/audio/Ambiente/battaglia.wav")); //1
        playList.add(new File("Risorse/audio/Ambiente/scheda allenatore.wav")); //2
        playList.add(new File("Risorse/audio/Ambiente/centro pokemon.wav")); //3
        playList.add(new File("Risorse/audio/Ambiente/Battaglia Campione.wav")); //4
        playList.add(new File("Risorse/audio/Ambiente/menu principale.wav")); //5
        playList.add(new File("Risorse/audio/Ambiente/avvio.wav")); //6
    }

    /**
     * Metodo che inizializza la clip con la traccia utilizzando un indice
     * @param i
     */
    @Override
    public void setTraccia(int i) {
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
    }

    /**
     * Metodo che regola il volume in base al file di testo
     * @param volume
     */
    public void setVolume(float volume){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(0, String.valueOf(volume));
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
            return Float.parseFloat(Files.readAllLines(file.toPath()).get(0));
        } catch (IOException e) {
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
     * Metodo che manda la stessa canzone in loop
     */
    public void loop() {
        clip.loop(-1);
    }

    /**
     * Metodo che ferma la musica
     */
    @Override
    public void stop() {
        clip.stop();
    }

    /**
     * Metodo che ritorna la playlist
     * @return
     */
    @Override
    public List<File> getPlayList() {
        return playList;
    }

    /**
     * Metodo che riproduce la traccia nella posizione i
     * utilizzando i metodi sopra citati
     * @param i
     */
    public void riproduciMusicaAmbiente(int i){
        setTraccia(i);
        regola();
        play();
        loop();
    }
}
