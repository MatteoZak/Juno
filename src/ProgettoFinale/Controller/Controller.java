package ProgettoFinale.Controller;

import ProgettoFinale.Controller.Pacchetti.*;
import ProgettoFinale.FinestraGioco;
import ProgettoFinale.Model.Carte.*;
import ProgettoFinale.Model.Giocatori.Computer;
import ProgettoFinale.Model.Giocatori.Giocatore;
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
import java.lang.reflect.Method;
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
                if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())){
                    switch(t.getScarti().peek().getValoreIntero()){
                        case "18": //Scambio
                            //TODO:metodo per stabilire la vittima
                            if ((((JButton) e.getSource()).getName()).contains("ComputerSx")) {
                                vittima = t.getComputerSx();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerSu")) {
                                vittima = t.getComputerSu();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerDx")) {
                                vittima = t.getComputerDx();
                            }
                            List<Carta> listaDiAppoggio = new LinkedList<>(t.getTm().getGiocatoreDiTurno().getMano());
                            System.out.println("Lista di appoggio:"+listaDiAppoggio);
                            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
                            t.getTm().getGiocatoreDiTurno().getMano().removeAll(t.getTm().getGiocatoreDiTurno().getMano());
                            t.getTm().getGiocatoreDiTurno().getMano().addAll(vittima.getMano());
                            System.out.println("Mano utilizzatore: "+t.getTm().getGiocatoreDiTurno().getNome()+t.getTm().getGiocatoreDiTurno().getMano().toString());
                            vittima.getMano().removeAll(vittima.getMano());
                            vittima.getMano().addAll(listaDiAppoggio);
                            System.out.println("Mano vittima: "+vittima.getNome()+vittima.getMano().toString());
                            t.notificaCambiamenti(new Aggiornamento(this, t.getTm().getGiocatoreDiTurno(), vittima));
                            f.getGw().getSelezionaGiocatore().setVisible(false);
                            t.getTm().passaTurno();
                            t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                            faiGiocare();
                            break;
                        case "20": //Duello
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


                            String maxGiocatore = String.valueOf(t.getTm().getGiocatoreDiTurno().getMano().stream()
                                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());
                            System.out.println("vittima: "+vittima.getNome());
                            System.out.println("carta vittima: "+maxVittima);
                            System.out.println("utilizzatore: "+t.getGiocatore().getNome());
                            System.out.println("carta utilizzatore: "+maxGiocatore);
                            fineMazzo();
                            Carta c1 = t.getMazzo().pesca();
                            fineMazzo();
                            Carta c2 = t.getMazzo().pesca();

                            if(maxGiocatore.compareTo(maxVittima)>=0) {
                                vittima.getMano().add(c1);
                                inviaPacchettoPescata(vittima,c1);
                                vittima.getMano().add(c2);
                                inviaPacchettoPescata(vittima,c2);
                            }else{
                                t.getGiocatore().getMano().add(c1);
                                inviaPacchettoPescata(t.getGiocatore(),c1);
                                t.getGiocatore().getMano().add(c2);
                                inviaPacchettoPescata(t.getGiocatore(),c2);
                            }
                            f.getGw().getSelezionaGiocatore().setVisible(false);
                            t.getTm().passaTurno();
                            t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
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
                if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())) {
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
                t.getTm().passaTurno();
                t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                faiGiocare();
                break;
            case "PESCACARTA":
                if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())) {
                    Carta cartaPescata = t.getMazzo().pesca();
                    t.getGiocatore().getMano().add(cartaPescata);
                    t.notificaCambiamenti(new AvvisoPescata(cartaPescata, (Giocatore) t.getTm().getGiocatoreDiTurno(),this));
                    if(!giocabile(cartaPescata)) {
                        f.getGw().getPilaMazzo().setEnabled(true);
                        t.getTm().passaTurno();
                        t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
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
                    t.getTm().setTurno(t.getTm().getTurno()-1);
                t.getTm().passaTurno();
                t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                faiGiocare();
                break;
            case "SPIEGAZIONI":
                if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())) {
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
        if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore()) && bc.isEnabled()) {
            if (giocabile(bc.getCarta())) {
                f.getGw().getPassaTurno().setVisible(false);
//                t.giocata(t.getGiocatore(), bc.getCarta(), getActList(bc));
                t.getGiocatore().getMano().remove(bc.getCarta());
                t.getScarti().push(bc.getCarta());
                t.notificaCambiamenti(new AvvisoGiocata(bc,t.getTm().getGiocatoreDiTurno(),this));
                f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
                f.getGw().getPilaMazzo().setEnabled(true);
                applicaEffetto(bc.getCarta().getValoreIntero());
                if(t.getGiocatore().getMano().isEmpty()){
                    t.finePartita(true);
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
                t.getTm().passaTurno();
                t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
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
                    t.getTm().getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
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
        t.getTm().cambiaDirezione();
        f.getGw().visualizzaDirezione(t.getTm().getDirezione());
    }

    /**
     * Metodo della carta che blocca il giocatore successivo
     */
    private void blocco(){
        riproduciEffettoSpeciale(3);
        t.getTm().passaTurno();
    }

    /**
     * Metodo della carta che fa pescare due carte al giocatore successivo e lo blocca
     */
    private void pescaDue(){
        riproduciEffettoSpeciale(3);
        fineMazzo();
        Carta c = t.getMazzo().pesca();
        t.getTm().getGiocatoreSuccessivo().getMano().add(c);
        inviaPacchettoPescata(t.getTm().getGiocatoreSuccessivo(),c);
        fineMazzo();
        riproduciEffettoSpeciale(3);
        Carta c2 = t.getMazzo().pesca();
        t.getTm().getGiocatoreSuccessivo().getMano().add(c2);
        inviaPacchettoPescata(t.getTm().getGiocatoreSuccessivo(),c2);
        t.getTm().passaTurno();
    }

    /**
     * Metodo della carta che cambia il colore della pila degli scarti
     */
    private void cambioColore(){
        Carta c = t.getScarti().peek();
        if (!t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())){
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
            Carta c = t.getMazzo().pesca();
            t.getTm().getGiocatoreSuccessivo().getMano().add(c);
            inviaPacchettoPescata(t.getTm().getGiocatoreSuccessivo(),c);
        }
        cambioColore();
        t.getTm().passaTurno();
    }

    /////////////////////////////   Effetti Speciali Carte Personalizzate ////////////////////////////////

    /**
     * Metodo della carta che permette di scambiare tutte le carte in mano con un giocatore a scelta
     */
    private void scambioMani(){
        if (!t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())){
            Giocatori vittima = t.getTm().getGiocatori().stream().filter(x->!x.getNome().equals(t.getTm().getGiocatoreDiTurno().getNome()))
                    .toList().get(sourceRandom.nextInt(3));
            Giocatori giocatoreDiTurno = t.getTm().getGiocatoreDiTurno();

            List<Carta> listaDiAppoggio = new LinkedList<>(t.getTm().getGiocatoreDiTurno().getMano());

            giocatoreDiTurno.getMano().clear();
            giocatoreDiTurno.getMano().addAll(vittima.getMano());

            vittima.getMano().clear();
            vittima.getMano().addAll(listaDiAppoggio);

            t.notificaCambiamenti(new Aggiornamento(this, giocatoreDiTurno, vittima));
        }else
            f.getGw().getSelezionaGiocatore().setVisible(true);
    }

    /**
     * Metodo della carta che chi la gioca sceglie un altro giocatore e chi ha la carta più alta nella propria mano
     * fa pescare due carte all'altro
     */
    private void duello(){
        if (!t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())){
            Giocatori vittima = t.getTm().getGiocatori().stream().filter(x->!x.getNome().equals(t.getTm().getGiocatoreDiTurno().getNome()))
                    .toList().get(sourceRandom.nextInt(3));
            String maxUtilizzatore = String.valueOf(t.getTm().getGiocatoreDiTurno().getMano().stream()
                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());

            String maxVittima = String.valueOf(vittima.getMano().stream()
                    .filter(x->!(x.getColore().equals(Colori.NERO.toString()) || x.getColore().equals((Colori.VIOLA.toString()))))
                    .max(Comparator.comparing(Carta::getValoreIntero)).get().getValoreIntero());
            System.out.println("carta vittima: "+maxVittima);
            System.out.println("carta utilizzatore: "+maxUtilizzatore);
            fineMazzo();
            Carta c1 = t.getMazzo().pesca();
            fineMazzo();
            Carta c2 = t.getMazzo().pesca();
            if(maxUtilizzatore.compareTo(maxVittima)>=0) {
                vittima.getMano().add(c1);
                inviaPacchettoPescata(vittima,c1);
                vittima.getMano().add(c2);
                inviaPacchettoPescata(vittima,c2);
            }else{
                t.getGiocatore().getMano().add(c1);
                inviaPacchettoPescata(t.getGiocatore(),c1);
                t.getGiocatore().getMano().add(c2);
                inviaPacchettoPescata(t.getGiocatore(),c2);
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
            Carta cartaPescataBot = t.getMazzo().pesca();
            if(giocabile(cartaPescataBot)){
                t.giocataPescata(cartaPescataBot);
                t.cambioPilaScarti(cartaPescataBot);
                applicaEffetto(cartaPescataBot.getValoreIntero());
            }else {t.pescata(computer);}
        } else {
            Carta cartaDaGiocare = giocabili.get(sourceRandom.nextInt(giocabili.size()));
            t.giocata(computer, cartaDaGiocare);
            t.cambioPilaScarti(cartaDaGiocare);
            applicaEffetto(cartaDaGiocare.getValoreIntero());
            if (computer.getMano().isEmpty()){
                t.finePartita(false);
            }
        }
        t.turnoPassato();
        faiGiocare();
    }

    /**
     * Metodo che permette ai giocatori di eseguire il proprio turno
     */
    private void faiGiocare(){
        if(!t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    turnoComputer(t.getTm().getGiocatoreDiTurno());
                }
            }, 3000);
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
        t.getTm().setDirezione(true);
        f.getGw().visualizzaDirezione(t.getTm().getDirezione());
        t.getTm().resetTurni();
        t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
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
        while(t.getMazzo().getPilaMazzo().peek().getValoreIntero().equals(Valori.PESCAQUATTRO.getValoreIntero()))
            t.getMazzo().mischiaCarte();
        t.getScarti().push(t.getMazzo().pesca());
        System.out.println("Carta Girata: "+t.getScarti().peek().toString());
        //TODO:Delay fatto da ricontrollare per sicurezza
        if(t.getScarti().peek().getValoreIntero().equals(Valori.PESCADUE.getValoreIntero())) {
            t.getTm().setTurno(t.getTm().getTurno() - 1);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    pescaDue();
                    t.getTm().passaTurno();
                    t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                    faiGiocare();
                }
            },2000);
            //TODO:Delay fatto da ricontrollare per sicurezza
        }else if(t.getScarti().peek().getValoreIntero().equals(Valori.CAMBIOCOLORE.getValoreIntero())) {
            t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
            cambioColore();
            faiGiocare();
        }else{
            //TODO:Delay fatto da ricontrollare per sicurezza
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    applicaEffetto(t.getScarti().peek().getValoreIntero());
                    t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                    faiGiocare();
                }
            },2000);
        }
        f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
        f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore().getMano(),this);
        f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx().getMano());
        f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu().getMano());
        f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx().getMano());
    }

    /**
     * Sceglie casualmente le icone e il nome degli avversari con i versi corrispettivi
     */
    public void impostaAvatars(){
        //TODO:Aggiustare i substring e vedere come aggiustare il decoratore magari facendo decorare anche nome
        /////////////////////////////////////////////////// GIOCATORE //////////////////////////////////////////////
        f.getGw().getNomeGiocatore().setText(t.getGiocatore().getUsername());
        new DecoratoreProfilo(f.getGw().getAvatarGiocatore()).visualizzaAvatar();
        /////////////////////////////////////////////////// COMPUTER SX ///////////////////////////////////////////
        int primo = sourceRandom.nextInt(0,3);
        versi.aggiungiCanzone(primo);
        f.getGw().getAvatarComputerSx().setName("ComputerSx"+Avversari.values()[primo].toString().toLowerCase());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSx()).visualizzaAvatar();
        f.getGw().getNomeComputerSx().setText(f.getGw().getAvatarComputerSx().getName().substring(10));
        /////////////////////////////////////////////////// COMPUTER SU ///////////////////////////////////////////
        int secondo = sourceRandom.nextInt(3,6);
        versi.aggiungiCanzone(secondo);
        f.getGw().getAvatarComputerSu().setName("ComputerSu"+Avversari.values()[secondo].toString().toLowerCase());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSu()).visualizzaAvatar();
        f.getGw().getNomeComputerSu().setText(f.getGw().getAvatarComputerSu().getName().substring(10));
        /////////////////////////////////////////////////// COMPUTER DX ///////////////////////////////////////////
        int terzo = sourceRandom.nextInt(6,9);
        versi.aggiungiCanzone(terzo);
        f.getGw().getAvatarComputerDx().setName("ComputerDx"+Avversari.values()[terzo].toString().toLowerCase());
        new DecoratoreProfilo(f.getGw().getAvatarComputerDx()).visualizzaAvatar();
        f.getGw().getNomeComputerDx().setText(f.getGw().getAvatarComputerDx().getName().substring(10));
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
     * Metodo che aggiorna la mano dei giocatori su schermo
     * @param g è il giocatore in questione
     */
    public void aggiornaMano(Giocatori g){
        switch (g.getNome()) {
            case "computerSx" -> f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx().getMano());
            case "computerSu" -> f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu().getMano());
            case "computerDx" -> f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx().getMano());
            default -> f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore().getMano(), this);
        }
    }

    public void inviaPacchettoPescata(Giocatori giocatore, Carta c){
        if(giocatore instanceof Giocatore){
            t.notificaCambiamenti(new AvvisoPescata(c, (Giocatore) giocatore,this));
        }else{
            t.notificaCambiamenti(new AvvisoPescataComputer((Computer) giocatore));
        }
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


//    private void animazioneGiocatoriGiocaCarta(Giocatori giocatore, Carta carta){
//        switch (giocatore.getNome()){
//            case "computerSx"-> {
//                f.getGw().getAnimazioneComputerSxGioca().setImage(carta.getImmagine());
//                f.getGw().getAnimazioneComputerSxGioca().setX(10);
//                f.getGw().getAnimazioneComputerSxGioca().setY(10);
//                f.getGw().getAnimazioneComputerSxGioca().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSxGioca());
//            }
//            case "computerSu" -> {
//                f.getGw().getAnimazioneComputerSuGioca().setImage(carta.getImmagine());
//                f.getGw().getAnimazioneComputerSuGioca().setX(5);
//                f.getGw().getAnimazioneComputerSuGioca().setY(5);
//                f.getGw().getAnimazioneComputerSuGioca().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSuGioca());
//            }
//            default -> {
//                f.getGw().getAnimazioneComputerDxGioca().setImage(carta.getImmagine());
//                //SE NON FUNZIONA METTIAMO f.getGw().getAnimazioneComputerDxGioca().getWidth() ma con piu velocita su x
//                f.getGw().getAnimazioneComputerDxGioca().setX(f.getGw().getPilaScarti().getX());
//                f.getGw().getAnimazioneComputerDxGioca().setY(10);
//                f.getGw().getAnimazioneComputerDxGioca().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerDxGioca());
//            }
//        }
//    }

//    /**
//     * Metodo che rende visibile la pescata degli avversari
//     * @param giocatore è il giocatore corrente del turno
//     */
//    private void animazioneGiocatori(Giocatori giocatore){
//        switch (giocatore.getNome()) {
//            case "computerSx" -> {
//                f.getGw().getAnimazioneComputerSx().setX(f.getGw().getPilaMazzo().getX() - 200);
//                f.getGw().getAnimazioneComputerSx().setY(f.getGw().getPilaMazzo().getY() - 200);
//                f.getGw().getAnimazioneComputerSx().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSx());
//            }
//            case "computerSu" -> {
//                f.getGw().getAnimazioneComputerSu().setX(f.getGw().getPilaMazzo().getX() - 200);
//                f.getGw().getAnimazioneComputerSu().setY(f.getGw().getPilaMazzo().getY() - 200);
//                f.getGw().getAnimazioneComputerSu().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerSu());
//            }
//            case "computerDx" -> {
//                f.getGw().getAnimazioneComputerDx().setX(f.getGw().getPilaMazzo().getX() - 200);
//                f.getGw().getAnimazioneComputerDx().setY(f.getGw().getPilaMazzo().getY() - 200);
//                f.getGw().getAnimazioneComputerDx().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneComputerDx());
//            }
//            default -> {
//                f.getGw().getAnimazioneGiocatore().setX(f.getGw().getPilaMazzo().getX() - 200);
//                f.getGw().getAnimazioneGiocatore().setY(f.getGw().getPilaMazzo().getY() - 200);
//                f.getGw().getAnimazioneGiocatore().timer();
//                f.getGw().getSfondo().add(f.getGw().getAnimazioneGiocatore());
//            }
//        }
//    }

//  /**
//     * Metodo che aggiorna la mano dei giocatori su schermo
//     * @param g è il giocatore in questione
//     */
//    public void aggiornaMano(Giocatori g){
//        switch (g.getNome()) {
//            case "computerSx" -> f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx());
//            case "computerSu" -> f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu());
//            case "computerDx" -> f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx());
//            default -> f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore(), this);
//        }
//    }//    }

//  /**
//     * Metodo che permette di visualizzare chi è il giocatore di turno
//     */
//    private void segnaGiocatoreAttivo(){
//        switch (tm.getGiocatoreDiTurno().getNome()) {
//            case "giocatore" -> {
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), true);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
//            }
//            case "computerSx" -> {
//                riproduciVerso(0);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), true);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
//            }
//            case "computerSu" -> {
//                riproduciVerso(1);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), true);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), false);
//            }
//            default -> {
//                riproduciVerso(2);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarGiocatore(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSx(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerSu(), false);
//                f.getGw().giocatoreAttivo(f.getGw().getAvatarComputerDx(), true);
//            }
//        }
//    }

