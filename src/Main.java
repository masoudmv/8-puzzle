import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import model.Board;
import view.GameView;
import view.MyFrame;
import view.MyPanel;
//import view.ModeSelection;
import view.ModeSelectionPanel;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConfigLoader.class, new customDeserializer());
        Gson gson = gsonBuilder.create();

        try (FileReader reader = new FileReader("src/controller/config.json")) {
            gson.fromJson(reader, ConfigLoader.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Board board = Board.getInstance();

        GameController controller = new GameController(board);
        ModeSelectionListener modeSelectionListener = new ModeSelectionListener();

        MyPanel panel = MyPanel.getInstance();
        ModeSelectionPanel modeSelectionPanel = ModeSelectionPanel.getInstance();
        panel.addKeyListener(new MyKeyListener());

        panel.setController(controller);
        modeSelectionPanel.setModeSelectionListener(modeSelectionListener);

        MyFrame frame = new MyFrame(panel, modeSelectionPanel);
        GameView gameView = new GameView(frame);

        controller.setPanel(panel);
        controller.setFrame(frame);
        modeSelectionListener.setGameView(gameView);
        modeSelectionListener.setGameController(controller);
//        modeSelectionListener.setModeSelectionPanel(modeSelectionPanel);

        controller.startGameLoop();
    }


}