package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.ErrorResponse;
import com.example.demo.services.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> GetAll() {
        var items = employeeService.getAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetById(@PathVariable int id) {
        var item = employeeService.getById(id);

        if (item == null) {
            var error = new ErrorResponse(404, "Employee with the id " + id + " was not found", System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(item);
    }
}
