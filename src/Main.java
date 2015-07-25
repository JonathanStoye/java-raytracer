import Scene.Raytracer;
import Utillities.Debugging;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jonathan on 28.04.15.
 * This is the main class which starts the raytracer and bootstraps the hole thing
 */
public class Main {

    public static void main(String [] args) {
        //testAll();
//        Color.test();
        Raytracer raytracer = new Raytracer(800, 600);
//        raytracer.testAllTransformations();
        raytracer.testAAB();

//        JFrame frame = new JFrame("Rendering in progress..");
//        frame.setSize(300, 110);
//        frame.setResizable(false);
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        JProgressBar progressBar = new JProgressBar(0, 100);
//        progressBar.setBounds(30, 15, 240, 60);
//        panel.add(progressBar);
//        frame.add(panel);
//        frame.setVisible(true);
//        progressBar.setValue(30);
//        progressBar.setValue(60);
//        progressBar.setValue(90);
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
