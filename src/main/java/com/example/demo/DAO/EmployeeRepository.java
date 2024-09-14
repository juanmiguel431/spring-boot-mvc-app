package com.example.demo.DAO;

import com.example.demo.models.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepository implements IBaseRepository<Employee> {

    private final EntityManager entityManager;

    @Autowired
    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAll() {
        var query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee getById(int id) {
        var query = entityManager.createQuery("from Employee where id=:id", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public void add(Employee employee) {
        entityManager.persist(employee);
    }

    @Transactional
    @Override
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    @Transactional
    @Override
    public int deleteById(int id) {
        var query = entityManager.createQuery("delete from Employee where id=:id", Employee.class);
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
