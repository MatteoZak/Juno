package ProgettoFinale.Controller;

import ProgettoFinale.Controller.Pacchetti.AvvisoPescata;
import ProgettoFinale.FinestraGioco;
import ProgettoFinale.Model.Carte.*;
import ProgettoFinale.Model.Giocatori.Giocatori;
import ProgettoFinale.Model.Tavolo.Avversari;
import ProgettoFinale.Model.Tavolo.ModalitaGioco;
import ProgettoFinale.Model.Tavolo.Tavolo;
import ProgettoFinale.Model.TurnManager;
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;
import ProgettoFinale.View.Decorator.DecoratoreCarta;
import ProgettoFinale.View.Decorator.DecoratoreProfilo;
import ProgettoFinale.View.Musica.Ambiente;
import ProgettoFinale.View.Musica.Effetti;
import ProgettoFinale.View.Musica.Versi;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Il Controller è la classe che gestisce la comunicazione tra Model e View
 * gli diamo i campi necessari per poter gestire la comunicazione
 */
public class Controller implements ActionListener, ChangeListener {
    private static final Random sourceRandom = new Random();
    public static Controller instance;
    private FinestraGioco f;
    private Tavolo t;
    private TurnManager tm;
    private Ambiente ambiente = new Ambiente();
    private Effetti effetti = new Effetti();
    private Versi versi = new Versi();
    private ModalitaGioco modalita;

    /**
     *
     * @param f è la nostra finestra di gioco (View)
     * @param t è il nostro tavolo da gioco (Model)
     *          Nel costruttore istanziamo i parametri e il TurnManager (tm)
     *          e riproduciamo la musica di sottofondo per poi aggiungere i Listener
     */
    private Controller(FinestraGioco f, Tavolo t) {
        this.f = f;
        this.t = t;
        tm = new TurnManager(t.getGiocatore(),t.getComputerSx(),t.getComputerSu(),t.getComputerDx());
        t.addObserver(f);
        riproduciMusicaAmbiente(5);
        riproduciEffettoSpeciale(0);
        riproduciVerso(0);

        addChangeListener();
        addActionListener();
    }

    /**
     * Mettiamo gli ActionListener alle componenti necessarie
     * sono gli ActionListener dei vari bottoni che sono presenti:
     * -nella finestra di gioco (f);
     * -nel Game View (gw);
     * -nel Profilo View (pw);
     * -nelle impostazioni Audio;
     * -nella scelta delle Modalità;
     */
    private void addActionListener() {
        f.getGiocaButton().addActionListener(this);
        f.getProfiloButton().addActionListener(this);
        f.getChiudiButton().addActionListener(this);
        f.getInfo().getBottoneCapito().addActionListener(this);

        f.getGw().getListaAvatars().forEach(x->x.addActionListener(this));
        f.getGw().getPilaMazzo().addActionListener(this);
        f.getGw().getBottoneAudio().addActionListener(this);
        f.getGw().getLabelSceltaColore().getSceltaRosso().addActionListener(this);
        f.getGw().getLabelSceltaColore().getSceltaBlu().addActionListener(this);
        f.getGw().getLabelSceltaColore().getSceltaVerde().addActionListener(this);
        f.getGw().getLabelSceltaColore().getSceltaGiallo().addActionListener(this);
        f.getGw().getPassaTurno().addActionListener(this);
        f.getGw().getTastoJuno().addActionListener(this);
        f.getGw().getBottoneMenu().addActionListener(this);
        f.getGw().getBottoneSpiegazioni().addActionListener(this);

        f.getPw().getApplica().addActionListener(this);
        f.getPw().getScelta().addActionListener(this);
        f.getPw().getUscita().addActionListener(this);
        f.getPw().getAzzeraDati().addActionListener(this);

        f.getAudioButton().addActionListener(this);
        f.getAudio().getUscita().addActionListener(this);
        f.getAudio().getRiprendiPartita().addActionListener(this);

        f.getModalita().getTastoIndietro().addActionListener(this);

        f.getModalita().getModStandard().addActionListener(this);
        f.getModalita().getModCarteExtra().addActionListener(this);
        f.getModalita().getModInvertita().addActionListener(this);

        f.getCarteExtra().getCheckBoxes().forEach(x->x.addActionListener(this));
        f.getCarteExtra().getBottoneGioca().addActionListener(this);
        f.getCarteExtra().getTornaSceltaModalita().addActionListener(this);
    }

    /**
     * Mettiamo i ChangeListener delle barre per:
     * -il volume di sottofondo;
     * -il volume per gli effetti speciali;
     * -il volume per il verso degli avversari;
     */
    private void addChangeListener(){
        f.getAudio().getBarraVolumeAmbiente().addChangeListener(this);
        f.getAudio().getBarraVolumeEffetti().addChangeListener(this);
        f.getAudio().getBarraVolumeVersi().addChangeListener(this);
    }

