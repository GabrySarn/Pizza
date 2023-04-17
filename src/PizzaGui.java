import javax.swing.*;
import java.awt.*;

public class PizzaGui extends JFrame implements ActionListener{

    private String folderpath = "Condimenti\\";

    private ArrayList<JButton> btnCarni = readFile(folderpath + "Carni.txt");
    private ArrayList<JButton> btnVerdure = readFile(folderpath + "Verdure.txt");
    private ArrayList<JButton> btnImpasti = readFile(folderpath + "Impasti.txt");
    private ArrayList<JButton> btnSalse = readFile(folderpath + "Salse.txt");

    private JLabel lblImg;


    public PizzaGui(){
        super("Pummarola");
        setLayout(new FlowLayout());

        
        for(int i = 0;i<btnCarni.length;i++){
            btnCarni[i].addActionListener(this);
            add(btnCarni[i]);
        }
        for(int i = 0;i<btnVerdure.length;i++){
            btnVerdure[i].addActionListener(this);
            add(btnVerdure[i]);
        }
        for(int i = 0;i<btnSalse.length;i++){
            btnSalse[i].addActionListener(this);
            add(btnSalse[i]);
        }
        for(int i = 0;i<btnImpasti.length;i++){
            btnImpasti[i].addActionListener(this);
            add(btnImpasti[i]);
        }

        lblImg = new JLabel("Immagine");







        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    public void ActionPerformed(ActionEvent e){


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