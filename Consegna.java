import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Consegna extends JFrame {
    private JPanel pnlNord, pnlSud;
    private JLabel richiesta, lblCassa;
    private JButton consegna, prossimo;
    private ArrayList<String> commBelli = getRichiesta("Gestione//CommentiBelli.txt");
    private ArrayList<String> commBrutti = getRichiesta("Gestione//CommentiBrutti.txt");
    private int num = 0;

    private String s;
    private String ric;
    private String ing;

    private PizzaDB pizzaDB = new PizzaDB();

    public Consegna(String s, String ing, String ric) {
        super("Pizzeria Pummarola");
        this.s = s;
        this.ric = ric;
        this.ing = ing;

        setLayout(new BorderLayout());
        num = (int) (Math.random() * (commBelli.size()));

        JLabel background = new JLabel(new ImageIcon("img//design//BanconeFinale.jpg"), JLabel.CENTER);
        add(background);

        lblCassa = new JLabel();
        lblCassa.setForeground(Color.black);
        lblCassa.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        lblCassa.setIcon(new ImageIcon("img//Cmp//cassa.png"));

        pnlNord = new JPanel();
        pnlNord.setLayout(new BorderLayout());
        pnlNord.setBackground(new Color(166, 251, 178));

        richiesta = new JLabel(" ");
        richiesta.setForeground(Color.black);
        richiesta.setFont(new Font("Bahnschrift", Font.BOLD, 25));

        
        pnlSud = new JPanel();
        pnlSud.setLayout(new FlowLayout());
        pnlSud.setBackground(new Color(166, 251, 178));

        consegna = new JButton("Consegna");
        consegna.setBackground(Color.WHITE);
        consegna.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        consegna.addActionListener(e -> {
            gradimento();
            lblCassa.setText("" + pizzaDB.leggiDB() + "€");
        });

        prossimo = new JButton("Prossimo cliente");
        prossimo.setBackground(Color.WHITE);
        prossimo.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        prossimo.addActionListener(e -> {
            dispose();
            new Cliente();
        });

        pnlSud.add(consegna);
        pnlSud.add(prossimo);
        lblCassa.setText("" + pizzaDB.leggiDB() + "€");
        pnlNord.add(lblCassa, BorderLayout.WEST);
        pnlNord.add(richiesta);


        add(pnlNord, BorderLayout.NORTH);
        add(pnlSud, BorderLayout.SOUTH);

        setSize(600, 450);
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
            System.out.println("File non trovato");
        }
        return req;
    }

    public void testoSeq(String text, JLabel label) {
        char[] chars = text.toCharArray();
        String tmp;

        for (int i = 0; i < text.length(); i++) {
            tmp = label.getText();
            tmp += chars[i];
            label.setText(tmp);
            try {
                TimeUnit.MILLISECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void gradimento() {
        float mancia;
        System.out.println(s);
        System.out.println(ing);
        if (s.equals(ing)) {
            mancia = (float) (Math.random() * 5 + 0.5);
            testoSeq(commBelli.get(num), richiesta);
            pizzaDB.scriviDB(ric, 15, mancia);
        } else {
            mancia = 0;
            testoSeq(commBrutti.get(num), richiesta);
            pizzaDB.scriviDB(ric, 0, mancia);
        }
        
    }
}
