package com.example.achievementboard.service.goal;

import com.example.achievementboard.domain.dtos.goal.GoalCreate;
import com.example.achievementboard.domain.dtos.goal.GoalChange;
import com.example.achievementboard.domain.dtos.goal.GoalView;
import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.BaseService;

import java.util.List;

public interface GoalService extends BaseService<GoalEntity> {

    List<GoalView> getAllGoalsSortedByDate(Long userId);

    List<GoalView> getAllGoalsSortByImportance(Long userId);

    List<GoalView> getAllGoalsSortByDifficulty(Long userId);

    void save(GoalCreate goalCreate, Long userId);

    void edit(GoalChange goalChange);

    void deleteGoal(Long l);

    void finishGoal(Long l);

}
