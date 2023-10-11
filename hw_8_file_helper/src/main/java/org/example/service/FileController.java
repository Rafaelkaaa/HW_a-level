package org.example.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileController {
    static final String MAIN_MENU_TEXT = "Enter 1 for read stack all files by directory\n" +
            "Enter 2 for create file or directory\n" +
            "Enter 3 for delete file or directory\n" +
            "Enter 4 for move file or directory on new dir\n" +
            "Enter 5 for find file or directory by dir\n" +
            "Enter 6 for find files by text inside files\n" +
            "Enter 7 for exit";
    static final String CHOOSE_FILE_OR_DIRECTORY = "Enter 1 for work with file\n" +
            "Enter 2 for work with directory";

    BufferedReader reader;
    FileService fileService;

    boolean isDir;

    public FileController() {
        reader = new BufferedReader(
                new InputStreamReader(System.in));
        fileService = new FileService();
    }

    public void mainMenu() {
        System.out.println(MAIN_MENU_TEXT);
        switch (readInConsole()) {
            case ("1"):
                readAllByDir();
                break;
            case ("2"):
                create();
                break;
            case ("3"):
                delete();
                break;
            case ("4"):
                moveToDir();
                break;
            case ("5"):
                findAllByDir();
                break;
            case ("6"):
                findAllFilesWithTextInside();
                break;
            case ("7"):
                System.exit(1);
                break;
            default:
                System.err.println("Invalid command try again");
        }
        mainMenu();
    }

    private void findAllFilesWithTextInside() {
        System.out.println("Enter directory path");
        String dirPath = readInConsole();
        System.out.println("Enter text");
        try {
            fileService.findAllFilesWithTextInside(dirPath, readInConsole())
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            findAllFilesWithTextInside();
        }
    }

    private void findAllByDir() {
        dirOrFileMenu();
        System.out.println("Enter directory path");
        String dirPath = readInConsole();
        System.out.println("Enter file or directory simple name");
        try {
            fileService.findByDir(dirPath, readInConsole(), isDir)
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            findAllByDir();
        }

    }

    private void moveToDir() {
        System.out.println("Enter file or directory path");
        String oldPath = readInConsole();
        System.out.println("Enter goal directory path");
        try {
            fileService.moveToDir(oldPath, readInConsole());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

        }
    }

    private void delete() {
        System.out.println("Enter file or directory path");
        if (fileService.delete(readInConsole())){
            System.out.println("Deleted successful");
        }else {
            System.err.println("Cannot be deleted try another path");
        }
    }

    private void create() {
        dirOrFileMenu();
        System.out.println("Enter path where have to create new file or directory");
        boolean result = false;
        if (isDir) {
            result = fileService.createDir(readInConsole());
        } else {
            result = fileService.createFile(readInConsole());
        }
        if (result) {
            System.out.println("Created successful");
        } else {
            System.err.println("Cannot be created try another path");
        create();
        }
    }

    private void readAllByDir() {
        System.out.println("Enter directory path for read stack all files");
        try {
            fileService.readFile(readInConsole())
                    .forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    private String readInConsole() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Some gone wrong, try again");
            mainMenu();
        }
        return null;
    }

    private void dirOrFileMenu() {
        System.out.println(CHOOSE_FILE_OR_DIRECTORY);
        switch (readInConsole()) {
            case ("1"):
                isDir = false;
                break;
            case ("2"):
                isDir = true;
                break;
            default:
                System.err.println("Invalid command try again");
                dirOrFileMenu();
        }
    }
}
