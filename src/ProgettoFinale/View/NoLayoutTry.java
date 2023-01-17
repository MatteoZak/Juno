package ProgettoFinale.View;
//
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
//
public class NoLayoutTry extends JFrame {
    public NoLayoutTry() {

    }
}
//    int i = 0;
//    private JPanel panel0 = new JPanel();
//    private JPanel panelManoBot1 = new JPanel();
//    private JPanel panelManoBot2 = new JPanel();
//    private JPanel panelManoBot3 = new JPanel();
//    private JPanel panelMazzo = new JPanel();
//    private JPanel panelGiocatore = new JPanel();
//    private JButton mazzo = new JButton();
//    private Mazzo veroMazzo = new Mazzo();
//
//    public NoLayoutTry() throws IOException {
//        super("Juno!!!");
//
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int height = (int) screenSize.getHeight();
//        int width = (int) screenSize.getWidth();
////        setUndecorated(true);
//        //setResizable(false);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setLayout(null);
//
//        Image img = ImageIO.read(new File("back.png"))
//                           .getScaledInstance(100, 150, 16);
//        Image img1 = ImageIO.read(new File("images/blue4.png")).getScaledInstance(100, 150, 16);
//
//////////////////////////     PANEL 1     //////////////////////////
////        panelManoBot1.setBounds(0,0,200,height-200);
////        panelManoBot1.setBackground(Color.BLUE);
////        add(panelManoBot1);
//        panelManoBot1.setBounds(0,0,200,height-200);
//        panelManoBot1.setBackground(Color.blue);
//        add(panelManoBot1);
////////////////////////////////////////////////////////////////////
//
//////////////////////////     PANEL 2     //////////////////////////
//        panelManoBot2.setBounds(200,0,width-400,200);
//        panelManoBot2.setBackground(Color.RED);
//        add(panelManoBot2);
////////////////////////////////////////////////////////////////////
//
//////////////////////////     PANEL 4     //////////////////////////
//        panelManoBot3.setBounds(width-200,0,200,height-200);
//        panelManoBot3.setBackground(Color.YELLOW);
//        add(panelManoBot3);
////////////////////////////////////////////////////////////////////
//
//////////////////////////     PANEL 3     //////////////////////////
//        JLabel scarti = new JLabel(new ImageIcon(img1));
//        scarti.setBounds(400,157,100,150);
//        panelMazzo.add(scarti);
//        panelMazzo.setBounds(200,200,width-400,height-400);
//        panelMazzo.setBackground(Color.GREEN);
//        panelMazzo.setLayout(new BorderLayout());
//        mazzo.setIcon(new ImageIcon(img));
//        mazzo.setBorderPainted(false);
//        mazzo.setFocusPainted(false);
//        mazzo.setContentAreaFilled(false);
//        mazzo.addActionListener((new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                    try {
//                        JButton nuovaCarta = new JButton();
//                        nuovaCarta.setBorderPainted(false);
//                        nuovaCarta.setFocusPainted(false);
//                        nuovaCarta.setContentAreaFilled(false);
//
//                        nuovaCarta.setIcon(new ImageIcon(veroMazzo.getCollezione().get(i).getImmagine()));
////                        nuovaCarta.setBounds(width / 2 -300+ i*100, height - 200, 100, 150);
//                        panelGiocatore.add(nuovaCarta);
//                        i++;
//                        nuovaCarta.addActionListener(e1 -> {
//                            Icon imgScarto = nuovaCarta.getIcon();
//                            panelGiocatore.remove(nuovaCarta);
//                            scarti.setIcon(imgScarto);
//                            panelGiocatore.revalidate();
//                            panelGiocatore.repaint();
//                        });
//
//                    } catch (IndexOutOfBoundsException ex) {
//                        ex.getStackTrace();
//                        System.out.println("fine mazzo");
//                    }
//                }
//
//
//            }));
//        panelMazzo.add(mazzo,BorderLayout.CENTER);
//        add(panelMazzo);
////////////////////////////////////////////////////////////////////
//
//////////////////////////     PANEL 5     //////////////////////////
//        panelGiocatore.setBounds(0,height-200,width,200);
//        panelGiocatore.setBackground(Color.MAGENTA);
//        panelGiocatore.setLayout(new FlowLayout());
//        add(panelGiocatore);
////////////////////////////////////////////////////////////////////
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) throws IOException {
//        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice device = graphics.getDefaultScreenDevice();
//        NoLayoutTry frame = new NoLayoutTry();
//        device.setFullScreenWindow(frame);
//        frame.setVisible(true);
//    }
//}
