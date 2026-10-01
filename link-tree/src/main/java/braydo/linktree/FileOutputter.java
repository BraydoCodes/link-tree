package braydo.linktree;


import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * The FileOutputter objective is print the string representation of an object to a file,
 * in this project it is currently used for LinkTrees
 */
public class FileOutputter {
    private static String workingFileDirectory = "output";

    public FileOutputter(String newDir){
        workingFileDirectory = newDir;
    }

    /**
     * This method will print the string representation to .txt file of the current @workingFileDirectory
     * @param object, an object with a .toString() method with the desired string to be printed
     * @return whether the operation was successful or not
     */
    static boolean printObjectToFile(Object object){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(workingFileDirectory.concat(".txt")),
                StandardCharsets.UTF_8))) {
            writer.write(object.toString());
        } catch (IOException e){
            System.out.println("Resulting operation failed: \n".concat(e.getMessage()));
            return false;
        }
        return true;
    }

}
