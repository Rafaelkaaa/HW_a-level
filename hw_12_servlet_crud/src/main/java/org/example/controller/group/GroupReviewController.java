package org.example.controller.group;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Group;
import org.example.service.impl.GroupCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class GroupReviewController extends HttpServlet {
    GroupCrudService groupCrudService = new GroupCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);

        List<Group> groups = groupCrudService.findAll().stream().toList();

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
            printWriter.write("All groups");
            printWriter.write("</h1>");
            printWriter.write("<h3><a href=\"/groups/new\">Create new Group</a></h3>");

            printWriter.write("<table>");
            printWriter.write("<tr>");
            printWriter.write("<th>Name</th>");
            printWriter.write("</tr>");

            for (Group group : groups) {
                printWriter.write("<tr>");
                printWriter.write("<td>");
                printWriter.write(group.getName());
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<form method=\"post\" action=\"http://localhost:8080/groups/delete\">");
                printWriter.write("<input type=\"hidden\" name=\"id\" value=\"" + group.getId() + "\" />");
                printWriter.write("<input type=\"submit\" value=\"Delete\" />");
                printWriter.write("</form>");
                printWriter.write("</td>");
                printWriter.write("<td>");
                printWriter.write("<form method=\"get\" action=\"http://localhost:8080/groups/update\">");
                printWriter.write("<input type=\"hidden\" name=\"id\" value=\"" + group.getId() + "\" />");
                printWriter.write("<input type=\"submit\" value=\"Update\" />");
                printWriter.write("</form>");
                printWriter.write("</td>");
                printWriter.write("</tr>");
            }
            printWriter.write("</table>");

            printWriter.write("</body>");
            printWriter.write("</html>");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