    /**
     * Metodo che controlla se ci sono stati cambiamenti dello scorrimento delle barre
     * dei volumi di ambiente, effetti speciali e versi
     * Prendiamo il valore nuovo e lo impostiamo come nuovo volume con il metodo regola()
     * @param e  un oggetto ChangeEvent che modifica i volumi di ambiente, effetti e versi
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource().equals(f.getAudio().getBarraVolumeAmbiente())){
            ambiente.setVolume(f.getAudio().getBarraVolumeAmbiente().getValue());
            f.getAudio().getVolumeAmbiente().setText(String.valueOf((int)((f.getAudio().getBarraVolumeAmbiente().getValue()+80F)/0.85F)));
            ambiente.regola();
        }else if (e.getSource().equals(f.getAudio().getBarraVolumeEffetti())){
            effetti.setVolume(f.getAudio().getBarraVolumeEffetti().getValue());
            f.getAudio().getVolumeEffetti().setText(String.valueOf((int)((f.getAudio().getBarraVolumeEffetti().getValue()+80F)/0.85F)));
            effetti.regola();

        } else if (e.getSource().equals(f.getAudio().getBarraVolumeVersi())) {
            versi.setVolume(f.getAudio().getBarraVolumeVersi().getValue());
            f.getAudio().getVolumeVersi().setText(String.valueOf((int) ((f.getAudio().getBarraVolumeVersi().getValue() + 80F) / 0.85F)));
            versi.regola();
        }
    }

    /**
     * Metodo che gestisce tutti i vari bottoni presenti, tramite un switch vediamo
     * quale bottone è stato premuto e agisce di conseguenza
     * (rende visibile o meno alcuni Label, fa apparire Frame con messaggi,
     * gestisce alcuni effetti delle carte che richiedono di premere nu bottone,
     * bottoni del Profilo del giocatore, bottoni presenti nella Game View che fanno
     * pescare o passare il turno e altri bottoni che gestiscono l'uscita da schermate o altro, ecc)
     *
     * @param e evento che viene processato e identifica che comando eseguire
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "APPLICA":
                JFrame applicaImmagine = new JFrame();
                int rispostaApplicaImmagine = JOptionPane.showConfirmDialog(applicaImmagine, "Sei sicuro di voler cambiare?");
                if (rispostaApplicaImmagine == 0) {
                    t.getGiocatore().setUsername(f.getPw().getUsername());
                    t.getGiocatore().setIcona(f.getPw().getFileChooser().getSelectedFile().getAbsolutePath());
                }else{
                    JOptionPane.showMessageDialog(applicaImmagine, "Nulla è stato cambiato!");
                }
                break;
            case "AIUTO":
                f.getGw().setVisible(false);
                break;
            case "AUDIO":
                stopMusicaAmbiente();
                riproduciMusicaAmbiente(3);
                leggiImpostazioniAudio();
                f.getMenu().setVisible(false);
                f.getAudio().setVisible(true);
                break;
            case "AVATAR":
                Giocatori vittima = null;
                if(tm.getGiocatoreDiTurno().equals(t.getGiocatore())){
                    switch(t.getScarti().peek().getValoreIntero()){
                        case "18":
                            //TODO:metodo per stabilire la vittima
                            if ((((JButton) e.getSource()).getName()).contains("ComputerSx")) {
                                vittima = t.getComputerSx();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerSu")) {
                                vittima = t.getComputerSu();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerDx")) {
                                vittima = t.getComputerDx();
                            }
                            List<Carta> listaDiAppoggio = new LinkedList<>(tm.getGiocatoreDiTurno().getMano());
                            System.out.println("Lista di appoggio:"+listaDiAppoggio);
                            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
                            tm.getGiocatoreDiTurno().getMano().removeAll(tm.getGiocatoreDiTurno().getMano());
                            tm.getGiocatoreDiTurno().getMano().addAll(vittima.getMano());
                            System.out.println("Mano utilizzatore: "+tm.getGiocatoreDiTurno().getNome()+tm.getGiocatoreDiTurno().getMano().toString());
                            vittima.getMano().removeAll(vittima.getMano());
                            vittima.getMano().addAll(listaDiAppoggio);
                            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
                            aggiornaMano(tm.getGiocatoreDiTurno());
                            aggiornaMano(vittima);
                            f.getGw().getSelezionaGiocatore().setVisible(false);
                            tm.passaTurno();
                            segnaGiocatoreAttivo();
                            faiGiocare();
                            break;
                        case "20":
                            if ((((JButton) e.getSource()).getName()).contains("ComputerSx")) {
                                vittima = t.getComputerSx();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerSu")) {
                                vittima = t.getComputerSu();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerDx")) {
                                vittima = t.getComputerDx();
                            }
                            String maxVittima = String.valueOf(vittima.getMano().stream()
                                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());


                            String maxGiocatore = String.valueOf(tm.getGiocatoreDiTurno().getMano().stream()
                                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());
                            System.out.println("vittima: "+vittima.getNome());
                            System.out.println("carta vittima: "+maxVittima);
                            System.out.println("utilizzatore: "+t.getGiocatore().getNome());
                            System.out.println("carta utilizzatore: "+maxGiocatore);
                            if(maxGiocatore.compareTo(maxVittima)>=0) {
                                System.out.println("entrato nel primo if"+maxGiocatore.compareTo(maxVittima));
                                fineMazzo();
                                vittima.getMano().add(t.getMazzo().pesca());
                                fineMazzo();
                                vittima.getMano().add(t.getMazzo().pesca());
                                aggiornaMano(vittima);
                            }else{
                                System.out.println("entrato nel secondo if"+maxGiocatore.compareTo(maxVittima));
                                fineMazzo();
                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
                                fineMazzo();
                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
                                aggiornaMano(t.getGiocatore());
                            }
                            f.getGw().getSelezionaGiocatore().setVisible(false);
                            tm.passaTurno();
                            segnaGiocatoreAttivo();
                            faiGiocare();
                            break;
                    }
                }
                break;
            case "AZZERADATI":
                JFrame azzeraDati = new JFrame();
                int rispostaAzzaraDati = JOptionPane.showConfirmDialog(azzeraDati, "Sei sicuro di voler cancellare tutto e ricominciare da capo?");
                if (rispostaAzzaraDati == 0) {
                    t.getGiocatore().setVittorie("0");
                    t.getGiocatore().setSconfitte("0");
                    t.getGiocatore().setLivello("0");
                    aggiornaProfilo();
                }else {
                    JOptionPane.showMessageDialog(azzeraDati, "Continua così!");
                }
                break;
            case "CAPITO":
                f.getGw().setVisible(true);
                f.getInfo().setVisible(false);
                break;
            case "CHIUDI":
                System.exit(0);
                break;
            case "CONTROLLO":
                JCheckBox check = (JCheckBox) e.getSource();

                f.getCarteExtra().getLabelCarte().stream()
                        .filter(x->x.getName().equals(check.getName()))
                        .toList().get(0).setEnabled(check.isSelected());
                t.getMazzo().setCarteSpeciali(Valori.valueOf(check.getName().toUpperCase()),check.isSelected());
                if(f.getCarteExtra().getCheckBoxes().stream().filter(AbstractButton::isSelected).count() == 2){
                    f.getCarteExtra().getBottoneGioca().setEnabled(true);
                    f.getCarteExtra().getCheckBoxes().stream().filter(x -> !x.isSelected()).forEach(x->x.setEnabled(false));
                } else {
                    f.getCarteExtra().getBottoneGioca().setEnabled(false);
                    f.getCarteExtra().getCheckBoxes().stream().filter(x -> !x.isSelected()).forEach(x->x.setEnabled(true));
                }
                break;
            case "EXTRA":
                modalita = ModalitaGioco.EXTRA;
                f.getCarteExtra().setVisible(false);
                iniziaPartita();
                f.getGw().setVisible(true);
                break;
            case "GIOCA":
                f.getCarteExtra().setVisible(false);
                f.getModalita().setVisible(true);
                f.getPrincipale().setVisible(false);
                break;
            case "INDIETRO": //Casa Blu
                if(tm.getGiocatoreDiTurno().equals(t.getGiocatore())) {
                    stopMusicaAmbiente();
                    leggiImpostazioniAudio();
                    f.getGw().setVisible(false);
                    f.getPrincipale().setVisible(true);
                    f.getMenu().setVisible(false);
                    f.getAudio().setVisible(true);
                    f.getAudio().getUscita().setActionCommand("PRINCIPALEPARTITA");
                    f.getAudio().getRiprendiPartita().setVisible(true);
                    riproduciMusicaAmbiente(3);
                }
                break;
            case "INVERTITA":
                modalita = ModalitaGioco.INVERTITA;
                iniziaPartita();
                f.getModalita().setVisible(false);
                f.getGw().setVisible(true);
                break;
            case "JUNO":
                riproduciEffettoSpeciale(5);
                f.getGw().getTastoJuno().setVisible(false);
                break;
            case "PASSA":
                Arrays.stream(f.getGw().getLabelManoGiocatore().getComponents()).forEach(x -> x.setEnabled(true));
                f.getGw().getPilaMazzo().setEnabled(true);
                f.getGw().getPassaTurno().setVisible(false);
                tm.passaTurno();
                segnaGiocatoreAttivo();
                faiGiocare();
                break;
            case "PESCACARTA":
                if(tm.getGiocatoreDiTurno().equals(t.getGiocatore())) {
                    animazioneGiocatori(tm.getGiocatoreDiTurno());
                    Carta cartaPescata = t.getMazzo().pesca();
                    t.getGiocatore().getMano().add(cartaPescata);
                    t.notificaCambiamenti(new AvvisoPescata(cartaPescata,tm.getGiocatoreDiTurno(),this));
                    if(!giocabile(cartaPescata)) {
                        f.getGw().getPilaMazzo().setEnabled(true);
                        tm.passaTurno();
                        segnaGiocatoreAttivo();
                        faiGiocare();
                    } else {
                        f.getGw().getPassaTurno().setVisible(true);
                        Arrays.stream(f.getGw().getLabelManoGiocatore().getComponents()).forEach(x -> x.setEnabled(false));
                        f.getGw().getLabelManoGiocatore().getComponent(t.getGiocatore().getMano().size()-1).setEnabled(true);
                    }
                }
                break;
            case "PRINCIPALE":
                stopMusicaAmbiente();
                riproduciMusicaAmbiente(5);
                f.getModalita().setVisible(false);
                f.getPw().setVisible(false);
                f.getAudio().setVisible(false);
                f.getPrincipale().setVisible(true);
                f.getMenu().setVisible(true);
                break;
            case "PRINCIPALEPARTITA":
                JFrame uscita = new JFrame();
                int risposta = JOptionPane.showConfirmDialog(uscita, "Vuoi davvero uscire e perdere la partita?");
                if (risposta == 0) {
                    t.getGiocatore().sconfitta();
                    riproduciEffettoSpeciale(7);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            riproduciMusicaAmbiente(5);
                        }
                    }, 1000);
                    resetPartita();
                    f.getGw().getLabelSceltaColore().setVisible(false);
                    f.getGw().setVisible(false);
                    f.getMenu().setVisible(true);
                    f.getPrincipale().setVisible(true);
                    f.getAudio().setVisible(false);
                    f.getAudio().getUscita().setActionCommand("PRINCIPALE");
                }
                else {
                    riproduciMusicaAmbiente(4);
                    f.getAudio().setVisible(false);
                    f.getPrincipale().setVisible(false);
                    f.getGw().setVisible(true);
                }
                f.getAudio().getRiprendiPartita().setVisible(false);
                break;
            case "PROFILO":
                stopMusicaAmbiente();
                riproduciMusicaAmbiente(2);
                f.getPw().getLabelIcona().setIcon(new ImageIcon(t.getGiocatore().getIcona()
                        .getScaledInstance(100, 100, 16)));
                f.getPw().setUsername(t.getGiocatore().getUsername());
                aggiornaProfilo();
                f.getMenu().setVisible(false);
                f.getPw().setVisible(true);
                break;
            case "RIPRENDIPARTITA":
                stopMusicaAmbiente();
                f.getAudio().getRiprendiPartita().setVisible(false);
                riproduciMusicaAmbiente(4);
                f.getAudio().setVisible(false);
                f.getPrincipale().setVisible(false);
                f.getGw().setVisible(true);
                faiGiocare();
                break;
            case "SCEGLI":
                if(f.getPw().getFileChooser().showOpenDialog(f.getPw()) == JFileChooser.APPROVE_OPTION)
                    f.getPw().getLabelIcona().setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(f.getPw().getFileChooser().getSelectedFile().getAbsolutePath())
                            .getScaledInstance(f.getPw().getLabelIcona().getWidth(), f.getPw().getLabelIcona().getHeight(), 16)));
                break;
            case "SCELTACARTE":
                f.getModalita().setVisible(false);
                f.getCarteExtra().setVisible(true);
                break;
            case "SCELTACOLORE":
                //TODO:nome
                Carta c = t.getScarti().peek();
                if(e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaRosso())){
                    System.out.println("premuto rosso");
                    c.setColore(Colori.ROSSO);
                } else if (e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaBlu())) {
                    System.out.println("premuto blu");
                    c.setColore(Colori.BLU);
                } else if(e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaVerde())){
                    System.out.println("premuto verde");
                    c.setColore(Colori.VERDE);
                }
                else{
                    System.out.println("premuto giallo");
                    c.setColore(Colori.GIALLO);
                }
                f.getGw().getLabelSceltaColore().setVisible(false);
                c.setImmagine(new DecoratoreCarta(c).visualizzaCarta());
                f.getGw().getPilaScarti().setIcon(new ImageIcon(c.getImmagine()));
                if(t.getScarti().size()==1)
                    tm.setTurno(tm.getTurno()-1);
                tm.passaTurno();
                segnaGiocatoreAttivo();
                faiGiocare();
                break;
            case "SPIEGAZIONI":
                if(tm.getGiocatoreDiTurno().equals(t.getGiocatore())) {
                    f.getGw().setVisible(false);
                    if (modalita != ModalitaGioco.EXTRA)
                        f.getInfo().nascondiCarteViola();
                    f.getInfo().setVisible(true);
                }
                break;
            case "STANDARD":
                modalita = ModalitaGioco.STANDARD;
                iniziaPartita();
                f.getModalita().setVisible(false);
                f.getGw().setVisible(true);
                break;
        }
    }

    /**
     * Metodo che gestisce quando l'utente clicca su una carta nella propria mano
     * controlla se è il suo turno, se la carta può essere giocata secondo le regole di gioco,
     * applica l'animazione, e applica l'eventuale effetto della carta.
     * Se era l'ultima carta finisce la partita
     * Se non è giocabile la aggiunge alla mano, in entrambi i casi poi passa il turno
     * @param bc è la carta nella mano che premendola viene giocata
     */
    public void cartaCliccata(BottoneCarta bc){
        if(tm.getGiocatoreDiTurno().equals(t.getGiocatore()) && bc.isEnabled()) {
            if (giocabile(bc.getCarta())) {
                f.getGw().getPassaTurno().setVisible(false);

                f.getGw().getAnimazioneGiocatoreGioca().setImage(bc.getCarta().getImmagine());
                f.getGw().getAnimazioneGiocatoreGioca().setX(bc.getX());
                System.out.println(bc.getX());
                f.getGw().getAnimazioneGiocatoreGioca().setY(f.getGw().getLabelManoGiocatore().getY()-350);
                f.getGw().getAnimazioneGiocatoreGioca().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneGiocatoreGioca());

                System.out.println("hai giocato: "+bc.getCarta().getColore()+" "+bc.getCarta().getValoreIntero()+" "+ new Date());
                t.getGiocatore().getMano().remove(bc.getCarta());
                t.getScarti().push(bc.getCarta());
                f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
                f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore(),this);
                f.getGw().getPilaMazzo().setEnabled(true);
                applicaEffetto(bc.getCarta().getValoreIntero());
                if(t.getGiocatore().getMano().isEmpty()){
                    t.getGiocatore().vittoria();
                    t.getGiocatore().livellamento(100);
                    riproduciEffettoSpeciale(6);
                    partitaFinita();
                }
                if(t.getGiocatore().getMano().size() == 1){
                    f.getGw().getTastoJuno().setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if(f.getGw().getTastoJuno().isVisible()) {
                                fineMazzo();
                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
                                fineMazzo();
                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
                                f.getGw().getTastoJuno().setVisible(false);
                                aggiornaMano(t.getGiocatore());
                            }
                        }
                    }, 3000);
                }
                if (f.getGw().getLabelSceltaColore().isVisible() || f.getGw().getSelezionaGiocatore().isVisible()){
                    return;
                }
                tm.passaTurno();
                segnaGiocatoreAttivo();
                faiGiocare();
            }
        }
    }

