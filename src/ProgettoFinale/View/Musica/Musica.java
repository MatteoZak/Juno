package ProgettoFinale.View.Musica;

import java.io.File;
import java.util.List;

public interface Musica {
    void setTraccia(int i);
    void regola();
    void close();
    void play();
    void stop();
    List<File> getPlayList();
}
