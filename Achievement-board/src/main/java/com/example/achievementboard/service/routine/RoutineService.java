package com.example.achievementboard.service.routine;

import com.example.achievementboard.domain.dtos.routine.RoutineCreate;
import com.example.achievementboard.domain.dtos.routine.RoutineChange;
import com.example.achievementboard.domain.dtos.routine.RoutineView;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.BaseService;

import java.util.List;

public interface RoutineService extends BaseService<RoutineEntity> {

    List<RoutineView> getAllRoutines(Long userId);

    List<RoutineView> getAllRoutinesSortedByDifficulty(Long userId);

    List<RoutineView> getAllRoutinesSortByHourSpend(Long userId);

    void add(RoutineCreate createRoutine, Long userId);

    void deleteRoutine(Long id);

    void edit(RoutineChange createRoutine);

    RoutineEntity getRandomRoutine();

}
