package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileService {
    private ArrayList<String> filesList = new ArrayList<>();
    private File file;

    public ArrayList<String> readFile(String dirPath) {
        file = new File(dirPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                filesList.add("Directory = " + file.getAbsolutePath());
                readDir(file);
            } else {
                filesList.add("File = " + file.getAbsolutePath());
            }
            return filesList;
        }
        throw new IllegalArgumentException("File with path " + dirPath + " isn't exists");
    }

    private void readDir(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                readFile(f.getAbsolutePath());
            }
        }
    }

    public boolean createFile(String filePath) {
        file = new File(filePath);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean createDir(String dirPath) {
        file = new File(dirPath);
        return file.mkdir();
    }

    public boolean delete(String path) {
        file = new File(path);
        if (file.isDirectory()) {
            deleteAllInDir(file);
        }
        return file.delete();
    }

    public void deleteAllInDir(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteAllInDir(f);
                }
                f.delete();
            }
        }
    }

    public void moveToDir(String oldFilePath, String newDirPath) {
        file = new File(oldFilePath);
        File destinationFile = new File(newDirPath);
        if (file.exists()) {
            if (destinationFile.exists()) {
                if (file.isFile()) {
                    moveFile(destinationFile);
                } else if (file.isDirectory()) {
                    moveDir(destinationFile);
                }
            } else throw new IllegalArgumentException("Directory with path " + destinationFile + " isn't exists");
        } else throw new IllegalArgumentException("File with path " + oldFilePath + " isn't exists");

    }

    public List<String> findAllFilesWithTextInside(String dirPath, String text) {
        filesList = new ArrayList<>();
        return filterFileOrDir(dirPath, "File = ")
                .stream()
                .filter(f -> readFileText(f).toLowerCase().contains(text.toLowerCase()))
                .toList();
    }

    public List<String> findByDir(String dirPath, String name, boolean isDir) {
        if (isDir) {
            filesList = new ArrayList<>(filterFileOrDir(dirPath, "Directory = "));
        } else filesList = new ArrayList<>(filterFileOrDir(dirPath, "File = "));
        return filesList
                .stream()
                .map(File::new)
                .filter(f -> f.getName().equals(name))
                .map(File::getAbsolutePath)
                .toList();
    }

    private List<String> filterFileOrDir(String path, String fileOrDir) {
        filesList = new ArrayList<>();
        return readFile(path)
                .stream()
                .filter(f -> f.contains(fileOrDir))
                .map(f -> f.replace(fileOrDir, ""))
                .toList();
    }

    private void moveDir(File destinationFile) {
        File newDestination = new File(destinationFile, file.getName());
        file.renameTo(newDestination);
    }

    private void moveFile(File destinationFile) {
        try {
            Files.move(file.toPath(),
                    destinationFile.toPath().resolve(file.getName()),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFileText(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
}
