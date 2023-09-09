package org.example.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.entity.Raider;
import org.example.service.RaiderCrudServiceImpl;

import java.util.Scanner;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RaiderController {
    final RaiderCrudServiceImpl raiderCrud;
    Scanner scanner;

    final static String MAIN_MENU_MESSAGE = "Choose operation:\n" +
            "If you want create new raider enter \"CRE\"\n" +
            "If you want print existing raider(s) enter \"REA\"\n" +
            "If you want update existing raider enter \"UPD\"\n" +
            "If you want delete existing raider enter  \"DEL\"\n" +
            "If you want exit enter \"EXI\"";

    final static String DELETE_MESSAGE = "If you want delete raider by ID enter \"ID\"\n " +
            "If you want delete raider by index enter \"IND\"\n" +
            "For return to the main menu, write \"MAIN\"";

    final static String UPDATE_MASSAGE = "If you want update raider by ID enter \"ID\"\n " +
            "If you want update raider by index enter \"IND\"\n" +
            "For return to the main menu, write \"MAIN\"";

    final static String READ_MASSAGE = "If you want get raider by ID enter \"ID\"\n " +
            "If you want get raider by index enter \"IND\"\n" +
            "If you want get all raider enter \"ALL\"\n" +
            "For return to the main menu, write \"MAIN\"";

    public RaiderController() {
        raiderCrud = new RaiderCrudServiceImpl();
        scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        boolean invalidCommand = false;
        while (true) {
            if (invalidCommand) {
                System.out.println("Entered command invalid");
                invalidCommand = false; // Reset the flag
            }

            System.out.println(MAIN_MENU_MESSAGE);

            String command = scanner.nextLine();
            switch (command) {
                case "CRE" -> create();
                case "REA" -> read();
                case "UPD" -> update();
                case "DEL" -> delete();
                case "EXI" -> System.exit(1);
                default -> invalidCommand = true;
            }
        }
    }

    private void update() {
        System.out.println(UPDATE_MASSAGE);
        String command = scanner.nextLine();
        switch (command) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                System.out.println("Entered raider ID");
                String id = scanner.nextLine();
                try {
                    raiderCrud.update(raiderCrud.read(id), new Raider(readCountry(), readGoalRaid(), readBooty(), readName()));
                } catch (RuntimeException ex) {
                    System.err.println(ex.getMessage());
                    update();
                }
            }
            case "IND" -> {
                System.out.println("Entered raider index");
                int index = Integer.parseInt(scanner.nextLine());
                try {
                    raiderCrud.update(raiderCrud.read(index), new Raider(readCountry(), readGoalRaid(), readBooty(), readName()));
                } catch (RuntimeException ex) {
                    System.err.println(ex.getMessage());
                    update();
                }
            }
            default -> {
                System.out.println("Entered command invalid");
                update();
            }
        }
    }


    private void read() {
        System.out.println(READ_MASSAGE);
        String command = scanner.nextLine();
        switch (command) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                try {
                    System.out.println("Entered raider ID");
                    String id = scanner.nextLine();
                    System.out.println(raiderCrud.read(id).toString());
                } catch (IllegalArgumentException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            case "IND" -> {
                System.out.println("Entered raider index");
                int index = Integer.parseInt(scanner.nextLine());
                System.out.println(raiderCrud.read(index).toString());
            }
            case "ALL" -> System.out.println(raiderCrud.findAll().toString());
            default -> {
                System.out.println("Entered command invalid");
                read();
            }
        }
    }

    void delete() {
        System.out.println(DELETE_MESSAGE);
        String command = scanner.nextLine();

        switch (command) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                try {
                    System.out.println("Entered raider ID");
                    String id = scanner.nextLine();
                    raiderCrud.delete(id);
                } catch (IllegalArgumentException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            case "IND" -> {
                System.out.println("Entered raider index");
                int index = Integer.parseInt(scanner.nextLine());
                raiderCrud.delete(index);
            }
            default -> {
                System.out.println("Entered command invalid");
                delete();
            }
        }
    }

    void create() {
        Raider raider = new Raider();
        try {
            raider.setFullName(readName());
            raider.setCountry(readCountry());
            raider.setGoalRaid(readGoalRaid());
            raider.setBooty(readBooty());
        } catch (RuntimeException ex) {
            System.err.println(ex.getMessage());
            create();
            return;
        }
       raiderCrud.create(raider);
    }

    void backToMainMenu(String command) {
        if (command.equals("MAIN")) {
            mainMenu();
        }
    }

    private Integer readBooty() {
        System.out.println("Enter the raider's booty (Booty should be from 500 till 1 000 000)\n" +
                "For return to the main menu, write \"MAIN\"");
        String booty = scanner.nextLine();

        if (booty.isEmpty()) {
            return null;
        }
        backToMainMenu(booty);
        return Integer.parseInt(booty);
    }


    private String readGoalRaid() {
        System.out.println("Enter the raider's goal (Goal raid should contain from 15 till 500 characters)\n" +
                "For return to the main menu, write \"MAIN\"");
        String goalRaid = scanner.nextLine();

        if (goalRaid.isEmpty()) {
            return null;
        }
        backToMainMenu(goalRaid);
        return goalRaid;
    }

    private String readCountry() {
        System.out.println("Enter the raider's country of origin (Country should contain the full name of the actual country)\n" +
                "For return to the main menu, write \"MAIN\"");
        String country = scanner.nextLine();
        backToMainMenu(country);
        return country;
    }

    private String readName() {
        System.out.println("Enter the raider's full name (Full name should contain 2 words, both starting with a capital letter)\n" +
                "For return to the main menu, write \"MAIN\"");
        String name = scanner.nextLine();
        backToMainMenu(name);
        return name;
    }
}