package view;

import controller.ConfigLoader;
import controller.GameController;
import model.PuzzlePiece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCLI {
    private GameController controller;
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    ConfigLoader configLoader = ConfigLoader.getInstance();
    int heightTiles = configLoader.heightTiles;
    int widthTiles = configLoader.widthTiles;
    int n = widthTiles * heightTiles;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void update(){
        this.puzzlePieces = controller.getBoard().puzzlePieces;
//        drawBoard();
    }

    public void drawBoard(){

        for (int i = 0; i < n; i++) {
            int pieceIdentifier = puzzlePieces.get(i).getPieceNumber()+1;
            if (pieceIdentifier == n){
                System.out.print("#" + "  ");
            }
            else {
                System.out.print(pieceIdentifier + "  ");
            }
            if (i % heightTiles == 2) {
                System.out.println(" ");
            }
        }

        System.out.println("=======");

        myCliController();

    }

    private void myCliController(){
        System.out.println("please enter your next move: ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Point offset = new Point();

        if (command.equals("right")){
            offset = new Point(1,0);
        } else if (command.equals("left")){
            offset = new Point(-1, 0);
        } else if (command.equals("up")){
            offset = new Point(0, -1);
        } else if (command.equals("down")){
            offset = new Point(0, +1);
        }

        controller.movePiece(offset);

        System.out.println("=======");
        drawBoard();


    }
}
