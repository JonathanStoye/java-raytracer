package Utillities;

import java.io.File;

/**
 * Created by Jonathan on 14.04.15.
 * a bundle of classes I often use
 */
public abstract class Debugging {

    /**
     * prints
     * @param string
     * to the console
     */
    public static void log (String string) {
        System.out.println(string);
    }

    /**
     * returns the file extension of a given file
     */
    public static String getFileExtension(File f) {
        String fileName = f.getName();
        return fileName.substring(fileName.indexOf(".")).toString();
    }
}
