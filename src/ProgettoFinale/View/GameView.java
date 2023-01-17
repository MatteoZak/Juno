package ProgettoFinale.View;

import ProgettoFinale.Utilita.Costanti;
import ProgettoFinale.View.Animazioni.*;
import ProgettoFinale.View.ImpostazioniAudio.Audio;
import ProgettoFinale.View.ManiGiocatori.LabelManoComputerDx;
import ProgettoFinale.View.ManiGiocatori.LabelManoComputerSu;
import ProgettoFinale.View.ManiGiocatori.LabelManoComputerSx;
import ProgettoFinale.View.ManiGiocatori.LabelManoGiocatore;
import ProgettoFinale.View.Scelte.LabelSceltaColore;
import ProgettoFinale.View.Scelte.LabelSelezionaGiocatore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JLabel{
    private JLabel sfondo = new JLabel();
    private JLabel labelAudio = new JLabel("Audio");
    private JButton bottoneAudio = new JButton();
    private JButton bottoneMenu = new JButton();
    private JLabel labelMenu = new JLabel("Menù");
    private JButton bottoneSpiegazioni = new JButton();
    private JLabel labelSpiegazioni = new JLabel("Aiuto");
    private JButton avatarGiocatore = new JButton();
    private JLabel nomeGiocatore = new JLabel();
    private LabelManoGiocatore labelManoGiocatore = new LabelManoGiocatore();

    private JButton avatarComputerSx = new JButton();
    private JLabel nomeComputerSx = new JLabel();
    private LabelManoComputerSx labelManoComputerSx = new LabelManoComputerSx();

    private JButton avatarComputerSu = new JButton();
    private JLabel nomeComputerSu = new JLabel();
    private LabelManoComputerSu labelManoComputerSu = new LabelManoComputerSu();

    private JButton avatarComputerDx = new JButton();
    private JLabel nomeComputerDx = new JLabel();
    private LabelManoComputerDx labelManoComputerDx = new LabelManoComputerDx();

    private LabelSceltaColore labelSceltaColore = new LabelSceltaColore();

    private JButton tastoJuno = new JButton();

    private JButton passaTurno = new JButton("Passa");
    private JLabel labelDirezione = new JLabel("Direzione: ");
    private JLabel direzioneGiro = new JLabel();

    private JLabel pilaScarti = new JLabel();

    private JButton pilaMazzo = new JButton();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int height = (int) screenSize.getHeight();
    protected int width = (int) screenSize.getWidth();

    private Informazioni info = new Informazioni(width,height);
    private Audio audio = new Audio(width,height);
    private LabelSelezionaGiocatore selezionaGiocatore = new LabelSelezionaGiocatore();
    private final String ACTIONCOMMAND = "AVATAR";
    private AnimazioneGiocatore animazioneGiocatore = new AnimazioneGiocatore();
    private AnimazioneGiocatoreGioca animazioneGiocatoreGioca = new AnimazioneGiocatoreGioca();
    private AnimazioneComputerSxGioca animazioneComputerSxGioca = new AnimazioneComputerSxGioca();
    private AnimazioneComputerSx animazioneComputerSx = new AnimazioneComputerSx();
    private AnimazioneComputerSu animazioneComputerSu = new AnimazioneComputerSu();
    private AnimazioneComputerSuGioca animazioneComputerSuGioca = new AnimazioneComputerSuGioca();
    private AnimazioneComputerDx animazioneComputerDx = new AnimazioneComputerDx();
    private AnimazioneComputerDxGioca animazioneComputerDxGioca = new AnimazioneComputerDxGioca();
    private Costanti costanti = new Costanti();
    public GameView() {
        setSize(width,height);
        setVisible(false);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

////////////////////////     SFONDO     /////////////////////////////////////////
        sfondo.setBounds(0,0,width,height);
        sfondo.setOpaque(true);
        sfondo.setBackground(Color.cyan);
        sfondo.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathSfondoGameView())
                .getScaledInstance(width,height,Image.SCALE_DEFAULT)));
        add(sfondo);

        bottoneAudio.setBounds(width-50,0,50,50);
        bottoneAudio.setActionCommand("INDIETRO");
        sfondo.add(bottoneAudio);
////////////////////////////////////////////////////////////////////////////////

