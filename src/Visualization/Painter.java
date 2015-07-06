package Visualization;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jonathan on 09.06.15.
 * Implemented by David on 10.06.2015.
 */
public class Painter extends JFrame{
    private int imageWidth;
    private int imageHeight;
    private int[] pixels;
    private JFrame parent;
    private BufferedImage image;
    private JPanel panel;


    private class MyJPanel extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }

    public Painter(int imageWidth, int imageHeight, int[] pixels) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.pixels = pixels;
        this.setupFrame();
    }

    private void setupFrame(){
        this.setVisible(true);
        this.setResizable(false);
        this.setupPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar();
        this.setSize(new Dimension(imageWidth, imageHeight + 20));
    }

    private void setupPanel(){
        panel = new MyJPanel();
        panel.setPreferredSize(new Dimension(imageWidth, imageHeight));
        panel.setLocation(0, 0);
        this.add(panel);
    }

    private void setupMenuBar(){
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
                String path = fc.getSelectedFile().getPath() + ".png";
                File imageFile = new File(path);
                save(imageFile);
            }
        });
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    private void save(File file) {
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void draw(){
        this.image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        for(int y = 0; y < imageHeight; y++){
            for (int x = 0; x < imageWidth; x++){
                this.image.setRGB(x, imageHeight-1-y, pixels[y * imageWidth + x]);
            }
        }
        this.repaint();
    }
}
