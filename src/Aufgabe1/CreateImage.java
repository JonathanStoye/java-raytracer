package Aufgabe1;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * Created by David on 28.04.2015.
 */
public class CreateImage extends JPanel {

    public CreateImage(){
        System.out.println("JFrame wird erzeugt.");
        setupJFrame();
    }

    private void setupJFrame(){
        JFrame createdImageJFrame = new JFrame("ShowImage");
        createdImageJFrame.setVisible(true);
        createdImageJFrame.setSize(640, 480);


        BufferedImage xySystem = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        Graphics grafic = xySystem.getGraphics();
        grafic.drawString("blablubbtest", 20, 20);

        createdImageJFrame.repaint();
    }
}
