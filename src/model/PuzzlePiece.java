package model;

import controller.ConfigLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    public Image img;
    protected int pieceNumber;
    public Location location;
    private final ConfigLoader configLoader = ConfigLoader.getInstance();

    public PuzzlePiece(String imageName) {
        try {
            img = ImageIO.read(new File("src/assets/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!imageName.equals("missing.jpg")) {
            pieceNumber = Integer.parseInt(imageName.substring(0, 1)) - 1;
        } else {
            pieceNumber = configLoader.heightTiles * configLoader.widthTiles - 1;
        }
    }

    public PuzzlePiece(String ImagePath, Location location) {
        this(ImagePath);
        this.location = location;
    }

    public PuzzlePiece(Image img, Location location, int pieceIdentifier) {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(img, location, pieceNumber);

        return clone;
    }
}
