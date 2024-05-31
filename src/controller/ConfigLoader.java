package controller;

import java.util.ArrayList;

public class ConfigLoader {
    private static ConfigLoader instance;
    public int widthTiles;
    public int heightTiles;
    public ArrayList<String> images;
    public ArrayList<Integer> initial_ordering;
    public boolean diagonalMovementAllowed;
    private ConfigLoader(){}

    public void setWidthTiles(int widthTiles) {
        this.widthTiles = widthTiles;
    }

    public void setHeightTiles(int heightTiles) {
        this.heightTiles = heightTiles;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setInitial_ordering(ArrayList<Integer> initial_ordering) {
        this.initial_ordering = initial_ordering;
    }

    public static ConfigLoader getInstance(){
        if (instance == null) instance = new ConfigLoader();
        return instance;
    }

    public void setDiagonalMovementAllowed(boolean diagonalMovementAllowed) {
        this.diagonalMovementAllowed = diagonalMovementAllowed;
    }
}
