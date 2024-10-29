package com.example.demo.DAO;

import com.example.demo.models.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorRepository extends BaseRepository<Instructor> implements IBaseRepository<Instructor> {

    @Override
    protected Class<Instructor> getType() {
        return Instructor.class;
    }

    @Autowired
    public InstructorRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
