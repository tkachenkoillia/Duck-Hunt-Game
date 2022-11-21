package Models.Others;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tree {
    public static int x = 74;
    public static int y = 150;

    static BufferedImage treeImage;


    public static BufferedImage getImage(){
        try {
            treeImage = ImageIO.read(new File("Files/Images/Others/Tree.png"));
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return treeImage;
    }
}
