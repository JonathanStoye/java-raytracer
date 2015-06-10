package Visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jonathan on 09.06.15.
 * Implemented by David on 10.06.2015.
 */
public class Painter extends JFrame{
    private int worldWidth;
    private int worldHeight;
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

    public Painter(int worldWidth, int worldHeight, int[] pixels) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.pixels = pixels;
        this.setupFrame();
    }

    private void setupFrame(){
        this.parent = new JFrame();
        this.parent.setVisible(true);
        this.parent.setResizable(true);
        this.parent.setSize(new Dimension((int) (worldWidth+16), (int) (60+worldHeight)));
        this.setupPanel(this.parent);
        this.parent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar(this.parent);
    }

    private void setupPanel(JFrame currentParent){
        panel = new MyJPanel();
        panel.setPreferredSize(new Dimension((int) worldWidth, (int) worldHeight));
        panel.setLocation(0, 0);
        currentParent.add(panel);
    }

    private void setupMenuBar(JFrame currentParent){
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
        currentParent.setJMenuBar(menuBar);
        currentParent.setVisible(true);
    }

    private void save(File file) {
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void draw(){
        this.image = new BufferedImage((int) worldWidth, (int)worldHeight, BufferedImage.TYPE_INT_RGB);
        for(int i=0; i<worldHeight; i++){
            for (int x=0; x<worldWidth; x++){
                drawPixel(i, x, pixels[i]);
            }
        }

    }

    private void drawPixel(int y, int x, int colorValue) {
        this.image.setRGB(x, y, colorValue);
    }

}
