package org.example.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.entity.Account;
import org.example.entity.Record;
import org.example.entity.Transaction;
import org.example.entity.User;
import org.example.service.*;
import org.example.util.CsvConvertorUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Controller {
    BaseCrudService crudService;
    String typeCrud;
    final BufferedReader bufferedReader;

    final static String MAIN_MENU_MESSAGE = "Select an entity:\n" +
            "If you want do something with users enter \"USE\"\n" +
            "If you want do something with account enter \"ACC\"\n" +
            "If you want create transaction enter \"TRA\"\n" +
            "If you want get csv file with transaction history \"HIS\"\n" +
            "If you want exit enter \"EXI\"";

    public Controller() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainMenu() {
        System.out.println(MAIN_MENU_MESSAGE);

        String command = readCommand();
        switch (command) {
            case "USE" -> crudService = new UserService();
            case "ACC" -> crudService = new AccountService();
            case "TRA" -> createTransaction();
            case "HIS" -> {
                getTransfersByAccount();
                mainMenu();
            }
            case "EXI" -> System.exit(1);
            default -> {
                System.out.println("Entered command invalid, try again");
                mainMenu();
                return;
            }
        }
        if (crudService != null) {
            typeCrud = crudService.getClass().getSimpleName();
        }
        actionMenu();
    }

    private void actionMenu() {
        if(typeCrud != null) {
            System.out.println(generateActionMenuMessage());
            String command = readCommand();
            switch (command) {
                case "MAIN" -> mainMenu();
                case "CRE" -> create();
                case "REA" -> read();
                case "UPD" -> update();
                case "DEL" -> delete();
                case "EXI" -> System.exit(1);
                default -> {
                    System.out.println("Entered command invalid, try again");
                    actionMenu();
                }
            }
        }
        mainMenu();
    }

    private String generateActionMenuMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("Choose operation:\n")
                .append("If you want create enter \"CRE\"\n")
                .append("If you want print existing enter \"REA\"\n");
        if (typeCrud.equals("UserService")) {
            builder.append("If you want update existing enter \"UPD\"\n");
        }
        builder.append("If you want delete existing enter \"DEL\"\n")
                .append("For return to the main menu, write \"MAIN\"\n")
                .append("If you want exit enter \"EXI\"");
        return builder.toString();
    }

    private void delete() {
        System.out.println("Enter ID");
        crudService.delete(UUID.fromString(readCommand()));
        actionMenu();
    }

    private void read() {
        if (typeCrud.equals("AccountService")) {
            System.out.println("If you want get by ID enter \"ID\"");
            System.out.println("If you want get by user ID enter \"USE\"");
            switch (readCommand()) {
                case "MAIN" -> mainMenu();
                case "ID" -> {
                    System.out.println("Enter ID");
                    System.out.println(crudService.read(UUID.fromString(readCommand())));
                }
                case "USE" -> {
                    System.out.println("Enter user ID");
                    System.out.println(new AccountService()
                            .findByUserId(UUID.fromString(readCommand())));
                }
                default -> System.out.println("Entered command invalid, try again");
            }
        } else if (typeCrud.equals("UserService")) {
            System.out.println("Enter ID");
            System.out.println(crudService.read(UUID.fromString(readCommand())));
        }
        actionMenu();
    }

    private void update() {
        System.out.println("Enter ID");
        if (typeCrud.equals("AccountService")) {
            System.out.println("Entered command invalid, try again");
            actionMenu();
        } else if (typeCrud.equals("UserService")) {
            crudService.update(createUser((User) crudService.read(UUID.fromString(readCommand()))));
        }

        actionMenu();
    }

    private void create() {
        if (typeCrud.equals("AccountService")) {
            crudService.create(createAccount());
        } else if (typeCrud.equals("UserService")) {
            crudService.create(createUser(null));
        }
        actionMenu();
    }

    private void createTransaction() {
        System.out.println("Enter transaction amount");
        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(Integer.parseInt(readCommand()));
        System.out.println("Enter sender account ID");
        UUID accountIdFrom = UUID.fromString(readCommand());
        System.out.println("Enter recipient account ID");
        UUID accountIdTo = UUID.fromString(readCommand());
        new TransactionService().create(transaction, accountIdFrom, accountIdTo);
    }

    private void getTransfersByAccount() {
        System.out.println("Enter account ID");
        List<Record> records = new RecordService()
                .findRecordsByAccountId(UUID.fromString(readCommand()));
        CsvConvertorUtil.createCsvFile("extract.csv", records);
    }


    private User createUser(User user) {
        System.out.println("Enter user name\n"
                + "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        if (user == null) {
            user = new User();
        }
        user.setName(command);
        return user;
    }

    private Account createAccount() {
        System.out.println("Enter user ID\n"
                + "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        Account account = new Account();
        account.setUser(new UserService().read(UUID.fromString(command)));
        return account;
    }


    private void backToMainMenu(String command) {
        if (command.equals("MAIN")) {
            mainMenu();
        }
    }

    private String readCommand() {
        String command = null;
        try {
            command = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;
    }
}
