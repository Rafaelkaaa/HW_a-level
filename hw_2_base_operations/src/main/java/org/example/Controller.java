package org.example;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Controller {
    static final String PRINT_TEXT = "Enter 1 for sum numbers in text\n"
            + "Enter 2 for count unique symbols in text\n"
            + "Enter 3 for count count duration of lessons";

    private void processingNumberOfAction() {
        Long numberOfAction = getNumberOfAction();
        ServiceDataFromUser serviceUserString = new ServiceDataFromUser();

        if (numberOfAction == 1) {
            System.out.println(serviceUserString.sumNumericalValues(new Scanner(System.in).nextLine()));
        } else if (numberOfAction == 2) {
            System.out.println(serviceUserString.countSameSymbols(new Scanner(System.in).nextLine()));
        } else if (numberOfAction == 3) {
            System.out.println(serviceUserString.countDurationLessons(new Scanner(System.in).nextLong()));
        } else {
            System.out.println("Invalid command");
        }
    }

    private static Long getNumberOfAction() {
        Scanner scannerNumberOfAction = new Scanner(System.in);
        Long numberOfAction = scannerNumberOfAction.nextLong();
        return numberOfAction;
    }

    public void printInConsole(){
        System.out.println(PRINT_TEXT);
        processingNumberOfAction();
    }
}
