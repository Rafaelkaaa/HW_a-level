package org.example.controller.clazz;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.service.impl.GroupCrudService;

import java.io.IOException;


public class GroupAddStudentController extends HttpServlet {
    private final GroupCrudService groupService = new GroupCrudService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        String groupId = req.getParameter("groupId");
        String studentId = req.getParameter("studentId");
        groupService.addStudentToGroup(studentId, groupId);
        resp.sendRedirect("/students/groups");
    }
}
