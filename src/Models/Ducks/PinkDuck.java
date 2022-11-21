package Models.Ducks;

import Defaults.MakeFrames;
import GUI.Frames.Game;

import java.awt.image.BufferedImage;

public class PinkDuck implements Duck {

    int frameCounter = 0;

    int x, y;

    BufferedImage[] right = new BufferedImage[7];
    BufferedImage[] left = new BufferedImage[7];

    int scorePerDuck = 300;
    int speed = 3;
    int HP = 1;
    static int tempHP;
    static int tempSpeed;
    String direction;

    public PinkDuck(int y, int direction){
        this.y = y;
        if (direction == 0){
            this.x = 800;
            this.direction = "RIGHT";
            for (int i = 0; i < right.length; i++) {
                right[i] = MakeFrames.makeFrames("Files/Images/Ducks/PinkDuck.png", this.direction, i);
            }
        }
        else {
            this.x = -100;
            this.direction = "LEFT";
            for (int i = 0; i < left.length; i++) {
                left[i] = MakeFrames.makeFrames("Files/Images/Ducks/PinkDuck.png", this.direction, i);
            }
        }
        this.HP = tempHP;
        this.speed = tempSpeed;
    }

    public void setScorePerDuck(int scorePerDuck) {
        this.scorePerDuck = scorePerDuck;
    }

    public static void setStats() {
        if (Game.gameDifficulty.equals("EASY")){
            tempSpeed = 2;
            tempHP = 1;
        }
        else if (Game.gameDifficulty.equals("MEDIUM")){
            tempSpeed = 3;
            tempHP = 2;
        }
        else{
            tempSpeed = 4;
            tempHP = 3;
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public int getScorePerDuck() {
        return this.scorePerDuck;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public boolean isAlive() {
        return (HP > 0);
    }

    @Override
    public void decreaseHP() {
        HP--;
    }


    @Override
    public void move() {
        if (this.direction.equals("LEFT"))
            this.x += speed;
        else
            this.x -= speed;
    }

    @Override
    public BufferedImage getImage() {
        this.frameCounter++;
        if (this.frameCounter == 27){
            this.frameCounter = 0;
        }
        if (this.direction.equals("LEFT"))
            return left[this.frameCounter/4];
        else
            return right[this.frameCounter/4];
    }

    public static void addHP(){
        tempHP++;
    }

    public static void addSpeed() {
        tempSpeed++;
    }

}
