import Scene.Raytracer;

/**
 * Created by Jonathan on 28.04.15.
 * This is the main class which starts the raytracer and bootstraps the hole thing
 */
public class Main {

    public static void main(String [] args) {
        //testAll();
//        Color.test();
        Raytracer raytracer = new Raytracer(500, 500);
//        raytracer.testAllTransformations();
//        raytracer.testDisc();
//        raytracer.testAAB();
//        raytracer.testSphere();
        raytracer.testSphereTransparent();
    }

    private static void testAll() {
        /*
        Mat3x3.test();
        Vector3.test();
        PaintDiagonal.test();
        ShowImage.test();
        Color.test();
        */
    }
}