package ProgettoFinale.View.Musica;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Ambiente implements Musica{

    private Clip clip;
    private List<File> playList= new ArrayList<>();
    private FloatControl fc;

    private List<String> lines;

    private File file = new File("musica.txt");


    public Ambiente(){
        playList.add(new File("Risorse/audio/Ambiente/lavandonia.wav")); //0
        playList.add(new File("Risorse/audio/Ambiente/battaglia.wav")); //1
        playList.add(new File("Risorse/audio/Ambiente/scheda allenatore.wav")); //2
        playList.add(new File("Risorse/audio/Ambiente/centro pokemon.wav")); //3
        playList.add(new File("Risorse/audio/Ambiente/Battaglia Campione.wav")); //4
        playList.add(new File("Risorse/audio/Ambiente/menu principale.wav")); //5
    }


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

    public void setVolume(float volume){
        try {
            lines = Files.readAllLines(file.toPath());
            lines.set(0, String.valueOf(volume));
            Files.write(file.toPath(), lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float getVolume(){
        try {
            return Float.parseFloat(Files.readAllLines(file.toPath()).get(0));
        } catch (IOException e) {
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

    public void loop() {
        clip.loop(-1);
    }

    @Override
    public void stop() {
        clip.stop();
    }

    @Override
    public List<File> getPlayList() {
        return playList;
    }

    public void riproduciMusicaAmbiente(int i){
        setTraccia(i);
        regola();
        play();
        loop();
    }
}
