package Defaults;

import GUI.Window;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Window());
        /*while (x !=0 && y !=0){
            x = MouseInfo.getPointerInfo().getLocation().x;
            y = MouseInfo.getPointerInfo().getLocation().y;

            System.out.println(x + " " + y);
        }*/
    }

}
