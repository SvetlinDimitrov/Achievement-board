package com.example.achievementboard.service.goal;

import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.BaseService;
import com.example.achievementboard.entity.Goal;

import java.util.List;

public interface GoalService extends BaseService<Goal> {
    List<Goal> getAllGoals(User user);

    List<Goal> getAllGoalsSortedByDate(User user);

    List<Goal> getAllGoalsSortByImportance(User user);

    List<Goal> getAllGoalsSortByDifficulty(User user);
}
