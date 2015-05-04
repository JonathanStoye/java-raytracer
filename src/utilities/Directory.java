package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;

/**
 * Created by Jonathan on 15.04.15.
 * A class to represent a filesystem with a basepath that must not be null
 *
 * It saves every File it contains in @param files an every Directory it contains in @param directories
 */
public class Directory extends File {

    final private ObservableList <File> files = FXCollections.observableArrayList();
    final private ObservableList<Directory> directories = FXCollections.observableArrayList();
    public boolean debugging = false;


    public ObservableList<Directory> getDirectories() {
        return directories;
    }

    public ObservableList<File> getFiles() {
        return files;
    }

    public Directory(String pathname) {

        super(pathname);

        /**
         * list all items @param this contains and check whether it is a File or a Directory an store it accordingly
         */
        for (File file : this.listFiles()) {

            if (file.isFile()) {

                this.files.add(file);

                if (debugging)
                    Debugging.log(file.getName());
            }
            else if (file.isDirectory()) {

                if (debugging)
                    Debugging.log(file.getPath());

                Directory dir = new Directory(file.getPath());
                this.directories.add(dir);
            }
        }
    }

    /**
     * instead of returning the path it returns
     * @return the name
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
