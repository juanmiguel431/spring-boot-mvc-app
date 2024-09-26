package com.example.demo.controllers;

import com.example.demo.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/employees")
@Controller
public class EmployeesController {

    private final IEmployeeService employeeService;

    public EmployeesController(@Qualifier("employeeServiceV2") IEmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String findAll(Model model) {
        var result = this.employeeService.findAll();

        model.addAttribute("employees", result);

        return "list-employees";
    }
}
