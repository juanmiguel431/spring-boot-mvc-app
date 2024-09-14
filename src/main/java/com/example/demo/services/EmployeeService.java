package com.example.demo.services;

import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.infrastructure.ApplicationException;
import com.example.demo.infrastructure.ErrorType;
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
    public Employee update(int id, Employee model) {

        var item = getById(id);

        if (item == null) {
            throw new ApplicationException(ErrorType.NotFound, "Employee with id " + id + " not found");
        }

        item.setFirstName(model.getFirstName());
        item.setLastName(model.getLastName());
        item.setEmail(model.getEmail());

        return employeeRepository.update(item);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        var item = getById(id);

        if (item == null) {
            throw new ApplicationException(ErrorType.NotFound, "Employee with id " + id + " not found");
        }

        employeeRepository.delete(item);
    }
}
