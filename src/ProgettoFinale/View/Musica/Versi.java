package ProgettoFinale.View.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Versi implements Musica{
    private Clip clip;
    private List<File> playListCompleta= new ArrayList<>();
    private List<File> playList = new ArrayList<>();
    private FloatControl fc;

    private List<String> lines;

    private File file = new File("musica.txt");

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

    @Override
    public void regola() {
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(getVolume());
    }


    public void aggiungiCanzone(int i){
        playList.add(playListCompleta.get(i));
    }

    public void setVolume(float volume){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(2, String.valueOf(volume));
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getVolume(){
        try {
            return Float.parseFloat(Files.readAllLines(file.toPath()).get(2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        clip.close();
    }

    @Override
    public void play() {
        clip.start();
    }

    @Override
    public void stop() {
        clip.stop();
    }

    @Override
    public List<File> getPlayList() {
        return playListCompleta;
    }

    public void riproduciVerso(int i){
        setTraccia(i);
        regola();
        play();
    }
}
