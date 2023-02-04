package ProgettoFinale.View.Musica;

import java.io.File;
import java.util.List;

/**
 * Interfaccia realizzata per i vari metodi che gestiscono i suoni
 */
public interface Musica {
    void setTraccia(int i);
    void regola();
    void close();
    void play();
    void stop();
    List<File> getPlayList();
}
