package ProgettoFinale.Model;

import java.io.*;

public class Database {

    //forse da togliere
    private String vittorie;
    private String sconfitte;
    private String livello;

    //da migliorare
    public void salvaSuFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutput oos = new ObjectOutputStream(fos);

        oos.writeObject(vittorie);

        oos.close();
        fos.close();
    }

    public void caricaDaFile(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            String vittorieCaricate = (String) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ois.close();
        fis.close();
    }
}
