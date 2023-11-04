package org.example;

import org.example.controller.Controller;
import org.example.factory.JdbcFactory;


public class JdbcApp {
    public static void main(String[] args) {
        JdbcFactory.getInstance().initDB(JdbcApp .class);
        new Controller().mainMenu();
//        ClassDao studentDao = new StudentDaoImpl();
//        Student student = new Student();
//        student.setFirstName("Create");
//        student.setLastName("Create");
//        student.setAge(25);
//        studentDao.create(student);
//
//        student = (Student) studentDao.read("a673a619-a6f0-4913-b3fb-c03a14a2a82c");
//        System.out.println(student);
//        student.setFirstName("Update");
//        studentDao.update(student);
//        studentDao.delete("ddccaaa6-3772-4bc7-9a5b-ce57c189dba5");
//        System.out.println(studentDao.findAll());
//        studentDao.addStudentToGroup("12dfc573-e130-49f5-ac34-6a0ece8a445d", "47196d70-8c79-4006-bd50-a9272ffc801b");
    }
}