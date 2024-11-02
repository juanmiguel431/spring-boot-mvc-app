package com.example.demo.DAO;

import com.example.demo.models.Course;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public Course findCourseAndReviewsById(int id) {
        var query = entityManager.createQuery("from Course c left join fetch c.reviews where c.id = :id", getType());
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    public Course findCourseAndStudentsById(int id) {
        var query = entityManager.createQuery("from Course c left join fetch c.students where c.id = :id", getType());
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Transactional
    public void removeStudentFromCourse(int courseId, int studentId) {
        var course = findCourseAndStudentsById(courseId);
        var students = course.getStudents();
        students.removeIf(p -> p.getId() == studentId);
    }
}
