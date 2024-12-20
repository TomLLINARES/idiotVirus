package Boot;

import character.Player;
import meanPart.FileEater;

import java.awt.*;

import static meanPart.FileEater.*;

public class Game implements Runnable{
    private final int FPS = 60;
    private final int UPS = 60;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Window window;
    private Playing playing;
    private Player player;
    public static boolean DELETING = false;



    public Game(){
        FileEater.Init();
        gamePanel = new GamePanel(this);
        playing = new Playing(this);
        window = new Window(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }



    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS;
        double timePerUpdate = 1000000000.0/UPS;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        long previousTime = System.nanoTime();
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;

        while(true){
            if (DELETING){
                continue;
            }
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                //TODO: update game ticks
                updates++;
                deltaU--;
            }
            if (deltaF >=1){
                gamePanel.repaint();
                //TODO: update visuals
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void render(Graphics g){
        playing.draw(g);
    }
    public void update(){
        playing.update();
    }

    public void windowFocusLost() {

    }


    public Playing getPlaying() {
        return playing;
    }
}
