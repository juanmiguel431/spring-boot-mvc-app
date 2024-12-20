package com.example.demo.controllers;

import com.example.demo.models.StudentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("")
    public String showForm(Model model) {

        var student = new StudentDto();
        student.setFirstName("John");

        model.addAttribute("model", student);
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("operativeSystems", systems);

        return "student-form";
    }

    @PostMapping("")
    public String processForm(Model model, @ModelAttribute StudentDto student) {

        model.addAttribute("model", student);

        return "process-student-form";
    }
}
