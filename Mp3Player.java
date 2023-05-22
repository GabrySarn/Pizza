import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Mp3Player {
    private Player player;
    private FileInputStream fis;
    private BufferedInputStream bis;

    private String p;

    public Mp3Player(String p) {
        this.p = p;
    }

    public void play() {
        try {
            fis = new FileInputStream(p);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Thread per la riproduzione dell'MP3
        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

    }

    public void stop() {
        try {
            player.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}