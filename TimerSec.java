import javax.swing.JLabel;

public class TimerSec extends Thread {
    private JLabel txt;

    public TimerSec(JLabel txt) {
        this.txt = txt;
    }

    public void run() {
        synchronized (txt) {
            int i = Integer.parseInt(txt.getText());

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (i > 1) {
                i = Integer.parseInt(txt.getText()) - 1;
                txt.setText("" + i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            txt.setText("Pronta!");
        }

    }

}
