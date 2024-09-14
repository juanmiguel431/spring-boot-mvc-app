package com.example.demo.services;

import com.example.demo.DAO.EmployeeV2Repository;
import com.example.demo.infrastructure.ApplicationException;
import com.example.demo.infrastructure.ErrorType;
import com.example.demo.models.Employee;
import com.example.demo.models.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceV2 implements IEmployeeService {

    private final EmployeeV2Repository employeeRepository;

    @Autowired
    public EmployeeServiceV2(EmployeeV2Repository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(int id) {
        var employee = employeeRepository.findById(id);
        return employee.orElse(null);

    }

    @Override
    public Employee add(EmployeeDto model) {
        var employee = new Employee();
        employee.setFirstName(model.getFirstName());
        employee.setLastName(model.getLastName());
        employee.setEmail(model.getEmail());

        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Employee update(int id, EmployeeDto model) {

        var item = getById(id);

        if (item == null) {
            throw new ApplicationException(ErrorType.NotFound, "Employee with id " + id + " not found");
        }

        item.setFirstName(model.getFirstName());
        item.setLastName(model.getLastName());
        item.setEmail(model.getEmail());

        return employeeRepository.save(item);
    }

    @Override
    public void deleteById(int id) {
        var item = getById(id);

        if (item == null) {
            throw new ApplicationException(ErrorType.NotFound, "Employee with id " + id + " not found");
        }

        employeeRepository.delete(item);
    }
}
