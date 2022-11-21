package GUI;


public class WindowRepaint implements Runnable{
    Window window;

    public WindowRepaint(Window window){
        this.window = window;
    }
    @Override
    public void run() {
        Window.frame.repaint();
    }
}
