package com.example.achievementboard.repos;

import com.example.achievementboard.entity.Achievement;
import com.example.achievementboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement , Integer> {
    List<Achievement> findAllByUser(User user);
}


