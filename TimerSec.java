import javax.swing.*;

public class TimerSec extends Thread {
    private JLabel txt;
    private JProgressBar jpb;

    public TimerSec(JLabel txt, JProgressBar jpb) {
        this.txt = txt;
        this.jpb = jpb;
    }

    public void run() {
        synchronized (txt) {
            // int i = Integer.parseInt(txt.getText());
            int j = 0;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (j < 100) {
                // i = Integer.parseInt(txt.getText()) - 1;
                // txt.setText("" + i);
                jpb.setValue(j + 10);
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                j += 10;
            }
            jpb.setValue(j + 10);
            txt.setText("Pronta!");
        }

    }

}