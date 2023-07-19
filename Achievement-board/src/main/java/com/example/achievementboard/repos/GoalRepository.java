package com.example.achievementboard.repos;

import com.example.achievementboard.entity.Goal;
import com.example.achievementboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findAllByUser(User user);
    List<Goal> findAllByUserOrderByEndDate(User user);
}
