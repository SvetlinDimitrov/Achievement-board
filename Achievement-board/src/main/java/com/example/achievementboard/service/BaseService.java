package com.example.achievementboard.service;

import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;

import java.util.List;

public interface BaseService<T> {
    boolean isEmpty();

    void saveAll(List<T> build);

    T getById(Long id) throws RoutineNotFoundException, GoalNotFoundException, UserNotFoundException;

    void save(T entity);
}
