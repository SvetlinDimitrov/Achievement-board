package com.example.achievementboard.service.routine;

import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.BaseService;
import com.example.achievementboard.entity.Routine;

import java.util.List;

public interface RoutineService extends BaseService<Routine> {
    Routine getRouteByName(String walking);

    List<Routine> getAllRoutines(User user);

    List<Routine> getAllRoutinesSortedByDifficulty(User user);

    List<Routine> getAllRoutinesSortByHourSpend(User user);
}
