package com.example.achievementboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity{
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private Integer age;
    @Column
    private LocalDate birthDate;
    @Column
    private String password;

    @OneToMany(mappedBy = "userEntity" , cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private List<AchievementEntity> achievementEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity" , cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private List<GoalEntity> goalEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private List<RoutineEntity> routineEntities = new ArrayList<>();
}
