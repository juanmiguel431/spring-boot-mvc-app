package com.example.demo.controllers;

import com.example.demo.models.EmployeeDto;
import com.example.demo.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        return "employee/employee-form";
    }

    @PostMapping("add")
    public String post(@Valid @ModelAttribute("employee") EmployeeDto employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee/employee-form";
        }

        employeeService.add(employee);

        return "redirect:/employees"; // This follows the pattern Post/Redirect/Get to prevent undesired form re-submission  www.luv2code.com/post-redirect-get
    }

    @GetMapping("update/{id}")
    public String update(Model model, @PathVariable Integer id) {

        var employee = employeeService.findById(id);

        if (employee == null) {
            return "employee/employee-list";
        }

        model.addAttribute("employee", employee);
        model.addAttribute("id", id);

        return "employee/employee-form";
    }

    @PatchMapping("update/{id}")
    public String patch(@Valid @ModelAttribute("employee") EmployeeDto employee, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return "employee/employee-form";
        }

        employeeService.update(id, employee);

        return "redirect:/employees";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {

        employeeService.deleteById(id);

        return "redirect:/employees";
    }
}
