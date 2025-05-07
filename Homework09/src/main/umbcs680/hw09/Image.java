package umbcs680.hw09;

import java.util.*;

public class Image {
    private int width;
    private int height;
    private ArrayList<ArrayList<Color>> pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            ArrayList<Color> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                row.add(new Color(0, 0, 0)); // Default color (black)
            }
            this.pixels.add(row);
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Color getColor(int x, int y) {
        return pixels.get(y).get(x); // rows = y, cols = x
    }

    public void setColor(int x, int y, Color color) {
        pixels.get(y).set(x, color);
    }
}
