package Help;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;



public class HelpMethods {
    public static class FileNames {
        public static final String BACKGROUND_IMG = "/resources/background.jpg";
        public static final String PLAYER_SPRITE = "/resources/playerSprite.png";
        public static final String FALLING_FILE_SPRITE = "/resources/fallingFile.png";
    }

    public static BufferedImage GetImage(String filename){
        BufferedImage img = null;
        InputStream is = HelpMethods.class.getResourceAsStream(filename);
        try{
            img = ImageIO.read(is);
        }   catch (IOException e){
            e.printStackTrace();
        }   finally {
            try{
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
}
