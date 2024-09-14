package com.example.demo.controllers;

import com.example.demo.models.ErrorResponse;
import com.example.demo.models.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        if (student.isEmpty()) {
            throw new RuntimeException("Student with id " + id + " not found");
        }

        return student.get();
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RuntimeException ex) {
        var error = new ErrorResponse(500, ex.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
