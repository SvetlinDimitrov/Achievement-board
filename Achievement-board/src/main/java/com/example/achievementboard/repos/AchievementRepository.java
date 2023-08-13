package com.example.achievementboard.repos;

import com.example.achievementboard.domain.entity.AchievementEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    List<AchievementEntity> findAllByUserEntity_Id(Long userId);

}


