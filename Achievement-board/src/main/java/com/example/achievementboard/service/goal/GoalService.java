package com.example.achievementboard.service.goal;

import com.example.achievementboard.domain.constants.exception.RoutineNotFoundException;
import com.example.achievementboard.domain.constants.exception.UserNotFoundException;
import com.example.achievementboard.domain.constants.exception.GoalNotFoundException;
import com.example.achievementboard.domain.dtos.goal.GoalCreate;
import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.dtos.goal.GoalView;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.service.BaseService;

import java.util.List;

public interface GoalService extends BaseService<GoalEntity> {

    List<GoalView> getAllGoalsSortedByDate(Long userId);

    List<GoalView> getAllGoalsSortByImportance(Long userId);

    List<GoalView> getAllGoalsSortByDifficulty(Long userId);

    void save(GoalCreate goalCreate, Long userId) throws UserNotFoundException;

    void edit(GoalChange goalChange) throws GoalNotFoundException, RoutineNotFoundException;

    void deleteGoal(Long l);

    void finishGoal(Long l) throws GoalNotFoundException;

    List<GoalView> getAllGoalViews(Long id);
}
