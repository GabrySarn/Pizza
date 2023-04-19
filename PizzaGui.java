import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaGui extends JFrame{

    private String folderpath = "Cond\\";

    private ArrayList<JButton> btnCarni = new ArrayList<>();
    private ArrayList<String> carni = readFile(folderpath + "Carni.txt");
    private ArrayList<JButton> btnVerdure = new ArrayList<>();
    private ArrayList<String> verdure = readFile(folderpath + "Verdure.txt");
    private ArrayList<JButton> btnImpasti = new ArrayList<>();
    private ArrayList<String> impasti = readFile(folderpath + "Impasti.txt");
    private ArrayList<JButton> btnSalse = new ArrayList<>();
    private ArrayList<String> salse = readFile(folderpath + "Salse.txt");
    private ArrayList<JButton> btnFormaggi = new ArrayList<>();
    private ArrayList<String> formaggi = readFile(folderpath + "Formaggi.txt");

    private JLabel lblImg, lblTimer;
    private JPanel pnlN, pnlC, pnlE;
    private JButton btnForno, btnOk;
    // fare progressivebar

    private String pizza = "img\\Pizze\\Pizza";
    private int level = 0;

    public PizzaGui() {
        super("Pummarola");
        setLayout(new BorderLayout());

        pnlN = new JPanel(new FlowLayout());
        pnlC = new JPanel(new FlowLayout());
        pnlE = new JPanel(new GridLayout(1, 3));

        btnOk = new JButton("Taglia e Consegna");
        btnOk.setIcon(new ImageIcon("img\\Cmp\\TagliaPizza.png"));
        btnOk.setBackground(Color.GRAY);
        btnOk.setForeground(Color.WHITE);
        btnForno = new JButton("Cuoci in forno");
        btnForno.setIcon(new ImageIcon("img\\Cmp\\fuoco.png"));
        btnForno.setBackground(Color.GRAY);
        btnForno.setForeground(Color.WHITE);

        lblTimer = new JLabel("");
        lblTimer.setBackground(Color.GRAY);
        lblTimer.setForeground(Color.WHITE);

        btnImpasti = setBtn(btnImpasti, impasti);
        btnSalse = setBtn(btnSalse, salse);
        btnFormaggi = setBtn(btnFormaggi, formaggi);
        btnCarni = setBtn(btnCarni, carni);
        btnVerdure = setBtn(btnVerdure, verdure);

        lblImg = new JLabel();

        pnlC.setBackground(Color.lightGray);
        pnlC.add(lblImg);

        pnlN.add(btnImpasti.get(level));
        pnlN.add(btnSalse.get(level));
        pnlN.add(btnCarni.get(level));
        pnlN.add(btnVerdure.get(level));
        pnlN.add(btnFormaggi.get(level));

        pnlE.setBackground(Color.GRAY);
        pnlE.add(btnForno);
        pnlE.add(lblTimer);
        pnlE.add(btnOk);

        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlE, BorderLayout.SOUTH);

        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public ArrayList<JButton> setBtn(ArrayList<JButton> btn, ArrayList<String> ing) {
        for (int i = 0; i < ing.size(); i++) {
            btn.add(new JButton());
            btn.get(i).setActionCommand(ing.get(i));
            btn.get(i).setIcon(new ImageIcon("img\\Cond\\" + ing.get(i) + ".png"));
            btn.get(i).addActionListener(e -> {
                pizza += " " + e.getActionCommand();
                System.out.println(pizza + ".png");
                lblImg.setIcon(new ImageIcon(pizza + ".png"));
            });
        }

        return btn;
    }

    public static ArrayList<String> readFile(String percorso) {
        ArrayList<String> list = new ArrayList<>();
        try (FileReader fr = new FileReader(percorso);
                Scanner in = new Scanner(fr)) {
            while (in.hasNextLine()) {
                list.add(in.nextLine());
            }
        } catch (IOException fe) {
            System.out.println("File non trovato");
        }
        return list;
    }

    public static void main(String[] args) {
        new PizzaGui();
    }

}