/////////////////////////////   Effetti Speciali Carte  ////////////////////////////////

    /**
     *
     * @param valore rappresenta l'effetto della carta che viene giocata
     *               e applicato tramite uno switch
     */
    private void applicaEffetto(String valore) {
        //TODO:fare solo una volta riproduci effetto speciale
        switch (valore) {
            case "10" -> {
                riproduciEffettoSpeciale(2);
                invertiGiro();
            }
            case "11" -> {
                riproduciEffettoSpeciale(2);
                blocco();
            }
            case "12" -> {
                riproduciEffettoSpeciale(2);
                pescaDue();
            }
            case "13" -> {
                riproduciEffettoSpeciale(2);
                cambioColore();
            }
            case "14" -> {
                riproduciEffettoSpeciale(2);
                pescaQuattro();
            }
            case "15" -> {
                riproduciEffettoSpeciale(2);
                for(int i = 0; i < 3 ; i++){
                    blocco();
                }
            }
            case "16" -> {
                riproduciEffettoSpeciale(2);
                for(int i = 0; i < 3 ; i++){
                    pescaDue();
                }
            }
            case "17" -> {
                riproduciEffettoSpeciale(2);
                for(int i = 0; i < 3 ; i++){
                    fineMazzo();
                    tm.getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
                    pescaDue();
                }
            }
            case "18" -> {
                riproduciEffettoSpeciale(2);
                scambioMani();
            }
            case "19" -> {
                riproduciEffettoSpeciale(2);
                invertiGiro();
                blocco();
            }
            case "20" -> {
                riproduciEffettoSpeciale(2);
                duello();
            }
            default -> riproduciEffettoSpeciale(1);

        }
    }
