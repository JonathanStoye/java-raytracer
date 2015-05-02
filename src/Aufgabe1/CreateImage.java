package Aufgabe1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * The Class CreateImage creates an Image based on a simple Coordinate-System.
 *
 * Created by David on 28.04.2015.
 */
public class CreateImage extends JFrame{

    BufferedImage buff;
    JPanel panel;

    public CreateImage(){
        System.out.println("neues Objekt vom Typ \"createImage\" wurde erzeugt");
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("Das Fenster wird groesser.");
                createKoordinatensystem(e.getComponent().getWidth(), e.getComponent().getHeight());
            }
        });
    }

    /**
     * The private class MyJPanel is used to override the .paint(Graphics g) Method in order to ensure
     * that the Panel is really shown.
     * */
    private class MyJPanel extends JPanel{
        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(buff, 0, 0, null);
        }
    }

    /**
     * The Method createKoordinatensystem builds a panel in the given size.
     * Then a BufferedImage is used in order to Draw Pixels in the Area.
     * */
    public void createKoordinatensystem(int breite, int hoehe){

        /**
         * A new Panel is created, which inherits all the Elements shown in the Application.
         * */
        System.out.println("Erzeugen des Panels mit einer Hoehe von: "+hoehe+" und einer Breite von: "+breite);
        panel = new MyJPanel();
        panel.setPreferredSize(new Dimension(breite, hoehe));
        panel.setLocation(50, 50);
        this.setSize(new Dimension(breite, hoehe));
        this.add(panel);

        /**
         * The BufferedImage buff is used to set the pixels based on RGB values.
         * */
        System.out.println("Neues Image wird erzeugt.");
        buff = new BufferedImage(breite, hoehe, BufferedImage.TYPE_INT_RGB);
        int rgb = 0xFFFF0000;
        int width = buff.getWidth();
        int height = buff.getHeight();
        int[] rgbValues = new int[width * height];

        buff.getRGB(0,0,width,height,rgbValues,0,width);

        /**
         * The Image is now filled with Pixels drawing a diagonal line.
         * */
        if(width>height) {
            for (int i = 0; i < height; i++) {
                buff.setRGB(i, i, rgb);
            }
        } else {
            for (int i = 0; i < width; i++) {
                buff.setRGB(i, i, rgb);
            }
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.BLACK);
    }
}
