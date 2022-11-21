package GUI;

import GUI.Frames.*;
import Models.Others.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Window implements KeyListener, MouseListener{

    public static JFrame frame = new JFrame();
    static MainMenu menuPanel = new MainMenu();
    public static Score scorePanel = new Score();
    static Game gamePanel = new Game();

    private final Set<Integer> pressedKeys = new HashSet<>();

    public Window(){
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("Files/Images/Others/Cursor.png").getImage(),
                new Point(15,15 ), "Cursor"));

        frame.addKeyListener(this);
        frame.addMouseListener(this);


        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new WindowRepaint(this), 10, 15L, TimeUnit.MILLISECONDS);
        if (gamePanel.isVisible()){
            if (!(Game.onGameOver && Game.onDifficultyChoice)){
                executor.scheduleAtFixedRate(new ToDo(), 0, 5000L, TimeUnit.MILLISECONDS);
            }
        }



        setPanel(scorePanel, false);
        setPanel(gamePanel, false);
        setPanel(menuPanel, true);
        frame.setVisible(true);
    }

    public static void setPanel(JPanel p, boolean isVisible) {
        p.setVisible(isVisible);
        frame.add(p);
    }


    public static void backToMenu(){
        setPanel(scorePanel, false);
        setPanel(gamePanel, false);
        setPanel(menuPanel, true);
    }



    @Override
    public void keyTyped(KeyEvent e) {
        if (scorePanel.isVisible()){
            if (e.getKeyChar() == '\n'){
                Window.setPanel(scorePanel, false);
                Window.setPanel(menuPanel, true);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gamePanel.isVisible()){
            pressedKeys.add(e.getKeyCode());
            for (Integer pressedKey : pressedKeys) {
                if (pressedKey == KeyEvent.VK_CONTROL && pressedKeys.contains(KeyEvent.VK_SHIFT) && pressedKeys.contains(KeyEvent.VK_Q)) {
                    SwingUtilities.invokeLater(Window::backToMenu);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gamePanel.isVisible()){
            if (Game.onDifficultyChoice){
                if (e.getPoint().x >= 340 && e.getPoint().x <= 450 && e.getPoint().y >= 200 && e.getPoint().y <= 250){
                    Game.onDifficultyChoice = false;
                    gamePanel.startGame("EASY");
                }
                if (e.getPoint().x >= 300 && e.getPoint().x <= 520 && e.getPoint().y >= 295 && e.getPoint().y <= 335){
                    Game.onDifficultyChoice = false;
                    gamePanel.startGame("MEDIUM");
                }
                if (e.getPoint().x >= 350 && e.getPoint().x <= 465 && e.getPoint().y >= 390 && e.getPoint().y <= 435){
                    Game.onDifficultyChoice = false;
                    gamePanel.startGame("HARD");
                }
            }

            if (!Game.onDifficultyChoice && !Game.onGameOver){
                for (int i = 0; i < Game.ducks.size(); i++) {
                    if (!(e.getPoint().x > Tree.x && e.getPoint().x < Tree.x + 186 && e.getPoint().y > Tree.y && e.getPoint().y < Tree.y + 331)){
                        if (e.getPoint().x > Game.ducks.get(i).getX() && e.getPoint().x < Game.ducks.get(i).getX() + 100 &&
                                e.getPoint().y > Game.ducks.get(i).getY() && e.getPoint().y < Game.ducks.get(i).getY() + 100){
                            Game.ducks.get(i).decreaseHP();
                        }
                        if (!Game.ducks.get(i).isAlive()) {
                            Game.score += Game.ducks.get(i).getScorePerDuck();
                            Game.ducks.remove(Game.ducks.get(i));
                        }
                    }
                }
            }

            if (Game.onGameOver){
                if (e.getPoint().x >= 355 && e.getPoint().x <= 440 && e.getPoint().y >= 295 && e.getPoint().y <= 335){
                    if (Game.checkNickname()){
                        gamePanel.setVisible(false);
                        menuPanel.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Enter your nickname!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        if (menuPanel.isVisible()) {
            if (e.getPoint().x >= 395 && e.getPoint().x <= 690 && e.getPoint().y >= 155 && e.getPoint().y <= 215){
                gamePanel = new Game();
                setPanel(menuPanel, false);
                setPanel(gamePanel, true);
            }
            if (e.getPoint().x >= 395 && e.getPoint().x <= 690 && e.getPoint().y >= 225 && e.getPoint().y <= 295){
                scorePanel = new Score();
                setPanel(menuPanel, false);
                setPanel(scorePanel, true);
            }
            if (e.getPoint().x >= 395 && e.getPoint().x <= 510 && e.getPoint().y >= 300 && e.getPoint().y <= 350){
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
