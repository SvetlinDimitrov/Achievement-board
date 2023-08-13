package com.example.achievementboard.service.achievement;

import com.example.achievementboard.domain.dtos.achievement.AchievementCreate;
import com.example.achievementboard.domain.dtos.achievement.AchievementChange;
import com.example.achievementboard.domain.dtos.achievement.AchievementView;
import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import com.example.achievementboard.service.BaseService;

import java.util.List;

public interface AchievementService extends BaseService<AchievementEntity> {

    List<AchievementView> getAllAchievementsSortedByTimeTook(Long userID);

    List<AchievementView> getAllAchievementsSortedByDifficulty(Long userID);

    void save(AchievementCreate achievementCreate, Long userID);

    void edit(AchievementChange achievementChange);

    void deleteAch(Long l);
}
