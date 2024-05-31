package controller;

import model.Board;
import view.MyCLI;
import view.MyGUI;
import view.MyPanel;
import javax.swing.*;
import java.awt.*;

public class GameController {
    private final Board board;
    private MyPanel panel;
    private MyGUI frame;
    private MyCLI myCLI;
    private boolean running = true;

    public GameController(Board model) {
        this.board = model;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public void setFrame(MyGUI frame) { this.frame = frame; }

    public void setMyCLI(MyCLI myCLI) { this.myCLI = myCLI; }

    public void startGameLoop() {
        while (running) {
            if (board.gameFinished) {
                break;
            }
            if (board.gameState.equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
                board.gameFinished = true;
            }
            update();
            try {
                Thread.sleep(16); // Roughly 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            frame.repaint();
        }
    }

    protected void update() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.update();
                myCLI.update();
            }
        });
    }

    public void updateBoard(Point offset){
        board.updateBoard(offset);
    }

    public Board getBoard() {
        return board;
    }
}

