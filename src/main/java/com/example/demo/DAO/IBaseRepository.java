package com.example.demo.DAO;

import java.util.List;

public interface IBaseRepository<T> {
    List<T> getAll();
    T getById(int id);
    void add(T t);
    T update(T t);
    int deleteById(int id);
}
