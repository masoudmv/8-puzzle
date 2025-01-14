package model;

import controller.ConfigLoader;
import java.awt.*;
import java.util.*;

public class Board {
    public ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    public int missingPiece;
    public String gameState = "#";
    public boolean gameFinished = false;
    static final int widthTiles = ConfigLoader.widthTiles;
    static final int heightTiles = ConfigLoader.heightTiles;
    static final int n = widthTiles * heightTiles;

    public Board() {

        ArrayList<Integer> initial_ordering = ConfigLoader.initial_ordering;
        ArrayList<Integer> piecesRandomOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            piecesRandomOrder.add(initial_ordering.get(i));
        }
        Collections.shuffle(piecesRandomOrder);

        if (!ConfigLoader.diagonalMovementAllowed) {
            while (!solvable(missingPiece, piecesRandomOrder)){
                Collections.shuffle(piecesRandomOrder);
            }
        }

        for (int i = 0; i < piecesRandomOrder.size(); i++) {
            if (piecesRandomOrder.get(i) == n-1) {
                this.setMissingPiece(i);
            }
        }

        ArrayList<String> images = ConfigLoader.images;

        for (int i = 0; i < n; i++) {
            int panelHeight = ConfigLoader.panelHeight;
            int panelWidth = ConfigLoader.panelWidth;

            if (missingPiece != i) {
                int x = piecesRandomOrder.get(i);
                puzzlePieces.add(new PuzzlePiece(images.get(x),
                        new Location(panelHeight / heightTiles * (i % heightTiles), panelWidth * (i / heightTiles) / widthTiles)));
            } else {

                puzzlePieces.add(new PuzzlePiece(images.get(ConfigLoader.images.size()-1),
                        new Location(panelHeight / heightTiles * (i % heightTiles), panelWidth * (i / heightTiles) / widthTiles)));
            }
        }
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

    public void updateBoard(Point offset){

        int missingPieceIndex = missingPiece;
        if (offset.equals(new Point(1, 0))) {
            if (missingPieceIndex % heightTiles == heightTiles-1) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex + 1);
            setMissingPiece(missingPieceIndex + 1);
        } else if (offset.equals(new Point(-1, 0))) {
            if (missingPieceIndex % heightTiles == 0) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex - 1);
            setMissingPiece(missingPieceIndex - 1);
        } else if (offset.equals(new Point(0, -1))){
            if (missingPieceIndex <= heightTiles-1) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex - heightTiles);
            setMissingPiece(missingPieceIndex - heightTiles);
        } else if (offset.equals(new Point(0, +1))) {
            if (missingPieceIndex >= heightTiles*(widthTiles-1)) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex + heightTiles);
            setMissingPiece(missingPieceIndex + heightTiles);
        }

        if (!ConfigLoader.diagonalMovementAllowed){
            return;
        }

        if (offset.equals(new Point(+1, -1))) {
            if (missingPieceIndex % heightTiles == heightTiles-1 || missingPieceIndex <= heightTiles-1) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex - heightTiles+ 1);
            setMissingPiece(missingPieceIndex - heightTiles + 1);

        } else if (offset.equals(new Point(-1, -1))) {
            if (missingPieceIndex % heightTiles == 0 || missingPieceIndex <= heightTiles-1) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex - heightTiles - 1);
            setMissingPiece(missingPieceIndex - heightTiles - 1);

        } else if (offset.equals(new Point(-1, +1))) {
            if (missingPieceIndex % heightTiles == 0 || missingPieceIndex >= heightTiles*(widthTiles-1)) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex + heightTiles - 1);
            setMissingPiece(missingPieceIndex + heightTiles - 1);

        } else if (offset.equals(new Point(+1, +1))) {
            if (missingPieceIndex % heightTiles == heightTiles-1 || missingPieceIndex >= heightTiles*(widthTiles-1)) {
                return;
            }
            swapPieces(missingPieceIndex, missingPieceIndex + heightTiles + 1);
            setMissingPiece(missingPieceIndex + heightTiles + 1);
        }
        if (gameState.equals("finished")) {
            return;
        }
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
