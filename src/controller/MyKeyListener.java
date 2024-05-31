package controller;

import model.Board;
import view.MyPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    Board board = Board.getInstance();
    MyPanel myPanel = MyPanel.getInstance();
    ConfigLoader configLoader = ConfigLoader.getInstance();
    int widthTiles = configLoader.widthTiles;
    int heightTiles = configLoader.heightTiles;

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int missingPieceIndex = board.missingPiece;
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (missingPieceIndex % heightTiles == heightTiles-1) {
                return;
            }
            board.swapPieces(missingPieceIndex, missingPieceIndex + 1);
            board.setMissingPiece(missingPieceIndex + 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if (missingPieceIndex % heightTiles == 0) {
                return;
            }
            board.swapPieces(missingPieceIndex, missingPieceIndex - 1);
            board.setMissingPiece(missingPieceIndex - 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if (missingPieceIndex <= heightTiles-1) {
                return;
            }
            board.swapPieces(missingPieceIndex, missingPieceIndex - heightTiles);
            board.setMissingPiece(missingPieceIndex - heightTiles);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if (missingPieceIndex >= heightTiles*(widthTiles-1)) {
                return;
            }
            board.swapPieces(missingPieceIndex, missingPieceIndex + heightTiles);
            board.setMissingPiece(missingPieceIndex + heightTiles);
        }

        if (board.gameState.equals("finished")) {
            return;
        }
        myPanel.repaint();

    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
