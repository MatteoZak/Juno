package ProgettoFinale;

import ProgettoFinale.Controller.Pacchetti.AvvisoPescata;
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
import java.util.Observable;
import java.util.Observer;

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

    public Informazioni getInfo() {
        return info;
    }

    public SelezionaCarteExtra getCarteExtra() {
        return carteExtra;
    }

    public SelezioneModalita getModalita() {
        return modalita;
    }

    public JButton getGiocaButton() {
        return giocaButton;
    }

    public JButton getProfiloButton() {
        return profiloButton;
    }

    public JButton getAudioButton() {
        return audioButton;
    }

    public JButton getChiudiButton() {
        return chiudiButton;
    }

    public JLabel getPrincipale() {
        return principale;
    }

    public JLabel getMenu() {
        return menu;
    }

    public GameView getGw() {
        return gw;
    }

    public ProfiloView getPw() {
        return pw;
    }

    public Audio getAudio() {
        return audio;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof AvvisoPescata) {
            effetti.riproduciEffettoSpeciale(0);
            gw.getLabelManoGiocatore().add(new BottoneCarta(((AvvisoPescata) arg).getCartaPescata(), ((AvvisoPescata) arg).getCtrl()));
            gw.getLabelManoGiocatore().visualizzaMano((Giocatore) ((AvvisoPescata) arg).getGiocatorePescante(),((AvvisoPescata) arg).getCtrl());
        }
    }
}

//new java.awt.Color(31, 122, 31)
