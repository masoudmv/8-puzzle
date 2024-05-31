package controller;

import model.Board;
import view.MyFrame;
import view.MyPanel;

import javax.swing.*;

public class GameController {
    private Board board;
    private MyPanel panel;
    private MyFrame frame;
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

    public void startGameLoop() {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                todo ?
//                panel.createAndShowGUI();
            }
        });

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
        }
    }

    private void update() {

//        System.out.println("ddsdsdsdsd");
        // Update model state
        // Ask view to update itself
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                todo ?
                panel.update();
            }
        });
    }

    public Board getBoard() {
        return board;
    }

    // Method to handle user input, etc.
}

