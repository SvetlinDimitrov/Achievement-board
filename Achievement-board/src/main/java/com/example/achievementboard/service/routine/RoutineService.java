package com.example.achievementboard.service.routine;

import com.example.achievementboard.constants.dtos.routine.RoutineCreate;
import com.example.achievementboard.constants.dtos.routine.RoutineView;
import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.BaseService;

import java.util.List;

public interface RoutineService extends BaseService<Routine> {

    List<Routine> getAllRoutines(User user);

    List<Routine> getAllRoutinesSortedByDifficulty(User user);

    List<Routine> getAllRoutinesSortByHourSpend(User user);

    void add(RoutineCreate createRoutine, User user);

    void deleteRoutine(Long id);

    void edit(RoutineView createRoutine);

    Routine getRandomRoutine();

}
