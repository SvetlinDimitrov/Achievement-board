package com.example.achievementboard.repos;

import com.example.achievementboard.domain.entity.RoutineEntity;
import com.example.achievementboard.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<RoutineEntity, Long> {
    RoutineEntity findByName(String name);
    List<RoutineEntity> findAllByUserEntity_Id(Long userID);

    @Query("select r From RoutineEntity r order by RAND() limit 1")
    RoutineEntity getRandomRoutine();

}
