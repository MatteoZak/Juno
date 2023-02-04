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
import ProgettoFinale.View.ManiGiocatori.BottoneCarta;
import ProgettoFinale.View.Decorator.DecoratoreCarta;
import ProgettoFinale.View.Decorator.DecoratoreProfilo;
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

    private ModalitaGioco modalita;

    /**
     *
     *          Nel costruttore istanziamo i parametri e il TurnManager (tm)
     *          e riproduciamo la musica di sottofondo per poi aggiungere i Listener
     */
    private Controller() {
        this.f = FinestraGioco.getInstance();
        this.t = Tavolo.getTavoloInstance();
        t.addObserver(f);
        f.getAmbiente().riproduciMusicaAmbiente(5);
        f.getEffetti().riproduciEffettoSpeciale(0);
        f.getVersi().riproduciVerso(9);

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
            f.getAmbiente().setVolume(f.getAudio().getBarraVolumeAmbiente().getValue());
            f.getAudio().getVolumeAmbiente().setText(String.valueOf((int)((f.getAudio().getBarraVolumeAmbiente().getValue()+80F)/0.85F)));
            f.getAmbiente().regola();
        }else if (e.getSource().equals(f.getAudio().getBarraVolumeEffetti())){
            f.getEffetti().setVolume(f.getAudio().getBarraVolumeEffetti().getValue());
            f.getAudio().getVolumeEffetti().setText(String.valueOf((int)((f.getAudio().getBarraVolumeEffetti().getValue()+80F)/0.85F)));
            f.getEffetti().regola();
        } else if (e.getSource().equals(f.getAudio().getBarraVolumeVersi())) {
            f.getVersi().setVolume(f.getAudio().getBarraVolumeVersi().getValue());
            f.getAudio().getVolumeVersi().setText(String.valueOf((int) ((f.getAudio().getBarraVolumeVersi().getValue() + 80F) / 0.85F)));
            f.getVersi().regola();
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
                f.getAmbiente().stop();
                f.getAmbiente().riproduciMusicaAmbiente(3);
                leggiImpostazioniAudio();
                f.getMenu().setVisible(false);
                f.getAudio().setVisible(true);
                break;
            case "AVATAR":
                Giocatori vittima = null;
                if(t.getTm().getGiocatoreDiTurno().equals(t.getGiocatore())){
                    switch(t.getScarti().peek().getValoreIntero()){
                        case "18": //Scambio
                            if ((((JButton) e.getSource()).getName()).contains("ComputerSx")) {
                                vittima = t.getComputerSx();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerSu")) {
                                vittima = t.getComputerSu();
                            } else if ((((JButton) e.getSource()).getName()).contains("ComputerDx")) {
                                vittima = t.getComputerDx();
                            }
                            List<Carta> listaDiAppoggio = new LinkedList<>(t.getTm().getGiocatoreDiTurno().getMano());
                            t.getTm().getGiocatoreDiTurno().getMano().removeAll(t.getTm().getGiocatoreDiTurno().getMano());
                            t.getTm().getGiocatoreDiTurno().getMano().addAll(vittima.getMano());
                            vittima.getMano().removeAll(vittima.getMano());
                            vittima.getMano().addAll(listaDiAppoggio);
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
                    f.getAmbiente().stop();
                    leggiImpostazioniAudio();
                    f.getGw().setVisible(false);
                    f.getPrincipale().setVisible(true);
                    f.getMenu().setVisible(false);
                    f.getAudio().setVisible(true);
                    f.getAudio().getUscita().setActionCommand("PRINCIPALEPARTITA");
                    f.getAudio().getRiprendiPartita().setVisible(true);
                    f.getAmbiente().riproduciMusicaAmbiente(3);
                }
                break;
            case "INVERTITA":
                modalita = ModalitaGioco.INVERTITA;
                iniziaPartita();
                f.getModalita().setVisible(false);
                f.getGw().setVisible(true);
                break;
            case "JUNO":
                f.getEffetti().riproduciEffettoSpeciale(6);
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
                f.getAmbiente().stop();
                f.getAmbiente().riproduciMusicaAmbiente(5);
                f.getModalita().setVisible(false);
                f.getPw().setVisible(false);
                f.getAudio().setVisible(false);
                f.getPrincipale().setVisible(true);
                f.getMenu().setVisible(true);
                break;
            case "PRINCIPALEPARTITA":
                JFrame uscita = new JFrame();
                f.getAmbiente().stop();
                int risposta = JOptionPane.showConfirmDialog(uscita, "Vuoi davvero uscire e perdere la partita?");
                if (risposta == 0) {
                    t.getGiocatore().sconfitta();
                    f.getEffetti().riproduciEffettoSpeciale(8);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            f.getAmbiente().riproduciMusicaAmbiente(5);
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
                    f.getAmbiente().riproduciMusicaAmbiente(4);
                    f.getAudio().setVisible(false);
                    f.getPrincipale().setVisible(false);
                    f.getGw().setVisible(true);
                }
                f.getAudio().getRiprendiPartita().setVisible(false);
                break;
            case "PROFILO":
                f.getAmbiente().stop();
                f.getAmbiente().riproduciMusicaAmbiente(2);
                f.getPw().getLabelIcona().setIcon(new ImageIcon(t.getGiocatore().getIcona()
                        .getScaledInstance(100, 100, 16)));
                f.getPw().setUsername(t.getGiocatore().getUsername());
                aggiornaProfilo();
                f.getMenu().setVisible(false);
                f.getPw().setVisible(true);
                break;
            case "RIPRENDIPARTITA":
                f.getAmbiente().stop();
                f.getAudio().getRiprendiPartita().setVisible(false);
                f.getAmbiente().riproduciMusicaAmbiente(4);
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
                Carta c = t.getScarti().peek();
                if(e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaRosso())){
                    c.setColore(Colori.ROSSO);
                } else if (e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaBlu())) {
                    c.setColore(Colori.BLU);
                } else if(e.getSource().equals(f.getGw().getLabelSceltaColore().getSceltaVerde())){
                    c.setColore(Colori.VERDE);
                } else{
                    c.setColore(Colori.GIALLO);
                }
                f.getGw().getLabelSceltaColore().setVisible(false);
                f.getGw().getPilaScarti().setIcon(new ImageIcon(new DecoratoreCarta(c).visualizzaCarta()));
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
                t.getGiocatore().getMano().remove(bc.getCarta());
                t.getScarti().push(bc.getCarta());
                t.notificaCambiamenti(new AvvisoGiocata(bc,t.getTm().getGiocatoreDiTurno(),this));
                f.getGw().getPilaScarti().setIcon(new ImageIcon(new DecoratoreCarta(t.getScarti().peek()).visualizzaCarta()));
                f.getGw().getPilaMazzo().setEnabled(true);
                applicaEffetto(bc.getCarta().getValoreIntero());
                if(t.getGiocatore().getMano().isEmpty()){
                    f.getAmbiente().stop();
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
        switch (valore) {
            case "10" -> {
                invertiGiro();
            }
            case "11" -> {
                blocco();
            }
            case "12" -> {
                pescaDue();
            }
            case "13" -> {
                cambioColore();
            }
            case "14" -> {
                pescaQuattro();
            }
            case "15" -> {
                for(int i = 0; i < 3 ; i++){
                    blocco();
                }
            }
            case "16" -> {
                for(int i = 0; i < 3 ; i++){
                    pescaDue();
                }
            }
            case "17" -> {
                for(int i = 0; i < 3 ; i++){
                    fineMazzo();
                    t.getTm().getGiocatoreSuccessivo().getMano().add(t.getMazzo().pesca());
                    pescaDue();
                }
            }
            case "18" -> {
                scambioMani();
            }
            case "19" -> {
                invertiGiro();
                blocco();
            }
            case "20" -> {
                duello();
            }

        }
    }
