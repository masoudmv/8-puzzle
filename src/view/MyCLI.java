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
    int heightTiles = ConfigLoader.heightTiles;
    int widthTiles = ConfigLoader.widthTiles;
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
            if (i % heightTiles == heightTiles-1) {
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
        } else if (command.equals("right-up")){
            offset = new Point(1, -1);
        } else if (command.equals("left-up")){
            offset = new Point(-1, -1);
        } else if (command.equals("right-down")){
            offset = new Point(1, +1);
        } else if (command.equals("left-down")){
            offset = new Point(-1, +1);
        } else {
            System.out.println("Invalid input. Please try again!");
        }

        controller.updateBoard(offset);

        System.out.println("=======");
        drawBoard();


    }
}
