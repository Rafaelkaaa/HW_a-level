package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.controller.clazz.ClassReviewController;
import org.example.controller.clazz.GroupAddStudentController;
import org.example.controller.group.*;
import org.example.controller.student.StudentCreateController;
import org.example.controller.student.StudentDeleteController;
import org.example.controller.student.StudentReviewController;
import org.example.controller.student.StudentUpdateController;

public class ServletApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        context.addServlet(new ServletHolder(new StudentReviewController()), "/students");
        context.addServlet(new ServletHolder(new StudentCreateController()), "/students/new");
        context.addServlet(new ServletHolder(new StudentDeleteController()), "/students/delete");
        context.addServlet(new ServletHolder(new StudentUpdateController()), "/students/update");

        context.addServlet(new ServletHolder(new GroupReviewController()), "/groups");
        context.addServlet(new ServletHolder(new GroupCreateController()), "/groups/new");
        context.addServlet(new ServletHolder(new GroupDeleteController()), "/groups/delete");
        context.addServlet(new ServletHolder(new GroupUpdateController()), "/groups/update");

        context.addServlet(new ServletHolder(new ClassReviewController()), "/students/groups");
        context.addServlet(new ServletHolder(new GroupAddStudentController()), "/students/groups/add");

        server.setHandler(context);

        server.start();
        server.join();
    }
}