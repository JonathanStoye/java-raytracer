package Visualization;

import utilities.Debugging;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Class PaintDiagonal creates an Image based on a JFrame and will be filled up with a Pane and an Image. *
 * Created by David on 28.04.2015.
 */
public class PaintDiagonal extends JFrame{

    BufferedImage buff;
    JPanel panel;
    private int backGroundColor;
    private int pixelColor;
    private int width;
    private int height;

    public PaintDiagonal() throws IOException{
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                super.componentResized(e);
                try {
                    drawImage(e.getComponent().getWidth(), e.getComponent().getHeight(), backGroundColor, pixelColor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    /**
     * The private class MyJPanel is used to override the .PaintDiagonal(Graphics g) Method in order to ensure
     * that the Panel is really shown.
     * */
    private class MyJPanel extends JPanel{
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(buff, 0, 0, null);
        }
    }
    /**
     * The Method drawImage builds a panel in the given size.
     * Then a BufferedImage is used in order to Draw Pixels in the Area.
     * */
    public void drawImage (int breite, int hoehe, int bColor, int pColor) throws IOException{
        /**
         * Setting up the Colors for background and pixels.
         * Also setting up width and height.
         * */
        backGroundColor = bColor;
        pixelColor = pColor;
        width = breite;
        height = hoehe;

        /**
         * A new Panel is created, which inherits all the Elements shown in the Application.
         * */
        panel = new MyJPanel();
        panel.setPreferredSize(new Dimension(breite, hoehe));
        panel.setLocation(50, 50);

        /**
         * The corresponding Frame is set up with the same size as the panel.
         * The DefaultCloseOperation will close the Application once the X-Button is pushed.
         * The Background Color of the Frame is set to BLACK by default.
         * */
        this.setSize(new Dimension(breite, hoehe));
        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /**
         * Setting up the MenuBar in order to be able to Save the Picture.
         * */
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Save");
        JMenuItem save = new JMenuItem("save picture");
        menu.add(save);
        menuBar.add(menu);
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Save as PNG", "png");
        fc.setFileFilter(extension);
        save.addActionListener(e -> {
            if (fc.showSaveDialog(save) == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath()+".png";
                File imageFile = new File(path);
                save(imageFile);
            }
        });
        this.setJMenuBar(menuBar);
        this.setVisible(true);


        /**
         * The BufferedImage buff is used to set the pixels based on RGB values.
         * */
        buff = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_RGB);


        /**
         * The Image is filled with the pixels colored by the preferred RGB-Value.
         * */
        for(int i=0; i<height;i++){
            for(int w=0; w<width; w++){
                buff.setRGB(w, i, backGroundColor);
            }
        }

        /**
         * The Image is now filled with Pixels drawing a diagonal line.
         * */
        if(width>height) {
            for (int i = 0; i < height; i++) {
                buff.setRGB(i, i, pixelColor);
            }
        } else {
            for (int i = 0; i < width; i++) {
                buff.setRGB(i, i, pixelColor);
            }
        }

    }

    /**
     * saves the given file
     * @param file
     */
    private void save(File file) {
        try {
            ImageIO.write(buff, "png", file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * used to test the functionality of this class
     */
    public static void test() {
        try {
            PaintDiagonal test = new PaintDiagonal();
            test.drawImage(640, 480, 0xFF000000, 0xFFFF0000);
            test.setVisible(false);
            String path = "test.png";
            File file = new File(path);
            test.save(file);
            Debugging.log("test PaintDiagonal successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
