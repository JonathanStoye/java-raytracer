package Visualization;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import Utilities.Debugging;

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
        File file = this.chooseFile();
        if (this.testFile(file))
            this.setFile(file);
        else
            JOptionPane.showMessageDialog(this.getComponent(0), "Fehler: \nBitte wählen sie ein \".jpg\" oder \".png\" aus.");
            System.exit(0);
    }

    public ShowImage(File file) throws IOException {
        if (this.testFile(file)) {
            this.setFile(file);
        }
        else {
            JOptionPane.showMessageDialog(this.getComponent(0), "Fehler: \nBitte wählen sie ein \".jpg\" oder \".png\" aus.");
            System.exit(0);
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
    private File chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            final File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }

    /**
     * test whether the image is a jpeg or png file
     * if not it notifies the user
     * @param file
     */
    private boolean testFile(File file) {
        if (Debugging.getFileExtension(file).equals(".jpeg") || Debugging.getFileExtension(file).equals(".jpg") || Debugging.getFileExtension(file).equals(".png")) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * set file to view
     * @param file
     */
    private void setFile(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(image.getWidth(), image.getHeight());
        this.setTitle(file.getName());
    }

    /**
     * performs a test to test all class functionality with user interaction
     * @throws Exception
     */
    public static void testUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ShowImage frame = new ShowImage();
        frame.setLocation((int) screenSize.getWidth() / 2 - frame.getWidth() / 2, (int) screenSize.getHeight() / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * test all class functionality fully automated
     * relies on the generated testPicture of PaintDiagonal
     */
    public static void test() {
        File file = new File("test.png");
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ShowImage frame = new ShowImage(file);
            frame.setLocation((int) screenSize.getWidth() / 2 - frame.getWidth() / 2, (int) screenSize.getHeight() / 2 - frame.getHeight() / 2);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            Debugging.log("test ShowImage successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}