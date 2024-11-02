package com.example.demo.DAO;

import com.example.demo.models.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository extends BaseRepository<Student> implements IBaseRepository<Student> {

    @Override
    protected Class<Student> getType() {
        return Student.class;
    }

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Student> findStudentsAndCoursesById(int id) {
        var query = entityManager.createQuery("from Student c join fetch c.courses where c.id = :id", getType());
        query.setParameter("id", id);

        return query.getResultList();
    }
}
