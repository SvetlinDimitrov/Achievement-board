package com.example.achievementboard.domain.entity;

import com.example.achievementboard.domain.dtos.achievement.AchievementChange;
import com.example.achievementboard.domain.constants.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AchievementEntity extends BaseEntity {
    @Column
    private String name;

    @Column
    private String descriptionHowItWasSucceeded;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity userEntity;

    @ManyToOne
    private RoutineEntity routineEntity;

    @Column
    private Long dayTook;

    @Column
    private String pictureRes;

    public AchievementChange toDto() {
        return AchievementChange.builder()
                .name(name)
                .descriptionHowItWasSucceeded(descriptionHowItWasSucceeded)
                .difficulty(difficulty)
                .startDate(startDate)
                .endDate(endDate)
                .routineId(routineEntity == null ? 0 : routineEntity.getId())
                .dayTook(dayTook)
                .id(getId())
                .build();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AchievementEntity that = (AchievementEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(descriptionHowItWasSucceeded, that.descriptionHowItWasSucceeded) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && difficulty == that.difficulty && Objects.equals(userEntity, that.userEntity) && Objects.equals(routineEntity, that.routineEntity) && Objects.equals(dayTook, that.dayTook) && Objects.equals(pictureRes, that.pictureRes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, descriptionHowItWasSucceeded, startDate, endDate, difficulty, userEntity, routineEntity, dayTook, pictureRes);
    }
}
