package com.example.demo.DAO;

import java.util.List;

public interface IBaseRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T model);
    T update(T model);
    void delete(T model);
}
