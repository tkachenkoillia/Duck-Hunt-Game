package Defaults;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MakeFrames {
    public static BufferedImage makeFrames(String source, String direction, int number) {
        int y;
        if (direction.equals("LEFT"))
            y = 0;
        else
            y = 100;

        try {
            BufferedImage allFrames = ImageIO.read(new File(source));
            return allFrames.getSubimage(100*number, y, 100, 100);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }
}
