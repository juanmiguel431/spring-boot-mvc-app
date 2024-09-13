package com.example.demo.controllers;

import com.example.demo.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @GetMapping("/students")
    public List<Student> getStudents() {

        var students = new ArrayList<Student>();
        students.add(new Student("Juan Miguel", "Paulino Carpio", "juanmiguel431@gmail.com"));
        students.add(new Student("Luis Miguel", "Paulino Carpio", "luismiguel@gmail.com"));
        students.add(new Student("Eduar", "Paulino Carpio", "eduar@gmail.com"));

        return students;
    }
}
