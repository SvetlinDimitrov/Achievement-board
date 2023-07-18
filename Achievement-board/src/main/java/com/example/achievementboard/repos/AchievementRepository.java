package com.example.achievementboard.repos;

import com.example.achievementboard.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement , Integer> {
}


