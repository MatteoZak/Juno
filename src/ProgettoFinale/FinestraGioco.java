package ProgettoFinale;

import ProgettoFinale.Controller.Pacchetti.*;
import ProgettoFinale.Model.Giocatori.Giocatore;
import ProgettoFinale.View.Animazioni.Animazione;
import ProgettoFinale.View.GameView;
import ProgettoFinale.View.Informazioni;
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;
import ProgettoFinale.View.Modalita.SelezionaCarteExtra;
import ProgettoFinale.View.Modalita.SelezioneModalita;
import ProgettoFinale.View.Musica.Ambiente;
import ProgettoFinale.View.Musica.Effetti;
import ProgettoFinale.View.Musica.Versi;
import ProgettoFinale.View.Profilo.ProfiloView;
import ProgettoFinale.View.ImpostazioniAudio.Audio;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Classe usata come View ed è la nostra finestra in cui posizioniamo tutti i label
 */
public class FinestraGioco extends JFrame implements Observer {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int height = (int) screenSize.getHeight();
    protected int width = (int) screenSize.getWidth();
    private JLabel principale = new JLabel();
    private JLabel menu = new JLabel();
    private JButton giocaButton = new JButton();
    private JButton profiloButton = new JButton();
    private JButton audioButton = new JButton();
    private JButton chiudiButton = new JButton();

    private JLabel zacchino = new JLabel();
    private JLabel rossoSprite = new JLabel();

    private JLabel naticchioni = new JLabel();
    private JLabel blueSprite = new JLabel();
    private JLabel piePagina = new JLabel();
    private GameView gw = new GameView();
    private ProfiloView pw = new ProfiloView(width,height);
    private Audio audio = new Audio(width,height);
    private SelezioneModalita modalita = new SelezioneModalita(width,height);
    private SelezionaCarteExtra carteExtra = new SelezionaCarteExtra(width,height);
    private Informazioni info = new Informazioni(width,height);

    private Effetti effetti = new Effetti();
    private Ambiente ambiente = new Ambiente();
    private Versi versi = new Versi();

    private Animazione animazione;

