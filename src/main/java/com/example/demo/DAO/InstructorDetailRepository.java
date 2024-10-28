package com.example.demo.DAO;

import com.example.demo.models.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDetailRepository implements IBaseRepository<InstructorDetail> {

    private final EntityManager entityManager;

    @Autowired
    public InstructorDetailRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<InstructorDetail> getAll() {
        var query = entityManager.createQuery("from Instructor", InstructorDetail.class);
        return query.getResultList();
    }

    @Override
    public InstructorDetail getById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void add(InstructorDetail model) {
        entityManager.persist(model);
    }

    @Override
    @Transactional
    public InstructorDetail update(InstructorDetail model) {
        if (model.getId() == 0) {
            throw new IllegalArgumentException("Invalid Id");
        }

        return entityManager.merge(model);
    }

    @Override
    @Transactional
    public void delete(InstructorDetail model) {
        entityManager.remove(model);
    }
}
