package com.example.achievementboard.repos;

import com.example.achievementboard.entity.Routine;
import com.example.achievementboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    Routine findByName(String name);
    List<Routine> findAllByUser(User user);

    @Query("select r From Routine r order by RAND() limit 1")
    Routine getRandomRoutine();

}
