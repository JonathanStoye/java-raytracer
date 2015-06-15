package Scene;

import Camera.OrthographicCamera;
import Camera.PerspectiveCamera;
import Geometry.*;
import Material.LambertMaterial;
import Material.SingleColorMaterial;
import MatrixVector.Normal3;
import MatrixVector.Point3;
import MatrixVector.Vector3;
import Utilities.Debugging;
import Visualization.Painter;
import javafx.scene.control.TreeItem;
import Scene.*;
import Light.*;

import java.util.ArrayList;
import java.util.List;

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
    }

    public void testScene1(){
        Geometry[] objects = new Geometry[1];
        Plane plane =       new Plane(   new Point3(0.0, -1.0, 0.0), new Normal3(0.0, 1.0, 0.0), new SingleColorMaterial(new Scene.Color(0, 1, 0)));
        objects[0] = plane;

        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }


    public void testScene2(){
        Geometry[] objects = new Geometry[1];

        Sphere sphere = new Sphere( new Point3(0.0, 0.0, -3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)));
        objects[0] = sphere;

        World world = new World(objects, new Color(0, 0, 0));

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }


    public void testScene3(){
        Geometry[] objects = new Geometry[1];

        AxisAlignedBox box = new AxisAlignedBox(new Point3(-0.5, 0.0, -0.5), new Point3(0.5, 1.0, 0.5), new SingleColorMaterial(new Color(0,0,1)));

        objects[0] = box;

        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(3.0,3.0,3.0), new Vector3(-3.0,-3.0,-3.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }


    public void testScene4(){
        Geometry[] objects = new Geometry[1];

        Triangle triangle = new Triangle(new Point3(-0.5,0.5,-3.0), new Point3(0.5,0.5,-3.0), new Point3(0.5,-0.5,-3.0), new SingleColorMaterial(new Scene.Color(1, 0, 1)));

        objects[0] = triangle;


        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }


    public void testScene5(){
        Geometry[] objects = new Geometry[2];

        Sphere sphere1 = new Sphere( new Point3(-1.0,0.0,-3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)));
        Sphere sphere2 = new Sphere( new Point3(1.0,0.0,-6.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)));

        objects[0] = sphere1;
        objects[1] = sphere2;


        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }


    public void testScene6(){
        Geometry[] objects = new Geometry[2];

        Sphere sphere1 = new Sphere( new Point3(-1.0,0.0,-3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)));
        Sphere sphere2 = new Sphere( new Point3(1.0,0.0,-6.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)));

        objects[0] = sphere1;
        objects[1] = sphere2;


        World world = new World(objects, new Color(0, 0, 0));
        OrthographicCamera camera = new OrthographicCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), 10.0);


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }

    public void testLightning1(){
        Geometry[] objects = new Geometry[4];

        Plane plane = new Plane( new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new SingleColorMaterial(new Scene.Color(1, 0, 0)));
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new SingleColorMaterial(new Color(0, 1, 0)));
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(new Color(0,0,1)));
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new SingleColorMaterial(new Scene.Color(1, 1, 0)));

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;

        World world = new World(objects, new Color(1.0, 0.0, 0.0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }

    public void testLightning2(){
        Geometry[] objects = new Geometry[4];

        Plane plane = new Plane( new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new LambertMaterial(new Color(0.6, 0, 0)));
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new LambertMaterial(new Color(0, 0.6, 0)));
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(new Color(0,0,0.6)));
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new LambertMaterial(new Scene.Color(0.6, 0.6, 0)));

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        World world = new World(objects, new Color(1.0, 0.0, 0.0), new Color(0.5,0.5,0.5), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);


        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if (hit != null) {
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this.height, this.pixels);
        p.draw();
    }

    public void testAllScenes(){
        this.testScene1();
        this.testScene2();
        this.testScene3();
        this.testScene4();
        this.testScene5();
        this.testScene6();
    }
}
