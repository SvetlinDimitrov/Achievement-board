package com.example.achievementboard.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(age, user.age) && Objects.equals(birthDate, user.birthDate) && Objects.equals(password, user.password) && Objects.equals(achievementEntities, user.achievementEntities) && Objects.equals(goalEntities, user.goalEntities) && Objects.equals(routineEntities, user.routineEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, age, birthDate, password, achievementEntities, goalEntities, routineEntities);
    }
}