    /**
     * Costruttore che chiama la super classe
     * e non usa layout, in cui vengono aggiunto tutti i label
     */
    public FinestraGioco(){
        super("Juno!");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        pack();
        setLayout(null);
        getContentPane().setBackground(new Color(42, 162, 42));
        principale.setBounds(0,0,width,height);

        menu.setBounds(width/2-250,100,500,height-200);
        menu.setBorder(new LineBorder(Color.BLUE,20,true));
        menu.setLayout(new GridBagLayout());

        giocaButton.setPreferredSize(new Dimension(250,150));
        giocaButton.setActionCommand("GIOCA");
        giocaButton.setBorderPainted(false);
        giocaButton.setContentAreaFilled(false);
        giocaButton.setFocusPainted(false);

        profiloButton.setPreferredSize(new Dimension(350,150));
        profiloButton.setActionCommand("PROFILO");
        profiloButton.setBorderPainted(false);
        profiloButton.setContentAreaFilled(false);
        profiloButton.setFocusPainted(false);

        audioButton.setPreferredSize(new Dimension(350,150));
        audioButton.setActionCommand("AUDIO");
        audioButton.setBorderPainted(false);
        audioButton.setContentAreaFilled(false);
        audioButton.setFocusPainted(false);

        chiudiButton.setPreferredSize(new Dimension(250,150));
        chiudiButton.setActionCommand("CHIUDI");
        chiudiButton.setBorderPainted(false);
        chiudiButton.setContentAreaFilled(false);
        chiudiButton.setFocusPainted(false);

        zacchino.setBounds(menu.getX()/2-200,height/2-330,400,90);
        rossoSprite.setBounds(menu.getX()/2-131,height/2-230,262,459);
        naticchioni.setBounds(menu.getX()+menu.getWidth()+((width-menu.getX()-menu.getWidth())/2)-200,height/2-331,400,90);
        blueSprite.setBounds(menu.getX()+menu.getWidth()+((width-menu.getX()-menu.getWidth())/2)-107,height/2-231,214,462);

        piePagina.setBounds(width/2-400,height-90,900,90);

        try {
            zacchino.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Zacchino.png"))
                    .getScaledInstance(400,90, 16)));
            rossoSprite.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Avatars/Rosso.png"))
                    .getScaledInstance(262,459, 16)));
            giocaButton.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Gioca.png"))
                    .getScaledInstance(250, 500/4, 16)));
            profiloButton.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Profilo.png"))
                    .getScaledInstance(350, 500/4, 16)));
            audioButton.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Audio.png"))
                    .getScaledInstance(350, 500/4, 16)));
            chiudiButton.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Chiudi.png"))
                    .getScaledInstance(250, 500/4, 16)));
            naticchioni.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/Naticchioni.png"))
                    .getScaledInstance(400,90, 16)));
            blueSprite.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Avatars/Blu.png"))
                    .getScaledInstance(214,462, 16)));
            piePagina.setIcon(new ImageIcon(ImageIO.read(new File("Risorse/images/Utilita/PiePagina.png"))
                    .getScaledInstance(900,90, 16)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

////////////////////////    GRIDBAG    ///////////////////////////////

        ////////////////////   GIOCA  ////////////////////
        GridBagConstraints gbc100 = new GridBagConstraints();
        gbc100.gridx = 0;
        gbc100.gridy = 0;
        gbc100.weighty = 0.5;
        menu.add(giocaButton,gbc100);

        ////////////////////   PROFILO  ////////////////////
        GridBagConstraints gbc101 = new GridBagConstraints();
        gbc101.gridx = 0;
        gbc101.gridy = 1;
        gbc101.weighty = 0.5;
        menu.add(profiloButton,gbc101);

        ////////////////////   OPZIONI  ////////////////////
        GridBagConstraints gbc102 = new GridBagConstraints();
        gbc102.gridx = 0;
        gbc102.gridy = 2;
        gbc102.weighty = 0.5;
        menu.add(audioButton,gbc102);

        ////////////////////   CHIUDI  ////////////////////
        GridBagConstraints gbc103 = new GridBagConstraints();
        gbc103.gridx = 0;
        gbc103.gridy = 3;
        gbc103.weighty = 0.5;
        menu.add(chiudiButton,gbc103);

////////////////////////////////////////////////////////////////////

        principale.add(menu);
        add(gw);
        add(pw);
        add(audio);
        add(info);
        principale.add(zacchino);
        principale.add(rossoSprite);
        principale.add(naticchioni);
        principale.add(blueSprite);
        principale.add(piePagina);
        add(modalita);
        add(carteExtra);
        add(principale);

        setVisible(true);
    }

    /**
     * Metodo che ritorna il label delle informazioni
     * @return
     */
    public Informazioni getInfo() {
        return info;
    }

    /**
     * Metodo che ritorna il label delle carte extra
     * @return
     */
    public SelezionaCarteExtra getCarteExtra() {
        return carteExtra;
    }

    /**
     * Metodo che ritorna il label delle modalità
     * @return
     */
    public SelezioneModalita getModalita() {
        return modalita;
    }

    /**
     * Metodo che ritorna il bottone per giocare
     * @return
     */
    public JButton getGiocaButton() {
        return giocaButton;
    }

    /**
     * Metodo che ritorna il bottone per il profilo
     * @return
     */
    public JButton getProfiloButton() {
        return profiloButton;
    }

    /**
     * Metodo che ritorna il bottone per l'audio
     * @return
     */
    public JButton getAudioButton() {
        return audioButton;
    }

    /**
     * Metodo che ritorna il bottone per chiudere il gioco
     * @return
     */
    public JButton getChiudiButton() {
        return chiudiButton;
    }

    /**
     * Metodo che ritorna il label del menu principale
     * @return
     */
    public JLabel getPrincipale() {
        return principale;
    }

    /**
     * Metodo che ritorna il label del menu
     * @return
     */
    public JLabel getMenu() {
        return menu;
    }

    /**
     * Metodo che ritorna la GameView
     * @return
     */
    public GameView getGw() {
        return gw;
    }

    /**
     * Metodo che ritorna il Profilo View
     * @return
     */
    public ProfiloView getPw() {
        return pw;
    }

    /**
     * Metodo che ritorna l'audio
     * @return
     */
    public Audio getAudio() {
        return audio;
    }

    /**
     * Meotdo che ritorna la classe ambiente
     * @return
     */
    public Ambiente getAmbiente(){return ambiente;}

    /**
     * Metodo che ritorna la classe versi
     * @return
     */
    public Versi getVersi(){return versi;}

    /**
     * Metodo che gestisce la fine della partita e fa ritornare nel menù principale
     */
    public void partitaFinita(){
        gw.setEnabled(false);
        JFrame uscita = new JFrame();
        JOptionPane.showMessageDialog(uscita,"FINE PARTITA!");
        effetti.riproduciEffettoSpeciale(8);
        gw.setVisible(false);
        getPrincipale().setVisible(true);
        getMenu().setVisible(true);
        ambiente.stop();
        ambiente.riproduciMusicaAmbiente(5);
    }

    /**
     * Metodo che esegue l'update a seconda dell'istanza del pacchetto
     * passato come input
     * @param o     the observable object.
     * @param arg   an argument passed to the {@code notifyObservers}
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof AvvisoPescata) {
            effetti.riproduciEffettoSpeciale(1);
            gw.animazioneGiocatori(((AvvisoPescata) arg).getGiocatorePescante().getNome());
            gw.getLabelManoGiocatore().add(new BottoneCarta(((AvvisoPescata) arg).getCartaPescata(),
                                            ((AvvisoPescata) arg).getCtrl()));
            gw.getLabelManoGiocatore().visualizzaCarte(((AvvisoPescata) arg).getGiocatorePescante().getMano(),
                                                        ((AvvisoPescata) arg).getCtrl());
        } else if (arg instanceof AvvisoPescataComputer){
            effetti.riproduciEffettoSpeciale(1);
            gw.animazioneGiocatori(((AvvisoPescataComputer) arg).getGiocatorePescante().getNome());
            gw.aggiornaMano(((AvvisoPescataComputer) arg).getGiocatorePescante().getNome(),
                            ((AvvisoPescataComputer) arg).getGiocatorePescante().getMano());

        } else if (arg instanceof AvvisoGiocata) {
            //TODO: Creare metodo in gw per animazioneGiocatoreGioca
            if(Integer.parseInt(((AvvisoGiocata) arg).getBottoneCarta().getCarta().getValoreIntero())<10)
                effetti.riproduciEffettoSpeciale(2);
            else if (Integer.parseInt(((AvvisoGiocata) arg).getBottoneCarta().getCarta().getValoreIntero())==10
                    ||Integer.parseInt(((AvvisoGiocata) arg).getBottoneCarta().getCarta().getValoreIntero())==11)
                effetti.riproduciEffettoSpeciale(3);
            else effetti.riproduciEffettoSpeciale(4);
            gw.getAnimazioneGiocatoreGioca().setImage(((AvvisoGiocata) arg).getBottoneCarta().getCarta().getImmagine());
            gw.getAnimazioneGiocatoreGioca().setX(((AvvisoGiocata) arg).getBottoneCarta().getX());
            gw.getAnimazioneGiocatoreGioca().setY(gw.getLabelManoGiocatore().getY()-350);
            gw.getAnimazioneGiocatoreGioca().timer();
            gw.getSfondo().add(gw.getAnimazioneGiocatoreGioca());
            gw.getLabelManoGiocatore().visualizzaCarte(((AvvisoGiocata) arg).getGiocatore().getMano(),
                                                        ((AvvisoGiocata) arg).getCtrl());
            gw.getPilaScarti().setIcon(new ImageIcon(((AvvisoGiocata) arg).getBottoneCarta().getCarta().getImmagine()));
        } else if (arg instanceof AvvisoGiocataComputer) {
            if(Integer.parseInt(((AvvisoGiocataComputer) arg).getCartaGiocata().getValoreIntero())<10)
                effetti.riproduciEffettoSpeciale(2);
            else if (Integer.parseInt(((AvvisoGiocataComputer) arg).getCartaGiocata().getValoreIntero())==10
                    ||Integer.parseInt(((AvvisoGiocataComputer) arg).getCartaGiocata().getValoreIntero())==11)
                effetti.riproduciEffettoSpeciale(3);
            else effetti.riproduciEffettoSpeciale(4);
            gw.animazioneGiocatoriGiocaCarta(((AvvisoGiocataComputer) arg).getGiocatore().getNome(),
                                                ((AvvisoGiocataComputer) arg).getCartaGiocata());
            gw.aggiornaMano(((AvvisoGiocataComputer) arg).getGiocatore().getNome(),
                            ((AvvisoGiocataComputer) arg).getGiocatore().getMano());
        } else if (arg instanceof PassaTurno){
            gw.segnaGiocatoreAttivo(((PassaTurno) arg).getGiocatoreDiTurno());
            if(((PassaTurno) arg).getGiocatoreDiTurno().contains("Sx")) versi.riproduciPlaylist(0);
            else if ((((PassaTurno) arg).getGiocatoreDiTurno().contains("Su")))
                versi.riproduciPlaylist(1);
            else if ((((PassaTurno) arg).getGiocatoreDiTurno().contains("Dx"))) versi.riproduciPlaylist(2);

        } else if (arg instanceof  Aggiornamento){
            Arrays.stream(((Aggiornamento) arg).getGiocatore()).forEach(x ->
                                                                {if (x instanceof Giocatore){
                                                                    gw.getLabelManoGiocatore().visualizzaCarte(x.getMano(),
                                                                            ((Aggiornamento) arg).getCtrl());}
                                                                else{gw.aggiornaMano(x.getNome(), x.getMano());}
                                                                });
        } else if (arg instanceof AvvisoPilaScarti){
            gw.getPilaScarti().setIcon(new ImageIcon(((AvvisoPilaScarti) arg).getImgCarta()));
        }  else if (arg instanceof FinePartita){
            effetti.riproduciEffettoSpeciale(9);
            partitaFinita();
        }
    }
}

//new java.awt.Color(31, 122, 31)
