package com.example.achievementboard.service.achievement;

import com.example.achievementboard.repos.AchievementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository repository;

}
