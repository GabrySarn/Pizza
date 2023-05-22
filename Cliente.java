import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends JFrame {
    private JPanel pnlnord, pnlsud;
    private JLabel richiesta;
    private JButton certo, info;

    private String folderpath = "Gestione//";
    private ArrayList<String> req = getRichiesta(folderpath + "Richieste.txt");
    private ArrayList<String> ingredienti = getRichiesta(folderpath + "Pizze.txt");
    private int num = 0;

    private DinChar dc;
    private PlayAudio pa = new PlayAudio();

    public Cliente() {
        super("pizzeria pummarola");
        setLayout(new BorderLayout());
        if (readRow("level.txt") == 0) {
            num = (int) (Math.random() * 15);
        } else {
            num = (int) (Math.random() * req.size());
        }

        pa.playSound("Audio//MusicaSottofondo.wav");

        JLabel background = new JLabel(new ImageIcon("Immagini//Design//banconeiniziale.jpg"), JLabel.CENTER);
        add(background);

        pnlnord = new JPanel();
        pnlnord.setLayout(new FlowLayout());
        pnlnord.setBackground(Color.black);

        richiesta = new JLabel("");
        richiesta.setForeground(Color.white);
        richiesta.setFont(new Font("Bahnschrift", Font.BOLD, 25));

        pnlnord.add(richiesta);

        pnlsud = new JPanel();
        pnlsud.setLayout(new FlowLayout());
        pnlsud.setBackground(Color.black);

        certo = new JButton("Certo");
        certo.setBackground(Color.white);
        certo.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        certo.addActionListener(e -> {
            setVisible(false);
            new PizzaGui(req.get(num), ingredienti.get(num), num,
                    req.get(num) + "\n" + "(" + ingredienti.get(num) + ")");
        });

        info = new JButton("Che cosa?");
        info.setBackground(Color.white);
        info.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        info.addActionListener(e -> {
            richiesta.setText(" ");
            testoseq(ingredienti.get(num), richiesta);
        });

        pnlsud.add(certo);
        pnlsud.add(info);

        add(pnlnord, BorderLayout.NORTH);
        add(pnlsud, BorderLayout.SOUTH);

        dc = new DinChar(richiesta, req.get(num));
        dc.start();

        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static ArrayList<String> getRichiesta(String percorso) {

        ArrayList<String> req = new ArrayList<>();
        try (FileReader fr = new FileReader(percorso);
                Scanner in = new Scanner(fr)) {
            while (in.hasNextLine()) {
                req.add(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("file non trovato: " + percorso);
        }
        return req;
    }

    public void testoseq(String text, JLabel label) {
        if (!dc.isAlive()) {
            dc = new DinChar(label, text);
            dc.start();
        }
    }

    public int readRow(String path) {
        int lvl = 0;
        try (FileReader fr = new FileReader(folderpath + path);
                Scanner in = new Scanner(fr)) {
            while (in.hasNextInt()) {
                lvl = in.nextInt();
            }
        } catch (IOException fe) {
            System.out.println("File non trovato: " + folderpath + path);
        }
        return lvl;
    }
}