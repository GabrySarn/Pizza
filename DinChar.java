import javax.swing.*;

public class DinChar extends Thread {
    private JLabel lbl;
    private String s;

    public DinChar(JLabel lbl, String s) {
        this.lbl = lbl;
        this.s = s;
    }

    public void run() {
        char[] c = s.toCharArray();
        String cs = "";
        int i = 0;
        while (i < s.length()) {
            cs += c[i];
            lbl.setText(cs);
            i++;
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
