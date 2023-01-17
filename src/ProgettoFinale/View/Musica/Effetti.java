package ProgettoFinale.View.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Effetti implements Musica{

    private Clip clip;
    protected List<File> playList= new ArrayList<>();
    protected FloatControl fc;
    private List<String> lines;

    private File file = new File("musica.txt");
    public Effetti(){
        playList.add(new File("Risorse/audio/EffettiSpeciali/drawCard.wav")); //0
        playList.add(new File("Risorse/audio/EffettiSpeciali/playCard1.wav")); //1
        playList.add(new File("Risorse/audio/EffettiSpeciali/playCard2.wav")); //2
        playList.add(new File("Risorse/audio/EffettiSpeciali/plusCard.wav")); //3
        playList.add(new File("Risorse/audio/EffettiSpeciali/shuffle.wav")); //4
        playList.add(new File("Risorse/audio/EffettiSpeciali/uno.wav")); //5
        playList.add(new File("Risorse/audio/EffettiSpeciali/vittoria.wav")); //6
        playList.add(new File("Risorse/audio/EffettiSpeciali/fuga.wav")); //7
        playList.add(new File("Risorse/audio/EffettiSpeciali/lose.wav")); //8
        /*
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(playList.get(0));
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


    public void setVolume(float volume){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(1, String.valueOf(volume));
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getVolume(){
        try {
            return Float.parseFloat(Files.readAllLines(file.toPath()).get(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTraccia(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(playList.get(i));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void regola() {
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(getVolume());
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
        return playList;
    }

    public void riproduciEffettoSpeciale(int i){
        setTraccia(i);
        regola();
        play();
    }
}
