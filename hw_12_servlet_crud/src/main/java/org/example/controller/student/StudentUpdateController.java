package org.example.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Student;
import org.example.service.impl.StudentCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

public class StudentUpdateController extends HttpServlet {
    StudentCrudService studentCrudService = new StudentCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        Student student = studentCrudService.read(req.getParameter("id"));
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<!DOCTYPE html>");
            printWriter.write("<html lang=\"en\">");
            printWriter.write("<body>");

            printWriter.write("<header>");
            printWriter.write("<h2 style=\"display: inline-block; margin-right: 10px;\"><a href=\"/hw_12_servlet_crud/students/groups\">Classes</a></h2>");
            printWriter.write("<h2 style=\"display: inline-block; margin-right: 10px;\"><a href=\"/hw_12_servlet_crud/students\">Students</a></h2>");
            printWriter.write("<h2 style=\"display: inline-block;\"><a href=\"/hw_12_servlet_crud/groups\">Groups</a></h2>");
            printWriter.write("</header>");

            printWriter.write("<h1>");
            printWriter.write("Update student");
            printWriter.write("</h1>");

            printWriter.write("<form method='post' action = '/hw_12_servlet_crud/students/update'>");
            printWriter.write("<label for='firstName'>First Name:</label><br>");
            printWriter.write("<input type='text' id='firstName' name='studentFirstName' " +
                    "value='" + student.getFirstName() + "'/><br><br>");

            printWriter.write("<label for='lastname'>Last Name:</label><br>");
            printWriter.write("<input type='text' id='lastname' name='studentLastName' " +
                    "value='" + student.getLastName() + "'/><br><br>");

            printWriter.write("<label for='age'>Age:</label><br>");
            printWriter.write("<input type='text' id='age' name='studentAge'" +
                    "value='" + student.getAge() + "'/><br><br>");

            printWriter.write("<input type='hidden' id='id' name='studentId' value='" +
                    student.getId().toString() + "'/><br><br>");

            printWriter.write("<input type='submit' value='Update'/>");

            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> paramsMap = req.getParameterMap();
        Student student = new Student();
        paramsMap.forEach((k, v) -> {
            switch (k) {
                case "studentId" -> student.setId(UUID.fromString(v[0]));
                case "studentFirstName" -> student.setFirstName(v[0]);
                case "studentLastName" -> student.setLastName(v[0]);
                case "studentAge" -> student.setAge(Integer.parseInt(v[0]));
            }
        });
        studentCrudService.update(student);
        resp.sendRedirect("/hw_12_servlet_crud/students");
    }
}
