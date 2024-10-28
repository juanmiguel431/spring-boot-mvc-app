package com.example.demo.DAO;

import com.example.demo.models.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorDetailRepository extends BaseRepository<InstructorDetail> implements IBaseRepository<InstructorDetail> {

    @Override
    Class<InstructorDetail> getType() {
        return InstructorDetail.class;
    }

    @Autowired
    public InstructorDetailRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
