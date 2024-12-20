package character;

import Help.HelpMethods;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Entities {
    protected Rectangle2D.Float hitbox;
    protected BufferedImage texture;
    protected int x, y;
    public Rectangle2D getHitbox() {
        return hitbox;
    }
    public Entities(int x, int y, String textName){
        this.x = x;
        this.y = y;
        texture = HelpMethods.GetImage(textName);
        initHitbox();
    }
    protected void initHitbox(){
        hitbox = new Rectangle2D.Float(x,y,64,64);
    }




    public abstract void update();

    public abstract void draw(Graphics g);
}
