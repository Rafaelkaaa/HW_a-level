package org.example;

import org.example.controller.Controller;
import org.example.factory.JdbcFactory;


public class JdbcApp {
    public static void main(String[] args) {
        JdbcFactory.getInstance().initDB(JdbcApp .class);
        new Controller().mainMenu();
   }
}