///**
//     * Metodo per far eseguire le mosse agli avversari
//     * @param computer è l'avversario corrente che giocherà il suo turno
//     */
//    public void turnoComputer(Giocatori computer){
//        if (!f.getGw().isVisible()) {
//            System.out.println(computer.getNome()+": Me fermo!");
//            return;
//        }
//        List<Carta> giocabili = computer.getMano().stream().filter(this::giocabile).toList();
//        if (giocabili.isEmpty()){
//            fineMazzo();
//            Carta cartaPescataBot = t.getMazzo().pesca();
//            if(giocabile(cartaPescataBot)){
////                t.notificaCambiamenti(new AvvisoGiocataComputer(cartaPescataBot,t.getTm().getGiocatoreDiTurno()));
////                t.getScarti().push(cartaPescataBot);
////                f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
//                t.giocataPescata(cartaPescataBot);
//                applicaEffetto(cartaPescataBot.getValoreIntero());
//            }else {
//                t.pescata(computer,null);
////                computer.pescata(cartaPescataBot);
////                t.notificaCambiamenti(new AvvisoPescataComputer((Computer) t.getTm().getGiocatoreDiTurno()));
//            }
//        } else {
//            Carta cartaDaGiocare = giocabili.get(sourceRandom.nextInt(giocabili.size()));
////            computer.getMano().remove(cartaDaGiocare);
////            t.notificaCambiamenti(new AvvisoGiocataComputer(cartaDaGiocare,t.getTm().getGiocatoreDiTurno()));
////            t.getScarti().push(cartaDaGiocare);
////            f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
//            t.giocata(computer, cartaDaGiocare);
//            applicaEffetto(cartaDaGiocare.getValoreIntero());
//            if (computer.getMano().isEmpty()){
//                t.getGiocatore().sconfitta();
//                t.getGiocatore().livellamento(50);
//                riproduciEffettoSpeciale(8);
//                partitaFinita();
//            }
//        }
//        t.getTm().passaTurno();
////        segnaGiocatoreAttivo();
//        t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
//        faiGiocare();
//    }


