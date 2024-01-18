package org.example.controller.group;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.ClassesCrudService;
import org.example.service.impl.GroupCrudService;

import java.io.IOException;

public class GroupDeleteController extends HttpServlet {
    private final ClassesCrudService groupService = new GroupCrudService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String groupId = req.getParameter("id");
        if (groupId != null && !groupId.isEmpty()) {
            groupService.delete(groupId);
            resp.setStatus(200);
            try {
                resp.sendRedirect("/hw_12_servlet_crud/groups");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setStatus(400);
        }
    }
}
