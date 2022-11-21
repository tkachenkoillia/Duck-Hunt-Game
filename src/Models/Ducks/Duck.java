package Models.Ducks;

import java.awt.image.BufferedImage;

public interface Duck {

    int getSpeed();
    int getHP();
    int getScorePerDuck();

    int getX();
    int getY();

    boolean isAlive();
    void decreaseHP();

    void move();
    BufferedImage getImage();
}