/////////////////////////////   Effetti Speciali Carte Base  ////////////////////////////////
    /**
     * Metodo per la carta che inverte il giro, riproducendo il suono
     */
    private void invertiGiro(){
        riproduciEffettoSpeciale(3);
        tm.cambiaDirezione();
        f.getGw().visualizzaDirezione(tm.getDirezione());
    }

    /**
     * Metodo della carta che blocca il giocatore successivo
     */
    private void blocco(){
        riproduciEffettoSpeciale(3);
        tm.passaTurno();
    }

    /**
     * Metodo della carta che fa pescare due carte al giocatore successivo e lo blocca
     */
    private void pescaDue(){
        riproduciEffettoSpeciale(3);
        fineMazzo();
        animazioneGiocatori(tm.getGiocatoreSuccessivo());
        tm.getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
        fineMazzo();
        riproduciEffettoSpeciale(3);
        animazioneGiocatori(tm.getGiocatoreSuccessivo());
        tm.getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
        aggiornaMano(tm.getGiocatoreSuccessivo());
        tm.passaTurno();
    }

    /**
     * Metodo della carta che cambia il colore della pila degli scarti
     */
    private void cambioColore(){
        //TODO:cambiare
        Carta c = t.getScarti().peek();
        //TODO:finire bene il cambio colore per i bot
        if (!tm.getGiocatoreDiTurno().equals(t.getGiocatore())){
            c.setColore(Colori.values()[sourceRandom.nextInt(4)]);
            System.out.println("Ha scelto colore "+Colori.values()[sourceRandom.nextInt(4)].toString());
            c.setImmagine(new DecoratoreCarta(c).visualizzaCarta());
            f.getGw().getPilaScarti().setIcon(new ImageIcon(c.getImmagine()));
        }else {
            f.getGw().getLabelSceltaColore().setVisible(true);
        }
    }

    /**
     * Metodo della carta che cambia colore della pila degli scarti e fa pescare 4 carte al giocatore
     * successivo e lo blocca
     */
    private void pescaQuattro() {
        for(int i = 0; i < 4; i++){
            fineMazzo();
            riproduciEffettoSpeciale(3);
            animazioneGiocatori(tm.getGiocatoreSuccessivo());
            tm.getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
        }
        aggiornaMano(tm.getGiocatoreSuccessivo());
        cambioColore();
        tm.passaTurno();
    }
    /////////////////////////////   Effetti Speciali Carte Personalizzate ////////////////////////////////

    /**
     * Metodo della carta che permette di scambiare tutte le carte in mano con un giocatore a scelta
     */
    private void scambioMani(){
        if (!tm.getGiocatoreDiTurno().equals(t.getGiocatore())){
            Giocatori vittima = tm.getGiocatori().stream().filter(x->!x.getNome().equals(tm.getGiocatoreDiTurno().getNome()))
                    .toList().get(sourceRandom.nextInt(3));
            List<Carta> listaDiAppoggio = new LinkedList<>(tm.getGiocatoreDiTurno().getMano());
            System.out.println("Lista di appoggio:"+listaDiAppoggio);
            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
            tm.getGiocatoreDiTurno().getMano().clear();
            tm.getGiocatoreDiTurno().getMano().addAll(vittima.getMano());
            System.out.println("Mano utilizzatore: "+tm.getGiocatoreDiTurno().getNome()+tm.getGiocatoreDiTurno().getMano().toString());
            vittima.getMano().clear();
            vittima.getMano().addAll(listaDiAppoggio);
            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
            aggiornaMano(tm.getGiocatoreDiTurno());
            aggiornaMano(vittima);
        }else
            f.getGw().getSelezionaGiocatore().setVisible(true);
    }

    /**
     * Metodo della carta che chi la gioca sceglie un altro giocatore e chi ha la carta più alta nella propria mano
     * fa pescare due carte all'altro
     */
    private void duello(){
        if (!tm.getGiocatoreDiTurno().equals(t.getGiocatore())){
            Giocatori vittima = tm.getGiocatori().stream().filter(x->!x.getNome().equals(tm.getGiocatoreDiTurno().getNome()))
                    .toList().get(sourceRandom.nextInt(3));
            String maxUtilizzatore = String.valueOf(tm.getGiocatoreDiTurno().getMano().stream()
                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());

            String maxVittima = String.valueOf(vittima.getMano().stream()
                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());
            System.out.println("carta vittima: "+maxVittima);
            System.out.println("carta utilizzatore: "+maxUtilizzatore);
            if(maxUtilizzatore.compareTo(maxVittima)>=0) {
                fineMazzo();
                vittima.getMano().add(t.getMazzo().pesca());
                fineMazzo();
                vittima.getMano().add(t.getMazzo().pesca());
                aggiornaMano(vittima);
            }else{
                fineMazzo();
                tm.getGiocatoreDiTurno().getMano().add(t.getMazzo().pesca());
                fineMazzo();
                tm.getGiocatoreDiTurno().getMano().add(t.getMazzo().pesca());
                aggiornaMano(tm.getGiocatoreDiTurno());
            }
        }else
            f.getGw().getSelezionaGiocatore().setVisible(true);
    }
