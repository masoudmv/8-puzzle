package controller;

import view.GameView;
import view.ModeSelectionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ModeSelectionListener implements ActionListener {
    GameView gameView;
    GameController gameController;
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JRadioButton radioButton) {
            String selectedOption = radioButton.getText();
            if (selectedOption.equals("diagonal movement allowed!")){
                ConfigLoader.getInstance().setDiagonalMovementAllowed(true);
            }
            if (selectedOption.equals("diagonal movement forbidden!")){
                ConfigLoader.getInstance().setDiagonalMovementAllowed(false);
            }

        } else if (source instanceof JButton button) {
            if ("Start Game".equals(button.getText())) {
                ModeSelectionPanel panel = (ModeSelectionPanel) button.getParent();
                String selectedUI = panel.getSelectedUI();

                if (selectedUI.equals("GUI")) {
                    gameView.startGUI();
                } else if (selectedUI.equals("CLI")){

                    gameView.startCLI();
                }
            }
        }
    }
}
