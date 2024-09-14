package com.example.demo.controllers;

import com.example.demo.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


//@Scope("prototype") // Makes a new instance of the controller is created for every request.
@RestController
@RequestMapping("/api")
public class StudentController {

    private List<Student> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student(1, "Juan Miguel", "Paulino Carpio", "juanmiguel431@gmail.com"));
        students.add(new Student(2, "Luis Miguel", "Paulino Carpio", "luismiguel@gmail.com"));
        students.add(new Student(3, "Eduar", "Paulino Carpio", "eduar@gmail.com"));
    }

    @GetMapping("/students")
    public List<Student> getAll() {
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable int id) {
        var student = students.stream().filter(s -> s.getId() == id).findFirst();
        return student.get();
    }
}
