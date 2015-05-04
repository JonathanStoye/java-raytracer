import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {

	public static void main(String[] args) throws IOException {
		
		File file; // File wo image reingeladen wird
		
		//Filter
		FileFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");
				
		//OpenDialog
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(filter);
		fc.showOpenDialog(null);
		
		//Bild �bergeben
		file = fc.getSelectedFile();
		
		
		//Bild laden
		Image img = ImageIO.read(file);

		
		//In JFrame laden
		JFrame frame = new JFrame();
		frame.setTitle("CG I - �bung 1");
		frame.setResizable(false);
		frame.setSize(img.getWidth(fc), img.getHeight(fc)); // Gr��e des Fensters auf Bildgr��e anpassen
		
		JLabel l = new JLabel(new ImageIcon(img)); // Bild auf label
		frame.add(l);
		
		frame.setVisible(true);
		
		

	}

}
