package com.example.demo.mvcControllers;

import com.example.demo.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentMvcController {

    @GetMapping("")
    public String showForm(Model model) {

        var student = new Student();
        student.setFirstName("John");

        model.addAttribute("model", student);

        return "student-form";
    }

    @PostMapping("")
    public String processForm(Model model, @ModelAttribute Student student) {

        model.addAttribute("model", student);

        return "process-student-form";
    }
}
