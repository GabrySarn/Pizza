import java.awt.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Cliente extends JFrame {
    private JPanel pnlNord, pnlSud;
    private JLabel richiesta;
    private JButton certo, info;

    private String folderPath = "Gestione//";
    private ArrayList<String> req = getRichiesta(folderPath + "Richieste.txt");
    private ArrayList<String> ingredienti = getRichiesta(folderPath + "Pizze.txt");
    private int num = 0;

    public Cliente() {
        super("Pizzeria Pummarola");
        setLayout(new BorderLayout());
        num = (int) (Math.random() * (req.size()));

        JLabel background = new JLabel(new ImageIcon("img//design//BanconeIniziale.jpg"), JLabel.CENTER);
        add(background);

        pnlNord = new JPanel();
        pnlNord.setLayout(new FlowLayout());
        pnlNord.setBackground(Color.BLACK);

        richiesta = new JLabel("");
        richiesta.setForeground(Color.WHITE);
        richiesta.setFont(new Font("Bahnschrift", Font.BOLD, 25));

        pnlNord.add(richiesta);

        pnlSud = new JPanel();
        pnlSud.setLayout(new FlowLayout());
        pnlSud.setBackground(Color.BLACK);

        certo = new JButton("Certo");
        certo.setBackground(Color.WHITE);
        certo.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        certo.addActionListener(e -> {
            setVisible(false);
            new PizzaGui(req.get(num), ingredienti.get(num), num);
        });

        info = new JButton("Che cosa?");
        info.setBackground(Color.WHITE);
        info.setFont(new Font("Bahnschrift", Font.BOLD, 20));
        info.addActionListener(e -> {
            richiesta.setText(" ");
            testoSeq(ingredienti.get(num), richiesta);
        });

        pnlSud.add(certo);
        pnlSud.add(info);

        add(pnlNord, BorderLayout.NORTH);
        add(pnlSud, BorderLayout.SOUTH);

        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        testoSeq(req.get(num), richiesta);
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
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}