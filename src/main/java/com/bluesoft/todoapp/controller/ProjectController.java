package com.bluesoft.todoapp.controller;

import com.bluesoft.todoapp.model.projection.ProjectWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
class ProjectController {

    @GetMapping
    String showProjects(Model model){
        model.addAttribute("project",new ProjectWriteModel());
        return "projects";
    }

    @PostMapping(params = "addSteps")
    String addProjectStep(){
        return "projects";
    }
}
