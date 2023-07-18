package com.example.achievementboard.constants;

import java.util.List;

public interface BaseService<T> {
    boolean isEmpty();

    void saveAll(List<T> build);

    T getById(Integer id);

    void save(T entity);
}
