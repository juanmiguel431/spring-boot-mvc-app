package com.example.demo.controllers.rest;

import com.example.demo.models.ErrorResponse;
import com.example.demo.models.StudentDto;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@Scope("prototype") // Makes a new instance of the controller is created for every request.
@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<StudentDto> students;

    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new StudentDto(1, "Juan Miguel", "Paulino Carpio", "juanmiguel431@gmail.com"));
        students.add(new StudentDto(2, "Luis Miguel", "Paulino Carpio", "luismiguel@gmail.com"));
        students.add(new StudentDto(3, "Eduar", "Paulino Carpio", "eduar@gmail.com"));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAll() {

        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var student = students.stream().filter(s -> s.getId() == id).findFirst();

        if (student.isEmpty()) {
            var error = new ErrorResponse(404, "Student with the id " + id + " was not found", System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student.get());
    }

//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
//        var error = new ErrorResponse(500, ex.getMessage(), System.currentTimeMillis());
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
