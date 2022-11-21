package GUI.Frames;

import javax.swing.*;
import java.awt.*;


public class MainMenu extends JPanel{


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Files/Images/BGs/MainMenuBgImage.jpg").getImage(), 0, 0, null);
    }

}
