package com.example.achievementboard.repos;

import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    Routine findByName(String name);
    List<Routine> findAllByUser(User user);
}
