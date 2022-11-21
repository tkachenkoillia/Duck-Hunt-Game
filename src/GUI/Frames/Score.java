package GUI.Frames;

import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Score extends JPanel{

    JList jlist = new JList();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Files/Images/BGs/DefaultBgImage.jpg").getImage(), 0, 0, null);
    }

    public Score(){
        JButton but1 = new JButton("Back");
        but1.addActionListener(e -> Window.backToMenu());
        this.add(but1);
        this.add(jlist);
    }

}
