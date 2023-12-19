package org.example.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Student;
import org.example.service.impl.StudentCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class StudentReviewController extends HttpServlet {
    StudentCrudService studentCrudService = new StudentCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        List<Student> students = studentCrudService.findAll().stream().toList();

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
            printWriter.write("All students");
            printWriter.write("</h1>");
            printWriter.write("<h3><a href=\"/students/new\">Create new Student</a></h3>");

            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>First Name</th>");
            printWriter.write("<th>Last Name</th>");
            printWriter.write("<th>Age</th>");
            printWriter.write("</tr>");

            for (Student student : students) {
                printWriter.write("<tr>");
                printWriter.write("<td>");
                printWriter.write(student.getFirstName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(student.getLastName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write(String.valueOf(student.getAge()));
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<form method=\"post\" action=\"http://localhost:8080/students/delete\">");
                printWriter.write("<input type=\"hidden\" name=\"id\" value=\"" + student.getId() + "\" />");
                printWriter.write("<input type=\"submit\" value=\"Delete\" />");
                printWriter.write("</form>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<form method=\"get\" action=\"http://localhost:8080/students/update\">");
                printWriter.write("<input type=\"hidden\" name=\"id\" value=\"" + student.getId() + "\" />");
                printWriter.write("<input type=\"submit\" value=\"Update\" />");
                printWriter.write("</form>");
                printWriter.write("</td>");
                printWriter.write("</tr>");
            }
            printWriter.write("</table>");

            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
