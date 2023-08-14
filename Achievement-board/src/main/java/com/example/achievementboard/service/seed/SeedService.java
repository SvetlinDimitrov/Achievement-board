package com.example.achievementboard.service.seed;


import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;

public interface SeedService {
    void seedUser();
    void seedGoals() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException;
    void seedAchievement() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException;
    void seedRoutine() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException;

    void seedAll() throws UserNotFoundException, RoutineNotFoundException, GoalNotFoundException;
}
