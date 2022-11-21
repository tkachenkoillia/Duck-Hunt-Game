package GUI.Frames;


import Defaults.Settings;
import Models.Ducks.*;
import Models.Others.Cloud;
import Models.Others.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;


public class Game extends JPanel{

    Font font = new Font(Settings.font.getFontName(), Settings.font.getStyle(), 20);
    static JTextField nickname = new JTextField(20);

    public static List<Duck> ducks = new ArrayList<>();

    public static String gameDifficulty;
    public static int score = 0;
    public static int birdsNum = 0;
    public static int health = 10;

    static ImageIcon bg = new ImageIcon("Files/Images/BGs/DifficultyBgImage.jpg");
    public static boolean onDifficultyChoice = false;
    public static boolean onGameOver = false;
    public static boolean isCloud = false;
    Cloud cloud;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(font);
        g.setColor(Color.WHITE);

        g.drawImage(bg.getImage(), 0, 0, null);

        if (!onGameOver && !onDifficultyChoice){
            for (int i = 0; i < ducks.size(); i++) {
                g.drawImage(ducks.get(i).getImage(), ducks.get(i).getX(), ducks.get(i).getY(), null);
                ducks.get(i).move();
                if (ducks.get(i).getX() < -100 || ducks.get(i).getX() > 800) {
                    ducks.remove(ducks.get(i));
                    health--;
                    checkHP();
                }
            }
            if (!isCloud){
                int spawn = (int) (Math.random() * (2));
                System.out.println(spawn);
                if (spawn == 1){
                    cloud = new Cloud(-200, 550);
                    g.drawImage(cloud.getImage(), -200, 550, null);
                    isCloud = true;
                }
            } else {
                cloud.move();
            }
            g.drawImage(Tree.getImage(), Tree.x, Tree.y, null);
            g.drawString("Score: " + score, 15, 25);
            g.drawString("HP: " + health, 720, 25);
            updateDucksNum();
        }

        if (onGameOver){
            String yourScore = "Your score: " + score;
            FontMetrics metrics = g.getFontMetrics(font);
            int x = metrics.stringWidth(yourScore) / 2;
            g.drawString(yourScore, 400 - x, 450 );
            nickname();
        }
    }

    public Game(){
        onDifficultyChoice = true;
        onGameOver = false;
        bg = new ImageIcon("Files/Images/BGs/DifficultyBgImage.jpg");
        ducks = new ArrayList<>();
        score = 0;
        birdsNum = 0;
        health = 10;
        nickname.setText("Enter your nickname:");
        this.add(nickname);
        nickname.setVisible(false);
    }

    public static void addDuck(){
        int duckType = (int) (Math.random() * (10) + 1);
        if (duckType < 10) {
            if (duckType < 8) {
                if (duckType < 6) {
                    ducks.add(new YellowDuck((int) (Math.random() * ((400 - 50 + 1) + 50)), (int) (Math.random() * ((2)))));
                } else {
                    ducks.add(new GreenDuck((int) (Math.random() * ((400 - 50 + 1) + 50)), (int) (Math.random() * ((2)))));
                }
            } else {
                ducks.add(new PinkDuck((int) (Math.random() * ((400 - 50 + 1) + 50)), (int) (Math.random() * ((2)))));
            }
        } else {
            ducks.add(new RedDuck((int) (Math.random() * ((400 - 50 + 1) + 50)), (int) (Math.random() * ((2)))));
        }
    }

    public static void updateDucksNum() {
         for (int i = 0; i < Settings.maxBirds - ducks.size(); i++) {
            addDuck();
        }
    }

    public void startGame(String difficulty){
        gameDifficulty = difficulty;
        birdsNum = Settings.setMaxBirds();
        bg = new ImageIcon("Files/Images/BGs/DefaultBgImage.jpg");
        YellowDuck.setStats();
        GreenDuck.setStats();
        PinkDuck.setStats();
        RedDuck.setStats();
        updateDucksNum();
    }


    static void checkHP(){
        if (health <= 0){
            bg = new ImageIcon("Files/Images/BGs/GameOverBgImage.jpg");
            onGameOver = true;
        }
    }

    void nickname(){
        nickname.setSize(350, 30);
        nickname.setLocation((800 - nickname.getWidth()) / 2, 215);
        nickname.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nickname.getText().equals("Enter your nickname:")){
                    nickname.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nickname.getText().equals("")){
                    nickname.setText("Enter your nickname:");
                }
            }
        });
        nickname.setVisible(true);
    }

    public static boolean checkNickname() {
        boolean valid = false;
        if (nickname.getText().equals("Enter your nickname:")) {
            return valid;
        }
        for (int i = 0; i < nickname.getText().length(); i++) {
            if (nickname.getText().charAt(i) != ' ') {
                valid = true;
                break;
            }
        }
        return valid;
    }

}
