package com.example.achievementboard.service;

import java.util.List;

public interface BaseService<T> {
    boolean isEmpty();

    void saveAll(List<T> build);

    T getById(Long id);

    void save(T entity);
}
