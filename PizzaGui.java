import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PizzaGui extends JFrame implements ActionListener {

    private String folderpath = "Condimenti\\";

    private ArrayList<JButton> btnCarni = new ArrayList<>();
    private ArrayList<String> carni = readFile(folderpath + "Carni.txt");
    private ArrayList<JButton> btnVerdure = new ArrayList<>();
    private ArrayList<String> verdure = readFile(folderpath + "Verdure.txt");
    private ArrayList<JButton> btnImpasti = new ArrayList<>();
    private ArrayList<String> impasti = readFile(folderpath + "Impasti.txt");
    private ArrayList<JButton> btnSalse = new ArrayList<>();
    private ArrayList<String> salse = readFile(folderpath + "Salse.txt");

    private JLabel lblImg;

    public PizzaGui() {
        super("Pummarola");
        setLayout(new FlowLayout());

        for (int i = 0; i < carni.size(); i++) {
            btnCarni.add(new JButton(carni.get(i)));
            //btnCarni.get(i).addActionListener(null);
            add(btnCarni.get(i));
        }
        for (int i = 0; i < verdure.size(); i++) {
            btnVerdure.add(new JButton(verdure.get(i)));
            //btnVerdure.get(i).addActionListener(null);
            add(btnVerdure.get(i));
        }
        for (int i = 0; i < salse.size(); i++) {
            btnSalse.add(new JButton(salse.get(i)));
            //btnSalse.get(i).addActionListener(null);
            add(btnSalse.get(i));
        }
        for (int i = 0; i < impasti.size(); i++) {
            btnImpasti.add(new JButton(impasti.get(i)));
            //btnImpasti.get(i).addActionListener(null);
            add(btnImpasti.get(i));
        }

        lblImg = new JLabel("Immagine");
        add(lblImg);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}