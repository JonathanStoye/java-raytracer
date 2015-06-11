package Scene;

import Camera.OrthographicCamera;
import Geometry.*;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Utilities.Debugging;
import Visualization.Painter;

import java.awt.*;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Raytracer {
    private final int width;
    private final int height;
    private final int[] pixels;

    public Raytracer(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new int[this.width * this.height];

        Geometry[] objects = new Geometry[1];
        Sphere sphere = new Sphere(new Point3(this.width/2,this.height/2,100), 30, new Color(1, 0, 0));
        objects[0] = sphere;
        World w = new World(objects, new Color(0, 0, 0));
        OrthographicCamera camera = new OrthographicCamera(new Point3(this.width / 2, this.height / 2, 0), new Vector3(0.0, 1.0, 0.0), new Vector3(0.0, 0.0, 1.0), 1.0);

        int x = 0;
        int y = 0;
        for (int i = 0; i < this.pixels.length; i++) {
            Ray ray = new Ray(new Point3((double)(x), (double)(y), 100.0), new Vector3(0.0, 1.0, 0.0));
//            Ray ray = camera.rayFor(this.width, this.height, x, y);
            Hit a = w.hit(ray);
            if (a == null) {
                this.pixels[i] = 0x000000;
            }
            else {
                this.pixels[i] = 0xffffff;
            }
            if (x < this.width) {
                x++;
            }
            else {
                x = 0;
            }
            if (x / this.width == 1) {
                y++;
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }
}
