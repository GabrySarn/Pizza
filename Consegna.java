import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Consegna extends JFrame {
    private JPanel pnlNord, pnlSud;
    private JLabel background, richiesta, lblCassa;
    private JButton consegna, prossimo;

    private String folderPath = "Gestione//";
    private ArrayList<String> commBelli = getCommenti(folderPath + "CommentiBelli.txt");
    private ArrayList<String> commBrutti = getCommenti(folderPath + "CommentiBrutti.txt");
    private int num = 0;

    private String s, ric, ing;
    private double prezzo = 0;
    private double mancia;

    private Mp3Player mp3P;

    private DinChar dc;
    private PizzaDB pDB = new PizzaDB();

    public Consegna(String s, String ing, String ric, int numPrezzo) {
        super("Pizzeria Pummarola");
        this.s = s;
        this.ric = ric;
        this.ing = ing;

        prezzo = getPrezzo(numPrezzo);

        setLayout(new BorderLayout());
        num = (int) (Math.random() * (commBelli.size()));

        background = new JLabel(new ImageIcon("Immagini//Design//BanconeFinale.jpg"), JLabel.CENTER);
        add(background);

        lblCassa = new JLabel();
        lblCassa.setForeground(Color.black);
        lblCassa.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblCassa.setIcon(new ImageIcon("Immagini//Components//cassa.png"));

        pnlNord = new JPanel();
        pnlNord.setLayout(new BorderLayout());
        pnlNord.setBackground(new Color(166, 251, 178));

        richiesta = new JLabel(" ");
        richiesta.setForeground(Color.black);
        richiesta.setFont(new Font("Bahnschrift", Font.BOLD, 20));

        pnlSud = new JPanel();
        pnlSud.setLayout(new FlowLayout());
        pnlSud.setBackground(new Color(166, 251, 178));

        consegna = new JButton("Consegna");
        consegna.setBackground(Color.WHITE);
        consegna.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        consegna.addActionListener(e -> {
            gradimento();
            lblCassa.setText(" ");
            lblCassa.setText("" + pDB.leggiDB() + "€" + "(+" + prezzo + "/" + mancia + ")");
        });

        prossimo = new JButton("Prossimo cliente");
        prossimo.setBackground(Color.WHITE);
        prossimo.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        prossimo.addActionListener(e -> {
            if (richiesta.getText().equals(" ")) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "La Pizza non è stata consegnata!",
                        "Consegna la pizza...",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                dispose();
                new Cliente();
            }
        });

        pnlSud.add(consegna);
        pnlSud.add(prossimo);
        lblCassa.setText("" + pDB.leggiDB() + "€");

        JScrollPane sp = new JScrollPane(richiesta);
        pnlNord.add(lblCassa, BorderLayout.WEST);
        pnlNord.add(richiesta);

        add(pnlNord, BorderLayout.NORTH);
        add(pnlSud, BorderLayout.SOUTH);

        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void testoSeq(String text, JLabel label) {
        dc = new DinChar(label, text);
        dc.start();

    }

    public void gradimento() {
        if (s.equals(ing)) {
            mp3P = new Mp3Player("Audio//CassaSound.mp3");
            mp3P.play();
            mancia = (double) (Math.random() * 5 + 0.5);
            mancia = Math.round(mancia * 100.0) / 100.0;
            testoSeq(commBelli.get(num), richiesta);
            pDB.scriviDB(ric, prezzo, mancia);
        } else {
            mp3P = new Mp3Player("Audio//Ops.mp3");
            mp3P.play();
            mancia = 0;
            prezzo = 0;
            testoSeq(commBrutti.get(num), richiesta);
            pDB.scriviDB(ric, 0, mancia);
        }

    }

    public double getPrezzo(int numPrezzo) {
        ArrayList<Double> prezzi = pDB.leggiPrezziDB();
        return prezzi.get(numPrezzo);
    }

    public static ArrayList<String> getCommenti(String percorso) {

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
}