////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metodo che controlla se il mazzo è finito e in caso affermativo lo ricrea
     */
    private void fineMazzo(){
        if (t.getMazzo().getPilaMazzo().isEmpty()) {
            riproduciEffettoSpeciale(4);
            System.out.println("Mazziere: Sto mischiando il mazzo!!");
            riformaMazzo();
        }
    }

    /**
     * Metodo che crea il mazzo prendendo le carte dalla pila degli scarti eccetto l'ultima carta giocata
     */
    private void riformaMazzo(){
        Carta ultimaCarta = t.getScarti().pop();
        t.getMazzo().mazzoFinito(t.getScarti());
        t.getScarti().clear();
        t.getScarti().push(ultimaCarta);
        f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
    }

    /**
     * Metodo per far eseguire le mosse agli avversari
     * @param computer è l'avversario corrente che giocherà il suo turno
     */
    public void turnoComputer(Giocatori computer){
        if (!f.getGw().isVisible()) {
            System.out.println(computer.getNome()+": Me fermo!");
            return;
        }
        List<Carta> giocabili = computer.getMano().stream().filter(this::giocabile).toList();
        if (giocabili.isEmpty()){
            fineMazzo();
            animazioneGiocatori(computer);
            Carta cartaPescataBot = t.getMazzo().pesca();
            riproduciEffettoSpeciale(0);
            if(giocabile(cartaPescataBot)){
                System.out.println("pescata e giocata: "+cartaPescataBot.getValoreIntero()+" "+cartaPescataBot.getColore()+" da "+computer.getNome()+" "+ new Date()+"    "+computer.getMano().size());
                t.getScarti().push(cartaPescataBot);
                f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
                applicaEffetto(cartaPescataBot.getValoreIntero());
            }else {
                computer.getMano().add(cartaPescataBot);
                aggiornaMano(computer);
                System.out.println("pescata: "+cartaPescataBot+" da "+computer.getNome()+" "+new Date()+"    "+computer.getMano().size());
            }
        } else {
            Carta cartaDaGiocare = giocabili.get(sourceRandom.nextInt(giocabili.size()));
            computer.getMano().remove(cartaDaGiocare);
            aggiornaMano(computer);
            if (computer.getMano().isEmpty()){
                System.out.println("Ha vinto "+computer.getNome());
                t.getGiocatore().sconfitta();
                t.getGiocatore().livellamento(50);
                riproduciEffettoSpeciale(8);
                partitaFinita();
            }

            animazioneGiocatoriGiocaCarta(computer,cartaDaGiocare);

            System.out.println("carta giocata da "+computer.getNome()+": "+ cartaDaGiocare +" "+new Date()+"    "+(computer.getMano().size()));
            t.getScarti().push(cartaDaGiocare);
            f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
            applicaEffetto(cartaDaGiocare.getValoreIntero());
        }
        tm.passaTurno();
        segnaGiocatoreAttivo();
        faiGiocare();
    }

    /**
     * Metodo che permette ai giocatori di eseguire il proprio turno
     */
    private void faiGiocare(){
        if(!tm.getGiocatoreDiTurno().equals(t.getGiocatore())) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    turnoComputer(tm.getGiocatoreDiTurno());
                }
            }, 3000);
        }
    }

    /**
     * Metodo che rende visibile la pescata degli avversari
     * @param giocatore è il giocatore corrente del turno
     */
    private void animazioneGiocatori(Giocatori giocatore){
        switch (giocatore.getNome()) {
            case "computerSx" -> {
                f.getGw().getAnimazioneComputerSx().setX(f.getGw().getPilaMazzo().getX() - 200);
                f.getGw().getAnimazioneComputerSx().setY(f.getGw().getPilaMazzo().getY() - 200);
                f.getGw().getAnimazioneComputerSx().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSx());
            }
            case "computerSu" -> {
                f.getGw().getAnimazioneComputerSu().setX(f.getGw().getPilaMazzo().getX() - 200);
                f.getGw().getAnimazioneComputerSu().setY(f.getGw().getPilaMazzo().getY() - 200);
                f.getGw().getAnimazioneComputerSu().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSu());
            }
            case "computerDx" -> {
                f.getGw().getAnimazioneComputerDx().setX(f.getGw().getPilaMazzo().getX() - 200);
                f.getGw().getAnimazioneComputerDx().setY(f.getGw().getPilaMazzo().getY() - 200);
                f.getGw().getAnimazioneComputerDx().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerDx());
            }
            default -> {
                f.getGw().getAnimazioneGiocatore().setX(f.getGw().getPilaMazzo().getX() - 200);
                f.getGw().getAnimazioneGiocatore().setY(f.getGw().getPilaMazzo().getY() - 200);
                f.getGw().getAnimazioneGiocatore().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneGiocatore());
            }
        }
    }
    private void animazioneGiocatoriGiocaCarta(Giocatori giocatore, Carta carta){
        switch (giocatore.getNome()){
            case "computerSx"-> {
                f.getGw().getAnimazioneComputerSxGioca().setImage(carta.getImmagine());
                f.getGw().getAnimazioneComputerSxGioca().setX(10);
                f.getGw().getAnimazioneComputerSxGioca().setY(10);
                f.getGw().getAnimazioneComputerSxGioca().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSxGioca());
            }
            case "computerSu" -> {
                f.getGw().getAnimazioneComputerSuGioca().setImage(carta.getImmagine());
                f.getGw().getAnimazioneComputerSuGioca().setX(5);
                f.getGw().getAnimazioneComputerSuGioca().setY(5);
                f.getGw().getAnimazioneComputerSuGioca().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSuGioca());
            }
            default -> {
                f.getGw().getAnimazioneComputerDxGioca().setImage(carta.getImmagine());
                                                            //SE NON FUNZIONA METTIAMO f.getGw().getAnimazioneComputerDxGioca().getWidth() ma con piu velocita su x
                f.getGw().getAnimazioneComputerDxGioca().setX(f.getGw().getPilaScarti().getX());
                f.getGw().getAnimazioneComputerDxGioca().setY(10);
                f.getGw().getAnimazioneComputerDxGioca().timer();
                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerDxGioca());
            }
        }
    }

    /**
     * Metodo che permette di visualizzare chi è il giocatore di turno
     */
    private void segnaGiocatoreAttivo(){
        switch (tm.getGiocatoreDiTurno().getNome()) {
            case "giocatore" -> {
                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), true);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
            }
            case "computerSx" -> {
                riproduciVerso(0);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), true);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
            }
            case "computerSu" -> {
                riproduciVerso(1);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), true);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
            }
            default -> {
                riproduciVerso(2);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), true);
            }
        }
    }

    /**
     * Metodo che controlla se una carta è giocabile
     * @param c è la carta da giocare
     * @return se è la modalità standard ritorna Vero nei casi base
     *         se è la modalità invertita ritorna Vero nei casi contrari delle regole ufficiali
     */
    private boolean giocabile(Carta c){
        if (modalita.equals(ModalitaGioco.INVERTITA))
            return !(t.getScarti().peek().getValoreIntero().equals(c.getValoreIntero()) ||
                    t.getScarti().peek().getColore().equals(c.getColore()));
        else
            return t.getScarti().peek().getValoreIntero().equals(c.getValoreIntero()) ||
                    t.getScarti().peek().getColore().equals(c.getColore()) ||
                    t.getScarti().peek().getColore().equals(Colori.VIOLA.toString()) ||
                    c.getColore().equals(Colori.NERO.toString()) ||
                    c.getColore().equals(Colori.VIOLA.toString());
    }

    /**
     * Metodo che gestisce il reset delle componenti della partita
     */
    public void resetPartita(){
        t.getGiocatore().getMano().clear();
        t.getComputerSx().getMano().clear();
        t.getComputerSu().getMano().clear();
        t.getComputerDx().getMano().clear();
        f.getGw().getLabelSceltaColore().setVisible(false);
        tm.setDirezione(true);
        f.getGw().visualizzaDirezione(tm.getDirezione());
        tm.resetTurni();
        segnaGiocatoreAttivo();
    }

    /**
     * Metodo che gestisce la fine della partita e fa ritornare nel menù principale
     */
    public void partitaFinita(){
        f.getGw().setEnabled(false);
        stopMusicaAmbiente();
        JFrame uscita = new JFrame();
        JOptionPane.showMessageDialog(uscita,"FINE PARTITA!");
        riproduciEffettoSpeciale(7);
        f.getGw().setVisible(false);
        f.getPrincipale().setVisible(true);
        f.getMenu().setVisible(true);
        riproduciMusicaAmbiente(5);

    }

    /**
     * Metodo che avvia la partita, mischiando il mazzo, mettendo la prima carta come pila degli scarti
     * controllandola e gestendo eventuali effetti e distribuendo le carte ai giocatori
     */
    public void iniziaPartita(){
        resetPartita();
        impostaAvatars();
        stopMusicaAmbiente();
        leggiImpostazioniAudio();
        f.getGw().setEnabled(true);
        riproduciMusicaAmbiente(4);
        t.getMazzo().formaMazzo();
        if(modalita.equals(ModalitaGioco.EXTRA))
            t.getMazzo().aggiungiCarteSpeciali();
        t.getMazzo().mischiaCarte();

        for(int i = 0; i < 7; i++){
            t.getGiocatore().getMano().add(t.getMazzo().pesca());
            t.getComputerSx().getMano().add(t.getMazzo().pesca());
            t.getComputerSu().getMano().add(t.getMazzo().pesca());
            t.getComputerDx().getMano().add(t.getMazzo().pesca());
        }
/*
            t.getGiocatore().getMano().clear();
            t.getComputerSx().getMano().clear();
            t.getComputerSu().getMano().clear();
            t.getComputerDx().getMano().clear();

        for(int i = 0; i < 7; i++){
            t.getGiocatore().getMano().add(new CartaNormale(Colori.NERO,Valori.PESCAQUATTRO));
            t.getComputerSx().getMano().add(new CartaNormale(Colori.NERO,Valori.PESCAQUATTRO));
            t.getComputerSu().getMano().add(new CartaNormale(Colori.NERO,Valori.PESCAQUATTRO));
            t.getComputerDx().getMano().add(new CartaNormale(Colori.NERO,Valori.PESCAQUATTRO));
        }
*/
        while(t.getMazzo().getPilaMazzo().peek().getValoreIntero().equals(Valori.PESCAQUATTRO.getValoreIntero()))
            t.getMazzo().mischiaCarte();
        t.getScarti().push(t.getMazzo().pesca());
        System.out.println("Carta Girata: "+t.getScarti().peek().toString());
        //TODO:Delay fatto da ricontrollare per sicurezza
        if(t.getScarti().peek().getValoreIntero().equals(Valori.PESCADUE.getValoreIntero())) {
            tm.setTurno(tm.getTurno() - 1);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    pescaDue();
                    tm.passaTurno();
                    segnaGiocatoreAttivo();
                    faiGiocare();
                }
            },2000);
            //TODO:Delay fatto da ricontrollare per sicurezza
        }else if(t.getScarti().peek().getValoreIntero().equals(Valori.CAMBIOCOLORE.getValoreIntero())) {
            segnaGiocatoreAttivo();
            cambioColore();
            faiGiocare();
        }else{
        //TODO:Delay fatto da ricontrollare per sicurezza
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    applicaEffetto(t.getScarti().peek().getValoreIntero());
                    segnaGiocatoreAttivo();
                    faiGiocare();
                }
            },2000);
        }
            f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
            f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore(),this);
            f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx());
            f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu());
            f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx());
    }

    /**
     * Sceglie casualmente le icone e il nome degli avversari con i versi corrispettivi
     */
    public void impostaAvatars(){
        //TODO:Aggiustare i substring e vedere come aggiustare il decoratore magari facendo decorare anche nome
        //GIOCATORE
        f.getGw().getNomeGiocatore().setText(t.getGiocatore().getUsername());
        new DecoratoreProfilo(f.getGw().getAvatarGiocatore()).visualizzaAvatar();
        //COMPUTER SX
        int primo = sourceRandom.nextInt(0,3);
        versi.aggiungiCanzone(primo);
        f.getGw().getAvatarComputerSx().setName("ComputerSx"+Avversari.values()[primo].toString().toLowerCase());
        System.out.println(f.getGw().getAvatarComputerSx().getName());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSx()).visualizzaAvatar();
        f.getGw().getNomeComputerSx().setText(f.getGw().getAvatarComputerSx().getName().substring(10));
        // COMPUTER SU
        int secondo = sourceRandom.nextInt(3,6);
        versi.aggiungiCanzone(secondo);
        f.getGw().getAvatarComputerSu().setName("ComputerSu"+Avversari.values()[secondo].toString().toLowerCase());
        System.out.println(f.getGw().getAvatarComputerSu().getName());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSu()).visualizzaAvatar();
        f.getGw().getNomeComputerSu().setText(f.getGw().getAvatarComputerSu().getName().substring(10));
        //COMPUTER DX
        int terzo = sourceRandom.nextInt(6,9);
        versi.aggiungiCanzone(terzo);
        f.getGw().getAvatarComputerDx().setName("ComputerDx"+Avversari.values()[terzo].toString().toLowerCase());
        System.out.println(f.getGw().getAvatarComputerDx().getName());
        new DecoratoreProfilo(f.getGw().getAvatarComputerDx()).visualizzaAvatar();
        f.getGw().getNomeComputerDx().setText(f.getGw().getAvatarComputerDx().getName().substring(10));
    }

    /**
     * Metodo che aggiorna la mano dei giocatori su schermo
     * @param g è il giocatore in questione
     */
    public void aggiornaMano(Giocatori g){
        switch (g.getNome()) {
            case "computerSx" -> f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx());
            case "computerSu" -> f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu());
            case "computerDx" -> f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx());
            default -> f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore(), this);
        }
    }

    /**
     * Metodo che aggiorna il profilo del giocatore in base alle partite giocate e le vittorie
     */
    public void aggiornaProfilo(){
        f.getPw().getGiocate().setText("Giocate: " + t.getGiocatore().getGiocate());
        f.getPw().getVittorie().setText("Vittorie: " + t.getGiocatore().getVittorie());
        f.getPw().getSconfitte().setText("Sconfitte: " + t.getGiocatore().getSconfitte());
        f.getPw().getLivello().setText("Livello: " + t.getGiocatore().getLivello());
    }

    /**
     * Metodo per riprodurre la musica di background
     * @param i è l'indice della traccia
     */
    public void riproduciMusicaAmbiente(int i){
        ambiente.setTraccia(i);
        ambiente.regola();
        ambiente.play();
        ambiente.loop();
    }

    /**
     * Metodo per fermare la musica
     */
    public void stopMusicaAmbiente(){
        ambiente.stop();
        ambiente.close();
    }

    /**
     * Metodo per riprodurre suoni
     * @param i è l'indice della traccia
     */
    public void riproduciEffettoSpeciale(int i){
        effetti.setTraccia(i);
        effetti.regola();
        effetti.play();
    }

    /**
     * Metodo per riprodurre i versi degli avversari
     * @param i è l'indice della traccia
     */
    public void riproduciVerso(int i){
        versi.setTraccia(i);
        versi.regola();
        versi.play();
    }

    /**
     * Metodo che legge i valori dei volumi sui JSlider e scrive il valore corrispondente dopo averlo convertito
     */
    public void leggiImpostazioniAudio(){
        f.getAudio().getBarraVolumeAmbiente().setValue((int) ambiente.getVolume());
        f.getAudio().getVolumeAmbiente().setText(String.valueOf((int)((f.getAudio().getBarraVolumeAmbiente().getValue()+80F)/0.85F)));
        f.getAudio().getBarraVolumeEffetti().setValue((int) effetti.getVolume());
        f.getAudio().getVolumeEffetti().setText(String.valueOf((int)((f.getAudio().getBarraVolumeEffetti().getValue()+80F)/0.85F)));
        f.getAudio().getBarraVolumeVersi().setValue((int) versi.getVolume());
        f.getAudio().getVolumeVersi().setText(String.valueOf((int)((f.getAudio().getBarraVolumeVersi().getValue()+80F)/0.85F)));
    }

    /**
     * Metodo per rendere il Controller Singleton
     * @param f è la finestra di gioco (View)
     * @param t è il tavolo di gioco (Model)
     * @return l'istanza del Controller senza crearne uno nuovo se già esiste
     */
    public static Controller getInstance(FinestraGioco f, Tavolo t) {
        if (instance == null) instance = new Controller(f,t);
        return instance;
    }

}

