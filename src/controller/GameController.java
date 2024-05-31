package controller;

import model.Board;
import view.MyCLI;
import view.MyFrame;
import view.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GameController {
    private Board board;
    private MyPanel panel;
    private MyFrame frame;
    private MyCLI myCLI;
    private boolean running = true;

    public GameController(Board model) {
        this.board = model;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }

    public void setMyCLI(MyCLI myCLI) { this.myCLI = myCLI; }

    public void startGameLoop() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                todo ?
//                panel.createAndShowGUI();
            }
        });

        while (running) {

//            panel.repaint();
//            frame.repaint();

//            System.out.println(ConfigLoader.getInstance().diagonalMovementAllowed);

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
//                todo ?
                panel.update();
                myCLI.update();
            }
        });
    }

    public void movePiece(Point offset){
        board.updateBoard(offset);
    }

    public Board getBoard() {
        return board;
    }

    // Method to handle user input, etc.
}

