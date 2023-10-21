package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileService {
    final String filePath;

    public FileService(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readFileText() {
        List stringList = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public void appendStringIntoFile(String string , boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(string + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeOutputIntoFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        appendStringIntoFile("9",false);
        appendStringIntoFile("Asgard\n" +
                "4\n" +
                "2 1\n" +
                "4 2\n" +
                "5 3\n" +
                "8 1", true);
        appendStringIntoFile("Vanaheimr\n" +
                "4\n" +
                "1 1\n" +
                "7 4\n" +
                "9 3\n" +
                "8 2",true);
        appendStringIntoFile("Alfheim\n" +
                "3\n" +
                "4 2\n" +
                "6 4\n" +
                "7 1", true);
        appendStringIntoFile("Midgard\n" +
                "3\n" +
                "3 2\n" +
                "1 2\n" +
                "6 1", true);
        appendStringIntoFile("Jotunheimr\n" +
                "3\n" +
                "1 3\n" +
                "9 2\n" +
                "6 1", true);
        appendStringIntoFile("Muspelheim\n" +
                "3\n" +
                "3 4\n" +
                "4 1\n" +
                "5 1", true);
        appendStringIntoFile("Svartálfar\n" +
                "2\n" +
                "2 4\n" +
                "3 1", true);
        appendStringIntoFile("Niflheim\n" +
                "2\n" +
                "1 1\n" +
                "2 2", true);
        appendStringIntoFile("Helheim\n" +
                "2\n" +
                "2 3\n" +
                "5 2",true);
    }
}
