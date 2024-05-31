package view;

public class GameView {
    private final MyFrame frame;

    public GameView(MyFrame frame) {
        this.frame = frame;
    }


    public void startGUI(){
        frame.initSwingGUI();
    }

    public void startCLI(){

    }
}
