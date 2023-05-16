import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaGui extends JFrame {

    private String folderpath = "Gestione\\";

    private ArrayList<JButton> btnCondimenti = new ArrayList<>();
    private ArrayList<String> ingredienti = readFile(folderpath + "Condimenti.txt");
    private ArrayList<String> listaPizze = readFile(folderpath + "Pizze.txt");

    private JLabel lblImg, lblTimer;
    private JPanel pnlN, pnlC, pnlS, pnlW;
    private JButton btnForno, btnOk, btnCestino, btnRichiesta;
    private JProgressBar jpb;

    private String pizza = "img\\Pizze\\Pizza";
    private int level = getLevel();

    private TimerSec t;

    ArrayList<String> tmp = new ArrayList<>();

    public PizzaGui(String richiesta, String ing,int num) {
        super("Pizzeria Pummarola");
        setLayout(new BorderLayout());

        jpb = new JProgressBar();
        jpb.setValue(0);
        jpb.setStringPainted(true);
        jpb.setBackground(Color.gray);
        //jpb.setForeground(Color.DARK_GRAY);
        jpb.setFont(new Font("MV Boli", Font.BOLD, 20));
        jpb.setForeground(new Color(171,205,239));

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
        btnCestino.setBackground(new Color(171,205,239));
        btnCestino.setMargin(new Insets(0, 0, 0, 0));

        btnRichiesta = new JButton(new ImageIcon("img\\Cmp\\Richiesta.png"));
        btnRichiesta.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(),
                    richiesta,
                    "Richiesta",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        btnRichiesta.setBackground(new Color(171,205,239));
        btnRichiesta.setMargin(new Insets(0, 0, 0, 0));

        t = new TimerSec(lblTimer, jpb);

        btnForno.addActionListener(e -> {
            // lblTimer.setText("10");
            t = new TimerSec(lblTimer, jpb);
            t.start();
        });

        lblTimer = new JLabel("");
        lblTimer.setBackground(Color.GRAY);
        lblTimer.setForeground(Color.WHITE);
        lblTimer.setHorizontalAlignment(JLabel.CENTER);
        lblTimer.setFont(new Font("Courier", Font.BOLD, 20));

        btnCondimenti = setBtn(btnCondimenti, ingredienti);

        btnOk.addActionListener(e -> {
            if (lblTimer.getText().equals("Pronta!")) {
                lblTimer.setText("");
                setVisible(false);
                new Consegna(writePizza(pizza), ing, richiesta,num);
                setLevel(level + 1);
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "La Pizza non è ancora pronta!!!",
                        "Aspetta un attimo",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        pnlC.setBackground(Color.lightGray);
        pnlC.add(lblImg);

        for (int i = 0; i <= level + 5; i++) {
            pnlN.add(btnCondimenti.get(i));
        }

        pnlS.setBackground(Color.GRAY);
        pnlS.add(btnForno);
        pnlS.add(jpb);
        pnlS.add(lblTimer);
        pnlS.add(btnOk);

        pnlW.add(btnCestino, BorderLayout.SOUTH);
        pnlW.add(btnRichiesta, BorderLayout.NORTH);

        pnlN.setBackground(new Color(171,205,239));
        pnlW.setBackground(new Color(171,205,239));

        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlS, BorderLayout.SOUTH);
        add(pnlW, BorderLayout.WEST);

        setSize(1000, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public ArrayList<JButton> setBtn(ArrayList<JButton> btn, ArrayList<String> ing) {
        for (int i = 0; i < ing.size(); i++) {
            btn.add(new JButton());
            btn.get(i).setBackground(new Color(171,205,239));
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
            System.out.println("File non trovato: " + percorso);
        }
        return list;
    }

    public String writePizza(String ordine) {
        String[] o = new String[10];
        ordine = ordine.replaceAll("[^\\w+]", ",");
        o = ordine.split(",");
        ordine = "";
        for (int i = 2; i < o.length && o[i] != null; i++) {
            ordine += o[i];
            if (i < o.length - 1) {
                ordine += " ";
            }
        }
        try (FileWriter fw = new FileWriter(folderpath + "Ordine.txt")) {
            fw.write(ordine);
        } catch (IOException e) {
            System.out.println("File non trovato: " + folderpath + "Ordine.txt");
        }

        return ordine;
    }

    public int getLevel() {
        int lvl = 0;
        try (FileReader fr = new FileReader(folderpath + "level.txt");
                Scanner in = new Scanner(fr)) {
            while (in.hasNextInt()) {
                lvl = in.nextInt();
            }
        } catch (IOException fe) {
            System.out.println("File non trovato: " + folderpath + "level.txt");
        }
        return lvl;
    }

    public void setLevel(int lvl) {
        try (FileWriter fw = new FileWriter(folderpath + "level.txt")) {
            fw.write(lvl + "");
        } catch (IOException e) {
            System.out.println("File non trovato: " + folderpath + "level.txt");
        }
    }

}