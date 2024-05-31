package model;

import controller.ConfigLoader;
import view.MyPanel;
import java.util.*;

public class Board {
    public static Board boardInstance;
    private static final ConfigLoader configLoader =ConfigLoader.getInstance();
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public int missingPiece;
    public String gameState = "#";
    public boolean gameFinished = false;
    static final int widthTiles = configLoader.widthTiles;
    static final int heightTiles = configLoader.heightTiles;
    static final int n = widthTiles * heightTiles;

    public Board() {

        MyPanel panel = MyPanel.getInstance();
        ArrayList<Integer> initial_ordering = configLoader.initial_ordering;
        ArrayList<Integer> piecesRandomOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            piecesRandomOrder.add(initial_ordering.get(i));
        }
        Collections.shuffle(piecesRandomOrder);
        while (!solvable(missingPiece, piecesRandomOrder)){
            Collections.shuffle(piecesRandomOrder);
        }
        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (piecesRandomOrder.get(i) == n-1) {
                this.setMissingPiece(i);
            }
        }

        ArrayList<String> images = configLoader.images;

        for (int i = 0; i < n; i++) {
            System.out.println(i + " " + piecesRandomOrder.get(i));
            int panelHeight = panel.getHeight();
            int panelWidth = panel.getWidth() ;

            if (missingPiece != i) {
                int x = piecesRandomOrder.get(i);
                puzzlePieces.add(new PuzzlePiece(images.get(x),
                        new Location(panelHeight / heightTiles * (i % heightTiles), panelWidth * (i / heightTiles) / widthTiles)));
            } else {

                puzzlePieces.add(new PuzzlePiece(images.get(configLoader.images.size()-1),
                        new Location(panelHeight / heightTiles * (i % heightTiles), panelWidth * (i / heightTiles) / widthTiles)));
            }
        }
    }

    public static Board getInstance() {
        if (boardInstance == null) boardInstance = new Board();
        return boardInstance;
    }

    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).img);
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).pieceNumber);
        puzzlePieces.get(j).setImage(copy.img);
        puzzlePieces.get(j).setPieceNumber(copy.pieceNumber);

        if (gameFinished()) {
            gameState = "finished";
        }
    }

    public boolean gameFinished() {
        for (int i = 0; i < n; i++) {
            int pieceIdentifier = puzzlePieces.get(i).pieceNumber;
            if (pieceIdentifier == n-1) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    public static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;
        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }

}




