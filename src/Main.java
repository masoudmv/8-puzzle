import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import model.Board;
import view.*;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Loading the config file ...
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ConfigLoader.class, new customDeserializer());
        Gson gson = gsonBuilder.create();
        try (FileReader reader = new FileReader("src/controller/config.json")) {
            gson.fromJson(reader, ConfigLoader.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyPanel panel = MyPanel.getInstance();
        Board board = new Board();

        GameController gameController = new GameController(board);
        ModeSelectionListener modeSelectionListener = new ModeSelectionListener();

        ModeSelectionPanel modeSelectionPanel = ModeSelectionPanel.getInstance();
        panel.addKeyListener(new MyKeyListener(gameController));

        panel.setController(gameController);
        modeSelectionPanel.setModeSelectionListener(modeSelectionListener);

        MyGUI myGUI = new MyGUI(panel, modeSelectionPanel);
        myGUI.setController(gameController);


        MyCLI myCLI = new MyCLI();
        myCLI.setController(gameController);

        GameView gameView = new GameView(myGUI, myCLI);

        gameController.setPanel(panel);
        gameController.setFrame(myGUI);
        gameController.setMyCLI(myCLI);

        modeSelectionListener.setGameView(gameView);
        modeSelectionListener.setGameController(gameController);

        gameController.startGameLoop();
    }


}