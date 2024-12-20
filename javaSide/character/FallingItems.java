package character;

import Boot.Playing;
import Help.HelpMethods;

import java.awt.*;
import java.util.Random;

import Boot.Playing.*;
import meanPart.FileEater;

import static Help.HelpMethods.FileNames.FALLING_FILE_SPRITE;

public class FallingItems extends Entities{
    private float ySpeed = 5f;
    private boolean inPlay = true;
    public FallingItems(int x){
        super(x,0, FALLING_FILE_SPRITE);
    }

    public void gotCaught(){
        Playing.SAVED_SCORE ++;
        inPlay = false;
    }
    public void fileLost(){
        Playing.LOST_SCORE ++;
        inPlay = false;

        FileEater.Delete();
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(texture,x,y,64,64,null);
    }

    @Override
    public void update(){
        y += (int) ySpeed;
        hitbox.y += (int) ySpeed;
        if (y>720){
           fileLost();
        }
    }

    public boolean isInPlay() {
        return inPlay;
    }
}
