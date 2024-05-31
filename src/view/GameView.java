package view;

import controller.ConfigLoader;

public class GameView {
    private final MyGUI myGUI;
    private final MyCLI myCLI;

    public GameView(MyGUI myGUI, MyCLI myCLI) {
        this.myGUI = myGUI;
        this.myCLI = myCLI;
    }

    public void startGUI(){
        myGUI.initSwingGUI();
    }

    public void startCLI(){
        myGUI.setVisible(false);

        System.out.println("allowed commands:");
        System.out.println("right");
        System.out.println("left");
        System.out.println("up");
        System.out.println("down");

        if (ConfigLoader.getInstance().diagonalMovementAllowed){
            System.out.println("right-up");
            System.out.println("right-down");
            System.out.println("left-up");
            System.out.println("left-down");
        }

        System.out.println("=======");
        myCLI.drawBoard();

    }
}
