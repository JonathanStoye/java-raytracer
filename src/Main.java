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
        /*
        Mat3x3.test();
        Vector3.test();
        Painter.test();
        ShowImage.test();
        Color.test();
        */
    // TEST Array für alle Pixel in diesem Fall 200x200.
        //
        int[] testPixelArray = new int[40000];

        // filling testArray with RGB= Blue = 0xFF0000FF; (Hexadezimal representation)
        // Syntax: 0xff
        //             00      (first two values for RED;
        //               00    (second two values for GREEN;
        //                 00  (thirs two values for BLUE;
        for (int i=0; i<=39999; i++){
            testPixelArray[i]=0xff0000ff;
        }
        Painter test = new Painter(200, 200, testPixelArray);
        test.draw();

    }
}
