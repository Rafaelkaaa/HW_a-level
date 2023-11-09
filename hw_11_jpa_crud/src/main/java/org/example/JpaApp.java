package org.example;

import org.example.controller.Controller;
import org.example.dao.GroupDao;
import org.example.dao.impl.GroupDaoImpl;
import org.example.model.Group;

public class JpaApp {
    public static void main(String[] args) {
        new Controller().mainMenu();
    }
}
