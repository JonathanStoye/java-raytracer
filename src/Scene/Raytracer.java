package Scene;

import Geometry.*;
import MatrixVector.Point3;
import Utilities.Debugging;
import Visualization.Painter;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Raytracer {
    private final int width;
    private final int height;
    private final double[] pixels;

    public Raytracer(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new double[this.width * this.height];

        Geometry[] objects = new Geometry[1];
        Sphere sphere = new Sphere(new Point3(30,30,30), 30, new Color(1, 0, 0));
        objects[0] = sphere;
        World w = new World(objects, new Color(0, 0, 0));

        for (int i = 0; i < this.pixels.length; i++) {
            this.pixels[i] = 0.5;
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
    }
}
