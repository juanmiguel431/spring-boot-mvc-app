package com.example.demo.DAO;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseRepository<T> implements IBaseRepository<T> {

    protected final EntityManager entityManager;
    abstract protected Class<T> getType();

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<T> getAll() {
        var type = getType();
        var query = entityManager.createQuery("from " + type.getName(), type);
        return query.getResultList();
    }

    @Override
    public T getById(int id) {
        return entityManager.find(getType(), id);
    }

    @Override
    @Transactional
    public void add(T model) {
        entityManager.persist(model);
    }

    @Override
    @Transactional
    public T update(T model) {
        return entityManager.merge(model);
    }

    @Override
    @Transactional
    public void delete(T model) {
        entityManager.remove(model);
    }
}
