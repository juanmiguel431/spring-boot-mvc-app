package com.example.demo.services;

import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.infrastructure.ApplicationException;
import com.example.demo.infrastructure.ErrorType;
import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeDto;
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
    public List<Employee> findAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.getById(id);
    }

    @Transactional
    @Override
    public Employee add(EmployeeDto model) {
        var employee = new Employee();
        employee.setFirstName(model.getFirstName());
        employee.setLastName(model.getLastName());
        employee.setEmail(model.getEmail());
        employeeRepository.add(employee);

        return employee;
    }

    @Transactional
    @Override
    public Employee update(int id, EmployeeDto model) {

        var item = findById(id);

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
        var item = findById(id);

        if (item == null) {
            throw new ApplicationException(ErrorType.NotFound, "Employee with id " + id + " not found");
        }

        employeeRepository.delete(item);
    }
}
