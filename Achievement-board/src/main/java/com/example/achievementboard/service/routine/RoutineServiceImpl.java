package com.example.achievementboard.service.routine;

import com.example.achievementboard.repos.RoutineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository repository;
}