////////////////////////     LABEL GIOCATORE     ///////////////////////////////
        labelManoGiocatore.setBounds(210,height-200,width-420,200);
        sfondo.add(labelManoGiocatore);
////////////////////////////////////////////////////////////////////////////////

////////////////////////     LABEL BOTSX     ///////////////////////////////////
        labelManoComputerSx.setBounds(0,210,200,height-420);
        sfondo.add(labelManoComputerSx);
////////////////////////////////////////////////////////////////////////////////

////////////////////////     LABEL MANOBOTSU     ///////////////////////////////
        labelManoComputerSu.setBounds(210,0,width-420,200);
        sfondo.add(labelManoComputerSu);
///////////////////////////////////////////////////////////////////////////////

////////////////////////     LABEL MANOBOTDX     //////////////////////////////
        labelManoComputerDx.setBounds(width-200,210,200,height-420);
        sfondo.add(labelManoComputerDx);
///////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////     CENTRO     ////////////////////////////////////////////////////

        //////////////////////////////////////////////// SCARTI ////////////////////////////////////////////////
        pilaScarti.setBounds(width/2-200,height/2-75,100,150);
        sfondo.add(pilaScarti);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////// MAZZO ////////////////////////////////////////////////

        pilaMazzo.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathDorsoCarte())
                .getScaledInstance(100, 150, 16)));
        pilaMazzo.setBounds(width/2+100,height/2-75,100,150);
        pilaMazzo.setBorderPainted(false);
        pilaMazzo.setContentAreaFilled(false);
        pilaMazzo.setActionCommand(costanti.getActionCommandBottoneAudio());
        sfondo.add(pilaMazzo);


        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        Font font = new FontPokemon(18f).getFont();
        ////////////////////////////////////////////////// AVATARS //////////////////////////////////////////////////////
        avatarGiocatore.setName("IconaGiocatore");
        avatarGiocatore.setBounds(width / 2 - 40, height - getLabelManoGiocatore().getHeight()-80, 70, 70);
        nomeGiocatore.setBounds(width / 2 + 40, height - getLabelManoGiocatore().getHeight()-40, 100, 25);
        nomeGiocatore.setFont(font);
        sfondo.add(nomeGiocatore);
        avatarComputerSx.setBounds(getLabelManoComputerSx().getWidth(), height / 2 - 40, 70, 70);
        nomeComputerSx.setBounds(getLabelManoComputerSx().getWidth(), height / 2 + 40, 100, 25);
        nomeComputerSx.setFont(font);
        sfondo.add(nomeComputerSx);
        avatarComputerSu.setBounds(width / 2 - 40, getLabelManoComputerSu().getHeight(), 70, 70);
        nomeComputerSu.setBounds(width / 2 +40, getLabelManoComputerSu().getHeight()+40, 100, 25);
        nomeComputerSu.setFont(font);
        sfondo.add(nomeComputerSu);
        avatarComputerDx.setBounds(width - getLabelManoComputerDx().getWidth()-80, height / 2 - 40, 70, 70);
        nomeComputerDx.setBounds(width - getLabelManoComputerDx().getWidth()-100, height / 2 - 40-25, 100, 25);
        nomeComputerDx.setFont(font);
        sfondo.add(nomeComputerDx);

        getListaAvatars().forEach(x->{
            x.setFocusPainted(false);
            x.setContentAreaFilled(false);
            x.setActionCommand(ACTIONCOMMAND);
        });
        avatarGiocatore.setFocusPainted(false);
        avatarGiocatore.setContentAreaFilled(false);

        sfondo.add(avatarGiocatore);
        sfondo.add(avatarComputerSx);
        sfondo.add(avatarComputerSu);
        sfondo.add(avatarComputerDx);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////// SCELTA COLORI //////////////////////////////////////////////
        labelSceltaColore.setBounds(getPilaScarti().getX()+getPilaScarti().getWidth(),
                getLabelManoComputerSu().getHeight()+ getAvatarComputerSu().getHeight(),
                getPilaMazzo().getX()-(getPilaScarti().getX()+getPilaScarti().getWidth()),
                getAvatarGiocatore().getY()-(getLabelManoComputerSu().getHeight()+ getAvatarComputerSu().getHeight()));
        labelSceltaColore.setSceltaRosso();
        labelSceltaColore.setSceltaBlu();
        labelSceltaColore.setSceltaGiallo();
        labelSceltaColore.setSceltaVerde();
        labelSceltaColore.setVisible(false);
        sfondo.add(labelSceltaColore);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////// FRECCE //////////////////////////////////////////////
        selezionaGiocatore.setBounds(0,0,width,height);
        selezionaGiocatore.setSelezioneSx(avatarComputerSx.getX()+avatarComputerSx.getWidth()+10,avatarComputerSx.getY());
        selezionaGiocatore.setSelezioneSu(avatarComputerSu.getX()+15,avatarComputerSu.getY()+avatarComputerSu.getWidth()+10);
        selezionaGiocatore.setSelezioneDx(avatarComputerDx.getX()-90,avatarComputerDx.getY());
        selezionaGiocatore.setTesto(width/2,height/2);
        selezionaGiocatore.getTesto().setFont(font);

        sfondo.add(selezionaGiocatore);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////// TASTO UNO ////////////////////////////////////////////
        tastoJuno.setBounds(pilaScarti.getX()-150, pilaScarti.getY(),150,150);
        tastoJuno.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathTastoJuno())
                .getScaledInstance(150, 150, 16)));
        tastoJuno.setBorderPainted(false);
        tastoJuno.setContentAreaFilled(false);
        tastoJuno.setVisible(false);
        tastoJuno.setActionCommand(costanti.getActionCommandTastoJuno());
        sfondo.add(tastoJuno);
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////// TASTO PASSA ////////////////////////////////////////////
        passaTurno.setActionCommand(costanti.getActionCommandTastoPassaTurno());
        passaTurno.setBounds(getAvatarGiocatore().getX()-100,
                             getAvatarGiocatore().getY()+(getAvatarGiocatore().getHeight()/2-25),
                        70,50);
        passaTurno.setVisible(false);
        passaTurno.setFocusPainted(false);
        sfondo.add(passaTurno);
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////// DIREZIONE //////////////////////////////////////////////
        direzioneGiro.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathDirezioneDx())));
        direzioneGiro.setBounds((labelManoComputerSu.getX()-10)/2-50,(labelManoComputerSx.getY()-10)/2-50,100,100);
        labelDirezione.setBounds(direzioneGiro.getX()-10,direzioneGiro.getY()-40,150,50);
        labelDirezione.setFont(font.deriveFont(25f));

        sfondo.add(direzioneGiro);
        sfondo.add(labelDirezione);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////// MENU //////////////////////////////////////////////

        bottoneMenu.setBounds(labelManoComputerDx.getX()+50,labelManoComputerDx.getY()/2-50,100,100);
        bottoneMenu.setActionCommand("PRINCIPALEPARTITA");
        bottoneMenu.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathPokemonCenter())
                .getScaledInstance(100,100,16)));
        bottoneMenu.setBorderPainted(false);
        bottoneMenu.setFocusPainted(false);
        bottoneMenu.setContentAreaFilled(false);

        labelMenu.setBounds(bottoneMenu.getX()+20, bottoneMenu.getY()-30,65,25);
        labelMenu.setFont(font.deriveFont(25f));
        sfondo.add(bottoneMenu);
        sfondo.add(labelMenu);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////// AUDIO //////////////////////////////////////////////
        bottoneAudio.setBounds((labelManoComputerDx.getX()+labelManoComputerDx.getWidth()/2)-50,
                                height-labelManoComputerSu.getHeight()/2-30,
                             100,100);
        bottoneAudio.setActionCommand(costanti.getActionCommandPokemonMarketIndietro());
        bottoneAudio.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathPokemonMarket())
                                        .getScaledInstance(100,100,16)));
        bottoneAudio.setBorderPainted(false);
        bottoneAudio.setFocusPainted(false);
        bottoneAudio.setContentAreaFilled(false);

        labelAudio.setBounds(bottoneAudio.getX()+20,bottoneAudio.getY()-35,120,30);
        labelAudio.setFont(font.deriveFont(25f));
        sfondo.add(bottoneAudio);
        sfondo.add(labelAudio);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        /////////////////////////////////////////// OAK //////////////////////////////////////////////
        bottoneSpiegazioni.setBounds((labelManoComputerSu.getX()-10)/2-50,
                                     height-labelManoComputerSu.getHeight()/2-30,
                                  100,100);
        bottoneSpiegazioni.setActionCommand(costanti.getActionCommandProfessorOakSpiegazioni());
        bottoneSpiegazioni.setIcon(new ImageIcon(toolkit.createImage(costanti.getPathProfessorOak())
                .getScaledInstance(100,100,16)));
        bottoneSpiegazioni.setBorderPainted(false);
        bottoneSpiegazioni.setFocusPainted(false);
        bottoneSpiegazioni.setContentAreaFilled(false);

        labelSpiegazioni.setBounds(bottoneSpiegazioni.getX()+20, bottoneSpiegazioni.getY()-30,65,25);
        labelSpiegazioni.setFont(font.deriveFont(25f));

        info.setVisible(false);
        info.setBounds(0,0,width,height);

        sfondo.add(info);
        sfondo.add(bottoneSpiegazioni);
        sfondo.add(labelSpiegazioni);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////// ANIMAZIONI ////////////////////////////////////////////////

        animazioneGiocatore.setBounds(labelManoComputerSx.getWidth(),labelManoComputerSu.getHeight(),
                labelManoComputerSu.getWidth(),labelManoGiocatore.getY()-150);

        animazioneGiocatoreGioca.setBounds(pilaScarti.getX(),pilaScarti.getY()+150,100,
                height-labelManoGiocatore.getHeight()-pilaScarti.getY());
        //animazioneGiocatoreGioca.setBounds(labelManoComputerSx.getWidth(),pilaScarti.getY()+150,
        //     labelManoGiocatore.getWidth(),labelManoGiocatore.getY()-pilaScarti.getY()+150);
        animazioneGiocatoreGioca.setxDestinazione(pilaScarti.getX());

        animazioneComputerSx.setBounds(labelManoComputerSx.getWidth()-40,pilaMazzo.getY(),
                pilaMazzo.getX()-labelManoComputerSx.getWidth()+40,100);
        //animazioneComputerSx.setBounds(labelManoComputerSx.getWidth(),labelManoComputerSu.getHeight(),
        //        labelManoComputerSu.getWidth(),labelManoGiocatore.getY());

        animazioneComputerSxGioca.setBounds(labelManoComputerSx.getWidth(),pilaScarti.getY()-10,
                pilaScarti.getX()-labelManoComputerSx.getWidth(),160);
        animazioneComputerSxGioca.setxDestinazione(pilaScarti.getX()-labelManoComputerSx.getWidth());
        //System.out.println(pilaScarti.getY());
        //System.out.println(labelManoComputerSu.getHeight());
        //System.out.println(pilaScarti.getX()-labelManoComputerSx.getWidth());
        //System.out.println(animazioneComputerSxGioca.getWidth());
        System.out.println("distanza mazzo-pila scarti: "+ (pilaMazzo.getX()-pilaScarti.getX()-100));
        animazioneComputerSu.setBounds(pilaMazzo.getX(),labelManoComputerSu.getHeight()-30,
                100,pilaMazzo.getY()+160-labelManoComputerSu.getHeight());

