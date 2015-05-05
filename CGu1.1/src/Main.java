import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		/* Framesize */
		int height = 480;
		int width = 640;
		
		BufferedImage bi = new BufferedImage(width, height, 0);
		bi.addTileObserver(null);
		bi.setRGB(0, 0, 0);
		
		JFrame frame = new JFrame();
		frame.setTitle("CG I - Übung 1.1");
		frame.setResizable(false);
		frame.setSize(width, height); /* Größe des Fensters auf Bildgröße anpassen */
		
		
		frame.setVisible(true);

	}

}
