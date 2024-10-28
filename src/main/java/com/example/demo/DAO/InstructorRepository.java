package com.example.demo.DAO;

import com.example.demo.models.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorRepository implements IBaseRepository<Instructor> {

    private final EntityManager entityManager;

    @Autowired
    public InstructorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Instructor> getAll() {
        var query = entityManager.createQuery("from Instructor", Instructor.class);
        return query.getResultList();
    }

    @Override
    public Instructor getById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void add(Instructor model) {
        entityManager.persist(model);
    }

    @Override
    @Transactional
    public Instructor update(Instructor model) {
        if (model.getId() == 0) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return entityManager.merge(model);
    }

    @Override
    @Transactional
    public void delete(Instructor model) {
        entityManager.remove(model);
    }
}