/////////////////////////////   Effetti Speciali Carte Base  ////////////////////////////////
    /**
     * Metodo per la carta che inverte il giro, riproducendo il suono
     */
    private void invertiGiro(){
        t.getTm().cambiaDirezione();
        f.getGw().visualizzaDirezione(t.getTm().getDirezione());
    }

    /**
     * Metodo della carta che blocca il giocatore successivo
     */
    private void blocco(){
        t.getTm().passaTurno();
    }

    /**
     * Metodo della carta che fa pescare due carte al giocatore successivo e lo blocca
     */
    private void pescaDue(){
        fineMazzo();
        Carta c = t.getMazzo().pesca();
        t.getTm().getGiocatoreSuccessivo().getMano().add(c);
        inviaPacchettoPescata(t.getTm().getGiocatoreSuccessivo(),c);
        fineMazzo();
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
            f.getGw().getPilaScarti().setIcon(new ImageIcon(new DecoratoreCarta(c).visualizzaCarta()));
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
            f.getEffetti().riproduciEffettoSpeciale(5);
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
        f.getGw().getPilaScarti().setIcon(new ImageIcon(new DecoratoreCarta(t.getScarti().peek()).visualizzaCarta()));
    }

    /**
     * Metodo per far eseguire le mosse agli avversari
     * @param computer è l'avversario corrente che giocherà il suo turno
     */
    public void turnoComputer(Giocatori computer){
        if (!f.getGw().isVisible()) {
            return;
        }
        List<Carta> giocabili = computer.getMano().stream().filter(this::giocabile).toList();
        if (giocabili.isEmpty()){
            fineMazzo();
            Carta cartaPescataBot = t.getMazzo().pesca();
            if(giocabile(cartaPescataBot)){
                t.notificaCambiamenti(new AvvisoGiocataComputer(cartaPescataBot, computer));
                t.cambioPilaScarti(cartaPescataBot);
                t.notificaCambiamenti(new AvvisoPilaScarti(cartaPescataBot));
                applicaEffetto(cartaPescataBot.getValoreIntero());
            }else {
                t.pescata(computer, cartaPescataBot);
                t.notificaCambiamenti(new AvvisoPescataComputer((Computer) computer));
            }
        } else {
            Carta cartaDaGiocare = giocabili.get(sourceRandom.nextInt(giocabili.size()));
            t.giocata(computer, cartaDaGiocare);
            t.notificaCambiamenti(new AvvisoGiocataComputer(cartaDaGiocare, computer));
            t.cambioPilaScarti(cartaDaGiocare);
            t.notificaCambiamenti(new AvvisoPilaScarti(cartaDaGiocare));
            applicaEffetto(cartaDaGiocare.getValoreIntero());
            if (computer.getMano().isEmpty()){
                f.getAmbiente().stop();
                t.finePartita(false);
                t.notificaCambiamenti(new FinePartita(false));
            }
        }
        t.turnoPassato();
        t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
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
        f.getAmbiente().stop();
        leggiImpostazioniAudio();
        f.getGw().setEnabled(true);
        f.getAmbiente().riproduciMusicaAmbiente(4);
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
        }else if(t.getScarti().peek().getValoreIntero().equals(Valori.CAMBIOCOLORE.getValoreIntero())) {
            t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
            cambioColore();
            faiGiocare();
        }else{
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    applicaEffetto(t.getScarti().peek().getValoreIntero());
                    t.notificaCambiamenti(new PassaTurno(t.getTm().getGiocatoreDiTurno().getNome()));
                    faiGiocare();
                }
            },2000);
        }
        f.getGw().getPilaScarti().setIcon(new ImageIcon(new DecoratoreCarta(t.getScarti().peek()).visualizzaCarta()));
        f.getGw().getLabelManoGiocatore().visualizzaCarte(t.getGiocatore().getMano(),this);
        f.getGw().getLabelManoComputerSx().visualizzaMano(t.getComputerSx().getMano());
        f.getGw().getLabelManoComputerSu().visualizzaMano(t.getComputerSu().getMano());
        f.getGw().getLabelManoComputerDx().visualizzaMano(t.getComputerDx().getMano());
    }

    /**
     * Sceglie casualmente le icone e il nome degli avversari con i versi corrispettivi
     */
    public void impostaAvatars(){
        /////////////////////////////////////////////////// GIOCATORE //////////////////////////////////////////////
        f.getVersi().resetPlaylist();
        f.getGw().getNomeGiocatore().setText(t.getGiocatore().getUsername());
        new DecoratoreProfilo(f.getGw().getAvatarGiocatore()).visualizzaAvatar();
        /////////////////////////////////////////////////// COMPUTER SX ///////////////////////////////////////////
        int primo = sourceRandom.nextInt(0,3);
        f.getVersi().aggiungiCanzone(primo);
        f.getGw().getAvatarComputerSx().setName("ComputerSx"+Avversari.values()[primo].toString().toLowerCase());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSx()).visualizzaAvatar();
        f.getGw().getNomeComputerSx().setText(f.getGw().getAvatarComputerSx().getName().substring(10));
        /////////////////////////////////////////////////// COMPUTER SU ///////////////////////////////////////////
        int secondo = sourceRandom.nextInt(3,6);
        f.getVersi().aggiungiCanzone(secondo);
        f.getGw().getAvatarComputerSu().setName("ComputerSu"+Avversari.values()[secondo].toString().toLowerCase());
        new DecoratoreProfilo(f.getGw().getAvatarComputerSu()).visualizzaAvatar();
        f.getGw().getNomeComputerSu().setText(f.getGw().getAvatarComputerSu().getName().substring(10));
        /////////////////////////////////////////////////// COMPUTER DX ///////////////////////////////////////////
        int terzo = sourceRandom.nextInt(6,9);
        f.getVersi().aggiungiCanzone(terzo);
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

    /**
     * Metodo che invia il pacchetto per pescare da parte dei giocatori con il
     * metodo del tavolo
     * @param giocatore
     * @param c
     */
    public void inviaPacchettoPescata(Giocatori giocatore, Carta c){
        if(giocatore instanceof Giocatore){
            t.notificaCambiamenti(new AvvisoPescata(c, (Giocatore) giocatore,this));
        }else{
            t.notificaCambiamenti(new AvvisoPescataComputer((Computer) giocatore));
        }
    }


    /**
     * Metodo che legge i valori dei volumi sui JSlider e scrive il
     * valore corrispondente dopo averlo convertito
     */
    public void leggiImpostazioniAudio(){
        f.getAudio().getBarraVolumeAmbiente().setValue((int) f.getAmbiente().getVolume());
        f.getAudio().getVolumeAmbiente().setText(String.valueOf((int)((f.getAudio().getBarraVolumeAmbiente().getValue()+80F)/0.85F)));
        f.getAudio().getBarraVolumeEffetti().setValue((int) f.getEffetti().getVolume());
        f.getAudio().getVolumeEffetti().setText(String.valueOf((int)((f.getAudio().getBarraVolumeEffetti().getValue()+80F)/0.85F)));
        f.getAudio().getBarraVolumeVersi().setValue((int) f.getVersi().getVolume());
        f.getAudio().getVolumeVersi().setText(String.valueOf((int)((f.getAudio().getBarraVolumeVersi().getValue()+80F)/0.85F)));
    }


    /**
     * Metodo per rendere il Controller Singleton
     * @return l'istanza del Controller senza crearne uno nuovo se già esiste
     */
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

}