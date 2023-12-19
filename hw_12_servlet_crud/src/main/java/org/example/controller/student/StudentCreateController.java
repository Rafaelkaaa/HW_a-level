package org.example.controller.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Student;
import org.example.service.ClassesCrudService;
import org.example.service.impl.StudentCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class StudentCreateController extends HttpServlet {

    private final ClassesCrudService studentService = new StudentCrudService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(200);
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang=\"en\">");
            printWriter.write("<body>");

            printWriter.write("<header>");
            printWriter.write("<h2 style=\"display: inline-block; margin-right: 10px;\"><a href=\"/students/groups\">Classes</a></h2>");
            printWriter.write("<h2 style=\"display: inline-block; margin-right: 10px;\"><a href=\"/students\">Students</a></h2>");
            printWriter.write("<h2 style=\"display: inline-block;\"><a href=\"/groups\">Groups</a></h2>");
            printWriter.write("</header>");

            printWriter.write("<h1>");
            printWriter.write("Create new student");
            printWriter.write("</h1>");

            printWriter.write("<form method='post' action = '/students/new'>");
            printWriter.write("<label for='firstName'>First Name:</label><br>");
            printWriter.write("<input type='text' id='firstName' name='studentFirstName'/><br><br>");

            printWriter.write("<label for='lastname'>Last Name:</label><br>");
            printWriter.write("<input type='text' id='lastname' name='studentLastName'/><br><br>");

            printWriter.write("<label for='age'>Age:</label><br>");
            printWriter.write("<input type='text' id='age' name='studentAge'/><br><br>");

            printWriter.write("<input type='submit' value='Create'/>");

            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setStatus(201);
        Map<String, String[]> paramsMap = req.getParameterMap();
        Student student = new Student();
        paramsMap.forEach((k, v) -> {
            switch (k) {
                case "studentFirstName" -> student.setFirstName(v[0]);
                case "studentLastName" -> student.setLastName(v[0]);
                case "studentAge" -> student.setAge(Integer.parseInt(v[0]));
            }
        });
        studentService.create(student);
        try {
            resp.sendRedirect("/students");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

