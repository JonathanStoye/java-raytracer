package Aufgabe1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import utilities.Debugging;

/**
 * Created by Jonathan on 28.04.15.
 * This class opens a dialog to choose a image, which gets displayed afterwards
 */
public class ShowImage extends JFrame {

    /**
     * Image which gets painted on this
     */
    BufferedImage image;

    /**
     * standard constructor just calls .chooseFile() and catches exceptions
     */
    public ShowImage() {
        try {
            this.chooseFile();
        } catch (IOException ie) {
            Debugging.log("Error: " + ie.getMessage());
        }
    }

    /**
     * paints the selected Image this.image by overriding the default paint method with always painting this.image
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Asks the user to choose an image and sets it to this.image. If this.image is not a .jpeg or a .png it prompts an error message and closes the app.
     * @throws IOException if the file is not found
     */
    private void chooseFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();

            if (Debugging.getFileExtension(file).equals(".jpeg") || Debugging.getFileExtension(file).equals(".jpg") || Debugging.getFileExtension(file).equals(".png")) {
                image = ImageIO.read(file);
                this.setSize(image.getWidth(), image.getHeight());
                this.setTitle(file.getName());
            }
            else {
                JOptionPane.showMessageDialog(this.getComponent(0), "Fehler: \nBitte wählen sie ein \".jpg\" oder \".png\" aus.");
                System.exit(0);
            }
        }
    }

    /**
     * creates a new instance of this and sets the needed parameters
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ShowImage frame = new ShowImage();
        frame.setLocation((int) screenSize.getWidth() / 2 - frame.getWidth() / 2, (int) screenSize.getHeight() / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}