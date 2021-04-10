package de.fhdw.chitter.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import de.fhdw.chitter.models.NewsMessage;
import de.fhdw.chitter.models.NewsMessageTopic;

public class MyFileHandler {
    public static boolean fileExists(String filename) {
        try {
            var useless = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            return false;
        } catch (Exception ioError) {
            ioError.printStackTrace();
            return false;
        }
        return true;
    }

    public static void createFile(String filename) {
        try {
            File file = new File(filename);
            // File dir = new File(file.getPath());
            System.out.println(filename);
            // if (!dir.exists()) {
            // dir.mkdirs();
            // }
            var pathesWithFile = filename.split("/");
            if (pathesWithFile.length > 1) { // data/dashjkfdbasfkjbas --> data
                var pathesWithoutFile = new String[pathesWithFile.length - 1];
                for (int i = 0; i < pathesWithFile.length - 1; i++) {
                    pathesWithoutFile[i] = pathesWithFile[i];
                }
                String dirPath = String.join("/", pathesWithoutFile);
                new File(dirPath).mkdir();
                System.out.println("Created dir:" + dirPath);

            }

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error while creating File: " + filename);
            e.printStackTrace();
        }
    }

    public static NewsMessage readFromFile(String filename) throws IOException {

        BufferedReader myReader = new BufferedReader(new FileReader(filename));
        var model = new NewsMessage(myReader.readLine(), myReader.readLine(), NewsMessageTopic.valueOf(myReader.readLine()), myReader.readLine());

        StringBuffer msgBuffer = new StringBuffer();
        String line = myReader.readLine();
        while (line != null) {
            msgBuffer.append(line);
            line = myReader.readLine();
        }
        model.setText(msgBuffer.toString());

        System.out.println("Successfully read from file.");
        myReader.close(); // neu
        return model;
    }

    public static void writeToFile(String filename, NewsMessage model) {
        // if (!fileExists(filename)) {
        System.out.println("File Existiert:" + fileExists(filename));
        createFile(filename);
        // }

        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(model.getDate());
            myWriter.write("\n");
            myWriter.write(model.getAuthor());
            myWriter.write("\n");
            myWriter.write(model.getTopic().toString());
            myWriter.write("\n");
            myWriter.write(model.getHeadline());
            myWriter.write("\n");
            myWriter.write(model.getText());
            myWriter.write("\n");

            myWriter.close();
            System.out.println("Successfully write to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String[] getFileNames(String dir) {
        return new File(dir).list();
    }
}
