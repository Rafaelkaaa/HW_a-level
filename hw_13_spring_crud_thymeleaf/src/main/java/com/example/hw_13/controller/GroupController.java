package com.example.hw_13.controller;

import com.example.hw_13.dto.GroupDto;
import com.example.hw_13.entity.Group;
import com.example.hw_13.service.impl.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/groups")
public class GroupController {
    GroupService groupService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "/group/all";
    }

    @GetMapping(path = "/new")
    public String redirectToNew(Model model) {
        model.addAttribute("group", new Group());
        return "/group/new";
    }

    @PostMapping(path = "/new")
    public String create(@ModelAttribute GroupDto group) {
        groupService.create(group);
        return "redirect:/groups";
    }

    @GetMapping(path = "update/{id}")
    public String redirectToUpdate(@PathVariable String id, Model model) {
        GroupDto group = groupService.read(id);
        model.addAttribute("group", group);
        return "/group/update";
    }

    @PostMapping(path = "update")
    public String update(@ModelAttribute GroupDto group) {
        groupService.update(group);
        return "redirect:/groups";
    }

    @PostMapping(path = "delete/{id}")
    public String delete(@PathVariable String id) {
        groupService.delete(id);
        return "redirect:/groups";
    }
}
