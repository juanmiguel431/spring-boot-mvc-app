package com.example.demo.services;

import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee getById(int id) {
        return employeeRepository.getById(id);
    }

    @Transactional
    @Override
    public void add(Employee model) {
        employeeRepository.add(model);
    }

    @Transactional
    @Override
    public Employee update(Employee model) {
        return employeeRepository.update(model);
    }

    @Transactional
    @Override
    public void delete(Employee model) {
        employeeRepository.delete(model);
    }
}
