import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends JFrame {
    private JPanel pnlnord, pnlsud;
    private JLabel richiesta;
    private JButton certo, info;

    PizzaDB pDB = new PizzaDB();

    private String folderpath = "Gestione//";
    private ArrayList<String> req = new ArrayList<>();
    private ArrayList<String> listaPizze = new ArrayList<>();
    private int num = 0;
    private int numPizze;

    private DinChar dc;
    private PlayAudio pa = new PlayAudio();

    public Cliente() {
        super("Pizzeria Pummarola");
        setLayout(new BorderLayout());

        // pDB.scriviRichiesteDB(getRichiesta(folderpath+"Richieste.txt"),
        // getRichiesta(folderpath+"Pizze.txt"), getRichiesta(folderpath+"Prezzi.txt"));

        ArrayList<String[]> row = pDB.leggiRichiesteDB();
        for (int i = 0; i < row.size(); i++) {
            req.add(row.get(i)[0]);
            listaPizze.add(row.get(i)[1]);
        }

        if (readRow("level.txt") == 0) {
            num = (int) (Math.random() * 15);
        } else {
            writeRow(readRow("NRichieste.txt") + 4, "NRichieste.txt");
            num = (int) (Math.random() * readRow("NRichieste.txt"));
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
            new PizzaGui(req.get(num), listaPizze.get(num), num,
                    req.get(num) + "\n" + "(" + listaPizze.get(num) + ")",listaPizze);
        });

        info = new JButton("Che cosa?");
        info.setBackground(Color.white);
        info.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        info.addActionListener(e -> {
            richiesta.setText(" ");
            testoseq(listaPizze.get(num), richiesta);
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
    
    public void writeRow(int lvl, String path) {
        try (FileWriter fw = new FileWriter(folderpath + path)) {
            fw.write(lvl + "");
        } catch (IOException e) {
            System.out.println("File non trovato: " + folderpath + path);
        }
    }

}