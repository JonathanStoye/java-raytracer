package Aufgabe1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Class CreateImage creates an Image based on a simple Coordinate-System.
 *
 * Created by David on 28.04.2015.
 */
public class CreateImage extends JFrame {

    BufferedImage buff;
    JPanel panel;

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

    public CreateImage(){
        System.out.println("neues Objekt vom Typ \"createImage\" wurde erzeugt");
    }

    /**
     * The Method createKoordinatensystem builds a panel in the given size.
     * Then a BufferedImage is used in order to Draw Pixels in the Area.
     * */
    public void createKoordinatensystem(int hoehe, int breite){

        /**
         * A new Panel is created, which inherits all the Elements shown in the Application.
         * */
        System.out.println("Erzeugen des Panels mit einer Hoehe von: "+hoehe+" und einer Breite von: "+breite);
        panel = new MyJPanel();
        add(panel);
        panel.setPreferredSize(new Dimension(hoehe, breite));
        setSize(hoehe, breite);

        /**
         * The BufferedImage buff is used to create a Graphics Element and to setup the RGB Colors.
         * */
        System.out.println("Neues Image wird erzeugt.");
        buff = new BufferedImage(hoehe, breite, BufferedImage.TYPE_INT_RGB);
        int rgb = buff.getRGB(1,1);
        int width = buff.getWidth();
        int height = buff.getHeight();

        buff.setRGB(1,1,rgb);

        Image g = buff.getScaledInstance(hoehe, breite, 1);




        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
