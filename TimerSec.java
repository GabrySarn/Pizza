import javax.swing.*;

public class TimerSec extends Thread {
    private JLabel txt;
    private JProgressBar jpb;
    private Mp3Player mp3P = new Mp3Player("Audio//AudioForno.mp3");

    public TimerSec(JLabel txt, JProgressBar jpb) {
        this.txt = txt;
        this.jpb = jpb;
    }

    public void run() {
        mp3P.play();
        synchronized (txt) {
            int j = 0;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (j < 100) {
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
            mp3P.stop();
        }

    }

}
