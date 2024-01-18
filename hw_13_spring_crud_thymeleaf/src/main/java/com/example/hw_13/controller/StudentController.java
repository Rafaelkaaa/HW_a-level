package com.example.hw_13.controller;

import com.example.hw_13.dto.StudentDto;
import com.example.hw_13.entity.Student;
import com.example.hw_13.service.impl.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {
    StudentService studentService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "/student/all";
    }

    @GetMapping(path = "/new")
    public String redirectToNew(Model model) {
        model.addAttribute("student", new Student());
        return "/student/new";
    }

    @PostMapping(path = "/new")
    public String create(@ModelAttribute StudentDto student) {
        studentService.create(student);
        return "redirect:/students";
    }

    @GetMapping(path = "update/{id}")
    public String redirectToUpdate(@PathVariable String id, Model model) {
        StudentDto student = studentService.read(id);
        model.addAttribute("student", student);
        return "/student/update";
    }

    @PostMapping(path = "update")
    public String update(@ModelAttribute StudentDto student) {
        studentService.update(student);
        return "redirect:/students";
    }
    @PostMapping(path = "delete/{id}")
    public String delete(@PathVariable String id) {
        studentService.delete(id);
        return "redirect:/students";
    }
}
