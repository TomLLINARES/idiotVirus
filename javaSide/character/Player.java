package character;

import java.awt.*;

import static Help.HelpMethods.FileNames.*;

public class Player extends Entities{

    private float playerSpeed = 10.0f;
    private boolean left, right;

    public Player(int x, int y){
        super(x,y, PLAYER_SPRITE);
    }


    public void update(){
        updatePos();
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(texture,x,y,64,64,null);
    }

    public void resetDirBools(){
        left = false;
        right = false;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    private void updatePos(){
        if (left && right){
            return;
        }
        if (!left && !right){
            return;
        }
        if (left){
            x -= (int) playerSpeed;
            hitbox.x -= playerSpeed;
            if (x<0){
                x = 0;
                hitbox.x = 0;
            }
            return;
        }
        x += playerSpeed;
        hitbox.x += playerSpeed;
        if (x>1280-64){
            x = 1280-64;
            hitbox.x = 1280-64;
        }

    }

    public void catchFile(FallingItems fi){
        fi.gotCaught();
    }
}
