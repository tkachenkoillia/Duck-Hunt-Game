package GUI.Frames;

import Defaults.Settings;
import Models.Ducks.GreenDuck;
import Models.Ducks.PinkDuck;
import Models.Ducks.RedDuck;
import Models.Ducks.YellowDuck;

public class ToDo extends Thread{

    @Override
    public void run() {
        int action = (int) (Math.random() * (3));
        if (action == 1){
            YellowDuck.addHP();
            GreenDuck.addHP();
            PinkDuck.addHP();
            RedDuck.addHP();
        }
        else if (action == 2){
            YellowDuck.addSpeed();
            GreenDuck.addSpeed();
            PinkDuck.addSpeed();
            RedDuck.addSpeed();
        }
        else{
            Settings.maxBirds++;
        }
    }
}