//        f.getGw().getIconaComputerSx().setIcon(new ImageIcon(Toolkit.getDefaultToolkit()
//                .createImage("Risorse/images/Sprite/"+f.getGw().getIconaComputerSx().getName()+".png")
//                .getScaledInstance(70, 70, 16)));

//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    visualizzaTavolo();
//                }
//            }, 5000);

//        try {
//                TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//                }

//TODOrivedere
//                switch (bc.getCarta().getValore()) {
//                        case "10":
//                        playES(2);
//                        invertiGiro();
//                        segnaGiocatoreAttivo(false);
////                        visualizzaTavolo();
////                        tm.passaTurno();
////                      segnaGiocatoreAttivo(true);
////                        new Timer().schedule(new TimerTask() {
////                            @Override
////                            public void run() {
////                                faiGiocare();
////                            }
////                        }, 3000);
//                        break;
//                        case "11":
//                        playES(2);
//                        blocco();
//                        segnaGiocatoreAttivo(false);
//                        tm.passaTurno();
////                        visualizzaTavolo();
////                        tm.passaTurno();
////                        segnaGiocatoreAttivo(true);
////                        new Timer().schedule(new TimerTask() {
////                        @Override
////                        public void run() {
////                            faiGiocare();
////                        }
////                    }, 3000);
//                        break;
//                        case "12":
//                        playES(2);
//                        pescaDue();
//                        segnaGiocatoreAttivo(false);
//                        tm.passaTurno();
////                        visualizzaTavolo();
////                        tm.passaTurno();
////                        segnaGiocatoreAttivo(false);
////                        new Timer().schedule(new TimerTask() {
////                            @Override
////                            public void run() {
////                                faiGiocare();
////                            }
////                        }, 3000);
//                        break;
//                        case "13":
//                        playES(2);
//                        cambioColore();
//                        segnaGiocatoreAttivo(false);
//                        visualizzaTavolo();
//                        return;
////                        break;
//                        case "14":
//                        playES(2);
//                        pescaQuattro();
//                        segnaGiocatoreAttivo(false);
//                        tm.passaTurno();
//                        return;
//default:
//        playES(1);
//        segnaGiocatoreAttivo(false);
//        break;
////                        segnaGiocatoreAttivo(false);
////                        visualizzaTavolo();
////                        break;
////                    default:
////                        visualizzaTavolo();
////                        tm.passaTurno();
////                        segnaGiocatoreAttivo(true);
////                        new Timer().schedule(new TimerTask() {
////                            @Override
////                            public void run() {
////                                faiGiocare();
////                            }
////                        }, 3000);
////                        break;
//        }
