package com.example.demo.DAO;

import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeV2Repository extends JpaRepository<Employee, Integer> {
}
