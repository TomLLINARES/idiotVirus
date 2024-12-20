package Boot;
import Help.HelpMethods;
import character.FallingItems;
import character.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static Help.HelpMethods.FileNames.*;

public class Playing extends State implements StateMethods {

    private BufferedImage backgroundImg;
    private Player player;
    private static int FILE_SPAWN_SPEED = 100;
    private int timeToSpawn;
    public static int SAVED_SCORE = 0;
    public static int LOST_SCORE = 0;

    private ArrayList<FallingItems> fileArray;
    public Playing(Game game) {
        super(game);
        timeToSpawn = 0;
        player = new Player((1280/2)-32,600);
        backgroundImg = HelpMethods.GetImage(BACKGROUND_IMG);
        fileArray = new ArrayList<>();
    }

    @Override
    public void update() {
        timeToSpawn ++;
        if (timeToSpawn==FILE_SPAWN_SPEED){
            spawnFile();
            timeToSpawn = 0;
            FILE_SPAWN_SPEED --;
            if (FILE_SPAWN_SPEED<10){
                FILE_SPAWN_SPEED = 10;
            }
        }
        player.update();
        //System.out.println("ENCORE LA, arraysize is: " + fileArray.size());
        for (int i = 0; i<fileArray.size();i++){
            fileArray.get(i).update();
            if (fileArray.get(i).getHitbox().intersects(player.getHitbox())){
                fileArray.get(i).gotCaught();
                fileArray.remove(i);
            } else {
                if (!fileArray.get(i).isInPlay()){
                    fileArray.remove(i);
                }
            }

        }
    }
    private void spawnFile(){
        Random random = new Random();
        fileArray.add(new FallingItems(random.nextInt(0,1280-64)));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0,1280,720,null);
        player.draw(g);
        for (int i = 0; i<fileArray.size();i++){
            fileArray.get(i).draw(g);
        }
        Font font = new Font("Arial", Font.BOLD, 24);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Saved files: " + SAVED_SCORE, 100, 100);
        g.drawString("Lost files: " + LOST_SCORE, 100, 200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                //player.catchFile();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                player.setRight(false);
                break;
        }
    }


}
