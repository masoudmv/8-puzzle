package view;

import controller.ModeSelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModeSelectionPanel extends JPanel {
    private static ModeSelectionPanel instance;
    private ActionListener modeSelectionListener;
    private JRadioButton mode1Button;
    private JRadioButton mode2Button;
    private ButtonGroup modeGroup;

    private JRadioButton ui1Button;
    private JRadioButton ui2Button;
    private ButtonGroup uiGroup;

    private JButton startButton;

    public ModeSelectionPanel() {

        int screenWidth;
        int screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        setSize(maxSize, maxSize);
        setLocation(screenWidth / 2 - maxSize / 2,screenHeight / 2 - maxSize / 2);



        // Set the layout for the panel
        setLayout(new GridLayout(6, 1));  // 6 rows, 1 column

        // Create radio buttons for modes
        mode1Button = new JRadioButton("diagonal movement allowed!");
        mode2Button = new JRadioButton("diagonal movement forbidden!");

        // Create a button group and add the mode radio buttons to it
        modeGroup = new ButtonGroup();
        modeGroup.add(mode1Button);
        modeGroup.add(mode2Button);

        // Add mode radio buttons to the panel
        add(mode1Button);
        add(mode2Button);

        // Create radio buttons for user interfaces
        ui1Button = new JRadioButton("GUI");
        ui2Button = new JRadioButton("CLI");

        // Create a button group and add the UI radio buttons to it
        uiGroup = new ButtonGroup();
        uiGroup.add(ui1Button);
        uiGroup.add(ui2Button);

        // Add UI radio buttons to the panel
        add(ui1Button);
        add(ui2Button);

        // Create the start button
        startButton = new JButton("Start Game");
        startButton.setBounds(8,20,20,20);

        // Set default selections
        mode1Button.setSelected(true);
        ui1Button.setSelected(true);
    }

    public String getSelectedMode() {
        if (mode1Button.isSelected()) {
            return "Mode 1";
        } else if (mode2Button.isSelected()) {
            return "Mode 2";
        }
        return null;
    }

    public String getSelectedUI() {
        if (ui1Button.isSelected()) {
            return "GUI";
        } else if (ui2Button.isSelected()) {
            return "CLI";
        }
        return null;
    }

    public void setModeSelectionListener(ActionListener modeSelectionListener) {
        this.modeSelectionListener = modeSelectionListener;

        startButton.addActionListener(modeSelectionListener);

        // Add the start button to the panel
        add(startButton);

        // Add action listeners to the mode and UI radio buttons
        mode1Button.addActionListener(modeSelectionListener);
        mode2Button.addActionListener(modeSelectionListener);
        ui1Button.addActionListener(modeSelectionListener);
        ui2Button.addActionListener(modeSelectionListener);
    }

    public static ModeSelectionPanel getInstance(){
        if (instance == null) instance = new ModeSelectionPanel();
        return instance;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
