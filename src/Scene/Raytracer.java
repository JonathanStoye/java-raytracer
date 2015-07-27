package Scene;

import Camera.Camera;
import Camera.OrthographicCamera;
import Camera.PerspectiveCamera;
import Geometry.*;
import Light.DirectionalLight;
import Light.Light;
import Light.PointLight;
import Light.SpotLight;
import Material.LambertMaterial;
import Material.PhongMaterial;
import Material.ReflectiveMaterial;
import Material.SingleColorMaterial;
import MatrixVector.*;
import Utillities.Debugging;
import Visualization.Painter;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan on 05.06.15.
 */
public class Raytracer {
    private final int width;
    private final int height;
    private final int[] pixels;

    private JFrame progressBarFrame;
    private JProgressBar progressBar;
    private JLabel timeRunningLabel;
    private JLabel timeTillFinishLabel;
    private final double threshold = 0.1;

    public Raytracer(int width, int height) {
        this.height = height;
        this.width = width;
        this.pixels = new int[this.width * this.height];
    }

    public void testScene1(){
        Geometry[] objects = new Geometry[1];
        Plane plane = new Plane(new Point3(0.0, -1.0, 0.0), new Normal3(0.0, 1.0, 0.0), new SingleColorMaterial(new Scene.Color(0, 1, 0)), this.threshold);
        objects[0] = plane;

        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }


    public void testScene2(){
        Geometry[] objects = new Geometry[1];

        Sphere sphere = new Sphere( new Point3(0.0, 0.0, -3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)), this.threshold);
        objects[0] = sphere;

