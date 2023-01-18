package com.admin.service;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();
    T findById(Integer id);
    void save(T t);
    void remove(Integer id);
}
