package controller;

import model.Board;
import view.MyPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyKeyListener implements KeyListener {
    MyPanel myPanel = MyPanel.getInstance();
    private final Set<Integer> pressedKeys = new HashSet<>();
    private GameController gameController;

    public MyKeyListener(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {

        Point offset = new Point();
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        offset.y = -1;
                        break;
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        offset.x = -1;
                        break;
                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        offset.y = 1;
                        break;
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        offset.x = 1;
                        break;
                }
            }
        }
        gameController.movePiece(offset);
        pressedKeys.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Event not used */ }
}