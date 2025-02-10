package main;

import javax.swing.*;
import java.io.File;

public class Methods {
    /*
    there used to be a bunch of static methods in here but now it's
    mostly empty
     */

    public static ImageIcon createImageIcon(String path,
                                            String description) {
        return new ImageIcon(path, description);
    }
}
