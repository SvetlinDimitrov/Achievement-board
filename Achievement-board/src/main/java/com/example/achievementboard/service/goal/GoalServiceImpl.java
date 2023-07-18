package com.example.achievementboard.service.goal;

import com.example.achievementboard.repos.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {
    private final GoalRepository repository;
}
