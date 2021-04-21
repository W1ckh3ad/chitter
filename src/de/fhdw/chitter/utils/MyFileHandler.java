package de.fhdw.chitter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFileHandler {
    public static Path convertStringToPath(String path) {
        return Paths.get(path);
    }

    public static boolean fileExists(String filename) {
        try {
            var useless = new BufferedReader(new FileReader(filename));
            useless.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception ioError) {
            ioError.printStackTrace();
            return false;
        }
        return true;
    }

    public static void createFile(File file) throws IOException {
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void createFile(String filename) throws IOException {
        var file = new File(filename);
        createFile(file);
    }

    public static void createFileWithDirectory(String filename) {
        try {
            File file = new File(filename);
            var pathesWithFile = filename.split("/");
            if (pathesWithFile.length > 1) {
                var pathesWithoutFile = new String[pathesWithFile.length - 1];
                for (int i = 0; i < pathesWithFile.length - 1; i++) {
                    pathesWithoutFile[i] = pathesWithFile[i];
                }
                String dirPath = String.join("/", pathesWithoutFile);
                new File(dirPath).mkdir();
                System.out.println("Created dir:" + dirPath);
            }
            createFile(file);
        } catch (IOException e) {
            System.out.println("Error while creating File: " + filename);
            e.printStackTrace();
        }
    }

    public static String readFromFile(String filename) throws IOException {
        return Files.readString(convertStringToPath(filename));
    }

    public static void writeToFile(String filename, String string) throws IOException {
        Files.writeString(convertStringToPath(filename), string);
    }

    public static String[] getFileNames(String dir) {
        return new File(dir).list();
    }
}
