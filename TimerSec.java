import javax.swing.JLabel;

public class TimerSec extends Thread {
    private JLabel txt;
    private int i = 15;

    public TimerSec(JLabel txt) {
        this.txt = txt;
    }

    public void run() {
        synchronized (txt) {
            while (i > 0) {
                i = Integer.parseInt(txt.getText()) - 1;
                txt.setText("" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (txt.getText().equals("0")) {
                txt.setText("Prontaaa");
            }
        }

    }

}
