package org.example.controller.group;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Group;
import org.example.service.impl.GroupCrudService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

public class GroupUpdateController extends HttpServlet {
    GroupCrudService groupCrudService = new GroupCrudService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        Group group = groupCrudService.read(req.getParameter("id"));
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
            printWriter.write("Update group");
            printWriter.write("</h1>");

            printWriter.write("<form method='post' action = '/groups/update'>");
            printWriter.write("<label for='firstName'>First Name:</label><br>");
            printWriter.write("<input type='text' id='firstName' name='groupName' " +
                    "value='" + group.getName() + "'/><br><br>");

            printWriter.write("<input type='hidden' id='id' name='groupId' value='" +
                    group.getId().toString() + "'/><br><br>");

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
        Group group = new Group();
        paramsMap.forEach((k, v) -> {
            switch (k) {
                case "groupId" -> group.setId(UUID.fromString(v[0]));
                case "groupName" -> group.setName(v[0]);
            }
        });
        groupCrudService.update(group);
        resp.sendRedirect("/groups");
    }
}
