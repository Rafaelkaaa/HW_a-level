package org.example.controller.group;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Group;
import org.example.service.ClassesCrudService;
import org.example.service.impl.GroupCrudService;

import java.io.IOException;
import java.io.PrintWriter;

public class GroupCreateController extends HttpServlet {

    private final ClassesCrudService groupService = new GroupCrudService();
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
            printWriter.write("Create new group");
            printWriter.write("</h1>");

            printWriter.write("<form method='post' action = '/groups/new'>");
            printWriter.write("<label for='Name'>Name:</label><br>");
            printWriter.write("<input type='text' id='Name' name='groupName'/><br><br>");

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
        Group group = new Group();
        group.setName(req.getParameter("groupName"));

        groupService.create(group);
        try {
            resp.sendRedirect("/groups");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

