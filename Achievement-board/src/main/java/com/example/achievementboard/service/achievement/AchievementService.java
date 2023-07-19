package com.example.achievementboard.service.achievement;

import com.example.achievementboard.constants.dtos.AchievementCreate;
import com.example.achievementboard.entity.User;
import com.example.achievementboard.service.BaseService;
import com.example.achievementboard.entity.Achievement;

import java.util.List;

public interface AchievementService extends BaseService<Achievement> {
    List<Achievement> getAllAchievements(User user);

    List<Achievement> getAllAchievementsSortedByTimeTook(User user);

    List<Achievement> getAllAchievementsSortedByDifficulty(User user);

    void save(AchievementCreate achievementCreate, User user);
}
