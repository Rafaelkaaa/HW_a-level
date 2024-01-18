package org.example.controller.student;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.ClassesCrudService;
import org.example.service.impl.StudentCrudService;

import java.io.IOException;

public class StudentDeleteController extends HttpServlet {
    private final ClassesCrudService studentService = new StudentCrudService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String studentId = req.getParameter("id");
        if (studentId != null && !studentId.isEmpty()) {
            studentService.delete(studentId);
            resp.setStatus(200);
            try {
                resp.sendRedirect("/hw_12_servlet_crud/students");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setStatus(400);
        }
    }
}
