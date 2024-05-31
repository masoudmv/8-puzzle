package view;

import controller.ConfigLoader;
import controller.GameController;
import model.PuzzlePiece;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MyPanel extends JPanel {
    private static GameController controller;
    private static MyPanel instance;
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    ConfigLoader configLoader = ConfigLoader.getInstance();

    private MyPanel() {
        int screenWidth;
        int screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        setSize(maxSize, maxSize);
        setLocation(screenWidth / 2 - maxSize / 2,screenHeight / 2 - maxSize / 2);

//        addKeyListener(new MyKeyListener());
    }

    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public static MyPanel getInstance() {
        if (instance == null) instance = new MyPanel();
        return instance;
    }

    public void setController(GameController controller) {
        MyPanel.controller = controller;
    }

    public void update(){
        this.puzzlePieces = controller.getBoard().puzzlePieces;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            g.drawImage(piece.img, piece.location.x, piece.location.y, (int) this.getSize().getWidth() / configLoader.heightTiles, (int) this.getSize().getHeight() / configLoader.widthTiles, null);
        }
    }


}
