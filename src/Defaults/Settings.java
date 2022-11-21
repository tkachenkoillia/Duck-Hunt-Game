package Defaults;
import GUI.Frames.*;
import Models.Ducks.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Settings {

    public static Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Files/Chalkduster.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static int maxBirds = 0;


    public static int setMaxBirds() {
        if (Game.gameDifficulty.equals("EASY")){
            maxBirds = 2;
        }
        if (Game.gameDifficulty.equals("MEDIUM")){
            maxBirds = 3;
        }
        if (Game.gameDifficulty.equals("HARD")){
            maxBirds = 4;
        }
        return maxBirds;
    }

}