        World world = new World(objects, new Color(0, 0, 0));

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }


    public void testScene3(){
        Geometry[] objects = new Geometry[1];

        AxisAlignedBox box = new AxisAlignedBox(new Point3(-0.5, 0.0, -0.5), new Point3(0.5, 1.0, 0.5), new SingleColorMaterial(new Color(0,0,1)), this.threshold);

        objects[0] = box;

        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(3.0,3.0,3.0), new Vector3(-3.0,-3.0,-3.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }


    public void testScene4(){
        Geometry[] objects = new Geometry[1];

        Triangle triangle = new Triangle(new Point3(-0.5,0.5,-3.0), new Point3(0.5,0.5,-3.0), new Point3(0.5,-0.5,-3.0), new SingleColorMaterial(new Scene.Color(1, 0, 1)), this.threshold);

        objects[0] = triangle;


        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }


    public void testScene5(){
        Geometry[] objects = new Geometry[2];

        Sphere sphere1 = new Sphere( new Point3(-1.0,0.0,-3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)), this.threshold);
        Sphere sphere2 = new Sphere( new Point3(1.0,0.0,-6.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)), this.threshold);

        objects[0] = sphere1;
        objects[1] = sphere2;


        World world = new World(objects, new Color(0, 0, 0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }


    public void testScene6(){
        Geometry[] objects = new Geometry[2];

        Sphere sphere1 = new Sphere( new Point3(-1.0,0.0,-3.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)), this.threshold);
        Sphere sphere2 = new Sphere( new Point3(1.0,0.0,-6.0), 0.5, new SingleColorMaterial(new Color(1, 0, 0)), this.threshold);

        objects[0] = sphere1;
        objects[1] = sphere2;


        World world = new World(objects, new Color(0, 0, 0));
        OrthographicCamera camera = new OrthographicCamera(new Point3(0.0,0.0,0.0), new Vector3(0.0,0.0,-1.0), new Vector3 (0.0,1.0,0.0), 10.0);
        // render the whole thing
        render(camera, world);
    }

    public void testLightning1(){
        Geometry[] objects = new Geometry[4];

        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new SingleColorMaterial(new Scene.Color(1, 0, 0)), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new SingleColorMaterial(new Color(0, 1, 0)), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new SingleColorMaterial(new Color(0,0,1)), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new SingleColorMaterial(new Scene.Color(1, 1, 0)), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;

        World world = new World(objects, new Color(1.0, 0.0, 0.0));
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testLambertLightning(){
        Geometry[] objects = new Geometry[4];

        Plane plane = new Plane( new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new LambertMaterial(new Color(0.6, 0, 0)), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new LambertMaterial(new Color(0, 0.6, 0)), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new LambertMaterial(new Color(0,0,0.6)), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new LambertMaterial(new Scene.Color(0.545, 0.458, 0)), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.5,0.5,0.5), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testPhongLightning(){
        Geometry[] objects = new Geometry[4];
//    public PhongMaterial(Color diffuse, Color specular, int exponent)
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new PhongMaterial(new Color(0.6, 0.0, 0.0), new Color(1.0,1.0,1.0), 64), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new PhongMaterial(new Color(0, 0.6, 0), new Color(1.0,1.0,1.0), 64), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(new Color(0,0,0.6), new Color (1.0,1.0,1.0), 64), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new PhongMaterial(new Color(0.545, 0.458, 0), new Color (1.0,1.0,1.0), 64), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.5,0.5,0.5), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testPhongLightningDirectional(){
        Geometry[] objects = new Geometry[4];
//    public PhongMaterial(Color diffuse, Color specular, int exponent)
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new PhongMaterial(new Color(0.6, 0.0, 0.0), new Color(1.0,1.0,1.0), 64), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new PhongMaterial(new Color(0, 0.6, 0), new Color(1.0,1.0,1.0), 64), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(new Color(0,0,0.6), new Color (1.0,1.0,1.0), 64), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new PhongMaterial(new Color(0.545, 0.458, 0), new Color (1.0,1.0,1.0), 64), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
//        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        lights.add(new DirectionalLight(new Color(1.0,1.0,1.0), new Vector3(-1.0,-1.0,-1.0)));
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.5,0.5,0.5), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testPhongLightningSpotLight(){
        Geometry[] objects = new Geometry[4];
//    public PhongMaterial(Color diffuse, Color specular, int exponent)
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new PhongMaterial(new Color(0.6, 0.0, 0.0), new Color(1.0,1.0,1.0), 64), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new PhongMaterial(new Color(0, 0.6, 0), new Color(1.0,1.0,1.0), 64), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(new Color(0,0,0.6), new Color (1.0,1.0,1.0), 64), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new PhongMaterial(new Color(0.545, 0.458, 0), new Color (1.0,1.0,1.0), 64), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
//        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        lights.add(new SpotLight(new Color(1.0,1.0,1.0), new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), Math.PI/14));
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(-0.0,-0.0,-0.0), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testPhongLightningSpotLightandAmbientLight(){
        Geometry[] objects = new Geometry[4];
//    public PhongMaterial(Color diffuse, Color specular, int exponent)
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new PhongMaterial(new Color(0.6, 0.0, 0.0), new Color(1.0,1.0,1.0), 64), this.threshold);
        Sphere sphere = new Sphere( new Point3(1.0,1.0,1.0), 0.5, new PhongMaterial(new Color(0, 0.6, 0), new Color(1.0,1.0,1.0), 64), this.threshold);
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), new PhongMaterial(new Color(0,0,0.6), new Color (1.0,1.0,1.0), 64), this.threshold);
        Triangle triangle = new Triangle(new Point3(0.0,0.0,-1.0), new Point3(1.0,0.0,-1.0), new Point3(1.0,1.0,-1.0), new PhongMaterial(new Color(0.545, 0.458, 0), new Color (1.0,1.0,1.0), 64), this.threshold);

        objects[0] = plane;
        objects[1] = sphere;
        objects[2] = box;
        objects[3] = triangle;
//     public World(Geometry[] objects, Color backgroundColor, Color ambientLight, List<Light> lights)
        List<Light> lights = new ArrayList<Light>();
//        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(4.0, 4.0, 4.0)));
        lights.add(new SpotLight(new Color(1.0,1.0,1.0), new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), Math.PI/14));
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.25,0.25,0.25), lights);
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(4.0,4.0,4.0), new Vector3(-1.0,-1.0,-1.0), new Vector3 (0.0,1.0,0.0), Math.PI / 4);
        // render the whole thing
        render(camera, world);
    }

    public void testSphere()
    {
        Geometry[] objects = new Geometry[4];
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new ReflectiveMaterial(new Color(0.1, 0.1, 0.1), new Color(0.0, 0.0, 0.0), 64, new Color(0.5, 0.5, 0.5)), this.threshold);
        // red sphere
        Sphere sphere1 = new Sphere(new Point3(-3.0, 1.0, 0.0), 1, new ReflectiveMaterial(new Color(1.0, 0.0, 0.0), new Color(1.0, 1.0, 1.0), 64, new Color(0.5, 0.5, 0.5)), this.threshold);
        // green sphere
        Sphere sphere2 = new Sphere(new Point3(0.0, 1.0, 0.0), 1, new ReflectiveMaterial(new Color(0.0, 1.0, 0.0), new Color(1.0, 1.0, 1.0), 64, new Color(0.5, 0.5, 0.5)), this.threshold);
        // blue sphere
        Sphere sphere3 = new Sphere(new Point3(3.0, 1.0, 0.0), 1, new ReflectiveMaterial(new Color(0.0, 0.0, 1.0), new Color(1.0, 1.0, 1.0), 64, new Color(0.5, 0.5, 0.5)), this.threshold);

        objects[0] = plane;
        objects[1] = sphere1;
        objects[2] = sphere2;
        objects[3] = sphere3;
        // lights
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(8.0, 8.0, 8.0), true));
        // camera
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(8.0, 8.0, 8.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0.0, 1.0, 0.0), Math.PI / 4);
        // world
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.25, 0.25, 0.25), lights);
        // render the whole thing
        render(camera, world);
    }

    public void testAAB()
    {
        Geometry[] objects = new Geometry[2];
        // plane
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new LambertMaterial(new Color(0.8, 0.8, 0.8)), this.threshold);
        // box
        AxisAlignedBox aab = new AxisAlignedBox(new Point3(-0.5, 0.0, -0.5), new Point3(0.5, 1.0, 0.5), new LambertMaterial(new Color(1.0, 0.0, 0.0)), this.threshold);

        objects[0] = plane;
        objects[1] = aab;
        List<Light> lights = new ArrayList<Light>();
        // light
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(8.0, 8.0, 0.0), true));
        // camera
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(8.0, 8.0, 8.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0.0, 1.0, 0.0), Math.PI / 4);
        // world
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.0, 0.0, 0.0), lights);
        // render the whole thing
        render(camera, world);
    }

    public void testSphereTransformation(){

        //Setting up the Test-Sphere
        Geometry[] objects = new Geometry[1];
        Sphere sphere = new Sphere(new Point3(0.0, 0.0, 0.0), 1, new PhongMaterial(new Color(0.6, 0.0, 0.0), new Color(1.0,1.0,1.0), 64), this.threshold);

        //Transforming Objects
        List<Geometry> transobjects = new ArrayList<>();
        transobjects.add(sphere);
        Node node = new Node(new Transform().rescale(6.0, 1.2, 4.0), transobjects, this.threshold);

        objects[0]=node;

        // lights
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(1.0, 20.0, 20.0), true));
        // camera
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(20.0, 20.0, 20.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0.0, 1.0, 0.0), Math.PI / 4);
        // world
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.25, 0.25, 0.25), lights);
        // render the whole thing
        render(camera, world);
    }

    public void testBoxTransformation(){

        //Setting up the Test-Box
        Geometry[] objects = new Geometry[1];
        AxisAlignedBox aab = new AxisAlignedBox(new Point3(-0.5, -0.5, -0.5), new Point3(0.5, 0.5, 0.5), new LambertMaterial(new Color(1.0, 1.0, 0.0)), this.threshold);

        //Transforming Objects
        List<Geometry> transobjects = new ArrayList<>();
        transobjects.add(aab);
        Node node = new Node(new Transform().rescale(4.0, 1.0, 8.0).rotateZ(Math.toRadians(-20)).rotateY(Math.toRadians(-5)).rotationOnX(Math.toRadians(-30)), transobjects, this.threshold);

        objects[0] = node;
        // lights
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(1.0, 20.0, 20.0), true));
        // camera
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10.0, 10.0, 10.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0.0, 1.0, 0.0), Math.PI / 4);
        // world
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.25, 0.25, 0.25), lights);
        // render the whole thing
        render(camera, world);
    }

    public void testDisc()
    {
        Geometry[] objects = new Geometry[2];
        Plane plane = new Plane(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new ReflectiveMaterial(new Color(1.0, 0.0, 0.0), new Color(0.0, 0.0, 0.0), 64, new Color(0.5, 0.5, 0.5)), this.threshold);
        // red sphere
       Disc disc1 = new Disc(new Point3(0.0, 0.0, 0.0), new Normal3(0.0, 1.0, 0.0), new ReflectiveMaterial(new Color(0.0, 1.0, 0.0), new Color(1.0, 1.0, 1.0), 2000, new Color(0.5, 0.5, 0.5)), 5.0, this.threshold);
        // green sphere
        //Disc disc2 = new Disc(new Point3(0.0, 1.0, 0.0), new Normal3(1.0, 0.0, 0.0), new ReflectiveMaterial(new Color(0.0, 1.0, 0.0), new Color(1.0, 1.0, 1.0), 64, new Color(0.5, 0.5, 0.5)), 3.0);
        // blue sphere
        //Disc disc3 = new Disc(new Point3(3.0, 1.0, 0.0), new Normal3(0.0, 0.0, 1.0), new ReflectiveMaterial(new Color(0.0, 0.0, 1.0), new Color(1.0, 1.0, 1.0), 64, new Color(0.5, 0.5, 0.5)), 4.0);

        objects[0] = disc1;
        objects[1] = plane;
        //objects[2] = disc2;
        //objects[3] = disc3;
        // lights
        List<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Color(1.0, 1.0, 1.0), new Point3(8.0, 8.0, 8.0), true));
        // camera
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(8.0, 8.0, 8.0), new Vector3(-1.0, -1.0, -1.0), new Vector3(0.0, 1.0, 0.0), Math.PI / 4);
        // world
        World world = new World(objects, new Color(0.0, 0.0, 0.0), new Color(0.1, 0.1, 0.1), lights);

        for(int y = 0; y < this.height; y++)
        {
            for(int x = 0; x < this.width; x++)
            {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);
                if(hit != null) {
                    RecursiveTracer tracer = new RecursiveTracer(world, 6);
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world, tracer).asHex();
                }
                else {
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
                }
            }
        }

        Painter p = new Painter(this.width, this. height, this.pixels);
        p.draw();
    }

    public void testGeometries()
    {
        testDisc();
    }

    public void testAllTransformations()
    {
        testSphereTransformation();
        testBoxTransformation();
    }

    public void testAllMaterialScenes()
    {
        testSphere();
        testAAB();
    }

    public void testAllLightningScenes(){
        testLightning1();
        testLambertLightning();
        testPhongLightning();
        testPhongLightningDirectional();
        testPhongLightningSpotLight();
        testPhongLightningSpotLightandAmbientLight();
    }

    public void testAllsimpleScenes(){
        this.testScene1();
        this.testScene2();
        this.testScene3();
        this.testScene4();
        this.testScene5();
        this.testScene6();
    }

    private void setupProgessBar() {
        this.progressBarFrame = new JFrame("Rendering...");
        this.progressBarFrame.setAlwaysOnTop(true);
        this.progressBarFrame.setSize(300, 110);
        this.progressBarFrame.setResizable(false);
        this.progressBarFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();

        this.timeRunningLabel = new JLabel("Time Running: 00:00");
        this.timeTillFinishLabel = new JLabel("Estimated Time remaining: 00:00");
        this.progressBar = new JProgressBar(0, this.pixels.length);
        this.progressBar.setBounds(30, 15, 240, 60);
        this.progressBar.setStringPainted(true);
        panel.add(this.timeRunningLabel);
        panel.add(this.timeTillFinishLabel);
        panel.add(this.progressBar);
        this.progressBarFrame.add(panel);
        this.progressBarFrame.setVisible(true);
    }

    private void render(Camera camera, World world) {
        Painter p = new Painter(this.width, this.height, this.pixels);

        /**
         * setup for progress visualisation
         */
        int start = (int) (System.currentTimeMillis() / 10);
        int progress = 0;
        this.setupProgessBar();
        int averageTime = 0;

        double lastAverageTime;

        if(world.lights == null){
            lastAverageTime = (this.width * this.height* world.objects.length + 100000) / 100000;
        } else if (world.objects == null) {
            lastAverageTime = (this.width * this.height * world.lights.size()  + 100000) / 100000;
        } else lastAverageTime = (this.width * this.height * world.lights.size() * world.objects.length + 100000) / 100000;

        int intervall = 60;

        for(int y = 0; y < this.height; y++) {
            int timePerLine = (int) (System.currentTimeMillis());
            for(int x = 0; x < this.width; x++) {
                Ray ray = camera.rayFor(this.width, this.height, x, y);
                Hit hit = world.hit(ray);

                if (hit != null) {
                    RecursiveTracer recursiveTracer = new RecursiveTracer(world, 6);
                    pixels[y * this.width + x] = hit.geo.material.colorFor(hit, world, recursiveTracer).asHex();
                }
                else
                    pixels[y * this.width + x] = world.backgroundColor.asHex();
            }

            timePerLine = (int) (System.currentTimeMillis()) - timePerLine;

            /**
             * calculates and seet the progress for the progressBar
             */
            progress = y * this.width + 800;
            this.progressBar.setValue(progress);
            p.draw();

            /**
             * calculates the time it has been processed
             */
            int s = (int) (System.currentTimeMillis() / 10) - start;
            String currentTime = "Time running: " + String.format("%d:%02d:%02d", s/3600, (s%3600)/60, (s%60));
            this.timeRunningLabel.setText(String.valueOf(currentTime));

            /**
             * calculates the estimated remaining time
             */
            averageTime = averageTime + timePerLine;

            int s2 = 0;
            if (lastAverageTime - averageTime / intervall < 3){
                lastAverageTime = averageTime / intervall;
                 s2 = (int) (lastAverageTime * (this.height - y - 1) / 10);
            }
            else {
                s2 = (int) (lastAverageTime * (this.height - y - 1) / 10);
            }
            String remainingTime = "Estimated Time remaining: " + String.format("%d:%02d:%02d", s2/3600, (s2%3600)/60, (s2%60));
            this.timeTillFinishLabel.setText(remainingTime);

            /**
             * hides progressbarFrame if finished
             */
            if (this.progressBar.getMaximum() - progress < this.width * 2) {
                this.progressBarFrame.setVisible(false);
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Raytracer raytracer = (Raytracer) o;

        if (width != raytracer.width) return false;
        if (height != raytracer.height) return false;
        if (Double.compare(raytracer.threshold, threshold) != 0) return false;
        if (!Arrays.equals(pixels, raytracer.pixels)) return false;
        if (progressBarFrame != null ? !progressBarFrame.equals(raytracer.progressBarFrame) : raytracer.progressBarFrame != null)
            return false;
        return !(progressBar != null ? !progressBar.equals(raytracer.progressBar) : raytracer.progressBar != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = width;
        result = 31 * result + height;
        result = 31 * result + (pixels != null ? Arrays.hashCode(pixels) : 0);
        result = 31 * result + (progressBarFrame != null ? progressBarFrame.hashCode() : 0);
        result = 31 * result + (progressBar != null ? progressBar.hashCode() : 0);
        temp = Double.doubleToLongBits(threshold);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Raytracer{" +
                "width=" + width +
                ", height=" + height +
                ", pixels=" + Arrays.toString(pixels) +
                ", progressBarFrame=" + progressBarFrame +
                ", progressBar=" + progressBar +
                ", threshold=" + threshold +
                '}';
    }
}
