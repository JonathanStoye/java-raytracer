package Visualization;

import Scene.Color;

import java.util.Arrays;

/**
 * Created by Jonathan on 09.06.15.
 */
public class Painter {
    private double worldWidth;
    private double worldHeight;
    private double frameWidth;
    private double frameHeight;
    private double[] pixels;

    public Painter(double worldWidth, double worldHeight, double[] pixels) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.pixels = pixels;
    }

    private void save() {

    }

    public void draw() {

    }

    private void drawPixel(double x, double y, Color color) {

    }
}
