package org.example.controller.clazz;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Group;
import org.example.model.Student;
import org.example.service.impl.GroupCrudService;
import org.example.service.impl.StudentCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClassReviewController extends HttpServlet {

    private final GroupCrudService groupService = new GroupCrudService();
    private final StudentCrudService studentService = new StudentCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        List<Group> groups = groupService.findAll().stream().toList();
        List<Student> students = studentService.findAll().stream().toList();

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
            printWriter.write("All classes");
            printWriter.write("</h1>");

            for (Group group : groups) {
                String groupName = group.getName();

                printWriter.write("<h3>");
                printWriter.write(groupName);
                printWriter.write("</h3>");

                printWriter.write("<form style=\"display: inline-block; margin-right: 10px;\" method='post' action = '/students/groups/add' name='" + groupName
                        + "' id=\"addTo" + groupName + "\">");
                printWriter.write("<input style=\"display: inline-block; margin-right: 10px;\" type=\"hidden\" name=\"groupId\" value=\"" + group.getId() + "\" />");
                printWriter.write("<label style=\"display: inline-block; margin-right: 10px;\" for='students'>Choose student:</label><br>");
                printWriter.write("<select style=\"display: inline-block;\" id='students' name='studentId'>");

                List<Student> studentsFromGroup = studentService.findByGroupId(group.getId().toString());
                for (Student student : students) {
                    if (!studentsFromGroup.contains(student)) {
                        printWriter.write(" <option value=\"" + student.getId() + "\">"
                                + student.getFirstName() + " " + student.getLastName() + "</option>");
                    }
                }
                printWriter.write("</select>");
                printWriter.write("</form>");
                printWriter.write("<button type=\"submit\" form=\"addTo" + group.getName()
                        + "\" value=\"Submit\">Add student</button>");


                printWriter.write("<table>");
                printWriter.write("<tr>");
                printWriter.write("<th>First Name</th>");
                printWriter.write("<th>Last Name</th>");
                printWriter.write("<th>Age</th>");
                printWriter.write("</tr>");

                for (Student student : studentsFromGroup) {
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
                    printWriter.write("</tr>");
                }
                printWriter.write("</table>");
            }

            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
