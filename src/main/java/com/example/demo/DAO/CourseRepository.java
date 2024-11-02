package com.example.demo.DAO;

import com.example.demo.models.Course;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Course> findCoursesByInstructorId(int instructorId) {
        var query = entityManager.createQuery("from Course where instructor.id = :instructorId", getType());
        query.setParameter("instructorId", instructorId);

        return query.getResultList();
    }

    public List<Course> findCoursesAndReviewsById(int id) {
        var query = entityManager.createQuery("from Course c join fetch c.reviews where c.id = :id", getType());
        query.setParameter("id", id);

        return query.getResultList();
    }

    public List<Course> findCoursesAndStudentsById(int id) {
        var query = entityManager.createQuery("from Course c join fetch c.students where c.id = :id", getType());
        query.setParameter("id", id);

        return query.getResultList();
    }
}
