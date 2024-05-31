package view;

public class GameView {
    private final MyFrame frame;
    private final MyCLI myCLI;

    public GameView(MyFrame frame, MyCLI myCLI) {
        this.frame = frame;
        this.myCLI = myCLI;
    }


    public void startGUI(){
        frame.initSwingGUI();
    }

    public void startCLI(){
        frame.setVisible(false);
        myCLI.drawBoard();

    }
}
