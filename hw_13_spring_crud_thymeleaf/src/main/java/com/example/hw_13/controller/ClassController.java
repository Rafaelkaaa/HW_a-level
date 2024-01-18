package com.example.hw_13.controller;

import com.example.hw_13.dto.AddToClassDto;
import com.example.hw_13.service.impl.GroupService;
import com.example.hw_13.service.impl.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/classes")
public class ClassController {
    GroupService groupService;
    StudentService studentService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("classes", groupService.findAllGroupsWithStudents());
model.addAttribute("studentsNotInClass", studentService.findStudentsNotInGroup());
        return "/class/all";
    }

    @PostMapping
    public String addToGroup(@ModelAttribute AddToClassDto addToClassDto) {
        studentService.addStudentToGroup(addToClassDto.getStudentId(), addToClassDto.getGroupId());
        return "redirect:/classes";
    }
}
