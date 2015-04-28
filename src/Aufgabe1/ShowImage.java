package Aufgabe1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Jonathan on 28.04.15.
 * This class opens a dialog to choose a image, which get display afterwards
 */


public class ShowImage extends Panel {

    BufferedImage image;

    public ShowImage() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                image = ImageIO.read(file);
                this.setSize(image.getWidth(), image.getHeight());
            }
        } catch (IOException ie) {
            System.out.println("Error:"+ie.getMessage());
        }
    }

    public void paint(Graphics g) {
        g.drawImage( image, 0, 0, null);
    }

    public static void main(String args[]) throws Exception {
        JFrame frame = new JFrame("Display image");
        Panel panel = new ShowImage();
        frame.getContentPane().add(panel);
        frame.setSize(panel.getWidth(), panel.getHeight());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}