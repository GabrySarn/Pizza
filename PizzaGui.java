import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaGui extends JFrame {

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
    private JPanel pnlN, pnlC, pnlS, pnlW;
    private JButton btnForno, btnOk, btnCestino, btnRichiesta;
    // fare progressivebar

    private String pizza = "img\\Pizze\\Pizza";
    private int level = 0;

    private TimerSec t;

    public PizzaGui( String richiesta) {
        super("Pizzeria Pummarola");
        setLayout(new BorderLayout());

        lblImg = new JLabel();

        pnlN = new JPanel(new FlowLayout());
        pnlC = new JPanel(new GridBagLayout());
        pnlS = new JPanel(new GridLayout(1, 3));
        pnlW = new JPanel(new BorderLayout());

        btnOk = new JButton("Taglia e Consegna");
        btnOk.setIcon(new ImageIcon("img\\Cmp\\TagliaPizza.png"));
        btnOk.setBackground(Color.GRAY);
        btnOk.setForeground(Color.WHITE);

        btnForno = new JButton("Cuoci in forno");
        btnForno.setIcon(new ImageIcon("img\\Cmp\\fuoco.png"));
        btnForno.setBackground(Color.GRAY);
        btnForno.setForeground(Color.WHITE);

        btnCestino = new JButton(new ImageIcon("img\\Cmp\\cestino.png"));
        btnCestino.addActionListener(e -> {
            lblImg.setIcon(null);
            pizza = "img\\Pizze\\Pizza";
        });
        btnCestino.setBackground(Color.yellow);
        btnCestino.setMargin(new Insets(0, 0, 0, 0));

        btnRichiesta = new JButton(new ImageIcon("img\\Cmp\\Richiesta.png"));
        btnRichiesta.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(),
                        richiesta,
                        "Richiesta",
                        JOptionPane.INFORMATION_MESSAGE);
        });
        btnRichiesta.setBackground(Color.yellow);
        btnRichiesta.setMargin(new Insets(0, 0, 0, 0));

        t = new TimerSec(lblTimer);

        btnForno.addActionListener(e -> {
            lblTimer.setText("10");
            t = new TimerSec(lblTimer);
            t.start();
        });

        lblTimer = new JLabel("");
        lblTimer.setBackground(Color.GRAY);
        lblTimer.setForeground(Color.WHITE);
        lblTimer.setHorizontalAlignment(JLabel.CENTER);
        lblTimer.setFont(new Font("Courier", Font.BOLD, 20));

        btnImpasti = setBtn(btnImpasti, impasti);
        btnSalse = setBtn(btnSalse, salse);
        btnFormaggi = setBtn(btnFormaggi, formaggi);
        btnCarni = setBtn(btnCarni, carni);
        btnVerdure = setBtn(btnVerdure, verdure);

        btnOk.addActionListener(e -> {
            if (lblTimer.getText().equals("Pronta!")) {
                lblTimer.setText("");
                writePizza(pizza);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "La Pizza non è ancora pronta!!!",
                        "Aspetta un attimo",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        pnlC.setBackground(Color.lightGray);
        pnlC.add(lblImg);

        pnlN.add(btnImpasti.get(level));
        pnlN.add(btnSalse.get(level));
        pnlN.add(btnFormaggi.get(level));
        pnlN.add(btnCarni.get(level));
        pnlN.add(btnVerdure.get(level));

        pnlS.setBackground(Color.GRAY);
        pnlS.add(btnForno);
        pnlS.add(lblTimer);
        pnlS.add(btnOk);

        pnlW.add(btnCestino, BorderLayout.SOUTH);
        pnlW.add(btnRichiesta, BorderLayout.NORTH);

        pnlN.setBackground(Color.YELLOW);
        pnlW.setBackground(Color.yellow);

        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlS, BorderLayout.SOUTH);
        add(pnlW, BorderLayout.WEST);

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
            btn.get(i).setMargin(new Insets(0, 0, 0, 0));
            btn.get(i).addActionListener(e -> {
                pizza += " " + e.getActionCommand();
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

    public void writePizza(String ordine) {
        String[] o = new String[10];
        ordine = ordine.replaceAll("[^\\w+]", ",");
        o = ordine.split(",");
        ordine = "";
        for (int i = 2; i < o.length && o[i] != null; i++) {
            ordine += o[i] + " ";
        }
        try (FileWriter fw = new FileWriter("ordine.txt")) {
            fw.write(ordine);
        } catch (IOException e) {
            System.out.println("File non trovato");
        }
    }
}