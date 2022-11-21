package Models.Others;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cloud {
    static int x;
    static int y;

    static BufferedImage cloudImage;

    public Cloud(int x, int y){
        Cloud.x = x;
        Cloud.y = y;
    }

    public int getX(){
        return x;
    }

    public static BufferedImage getImage(){
        try {
            cloudImage = ImageIO.read(new File("Files/Images/Others/Cloud.png"));
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return cloudImage;
    }

    public static void move(){
        Cloud.x += 5;
    }
}
