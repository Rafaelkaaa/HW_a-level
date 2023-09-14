package org.example.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.model.BaseEntity;
import org.example.model.Group;
import org.example.model.Student;
import org.example.service.ClassesCrudService;
import org.example.service.GroupCrudService;
import org.example.service.StudentCrudService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Controller {
    ClassesCrudService crudService;
    GroupCrudService groupCrudService;
    StudentCrudService studentCrudService;
    BufferedReader bufferedReader;

    String typeCrud;

    final static String MAIN_MENU_MESSAGE = "Select an entity:\n" +
            "If you want do something with students enter \"STU\"\n" +
            "If you want do something with groups enter \"GRO\"\n" +
            "If you want exit enter \"EXI\"";

    final static String ACTION_MENU_MESSAGE = "Choose operation:\n" +
            "If you want create enter \"CRE\"\n" +
            "If you want print existing enter \"REA\"\n" +
            "If you want update existing enter \"UPD\"\n" +
            "If you want delete existing enter \"DEL\"\n" +
            "If you want add student to group \"ADD\"\n" +
            "For return to the main menu, write \"MAIN\"\n" +
            "If you want exit enter \"EXI\"";

    final static String UPDATE_MESSAGE = "If you want update by ID enter \"ID\"\n" +
            "If you want update by index enter \"IND\"\n" +
            "For return to the main menu, write \"MAIN\"";

    final static String DELETE_MESSAGE ="If you want delete by ID enter \"ID\"\n" +
            "If you want delete by index enter \"IND\"\n" +
            "For return to the main menu, write \"MAIN\"";

    public Controller() {
        groupCrudService = new GroupCrudService();
        studentCrudService = new StudentCrudService();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainMenu() {
        System.out.println(MAIN_MENU_MESSAGE);

        String command = readCommand();
        switch (command) {
            case "STU" -> crudService = studentCrudService;
            case "GRO" -> crudService = groupCrudService;
            case "EXI" -> System.exit(1);
            default -> {
                System.out.println("Entered command invalid, try again");
                mainMenu();
                return;
            }
        }
        typeCrud = crudService.getClass().getSimpleName();
        actionMenu();
    }

    public void actionMenu() {
        System.out.println(ACTION_MENU_MESSAGE);

        String command = readCommand();
        switch (command) {
            case "MAIN" -> mainMenu();
            case "CRE" -> create();
            case "REA" -> read();
            case "UPD" -> update();
            case "DEL" -> delete();
            case "ADD" -> {
                System.out.println("Enter student id");
                String studentId = readCommand();
                System.out.println("Enter group id");
                groupCrudService.addStudentToGroup(studentId,readCommand());
                actionMenu();
            }
            case "EXI" -> System.exit(1);
            default -> {
                System.out.println("Entered command invalid, try again");
                actionMenu();
            }
        }
    }

    private void create() {
        if (typeCrud.equals("GroupCrudService")) {
            crudService.create(createGroup());
        } else if (typeCrud.equals("StudentCrudService")) {
            crudService.create(createStudent());
        }
        actionMenu();
    }

    private void read() {
        System.out.println("If you want get by ID enter \"ID\"\n" +
                "If you want get by index enter \"IND\"\n" +
                "If you want get " + printGroupOrStudent() + " by ID enter \"FIN\"\n" +
                "If you want get all enter \"ALL\"\n" +
                "For return to the main menu, write \"MAIN\"");
        switch (readCommand()) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                System.out.println("Enter ID");
                System.out.println(crudService.read(readCommand()).toString());
            }
            case "IND" -> {
                System.out.println("Enter index");
                System.out.println(crudService.read(stringToInt(readCommand())).toString());
            }
            case "ALL" -> System.out.println(crudService.findAll());
            case "ENT" -> findByGroupOrStudent();
            default -> {
                System.out.println("Entered command invalid, try again");
                actionMenu();
            }
        }
        actionMenu();
    }

    private void update() {
        System.out.println(UPDATE_MESSAGE);
        switch (readCommand()) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                System.out.println("Enter ID");
                crudService.update(crudService.read(readCommand()), getEntity());
            }
            case "IND" -> {
                System.out.println("Enter index");
                crudService.update(crudService.read(stringToInt(readCommand())), getEntity());
            }
            case "EXI" -> System.exit(1);
            default -> {
                System.out.println("Entered command invalid, try again");
                actionMenu();
            }
        }
        actionMenu();
    }

    private void delete() {
        System.out.println(DELETE_MESSAGE);
        switch (readCommand()) {
            case "MAIN" -> mainMenu();
            case "ID" -> {
                System.out.println("Enter ID");
                crudService.delete(readCommand());
            }
            case "IND" -> {
                System.out.println("Enter index");
                crudService.delete(stringToInt(readCommand()));
            }
            case "EXI" -> System.exit(1);
            default -> {
                System.out.println("Entered command invalid, try again");
                actionMenu();
            }
        }
        actionMenu();
    }

    private Group createGroup() {
        System.out.println("Enter group name\n" + "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        Group group = new Group();
        group.setName(command);
        return group;
    }

    private Student createStudent() {
        Student student = new Student();
        readFirstName(student);
        readLastName(student);
        readAge(student);
        return student;
    }

    private void readFirstName(Student student) {
        System.out.println("Enter student first name\n" +
        "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        student.setFirstName(command);
    }

    private void readLastName(Student student) {
        System.out.println("Enter student last name\n" +
                "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        student.setLastName(command);
    }

    private void readAge(Student student) {
        System.out.println("Enter student age\n" +
                "For return to the main menu, write \"MAIN\"");
        String command = readCommand();
        backToMainMenu(command);
        try {
            student.setAge(stringToInt(command));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            readAge(student);
        }
    }

    private void findByGroupOrStudent() {
        if (typeCrud.equals("GroupCrudService")) {
            System.out.println("Enter student ID");
            System.out.println(groupCrudService.findByStudentId(readCommand()));
        } else if (typeCrud.equals("StudentCrudService")) {
            System.out.println("Enter group ID");
            System.out.println(studentCrudService.findByGroupId(readCommand()));
        }
    }

    private String printGroupOrStudent() {
        if (typeCrud.equals("GroupCrudService")) {
            return "groups with student";
        } else if (typeCrud.equals("StudentCrudService")) {
            return "students from group";
        }
        throw new IllegalArgumentException("Back to main menu and choose entity");
    }

    private BaseEntity getEntity() {
        if (typeCrud.equals("GroupCrudService")) return createGroup();
        else if (typeCrud.equals("StudentCrudService")) return createStudent();
        throw new RuntimeException("Back to main menu and choose entity");
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

    private int stringToInt(String index) {
        try {
            return Integer.parseInt(index);
        } catch (IllegalArgumentException ex) {
            System.out.println("Should be numerical value");
            return -1;
        }
    }
}
