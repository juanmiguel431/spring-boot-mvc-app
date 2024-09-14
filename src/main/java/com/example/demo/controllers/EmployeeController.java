package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.models.ErrorResponse;
import com.example.demo.services.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping("")
    public ResponseEntity<?> Post(@RequestBody Employee model) {

        employeeService.add(model);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(model.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> Patch(@RequestBody Employee model, @PathVariable int id) {

        var item = employeeService.getById(id);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        item.setFirstName(model.getFirstName());
        item.setLastName(model.getLastName());
        item.setEmail(model.getEmail());

        employeeService.update(item);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete(@PathVariable int id) {

        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
