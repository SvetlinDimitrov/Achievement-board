package com.example.achievementboard.service.routine;

import com.example.achievementboard.constants.BaseService;
import com.example.achievementboard.entity.Routine;

public interface RoutineService extends BaseService<Routine> {
    Routine getRouteByName(String walking);
}
