package controller;

import java.util.ArrayList;

public class ConfigLoader {
    private static ConfigLoader instance;
    public static int widthTiles;
    public static int heightTiles;
    public static ArrayList<String> images;
    public static ArrayList<Integer> initial_ordering;
    public static boolean diagonalMovementAllowed;
    public static int panelWidth;
    public static int panelHeight;


    private ConfigLoader(){}

    public void setWidthTiles(int widthTiles) {
        ConfigLoader.widthTiles = widthTiles;
    }

    public void setHeightTiles(int heightTiles) {
        ConfigLoader.heightTiles = heightTiles;
    }

    public void setImages(ArrayList<String> images) {
        ConfigLoader.images = images;
    }

    public void setInitial_ordering(ArrayList<Integer> initial_ordering) {
        ConfigLoader.initial_ordering = initial_ordering;
    }

    public void setDiagonalMovementAllowed(boolean diagonalMovementAllowed) {
        ConfigLoader.diagonalMovementAllowed = diagonalMovementAllowed;
    }

    public static void setPanelWidth(int panelWidth) {
        ConfigLoader.panelWidth = panelWidth;
    }

    public static void setPanelHeight(int panelHeight) {
        ConfigLoader.panelHeight = panelHeight;
    }

    public static ConfigLoader getInstance(){
        if (instance == null) instance = new ConfigLoader();
        return instance;
    }
}
