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
    private ArrayList<String> listaPizze ;

    private JLabel lblImg, lblTimer;
    private JPanel pnlN, pnlC, pnlS, pnlW;
    private JButton btnForno, btnOk, btnCestino, btnRichiesta;
    private JProgressBar jpb;

    private int level = readRow("level.txt");

    private TimerSec t;

    private String selected = "Pizza";
    private String result = "";

    public PizzaGui(String richiesta, String ing, int num, String scontrino,ArrayList<String> listaPizze) {
        super("Pizzeria Pummarola");
        setLayout(new BorderLayout());

        this.listaPizze = listaPizze;

        jpb = new JProgressBar();
        jpb.setValue(0);
        jpb.setStringPainted(true);
        jpb.setBackground(Color.gray);
        jpb.setFont(new Font("MV Boli", Font.BOLD, 20));
        jpb.setForeground(new Color(171, 205, 239));

        lblImg = new JLabel();

        pnlN = new JPanel(new FlowLayout());
        pnlC = new JPanel(new GridBagLayout());
        pnlS = new JPanel(new GridLayout(1, 3));
        pnlW = new JPanel(new BorderLayout());

        btnOk = new JButton("Taglia e Consegna");
        btnOk.setIcon(new ImageIcon("Immagini\\Components\\TagliaPizza.png"));
        btnOk.setBackground(Color.GRAY);
        btnOk.setForeground(Color.WHITE);

        btnForno = new JButton("Cuoci in forno");
        btnForno.setIcon(new ImageIcon("Immagini\\Components\\fuoco.png"));
        btnForno.setBackground(Color.GRAY);
        btnForno.setForeground(Color.WHITE);
        btnForno.setFont(new Font("Bahnschrift", Font.BOLD, 12));

        btnCestino = new JButton(new ImageIcon("Immagini\\Components\\cestino.png"));
        btnCestino.addActionListener(e -> {
            lblImg.setIcon(null);
            selected = "Pizza";
        });
        btnCestino.setBackground(new Color(171, 205, 239));
        btnCestino.setMargin(new Insets(0, 0, 0, 0));

        btnRichiesta = new JButton(new ImageIcon("Immagini\\Components\\Richiesta.png"));
        btnRichiesta.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(),
                    scontrino,
                    "Richiesta",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        btnRichiesta.setBackground(new Color(171, 205, 239));
        btnRichiesta.setMargin(new Insets(0, 0, 0, 0));

        t = new TimerSec(lblTimer, jpb);

        btnForno.addActionListener(e -> {
            if (!t.isAlive()) {
                t = new TimerSec(lblTimer, jpb);
                t.start();
            }

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
                new Consegna(result, ing, richiesta, num);
                if (readRow("Day.txt") == 4) {
                    writeRow(level + 1, "level.txt");
                    writeRow(0, "Day.txt");
                } else
                    writeRow(readRow("Day.txt") + 1, "Day.txt");
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

        pnlN.setBackground(new Color(171, 205, 239));
        pnlW.setBackground(new Color(171, 205, 239));

        add(pnlN, BorderLayout.NORTH);
        add(pnlC, BorderLayout.CENTER);
        add(pnlS, BorderLayout.SOUTH);
        add(pnlW, BorderLayout.WEST);

        setSize(900, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }
    

    public ArrayList<JButton> setBtn(ArrayList<JButton> btn, ArrayList<String> ing) {
        for (int i = 0; i < ing.size(); i++) {
            btn.add(new JButton());
            btn.get(i).setBackground(new Color(171, 205, 239));
            btn.get(i).setActionCommand(ing.get(i));
            btn.get(i).setIcon(new ImageIcon("Immagini\\Condimenti\\" + ing.get(i) + ".png"));
            btn.get(i).setMargin(new Insets(0, 0, 0, 0));
            btn.get(i).addActionListener(e -> {
                if (selected.contains(e.getActionCommand())) {
                    JOptionPane.showMessageDialog(this, "Ingrediente già inserito"); // messaggio di controllo
                } else {
                    selected += " " + e.getActionCommand();
                    result = writePizza(selected);
                    if (result == null) {
                        JOptionPane.showMessageDialog(this, "Pizza non disponibile"); // messaggio di controllo
                        selected = "Pizza";
                        lblImg.setIcon(null);
                    } else {
                        lblImg.setIcon(new ImageIcon("Immagini\\Pizze\\" + result + ".png"));
                    }
                }
            });
        }

        return btn;
    }

    // metodo di controllo ingredienti per selezione delle immagini
    public String writePizza(String ingredients) {
        if (ingredients.contains("Normale")) {
            String row1;
            int index = -1;
            int count;
            String[] ing = ingredients.split(" ");
            for (int i = 0; i < listaPizze.size(); i++) {
                row1 = listaPizze.get(i);
                count = 0;
                for (int j = 0; j < ing.length; j++) {
                    if (row1.contains(ing[j]) && row1.length() == ingredients.length()) {
                        count++;
                    }
                }
                if (count == ing.length) {
                    index = i;
                }
            }

            if (index < 0) {
                return null;
            }
            return listaPizze.get(index);
        } else {
            JOptionPane.showMessageDialog(PizzaGui.this, "Selezionare prima l'impasto"); // messaggio di controllo
            selected = "Pizza"; // rimposto la variabile che tiene conto degli ingredienti
        }
        return "";
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