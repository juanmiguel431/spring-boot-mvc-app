package com.example.demo.services;

import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeDto;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAll();
    Employee getById(int id);
    Employee add(EmployeeDto model);
    Employee update(int id, EmployeeDto model);
    void deleteById(int id);
}
