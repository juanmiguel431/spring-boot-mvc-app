package com.example.demo.DAO;

import com.example.demo.models.Course;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends BaseRepository<Course> implements IBaseRepository<Course> {

    @Override
    protected Class<Course> getType() {
        return Course.class;
    }

    @Autowired
    public CourseRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
