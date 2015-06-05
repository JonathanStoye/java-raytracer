import MatrixVector.Mat3x3;
import MatrixVector.Vector3;
import Scene.Color;
import Visualization.Painter;
import Visualization.ShowImage;

/**
 * Created by Jonathan on 28.04.15.
 * This is the main class which starts the raytracer and bootstraps the hole thing
 */
public class Main {

    public static void main(String [] args) {
        testAll();
    }

    private static void testAll() {
        Mat3x3.test();
        Vector3.test();
        Painter.test();
        ShowImage.test();
        Color.test();
    }
}
