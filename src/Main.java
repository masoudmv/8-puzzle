import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import model.Board;
import view.*;
//import view.ModeSelection;

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

        Board board = new Board();

        GameController controller = new GameController(board);
        ModeSelectionListener modeSelectionListener = new ModeSelectionListener();

        MyPanel panel = MyPanel.getInstance();
        ModeSelectionPanel modeSelectionPanel = ModeSelectionPanel.getInstance();
        panel.addKeyListener(new MyKeyListener(controller));

        panel.setController(controller);
        modeSelectionPanel.setModeSelectionListener(modeSelectionListener);
        MyCLI myCLI = new MyCLI();
        myCLI.setController(controller);

        MyFrame frame = new MyFrame(panel, modeSelectionPanel);
        frame.setController(controller);
        GameView gameView = new GameView(frame, myCLI);



        controller.setPanel(panel);
        controller.setFrame(frame);
        controller.setMyCLI(myCLI);

        modeSelectionListener.setGameView(gameView);
        modeSelectionListener.setGameController(controller);

        controller.startGameLoop();
    }


}