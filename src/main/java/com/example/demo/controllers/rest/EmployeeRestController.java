package com.example.demo.controllers.rest;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeDto;
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
public class EmployeeRestController {

    private final IEmployeeService employeeService;

    public EmployeeRestController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<Employee>> findAll() {
        var items = employeeService.findAll();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        var item = employeeService.findById(id);

        if (item == null) {
            var error = new ErrorResponse(404, "Employee with the id " + id + " was not found", System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(item);
    }

    @PostMapping("")
    public ResponseEntity<?> post(@RequestBody EmployeeDto model) {

        var item = employeeService.add(model);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@RequestBody EmployeeDto model, @PathVariable int id) {

        employeeService.update(id, model);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {

        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
