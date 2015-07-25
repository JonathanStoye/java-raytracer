package Visualization;

import Utillities.Debugging;

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
    public int progress = 1;
    private int imageWidth;
    private int imageHeight;
    private int[] pixels;
    private JFrame parent;
    private BufferedImage image;
    private JPanel panel;

    private JFrame progressBarFrame;
    private JProgressBar progressBar;


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
        this.image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    private void setupFrame(){
        this.setResizable(false);
        this.setupPanel();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setupMenuBar();
        this.setSize(new Dimension(this.imageWidth, this.imageHeight + 20));
        this.setLocationRelativeTo(null);
        this.setupProgessBar();
    }

    private void setupPanel(){
        this.panel = new MyJPanel();
        this.panel.setPreferredSize(new Dimension(imageWidth, imageHeight));
        this.add(this.panel);
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
    }

    private void setupProgessBar() {
        this.progressBarFrame = new JFrame("Rendering...");
        progressBarFrame.setAlwaysOnTop(true);
        progressBarFrame.setSize(300, 110);
        progressBarFrame.setResizable(false);
        progressBarFrame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.progressBar = new JProgressBar(0, this.pixels.length);
        this.progressBar.setBounds(30, 15, 240, 60);
        this.progressBar.setStringPainted(true);
        panel.add(this.progressBar);
        progressBarFrame.add(panel);
        progressBarFrame.setVisible(true);
    }

    private void save(File file){
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void draw(){
        for(int y = 0; y < this.imageHeight; y++) {
            for (int x = 0; x < this.imageWidth; x++) {
                this.image.setRGB(x, this.imageHeight - 1 - y, this.pixels[y * this.imageWidth + x]);
            }
        }
        this.progressBar.setValue(this.progress);
        this.repaint();
        this.setVisible(true);

        if (this.progressBar.getMaximum() == this.progress)
            this.progressBarFrame.setVisible(false);
    }
}
