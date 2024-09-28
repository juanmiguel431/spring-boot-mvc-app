package com.example.demo.controllers;

import com.example.demo.models.EmployeeDto;
import com.example.demo.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeesController {

    private final IEmployeeService employeeService;

    public EmployeesController(@Qualifier("employeeServiceV2") IEmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("")
    public String findAll(Model model) {
        var result = this.employeeService.findAll();

        model.addAttribute("employees", result);

        return "employee/employee-list";
    }

    @GetMapping("add")
    public String add(Model model) {

        var employee = new EmployeeDto();
        model.addAttribute("employee", employee);

        return "employee/employee-add";
    }

    @PostMapping("")
    public String post(@Valid @ModelAttribute("employee") EmployeeDto employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee/employee-add";
        }

        employeeService.add(employee);

        return "redirect:/employees"; // This follows the pattern Post/Redirect/Get to prevent undesired form re-submission  www.luv2code.com/post-redirect-get
//        return "employee/created-employee";
    }
}
