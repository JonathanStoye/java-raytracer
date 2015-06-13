package Scene;

import Camera.OrthographicCamera;
import Geometry.*;
import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Utilities.Debugging;
import Visualization.Painter;
import javafx.scene.control.TreeItem;

import java.awt.*;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Raytracer {
    private final int width;
    private final int height;
    private final int[] pixels;

    public Raytracer(int width, int height) {
        this.height = height;
        this.width = width;
        this.pixels = new int[this.width * this.height];

        Geometry[] objects = new Geometry[4];

        Sphere sphere =     new Sphere(  new Point3(this.width / 2, this.height / 2, 80), 100,                            new Color(1, 1, 1));
        Triangle triangle = new Triangle(new Point3(100, 100, 150), new Point3(100, 900, 150), new Point3(900, 100, 150), new Color(1, 0, 0));
        Plane plane =       new Plane(   new Point3(100, 500, 140), new Normal3(0.0, 0.0, 0.0),                           new Color(0, 1, 0));
        Plane plane2 =      new Plane(   new Point3(100, 600, 130), new Normal3(0.0, 0.0, 0.0),                           new Color(0, 0, 1));

        objects[0] = sphere;
        objects[1] = triangle;
        objects[2] = plane2;
        objects[3] = plane;


        World world = new World(objects, new Color(0, 0, 0));
        OrthographicCamera camera = new OrthographicCamera(new Point3(this.width, 0, 0), new Vector3(0.0, 0.0, 1.0), new Vector3(0.0, 1.0, 0.0), 1.0);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
//                Ray ray = new Ray(new Point3(x, y, 0), new Vector3((double) x, (double) y, 1000.0));
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.color.asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }
}
