package controller;

import view.GameView;
import view.ModeSelectionPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ModeSelectionListener implements ActionListener {
    GameView gameView;
    GameController gameController;
    ModeSelectionPanel modeSelectionPanel;

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setModeSelectionPanel(ModeSelectionPanel modeSelectionPanel) {
        this.modeSelectionPanel = modeSelectionPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JRadioButton) {
            JRadioButton radioButton = (JRadioButton) source;
            String selectedOption = radioButton.getText();
            if (selectedOption.equals("diagonal movement allowed!")){
                ConfigLoader.getInstance().setDiagonalMovementAllowed(true);
            } else {
                ConfigLoader.getInstance().setDiagonalMovementAllowed(false);
            }

        } else if (source instanceof JButton) {
            JButton button = (JButton) source;
            if ("Start Game".equals(button.getText())) {
                ModeSelectionPanel panel = (ModeSelectionPanel) button.getParent();
                String selectedUI = panel.getSelectedUI();

                if (selectedUI.equals("GUI")) {
                    gameView.startGUI();
                } else if (selectedUI.equals("CLI")){

                    gameView.startCLI();
                }

                // Implement the logic to start the game with the selected mode and UI

            }
        }
    }
}
