import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Cliente extends JFrame implements ActionListener{
    private JPanel pnlNord, pnlSud;
    private JLabel richiesta;
    private JButton certo, info;
    private ArrayList <String> req = getRichiesta("E:\\4G\\Informatica\\Progetto Pizza\\Clienti\\Richieste.txt");
    private ArrayList <String> ingredienti = getRichiesta("E:\\4G\\Informatica\\Progetto Pizza\\Clienti\\IngredientiPizza.txt");
    private int num = 0;

    public Cliente(){ 
        super("Richiesta cliente");
        setLayout(new BorderLayout());
        num = (int) (Math.random()*(req.size()));

        changeSfondo(); 

        pnlNord = new JPanel();
        pnlNord.setLayout(new FlowLayout());
        pnlNord.setBackground(Color.BLACK);
        richiesta = new JLabel("");
        richiesta.setForeground(Color.WHITE);
        richiesta.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        pnlNord.add(richiesta);

        pnlSud = new JPanel();
        pnlSud.setLayout(new FlowLayout());
        pnlSud.setBackground(Color.BLACK);
        certo = new JButton("Certo");
        certo.addActionListener(e->{
            new PizzaGui();
        });
        info = new JButton("Che cosa?");
        info.addActionListener(this);
        pnlSud.add(certo);
        pnlSud.add(info);

        add(pnlNord,BorderLayout.NORTH);
        add(pnlSud,BorderLayout.SOUTH);

        setSize(350,300);
        setLocationRelativeTo(null);
        setVisible(true);
        testoSeq(req.get(num),richiesta);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton b =(JButton)e.getSource();

        if(b==certo){
            setVisible(false);
        }else if(b==info){
            richiesta.setText(" ");
            testoSeq(ingredienti.get(num),richiesta);
        }
    }


    public static ArrayList<String> getRichiesta(String percorso) {

        ArrayList<String> req = new ArrayList<>();
        try (FileReader fr = new FileReader(percorso);
                Scanner in = new Scanner(fr)){
            while(in.hasNextLine()){
                req.add(in.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File non trovato");
        }
        return req;
    }

    public void testoSeq(String text, JLabel label){
        char[] chars = text.toCharArray();
        String tmp;
         
        for(int i=0; i<text.length(); i++){
            tmp=label.getText();
            tmp=tmp+chars[i];
            label.setText(tmp);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeSfondo(){
        ImageIcon img = new ImageIcon("E:\\4G\\Informatica\\Progetto Pizza\\Image\\SfondoPizza.jpg");
        Image newImg = img.getImage().getScaledInstance(350, 300, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newImg);
        JLabel background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,200,300);
        add(background);
    }
}