//labelManoGiocatore.getY()
        animazioneComputerSuGioca.setBounds(pilaScarti.getX()-10,labelManoComputerSu.getHeight(),
                110, pilaScarti.getY()-labelManoComputerSu.getHeight());
        animazioneComputerSuGioca.setyDestinazione(pilaScarti.getY()-labelManoComputerSu.getHeight());

        animazioneComputerDx.setBounds(pilaMazzo.getX(),pilaMazzo.getY(),
                width-pilaMazzo.getX()+100-labelManoComputerDx.getWidth(),100);

        System.out.println(animazioneComputerDx.getX());
        System.out.println(animazioneComputerDx.getY());
        System.out.println(animazioneComputerDx.getWidth());
        System.out.println(animazioneComputerDx.getHeight());
        //animazioneComputerDxGioca.setBounds(pilaScarti.getX()+100,pilaScarti.getY(),
          //      getPilaMazzo().getX(),labelManoComputerDx.getHeight());
        animazioneComputerDxGioca.setBounds(pilaScarti.getX()+100,pilaScarti.getY()-10,
                width-labelManoComputerDx.getWidth()-pilaScarti.getX(),160);
        System.out.println(animazioneComputerDxGioca.getX());
        //System.out.println(width-labelManoComputerDx.getWidth()-pilaScarti.getX());
        //animazioneComputerDxGioca.setxDestinazione(pilaScarti.getX()+100);
        //animazioneGiocatore.setX(pilaMazzo.getX());

        //animazioneGiocatore.setVelocitaY(5);


        // sfondo.add(animazioneGiocatore);

        ///////////////////////////////////////////////////////////////////////////////////////////////////

        sfondo.add(audio);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public Audio getAudio() {
        return audio;
    }

    /**
     * metodo che restituisce una lista contenente i bottoni degli avatar,
     * utile quando bisogna utilizzare metodi su tutti
     * @return lista dei bottoni degli avatar
     */
    public List<JButton> getListaAvatars(){
        return new ArrayList<>(List.of(avatarComputerSx,avatarComputerSu,avatarComputerDx));
    }

    /**
     * metodo che restituisce il label che rappresenta
     * la mano del giocatore
     * @return label che rappresenta la mano del giocatore
     */
    public LabelManoGiocatore getLabelManoGiocatore() {
        return labelManoGiocatore;
    }

    /**
     * metodo che restituisce il label che rappresenta
     * la mano del computer a sinistra
     * @return label che rappresenta la mano del giocatore
     */
    public LabelManoComputerSx getLabelManoComputerSx() {
        return labelManoComputerSx;
    }

    /**
     * metodo che restituisce il label che rappresenta
     * la mano del computer in alto
     * @return label che rappresenta la mano del giocatore
     */
    public LabelManoComputerSu getLabelManoComputerSu() {
        return labelManoComputerSu;
    }

    /**
     * metodo che restituisce il label che rappresenta
     * la mano del computer a destra
     * @return label che rappresenta la mano del giocatore
     */
    public LabelManoComputerDx getLabelManoComputerDx() {
        return labelManoComputerDx;
    }

    /**
     * metodo che restituisce la
     * pila degli scarti
     * @return label che rappresenta la pila degli scarti
     */
    public JLabel getPilaScarti() {
        return pilaScarti;
    }

    /**
     * metodo che restituisce
     * il mazzo
     * @return bottone che rappresenta il mazzo
     */
    public JButton getPilaMazzo() {
        return pilaMazzo;
    }

    /**
     * metodo che restituisce
     * il bottone dell'audio
     * @return bottone che indirizza nelle impostazioni audio
     */
    public JButton getBottoneAudio() { return bottoneAudio; }

    /**
     * metodo che permette d'impostare l'immagine
     * rappresentante la direzione di gioco ogni
     * volta che quest'ultima cambia
     * @param b valore booleano che indica la direzione (vero: destra, falso: sinistra)
     */
    public void visualizzaDirezione(boolean b){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        if (b) {
            direzioneGiro.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Utilita/GiroDx.png")));
        }else{
            direzioneGiro.setIcon(new ImageIcon(toolkit.createImage("Risorse/images/Utilita/GiroSx.png")));
        }

    }

    /**
     * metodo che permette d'impostare
     * un bordo a uno dei bottoni degli avatar
     * in questo modo si capisce chi
     * è il giocatore di turno durante
     * una partita
     * @param bottone rappresenta il bottone dell'avatar
     * @param b valore booleano che permette d'inserire o togliere il bordo (vero: inserisce, falso: toglie)
     */
    public void giocatoreAttivo(JButton bottone,boolean b){
        if (b)
            bottone.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        else
            bottone.setBorder(new EmptyBorder(0,0,0,0));
    }

    /**
     * metodo che restituisce il bottone
     * che rappresenta l'avatar del giocatore
     * @return bottone che rappresenta l'avatar del giocatore
     */
    public JButton getAvatarGiocatore() {
        return avatarGiocatore;
    }

    /**
     * metodo che restituisce il bottone
     * che rappresenta l'avatar del computer di sinistra
     * @return bottone che rappresenta l'avatar del computer di sinistra
     */
    public JButton getAvatarComputerSx() {
        return avatarComputerSx;
    }

    /**
     * metodo che restituisce il bottone
     * che rappresenta l'avatar del computer in alto
     * @return bottone che rappresenta l'avatar del computer in alto
     */
    public JButton getAvatarComputerSu() {
        return avatarComputerSu;
    }

    /**
     * metodo che restituisce il bottone
     * che rappresenta l'avatar del computer di destra
     * @return bottone che rappresenta l'avatar del computer di destra
     */
    public JButton getAvatarComputerDx() {
        return avatarComputerDx;
    }

    /**
     * metodo che restituisce il
     * label che rappresenta il nome del giocatore
     * @return label che rappresenta del giocatore
     */
    public JLabel getNomeGiocatore() {
        return nomeGiocatore;
    }

    /**
     * metodo che restituisce il
     * label che rappresenta il nome del computer di sinistra
     * @return label che rappresenta del computer di sinistra
     */
    public JLabel getNomeComputerSx() {
        return nomeComputerSx;
    }

    /**
     * metodo che restituisce il
     * label che rappresenta il nome del computer in alto
     * @return label che rappresenta del computer in alto
     */
    public JLabel getNomeComputerSu() {
        return nomeComputerSu;
    }

    /**
     * metodo che restituisce il
     * label che rappresenta il nome del computer di destra
     * @return label che rappresenta del computer di destra
     */
    public JLabel getNomeComputerDx() {
        return nomeComputerDx;
    }

    /**
     * metodo che restituisce
     * il label che rappresenta lo sfondo
     * @return il label che rappresenta lo sfondo
     */
    public JLabel getSfondo() {
        return sfondo;
    }

    /**
     * metodo che restituisce
     * il label che rappresenta la scelta
     * del colore dopo aver giocato le
     * carte Cambiocolore o Pescaquattro
     * @returnil label che rappresenta la scelta del colore
     */
    public LabelSceltaColore getLabelSceltaColore() {
        return labelSceltaColore;
    }

    /**
     * metodo che restituisce
     * il label che rappresenta la scelta
     * del colore dopo aver giocato le
     * carte Scambiamani o Duello
     * @returnil label che rappresenta la scelta del giocatore
     */
    public LabelSelezionaGiocatore getSelezionaGiocatore() {
        return selezionaGiocatore;
    }

    /**
     * metodo che restituisce
     * il bottone Juno da premere se si rimane
     * con una sola carta in mano
     * @return il bottone Juno
     */
    public JButton getTastoJuno() {
        return tastoJuno;
    }

    /**
     * metodo che restituisce
     * il bottone che permette di tornare al menù
     * @return il bottone che permette di tornare al menù
     */
    public JButton getBottoneMenu() {
        return bottoneMenu;
    }

    /**
     * metodo che restituisce
     * il bottone per passare il turno qualora si peschi
     * una carta ma non la si vuole giocare
     * @return il label che rappresenta il bottone per passare il turno
     */
    public JButton getPassaTurno() {
        return passaTurno;
    }

    /**
     * metodo che restituisce
     * il bottone che permette di visualizzare
     * le informazioni sulle carte
     * @return il bottone che permette di visualizzare le informazioni sulle carte
     */
    public JButton getBottoneSpiegazioni() {
        return bottoneSpiegazioni;
    }

    public Informazioni getInfo() {
        return info;
    }

    /**
     * metodo che restiuisce l'animazione del giocatore
     * @return l'animazione del giocatore
     */
    public Animazione getAnimazioneGiocatore() {
        return animazioneGiocatore;
    }

    /**
     * metodo che restiuisce l'animazione del giocatore
     * @return l'animazione del giocatore
     */
    public Animazione getAnimazioneGiocatoreGioca() {
        return animazioneGiocatoreGioca;
    }

    /**
     * metodo che restiuisce l'animazione del computer di sinistra
     * @return l'animazione del computer di sinistra
     */
    public Animazione getAnimazioneComputerSx() {
        return animazioneComputerSx;
    }

    public Animazione getAnimazioneComputerSxGioca() {
        return animazioneComputerSxGioca;
    }

    /**
     * metodo che restiuisce l'animazione del computer in alto
     * @return l'animazione del computer in alto
     */
    public Animazione getAnimazioneComputerSu() {
        return animazioneComputerSu;
    }

    public Animazione getAnimazioneComputerSuGioca() {
        return animazioneComputerSuGioca;
    }

    /**
     * metodo che restiuisce l'animazione del computer di destra
     * @return l'animazione del computer di destra
     */
    public Animazione getAnimazioneComputerDx() {
        return animazioneComputerDx;
    }

    public Animazione getAnimazioneComputerDxGioca() {
        return animazioneComputerDxGioca;
    }

}
