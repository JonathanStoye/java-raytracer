import MatrixVector.Mat3x3;
import MatrixVector.Vector3;
import Scene.Color;
import Scene.Raytracer;
import Visualization.PaintDiagonal;
import Visualization.ShowImage;

/**
 * Created by Jonathan on 28.04.15.
 * This is the main class which starts the raytracer and bootstraps the hole thing
 */
public class Main {

    public static void main(String [] args) {
        //testAll();
//        Color.test();
        Raytracer raytracer = new Raytracer(100, 100);
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
