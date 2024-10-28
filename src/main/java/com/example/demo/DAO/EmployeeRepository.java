package com.example.demo.DAO;

import com.example.demo.models.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends BaseRepository<Employee> implements IBaseRepository<Employee> {

    @Override
    Class<Employee> getType() {
        return Employee.class;
    }

    @Autowired
    public EmployeeRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
