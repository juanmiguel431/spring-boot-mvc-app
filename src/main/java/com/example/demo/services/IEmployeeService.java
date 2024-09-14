package com.example.demo.services;

import com.example.demo.models.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAll();
    Employee getById(int id);
    void add(Employee model);
    Employee update(int id, Employee model);
    void deleteById(int id);
}
