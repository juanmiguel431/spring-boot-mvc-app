package com.example.demo.DAO;

import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "members") // This uses DataRest package and creates and rest api endpoint with the resource name /members
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByOrderByLastNameAsc(); // Automagically creates a query ordered by Lastname ascending.
}