//    public void cartaCliccata(BottoneCarta bc){
//        //TODO:Ricontrollare
//        if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore()) && bc.isEnabled()) {
//            if (giocabile(bc.getCarta())) {
//                f.getGw().getPassaTurno().setVisible(false);
//                t.giocata(t.getGiocatore(), bc.getCarta(), getActList(bc));
//                t.getGiocatore().getMano().remove(bc.getCarta());
//                t.getScarti().push(bc.getCarta());
//                t.notificaCambiamenti(new AvvisoGiocata(bc,t.getTm().getGiocatoreDiTurno(),this));
//                f.getGw().getPilaScarti().setIcon(new ImageIcon(t.getScarti().peek().getImmagine()));
//                f.getGw().getPilaMazzo().setEnabled(true);
//                applicaEffetto(bc.getCarta().getValoreIntero());
//                if(t.getGiocatore().getMano().isEmpty()){
//                    t.finePartita(true);
//                }
//                if(t.getGiocatore().getMano().size() == 1){
//                    f.getGw().getTastoJuno().setVisible(true);
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            if(f.getGw().getTastoJuno().isVisible()) {
//                                fineMazzo();
//                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
//                                fineMazzo();
//                                t.getGiocatore().getMano().add(t.getMazzo().pesca());
//                                f.getGw().getTastoJuno().setVisible(false);
//                                aggiornaMano(t.getGiocatore());
//                            }
//                        }
//                    }, 3000);
//                }
//                if (f.getGw().getLabelSceltaColore().isVisible() || f.getGw().getSelezionaGiocatore().isVisible()){
//                    return;
//                }
//                t.getTm().passaTurno();
//                t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
//                faiGiocare();
//            }
//        }
//    }


//    /**
//     * Metodo che gestisce la fine della partita e fa ritornare nel menù principale
//     */
//    public void partitaFinita(){
//        f.getGw().setEnabled(false);
//        stopMusicaAmbiente();
//        JFrame uscita = new JFrame();
//        JOptionPane.showMessageDialog(uscita,"FINE PARTITA!");
//        riproduciEffettoSpeciale(7);
//        f.getGw().setVisible(false);
//        f.getPrincipale().setVisible(true);
//        f.getMenu().setVisible(true);
//        riproduciMusicaAmbiente(5);
//    }