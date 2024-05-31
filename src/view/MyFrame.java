package view;

import controller.GameController;
import controller.MyKeyListener;

import javax.swing.*;

public class MyFrame extends JFrame {

    private MyPanel myPanel;
    private final ModeSelectionPanel modeSelectionPanel;
    private GameController controller;

    public MyFrame(MyPanel myPanel, ModeSelectionPanel modeSelectionPanel) {
        this.myPanel = myPanel;
        this.modeSelectionPanel = modeSelectionPanel;
        this.initFrame();
        this.repaint();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void initFrame() {


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        // Start with mode selection panel
        initModeSelection();

        setVisible(true);
    }

    public void initModeSelection() {
        setSize(modeSelectionPanel.getSize());
        setLocation(modeSelectionPanel.getLocation());
        getContentPane().removeAll();
        getContentPane().add(modeSelectionPanel);
        pack(); // Adjust the frame size to fit the preferred size of the panel
        revalidate(); // Revalidate the frame to refresh the layout
        repaint(); // Repaint the frame to ensure proper display
    }

    public void removeSelectionModePanel(){
        getContentPane().removeAll();
    }

    public void initSwingGUI() {
        getContentPane().removeAll();
        getContentPane().add(myPanel);
        setSize(myPanel.getSize());
        setLocation(myPanel.getLocation());
        repaint(); // Repaint the frame to ensure proper display
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new MyKeyListener(controller));
        repaint();
    }

}
