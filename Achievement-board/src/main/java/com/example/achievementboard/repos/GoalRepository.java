package com.example.achievementboard.repos;

import com.example.achievementboard.domain.entity.GoalEntity;
import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, Long> {
    List<GoalEntity> findAllByUserEntity_Id(Long userId);
    List<GoalEntity> findAllByUserEntity_IdOrderByEndDate(Long userId);
    List<GoalEntity> findAllByRoutineEntity_Id(Long routineId);
}
